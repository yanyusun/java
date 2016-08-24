package com.dqys.core.utils;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;

import java.util.*;

/**
 * Created by Yvan on 16/6/12.
 */
public class CommonUtil {

    public static final String[] UPLETTER = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    public static final String[] LOWERLETTER = {"a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"};
    public static final String[] CNA_UP_CASE_NUMBER = {
            "零", "壹", "贰", "叁", "肆", "伍", "陆", "染", "捌", "玖"
    };
    public static final String[] CNA_UP_CASE_UNIT = {
            "", "拾", "佰", "仟", "萬", "亿"
    };

    /**
     * 检验参数是否为空
     *
     * @param data
     * @return true 存在空值数据
     */
    public static boolean checkParam(Object... data) {
        if (data.length > 0) {
            for (Object o : data) {
                if (o == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检验参数是否全为空
     *
     * @param datas
     * @return true 存在不为空数据
     */
    public static boolean checkNullParam(Object... datas) {
        if (datas.length > 0) {
            for (Object o : datas) {
                if (o != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断数组中是否存在某字符
     * @param arr 目标数组
     * @param str 字符
     * @return true 数组中存在该字符
     */
    public static boolean isExist(String[] arr, String str){
        if(arr == null || arr.length == 0 || str == null || str.equals("")){
            return false;
        }
        for (String s : arr) {
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化评级
     * @param num
     * @return
     */
    public static String[] initLevel(Integer num){
        return CommonUtil.UPLETTER.toString().substring(0,num).split(",");
    }

    /**
     * 判断是否金额格式(2位小数)
     * @param numArr 金额
     * @return true 符合金额格式
     */
    public static boolean isMoneyFormat(Double... numArr){
        if(numArr == null || numArr.length == 0){
            return false;
        }
        for (Double num : numArr) {
            Double number = num * 1000;
            if(number % 10 > 0){
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args){
//        Double a = 100.2;
//        Double b = 100.22;
//        Double c = 100.233;
//        System.out.println("a:" + isMoneyFormat(a) + " b:" + isMoneyFormat(a) + " c:" + isMoneyFormat(c));
//
//    }

    /**
     * 通用判断结果是否为空返回消息
     *
     * @param data
     * @return
     */
    public static JsonResponse responseBack(Object data) {
        if (data == null || data.equals(0)) {
            return JsonResponseTool.failure("操作失败!");
        } else {
            return JsonResponseTool.success(data);
        }
    }

    /**
     * 通用检查返回结果<非空|非空字符串|非0返回值>
     *
     * @param id
     * @return
     */
    public static boolean checkResult(Integer id) {
        if (id == null || id.equals("") || id.equals("0")) {
            return true;
        }
        return false;
    }

    /**
     * 对数据去重
     *
     * @param list
     * @return
     */
    public static List<Integer> exceptMulty(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        list.forEach(integer -> {
            set.add(integer);
        });
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    /**
     * 两个表数据提取出不重复的重复数据
     *
     * @param list
     * @param list2
     * @return
     */
    public static List<Integer> unionList(List<Integer> list, List<Integer> list2) {
        if (checkParam(list, list2)) {
            return null;
        }
        if (list == null || list.size() == 0) {
            return exceptMulty(list2);
        }
        if (list2 == null || list2.size() == 0) {
            return exceptMulty(list);
        }
        List<Integer> result = new ArrayList<>();
        list = exceptMulty(list);
        list2 = exceptMulty(list2);
        for (Integer i : list) {
            for (Integer j : list2) {
                if (i.equals(j)) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 两个列表的数据整合到一起,且去重
     *
     * @param list
     * @param list2
     * @return
     */
    public static List<Integer> pickList(List<Integer> list, List<Integer> list2) {
        if (checkParam(list, list2)) {
            return null;
        }
        if (list == null || list.size() == 0) {
            return exceptMulty(list2);
        }
        if (list2 == null || list2.size() == 0) {
            return exceptMulty(list);
        }
        List<Integer> result = new ArrayList<>();
        list = exceptMulty(list);
        list2 = exceptMulty(list2);
        for (Integer i : list) {
            boolean flag = true;
            for (Integer j : list2) {
                if (i.equals(j)) {
                    break;
                }
            }
            if (flag) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 以pList为基础,去除pList与cList之间重复的部分
     *
     * @param pList
     * @param cList
     * @return
     */
    public static List<Integer> exceptList(List<Integer> pList, List<Integer> cList) {
        if (pList == null || pList.size() == 0) {
            return null;
        }
        if (cList == null || cList.size() == 0) {
            return pList;
        }
        List<Integer> result = new ArrayList<>();
        for (Integer i : pList) {
            boolean flag = true;
            for (Integer j : cList) {
                if (i.equals(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 判断当前登录用户是否是总管理员
     *
     * @return true 是总管理员
     */
    public static boolean isManage() {
        String roleStr = UserSession.getCurrent().getRoleId();
        String typeStr = UserSession.getCurrent().getUserType();

        TSysProperty property = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM);
        String reg = property.getPropertyValue();
        property = SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY);
        String reg1 = property.getPropertyValue();

        if (roleStr.indexOf(reg) > 0
                && typeStr.indexOf(reg1) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 创建特定位数的数字
     *
     * @param number
     * @param size
     * @return
     */
    public static String createStringNumber(Integer number, Integer size) {
        StringBuffer result = new StringBuffer();
        while (number > 0) {
            Integer suffNum = number % 10;
            number = number / 10;
            result.insert(0, suffNum);
        }
        while (result.length() < size) {
            result.insert(0, 0);
        }
        return result.toString();
    }

    /**
     * 将数字单纯转化为大写的数字
     *
     * @param number
     * @return
     */
    public static String createCNANumber(Integer number) {
        StringBuffer result = new StringBuffer();
        while (number > 0) {
            result.insert(0, CNA_UP_CASE_NUMBER[number % 10]);
            number = number / 10;
        }
        return result.toString();
    }

    /**
     * 将数字字符串转化成大写
     *
     * @param numberStr
     * @return
     */
    public static String createCNANumber(String numberStr) {
        StringBuffer result = new StringBuffer();
        Integer index = 0;
        while (numberStr.length() > index) {
            result.append(CNA_UP_CASE_NUMBER[Integer.valueOf(numberStr.substring(index, index + 1))]);
            index++;
        }
        return result.toString();
    }


//    public static void main(String[] a){
//
//        System.out.println(createStringNumber(20,5));
//        System.out.println(createCNANumber(305));
//        System.out.println(createCNANumber("00234"));
//    }

}
