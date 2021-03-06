package org.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.api.bo.Person;
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
		String assignee="b";
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			System.out.println(task.getId());
			System.out.println(task.getName());
			System.out.println(taskService.getVariable(task.getId(), "???????????????"));
			System.out.println(taskService.getVariable(task.getId(), "???????????????"));
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
	 * ??????????????????????????????
	 */
	@Test
	public void compeletedInstance() {
		String processInstanceId ="";
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(pi==null) {
			System.out.println("????????????????????????");
		}else {
			System.out.println("????????????????????????");
		}
	}
	/**
	 * ??????
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
	 * ??????????????????
	 */
	@Test
	public void historyInstance() {
		String defid="leave1:1:98ad4415-5045-11ec-b9a9-94de800f45a5";
		List<HistoricProcessInstance> instances = historyService.createHistoricProcessInstanceQuery().processDefinitionId(defid).list();
		for(HistoricProcessInstance instance:instances) {
			System.out.println(instance.getProcessDefinitionId()+"\t"+instance.getId());
		}
	}
	/**
	 * ????????????????????????
	 */
	@Test
	public void setVariable() {
		String processDefinitionId="leave1:1:98ad4415-5045-11ec-b9a9-94de800f45a5";
		ProcessInstance pi =runtimeService.startProcessInstanceById(processDefinitionId);
		System.out.println("ID:"+pi.getId()+"\tactivitiid:"+pi.getActivityId());
		
		String assignee="a";
		Map<String,Object> variables = new HashMap<>();
		variables.put("???????????????", 3);
		variables.put("???????????????", "????????????");
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		for(Task task:tasks) {
			
			taskService.complete(task.getId(), variables);
		}
		
		assignee="b";
		tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		variables = new HashMap<>();
		for(Task task:tasks) {
			variables.put("????????????", "????????????");
			taskService.setVariable(task.getId(), "person", new Person(100,"?????????","??????"));
			System.out.println(task.getId());
			System.out.println(task.getName());
			System.out.println(taskService.getVariable(task.getId(), "???????????????"));
			System.out.println(taskService.getVariable(task.getId(), "???????????????"));
			taskService.complete(task.getId(), variables);
		}
		
		assignee="c";
		tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
		variables = new HashMap<>();
		for(Task task:tasks) {
			variables.put("????????????", "????????????");
			System.out.println(task.getId());
			System.out.println(task.getName());
			System.out.println(taskService.getVariable(task.getId(), "person"));
			taskService.setVariable(task.getId(),"??????","???????????????");
			
		}	
	}

}
