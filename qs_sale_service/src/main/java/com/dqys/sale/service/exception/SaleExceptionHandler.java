package com.dqys.sale.service.exception;

import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.sale.service.exception.bean.base.SaleException;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by yan on 16-7-16.
 */
public class SaleExceptionHandler implements HandlerExceptionResolver {
    /**
     * 未知错误名称
     */
    private static String UNKOWN_EXCEPTION_NAME = "UNKOWN_EXCEPTION_NAME";
    /**
     * 未知异常错误code
     */
    public static int UNKOWN_ERROR = 9999;

//    @Override
//    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//        Map<String, Object> model = new HashMap<String, Object>();
//        String msg = e.getMessage();
//        model.put("exMsg", msg);
//        String name = UNKOWN_EXCEPTION_NAME;
//        int code = UNKOWN_ERROR;
//        // 根据不同错误转向不同页面
//        if (e instanceof BussinessException) {
//            name = ((BussinessException) e).getExceptionName();
//            code = ((BussinessException) e).getExceptionCode();
//        }
//
//
//
//
//        model.put("exName", name);
//        model.put("exCode", code);
//        model.put("code", ResponseCodeEnum.BUSINESS_ERROR.getValue());
//        model.put("msg", ResponseCodeEnum.BUSINESS_ERROR.getText());
//        model.put("exception" ,e);
//        MappingJackson2JsonView jw = new MappingJackson2JsonView();
//        LogManager.getLogger("businessErrorAsync").error("name:" + name + ",code:" + code + ",msg:" + msg+"exception:"+e);
//        jw.setAttributesMap(model);
//        return new ModelAndView(jw);
//    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exMsg", ResponseCodeEnum.BUSINESS_ERROR.getText());
        model.put("exCode", ResponseCodeEnum.BUSINESS_ERROR.getValue());
        String msg = e.getMessage();
        String name = UNKOWN_EXCEPTION_NAME;
        int code = UNKOWN_ERROR;
        if (e instanceof SaleException) {
            model.put("msg",  e.getMessage());
            name = ((SaleException) e).getExceptionName();
            code = ((SaleException) e).getExceptionCode();
        }else{
            model.put("msg", "业务操作产生的未做处理异常");
        }
        model.put("name",name);
        model.put("code", code);
        model.put("exception" ,e);
        model.put("exceptionMsg",e.getMessage());
        MappingJackson2JsonView jw = new MappingJackson2JsonView();
        LogManager.getLogger("businessErrorAsync").error("name:" + name + ",code:" + code + ",msg:" + msg + "exception:" + e);
        jw.setAttributesMap(model);
        return new ModelAndView(jw);
    }
}
