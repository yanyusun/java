package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.NewsMapper;
import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;
import com.dqys.sale.orm.query.NewsQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/24.
 */
@Repository
public class NewsMapperImpl extends SaleBaseDao implements NewsMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NewsMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(News record) {
        return super.getSqlSession().getMapper(NewsMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(News record) {
        return super.getSqlSession().getMapper(NewsMapper.class).insertSelective(record);
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NewsMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(News record) {
        return super.getSqlSession().getMapper(NewsMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(News record) {
        return super.getSqlSession().getMapper(NewsMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(News record) {
        return super.getSqlSession().getMapper(NewsMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<News> list(NewsQuery query) {
        return super.getSqlSession().getMapper(NewsMapper.class).list(query);
    }

    @Override
    public Integer listCount(NewsQuery query) {
        return super.getSqlSession().getMapper(NewsMapper.class).listCount(query);
    }

    @Override
    public List<NewsLable> selectLableByNewId(Integer id) {
        return super.getSqlSession().getMapper(NewsMapper.class).selectLableByNewId(id);
    }

    @Override
    public Integer addNewsAndLableRe(@Param("newsId") Integer newsId, @Param("lableId") Integer lableId) {
        return super.getSqlSession().getMapper(NewsMapper.class).addNewsAndLableRe(newsId, lableId);
    }

    @Override
    public Integer delLableReByNewsId(Integer id) {
        return super.getSqlSession().getMapper(NewsMapper.class).delLableReByNewsId(id);
    }
}
