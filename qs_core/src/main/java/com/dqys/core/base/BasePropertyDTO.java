package com.dqys.core.base;

import java.util.Date;

/**
 * Created by Yvan on 16/7/11.
 * @apiDefine PropertyDTO
 * @apiSuccessExample {json} Property:
 * {
 *     id:1,
 *     createAt:"2011-11-11 11:11:11",
 *     state:0,
 *     name:"name",
 *     value:1
 * }
 */
public class BasePropertyDTO {

    private Integer id;
    private Date createAt;
    private Integer status;
    private String name;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
