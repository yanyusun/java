package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.News;
import com.dqys.sale.orm.pojo.NewsLable;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/24.
 */
public class NewsDTO {
    private News news;
    private List<NewsLable> lables;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public List<NewsLable> getLables() {
        return lables;
    }

    public void setLables(List<NewsLable> lables) {
        this.lables = lables;
    }
}
