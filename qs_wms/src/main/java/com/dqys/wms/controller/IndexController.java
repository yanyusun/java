package com.dqys.wms.controller;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author by pan on 16-4-7.
 */
@Controller
public class IndexController implements ApplicationListener<ContextRefreshedEvent> {

    private static final String SYS_AUTH_URL_KEY = "sys_auth_url";

    @RequestMapping({"/", "/index"})
    public String index() throws Exception {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession httpSession) throws Exception {
        //model.asMap().put("authUrl", SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SYS_AUTH_URL_KEY).getPropertyValue());
        model.asMap().put("sessionId", httpSession.getId());
        model.asMap().put("authUrl", "http://localhost:9081");
        return "login";
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
