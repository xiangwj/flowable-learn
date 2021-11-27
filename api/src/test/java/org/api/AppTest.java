package org.api;

import javax.annotation.Resource;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.form.api.FormService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	@Resource
	ProcessEngine processEngine;
	@Resource
	RepositoryService repositoryService;
	@Resource
	RuntimeService runtimeService;
	@Resource
	TaskService taskService;
	@Resource
	FormService formService;
	@Resource
	IdentityService identityService;
	@Resource
	ManagementService managementService;
	@Resource
	DynamicBpmnService dynamicBpmnService;
    @Test
    public void shouldAnswerWithTrue()
    {
    	
    	System.out.println(processEngine);
        System.out.println(formService);
        System.out.println(repositoryService);
        System.out.println(runtimeService);
        System.out.println(taskService);
        System.out.println(formService);
        System.out.println(identityService);
        System.out.println(managementService);
        System.out.println(dynamicBpmnService);
    }
}
