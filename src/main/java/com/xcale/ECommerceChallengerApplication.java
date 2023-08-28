package com.xcale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.xcale"
})
public class ECommerceChallengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceChallengerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void started() {
		log.info("---------------------------------------------------------------------------");
		log.info("---------------------------------------------------------------------------");
		log.info("---------------------------------------------------------------------------");
		log.info("----------------------------Started application----------------------------");
		log.info("---------------------------------------------------------------------------");
		log.info("---------------------------------------------------------------------------");
		log.info("---------------------------------------------------------------------------");
		log.info("---------------------------------------------------------------------------");
	}

}
