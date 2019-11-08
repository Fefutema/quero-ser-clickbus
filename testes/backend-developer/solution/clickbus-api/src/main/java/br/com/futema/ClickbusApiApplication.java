package br.com.futema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("br.com.futema")
@SpringBootApplication
//@SpringCloudApplication
public class ClickbusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickbusApiApplication.class, args);
	}

}
