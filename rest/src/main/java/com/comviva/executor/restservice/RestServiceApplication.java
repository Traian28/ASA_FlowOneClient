
package com.comviva.executor.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.comviva.executor.body.ServerExecutor;

@SpringBootApplication
@EnableAsync
public class RestServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RestServiceApplication.class, args);
		
		ServerExecutor serverExec = new ServerExecutor();

	}
}
