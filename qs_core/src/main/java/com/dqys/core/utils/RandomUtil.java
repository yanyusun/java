package com.dqys.core.utils;

import com.dqys.core.cache.MybatisRedisCache;
import com.mysql.jdbc.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public final static String OWNER_CODE = "YZ";//业主
    public final static String INDIVIDUAL_CREDITOR_CODE = "CZ";//个人债权
    public final static String ENTERPRISE_CREDITOR_CODE = "QZ";//企业债权
    public final static String PAST_LOANS_CODE = "YQ";//逾期贷款
    public final static String FIXED_ASSET_CODE = "GD";//固定资产
    public final static String ASSET_PACKAGE_CODE = "AS";//资产包（B端）
    private static String timeFormat = "yyMM";//日期格式
    private static Integer count = 10000;//循环数
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
        List<String> ownerList = setCodeList(OWNER_CODE + time);
        List<String> individualList = setCodeList(INDIVIDUAL_CREDITOR_CODE + time);
        List<String> enterpriseList = setCodeList(ENTERPRISE_CREDITOR_CODE + time);
        List<String> pastList = setCodeList(PAST_LOANS_CODE + time);
        List<String> fixdeList = setCodeList(FIXED_ASSET_CODE + time);
        List<String> packageList = setCodeList(ASSET_PACKAGE_CODE + time);
//        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
//        mybatisRedisCache.putObject(ASSET_CODE + time, assetList);
//        mybatisRedisCache.putObject(CASE_CODE + time, caseList);
//        mybatisRedisCache.putObject(IOU_CODE + time, iouList);
//        mybatisRedisCache.putObject(LENDER_CODE + time, lenderList);
//        mybatisRedisCache.putObject(PAWN_CODE + time, pawnList);
//        mybatisRedisCache.putObject(ESTATES_CODE + time, estatesList);
        NoSQLWithRedisTool.setValueObject(ASSET_CODE + time, assetList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(CASE_CODE + time, caseList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(IOU_CODE + time, iouList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(LENDER_CODE + time, lenderList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(PAWN_CODE + time, pawnList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(ESTATES_CODE + time, estatesList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(OWNER_CODE + time, ownerList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(INDIVIDUAL_CREDITOR_CODE + time, individualList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(ENTERPRISE_CREDITOR_CODE + time, enterpriseList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(PAST_LOANS_CODE + time, pastList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(FIXED_ASSET_CODE + time, fixdeList, 31L, TimeUnit.DAYS);
        NoSQLWithRedisTool.setValueObject(ASSET_PACKAGE_CODE + time, packageList, 31L, TimeUnit.DAYS);
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
                && !LENDER_CODE.equals(code) && !PAWN_CODE.equals(code) && !ESTATES_CODE.equals(code)
                && !OWNER_CODE.equals(code) && !INDIVIDUAL_CREDITOR_CODE.equals(code) && !ENTERPRISE_CREDITOR_CODE.equals(code)
                && !PAST_LOANS_CODE.equals(code) && !FIXED_ASSET_CODE.equals(code) && !ASSET_PACKAGE_CODE.equals(code)) {
            return null;
        }
        String time = new SimpleDateFormat(timeFormat).format(new Date());
//        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
//        List<String> list = (List<String>) mybatisRedisCache.getObject(code + time);
        List<String> list = NoSQLWithRedisTool.getValueObject(code + time) == null ? new ArrayList<>() : NoSQLWithRedisTool.getValueObject(code + time);
        if (list.size() > 0) {
            Integer num = CommonUtil.getRandomNum(list.size());
            String serialNumber = list.get(num);//规则编号
            list.remove(serialNumber);
//            mybatisRedisCache.putObject(code + time, list);
            NoSQLWithRedisTool.setValueObject(code + time, list, 31L, TimeUnit.DAYS);
            return serialNumber;
        } else {
            createRandomNum(time);
            getCode(code);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(createRandomNum("1609"));
//        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
//        List<String> list = (List<String>) mybatisRedisCache.getObject(ASSET_CODE + "1608");
//        System.out.println(list);
//        System.out.println(getCode(ASSET_CODE));

    }


}
