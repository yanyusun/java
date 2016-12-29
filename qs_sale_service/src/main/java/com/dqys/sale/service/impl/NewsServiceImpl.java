package com.dqys.sale.service.impl;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.flowbusiness.service.constant.saleBusiness.NewsAnnounceBusiness;
import com.dqys.sale.orm.mapper.NewsMapper;
import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.orm.query.NewsQueryY;
import com.dqys.sale.service.constant.NewsTypeEnum;
import com.dqys.sale.service.dto.NewsDTO;
import com.dqys.sale.service.dto.news.NewsDtoY;
import com.dqys.sale.service.dto.news.RecommendDto;
import com.dqys.sale.service.dto.news.SecondLevelDto;
import com.dqys.sale.service.dto.news.SecondLevelDtoList;
import com.dqys.sale.service.facade.NewsService;
import com.dqys.sale.service.util.NewsUtil;
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
        getNewsDTOMkf(newses, dtos);
//        for (News entity : newses) {
//            NewsDTO dto = new NewsDTO();
//            dto.setNews(entity);
//            dto.setLables(newsMapper.selectLableByNewId(entity.getId()));
//            dtos.add(dto);
//        }
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
        List<News> newses = newsMapper.list(query);
        List<NewsDTO> dtos = new ArrayList<>();
        getNewsDTOMkf(newses, dtos);
        Map map = new HashMap<>();
        map.put("newsList", dtos);
        return JsonResponseTool.success(map);
    }
    
    //// TODO: 16-12-29 整体加缓存处理
    @Override
    public SecondLevelDtoList sencondLevelPage() {
        SecondLevelDtoList sencondLevelPage = new SecondLevelDtoList();
        sencondLevelPage.setInfomation(getSecondLevelDto(NewsTypeEnum.infomation.getValue()));
        sencondLevelPage.setDynamic(getSecondLevelDto(NewsTypeEnum.dynamic.getValue()));
        sencondLevelPage.setBusiness(getSecondLevelDto(NewsTypeEnum.business.getValue()));
        return sencondLevelPage;
    }

    private SecondLevelDto getSecondLevelDto(int type) {
        SecondLevelDto secondLevelDto = new SecondLevelDto();
        //查询head
        secondLevelDto.setHeadList(NewsUtil.getNewsDtoYList(getSecondHeadNews(type)));
        //查询推荐
        secondLevelDto.setRecommendDtoList(getRecommendDtoList(type));
        //查询得到初始化新闻list
        secondLevelDto.setInitNewsDtoYList(NewsUtil.getNewsDtoYList(getinitNewsList(type)));
        return secondLevelDto;
    }

    /**
     * 得到二级头条
     * @param type
     * @return
     */
    private List<News> getSecondHeadNews(int type) {
        NewsQueryY query = new NewsQueryY();
        query.setIsHeadline(1);//头条
        query.setStatus(NewsAnnounceBusiness.getOkLevel().getLevel());
        query.setType(type);
        query.setPageSize(2);//默认两条
        query.setIsPaging(true);
        return newsMapper.listWithOutLables(query);
    }
    /**
     * 得到初始化信息
     * @param type
     * @return
     */
    private List<News> getinitNewsList(int type) {
        return getinitNewsList(type,1);
    }

    /**
     * 得到二级列表信息
     * @param type
     * @return
     */
    private List<News> getinitNewsList(int type,int page) {
        NewsQueryY query = new NewsQueryY();
        query.setStatus(NewsAnnounceBusiness.getOkLevel().getLevel());
        query.setType(type);
        query.setIsPaging(true);
        query.setStartPageNum(page);
        query.setPageSize(3);//默认三条
        return newsMapper.listY(query);
    }

    /**
     * 得到推荐
     * @param type
     * @return
     */
    private List<RecommendDto> getRecommendDtoList(int type){
        NewsQueryY query = new NewsQueryY();
        query.setType(type);
        query.setStatus(NewsAnnounceBusiness.getOkLevel().getLevel());
        query.setIsRefer(1);
        query.setIsPaging(true);
        query.setStartPageNum(1);
        query.setPageSize(3);
        List<News> newsList= newsMapper.listWithOutLables(query);
        return NewsUtil.getRecommendDto(newsList);
    }

    @Override
    public List<NewsDtoY> sencondNewsList(int type,int page) {
        return NewsUtil.getNewsDtoYList(getinitNewsList(type,page));
    }

    private void getNewsDTOMkf(List<News> newses, List<NewsDTO> dtos) {
        for (News entity : newses) {
            NewsDTO dto = new NewsDTO();
            dto.setNews(entity);
            dto.setLables(entity.getLables());
            dtos.add(dto);
        }
    }

  
    @Override
    public JsonResponse List(NewsQuery query) {
        return null;
    }


}
