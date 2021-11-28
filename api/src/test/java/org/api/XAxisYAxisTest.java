package org.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.DiagramLayout;
import org.flowable.form.api.FormService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XAxisYAxisTest {
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
	public void test() {
		String processDefinitionId="leave1:6:b2cb9a02-500c-11ec-86e7-94de800f45a5";
		DiagramLayout layout = repositoryService.getProcessDiagramLayout(processDefinitionId);
		System.out.println(layout.getElements());
		System.out.println(layout);
	}
	@Test
	public void getBpmnModule() {
		String processDefinitionId="leave1:6:b2cb9a02-500c-11ec-86e7-94de800f45a5";
		BpmnModel model =repositoryService.getBpmnModel(processDefinitionId);
		Process process = model.getProcesses().get(0);
		FlowElement flowEvent = process.getFlowElement("startevent1");
		System.out.println(flowEvent.getId());
		System.out.println(flowEvent.getName());
		
		Map<String, FlowElement> eleMap =process.getFlowElementMap();
		for(Map.Entry<String, FlowElement> ele:eleMap.entrySet()) {
			System.out.println(ele.getKey()+"\t"+ele.getValue());
		}
	}
	@Test
	public void getFlowElementByType() {
		String processDefinitionId="leave1:6:b2cb9a02-500c-11ec-86e7-94de800f45a5";
		BpmnModel model =repositoryService.getBpmnModel(processDefinitionId);
		Process process = model.getProcesses().get(0);
		List<UserTask> list =process.findFlowElementsOfType(UserTask.class);
		for(UserTask task:list) {
			List<SequenceFlow> outgoings =task.getOutgoingFlows();
			for(SequenceFlow flow:outgoings) {
				System.out.println("\t"+flow);
			}
			System.out.println(task.getId()+"\t"+task.getName());
		}
		
	}
	@Test
	public void getFlowElementByType1() {
		String processDefinitionId="leave1:6:b2cb9a02-500c-11ec-86e7-94de800f45a5";
		BpmnModel model =repositoryService.getBpmnModel(processDefinitionId);
		Process process = model.getProcesses().get(0);
		List<SequenceFlow> list =process.findFlowElementsOfType(SequenceFlow.class);
		for(SequenceFlow seq:list) {
			System.out.println(seq.getId()+"\t"+seq.getSourceRef()+"==>"+seq.getTargetRef());
		}
		
	}	
}
