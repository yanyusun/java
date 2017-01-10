package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.orm.query.NewsQueryY;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper extends BaseMapper<News> {
    /**
     * todo 改为修正后的查询方法
     * @param query
     * @return
     */
    List<News> list(NewsQuery query);

    /**
     * 修正后的查询方法
     * @param query
     * @return
     */
    List<News> listY(NewsQueryY query);

    /**
     * todo 改为修正后的查询方法
     * @param query
     * @return
     */
    Integer listCount(NewsQuery query);

    /**
     * gfenmui
     *
     * @param id
     * @return
     */
    List<NewsLable> selectLableByNewId(Integer id);

    Integer addNewsAndLableRe(@Param("newsId") Integer newsId, @Param("lableId") Integer lableId);

    Integer delLableReByNewsId(Integer id);

    List<News> listWithOutLables(NewsQueryY query);



}