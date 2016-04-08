package com.qs.jbpm;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.*;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;

/**
 * @author by pan on 16-3-29.
 */
public class MainThree {

    public static void main(String[] args) {
        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultInMemoryBuilder()
                .addAsset(ResourceFactory.newClassPathResource("BPMN2-ScriptTask.bpmn2"), ResourceType.BPMN2)
                .get();

        // next create RuntimeManager - in this case singleton strategy is chosen
        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);

        // then get RuntimeEngine out of manager - using empty context as singleton does not keep track
        // of runtime engine as there is only one
        RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());

        // get KieSession from runtime runtimeEngine - already initialized with all handlers, listeners, etc that were configured
        // on the environment
        KieSession ksession = runtimeEngine.getKieSession();

        // add invocations to the process engine here,
        // e.g. ksession.startProcess(processId);

        // and last dispose the runtime engine
        manager.disposeRuntimeEngine(runtimeEngine);
    }
}
