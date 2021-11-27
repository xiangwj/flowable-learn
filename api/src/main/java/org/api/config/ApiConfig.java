package org.api.config;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.FormService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class ApiConfig {
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
	/*
	@Primary
	@Bean(name="processEngine")
	public ProcessEngine initProcessEngin() {
		ProcessEngineConfiguration cfg = null;
		try {
			cfg = new StandaloneProcessEngineConfiguration()
			.setJdbcUrl(url)
			.setJdbcUsername(username)
			.setJdbcPassword(password)
			.setJdbcDriver(driverClassName)
			.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
			.setMailServerHost("smtp.qq.com")
			.setMailServerPassword("asiainfo45")
			.setMailServerDefaultFrom("93128251@qq.com")
			.setMailServerUsername("93128251")
			.setActivityFontName("宋体")
			.setLabelFontName("宋体")
			.setAnnotationFontName("宋体");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ProcessEngine processEngine = cfg.buildProcessEngine();
		return processEngine;
	}
	
	@Bean(name="repositoryService")
	public RepositoryService  getRepositoryService(ProcessEngine processEngine) {
		return processEngine.getRepositoryService();
	}
	@Bean(name="runtimeService")
	public RuntimeService getRuntimeService(ProcessEngine processEngine) {
		return processEngine.getRuntimeService();
	}
	@Bean(name="taskService")
	public TaskService getTaskService(ProcessEngine processEngine) {
		return processEngine.getTaskService();
	}
	@Bean(name="formService")
	public FormService getFormService(ProcessEngine processEngine) {
		return processEngine.getFormService();
	}
	@Bean(name="identityService")
	public IdentityService getIdentityService(ProcessEngine processEngine) {
		return processEngine.getIdentityService();
	}
	@Bean(name="managementService")
	public ManagementService getManagementService(ProcessEngine processEngine) {
		return processEngine.getManagementService();
	}
	@Bean(name="dynamicBpmnService")
	public DynamicBpmnService getDynamicBpmnService(ProcessEngine processEngine) {
		return processEngine.getDynamicBpmnService();
	}*/
	
	
}
