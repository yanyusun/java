package com.dqys.core.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mkfeng on 2016/6/27.
 */
public class ExcelTool {


    /**
     * HSSF读取表格文件
     */
    public static List<Map<String, Object>> readExcelForList(String filepath, String filename, int startrow, int startcol, int sheetnum) {
        List<Map<String, Object>> varList = new ArrayList<Map<String, Object>>();
        try {
            File target = new File(filepath, filename);
            FileInputStream fi = new FileInputStream(target);
            HSSFWorkbook wb = new HSSFWorkbook(fi);
            HSSFSheet sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
            if (sheet != null) {
                int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号
                for (int i = startrow; i < rowNum; i++) { // 行循环开始
                    Map<String, Object> varpd = new HashMap<String, Object>();
                    HSSFRow row = sheet.getRow(i); // 行
                    if (row != null) {
                        int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置
                        for (int j = startcol; j < cellNum; j++) { // 列循环开始
                            HSSFCell cell = row.getCell(j);
                            String cellValue = null;
                            if (null != cell) {
                                cellValue = getCellValue(cell);
                            } else {
                                cellValue = "";
                            }
                            varpd.put("var" + j, cellValue);
                        }
                        varList.add(varpd);
                    }
                }
            }
        } catch (Exception e) {
            return readExcelXSSFForList(filepath, filename, startrow, startcol, sheetnum);
        }
        return varList;
    }

    /**
     * XSSF读取表格文件
     */
    public static List<Map<String, Object>> readExcelXSSFForList(String filepath, String filename, int startrow, int startcol, int sheetnum) {
        List<Map<String, Object>> varList = new ArrayList<Map<String, Object>>();
        try {
            File target = new File(filepath, filename);
            FileInputStream fi = new FileInputStream(filepath + filename);
            XSSFWorkbook wb = new XSSFWorkbook(fi);
            XSSFSheet sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
            if (sheet != null) {
                int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号
                for (int i = startrow; i < rowNum; i++) { // 行循环开始
                    Map<String, Object> varpd = new HashMap<String, Object>();
                    XSSFRow row = sheet.getRow(i); // 行
                    if (row != null) {
                        int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置
                        for (int j = startcol; j < cellNum; j++) { // 列循环开始
                            XSSFCell cell = row.getCell(j);
                            String cellValue = null;
                            if (null != cell) {
                                cellValue = getCellValue(cell);
                            } else {
                                cellValue = "";
                            }
                            varpd.put("var" + j, cellValue);
                        }
                        varList.add(varpd);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return varList;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> maps = readExcelXSSFForList("f://", "2.xlsx", 0, 0, 0);
        for (Map map : maps) {
            System.out.println(map.get("var1"));
        }
    }

    private static String getCellValue(Cell cell) {
        String ret;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                ret = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                ret = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                ret = null;
                break;
            case Cell.CELL_TYPE_FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                CreationHelper crateHelper = wb.getCreationHelper();
                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
                ret = getCellValue(evaluator.evaluateInCell(cell));
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                    ret = formater.format(d);
                } else {
                    ret = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                ret = cell.getRichStringCellValue().getString();
                break;
            default:
                ret = null;
        }
        return ret; // 有必要自行trim
    }

    /**
     * @param dataList 数据集合
     * @param headers  //数据字段
     * @param path     //保存文件的路径
     * @return
     */
    public static void exportExcel(List<String[]> dataList, String[] headers, String path, String fileName) {
        OutputStream out = null;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            out = new FileOutputStream(new File(path + "/" + fileName));
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet("导入失败");
            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            int index = 1;
            for (String[] body : dataList) {
                row = sheet.createRow(index++);
                for (int i = 0; i < body.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(body[i]);
                }
            }
            workbook.write(out);
            out.close();
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
