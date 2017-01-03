package com.dqys.sale.service.util;

import com.dqys.core.utils.DateFormatTool;
import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.service.dto.news.NewsDtoY;
import com.dqys.sale.service.dto.news.RecommendDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yan on 16-12-29.
 */
public class NewsUtil {
    public static List<NewsDtoY> getNewsDtoYList(List<News> newsList){
        List<NewsDtoY> newsDtoYList = new ArrayList<>();
        for(News news : newsList){
            newsDtoYList.add(getNewsDtoY(news));
        }
        return newsDtoYList;
    }
    public static NewsDtoY getNewsDtoY(News news){
        NewsDtoY newsDtoY = new NewsDtoY();
        newsDtoY.setId(news.getId());
        newsDtoY.setTitle(news.getTitle());
        newsDtoY.setCover(news.getCover());
        Date openTime = news.getOpenTime();
        if(openTime!=null){
            if(DateFormatTool.getDayDistance(openTime,new Date())>(24*60*60*1000)){//时间超过了一天
                newsDtoY.setOpenTime(DateFormatTool.format(openTime,DateFormatTool.DATE_FORMAT_10_REG1));
            }else{
                int hours=openTime.getHours();
                if(hours>12){
                    newsDtoY.setOpenTime("下午 "+hours+" 时");
                }else{
                    newsDtoY.setOpenTime("上午 "+hours+" 时");
                }
            }
        }
        newsDtoY.setLables(news.getLables());
        newsDtoY.setDigest(news.getDigest());
        return newsDtoY;
    }

    /**
     * 得到推荐
     * @param newsList
     * @return
     */
    public static List<RecommendDto> getRecommendDto(List<News> newsList){
        List<RecommendDto> RecommendDtoList = new ArrayList<>();
        for(News news : newsList){
            RecommendDto recommendDto = new RecommendDto();
            recommendDto.setId(news.getId());
            recommendDto.setTitle(news.getTitle());
            RecommendDtoList.add(recommendDto);
        }
        return RecommendDtoList;
    }
}
