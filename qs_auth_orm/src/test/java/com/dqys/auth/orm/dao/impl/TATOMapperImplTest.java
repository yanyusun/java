package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.base.BaseTest;
import com.dqys.auth.orm.dao.facade.TATOInfoMapper;
import com.dqys.auth.orm.pojo.TATOInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
public class TATOMapperImplTest extends BaseTest {

    @Autowired
    public TATOInfoMapper tatoInfoMapper;

    @Test
    public void test(){
        TATOInfo tatoInfo = new TATOInfo();
        tatoInfo.setType(1);
        tatoInfo.setName("测试部门名称");
        // 新增
        Integer count = tatoInfoMapper.insertSelective(tatoInfo);
        Assert.assertEquals(Integer.valueOf(1), count);

        Integer id = tatoInfo.getId();

        // id查询
        TATOInfo tatoInfo1 = tatoInfoMapper.selectByPrimaryKey(id);
        Assert.assertNotNull(tatoInfo1);

        // 修改
        tatoInfo1.setType(2);
        Integer result = tatoInfoMapper.updateByPrimaryKey(tatoInfo1);
        Assert.assertNotNull(result);
        tatoInfo1 = tatoInfoMapper.selectByPrimaryKey(id);
        Assert.assertEquals(Integer.valueOf(2),tatoInfo1.getType());

        // 类型查全
        for(int i=1;i<4;i++){
            tatoInfo.setName("部门" + i);
            tatoInfoMapper.insertSelective(tatoInfo);
        }
        List<TATOInfo> tatoInfoList = tatoInfoMapper.selectListByType(1);
        Assert.assertNotNull(tatoInfoList);
        Assert.assertEquals(tatoInfoList.size(), 3);


    }
}
