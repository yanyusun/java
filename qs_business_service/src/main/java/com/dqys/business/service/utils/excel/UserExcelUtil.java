package com.dqys.business.service.utils.excel;

import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.business.service.dto.user.UserFileDTO;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/7/1.
 * <p/>
 * 成员信息导入模块
 */
public class UserExcelUtil {

    /**
     * 用户信息表格上传
     *
     * @return
     */
    public static Map<String, Object> upLoadUserExcel(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        String type = SysPropertyTypeEnum.FILE_BUSINESS_TYPE.getValue().toString();
        Integer userId = 0;
        try {
            String fileName = FileTool.saveFileSyncTmp(type, userId, file);//上传保存临时文件
            String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue()
                    + "/temp/" + type + "/" + userId + "/";

//            String path="E://";
//            String fileName="1.xlsx";

            List<Map<String, Object>> list = ExcelTool.readExcelForList(path, fileName, 1, 0, 0);//借款人
            //判断文件的字段格式
            List<ExcelMessage> error = new ArrayList<ExcelMessage>();//错误信息

            if (!checkUserExcel(list, error)) {
                //文件的写出成表格文件
//                fileName = "error" + DateFormatTool.format(DateFormatTool.DATE_FORMAT_10_REG1)
//                        + RandomStringUtils.randomNumeric(4) + ".xls";
//                String[] str = {"序号", "表名称", "位置", "字段名称", "问题内容"};
//                ExcelTool.exportExcel(error, str, path, fileName);
//                map.put("filePath", path + fileName);
                map.put("result", "error");
                map.put("data", error);
            } else {
                //文件格式都正确的就处理表格
                map.put("result", "ok");
                List<UserFileDTO> userFileDTOs = new ArrayList<UserFileDTO>();
                disposeUserExcele(list, userFileDTOs);
                map.put("userFileDTOs", userFileDTOs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 处理用户信息表格
     *
     * @param list
     * @param userFileDTOs
     * @return map(包含key：userFileDTOs)
     */
    private static void disposeUserExcele(List<Map<String, Object>> list, List<UserFileDTO> userFileDTOs) {
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> map0 = list.get(i);
            if (transStringToInteger(transMapToString(map0, "var0")) == null) {
                continue;
            }
            UserFileDTO userFileDTO = new UserFileDTO();
            userFileDTO.setRealName(transMapToString(map0, "var1"));
            userFileDTO.setUserName(transMapToString(map0, "var2"));
            userFileDTO.setSex(transMapToString(map0, "var3").equals("男") == true ? 1 : 0);
            userFileDTO.setAccount(transMapToString(map0, "var4"));
            userFileDTO.setWechat(transMapToString(map0, "var5"));
            userFileDTO.setQq(transMapToString(map0, "var6"));
            userFileDTO.setOfficeTel(transMapToString(map0, "var7"));
            userFileDTO.setMobile(transMapToString(map0, "var8"));
            userFileDTO.setEmail(transMapToString(map0, "var9"));
            userFileDTO.setApartment(transMapToString(map0, "var10"));
            userFileDTO.setOccupation(transMapToString(map0, "var11"));
            userFileDTO.setDuty(transMapToString(map0, "var12"));
            userFileDTO.setDutyMark(transMapToString(map0, "var13"));
            userFileDTO.setDutyArea(transMapToString(map0, "var14"));
            userFileDTO.setRole(transMapToString(map0, "var15").equals("管理员") == true ? 1 : transMapToString(map0, "var15").equals("管理者") == true ? 2 : 3);
            userFileDTO.setYear(transStringToInteger(transMapToString(map0, "var16")));
            if (!transMapToString(map0, "var17").equals("")) {
                userFileDTO.setJoinAt(DateFormatTool.parse(transMapToString(map0, "var17"), DateFormatTool.DATE_FORMAT_10_REG2));
            }
            userFileDTO.setRemark(transMapToString(map0, "var19"));
            userFileDTOs.add(userFileDTO);
        }
    }

    /**
     * 验证用户信息表格信息
     *
     * @param list
     * @param error
     * @return
     */
    private static boolean checkUserExcel(List<Map<String, Object>> list, List<ExcelMessage> error) {
        String[] str = {"序号", "*姓名", "*昵称", "*性别", "*自定义账号", "*微信号", "QQ号", "办公电话", "*手机号", "*工作邮箱", "*部门", "*职位名称",
                "*职责名称", "职责描述", "*职责区域", "*系统角色", "从业年限(年）", "入职时间", "历史业绩（总数量）", "备注"};
        String msg = "";
        msg += templateFormat(str, list.get(0), "用户信息表问题");
        boolean flag = true;
        if (msg.equals("")) {
            //判断每个表格的数据类型
            checkUser(error, list);
            if (error.size() > 0) {
                flag = false;
            }
        } else {
            //导出错误信息到文件
//            String[] string = {"1", "", "", "", msg};
            ExcelMessage excelMessage=new ExcelMessage(1, "", "", "", msg);
            error.add(excelMessage);//模板有问题
            flag = false;
        }
        return flag;
    }

    private static void checkUser(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "用户信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var0"), "格式错误");
            }
            if (transMapToString(l, "var1").equals("")) {//*姓名
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "不能为空");
                ;
            }
            if (transMapToString(l, "var2").equals("")) {//*昵称
                placeByExcel(error, name, i, 2, transMapToString(map, "var2"), "不能为空");
                ;
            }
            if (transMapToString(l, "var3").equals("")) {//*性别
                placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "不能为空");
                ;
            }
            if (transMapToString(l, "var4").equals("")) {//*自定义账号
                placeByExcel(error, name, i, 4, transMapToString(map, "var4"), "不能为空");
                ;
            }
            if (transMapToString(l, "var5").equals("")) {//*微信号
                placeByExcel(error, name, i, 5, transMapToString(map, "var5"), "不能为空");
                ;
            }
            if (!transMapToString(l, "var6").equals("") && !FormatValidateTool.isNumeric(transMapToString(l, "var6"))) {//QQ号
                placeByExcel(error, name, i, 6, transMapToString(map, "var6"), "格式错误");
                ;
            }
            if (!transMapToString(l, "var7").equals("") && !FormatValidateTool.checkPhone(transMapToString(l, "var7"))) {//办公电话
                placeByExcel(error, name, i, 7, transMapToString(map, "var7"), "格式错误");
                ;
            }
            if (!FormatValidateTool.checkMobile(transMapToString(l, "var8"))) {//*手机号
                placeByExcel(error, name, i, 8, transMapToString(map, "var8"), "不能为空");
                ;
            }
            if (!FormatValidateTool.checkEmail(transMapToString(l, "var9"))) {//*工作邮箱
                placeByExcel(error, name, i, 9, transMapToString(map, "var9"), "格式错误");
                ;
            }
            if (transMapToString(l, "var10").equals("")) {//*部门
                placeByExcel(error, name, i, 10, transMapToString(map, "var10"), "不能为空");
                ;
            }
            if (transMapToString(l, "var11").equals("")) {//*职位名称
                placeByExcel(error, name, i, 11, transMapToString(map, "var11"), "不能为空");
                ;
            }
            if (transMapToString(l, "var12").equals("")) {//*职责名称
                placeByExcel(error, name, i, 12, transMapToString(map, "var12"), "不能为空");
                ;
            }
            if (transMapToString(l, "var14").equals("")) {//*职责区域
                placeByExcel(error, name, i, 14, transMapToString(map, "var14"), "不能为空");
                ;
            }
            if (transMapToString(l, "var15").equals("")) {//*系统角色
                placeByExcel(error, name, i, 15, transMapToString(map, "var15"), "不能为空");
                ;
            }
            if (!transMapToString(l, "var16").equals("") && !FormatValidateTool.isNumeric(transMapToString(l, "var16"))) {//从业年限(年）
                placeByExcel(error, name, i, 16, transMapToString(map, "var16"), "格式错误");
                ;
            }
            if (!transMapToString(l, "var17").equals("") && !FormatValidateTool.isDate(transMapToString(l, "var17"))) {//入职时间
                placeByExcel(error, name, i, 17, transMapToString(map, "var16"), "格式错误");
                ;
            }
        }
    }

    /**
     * @param error      错误信息集合
     * @param name       　表名称
     * @param row        行数
     * @param col        列数
     * @param fieldsName 字段名称
     * @param msg        错误内容
     */
    private static void placeByExcel(List<ExcelMessage> error, String name, Integer row, Integer col, String fieldsName, String msg) {
//        String[] str = {(error.size() + 1) + "", name, (row) + "行" + (col + 1) + "列", fieldsName, msg};
        ExcelMessage excelMessage = new ExcelMessage((error.size() + 1), name, (row) + "行" + (col + 1) + "列", fieldsName, msg);
        error.add(excelMessage);
    }

    /**
     * 模板格式判断
     *
     * @param strs 要求模板字段属性
     * @param map  模板第一行对应的属性名称
     * @param str  说明
     */
    public static String templateFormat(String[] strs, Map<String, Object> map, String str) {
        String msg = "";
        for (int i = 0; i < strs.length; i++) {
            if (!transMapToString(map, "var" + i).equals(strs[i])) {
                msg = str;
                break;
            }
        }
        return msg;
    }

    /**
     * 从map中获取字符
     */
    public static String transMapToString(Map<String, Object> map, String key) {
        return map.get(key) == null ? "" : map.get(key).toString().trim();
    }

    /**
     * 字符转为Integer
     */
    public static Integer transStringToInteger(String str) {
        if (str != "" && str.matches("[0-9]*")) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }

    /**
     * 字符转为double
     */
    public static Double transStringToDouble(String str) {
        if (str != "" && str.matches("[0-9]*([.][0-9]*)?")) {
            return Double.parseDouble(str);
        } else {
            return null;
        }
    }


}
