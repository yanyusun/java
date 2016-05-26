package com.dqys.wms.controller;

import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by pan on 16-5-26.
 */
@Component
public class InitComponent implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private SysPropertyTool sysPropertyTool;


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        sysPropertyTool.initProperty();
    }
}
