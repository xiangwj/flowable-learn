package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.Test;

public class AssigneeTest extends FlowTest{
	@Test
	public void deploy() {
		String resourcename="chart/DynamicAssign.bpmn";
		repositoryService.createDeployment()
		.addClasspathResource(resourcename)
		.tenantId(TENANT_ID)
		.category("XIANGWENJUN")
		.key("dynamicAssign")
		.name("dynamicAssign")
		.deploy();
		
	}
	@Test
	public void run() {
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery()
				.processDefinitionTenantId(TENANT_ID)
				.processDefinitionCategory("XIANGWENJUN")
				.processDefinitionKey("dynamicAssign")
				.processDefinitionName("dynamicAssign")
				.latestVersion()
				.singleResult();
		Map<String,Object> variables = new HashMap<>();
		variables.put("userid","abcd");
		runtimeService.startProcessInstanceById(def.getId(),variables);		
	}
	@Test
	public void run01() {
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery()
				.processDefinitionTenantId(TENANT_ID)
				.processDefinitionCategory("XIANGWENJUN")
				.processDefinitionKey("dynamicAssign")
				.processDefinitionName("dynamicAssign")
				.latestVersion()
				.singleResult();
		List<Task> tasks =taskService.createTaskQuery().taskAssignee("efgh").list();
		for(Task task:tasks) {
			System.out.println("----"+task.getId());
			taskService.complete(task.getId());
		}
	}	
}
