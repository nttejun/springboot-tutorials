package com.tutorial.batchtoy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing // 배치기능 활성
@SpringBootApplication
// 참고 : https://jojoldu.tistory.com/325
public class BatchtoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchtoyApplication.class, args);
	}

}