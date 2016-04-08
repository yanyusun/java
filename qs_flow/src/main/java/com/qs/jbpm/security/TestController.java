package com.qs.jbpm.security;

import org.jbpm.services.api.DefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by pan on 16-4-1.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private DefinitionService jbpmDefinitionService;

    @RequestMapping("index")
    public String test() {
        jbpmDefinitionService.buildProcessDefinition("", "", null, false);
        return "success";
    }
}
