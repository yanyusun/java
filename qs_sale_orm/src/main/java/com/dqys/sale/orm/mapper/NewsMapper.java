package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;
import com.dqys.sale.orm.query.NewsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper extends BaseMapper<News> {
    List<News> list(NewsQuery query);

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
}