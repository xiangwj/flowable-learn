package org.api;

import java.util.List;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.Test;

public class ExcludeTest extends FlowTest{
	@Test
	public void deploy() {
	 String resourcename="chart/ExcludeGateway.bpmn";	
	 repositoryService.createDeployment()
	 .addClasspathResource(resourcename)
	 .tenantId(TENANT_ID)
	 .category("ExcludeGateway")
	 .key("ExcludeGateway")
	 .name("费用报销流程")
	 .deploy();
	}
	@Test
	public void deletedeploy() {
		runtimeService.deleteProcessInstance("abe7f080-50cb-11ec-984b-94de800f45a5","xxx");
		repositoryService.deleteDeployment("489d15df-50cb-11ec-99e0-94de800f45a5");
		
		
	}	
	@Test
	public void testLow300() {
		ProcessDefinition def =repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionTenantId(TENANT_ID)
		.processDefinitionCategory("ExcludeGateway")
		.processDefinitionKey("ExcludeGateway")
		.singleResult();
		
		runtimeService.startProcessInstanceById(def.getId());
		
		List<Task> tasks = taskService.createTaskQuery()
		.taskAssignee("a")
		.orderByTaskCreateTime().desc()
		.list();
		for(Task task:tasks) {
			System.out.println(task.getId()+"\t"+task.getName());
			taskService.setVariable(task.getId(),"money",300);
			taskService.complete(task.getId());
		}
		tasks = taskService.createTaskQuery()
				.taskAssignee("财务")
				.orderByTaskCreateTime().desc()
				.list();
				for(Task task:tasks) {
					System.out.println(task.getId()+"\t"+task.getName());
					//taskService.setVariable(task.getId(),"money",300);
					taskService.complete(task.getId());
				}		
	}
	@Test
	public void testLow800() {
		ProcessDefinition def =repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionTenantId(TENANT_ID)
		.processDefinitionCategory("ExcludeGateway")
		.processDefinitionKey("ExcludeGateway")
		.singleResult();
		
		runtimeService.startProcessInstanceById(def.getId());
		
		List<Task> tasks = taskService.createTaskQuery()
		.taskAssignee("a")
		.orderByTaskCreateTime().desc()
		.list();
		for(Task task:tasks) {
			System.out.println(task.getId()+"\t"+task.getName());
			taskService.setVariable(task.getId(),"money",800);
			taskService.complete(task.getId());
		}
		tasks = taskService.createTaskQuery()
				.taskAssignee("部门经理")
				.orderByTaskCreateTime().desc()
				.list();
				for(Task task:tasks) {
					System.out.println(task.getId()+"\t"+task.getName());
					//taskService.setVariable(task.getId(),"money",300);
					taskService.complete(task.getId());
				}		
	}	
	@Test
	public void testLow2000() {
		ProcessDefinition def =repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionTenantId(TENANT_ID)
		.processDefinitionCategory("ExcludeGateway")
		.processDefinitionKey("ExcludeGateway")
		.singleResult();
		
		runtimeService.startProcessInstanceById(def.getId());
		
		List<Task> tasks = taskService.createTaskQuery()
		.taskAssignee("a")
		.orderByTaskCreateTime().desc()
		.list();
		for(Task task:tasks) {
			System.out.println(task.getId()+"\t"+task.getName());
			taskService.setVariable(task.getId(),"money",2000);
			taskService.complete(task.getId());
		}
		tasks = taskService.createTaskQuery()
				.taskAssignee("总经理")
				.orderByTaskCreateTime().desc()
				.list();
				for(Task task:tasks) {
					System.out.println(task.getId()+"\t"+task.getName());
					//taskService.setVariable(task.getId(),"money",300);
					taskService.complete(task.getId());
				}		
	}	
}
