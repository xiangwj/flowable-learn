package org.api;

import java.util.List;

import javax.annotation.Resource;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartProcessTest {
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
	@Resource
	HistoryService historyService;
	
	
	@Test
	public void startProcess() {
		String processDefinitionId="leave1:1:98ad4415-5045-11ec-b9a9-94de800f45a5";
		ProcessInstance pi =runtimeService.startProcessInstanceById(processDefinitionId);
		System.out.println("ID:"+pi.getId()+"\tactivitiid:"+pi.getActivityId());
	}
	@Test
	public void queryPersonTask() {
		String assignee="a";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
		
	}
	@Test
	public void compeleteATask() {
		String assignee="a";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}
		
	}
	@Test
	public void compeleteBPersonTask() {
		String assignee="b";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}
		
	}
	@Test
	public void compeleteCPersonTask() {
		String assignee="c";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			taskService.complete(task.getId());
		}
		
	}
	/**
	 * 查询流程实例是否结束
	 */
	@Test
	public void compeletedInstance() {
		String processInstanceId ="";
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(pi==null) {
			System.out.println("当前实例已经结束");
		}else {
			System.out.println("当前实例正在运行");
		}
	}
	/**
	 * 历史
	 */
	@Test
	public void historyTask() {
		List<HistoricTaskInstance> htasks = historyService.createHistoricTaskInstanceQuery().taskAssignee("a").orderByTaskCreateTime().desc().list();
		for(HistoricTaskInstance task:htasks) {
			System.out.println(task.getId()+"\t"+task.getName()+"\t"+task.getProcessInstanceId());
		}
		htasks = historyService.createHistoricTaskInstanceQuery().taskAssignee("a").orderByTaskCreateTime().desc().list();
		for(HistoricTaskInstance task:htasks) {
			System.out.println(task.getId()+"\t"+task.getName()+"\t"+task.getProcessInstanceId());
		}		
	}
	/**
	 * 历史流程实例
	 */
	@Test
	public void historyInstance() {
		String defid="leave1:1:98ad4415-5045-11ec-b9a9-94de800f45a5";
		List<HistoricProcessInstance> instances = historyService.createHistoricProcessInstanceQuery().processDefinitionId(defid).list();
		for(HistoricProcessInstance instance:instances) {
			System.out.println(instance.getProcessDefinitionId()+"\t"+instance.getId());
		}
	}
}
