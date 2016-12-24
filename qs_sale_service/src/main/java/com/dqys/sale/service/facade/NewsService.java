package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.service.dto.NewsDTO;

/**
 * Created by mkfeng on 2016/12/24.
 */
public interface NewsService {
    JsonResponse newsList(NewsQuery query);

    JsonResponse getDetail(Integer newsId);

    JsonResponse addOrUpdateNews(NewsDTO newsDTO);
}
