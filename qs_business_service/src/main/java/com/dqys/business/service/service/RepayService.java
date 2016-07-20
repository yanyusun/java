package com.dqys.business.service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by mkfeng on 2016/7/19.
 */
public interface RepayService {

    /**
     * 还款操作
     * @param objectId 对象id
     * @param objectType 对象类型
     * @param repayType 还款类型
     * @param repayWay 还款方式
     * @param money 金额
     * @param remark 备注
     * @param file 文件
     * @return
     */
    Map repayMoney(Integer userId,Integer objectId, Integer objectType, Integer repayType, Integer repayWay, Double money, String remark, MultipartFile file) throws Exception;
}
