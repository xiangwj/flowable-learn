package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.task.api.Task;
import org.junit.Test;

public class Ch06Test extends FlowTest{
	/**
	 * 部署流程文档
	 */
	@Test
	public void deploy() {
		String rescourcename="chart/sequenceflow.bpmn";
		repositoryService.createDeployment()
		.addClasspathResource(rescourcename)
		.category("sequenceflow")
		.key("sequenceflow")
		.name("测试连线上的表达式")
		.tenantId(FlowTest.TENANT_ID)
		.deploy();
		
	}
	@Test
	public void delProcessDef() {
		repositoryService.deleteDeployment("a81cb1f1-50b8-11ec-be8b-94de800f45a5");
	}
	@Test
	public void runProcess() {
		String processdefid="sequenceflow:2:4eb5ed48-50b9-11ec-98cb-94de800f45a5";
		runtimeService.startProcessInstanceById(processdefid);
		String assignee="张三";
		List<Task> tasks =taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .orderByTaskCreateTime().desc()
				   .list();
		for(Task task:tasks) {
			Map<String,Object> map = new HashMap<>();
			map.put("message", "不重要");
			taskService.complete(task.getId(), map);
		}
		assignee="经理";
		tasks =taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .orderByTaskCreateTime().desc()
				   .list();
		for(Task task:tasks) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}
	@Test
	public void runProcessImport() {
		String processdefid="sequenceflow:2:4eb5ed48-50b9-11ec-98cb-94de800f45a5";
		runtimeService.startProcessInstanceById(processdefid);
		String assignee="张三";
		List<Task> tasks =taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .orderByTaskCreateTime().desc()
				   .list();
		for(Task task:tasks) {
			Map<String,Object> map = new HashMap<>();
			map.put("message", "重要");
			taskService.complete(task.getId(), map);
		}
		assignee="经理";
		tasks =taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .orderByTaskCreateTime().desc()
				   .list();
		for(Task task:tasks) {
			System.out.println(task.getId());
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}
	}	
}
