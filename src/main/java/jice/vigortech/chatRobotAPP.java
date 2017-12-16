package jice.vigortech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class chatRobotAPP {
	
	public static void main(String[] args) {
		SpringApplication.run(chatRobotAPP.class, args);
	}
}
