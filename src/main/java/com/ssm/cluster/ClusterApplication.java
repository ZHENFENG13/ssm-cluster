package com.ssm.cluster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssm.cluster.dao")
public class ClusterApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClusterApplication.class, args);
	}
}
