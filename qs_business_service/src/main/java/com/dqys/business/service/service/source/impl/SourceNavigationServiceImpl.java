package com.dqys.business.service.service.source.impl;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.service.service.source.SourceNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 17-1-11.
 */
@Primary
@Repository
public class SourceNavigationServiceImpl implements SourceNavigationService {

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    @Override
    public Integer addNavigation() {

        return null;
    }
}
