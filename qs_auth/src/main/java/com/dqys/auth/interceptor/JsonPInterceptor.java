package com.dqys.auth.interceptor;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by pan on 16-4-21.
 */
//@Component
@Aspect
public class JsonPInterceptor implements HandlerInterceptor {

    /*@AfterReturning(pointcut = "execution(public * com.dqys.auth.controller.AuthController.userLogin(..))", returning = "retval")
    public void doJsonPResult(Object retval) {
        //Object args = jp.getArgs();
        System.out.println(retval);
    }*/

    //@Around("execution(* com.dqys.auth.controller.AuthController.userLogin(..))")
    //@Around("execution(* org.springframework.web.context.request.async.StandardServletAsyncWebRequest.dispatch(..))")
    public void doJsonPResult(ProceedingJoinPoint pjp) throws Throwable {
        Object args = pjp.getArgs();
        Object retVal = pjp.proceed();
        System.out.println(args);
    }


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //httpServletResponse.getWriter().append("ccc");
        //httpServletResponse.getWriter().close();
        //httpServletResponse.getOutputStream().write("ccccc".getBytes());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(StringUtils.isNotBlank(httpServletRequest.getParameter("jsonPCallable"))) {
            //httpServletResponse.getWriter().write("bbbbccccccccccccccccccccc");
            //httpServletResponse.reset();
            //httpServletResponse.getWriter().append(httpServletRequest.getParameter("jsonPCallable") + "(", 0, 0).append(");");
        }
        System.out.println("aaaa");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        /*//JsonResponse jsonResponse = (JsonResponse) o;
        jsonResponse.setMsg("aaa");*/
        System.out.println("bbb");
    }

    /*@Pointcut("execution(public * com.dqys.auth.controller.AuthController.userLogin(..))")
    public void pointCut() {

    }*/
}
