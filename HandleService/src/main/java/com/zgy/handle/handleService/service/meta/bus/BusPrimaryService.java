package com.zgy.handle.handleService.service.meta.bus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.model.meta.bus.BusDetails;
import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.meta.bus.BusDetailsRepository;
import com.zgy.handle.handleService.repository.meta.bus.BusPrimaryRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BusDetailsRepository busDetailsRepository;
    @Autowired
    public BusPrimaryService(BusPrimaryRepository busPrimaryRepository) {
        super(busPrimaryRepository);
        this.busPrimaryRepository = busPrimaryRepository;
    }

    public void saveBusinessData(MetaHeader metaHeader, JSONArray jsonArray, List<MetaBody> metaBodyList){
        long maxPriamry = busPrimaryRepository.findByMetaHeaderId(metaHeader.getId())
                .stream().mapToLong(BusPrimary::getPrimaryValue).max().orElse(0L);
        int size = jsonArray.size();
        List<BusDetails> detailsList = new ArrayList<>();
        List<BusPrimary> busPrimaryList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BusPrimary busPrimary = saveBusPrimary(metaHeader,++maxPriamry);
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

    private BusPrimary saveBusPrimary(MetaHeader header,Long primary){
        BusPrimary busPrimary = new BusPrimary();
        busPrimary.setMetaHeader(header);
        busPrimary.setPrimaryValue(primary);
        busPrimary.setHandleCode(header.getHeader().getIdentityNum() + "_" + primary);
        busPrimaryRepository.save(busPrimary);
        return busPrimary;
    }
    private void createDownloadExcel(String realHandle,MetaHeader metaHeader, List<BusPrimary> busPrimaryList, List<MetaBody> metaBodyList, List<BusDetails> busDetailsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        //Sheet sheet = workbook.createSheet(metaNode.getIdentityNum());
        Sheet sheet = workbook.createSheet("业务数据");

        //PrefixInfo prefixInfo = metaNode.getPrefixInfo();
        //String prefixIdentifiy = metaNode.getDepartment().getPrefix() + "/";

        // create a row
        Row headerRow = sheet.createRow(0);
        createHeaderCell(workbook,headerRow,metaBodyList);
        // 创建列数据
        int rowIndex = 1;
        // String enterpriseHandleCode = prefixIdentifiy;
        for (BusPrimary busPrimary : busPrimaryList){
            Row dataRow = sheet.createRow(rowIndex++);
            String handleCodeOfData = metaHeader.getHeader().getIdentityNum() + "_" + busPrimary.getPrimaryValue();
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
        filePost(fileName,1,metaHeader.getHeader().getIdentityNum());
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

    public void filePost(String fileName,int type,String metaHandleCode){
        //String url = "http://114.115.215.119:8011/api/datadefine";
        // String url = getDepartment().getIp();
        String url = "114.115.215.119:8011";
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

}
