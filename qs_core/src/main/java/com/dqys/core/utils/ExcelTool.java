package com.dqys.core.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/16.
 */
public class ExcelTool {
    /**
     * filepath 文件路径
     * filename 文件名称
     * startrow 开始行数
     * startcol 开始列数
     * sheetnum 开始表格
     */
    public static List<Object> readExcelForList(String filepath, String filename, int startrow, int startcol,
                                                int sheetnum) {
        List<Object> varList = new ArrayList<Object>();

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
            e.printStackTrace();
        }

        return varList;
    }

    public static String getCellValue(Cell cell) {
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

                ret = NumberToTextConverter.toText(cell.getNumericCellValue());

                break;
            case Cell.CELL_TYPE_STRING:
                ret = cell.getRichStringCellValue().getString();
                break;
            default:
                ret = null;
        }
        return ret; // 有必要自行trim
    }

}
