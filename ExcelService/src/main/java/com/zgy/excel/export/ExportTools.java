package com.zgy.excel.export;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zgy.excel.export.annotation.ExcelExport;
import com.zgy.excel.export.config.ExcelExportConfig;
import com.zgy.excel.export.config.MergeCellIndex;
import com.zgy.excel.export.model.HeaderConfig;
import com.zgy.excel.export.model.HeaderInfo;
import com.zgy.excel.export.reflect.ParseExcelExportConfig;
import com.zgy.excel.export.style.ExcelStyle;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author a4423
 * 导出工具类
 */
public class ExportTools<T> {
    private static final Logger log = LoggerFactory.getLogger(ExportTools.class);

    private String excelPath = "export";
    private int curRow = 0;


    /**
     * 导出excel
     */
    public String generateExcel(String excelPath,List<T> data, Class tCls) throws Exception {
        ExcelExportConfig<T> excelExportConfig = new ExcelExportConfig<>();
        excelExportConfig.setDataSource(data);

        ParseExcelExportConfig.fillExcelExportConfig(excelExportConfig, tCls);
        Workbook workbook = null;
        if (StringUtils.isBlank(excelPath)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(excelPath));
        }
        // 判断传入的配置类是否符合要求
        if (excelExportConfig.getTitles() == null || excelExportConfig.getTitles().length == 0) {
            throw new Exception("请传入正确的表头");
        }
        int totalColLen = excelExportConfig.getTitles().length;
        // 创建工作簿
        Sheet activeSheet = workbook.createSheet(excelExportConfig.getSheetName());
        // 判断是否需要表头
        if (excelExportConfig.isShowHeader()) {
            curRow = 0;
            // 创建表头
            createHeader(workbook, activeSheet, excelExportConfig.getHeader(), totalColLen);
        }
        // 创建标题
        if (excelExportConfig.isMergedTitle()) {
            // 是否合并标题
            createMergedTitles(workbook, activeSheet, excelExportConfig.getTitles(), excelExportConfig.getHeaderConfig());
        } else {
            createTitles(workbook, activeSheet, excelExportConfig.getTitles());
        }

        // 填充数据

