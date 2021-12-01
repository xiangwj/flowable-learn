package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ReceiveTaskTest extends FlowTest{
	@Test
	public void test() {
		String rescourcename="chart/receiveTask.bpmn";
		repositoryService.createDeployment()
		.addClasspathResource(rescourcename)
		.tenantId(TENANT_ID)
		.category("XIANGWENJUN")
		.key("receiveTask")
		.name("receiveTask")
		.deploy();
	}
	@Test
	public void run() {
		ProcessDefinition def =repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionTenantId(TENANT_ID)
		.processDefinitionCategory("XIANGWENJUN")
		.processDefinitionKey("receiveTask")
		.processDefinitionName("receiveTask")
		.singleResult();
		runtimeService.startProcessInstanceById(def.getId());
		
	}
	@Test
	public void run1() {
		List<ProcessInstance> ins =runtimeService.createProcessInstanceQuery().processDefinitionKey("receiveTask").processDefinitionCategory("XIANGWENJUN").processDefinitionName("receiveTask").processInstanceTenantId(TENANT_ID).list();
		for(ProcessInstance in:ins) {
			Map<String,Object> variables = new HashMap<>();
			variables.put("当日的销售额", 10000);
			List<Execution> exs =runtimeService.createExecutionQuery().processInstanceId(in.getId()).list();
			for(Execution ex:exs) {
				if(ex.getActivityId()!=null) {
					runtimeService.trigger(ex.getId(),variables);
				}
			}
		}
	}
	
}
