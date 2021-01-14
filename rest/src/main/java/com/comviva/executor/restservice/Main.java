package com.comviva.executor.restservice;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.comviva.executor.body.ServerExecutor;
import com.comviva.executor.sysout2log.SystemOutToLog4j;

@SpringBootApplication
@EnableAsync
public class Main {

	public static void main(String[] args) {
	    
		PropertyConfigurator.configure("log4j.properties");
		
		SystemOutToLog4j.enableForPackage("main.java.com");
		
		SpringApplication.run(Main.class, args);
		
		//Add class that have println to convert them to log file

		//ServerExecutor serverExec = new ServerExecutor();
	}

}