        fillData(activeSheet, excelExportConfig.getDataSource(), excelExportConfig.getFields(),
                excelExportConfig.isCenterAllFields(), excelExportConfig.getCenterFields());
        // 判断是否需要合并行单元格
        if (excelExportConfig.isNeedMergedRows()) {
            mergeRows(activeSheet, excelExportConfig.getMergeCellIndexList());
        }
        // 自动缩放
        autoSizeColumWidth(activeSheet, totalColLen, excelExportConfig.isSwarpLine());
        // 保存文件
        String saveName = saveFile(workbook, excelExportConfig.getExcelFileName());
        log.info("保存后的文件名为:" + saveName);
        return saveName;
    }

    /**
     * 创建excel的表头
     *
     * @param workbook
     * @param sheet
     * @param header
     * @param mergerdCellCount
     * @return
     */
    private void createHeader(Workbook workbook, Sheet sheet, String header, int mergerdCellCount) {
        // 创建行
        Row headerRow = sheet.createRow(curRow++);
        // 合并列
        if (mergerdCellCount > 1) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, mergerdCellCount - 1));
        }
        // 创建单元格
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellStyle(ExcelStyle.getHeaderCellStyle(sheet));
        // 赋值
        headerCell.setCellValue(header);
        CellUtil.setAlignment(headerCell, HorizontalAlignment.CENTER);
    }


    /**
     * 创建表头
     *
     * @param workbook
     * @param sheet
     * @param titles
     */
    private void createTitles(Workbook workbook, Sheet sheet, String[] titles) {
        Row titleRow = sheet.createRow(curRow++);
        int len = titles.length;
        for (int i = 0; i < len; i++) {
            Cell titleCell = titleRow.createCell(i);
            titleCell.setCellStyle(ExcelStyle.getTitleCellStyle(sheet));
            titleCell.setCellValue(titles[i]);
        }
    }

    private void createMergedTitles(Workbook workbook, Sheet sheet, String[] titles, HeaderConfig headerConfig) {
        if (headerConfig.getMergedCount() == 1) {
            createTitles(workbook, sheet, titles);
        } else {
            int mergedCount = headerConfig.getMergedCount();
            List<HeaderInfo> headerInfoList = headerConfig.getHeaderInfoList()
                    .stream().sorted(Comparator.comparing(HeaderInfo::getSerial))
                    .collect(Collectors.toList());

            for (int row = 1; row < mergedCount; row++) {
                Row titleRow = sheet.createRow(curRow++);
                for (int i = 0; i < headerInfoList.size(); i++) {
                    HeaderInfo headerInfo = headerInfoList.get(i);
                    Cell titleCell = titleRow.createCell(i);
                    titleCell.setCellStyle(ExcelStyle.getTitleCellStyle(sheet));
                    titleCell.setCellValue(headerInfo.getName());
                    if (headerInfo.getChildren() == null || headerInfo.getChildren().size() == 0) {
                        sheet.addMergedRegion(new CellRangeAddress(row, mergedCount - row, i, i));
                    }/*else {
                        sheet.addMergedRegion(new CellRangeAddress(row,row,i,headerInfo.getChildren().size()));
                    }*/
                }
            }
        }
    }

    /**
     * 填充业务数据
     *
     * @param sheet
     * @param dataList
     */
    private void fillData(Sheet sheet, List<T> dataList, String[] fields, boolean isAllCenter, Set<String> centerFields) {
        if (dataList != null) {
            Gson gson = new Gson();
            int len = dataList.size();
            for (int i = 0; i < len; i++) {
                T obj = dataList.get(i);
                String json = gson.toJson(obj);
                JSONObject jsonObject = JSONObject.parseObject(json);
                // 插入行
                insertRow(sheet, jsonObject, fields, isAllCenter, centerFields);
            }
        }
    }

    private void mergeRows(Sheet sheet, List<MergeCellIndex> mergeCellIndiceList) {
        for (MergeCellIndex cellIndex : mergeCellIndiceList) {
            sheet.addMergedRegion(new CellRangeAddress(cellIndex.getFirstRowIndex(), cellIndex.getLastRowIndex(), cellIndex.getFirstColIndex(), cellIndex.getLastColIndex()));
        }
    }

    /**
     * 填充每行的数据
     *
     * @param sheet
     * @param jsonObject
     * @param fields
     */
    private void insertRow(Sheet sheet, JSONObject jsonObject, String[] fields, boolean isAllCenter, Set<String> centerFields) {
        Row insertRow = sheet.createRow(curRow++);
        int colCount = fields.length;

        for (int i = 0; i < colCount; i++) {
            boolean isCenter = false;
            if (isAllCenter) {
                isCenter = true;
            }
            Cell itemCell = insertRow.createCell(i);
            if (!isCenter) {
                if (centerFields.contains(fields[i])) {
                    isCenter = true;
                }
            }
            itemCell.setCellStyle(ExcelStyle.getValueCellStyle(sheet, isCenter ? HorizontalAlignment.CENTER : HorizontalAlignment.LEFT));

            if (jsonObject.containsKey(fields[i])) {
                checkTypeAndFillCellValue(itemCell, jsonObject.get(fields[i]));
            } else {
                itemCell.setCellValue("");
            }
        }
    }

    /**
     * 检查json的类型并进行处理
     * 目前日期类型作为字符串进行处理，为进行特殊处理
     *
     * @param cell
     * @param object
     */
    private void checkTypeAndFillCellValue(Cell cell, Object object) {
        if (object instanceof Integer || object instanceof Long) {
            cell.setCellValue(((Number) object).longValue());
        } else if (object instanceof Boolean) {
            cell.setCellValue((Boolean) object);
        } else {
            cell.setCellValue(object.toString());
        }
    }

    /**
     * 根据内容对excel表格进行自动缩放
     *
     * @param sheet
     * @param totalCol
     */
    private void autoSizeColumWidth(Sheet sheet, int totalCol, boolean isSwarpLine) {
        for (int i = 0; i < totalCol; i++) {
            sheet.autoSizeColumn(i);
            if (!isSwarpLine) {
                int colWidth = sheet.getColumnWidth(i) * 17 / 10;
                if (colWidth < 255 * 256) {
                    // 将默认的列宽放大1.7倍
                    sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
                } else {
                    // 将默认的列宽放大1.7倍
                    sheet.setColumnWidth(i, 6000);
                }

            } else {
                int colWidth = sheet.getColumnWidth(i) + 1000;
                if (colWidth < 255 * 256) {
                    // 将默认的列宽放大1.7倍
                    sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
                } else {
                    // 将默认的列宽放大1.7倍
                    sheet.setColumnWidth(i, 6000);
                }

            }

        }
    }

    /**
     * 将excel保存为文件
     *
     * @param workbook
     * @param fileName
     */
    private String saveFile(Workbook workbook, String fileName) throws IOException {
        if (Files.notExists(Paths.get(excelPath))) {
            Files.createDirectories(Paths.get(excelPath));
        }
        String filePathByName = excelPath + "//" + fileName + ".xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(filePathByName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePathByName;
    }


}
