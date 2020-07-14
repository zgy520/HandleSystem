package com.zgy.handle.handleService.service.meta.bus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.client.EntepriseFeignClient;
import com.zgy.handle.handleService.model.meta.bus.BusDetails;
import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.model.meta.simulate.WLData;
import com.zgy.handle.handleService.model.meta.simulate.XSData;
import com.zgy.handle.handleService.model.meta.structure.enterprise.EnterprisePre;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.meta.bus.BusDetailsRepository;
import com.zgy.handle.handleService.repository.meta.bus.BusPrimaryRepository;
import com.zgy.handle.handleService.repository.meta.structure.MetaBodyRepository;
import com.zgy.handle.handleService.repository.meta.structure.MetaHeaderRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusPrimaryService extends SystemService<BusPrimary,BusPrimary> {
    private BusPrimaryRepository busPrimaryRepository;
    @Autowired
    private EntepriseFeignClient entepriseFeignClient;
    @Autowired
    private BusDetailsRepository busDetailsRepository;
    @Autowired
    private MetaHeaderRepository metaHeaderRepository;
    @Autowired
    private MetaBodyRepository metaBodyRepository;
    @Autowired
    public BusPrimaryService(BusPrimaryRepository busPrimaryRepository) {
        super(busPrimaryRepository);
        this.busPrimaryRepository = busPrimaryRepository;
    }

    public ResponseCode updateBusiness(Long headerId,String jsonData,String relaHandleCode){
        ResponseCode responseCode = ResponseCode.sucess();
        List<BusPrimary> busPrimaryList = new ArrayList<>(); // 主键列表
        List<BusDetails> busDetailsList = new ArrayList<>(); // 业务数据列表

        Optional<MetaHeader> metaHeaderOptional = metaHeaderRepository.findById(headerId);
        if (metaHeaderOptional.isPresent()){
            MetaHeader metaHeader = metaHeaderOptional.get();
            busPrimaryRepository.deleteByMetaHeaderId(headerId);
            List<MetaBody> metaBodyList = metaBodyRepository.findByMetaHeaderId(headerId);
            JSONArray jsonArray = JSONArray.parseArray(jsonData);
            saveBusinessData(metaHeader,jsonArray,metaBodyList);
        }else {
            throw new EntityNotFoundException("元数据id:" + headerId.toString()  + "不存在");
        }

        return responseCode;
    }


    public ResponseCode<JSONArray> getBusData(Long metaHeaderId){
        JSONArray jsonArray = new JSONArray();
        ResponseCode<JSONArray> responseCode = ResponseCode.sucess();
        List<BusPrimary> busPrimaryList = busPrimaryRepository.findByMetaHeaderId(metaHeaderId);
        for (BusPrimary busPrimary : busPrimaryList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("primaryValue",busPrimary.getPrimaryValue());
            jsonObject.put("handleCode",busPrimary.getHandleCode());
            jsonObject.put("primaryId",busPrimary.getId().toString());
            List<BusDetails> busDetailsList = busDetailsRepository.findByBusPrimaryId(busPrimary.getId());
            for (BusDetails busDetails : busDetailsList){
                jsonObject.put(busDetails.getMetaBody().getBody().getName(),busDetails.getFieldValue());
            }
            jsonObject.put("enterpriseName","待确认");
            jsonArray.add(jsonObject);
        }
        responseCode.setData(jsonArray);
        return responseCode;
    }

    public ResponseCode<JSONObject> getBusPageData(Long metaHeaderId, Pageable pageable){
        JSONArray jsonArray = new JSONArray();
        JSONObject finalJson = new JSONObject();
        ResponseCode<JSONObject> responseCode = ResponseCode.sucess();
        Page<BusPrimary> page = busPrimaryRepository.findByMetaHeaderId(metaHeaderId,pageable);
        List<BusPrimary> busPrimaryList = page.getContent();
        for (BusPrimary busPrimary : busPrimaryList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("primaryValue",busPrimary.getPrimaryValue());
            jsonObject.put("handleCode",busPrimary.getHandleCode());
            jsonObject.put("primaryId",busPrimary.getId().toString());
            List<BusDetails> busDetailsList = busDetailsRepository.findByBusPrimaryId(busPrimary.getId());
            for (BusDetails busDetails : busDetailsList){
                jsonObject.put(busDetails.getMetaBody().getBody().getName(),busDetails.getFieldValue());
            }
            //jsonObject.put("enterpriseName","待确认");
            jsonArray.add(jsonObject);
        }
        finalJson.put("data",jsonArray);
        /*finalJson.put("totalElements",page.getTotalElements());
        finalJson.put("totalPage",page.getTotalPages());*/
        responseCode.setData(finalJson);
        responseCode.setPageInfo(page);
        return responseCode;
    }


    @Transactional
    public void saveBusinessData(MetaHeader metaHeader, JSONArray jsonArray, List<MetaBody> metaBodyList){
        long maxPriamry = busPrimaryRepository.findByMetaHeaderId(metaHeader.getId())
                .stream().mapToLong(BusPrimary::getPrimaryValue).max().orElse(0L);
        int size = jsonArray.size();
        List<BusDetails> detailsList = new ArrayList<>();
        List<BusPrimary> busPrimaryList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BusPrimary busPrimary = null;
            if (!jsonObject.containsKey("handle")){
                busPrimary = saveBusPrimary(metaHeader,++maxPriamry,"");
            }else {
                busPrimary = saveBusPrimary(metaHeader,++maxPriamry,jsonObject.getString("handle"));
            }

            busPrimaryList.add(busPrimary);
            List<BusDetails> busDetails = getDetails(busPrimary,jsonObject,metaBodyList);
            detailsList.addAll(busDetails);
        }
        busDetailsRepository.saveAll(detailsList);
        try {
            createDownloadExcel("",metaHeader,busPrimaryList,metaBodyList,detailsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<BusDetails> getDetails(BusPrimary busPrimary,JSONObject jsonObject,List<MetaBody> metaBodies){
        List<BusDetails> busDetailsList = new ArrayList<>();
        for (MetaBody metaBody : metaBodies){
            BusDetails busDetails = new BusDetails();
            if (jsonObject.containsKey(metaBody.getBody().getName())){
                busDetails.setBusPrimary(busPrimary);
                busDetails.setFieldValue(jsonObject.getString(metaBody.getBody().getName()));
                busDetails.setMetaBody(metaBody);
                busDetailsList.add(busDetails);
            }
        }
        return busDetailsList;
    }

    private BusPrimary saveBusPrimary(MetaHeader header,Long primary,String importHandle){
        BusPrimary busPrimary = new BusPrimary();
        busPrimary.setMetaHeader(header);
        busPrimary.setPrimaryValue(primary);
        if (StringUtils.isBlank(importHandle)){
            busPrimary.setHandleCode(header.getHeader().getIdentityNum() + "_" + primary);
        }else {
            busPrimary.setHandleCode(importHandle);
        }

        busPrimaryRepository.save(busPrimary);
        return busPrimary;
    }
    private void createDownloadExcel(String realHandle,MetaHeader metaHeader, List<BusPrimary> busPrimaryList, List<MetaBody> metaBodyList, List<BusDetails> busDetailsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        EnterprisePre enterprisePre = entepriseFeignClient.getEnterpriseInfo(metaHeader.getEnterpriseId().toString());
        //Sheet sheet = workbook.createSheet(metaNode.getIdentityNum());
        if (enterprisePre == null)
            throw new EntityNotFoundException("找不到对应的企业");
        Sheet sheet = workbook.createSheet("业务数据");

        //PrefixInfo prefixInfo = metaNode.getPrefixInfo();
        String prefixIdentifiy = enterprisePre.getPrefix() + "/";

        // create a row
        Row headerRow = sheet.createRow(0);
        createHeaderCell(workbook,headerRow,metaBodyList);
        // 创建列数据
        int rowIndex = 1;
        // String enterpriseHandleCode = prefixIdentifiy;
        for (BusPrimary busPrimary : busPrimaryList){
            Row dataRow = sheet.createRow(rowIndex++);
            String handleCodeOfData = busPrimary.getHandleCode();
            if (StringUtils.isBlank(handleCodeOfData)){
                handleCodeOfData = metaHeader.getHeader().getIdentityNum() + "_" + busPrimary.getPrimaryValue();
            }

            dataRow.createCell(0).setCellValue(handleCodeOfData);
            // 获取改数据下的所有元数据信息
            int cellIndex = fillMetaDataInfo(dataRow,busPrimary,metaBodyList,busDetailsList);
            // 获取对应的外键信息
            //List<HandleRelate> handleRelateList = handleRelateRepository.findByHandleCode(metaPrimary.getHandleCode());
            if (!realHandle.equals("0")){
                Cell cell = dataRow.createCell(cellIndex);

                cell.setCellValue(realHandle);
            }
            //cell.setCellValue(handleRelateList.stream().map(HandleRelate::getRelateHandleCode).collect(Collectors.joining(",")));
        }

        // Write the output to a file
        String fileName = metaHeader.getHeader().getIdentityNum().split("/")[1]+".xlsx";
        FileOutputStream fileOut = new FileOutputStream(fileName);

        workbook.write(fileOut);
        fileOut.close();
        filePost(fileName,1,metaHeader.getHeader().getIdentityNum(),enterprisePre);
    }

    /**
     * 创建标题
     * @param workbook
     * @param headerRow
     * @param metaBodyList
     */
    private void createHeaderCell(Workbook workbook,Row headerRow,List<MetaBody> metaBodyList){
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        int cellIndex = 0;
        Cell cell = headerRow.createCell(cellIndex++);
        cell.setCellValue("handle");
        cell.setCellStyle(headerCellStyle);
        // 创建其他的标题
        for (MetaBody metaData : metaBodyList){
            Cell mCell = headerRow.createCell(cellIndex++);
            mCell.setCellValue(metaData.getBody().getName());
            mCell.setCellStyle(headerCellStyle);
        }
        // 创建最后一列
        Cell relateCell = headerRow.createCell(cellIndex);
        relateCell.setCellValue("rela_handle");
        relateCell.setCellStyle(headerCellStyle);
    }

    /**
     * 填充具体的元数据信息
     * @param dataRow
     * @param metaBodyList
     * @param busDetailsList
     */
    private int fillMetaDataInfo(Row dataRow,BusPrimary busPrimary,List<MetaBody> metaBodyList,List<BusDetails> busDetailsList){
        int cellIndex = 1;
        List<BusDetails> filterMetaBusDataList = busDetailsList.stream().filter(atmdil->atmdil.getBusPrimary().equals(busPrimary)).collect(Collectors.toList());
        for (MetaBody metaBody : metaBodyList){
            try{
                Cell cell = dataRow.createCell(cellIndex++);
                Optional<BusDetails> metaBusDataOptional = filterMetaBusDataList.stream().filter(fatmil->fatmil.getMetaBody().equals(metaBody)).findFirst();
                if (metaBusDataOptional.isPresent()){
                    String value = metaBusDataOptional.get().getFieldValue();
                    cell.setCellValue(value);
                }else {
                    continue;
                }
                //String value = filterMetaBusDataList.stream().filter(fatmil->fatmil.getMetaData().equals(metaData)).map(MetaBusData::getFieldValue).findFirst().orElse("");

            }catch (NullPointerException ex){
                log.info(metaBody.getBody().getDescription());
            }

        }
        return cellIndex;
    }

    public void filePost(String fileName,int type,String metaHandleCode,EnterprisePre enterprisePre){
        //String url = "http://114.115.215.119:8011/api/datadefine";
         String url = enterprisePre.getPrefix();
        //String url = "114.115.215.119:8011";
        String sessionId = "qdfp2e1al1vs1nocg5tflhx13"; //entepriseFeignClient.getSessionId();
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
            con.setRequestProperty("sessionId",sessionId);
            con.setRequestProperty("Handle version","0");
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
                    log.error("错误信息为:" + errorResult);
                    throw new EntityNotFoundException(errorResult);
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



    /*
    -----------------------一下为特殊处理情况-------------------------------
     */
    public ResponseCode<BusPrimary> saveWLData(WLData wlData){
        ResponseCode<BusPrimary> responseCode = ResponseCode.sucess();
        BusPrimary busPrimary = null;
        MetaHeader metaHeader = metaHeaderRepository.findByTableName("WL");
        try{
            if (metaHeader != null){
                busPrimary = saveBusPrimary(metaHeader); // 保存主键
                busPrimary.setStrId(String.valueOf(busPrimary.getId()));
                if (busPrimary == null){
                    responseCode.setSuccess(false);
                    responseCode.setMsg("主键保存失败");
                }else {
                    // 保存业务数据
                    saveWLBusData(wlData,busPrimary,metaHeader.getId());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            responseCode.setSuccess(false);
            responseCode.setMsg(ex.getLocalizedMessage());
        }
        responseCode.setData(busPrimary);
        return responseCode;
    }
    public ResponseCode<BusPrimary> saveXSData(XSData xsData){
        ResponseCode<BusPrimary> responseCode = ResponseCode.sucess();
        BusPrimary busPrimary = null;
        MetaHeader metaHeader = metaHeaderRepository.findByTableName("XS");
        try{
            if (metaHeader != null){
                busPrimary = saveBusPrimary(metaHeader); // 保存主键
                busPrimary.setStrId(String.valueOf(busPrimary.getId()));
                if (busPrimary == null){
                    responseCode.setSuccess(false);
                    responseCode.setMsg("销售数据主键保存失败");
                }else {
                    // 保存业务数据
                    saveXSBusData(xsData,busPrimary,metaHeader.getId());
                }
            }
            responseCode.setData(busPrimary);
        }catch (Exception ex){
            ex.printStackTrace();
            responseCode.setSuccess(false);
            responseCode.setMsg(ex.getLocalizedMessage());
        }
        responseCode.setData(busPrimary);
        return responseCode;
    }

    /**
     * 保存物流得具体业务数据
     * @param wlData
     * @param busPrimary
     * @param metaHeaderId
     */
    private void saveWLBusData(WLData wlData,BusPrimary busPrimary,Long metaHeaderId){
        List<MetaBody> metaBodyList = metaBodyRepository.findByMetaHeaderId(metaHeaderId);
        List<BusDetails> busDetailsList = new ArrayList<>();
        for (MetaBody metaBody : metaBodyList){
            BusDetails busDetails = new BusDetails();
            busDetails.setBusPrimary(busPrimary);
            busDetails.setMetaBody(metaBody);
            switch (metaBody.getBody().getName()){
                case "orderNum":
                    busDetails.setFieldValue(wlData.getOrderNum());
                    break;
                case "startLocation":
                    busDetails.setFieldValue(wlData.getStartLocation());
                    break;
                case "endLocation":
                    busDetails.setFieldValue(wlData.getEndLocation());
                    break;
                case "person":
                    busDetails.setFieldValue(wlData.getPerson());
                    break;
                case "phoneNumber":
                    busDetails.setFieldValue(wlData.getPhoneNumber());
                    break;
                default:
                    log.info("未预期的字段信息");
                    break;
            }
            busDetailsList.add(busDetails);
        }
        busDetailsRepository.saveAll(busDetailsList);
    }

    /**
     * 保存销售得具体业务数据
     * @param xsData
     * @param busPrimary
     * @param metaHeaderId
     */
    private void saveXSBusData(XSData xsData,BusPrimary busPrimary,Long metaHeaderId){
        List<MetaBody> metaBodyList = metaBodyRepository.findByMetaHeaderId(metaHeaderId);
        List<BusDetails> busDetailsList = new ArrayList<>();
        for (MetaBody metaBody : metaBodyList){
            BusDetails busDetails = new BusDetails();
            busDetails.setBusPrimary(busPrimary);
            busDetails.setMetaBody(metaBody);
            switch (metaBody.getBody().getName()){
                case "saleCustomer":
                    busDetails.setFieldValue(xsData.getSaleCustomer());
                    break;
                case "salePerson":
                    busDetails.setFieldValue(xsData.getSalePerson());
                    break;
                case "saleDate":
                    busDetails.setFieldValue(xsData.getSaleDate());
                    break;
                case "saleMoney":
                    busDetails.setFieldValue(xsData.getSaleMoney());
                    break;
                case "saleCount":
                    busDetails.setFieldValue(xsData.getSaleCount());
                    break;
                default:
                    log.info("未预期的字段信息");
                    break;
            }
            busDetailsList.add(busDetails);
        }
        busDetailsRepository.saveAll(busDetailsList);
    }

    /**
     * 保存主键数据
     * @param metaHeader
     * @return
     */
    private BusPrimary saveBusPrimary(MetaHeader metaHeader){
        BusPrimary busPrimary = new BusPrimary();
        busPrimary.setMetaHeader(metaHeader);
        long maxPriamry = busPrimaryRepository.findByMetaHeaderId(metaHeader.getId())
                .stream().mapToLong(BusPrimary::getPrimaryValue).max().orElse(0L) + 1;
        busPrimary.setPrimaryValue(maxPriamry);
        busPrimary.setHandleCode(metaHeader.getHeader().getIdentityNum() + "_" + maxPriamry);
        busPrimaryRepository.save(busPrimary);
        return busPrimary;
    }

    public ResponseCode updateDPRelate(Long busId,String ids){
        ResponseCode responseCode = ResponseCode.sucess();
        String realHandleCode = "";
        Optional<BusPrimary> busPrimaryOptional = busPrimaryRepository.findById(busId);
        if (busPrimaryOptional.isPresent()){
            BusPrimary busPrimary = busPrimaryOptional.get();
            List<BusPrimary> metaBusPrimaryList = new ArrayList<>();
            metaBusPrimaryList.add(busPrimary);
            MetaHeader metaHeader = busPrimary.getMetaHeader();
            List<MetaBody> metaBodyList = metaBodyRepository.findByMetaHeaderId(metaHeader.getId());
            List<BusDetails> busDetailsList = busDetailsRepository.findByBusPrimaryId(busPrimary.getId());
            // 保存handle和关联id之间的关系
            String[] idList = ids.split(",");
            for (String id : idList){
                BusPrimary dpMBP = busPrimaryRepository.findById(Long.valueOf(id)).get();
                realHandleCode += dpMBP.getHandleCode() + ",";
            }
            realHandleCode = realHandleCode.substring(0,realHandleCode.length() - 1);
            try{
                createDownloadExcel(realHandleCode,metaHeader,metaBusPrimaryList,metaBodyList,busDetailsList);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return responseCode;
    }
}
