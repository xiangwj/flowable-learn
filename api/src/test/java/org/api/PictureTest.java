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
		repositoryService.deleteDeployment("1aeea44b-5022-11ec-92f2-94de800f45a5");
		repositoryService.deleteDeployment("2909a5a2-5001-11ec-9342-94de800f45a5");
		repositoryService.deleteDeployment("32043315-5004-11ec-97c1-94de800f45a5");
		repositoryService.deleteDeployment("4255ec2b-5022-11ec-a8f4-94de800f45a5");
		repositoryService.deleteDeployment("4d282dc2-5005-11ec-838c-94de800f45a5");
		repositoryService.deleteDeployment("566e7bf4-5011-11ec-85e7-94de800f45a5");
		repositoryService.deleteDeployment("59e6cf83-5010-11ec-a918-94de800f45a5");
		repositoryService.deleteDeployment("79d6f073-4fef-11ec-81ee-94de800f45a5");
		repositoryService.deleteDeployment("967cdaf9-500b-11ec-a608-94de800f45a5");
		repositoryService.deleteDeployment("a39436be-5009-11ec-86fe-94de800f45a5");
		repositoryService.deleteDeployment("b26cff3f-500c-11ec-86e7-94de800f45a5");
		repositoryService.deleteDeployment("b73e2f89-500f-11ec-a812-94de800f45a5");
		repositoryService.deleteDeployment("d3c29466-5021-11ec-8daf-94de800f45a5");
		repositoryService.deleteDeployment("da25468b-500f-11ec-870b-94de800f45a5");
	}
	@Test
	public void getDefinationlist() {
		List<ProcessDefinition> lst =repositoryService.createProcessDefinitionQuery().list();
		for(ProcessDefinition def:lst) {
			System.out.println(def.getId()+"\t"+def.getCategory()+"\t"+def.getDeploymentId()+"\t"+def.getKey()+"\t"+def.getVersion());
		}
	}
	@Test
	public void getDefinationlistbysql() {
		List<ProcessDefinition> lst =repositoryService.createNativeProcessDefinitionQuery().sql("select * from ACT_RE_DEPLOYMENT").list();
		for(ProcessDefinition def:lst) {
			System.out.println(def.getId()+"\t"+def.getCategory()+"\t"+def.getDeploymentId()+"\t"+def.getKey()+"\t"+def.getVersion());
		}
	}	
}
