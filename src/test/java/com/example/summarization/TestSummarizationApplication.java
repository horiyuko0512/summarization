package com.example.summarization;

import org.springframework.boot.SpringApplication;

public class TestSummarizationApplication {

	public static void main(String[] args) {
		SpringApplication.from(SummarizationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
