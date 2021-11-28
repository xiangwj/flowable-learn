package org.api;

import javax.annotation.Resource;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
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
}
