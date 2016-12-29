package com.dqys.sale.service.impl;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.mapper.NewsMapper;
import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.service.dto.NewsDTO;
import com.dqys.sale.service.facade.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/24.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public JsonResponse newsList(NewsQuery query) {
        List<News> newses = newsMapper.list(query);
        Integer count = newsMapper.listCount(query);
        query.setTotalCount(count);
        List<NewsDTO> dtos = new ArrayList<>();
        for (News entity : newses) {
            NewsDTO dto = new NewsDTO();
            dto.setNews(entity);
            dto.setLables(newsMapper.selectLableByNewId(entity.getId()));
            dtos.add(dto);
        }
        Map map = new HashMap<>();
        map.put("newsList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse getDetail(Integer newsId) {
        if (newsId == null) {
            return JsonResponseTool.failure("缺少必要数值");
        }
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news != null) {
            NewsDTO dto = new NewsDTO();
            dto.setNews(news);
            dto.setLables(newsMapper.selectLableByNewId(news.getId()));
            return JsonResponseTool.success(dto);
        }
        return JsonResponseTool.failure("没有数据");
    }

    @Override
    public JsonResponse addOrUpdateNews_tx(NewsDTO newsDTO) {
        Integer num = 0;
        News news = newsDTO.getNews();
        if (news.getId() == null) {
            num = newsMapper.insertSelective(news);
        } else {
            num = newsMapper.updateByPrimaryKeySelective(news);
            newsMapper.delLableReByNewsId(news.getId());
        }
        if (num == 0) {
            return JsonResponseTool.failure("操作失败");
        }
        if (newsDTO.getLables() != null && newsDTO.getLables().size() > 0) {
            for (NewsLable lable : newsDTO.getLables()) {
                newsMapper.addNewsAndLableRe(news.getId(), lable.getId());
            }
        }
        return JsonResponseTool.success(news.getId());
    }

    @Override
    public JsonResponse indexList(NewsQuery query) {
        return null;
    }

    @Override
    public JsonResponse List(NewsQuery query) {
        return null;
    }
}
