package br.com.zuporange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrangeTalents04TemplatePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeTalents04TemplatePropostaApplication.class, args);
	}

}
