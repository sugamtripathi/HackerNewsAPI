package pro.question.questionPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import pro.question.questionPro.client.HackerNewsApiClient;



@SpringBootApplication
@EnableCaching
@EnableFeignClients(clients = {HackerNewsApiClient.class})
@ComponentScan(basePackages = "pro.question.questionPro.*")
public class QuestionProApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionProApplication.class, args);
	}

}
