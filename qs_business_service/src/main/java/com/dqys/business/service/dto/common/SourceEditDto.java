package com.dqys.business.service.dto.common;

/**
 * 编辑接口dto
 * Created by yan on 17-1-12.
 *
 * @apiDefine SourceEditDto
 * @apiParam {string} [name] 文件名称
 * @apiParam {number} [type] 文件类型
 * @apiParam {string} [id] 文件id
 */
public class SourceEditDto {
    private String name;//文件名称
    private Integer type;//文件类型
    private Integer id;//文件id

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
