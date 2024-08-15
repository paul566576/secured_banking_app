package com.banking.secured_banking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
public class SecuredBankingAppApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SecuredBankingAppApplication.class, args);
	}
}
