package org.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.form.api.FormService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PictureTest {
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
	public void getDeployNames() throws IOException {
		String img="";
		String deploymentid="79d6f073-4fef-11ec-81ee-94de800f45a5";
		List<String> resourcenames =repositoryService.getDeploymentResourceNames(deploymentid);
		for(String resourcename:resourcenames) {
			if(resourcename.indexOf("png")!=-1) {
				img = resourcename;
			}
			System.out.println(resourcename);
		}
		InputStream in = repositoryService.getResourceAsStream(deploymentid, img);
		FileUtils.copyInputStreamToFile(in, new File("e:\\1.png"));
		
	}
	@Test
	public void deleteDeployment() {
		String deploymentid="cbf00eb8-5022-11ec-99bc-94de800f45a5";
		repositoryService.deleteDeployment(deploymentid);
	}
	@Test
	public void getDefinationlist() {
		List<ProcessDefinition> lst =repositoryService.createProcessDefinitionQuery().list();
		for(ProcessDefinition def:lst) {
			System.out.println(def.getId()+"\t"+def.getCategory()+"\t"+def.getDeploymentId()+"\t"+def.getKey()+"\t"+def.getVersion());
		}
	}
}
