package org.polytech.pfe.domego;

import org.polytech.pfe.domego.logger.MyLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@SpringBootApplication
public class DomegoApplication {


	public static void main(String[] args) {
		/*
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		try {
			MyLogger.setup();
			logger.info("Application Start");
		} catch (IOException | URISyntaxException e) {
			logger.warning("Problems with creating the log files");
			throw new RuntimeException("Problems with creating the log files");
		}*/



		SpringApplication.run(DomegoApplication.class, args);
	}

}
