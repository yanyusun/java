package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.service.dto.NewsDTO;
import com.dqys.sale.service.dto.news.NewsDtoY;
import com.dqys.sale.service.dto.news.SecondLevelDtoList;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/24.
 */
public interface NewsService {
    /**
     * todo 改为修正后的查询方法
     * 新闻列表
     *
     * @param query
     * @return
     */
    JsonResponse newsList(NewsQuery query);


    /**
     * todo 改为修正后的查询方法
     *
     * 首页新闻
     *
     * @return
     */
    JsonResponse indexList(NewsQuery query);

    /**
     * 二级页面
     *
     * @return
     */
    SecondLevelDtoList sencondLevelPage();

    /**
     * 二级新闻列表
     *
     * @param type 　新闻类型
     * @return
     */
    List<NewsDtoY> sencondNewsList(int type,int page);

    JsonResponse List(NewsQuery query);

    JsonResponse getDetail(Integer newsId);

    JsonResponse addOrUpdateNews_tx(NewsDTO newsDTO);
}
