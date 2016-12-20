package com.dqys.business.service.utils.excel;

import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.*;

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
    public static Map<String, Object> uploadExcel(String fileName) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] name = fileName.split("_");
        if (name.length != 3) {
            map.put("result", "error");
            map.put("data", "文件解析错误");
            return map;
        }
        String type = name[0];
        Integer userId = Integer.valueOf(name[1]);
        try {
            // 临时文件地址
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
                map = disposeExcele(list0, list1, list2, list3);
                map.put("result", "ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "exception");
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
        String[] str0 = {"序号", "*借款人", "*类型", "*委托起止日期", "*来源", "*评优", "*评级", "个性处置方式", "标签", "*担保方式", "*公司担保", "*抵押",
                "*质押", "*担保人是否能联系", "*担保人经济状况", "*抵押物估价能否覆盖债务", "*诉讼与否", "*判决与否", "*实地催收次数",
                "*电话催收次数", "*委托催收次数", "*债务方是否能正常联系", "*债务方是否有能力偿还"};
        String[] str1 = {"序号", "*关系", "*所属原始借据（号）", "*贷款金额", "*抵押物类型", "*抵押物面积", "*抵押率", "*地址省", "*地址市", "*地址区", "*具体地址",
                "*处置状态", "*抵押物估值（元）", "*抵押物中的借据", "备注"};
        String[] str2 = {"序号", "*关系", "*原始借据（号）", "*贷款类型", "*经办机构", "*放款日期", "*到期日期", "*放款金额", "*合同利率", "*逾期利率倍数", "*挪用利率倍数",
                "*已还利息金额", "*已还贷款金额", "*五级分类", "*逾期天数", "*剩余本金", "*拖欠利息", "*罚息", "*欠款合计", "*欠款合计截止日期",
                "*抵押物银行估值", "*提前偿还本金", "*借据中的抵押物"};
        String[] str3 = {"序号", "*相关人种类", "*姓名", "*身份证号", "*手机号码", "住宅电话", "办公电话", "电子邮箱", "其它联系方式01", "其它联系方式02",
                "其它联系方式03", "地址省", "地址市", "地址区", "具体地址", "所在机构", "备注"};
        // 表头校验
        templateFormat(str0, list0.get(0), "借款人", "字段名称不匹配", error);
        templateFormat(str1, list1.get(0), "抵押物", "字段名称不匹配", error);
        templateFormat(str2, list2.get(0), "借据", "字段名称不匹配", error);
        templateFormat(str3, list3.get(0), "相关联系人", "字段名称不匹配", error);

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
            if (transStringToInteger(transMapToString(l, "var" + "0")) == null) {
                continue;
            }
            // 序号
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "0"), 0)) {//序号
                placeByExcel(error, name, i, 0, transMapToString(map, "var" + "0"), "格式错误");
            }
            if (transMapToString(l, "var" + "1").equals("")) {//*借款人
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "不能为空");
            }
            if (transMapToString(l, "var" + "" + "2").equals("")) {//*类型
                placeByExcel(error, name, i, 2, transMapToString(map, "var" + "2"), "不能为空");
            }
            if (transMapToString(l, "var" + "3").equals("")) {//*委托起始日期
                placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "不能为空");
            } else if (!FormatValidateTool.isDate(transMapToString(l, "var" + "3"))) {
                placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "格式错误");
            }
            if (transMapToString(l, "var" + "4").equals("")) {//*委托结束日期
                placeByExcel(error, name, i, 4, transMapToString(map, "var" + "3"), "不能为空");
            } else if (!FormatValidateTool.isDate(transMapToString(l, "var" + "4"))) {
                placeByExcel(error, name, i, 4, transMapToString(map, "var" + "3"), "格式错误");
            }
            if (transMapToString(l, "var" + "6").equals("")) {//*所属机构
                placeByExcel(error, name, i, 6, transMapToString(map, "var" + "5"), "不能为空");
            }
            if (transMapToString(l, "var" + "7").equals("")) {//*评优
                placeByExcel(error, name, i, 7, transMapToString(map, "var" + "6"), "不能为空");
            }
            if (transMapToString(l, "var" + "8").equals("")) {//*评级
                placeByExcel(error, name, i, 8, transMapToString(map, "var" + "7"), "不能为空");
            }
            if (!transMapToString(l, "var" + "9").equals("") && transMapToString(l, "var" + "9").indexOf("，") > 0) {//个性处置方式
                placeByExcel(error, name, i, 9, transMapToString(map, "var" + "8"), "不是英文逗号");
            }
            if (!transMapToString(l, "var" + "10").equals("") && transMapToString(l, "var" + "10").indexOf("，") > 0) {//标签
                placeByExcel(error, name, i, 10, transMapToString(map, "var" + "9"), "不是英文逗号");
            }
            if (transMapToString(l, "var" + "10").equals("")) {//*担保方式
                placeByExcel(error, name, i, 10, transMapToString(map, "var" + "10"), "不能为空");
            }
            if (transMapToString(l, "var" + "11").equals("公司担保") && transMapToString(l, "var" + "12").equals("")) {//*公司担保
                placeByExcel(error, name, i, 12, transMapToString(map, "var" + "11"), "不能为空");
            }
            if (transMapToString(l, "var" + "11").equals("公司担保") && transMapToString(l, "var" + "12").equals("抵押") && transMapToString(l, "var" + "13").equals("")) {//*抵押
                placeByExcel(error, name, i, 13, transMapToString(map, "var" + "12"), "不能为空");
            }
            if (transMapToString(l, "var" + "11").equals("公司担保") && transMapToString(l, "var" + "12").equals("质押") && transMapToString(l, "var" + "14").equals("")) {//*质押
                placeByExcel(error, name, i, 14, transMapToString(map, "var" + "13"), "不能为空");
            }
            if (!transMapToString(l, "var" + "11").equals("公司担保")) {
                if (!transMapToString(l, "var" + "12").equals("")) {
                    placeByExcel(error, name, i, 12, transMapToString(map, "var" + "11"), "必须为空");
                }
                if (!transMapToString(l, "var" + "13").equals("")) {
                    placeByExcel(error, name, i, 13, transMapToString(map, "var" + "12"), "必须为空");
                }
                if (!transMapToString(l, "var" + "14").equals("")) {
                    placeByExcel(error, name, i, 14, transMapToString(map, "var" + "13"), "必须为空");
                }
            }
            if (transMapToString(l, "var" + "15").equals("")) {//*担保人是否能联系
                placeByExcel(error, name, i, 15, transMapToString(map, "var" + "14"), "不能为空");
            }
            if (transMapToString(l, "var" + "16").equals("")) {//*担保人经济状况
                placeByExcel(error, name, i, 16, transMapToString(map, "var" + "15"), "不能为空");
            }
            if (transMapToString(l, "var" + "17").equals("")) {//*抵押物估价能否覆盖债务
                placeByExcel(error, name, i, 17, transMapToString(map, "var" + "16"), "不能为空");
            }
            if (transMapToString(l, "var" + "18").equals("")) {//*诉讼与否
                placeByExcel(error, name, i, 18, transMapToString(map, "var" + "17"), "不能为空");
            }
            if (transMapToString(l, "var" + "19").equals("")) {//*判决与否
                placeByExcel(error, name, i, 19, transMapToString(map, "var" + "18"), "不能为空");
            }
            if (transMapToString(l, "var" + "20").equals("")) {//*实地催收次数
                placeByExcel(error, name, i, 20, transMapToString(map, "var" + "19"), "不能为空");
            }
            if (transMapToString(l, "var" + "21").equals("")) {//*电话催收次数
                placeByExcel(error, name, i, 21, transMapToString(map, "var" + "20"), "不能为空");
            }
            if (transMapToString(l, "var" + "22").equals("")) {//*委托催收次数
                placeByExcel(error, name, i, 22, transMapToString(map, "var" + "21"), "不能为空");
            }
            if (transMapToString(l, "var" + "23").equals("")) {//*债务方是否能正常联系
                placeByExcel(error, name, i, 23, transMapToString(map, "var" + "22"), "不能为空");
            }
            if (transMapToString(l, "var" + "24").equals("")) {//*债务方是否有能力偿还
                placeByExcel(error, name, i, 24, transMapToString(map, "var" + "23"), "不能为空");
                ;
            }
        }
    }

    private static void checkPawn(List<ExcelMessage> error, List<Map<String, Object>> list) {
        String name = "抵押物信息表";
        Map<String, Object> map = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (transStringToInteger(transMapToString(l, "var" + "0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "0"), 0)) {//序号
                placeByExcel(error, name, i, 0, transMapToString(map, "var" + "0"), "格式错误");
            }
            if (transMapToString(l, "var" + "1").equals("")) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "不能为空");
            }
            if (!transMapToString(l, "var" + "1").equals("") && transMapToString(l, "var" + "1").split("-").length != 2 && !FormatValidateTool.isNumeric(transMapToString(l, "var" + "1").split("-")[0])) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "格式错误");
            }
            if (transMapToString(l, "var" + "2").equals("")) {//*所属原始借据（号）
                placeByExcel(error, name, i, 2, transMapToString(map, "var" + "2"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "3"), null)) {//*贷款金额
                placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "格式错误");
            }
            if (transMapToString(l, "var" + "4").equals("")) {//*抵押物类型
                placeByExcel(error, name, i, 4, transMapToString(map, "var" + "4"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "5"), null)) {//*抵押物面积
                placeByExcel(error, name, i, 5, transMapToString(map, "var" + "5"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "6").replace("%", ""), null)) {//*抵押率
                placeByExcel(error, name, i, 6, transMapToString(map, "var" + "6"), "格式错误");
            }
            if (transMapToString(l, "var" + "7").equals("")) {//*地址省
                placeByExcel(error, name, i, 7, transMapToString(map, "var" + "7"), "不能为空");
            }
            if (transMapToString(l, "var" + "8").equals("")) {//*地址市
                placeByExcel(error, name, i, 8, transMapToString(map, "var" + "8"), "不能为空");
            }
            if (transMapToString(l, "var" + "9").equals("")) {//*地址区
                placeByExcel(error, name, i, 9, transMapToString(map, "var" + "9"), "不能为空");
            }
            if (transMapToString(l, "var" + "10").equals("")) {//*具体地址
                placeByExcel(error, name, i, 10, transMapToString(map, "var" + "10"), "不能为空");
            }
            if (transMapToString(l, "var" + "11").equals("")) {//*处置状态
                placeByExcel(error, name, i, 11, transMapToString(map, "var" + "11"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "12"), null)) {//*抵押物估值（元）
                placeByExcel(error, name, i, 12, transMapToString(map, "var" + "12"), "格式错误");
            }
            String[] strs = transMapToString(l, "var" + "13").split(",");
            for (String str : strs) {
                if (!FormatValidateTool.isDecimals(str, null)) {//*抵押物中的借据
                    placeByExcel(error, name, i, 13, transMapToString(map, "var" + "13"), "格式错误:不是英文逗号或不是数字");
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
            if (transStringToInteger(transMapToString(l, "var" + "0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "0"), 0)) {
                placeByExcel(error, name, i, 0, transMapToString(map, "var" + "0"), "格式错误");
            }
            if (transMapToString(l, "var" + "1").equals("")) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "不能为空");
            }
            if (!transMapToString(l, "var" + "1").equals("") && transMapToString(l, "var" + "1").split("-").length != 2 && !FormatValidateTool.isNumeric(transMapToString(l, "var" + "1").split("-")[0])) {//*关系
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "格式错误");
            }
            if (transMapToString(l, "var" + "2").equals("")) {//*原始借据（号）
                placeByExcel(error, name, i, 2, transMapToString(map, "var" + "2"), "不能为空");
            }
            if (transMapToString(l, "var" + "3").equals("")) {//*贷款类型
                placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "不能为空");
            }
            if (transMapToString(l, "var" + "4").equals("")) {//*经办机构
                placeByExcel(error, name, i, 4, transMapToString(map, "var" + "4"), "不能为空");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var" + "5"))) {//*放款日期
                placeByExcel(error, name, i, 5, transMapToString(map, "var" + "5"), "格式错误");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var" + "6"))) {//*到期日期
                placeByExcel(error, name, i, 6, transMapToString(map, "var" + "6"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "7"), null)) {//*放款金额
                placeByExcel(error, name, i, 7, transMapToString(map, "var" + "7"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "8").replace("%", ""), null)) {//*合同利率
                placeByExcel(error, name, i, 8, transMapToString(map, "var" + "8"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "9"), null)) {//*逾期利率倍数
                placeByExcel(error, name, i, 9, transMapToString(map, "var" + "9"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "10"), null)) {//*挪用利率倍数
                placeByExcel(error, name, i, 10, transMapToString(map, "var" + "10"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "10"), null)) {//*已还利息金额
                placeByExcel(error, name, i, 11, transMapToString(map, "var" + "11"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "12"), null)) {//*已还贷款金额
                placeByExcel(error, name, i, 12, transMapToString(map, "var" + "12"), "格式错误");
            }
            if (transMapToString(l, "var" + "13").equals("")) {//*五级分类
                placeByExcel(error, name, i, 13, transMapToString(map, "var" + "13"), "不能为空");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "14"), 0)) {//*逾期天数
                placeByExcel(error, name, i, 14, transMapToString(map, "var" + "14"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "15"), null)) {//*剩余本金
                placeByExcel(error, name, i, 15, transMapToString(map, "var" + "15"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "16"), null)) {//*拖欠利息
                placeByExcel(error, name, i, 16, transMapToString(map, "var" + "16"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "17"), null)) {//*罚息
                placeByExcel(error, name, i, 17, transMapToString(map, "var" + "17"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "18"), null)) {//*欠款合计
                placeByExcel(error, name, i, 18, transMapToString(map, "var" + "18"), "格式错误");
            }
            if (!FormatValidateTool.isDate(transMapToString(l, "var" + "19"))) {//*欠款合计截止日期
                placeByExcel(error, name, i, 19, transMapToString(map, "var" + "19"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "20"), null)) {//*抵押物银行估值
                placeByExcel(error, name, i, 20, transMapToString(map, "var" + "20"), "格式错误");
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "21"), null)) {//*提前偿还本金
                placeByExcel(error, name, i, 21, transMapToString(map, "var" + "21"), "格式错误");
            }
            String[] strs = transMapToString(l, "var" + "22").split(",");
            for (String str : strs) {
                if (!str.matches("[A-Z]")) {//*借据中的抵押物
                    placeByExcel(error, name, i, 22, transMapToString(map, "var" + "22"), "格式错误:不是英文逗号或不是大写字母");
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
            if (transStringToInteger(transMapToString(l, "var" + "0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(transMapToString(l, "var" + "0"), 0)) {//序号
                placeByExcel(error, name, i, 0, transMapToString(map, "var" + "0"), "格式错误");
            }
            if (transMapToString(l, "var" + "1").equals("")) {//相关种类
                placeByExcel(error, name, i, 1, transMapToString(map, "var" + "1"), "不能为空");
            }
            if (transMapToString(l, "var" + "2").equals("")) {//姓名验证
                placeByExcel(error, name, i, 2, transMapToString(map, "var" + "2"), "不能为空");
            }
            try {
                if (!FormatValidateTool.idCardValidate(transMapToString(l, "var" + "3")).equals("")) {//身份证验证
                    placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "格式错误");
                }
            } catch (ParseException e) {
                placeByExcel(error, name, i, 3, transMapToString(map, "var" + "3"), "格式错误");
            }
            if (!FormatValidateTool.checkMobile(transMapToString(l, "var" + "4"))) {//手机号验证
                placeByExcel(error, name, i, 4, transMapToString(map, "var" + "4"), "格式错误");
            }
            if (!transMapToString(l, "var" + "5").equals("") && !FormatValidateTool.checkPhone(transMapToString(l, "var" + "5"))) {//住宅电话
                placeByExcel(error, name, i, 5, transMapToString(map, "var" + "5"), "格式错误");
            }
            if (!transMapToString(l, "var" + "6").equals("") && !FormatValidateTool.checkPhone(transMapToString(l, "var" + "6"))) {//办公电话
                placeByExcel(error, name, i, 6, transMapToString(map, "var" + "6"), "格式错误");
            }
            if (!transMapToString(l, "var" + "7").equals("") && !FormatValidateTool.checkEmail(transMapToString(l, "var" + "7"))) {//电子邮箱
                placeByExcel(error, name, i, 7, transMapToString(map, "var" + "7"), "格式错误");
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
    public static void placeByExcel(List<ExcelMessage> error, String name,
                                    Integer row, Integer col, String fieldsName, String msg) {
//        String[] str = {(error.size() + 1) + "", name, (row) + "行" + (col + 1) + "列", fieldsName, msg};
        String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        ExcelMessage excelMessage = new ExcelMessage((error.size() + 1), name, (row) + "行" + (letter[col]) + "列", fieldsName, msg);
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
        String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        for (int i = 0; i < arr.length; i++) {
            if (!transMapToString(map, "var" + "" + i).equals(arr[i])) {
                ExcelMessage excelMessage = new ExcelMessage((error.size() + 1), name, (letter[i]) + "列", arr[i], errMsg);
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
            if (!transMapToString(map, "var" + "" + i).equals(strs[i])) {
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
            if (transStringToInteger(transMapToString(map3, "var" + "0")) == null) {
                continue;
            }
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(transStringToInteger(transMapToString(map3, "var" + "0")));
            contactDTO.setType(ContactTypeEnum.getContactTypeEnumValue(transMapToString(map3, "var" + "1")));//联系人类型
            contactDTO.setName(transMapToString(map3, "var" + "2"));// 姓名
            contactDTO.setIdcard(transMapToString(map3, "var" + "3"));// 身份证
            contactDTO.setMobile(transMapToString(map3, "var" + "4"));// 手机号
            contactDTO.setHomeTel(transMapToString(map3, "var" + "5")); // 家庭电话
            contactDTO.setOfficeTel(transMapToString(map3, "var" + "6")); // 办公电话
            contactDTO.setEmail(transMapToString(map3, "var" + "7"));// 电子邮件
            contactDTO.setOther1(transMapToString(map3, "var" + "8"));//其它联系方式01
            contactDTO.setOther2(transMapToString(map3, "var" + "9"));//其它联系方式02
            contactDTO.setOther3(transMapToString(map3, "var" + "10"));//其它联系方式03
            contactDTO.setProvince(AreaTool.getAreaId(transMapToString(map3, "var" + "11")));//省
            contactDTO.setCity(AreaTool.getAreaId(transMapToString(map3, "var" + "12")));//市
            contactDTO.setDistrict(AreaTool.getAreaId(transMapToString(map3, "var" + "13")));//区县
            contactDTO.setAddress(transMapToString(map3, "var" + "14"));
            contactDTO.setCompany(transMapToString(map3, "var" + "15")); // 公司
            contactDTO.setMemo(transMapToString(map3, "var" + "16"));// 备注
            contactDTOs.add(contactDTO);
        }
    }

    private static void disposeIou(List<Map<String, Object>> list2, List<IouDTO> iouDTOs) {
        for (int i = 1; i < list2.size(); i++) {
            Map<String, Object> map2 = list2.get(i);
            if (transStringToInteger(transMapToString(map2, "var" + "0")) == null) {
                continue;
            }
            IouDTO iouDTO = new IouDTO();
            iouDTO.setId(transStringToInteger(transMapToString(map2, "var" + "1").split("-")[0]));
            iouDTO.setIouName(transMapToString(map2, "var" + "1").split("-")[1]);
            iouDTO.setIouCode(transMapToString(map2, "var" + "2"));//原始借据号
            iouDTO.setType(transMapToString(map2, "var" + "3"));// 贷款类型
            iouDTO.setAgency(transMapToString(map2, "var" + "4")); // 代理机构
            iouDTO.setLoanTime(DateFormatTool.parse(transMapToString(map2, "var" + "5"), DateFormatTool.DATE_FORMAT_10_REG2));// 放款时间
            iouDTO.setLoanAtTime(DateFormatTool.parse(transMapToString(map2, "var" + "6"), DateFormatTool.DATE_FORMAT_10_REG2));// 到款时间
            iouDTO.setAmount(transStringToDouble(transMapToString(map2, "var" + "7"))); // 金额
            iouDTO.setPactRate(transStringToDouble(transMapToString(map2, "var" + "8").replace("%", "")));// 合同利率
            iouDTO.setOuttimeMultiple(transStringToDouble(transMapToString(map2, "var" + "9")));// 逾期倍数
            iouDTO.setAppropriationMultiple(transMapToString(map2, "var" + "10"));// 挪用倍数
            iouDTO.setAccrualRepay(transStringToDouble(transMapToString(map2, "var" + "11")));// 已还利息金额
            iouDTO.setLoanRepay(transStringToDouble(transMapToString(map2, "var" + "12")));// 已还贷款金额
            iouDTO.setLevelType(transMapToString(map2, "var" + "13"));// 5级分类
            iouDTO.setOutDays(transStringToInteger(transMapToString(map2, "var" + "14")));// 逾期天数
            iouDTO.setLessCorpus(transStringToDouble(transMapToString(map2, "var" + "15")));// 剩余本金
            iouDTO.setAccrualArrears(transStringToDouble(transMapToString(map2, "var" + "16"))); // 拖欠利息
            iouDTO.setPenalty(transStringToDouble(transMapToString(map2, "var" + "17"))); // 拖欠利息
            iouDTO.setArrears(transStringToDouble(transMapToString(map2, "var" + "18"))); // 欠款合计
            iouDTO.setEndAt(DateFormatTool.parse(transMapToString(map2, "var" + "19"), DateFormatTool.DATE_FORMAT_10_REG2));// 欠款截止日期
            iouDTO.setWorth(transStringToDouble(transMapToString(map2, "var" + "20")));// 价值
            iouDTO.setAdvanceCorpus(transStringToDouble(transMapToString(map2, "var" + "21")));// 提前偿还本金
            iouDTO.setPawnIds(transMapToString(map2, "var" + "22"));// 抵押物IDs
            iouDTOs.add(iouDTO);
        }
    }

    private static void disposePawn(List<Map<String, Object>> list1, List<PawnDTO> pawnDTOs) {
        for (int i = 1; i < list1.size(); i++) {
            Map<String, Object> map1 = list1.get(i);
            if (transStringToInteger(transMapToString(map1, "var" + "0")) == null) {
                continue;
            }
            PawnDTO pawnDTO = new PawnDTO();
            pawnDTO.setId(transStringToInteger(transMapToString(map1, "var" + "1").split("-")[0]));
            pawnDTO.setPawnName(transMapToString(map1, "var" + "1").split("-")[1]);//关系的抵押物编号
            pawnDTO.setAmount(transStringToDouble(transMapToString(map1, "var" + "3")));//贷款金额
            pawnDTO.setType(transMapToString(map1, "var" + "4"));// 抵押物类型
            pawnDTO.setSize(transMapToString(map1, "var" + "5"));// 规模大小
            pawnDTO.setPawnRate(transStringToDouble(transMapToString(map1, "var" + "6").replace("%", "")));// 抵押率
            pawnDTO.setProvince(AreaTool.getAreaId(transMapToString(map1, "var" + "7")));//*省
            pawnDTO.setCity(AreaTool.getAreaId(transMapToString(map1, "var" + "8")));//*市
            pawnDTO.setDistrict(AreaTool.getAreaId(transMapToString(map1, "var" + "9")));//*区县
            pawnDTO.setAddress(transMapToString(map1, "var" + "10"));//*抵押物中 详细地址
            pawnDTO.setDisposeStatus(transMapToString(map1, "var" + "11")); // 处置状态
            pawnDTO.setWorth(transStringToDouble(transMapToString(map1, "var" + "12")));// 价值
            pawnDTO.setIouIds(transMapToString(map1, "var" + "13"));//*抵押物中的借据
            pawnDTO.setMemo(transMapToString(map1, "var" + "14"));// 备注
            pawnDTOs.add(pawnDTO);
        }
    }

    private static void disposeLender(List<Map<String, Object>> list0, List<LenderDTO> lenderDTOs) {
        for (int i = 1; i < list0.size(); i++) {
            Map<String, Object> map0 = list0.get(i);
            if (transStringToInteger(transMapToString(map0, "var" + "0")) == null) {
                continue;
            }
            LenderDTO lenderDTO = new LenderDTO();
            lenderDTO.setId(transStringToInteger(transMapToString(map0, "var" + "0")));//序号
            lenderDTO.setStartAt(DateFormatTool.parse(transMapToString(map0, "var" + "3"), DateFormatTool.DATE_FORMAT_10_REG2));//委托起始日期
            lenderDTO.setEndAt(DateFormatTool.parse(transMapToString(map0, "var" + "4"), DateFormatTool.DATE_FORMAT_10_REG2));//委托结束日期
            lenderDTO.setSourceWay(transMapToString(map0, "var" + "5"));//来源方式
            lenderDTO.setSubsidiary(transMapToString(map0, "var" + "6"));//所属机构
            lenderDTO.setEvaluateExcellent(ExcellentTypeEnum.getEnum(transMapToString(map0, "var" + "7")));//评优
            lenderDTO.setEvaluateLevel(transMapToString(map0, "var" + "8"));//评级
            lenderDTO.setDisposeMode(setDisposeMode(transMapToString(map0, "var" + "9")));//个性处置
            lenderDTO.setTags(transMapToString(map0, "var" + "10"));//标签
            lenderDTO.setGuaranteeType(transMapToString(map0, "var" + "11").equals("公司担保") == true ? "公司" : "个人");//担保方式
            lenderDTO.setGuaranteeMode(transMapToString(map0, "var" + "12"));//公司担保
            lenderDTO.setGuaranteeSource(lenderDTO.getGuaranteeMode().equals("") == true ? "" : lenderDTO.getGuaranteeMode().equals("抵押") == true ? transMapToString(map0, "var" + "13") : transMapToString(map0, "var" + "14"));// 担保源
            lenderDTO.setIsGuaranteeConnection(transMapToString(map0, "var" + "15").equals("是") == true ? 1 : 0);//担保人是否能联系
            lenderDTO.setGuaranteeEconomic(transMapToString(map0, "var" + "16"));//担保人经济状况
            lenderDTO.setIsWorth(transMapToString(map0, "var" + "17").equals("否") == true ? 0 : transMapToString(map0, "var" + "17").equals("能") == true ? 1 : 2);//抵押物是否能覆盖债务
            lenderDTO.setIsLawsuit(transMapToString(map0, "var" + "18").equals("未诉讼") == true ? 0 : transMapToString(map0, "var" + "18").equals("已诉讼") == true ? 1 : 2);//诉讼与否
            lenderDTO.setIsDecision(transMapToString(map0, "var" + "19").equals("未判决") == true ? 0 : transMapToString(map0, "var" + "19").equals("已判决") == true ? 1 : 2);// 判决与否
            lenderDTO.setRealUrgeTime(transStringToInteger(transMapToString(map0, "var" + "20")));//实地催收次数
            lenderDTO.setPhoneUrgeTime(transStringToInteger(transMapToString(map0, "var" + "21")));//电话催收次数
            lenderDTO.setEntrustUrgeTime(transStringToInteger(transMapToString(map0, "var" + "22")));//委托催收次数
            lenderDTO.setCanContact(transMapToString(map0, "var" + "23").equals("是") == true ? 1 : 0);//债务方是否能正常联系
            lenderDTO.setCanPay(transMapToString(map0, "var" + "24").equals("是") == true ? 1 : 0);//债务方是否能偿还
            lenderDTOs.add(lenderDTO);
        }
    }

    /**
     * 处置方式处理成json格式
     *
     * @param mode
     * @return
     */
    private static String setDisposeMode(String mode) {
        String[] modes = mode.split(",");
        String str = "[";
        for (int i = 0; i < modes.length; i++) {
            Integer value = null;
            //常规催收
            if (modes[i].equals(LenderEnum.STATUS_COLLECTION.getName())) {
                value = LenderEnum.STATUS_COLLECTION.getValue();
            }
            //司法化解
            if (modes[i].equals(LenderEnum.STATUS_JUDICIARY.getName())) {
                value = LenderEnum.STATUS_JUDICIARY.getValue();
            }
            //市场处置
            if (modes[i].equals(LenderEnum.STATUS_BAZAAR.getName())) {
                value = LenderEnum.STATUS_BAZAAR.getValue();
            }
            if (value != null) {
                if (i == modes.length - 1) {
                    str += "{\"disMethod\":" + value + "\",\"disQuota\":0,\"disVal\":\"0\"}";
                } else {
                    str += "{\"disMethod\":" + value + "\",\"disQuota\":0,\"disVal\":\"0\"},";
                }
            }
        }
        str += "]";
        return str;
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
