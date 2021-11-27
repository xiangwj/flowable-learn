package org.api.utils;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * Hello world!
 *
 */
public class CreateAllTables 
{
    public static void main( String[] args )
    {
        String jdbcURL="jdbc:mysql://192.168.137.120:3306/flowable01?characterEncoding=UTF-8&nullCatalogMeansCurrent=true";
        String jdbcDriver="com.mysql.jdbc.Driver";
        String jdbcUserName = "root";
        String jdbcPasswork ="rootroot";
        StandaloneProcessEngineConfiguration aloneProcessEngineConfiguration = new StandaloneProcessEngineConfiguration();
        aloneProcessEngineConfiguration.setJdbcUrl(jdbcURL);
        aloneProcessEngineConfiguration.setJdbcDriver(jdbcDriver);
        aloneProcessEngineConfiguration.setJdbcUsername(jdbcUserName);
        aloneProcessEngineConfiguration.setJdbcPassword(jdbcPasswork);
        aloneProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine =aloneProcessEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
        
    	//System.out.println( "Hello World!" );
    }
}
