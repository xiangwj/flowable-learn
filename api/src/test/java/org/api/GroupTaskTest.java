package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;
import org.junit.Test;

public class GroupTaskTest extends FlowTest{
	@Test
	public void deploy() {
		String resourcename="chart/grouptasksprocess.bpmn";
		repositoryService.createDeployment()
		.addClasspathResource(resourcename)
		.tenantId(TENANT_ID)
		.category("XIANGWENJUN")
		.key("taskgroup")
		.name("taskgroup")
		.deploy();
	}
	@Test
	
	public void run() {
		ProcessDefinition def = repositoryService.createProcessDefinitionQuery()
				.processDefinitionTenantId(TENANT_ID)
				.processDefinitionCategory("XIANGWENJUN")
				.processDefinitionKey("taskgroup")
				.processDefinitionName("taskgroup")
				.latestVersion()
				.singleResult();

		runtimeService.startProcessInstanceById(def.getId());	
		List<Task> tasks =taskService.createTaskQuery().taskCandidateUser("a").list();
		for(Task task:tasks) {
			System.out.println("----"+task.getId());
			List<IdentityLink> links =taskService.getIdentityLinksForTask(task.getId());
			for(IdentityLink link:links) {
				System.out.println(link.getUserId());
			}
		}
	}

}
