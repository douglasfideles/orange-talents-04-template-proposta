package br.com.zuporange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class OrangeTalents04TemplatePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeTalents04TemplatePropostaApplication.class, args);
	}

}
