package com.hst.shrp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hst.shrp")
@MapperScan(basePackages = "com.hst.shrp.dao")
public class ShrpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShrpApplication.class, args);
	}

}
