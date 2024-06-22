package com.simple.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
/*@OpenAPIDefinition(info = @Info(
			title="The Bank App",
		description = "Backend Rest APIs",
		version = "v1.0",
		contact = @Contact(
				name="Some Time",
				email="some@gmail.com",
				url="https://github.com/sugam12/bank/compare/main...main"
		),
		license = @License(
				name = "The Bank App",
				url = "https://github.com/sugam12/bank/compare/main...main"
		)
))*/
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
