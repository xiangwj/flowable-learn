package org.api;

import java.util.List;

import javax.annotation.Resource;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.form.api.FormService;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProgramMoudle {
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
	public void testProgramGram() {
		BpmnModel bpmnModel = generateBpmn();
		repositoryService.createDeployment().addBpmnModel("ch5/leave5", bpmnModel).category("prodeploy").name("ch5/dev5").key("proleave").deploy();
		
	}
	@Test
	public void testValidat() {
		ProcessValidatorFactory pf = new ProcessValidatorFactory();
		ProcessValidator pv = pf.createDefaultProcessValidator();
		List<ValidationError> errors =pv.validate(generateBpmn());
		System.out.println(errors.size());
		
		
	}
	private BpmnModel generateBpmn() {
		BpmnModel bpmnModel = new BpmnModel();
		
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startid");
		startEvent.setName("开始");
		
		UserTask u1 = generateUserTask("a","u1","提交申请");
		UserTask u2 = generateUserTask("b","u2","部门经理审批");
		UserTask u3 = generateUserTask("c","u3","总经理审批");

		EndEvent endEvent = new EndEvent();
	    endEvent.setId("endid");
	    endEvent.setName("end");

	    
		SequenceFlow s1 = generateFlow("s1","开始==>u1","startid","u1");
		SequenceFlow s2 = generateFlow("s2","u1==>u2","u1","u2");
		SequenceFlow s3 = generateFlow("s3","u2==>u3","u2","u3");
		SequenceFlow s4 = generateFlow("s3","u3==>end","u3","endid");
		
		Process process = new Process();
		process.setId("leave");
		process.setName("请假刘晨");
		
		process.addFlowElement(s1);
		process.addFlowElement(s2);
		process.addFlowElement(s3);
		process.addFlowElement(s4);
		
		process.addFlowElement(startEvent);
		process.addFlowElement(endEvent);
		
		process.addFlowElement(u1);
		process.addFlowElement(u2);
		process.addFlowElement(u3);
		
		bpmnModel.addProcess(process);
		return bpmnModel;
	}
	public SequenceFlow generateFlow(String id,String name, String source,String target) {
		SequenceFlow se = new SequenceFlow();
		se.setId(id);
		se.setName(name);
		se.setSourceRef(source);
		se.setTargetRef(target);
		return se;
	}
	public UserTask generateUserTask(String assignee,String taskid,String taskname) {
		UserTask userTask = new UserTask();
		userTask.setAssignee(assignee);
		userTask.setId(taskid);
		userTask.setName(taskname);
		return userTask;
	}
	
}
