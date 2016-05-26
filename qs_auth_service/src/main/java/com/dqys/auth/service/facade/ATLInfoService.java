package com.dqys.auth.service.facade;

import com.dqys.auth.orm.pojo.TATLInfo;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
public interface ATLInfoService {

    /**
     * 新增一个项目组
     * @return
     */
    Integer addNewOne();

    /**
     * 根据id修改项目
     * @return
     */
    Integer updateById(TATLInfo tatlInfo);

    /**
     *
     * @param type
     * @return
     */
    List<TATLInfo> listAll(Integer type);

}
