package com.dqys.business.service.dto.common;

import java.util.List;

/**
 * 资源删除接口dto
 * Created by yan on 17-1-12.
 *
 * @apiDefine SourceDelDTO
 * @apiParam {list} list SourceEditDto列表
 */
public class SourceDelDTO {
    public List<SourceEditDto> list;

    public List<SourceEditDto> getList() {
        return list;
    }

    public void setList(List<SourceEditDto> list) {
        this.list = list;
    }
}
