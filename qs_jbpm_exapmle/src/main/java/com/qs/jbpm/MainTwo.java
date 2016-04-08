package com.qs.jbpm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author by pan on 16-3-29.
 */
public class MainTwo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-jbpm-service.xml");
        TestTow testTow = applicationContext.getBean("testTow", TestTow.class);
        testTow.test();
    }
}
