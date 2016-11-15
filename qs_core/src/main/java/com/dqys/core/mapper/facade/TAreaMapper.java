package com.dqys.core.mapper.facade;

import com.dqys.core.model.TArea;

import java.util.List;

public interface TAreaMapper {

    List<TArea> selectByUpper(Integer upper);

    List<TArea> selectAll();

    TArea getByName(String name);

    TArea get(Integer id);
}