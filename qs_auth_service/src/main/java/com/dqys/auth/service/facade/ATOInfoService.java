package com.dqys.auth.service.facade;

import com.dqys.auth.orm.pojo.TATOInfo;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
public interface ATOInfoService {

    /**
     * 新增一个项目组
     * @return
     */
    Integer add(TATOInfo tatoInfo);

    /**
     * 根据id修改项目
     * @return
     */
    Integer updateById(TATOInfo tatoInfo);

    /**
     * 删除项目组
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据ID获取项目
     * @param id
     * @return
     */
    TATOInfo get(Integer id);

    /**
     * 品类查全
     * @param type
     * @return
     */
    List<TATOInfo> listAll(Integer type);

}
