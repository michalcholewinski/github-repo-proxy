package com.cholewinskimichal.githubproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@SpringBootApplication
public class GithubRepoProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubRepoProxyApplication.class, args);
	}
}
