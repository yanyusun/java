package com.dqys.sale.service.dto.news;

/**
 * Created by yan on 16-12-29.
 */
public class RecommendDto {
    private Integer id;
    /**
     * 标题
     */
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
