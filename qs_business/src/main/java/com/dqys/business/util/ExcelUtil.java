package com.dqys.business.util;

import com.dqys.business.dto.asset.ContactDTO;
import com.dqys.business.dto.asset.IouDTO;
import com.dqys.business.dto.asset.LenderDTO;
import com.dqys.business.dto.asset.PawnDTO;
import com.dqys.business.service.constant.ContactTypeEnum;
import com.dqys.core.utils.DateFormatTool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mkefeng on 2016/6/21.
 */
public class ExcelUtil {

    /**表格文件的处理*/
    public static Map<String,Object> disposeExcele( MultipartFile file ){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            String type="文件业务类型";
            Integer userId=1;
//            String fileName=    FileTool.saveFileSyncTmp(type,userId , file);//上传保存文件
//            String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue() + "/temp/" + type + "/" + userId + "/";
            String fileName="1.xls";
            String path ="E://";
            //读取表格文件中的数据
            List<Map<String,Object>> list0= readExcelForList(path, fileName, 1, 0, 0);//借款人
            List<Map<String,Object>> list1=  readExcelForList(path, fileName, 1, 0, 1);//抵押物
            List<Map<String,Object>> list2=  readExcelForList(path, fileName, 1, 0, 2);//借据
            List<Map<String,Object>> list3= readExcelForList(path, fileName, 1, 0, 3);//联系人
            List<Map<String,Object>> error0=new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> error1=new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> error2=new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> error3=new ArrayList<Map<String,Object>>();
            List<LenderDTO> lenderDTOs=new ArrayList<LenderDTO>();//借款人基础信息
            List<PawnDTO> pawnDTOs=new ArrayList<PawnDTO>();//抵押物信息
            List<IouDTO> iouDTOs=new ArrayList<IouDTO>();//借据信息
            List<ContactDTO> contactDTOs=new ArrayList<ContactDTO>();//联系人信息
            for(Map<String,Object> map0:list0){
                if(transStringToInteger(transMapToString(map0,"var0"))==null){
                    continue;
                }
                LenderDTO lenderDTO=new LenderDTO();
                lenderDTO.setId(transStringToInteger(transMapToString(map0, "var0")));
                lenderDTO.setEvaluateExcellent(transMapToString(map0, "var5"));
                lenderDTO.setEvaluateLevel(transMapToString(map0, "var6"));
                lenderDTO.setDisposeMode(transMapToString(map0, "var7"));
                lenderDTO.setTags(transMapToString(map0, "var8"));
                lenderDTO.setGuaranteeType(transMapToString(map0, "var9").equals("公司担保") == true ? "公司" : "个人");
                lenderDTO.setGuaranteeMode(transMapToString(map0, "var10"));
                lenderDTO.setGuaranteeSource(lenderDTO.getGuaranteeMode().equals("") == true ? "" : lenderDTO.getGuaranteeMode().equals("抵押") == true ? transMapToString(map0, "var11") : transMapToString(map0, "var12"));
                lenderDTO.setIsGuaranteeConnection(transMapToString(map0, "var13").equals("是") == true ? 1 : 0);
                lenderDTO.setGuarenteeEconomic(transMapToString(map0, "var14"));
                lenderDTO.setIsWorth(transMapToString(map0, "var15").equals("否") == true ? 0 : transMapToString(map0, "var15").equals("能") == true ? 1 : 2);
                lenderDTO.setIsLawsuit(transMapToString(map0, "var16").equals("未诉讼") == true ? 0 : transMapToString(map0, "var16").equals("已诉讼") == true ? 1 : 2);
                lenderDTO.setIsDecision(transMapToString(map0, "var17").equals("未判决") == true ? 0 : transMapToString(map0, "var17").equals("已判决") == true ? 1 : 2);
                lenderDTO.setRealUrgeTime(transStringToInteger(transMapToString(map0, "var18")));
                lenderDTO.setPhoneUrgeTime(transStringToInteger(transMapToString(map0, "var19")));
                lenderDTO.setEntrustUrgeTime(transStringToInteger(transMapToString(map0, "var20")));
                lenderDTO.setCanContact(transMapToString(map0, "var21").equals("是")==true?1:0);
                lenderDTO.setCanPay(transMapToString(map0, "var22").equals("是")==true?1:0);
                lenderDTOs.add(lenderDTO);
            }
            for(Map<String,Object> map1:list1){
                if(transStringToInteger(transMapToString(map1,"var0"))==null){
                    continue;
                }
                PawnDTO pawnDTO=new PawnDTO();
                pawnDTO.setId(transStringToInteger(transMapToString(map1,"var0")));
                pawnDTO.setAmount(transStringToDouble(transMapToString(map1,"var3")));
                pawnDTO.setType(transMapToString(map1,"var4"));
                pawnDTO.setSize(transMapToString(map1,"var5"));
                pawnDTO.setPawnRate(transStringToDouble(transMapToString(map1,"var6").replace("%","")));
                pawnDTO.setAddress(transMapToString(map1,"var7"));
                pawnDTO.setDisposeStatus(transMapToString(map1,"var8"));
                pawnDTO.setWorth(transStringToDouble(transMapToString(map1,"var9")));
                String[] strs=transMapToString(map1,"var10").split("、");
                List<Integer> iouids=new ArrayList<Integer>();
                for(String iou:strs){
                    iouids.add(transStringToInteger(iou));
                }
                pawnDTO.setIouIds(iouids);
                pawnDTO.setMemo(transMapToString(map1,"var11"));
                pawnDTOs.add(pawnDTO);
            }
            for(Map<String,Object> map2:list2){
                IouDTO iouDTO=new IouDTO();
                if(transStringToInteger(transMapToString(map2,"var0"))==null){
                    continue;
                }
                iouDTO.setId(transStringToInteger(transMapToString(map2,"var0")));
                iouDTO.setIouCode(transMapToString(map2,"var2"));
                iouDTO.setType(transMapToString(map2,"var3"));
                iouDTO.setAgency(transMapToString(map2,"var4"));
                iouDTO.setLoanTime(DateFormatTool.parse(transMapToString(map2,"var5"),DateFormatTool.DATE_FORMAT_10_REG2));
                iouDTO.setLoanAttime(DateFormatTool.parse(transMapToString(map2, "var6"), DateFormatTool.DATE_FORMAT_10_REG2));
                iouDTO.setAmount(transStringToDouble(transMapToString(map2,"var7")));
                iouDTO.setPactRate(transStringToDouble(transMapToString(map2,"var8").replace("%","")));
                iouDTO.setOuttimeMultiple(transStringToDouble(transMapToString(map2,"var9")));
                iouDTO.setAppropriationMultiple(transMapToString(map2, "var10"));
                iouDTO.setAccrualRepay(transStringToDouble(transMapToString(map2,"var11")));
                iouDTO.setLoanRepay(transStringToDouble(transMapToString(map2,"var12")));
                iouDTO.setLevelType(transMapToString(map2,"var13"));
                iouDTO.setOutDays(transStringToInteger(transMapToString(map2,"var14")));
                iouDTO.setLessCorpus(transStringToDouble(transMapToString(map2,"var15")));
                iouDTO.setAccrualArrears(transStringToDouble(transMapToString(map2,"var16")));
                iouDTO.setPenalty(transStringToDouble(transMapToString(map2,"var17")));
                iouDTO.setArrears(transStringToDouble(transMapToString(map2,"var18")));
                iouDTO.setEndAt(DateFormatTool.parse(transMapToString(map2, "var19"), DateFormatTool.DATE_FORMAT_10_REG2));
                iouDTO.setWorth(transStringToDouble(transMapToString(map2,"var20")));
                iouDTO.setAdvanceCorpus(transStringToDouble(transMapToString(map2,"var21")));
                String[] strs=transMapToString(map2,"var10").split("、");
                List<String> pawnIds=new ArrayList<String>();
                for(String paw:strs){
                    pawnIds.add(paw);
                }
                iouDTO.setPawnIds(pawnIds);
            }
            for(Map<String,Object> map3:list3){
                if(transStringToInteger(transMapToString(map3,"var0"))==null){
                    continue;
                }
                ContactDTO contactDTO=new ContactDTO();
                contactDTO.setId(transStringToInteger(transMapToString(map3,"var0")));
                contactDTO.setType(ContactTypeEnum.getContactTypeEnumValue(transMapToString(map3, "var1")));
                contactDTO.setName(transMapToString(map3,"var2"));
                contactDTO.setIdcard(transMapToString(map3,"var3"));
                contactDTO.setMobile(transMapToString(map3,"var4"));
                contactDTO.setHomeTel(transMapToString(map3,"var5"));
                contactDTO.setOfficeTel(transMapToString(map3,"var6"));
                contactDTO.setEmail(transMapToString(map3,"var7"));
                contactDTO.setCompany(transMapToString(map3,"var11"));
                contactDTO.setMemo(transMapToString(map3,"var12"));
                contactDTOs.add(contactDTO);
            }
            map.put("lenderDTOs",lenderDTOs);
            map.put("pawnDTOs",pawnDTOs);
            map.put("iouDTOs",iouDTOs);
            map.put("contactDTOs",contactDTOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
/**从map中获取字符*/
    public static String transMapToString(Map<String,Object> map,String key){
        return map.get(key)==null?"":map.get(key).toString().trim();
    }
    /**字符转为Integer*/
    public static Integer transStringToInteger(String str){
        if(str!=""&&str.matches("[0-9]*")){
            return Integer.parseInt(str);
        }else{
            return null;
        }
    }
    /**字符转为double*/
    public static Double transStringToDouble(String str){
        if(str!=""&&str.matches("[0-9]*([.][0-9]*)?")){
            return Double.parseDouble(str);
        }else{
            return null;
        }
    }
    /**读取表格文件*/
    public static List<Map<String,Object>> readExcelForList(String filepath, String filename, int startrow, int startcol, int sheetnum) {
        List<Map<String,Object>> varList = new ArrayList<Map<String,Object>>();
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
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date d = cell.getDateCellValue();
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                    ret = formater.format(d);
                }else{
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


}
