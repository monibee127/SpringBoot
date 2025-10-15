package com.payment_management.payment_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PaymentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentManagementApplication.class, args);
	}

}
