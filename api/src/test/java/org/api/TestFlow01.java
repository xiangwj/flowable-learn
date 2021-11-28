package org.api;

import java.util.List;

import javax.annotation.Resource;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestFlow01 {
	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	HistoryService historyService;
	/**
	 * 部署流程文件
	 */
	@Test
	public void deploy() {
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addClasspathResource("chart/leave.bpmn").deploy();
		System.out.println("success!");
		
	}
	/**
	 * 启动流程
	 */
	@Test
	public void intialProcess() {
		ProcessInstance processInstance =runtimeService.startProcessInstanceByKey("leave");
		System.out.println(processInstance);
	}
	/**
	 * 查询待办
	 */
	@Test
	public void retrieveWaitList() {
		List<Task> tasks =taskService.createTaskQuery().taskAssignee("a").list();
		System.out.println(taskService.createTaskQuery().taskAssignee("a").singleResult().getId());
		for(Task task:tasks) {
			System.out.println(task.getId()+"\t"+task.getName());
		}
	}
	/**
	 * 完成a代办
	 */
	@Test
	public void completApending() {
		taskService.complete(taskService.createTaskQuery().taskAssignee("a").singleResult().getId());
	}
	/*
	 * 完成b代办
	 */
	@Test
	public void completeBpending() {
		taskService.complete(taskService.createTaskQuery().taskAssignee("b").singleResult().getId());
	}
	/*
	 * 完成c代办
	 */
	@Test
	public void completeCpending() {
		taskService.complete(taskService.createTaskQuery().taskAssignee("c").singleResult().getId());
	}
	/**
	 * 查看已完成的任务
	 * 
	 */
	@Test
	public void listCompletedTaks() {
		String assignee="c";
		List<HistoricTaskInstance> taskIntances = historyService.createHistoricTaskInstanceQuery().finished().taskAssignee(assignee).list();
		for(HistoricTaskInstance taskIntance:taskIntances) {
			System.out.println(taskIntance.getId()+"\t"+taskIntance.getName());
		}
	}
}
