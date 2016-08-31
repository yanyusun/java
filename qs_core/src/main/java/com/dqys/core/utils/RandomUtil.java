package com.dqys.core.utils;

import com.dqys.core.cache.MybatisRedisCache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 随机编号规则
 * Created by mkfeng on 2016/8/30.
 */
public class RandomUtil {

    public final static String ASSET_CODE = "QS";//资产包
    public final static String CASE_CODE = "LC";//案件
    public final static String IOU_CODE = "IO";//借据
    public final static String LENDER_CODE = "BO";//借款人
    public final static String PAWN_CODE = "CO";//抵押物
    public final static String ESTATES_CODE = "ZC";//资产源
    private static String timeFormat = "yyMM";//日期格式
    private static Integer count = 10;//循环数
    private static Integer codePlaces = 4;//随机位数的个数

    public static String createRandomNum(String time) {
        if (!time.matches("\\d{" + timeFormat.length() + "}")) {
            return "error_time";
        }
        List<String> assetList = setCodeList(ASSET_CODE + time);
        List<String> caseList = setCodeList(CASE_CODE + time);
        List<String> iouList = setCodeList(IOU_CODE + time);
        List<String> lenderList = setCodeList(LENDER_CODE + time);
        List<String> pawnList = setCodeList(PAWN_CODE + time);
        List<String> estatesList = setCodeList(ESTATES_CODE + time);
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
        mybatisRedisCache.putObject(ASSET_CODE + time, assetList);
        mybatisRedisCache.putObject(CASE_CODE + time, caseList);
        mybatisRedisCache.putObject(IOU_CODE + time, iouList);
        mybatisRedisCache.putObject(LENDER_CODE + time, lenderList);
        mybatisRedisCache.putObject(PAWN_CODE + time, pawnList);
        mybatisRedisCache.putObject(ESTATES_CODE + time, estatesList);
        return "yes";
    }

    private static List<String> setCodeList(String code) {
        List<String> codes = new ArrayList<>();
        for (Integer i = 1; i < count; i++) {
            String number = i.toString();
            int num = number.length();
            if (num > codePlaces) {
                break;
            }
            for (int j = 0; j < codePlaces - num; j++) {    //所缺位数前面补0
                number = 0 + number;
            }
            codes.add(code + number);
        }
        return codes;
    }

    public static String getCode(String code) {
        if (!ASSET_CODE.equals(code) && !CASE_CODE.equals(code) && !IOU_CODE.equals(code)
                && !LENDER_CODE.equals(code) && !PAWN_CODE.equals(code) && !ESTATES_CODE.equals(code)) {
            return null;
        }
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
        String time = new SimpleDateFormat(timeFormat).format(new Date());
        List<String> list = (List<String>) mybatisRedisCache.getObject(code + time);
        if (list.size() > 0) {
            Integer num = CommonUtil.getRandomNum(list.size());
            String serialNumber = list.get(num);//规则编号
            list.remove(serialNumber);
            mybatisRedisCache.putObject(code + time, list);
            return serialNumber;
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(createRandomNum("1608"));
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
        List<String> list = (List<String>) mybatisRedisCache.getObject(ASSET_CODE + "1608");
        System.out.println(list);
        System.out.println(getCode(ASSET_CODE));

    }


}
