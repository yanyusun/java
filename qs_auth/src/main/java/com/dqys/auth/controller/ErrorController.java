package com.dqys.auth.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by pan on 16-4-22.
 */
@RestController
@RequestMapping("/err")
public class ErrorController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/err", produces = "text/plain;charset=utf-8")
    public String err(@RequestParam(required = false) String jsonPCallable) throws JsonProcessingException {
        JsonResponse jsonResponse = JsonResponseTool.exception();
        if(StringUtils.isNotBlank(jsonPCallable)) {
            StringBuffer resutStr = new StringBuffer(jsonPCallable);
            return resutStr.append("(").append(objectMapper.writeValueAsString(jsonResponse)).append(");").toString();
        }

        return objectMapper.writeValueAsString(jsonResponse);
    }
}
