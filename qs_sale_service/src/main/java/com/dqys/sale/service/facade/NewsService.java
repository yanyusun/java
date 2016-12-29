package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.service.dto.NewsDTO;

/**
 * Created by mkfeng on 2016/12/24.
 */
public interface NewsService {
    /**
     * 新闻列表
     * @param query
     * @return
     */
    JsonResponse newsList(NewsQuery query);

    /**
     * 首页新闻
     * @param query
     * @return
     */
    JsonResponse indexList(NewsQuery query);

    JsonResponse List(NewsQuery query);

    JsonResponse getDetail(Integer newsId);

    JsonResponse addOrUpdateNews_tx(NewsDTO newsDTO);
}
