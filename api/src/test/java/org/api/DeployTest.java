package org.api;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeployTest {
	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	HistoryService historyService;	
	@Test
	public void deployByClassPath() {
		String resource ="chart/leave1.bpmn";
		repositoryService.createDeployment().category("请假").name("这是用addClasspathResource导入的请假流程").key("leave1").addClasspathResource(resource).deploy();
	}
	@Test
	public void deployByStream() {
		String resource ="chart/leave1.bpmn";
		InputStream  in = DeployTest.class.getClassLoader().getResourceAsStream(resource);
		repositoryService.createDeployment().addInputStream(resource, in).category("请假").name("这是用stream导入的请假流程").key("leave1").deploy();
		
	}
	/**
	 * 可以部署多个
	 */
	@Test
	public void deployByZipStream() {
		String resource ="chart/leave1.zip";
		InputStream in = DeployTest.class.getClassLoader().getResourceAsStream(resource);
		ZipInputStream ziStream = new ZipInputStream(in);
		repositoryService.createDeployment().addZipInputStream(ziStream).category("请假").name("这是用stream导入的请假流程").key("leave1").deploy();
		
	}
	/**
	 * 通过字符串方式deploy
	 */
	@Test
	public void deployByString() {
		String resource ="chart/leave1.zip";
		String text = IoUtil.readFileAsString(resource);
		repositoryService.createDeployment().addString(resource, text).category("leave2").name("这是请假流程2").key("leave2").deploy();
	}
	/**
	 * 通过bye数组方式获取
	 */
	@Test
	public void deployByByteArr() {
		String resource ="chart/leave1.bpmn";
		InputStream  in = DeployTest.class.getClassLoader().getResourceAsStream(resource);
		byte[] bytes = IoUtil.readInputStream(in, "xx");
		repositoryService.createDeployment().addBytes(resource, bytes).category("leave2").name("这是请假流程2").key("leave2").deploy();
		
	}
	
}
