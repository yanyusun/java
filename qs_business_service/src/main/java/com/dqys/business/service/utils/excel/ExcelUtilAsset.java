package com.dqys.business.service.utils.excel;

import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkefeng on 2016/6/27.
 */
public class ExcelUtilAsset {
    /**
     * 资产包表格文件的上传
     */
    public static Map<String, Object> uploadExcel(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        String type = SysPropertyTypeEnum.FILE_BUSINESS_TYPE.getValue().toString();
        Integer userId = 0;
        try {
            // 临时保存上传文件
            String fileName = FileTool.saveFileSyncTmp(type, userId, file);//上传保存文件
            String path = SysPropertyTool.getProperty(
                    SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue()
                    + "/temp/" + type + "/" + userId + "/";
            List<Map<String, Object>> list0 = ExcelTool.readExcelForList(path, fileName, 0, 0, 0);//借款人
            List<Map<String, Object>> list1 = ExcelTool.readExcelForList(path, fileName, 0, 0, 1);//抵押物
            List<Map<String, Object>> list2 = ExcelTool.readExcelForList(path, fileName, 0, 0, 2);//借据
            List<Map<String, Object>> list3 = ExcelTool.readExcelForList(path, fileName, 0, 0, 3);//联系人
            //判断文件的字段格式
            List<ExcelMessage> error = new ArrayList<ExcelMessage>();//错误信息
            if (!checkExcel(list0, list1, list2, list3, error)) {
                //文件的写出成表格文件
                map.put("result", "error");
                map.put("data", error);
            } else {
                //文件格式都正确的就处理表格
                map.put("result", "ok");
                map = disposeExcele(list0, list1, list2, list3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 资产包表格文件格式的判断
     *
     * @param list0 借款人基础信息
     * @param list1 抵押物信息
     * @param list2 借据信息
     * @param list3 联系人信息
     * @return
     */
    private static boolean checkExcel(List<Map<String, Object>> list0, List<Map<String, Object>> list1, List<Map<String, Object>> list2, List<Map<String, Object>> list3, List<ExcelMessage> error) {
        String[] str0 = {"序号", "*借款人", "*类型", "*来源", "*所属资产包", "*评优", "*评级", "个性处置方式", "标签", "*担保方式", "*公司担保", "*抵押",
                "*质押", "*担保人是否能联系", "*担保人经济状况", "*抵押物估价能否覆盖债务", "*诉讼与否", "*判决与否", "*实地催收次数",
                "*电话催收次数", "*委托催收次数", "*债务方是否能正常联系", "*债务方是否有能力偿还"};
        String[] str1 = {"序号", "*关系", "*所属原始借据（号）", "*贷款金额", "*抵押物类型", "*抵押物面积", "*抵押率", "*抵押物地址",
                "*处置状态", "*抵押物估值（元）", "*抵押物中的借据", "备注"};
        String[] str2 = {"序号", "*关系", "*原始借据（号）", "*品种", "*经办机构", "*放款日期", "*到期日期", "*放款金额", "*合同利率", "*逾期利率倍数", "*挪用利率倍数",
                "*已还利息金额", "*已还贷款金额", "*五级分类", "*逾期天数", "*剩余本金", "*拖欠利息", "*罚息", "*欠款合计", "*欠款合计截止日期",
                "*抵押物银行估值", "*提前偿还本金", "*借据中的抵押物"};
        String[] str3 = {"序号", "*相关人种类", "*姓名", "*身份证号", "*手机号码", "住宅电话", "办公电话", "电子邮箱", "其它联系方式01", "其它联系方式02",
                "其它联系方式03", "所在机构", "备注"};
        // 表头校验
        templateFormat(str0, list0.get(0), "借款人", "表头信息出错", error);
        templateFormat(str1, list1.get(0), "抵押物", "表头信息出错", error);
        templateFormat(str2, list2.get(0), "借据", "表头信息出错", error);
        templateFormat(str3, list3.get(0), "相关联系人", "表头信息出错", error);

        boolean flag = true;
        if (error.size() == 0) {
            //判断每个表格内的数据类型
            checkLender(error, list0);
            checkPawn(error, list1);
            checkIou(error, list2);
            checkContact(error, list3);
            if (error.size() > 0) {
                flag = false;
            }
        } else {
            //表头校验不过关
            flag = false;
        }
        return flag;
    }

    private static void checkLender(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "借款人信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var0")) == null) {
                continue;
            }
            // 序号
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var0"), "格式错误");
            }
            if (transMapToString(l, "var1").equals("")) {//*借款人
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "不能为空");
            }
            if (transMapToString(l, "var2").equals("")) {//*类型
                placeByExcel(error, name, i, 2, transMapToString(map, "var2"), "不能为空");
            }
            if (transMapToString(l, "var3").equals("")) {//*来源
                placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "不能为空");
            }
            if (transMapToString(l, "var4").equals("")) {//*所属资产包
                placeByExcel(error, name, i, 4, transMapToString(map, "var4"), "不能为空");
            }
            if (transMapToString(l, "var5").equals("")) {//*评优
                placeByExcel(error, name, i, 5, transMapToString(map, "var5"), "不能为空");
            }
            if (transMapToString(l, "var6").equals("")) {//*评级
                placeByExcel(error, name, i, 6, transMapToString(map, "var6"), "不能为空");
            }
            if (!transMapToString(l, "var7").equals("") && transMapToString(l, "var7").indexOf("，") > 0) {//个性处置方式
                placeByExcel(error, name, i, 7, transMapToString(map, "var7"), "不是英文逗号");
            }
            if (!transMapToString(l, "var8").equals("") && transMapToString(l, "var8").indexOf("，") > 0) {//标签
                placeByExcel(error, name, i, 8, transMapToString(map, "var8"), "不是英文逗号");
            }
            if (transMapToString(l, "var9").equals("")) {//*担保方式
                placeByExcel(error, name, i, 9, transMapToString(map, "var9"), "不能为空");
            }
            if (!transMapToString(l, "var9").equals("") && transMapToString(l, "var10").equals("")) {//*公司担保
                placeByExcel(error, name, i, 10, transMapToString(map, "var10"), "不能为空");
            }
            if (transMapToString(l, "var9").equals("公司担保") && transMapToString(l, "var10").equals("抵押") && transMapToString(l, "var11").equals("")) {//*抵押
                placeByExcel(error, name, i, 11, transMapToString(map, "var1"), "不能为空");
            }
            if (transMapToString(l, "var9").equals("公司担保") && transMapToString(l, "var10").equals("质押") && transMapToString(l, "var12").equals("")) {//*质押
                placeByExcel(error, name, i, 12, transMapToString(map, "var12"), "不能为空");
            }
            if (!transMapToString(l, "var9").equals("公司担保")) {
                if (!transMapToString(l, "var10").equals("")) {
                    placeByExcel(error, name, i, 10, transMapToString(map, "var10"), "必须为空");
                }
                if (!transMapToString(l, "var11").equals("")) {
                    placeByExcel(error, name, i, 11, transMapToString(map, "var11"), "必须为空");
                }
                if (!transMapToString(l, "var12").equals("")) {
                    placeByExcel(error, name, i, 12, transMapToString(map, "var12"), "必须为空");
                }
            }
            if (transMapToString(l, "var13").equals("")) {//*担保人是否能联系
                placeByExcel(error, name, i, 13, transMapToString(map, "var13"), "不能为空");
            }
            if (transMapToString(l, "var14").equals("")) {//*担保人经济状况
                placeByExcel(error, name, i, 14, transMapToString(map, "var14"), "不能为空");
            }
            if (transMapToString(l, "var15").equals("")) {//*抵押物估价能否覆盖债务
                placeByExcel(error, name, i, 15, transMapToString(map, "var15"), "不能为空");
            }
            if (transMapToString(l, "var16").equals("")) {//*诉讼与否
                placeByExcel(error, name, i, 16, transMapToString(map, "var16"), "不能为空");
            }
            if (transMapToString(l, "var17").equals("")) {//*判决与否
                placeByExcel(error, name, i, 17, transMapToString(map, "var17"), "不能为空");
            }
            if (transMapToString(l, "var18").equals("")) {//*实地催收次数
                placeByExcel(error, name, i, 18, transMapToString(map, "var18"), "不能为空");
            }
            if (transMapToString(l, "var19").equals("")) {//*电话催收次数
                placeByExcel(error, name, i, 19, transMapToString(map, "var19"), "不能为空");
            }
            if (transMapToString(l, "var20").equals("")) {//*委托催收次数
                placeByExcel(error, name, i, 20, transMapToString(map, "var20"), "不能为空");
            }
            if (transMapToString(l, "var21").equals("")) {//*债务方是否能正常联系
                placeByExcel(error, name, i, 21, transMapToString(map, "var21"), "不能为空");
            }
            if (transMapToString(l, "var22").equals("")) {//*债务方是否有能力偿还
                placeByExcel(error, name, i, 22, transMapToString(map, "var22"), "不能为空");
                ;
            }
        }
    }

    private static void checkPawn(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "抵押物信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var0"), "格式错误");
            }
            if (transMapToString(l, "var1").equals("")) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "不能为空");
            }
            if (!transMapToString(l, "var1").equals("") && FormatValidateTool.isNumeric(transMapToString(l, "var1").split("-")[0])) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "格式错误");
            }
            if (transMapToString(l, "var2").equals("")) {//*所属原始借据（号）
                placeByExcel(error, name, i, 2, transMapToString(map, "var2"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var3"), null)) {//*贷款金额
                placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "格式错误");
            }
            if (transMapToString(l, "var4").equals("")) {//*抵押物类型
                placeByExcel(error, name, i, 4, transMapToString(map, "var4"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var5"), null)) {//*抵押物面积
                placeByExcel(error, name, i, 5, transMapToString(map, "var5"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var6").replace("%", ""), null)) {//*抵押率
                placeByExcel(error, name, i, 6, transMapToString(map, "var6"), "格式错误");
            }
            if (transMapToString(l, "var7").equals("")) {//*抵押物地址
                placeByExcel(error, name, i, 7, transMapToString(map, "var7"), "不能为空");
            }
            if (transMapToString(l, "var8").equals("")) {//*处置状态
                placeByExcel(error, name, i, 8, transMapToString(map, "var8"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var9"), null)) {//*抵押物估值（元）
                placeByExcel(error, name, i, 9, transMapToString(map, "var9"), "格式错误");
            }
            String[] strs = transMapToString(l, "var10").split(",");
            for (String str : strs) {
                if (!FormatValidateTool.isDecimals(str, null)) {//*抵押物中的借据
                    placeByExcel(error, name, i, 10, transMapToString(map, "var10"), "格式错误:不是英文逗号或不是数字");
                    break;
                }
            }

        }
    }

    private static void checkIou(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "借据信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var0"), "格式错误");
            }
            if (transMapToString(l, "var1").equals("")) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "不能为空");
            }
            if (!transMapToString(l, "var1").equals("") && FormatValidateTool.isNumeric(transMapToString(l, "var1").split("-")[0])) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "格式错误");
            }
            if (transMapToString(l, "var2").equals("")) {//*原始借据（号）
                placeByExcel(error, name, i, 2, transMapToString(map, "var2"), "不能为空");
            }
            if (transMapToString(l, "var3").equals("")) {//*品种
                placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "不能为空");
            }
            if (transMapToString(l, "var4").equals("")) {//*经办机构
                placeByExcel(error, name, i, 4, transMapToString(map, "var4"), "不能为空");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var5"))) {//*放款日期
                placeByExcel(error, name, i, 5, transMapToString(map, "var5"), "格式错误");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var6"))) {//*到期日期
                placeByExcel(error, name, i, 6, transMapToString(map, "var6"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var7"), null)) {//*放款金额
                placeByExcel(error, name, i, 7, transMapToString(map, "var7"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var8").replace("%", ""), null)) {//*合同利率
                placeByExcel(error, name, i, 8, transMapToString(map, "var8"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var9"), null)) {//*逾期利率倍数
                placeByExcel(error, name, i, 9, transMapToString(map, "var9"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var10"), null)) {//*挪用利率倍数
                placeByExcel(error, name, i, 10, transMapToString(map, "var10"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var11"), null)) {//*已还利息金额
                placeByExcel(error, name, i, 11, transMapToString(map, "var11"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var12"), null)) {//*已还贷款金额
                placeByExcel(error, name, i, 12, transMapToString(map, "var12"), "格式错误");
            }
            if (transMapToString(l, "var13").equals("")) {//*五级分类
                placeByExcel(error, name, i, 13, transMapToString(map, "var13"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var14"), 0)) {//*逾期天数
                placeByExcel(error, name, i, 14, transMapToString(map, "var14"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var15"), null)) {//*剩余本金
                placeByExcel(error, name, i, 15, transMapToString(map, "var15"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var16"), null)) {//*拖欠利息
                placeByExcel(error, name, i, 16, transMapToString(map, "var16"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var17"), null)) {//*罚息
                placeByExcel(error, name, i, 17, transMapToString(map, "var17"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var18"), null)) {//*欠款合计
                placeByExcel(error, name, i, 18, transMapToString(map, "var18"), "格式错误");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var19"))) {//*欠款合计截止日期
                placeByExcel(error, name, i, 19, transMapToString(map, "var19"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var20"), null)) {//*抵押物银行估值
                placeByExcel(error, name, i, 20, transMapToString(map, "var20"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var21"), null)) {//*提前偿还本金
                placeByExcel(error, name, i, 21, transMapToString(map, "var21"), "格式错误");
            }
            String[] strs = transMapToString(l, "var22").split(",");
            for (String str : strs) {
                if (!str.matches("[A-Z]")) {//*借据中的抵押物
                    placeByExcel(error, name, i, 22, transMapToString(map, "var22"), "格式错误:不是英文逗号或不是大写字母");
                    break;
                }
            }
        }
    }

    private static void checkContact(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "联系人信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var0"), "格式错误");
            }
            if (transMapToString(l, "var1").equals("")) {//相关种类
                placeByExcel(error, name, i, 1, transMapToString(map, "var1"), "不能为空");
            }
            if (transMapToString(l, "var2").equals("")) {//姓名验证
                placeByExcel(error, name, i, 2, transMapToString(map, "var2"), "不能为空");
            }
            try {
                if (!FormatValidateTool.idCardValidate(transMapToString(l, "var3")).equals("")) {//身份证验证
                    placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "格式错误");
                }
            } catch (ParseException e) {
                placeByExcel(error, name, i, 3, transMapToString(map, "var3"), "格式错误");
            }
            if (!FormatValidateTool.checkMobile(transMapToString(l, "var4"))) {//手机号验证
                placeByExcel(error, name, i, 4, transMapToString(map, "var4"), "格式错误");
            }
            if (!transMapToString(l, "var5").equals("") && !FormatValidateTool.checkPhone(transMapToString(l, "var5"))) {//住宅电话
                placeByExcel(error, name, i, 5, transMapToString(map, "var5"), "格式错误");
            }
            if (!transMapToString(l, "var6").equals("") && !FormatValidateTool.checkPhone(transMapToString(l, "var6"))) {//办公电话
                placeByExcel(error, name, i, 6, transMapToString(map, "var6"), "格式错误");
            }
            if (!transMapToString(l, "var7").equals("") && !FormatValidateTool.checkEmail(transMapToString(l, "var7"))) {//电子邮箱
                placeByExcel(error, name, i, 7, transMapToString(map, "var7"), "格式错误");
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
    private static void placeByExcel(List<ExcelMessage> error, String name,
                                     Integer row, Integer col, String fieldsName, String msg) {
//        String[] str = {(error.size() + 1) + "", name, (row) + "行" + (col + 1) + "列", fieldsName, msg};
        ExcelMessage excelMessage = new ExcelMessage((error.size() + 1), name, (row) + "行" + (col + 1) + "列", fieldsName, msg);
        error.add(excelMessage);
    }

    /**
     * 模板格式判断
     *
     * @param arr    要求模板字段属性
     * @param map    模板第一行对应的属性名称
     * @param name   表名
     * @param errMsg 错误信息
     * @param error  错误收集对象
     */
    public static void templateFormat(String[] arr, Map<String, Object> map, String name,
                                      String errMsg, List<ExcelMessage> error) {
        for (int i = 0; i < arr.length; i++) {
            if (!transMapToString(map, "var" + i).equals(arr[i])) {
                ExcelMessage excelMessage = new ExcelMessage(1, name, String.valueOf(i + 1), arr[i], errMsg);
                error.add(excelMessage);
            }
        }
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
     * 资产包表格文件的处理（转换成对象形式）
     *
     * @param list0 借款人基础信息
     * @param list1 抵押物信息
     * @param list2 借据信息
     * @param list3 联系人信息
     * @return Map<String,Object> 包含（
     * map.put("lenderDTOs",lenderDTOs);
     * map.put("pawnDTOs",pawnDTOs);
     * map.put("iouDTOs", iouDTOs);
     * map.put("contactDTOs", contactDTOs);）
     */
    public static Map<String, Object> disposeExcele(List<Map<String, Object>> list0, List<Map<String, Object>> list1, List<Map<String, Object>> list2, List<Map<String, Object>> list3) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<LenderDTO> lenderDTOs = new ArrayList<LenderDTO>();//借款人基础信息
            List<PawnDTO> pawnDTOs = new ArrayList<PawnDTO>();//抵押物信息
            List<IouDTO> iouDTOs = new ArrayList<IouDTO>();//借据信息
            List<ContactDTO> contactDTOs = new ArrayList<ContactDTO>();//联系人信息
            disposeLender(list0, lenderDTOs);
            disposePawn(list1, pawnDTOs);
            disposeIou(list2, iouDTOs);
            disposeContact(list3, contactDTOs);
            map.put("lenderDTOs", lenderDTOs);
            map.put("pawnDTOs", pawnDTOs);
            map.put("iouDTOs", iouDTOs);
            map.put("contactDTOs", contactDTOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void disposeContact(List<Map<String, Object>> list3, List<ContactDTO> contactDTOs) {
        for (int i = 1; i < list3.size(); i++) {
            Map<String, Object> map3 = list3.get(i);
            if (transStringToInteger(transMapToString(map3, "var0")) == null) {
                continue;
            }
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(transStringToInteger(transMapToString(map3, "var0")));
            contactDTO.setType(ContactTypeEnum.getContactTypeEnumValue(transMapToString(map3, "var1")));
            contactDTO.setName(transMapToString(map3, "var2"));
            contactDTO.setIdcard(transMapToString(map3, "var3"));
            contactDTO.setMobile(transMapToString(map3, "var4"));
            contactDTO.setHomeTel(transMapToString(map3, "var5"));
            contactDTO.setOfficeTel(transMapToString(map3, "var6"));
            contactDTO.setEmail(transMapToString(map3, "var7"));
            contactDTO.setCompany(transMapToString(map3, "var11"));
            contactDTO.setMemo(transMapToString(map3, "var12"));
            contactDTOs.add(contactDTO);
        }
    }

    private static void disposeIou(List<Map<String, Object>> list2, List<IouDTO> iouDTOs) {
        for (int i = 1; i < list2.size(); i++) {
            Map<String, Object> map2 = list2.get(i);
            if (transStringToInteger(transMapToString(map2, "var0")) == null) {
                continue;
            }
            IouDTO iouDTO = new IouDTO();
            iouDTO.setId(transStringToInteger(transMapToString(map2, "var1").split("-")[0]));
            iouDTO.setIouCode(transMapToString(map2, "var2"));
            iouDTO.setType(transMapToString(map2, "var3"));
            iouDTO.setAgency(transMapToString(map2, "var4"));
            iouDTO.setLoanTime(DateFormatTool.parse(transMapToString(map2, "var5"), DateFormatTool.DATE_FORMAT_10_REG2));
            iouDTO.setLoanAtTime(DateFormatTool.parse(transMapToString(map2, "var6"), DateFormatTool.DATE_FORMAT_10_REG2));
            iouDTO.setAmount(transStringToDouble(transMapToString(map2, "var7")));
            iouDTO.setPactRate(transStringToDouble(transMapToString(map2, "var8").replace("%", "")));
            iouDTO.setOuttimeMultiple(transStringToDouble(transMapToString(map2, "var9")));
            iouDTO.setAppropriationMultiple(transMapToString(map2, "var10"));
            iouDTO.setAccrualRepay(transStringToDouble(transMapToString(map2, "var11")));
            iouDTO.setLoanRepay(transStringToDouble(transMapToString(map2, "var12")));
            iouDTO.setLevelType(transMapToString(map2, "var13"));
            iouDTO.setOutDays(transStringToInteger(transMapToString(map2, "var14")));
            iouDTO.setLessCorpus(transStringToDouble(transMapToString(map2, "var15")));
            iouDTO.setAccrualArrears(transStringToDouble(transMapToString(map2, "var16")));
            iouDTO.setPenalty(transStringToDouble(transMapToString(map2, "var17")));
            iouDTO.setArrears(transStringToDouble(transMapToString(map2, "var18")));
            iouDTO.setEndAt(DateFormatTool.parse(transMapToString(map2, "var19"), DateFormatTool.DATE_FORMAT_10_REG2));
            iouDTO.setWorth(transStringToDouble(transMapToString(map2, "var20")));
            iouDTO.setAdvanceCorpus(transStringToDouble(transMapToString(map2, "var21")));
            iouDTO.setPawnIds(transMapToString(map2, "var22"));
            iouDTOs.add(iouDTO);
        }
    }

    private static void disposePawn(List<Map<String, Object>> list1, List<PawnDTO> pawnDTOs) {
        for (int i = 1; i < list1.size(); i++) {
            Map<String, Object> map1 = list1.get(i);
            if (transStringToInteger(transMapToString(map1, "var0")) == null) {
                continue;
            }
            PawnDTO pawnDTO = new PawnDTO();
            pawnDTO.setId(transStringToInteger(transMapToString(map1, "var1").split("-")[0]));
            pawnDTO.setAmount(transStringToDouble(transMapToString(map1, "var3")));
            pawnDTO.setType(transMapToString(map1, "var4"));
            pawnDTO.setSize(transMapToString(map1, "var5"));
            pawnDTO.setPawnRate(transStringToDouble(transMapToString(map1, "var6").replace("%", "")));
            pawnDTO.setAddress(transMapToString(map1, "var7"));
            pawnDTO.setDisposeStatus(transMapToString(map1, "var8"));
            pawnDTO.setWorth(transStringToDouble(transMapToString(map1, "var9")));
            pawnDTO.setIouIds(transMapToString(map1, "var10"));
            pawnDTO.setMemo(transMapToString(map1, "var11"));
            pawnDTOs.add(pawnDTO);
        }
    }

    private static void disposeLender(List<Map<String, Object>> list0, List<LenderDTO> lenderDTOs) {
        for (int i = 1; i < list0.size(); i++) {
            Map<String, Object> map0 = list0.get(i);
            if (transStringToInteger(transMapToString(map0, "var0")) == null) {
                continue;
            }
            LenderDTO lenderDTO = new LenderDTO();
            lenderDTO.setId(transStringToInteger(transMapToString(map0, "var0")));
            lenderDTO.setEvaluateExcellent(transMapToString(map0, "var5"));
            lenderDTO.setEvaluateLevel(transMapToString(map0, "var6"));
            lenderDTO.setDisposeMode(transMapToString(map0, "var7"));
            lenderDTO.setTags(transMapToString(map0, "var8"));
            lenderDTO.setGuaranteeType(transMapToString(map0, "var9").equals("公司担保") == true ? "公司" : "个人");
            lenderDTO.setGuaranteeMode(transMapToString(map0, "var10"));
            lenderDTO.setGuaranteeSource(lenderDTO.getGuaranteeMode().equals("") == true ? "" : lenderDTO.getGuaranteeMode().equals("抵押") == true ? transMapToString(map0, "var11") : transMapToString(map0, "var12"));
            lenderDTO.setIsGuaranteeConnection(transMapToString(map0, "var13").equals("是") == true ? 1 : 0);
            lenderDTO.setGuaranteeEconomic(transMapToString(map0, "var14"));
            lenderDTO.setIsWorth(transMapToString(map0, "var15").equals("否") == true ? 0 : transMapToString(map0, "var15").equals("能") == true ? 1 : 2);
            lenderDTO.setIsLawsuit(transMapToString(map0, "var16").equals("未诉讼") == true ? 0 : transMapToString(map0, "var16").equals("已诉讼") == true ? 1 : 2);
            lenderDTO.setIsDecision(transMapToString(map0, "var17").equals("未判决") == true ? 0 : transMapToString(map0, "var17").equals("已判决") == true ? 1 : 2);
            lenderDTO.setRealUrgeTime(transStringToInteger(transMapToString(map0, "var18")));
            lenderDTO.setPhoneUrgeTime(transStringToInteger(transMapToString(map0, "var19")));
            lenderDTO.setEntrustUrgeTime(transStringToInteger(transMapToString(map0, "var20")));
            lenderDTO.setCanContact(transMapToString(map0, "var21").equals("是") == true ? 1 : 0);
            lenderDTO.setCanPay(transMapToString(map0, "var22").equals("是") == true ? 1 : 0);
            lenderDTOs.add(lenderDTO);
        }
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
