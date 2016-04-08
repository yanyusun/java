package com.qs.jbpm;

import org.jbpm.kie.services.impl.*;
import org.jbpm.kie.services.impl.bpmn2.BPMN2DataServiceImpl;
import org.jbpm.services.api.model.DeployedUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.kie.api.runtime.manager.RuntimeManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;

/**
 * @author by pan on 16-3-29.
 */
public class TestTow {

    private BPMN2DataServiceImpl definitionService;

    private RuntimeDataServiceImpl runtimeDataService;

    private KModuleDeploymentService deploymentService;

    private ProcessServiceImpl processService;

    private UserTaskServiceImpl userTaskService;

    private MethodInvokingFactoryBean data;

    public BPMN2DataServiceImpl getDefinitionService() {
        return definitionService;
    }

    public void setDefinitionService(BPMN2DataServiceImpl definitionService) {
        this.definitionService = definitionService;
    }

    public RuntimeDataServiceImpl getRuntimeDataService() {
        return runtimeDataService;
    }

    public void setRuntimeDataService(RuntimeDataServiceImpl runtimeDataService) {
        this.runtimeDataService = runtimeDataService;
    }

    public KModuleDeploymentService getDeploymentService() {
        return deploymentService;
    }

    public void setDeploymentService(KModuleDeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    public ProcessServiceImpl getProcessService() {
        return processService;
    }

    public void setProcessService(ProcessServiceImpl processService) {
        this.processService = processService;
    }

    public UserTaskServiceImpl getUserTaskService() {
        return userTaskService;
    }

    public void setUserTaskService(UserTaskServiceImpl userTaskService) {
        this.userTaskService = userTaskService;
    }

    public MethodInvokingFactoryBean getData() {
        return data;
    }

    public void setData(MethodInvokingFactoryBean data) {
        this.data = data;
    }

    public void test() {
        // create deployment unit by giving GAV
        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit("agu.samples", "sample1", "1");
// deploy
        deploymentService.deploy(deploymentUnit);
// retrieve deployed unit
        DeployedUnit deployed = deploymentService.getDeployedUnit(deploymentUnit.getIdentifier());
// get runtime manager
        RuntimeManager manager = deployed.getRuntimeManager();
    }
}
