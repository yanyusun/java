package com.qs.jbpm;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;

/**
 * @author by pan on 16-3-28.
 */
public class MainTestTow {

    private RuntimeManager runtimeManager;

    public RuntimeManager getRuntimeManager() {
        return runtimeManager;
    }

    public void setRuntimeManager(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }

    public void test() {
        RuntimeEngine runtime = runtimeManager.getRuntimeEngine(ProcessInstanceIdContext.get());
        KieSession ksession = runtime.getKieSession();
        ProcessInstance processInstance = ksession.startProcess("com.sample.bpmn.hello");
        long pid = processInstance.getId();
        TaskService taskService = runtime.getTaskService();
        //taskService.



    }
}
