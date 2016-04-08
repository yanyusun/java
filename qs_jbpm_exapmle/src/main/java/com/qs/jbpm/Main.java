package com.qs.jbpm;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author by pan on 16-3-28.
 */
public class Main {

    public static void main(String[] args) {
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-jbpm.xml");
        Object o1 = applicationContext.getBean("runtimeManager");
        Object o2 = applicationContext.getBean("jbpmRuntimeDataService");
        Object o3 = applicationContext.getBean("jbpmDeploymentService");
        Object o4 = applicationContext.getBean("jbpmProcessService");
        Object o5 = applicationContext.getBean("jbpmUserTaskService");
        Object o6 = applicationContext.getBean("jbpmData");*/

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-jbpm.xml");

        MainTestTow mainTestTow = (MainTestTow) applicationContext.getBean("mainTest");
        mainTestTow.test();
        /*RuntimeManager manager = (RuntimeManager) applicationContext.getBean("runtimeManager");
        RuntimeEngine runtime = manager.getRuntimeEngine(ProcessInstanceIdContext.get());
        KieSession ksession = runtime.getKieSession();
        ProcessInstance processInstance = ksession.startProcess("com.sample.bpmn.hello");
        processInstance.getId();*/


        // first configure environment that will be used by RuntimeManager
        /*RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultInMemoryBuilder()
                .addAsset(ResourceFactory.newClassPathResource("bpmn/sample.bpmn"), ResourceType.BPMN2)
                .get();

        // next create RuntimeManager - in this case singleton strategy is chosen
        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);

        // then get RuntimeEngine out of manager - using empty context as singleton does not keep track
        // of runtime engine as there is only one
        RuntimeEngine runtime = manager.getRuntimeEngine(EmptyContext.get());

        // get KieSession from runtime runtimeEngine - already initialized with all handlers, listeners, etc that were configured
        // on the environment
        KieSession ksession = runtime.getKieSession();

        // add invocations to the process engine here,
        // e.g. ksession.startProcess(processId);

        // and last dispose the runtime engine
        //manager.disposeRuntimeEngine(runtime);


        *//*KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                .addResource(ResourceFactory.newClassPathResource("/bpmn/sample.bpmn"))
                .build();

        KieSession ksession = kieBase.newKieSession();
        ProcessInstance processInstance = ksession.startProcess("com.sample.bpmn.hello");*//*
        ProcessInstance processInstance = ksession.startProcess("com.sample.bpmn.hello");
        processInstance.getId();*/

    }
}
