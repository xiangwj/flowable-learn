package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.Test;

public class ParallelGatewayTest extends FlowTest{
	@Test
	public void deploy() {
		String resourcename="chart/parallelGateway.bpmn";
		repositoryService.createDeployment()
		.addClasspathResource(resourcename)
		.tenantId(TENANT_ID)
		.category("shoppingstore")
		.name("parallelGateway")
		.key("parallelGateway")
		.deploy();
		
	}
	@Test
	public void run() {
		ProcessDefinition def =repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionTenantId(TENANT_ID)
		.processDefinitionCategory("shoppingstore")
		.processDefinitionKey("parallelGateway")
		.processDefinitionName("parallelGateway")
		.singleResult();
		
		String key="parallelGateway";
				Map<String,Object> variables = new HashMap<>();
		runtimeService.startProcessInstanceById(def.getId(), variables);
		
		String assignee="发货";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}

		assignee="收货";
		tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}
		assignee="付款";
		tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}
		assignee="收款";
		tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}		
	}

}
