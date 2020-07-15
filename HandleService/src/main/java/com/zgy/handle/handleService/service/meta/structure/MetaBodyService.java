package com.zgy.handle.handleService.service.meta.structure;

import com.zgy.handle.handleService.client.EntepriseFeignClient;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.EnterprisePre;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.model.meta.structure.enterprise.xml.*;
import com.zgy.handle.handleService.repository.meta.structure.MetaBodyRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MetaBodyService extends SystemService<MetaBody, MetaBodyDTO> {
    private MetaBodyRepository metaBodyRepository;
    @Autowired
    EntepriseFeignClient entepriseFeignClient;
    @Autowired
    private MetaHeaderService metaHeaderService;
    @Autowired
    public MetaBodyService(MetaBodyRepository metaBodyRepository) {
        super(metaBodyRepository);
        this.metaBodyRepository = metaBodyRepository;
    }

    public List<MetaBody> findByHeaderId(Long headerId){
        if (headerId == null){
            throw new EntityNotFoundException("请传入元数据头部id");
        }
        return metaBodyRepository.findByMetaHeaderId(headerId);
    }

    public Page<MetaBody> findByHeaderId(Long headerId, Pageable pageable){
        if (headerId == null){
            throw new EntityNotFoundException("请传入元数据头部id");
        }
        return metaBodyRepository.findByMetaHeaderId(headerId,pageable);
    }

    @Override
    public void beforeUpdate(MetaBodyDTO metaBodyDTO, MetaBody metaBody) {
        if (StringUtils.isNotBlank(metaBodyDTO.getHeaderId())){
            Optional<MetaHeader> metaHeaderOptional = metaHeaderService.findById(Long.valueOf(metaBodyDTO.getHeaderId()));
            if (metaHeaderOptional.isPresent()){
                metaBody.setMetaHeader(metaHeaderOptional.get());
            }
        }
    }

    public void createRegisterMetaDataXml(MetaHeader metaHeader, List<MetaBody> metaBodyList){
        EnterprisePre enterprisePre = entepriseFeignClient.getEnterpriseInfo(metaHeader.getEnterpriseId().toString());
        if (enterprisePre == null)
            throw new EntityNotFoundException("找不到对应的企业");
        //PrefixInfo prefixInfo = metaNode.getPrefixInfo();
        String prefixIdentifiy = enterprisePre.getPrefix() + "/";
        //String prefixIdentifiy = "test/";
        try {
            JAXBContext contextObj = JAXBContext.newInstance(XmlMetaData.class);

            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

            List<XmlCol> xmlColList = new ArrayList<>();
            for (MetaBody metaBody : metaBodyList){
                XmlCol xmlCol1 = new XmlCol(metaBody.getBody().getName(),metaBody.getBody().getDescription(),metaBody.getBody().getColType().name(),metaBody.getBody().getFieldLen());
                xmlColList.add(xmlCol1);
            }

            XmlData xmlData = new XmlData(xmlColList);
            XmlHeader xmlHeader = new XmlHeader(metaHeader.getHeader().getIdentityNum(),getMetaNodeLevel(metaHeader),metaHeader.getParent().getHeader().getIdentityNum());
            ListRecord listRecord = new ListRecord(getRootIdentityNum(metaHeader),"0",xmlHeader,xmlData);

            XmlMetaData xmlMetaData = new XmlMetaData(listRecord);

            String fileName = "metadata_"+metaHeader.getHeader().getIdentityNum().replace("/","_")+".xml";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            marshallerObj.marshal(xmlMetaData,fileOutputStream);

            filePost(fileName,0,"",enterprisePre);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void filePost(String fileName,int type,String metaHandleCode,EnterprisePre enterprisePre){
        //EnterprisePre enterprisePre = entepriseFeignClient.getEnterpriseInfo(metaHeader.getEnterpriseId().toString());
        //String url = "http://114.115.215.119:8011";
        String url = enterprisePre.getPrefix();
        log.info("获取用户:" + getPersonalId() + "的sessionId");
        String sessionId = entepriseFeignClient.getSessionId(getPersonalId());
        log.info("获取到当前用户的sessionId为:" + sessionId);
        log.info("获取到当前用户的sessionId为:" + sessionId);
        if (type == 0){
            // 元数据标准的注册
            url += "/api/datadefine";
        }else if (type == 1){
            url += "/api/dataupload/" + metaHandleCode;
        }

        //String fileName = "F://metatest1.xml";

        File file = new File(fileName);
        /*RestResponse restResponse = new RestResponse();*/
        try {
            if (!file.exists() || !file.isFile()) {
                throw new IOException("文件不存在");
            }
            URL urlObj = new URL(url);
            // 连接
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            /**
             * 设置关键值
             */
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("charset", "UTF-8");

            con.setRequestProperty("Cookie","JSESSIONID=" +sessionId);
            con.setRequestProperty("Authorization","Handle version=\"0\",sessionId=\"" + sessionId + "\"");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("Content-length", String.valueOf(file.length()));
            // 设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
            // 请求正文信息
            // 第一部分：
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                    + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            sb.append("Authorization:Handle version=\"0\",sessionId=\"" + sessionId + "\"");
            sb.append("Cookie:JSESSIONID=" +sessionId);
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            BufferedReader reader = null;
            try {
                //返回值
                int resultCode = con.getResponseCode();
                if (resultCode == HttpURLConnection.HTTP_OK) {
                    // reads server's response
                    BufferedReader outReader = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String response = outReader.readLine();
                    System.out.println("Server's response: " + response);
                } else {
                    System.out.println("Server returned non-OK code: " + resultCode );
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
                    String errorResult = errorReader.readLine();
                    log.info("注册元数据标准的错误代码为:" + errorResult);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }catch (IOException e2){
			/*restResponse.setCode(HttpStatus.GONE.value());
			restResponse.setMessage(e2.toString());
			logger.error(e2.toString());*/
        }
        //return restResponse;
    }

    private String getRootIdentityNum(MetaHeader metaHeader){
        String rootIdentity =  "";
        while (metaHeader.getParent() != null){
            rootIdentity = metaHeader.getParent().getHeader().getIdentityNum();
            metaHeader = metaHeader.getParent();
        }
        return rootIdentity;
    }

    private int getMetaNodeLevel(MetaHeader metaHeader){
        int level = 0;
        /*if (metaHeader.getParent() != null && metaHeader.getParent().getParent() == null){
            level = 0;
        }else {
            level = 2;
        }*/
        return level;
    }
}
