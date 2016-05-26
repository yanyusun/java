package com.dqys.auth.service.impl;

import com.dqys.auth.orm.dao.facade.TATOInfoMapper;
import com.dqys.auth.orm.pojo.TATOInfo;
import com.dqys.auth.service.facade.ATOInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
@Service
@Primary
public class ATOInfoServiceImpl implements ATOInfoService {

    @Autowired
    private TATOInfoMapper tatoInfoMapper;

    @Override
    public Integer add(TATOInfo tatoInfo) {
        return tatoInfoMapper.insertSelective(tatoInfo);
    }

    @Override
    public Integer updateById(TATOInfo tatoInfo) {
        return tatoInfoMapper.updateByPrimaryKey(tatoInfo);
    }

    @Override
    public Integer delete(Integer id) {
        return tatoInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TATOInfo get(Integer id) {
        return tatoInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TATOInfo> listAll(Integer type) {
        return tatoInfoMapper.selectListByType(type);
    }
}
