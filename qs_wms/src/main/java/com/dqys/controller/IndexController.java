package com.dqys.controller;

import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author by pan on 16-4-7.
 */
@Controller
public class IndexController implements ApplicationListener<ContextRefreshedEvent> {

    @RequestMapping({"/", "/index"})
    public String index() throws Exception {
        return "index";
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            try {
                SysPropertyTool.loadAllProperty();
                //fixme dev mode
                //AreaTool.loadArea();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
