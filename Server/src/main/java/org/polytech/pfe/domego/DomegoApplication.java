package org.polytech.pfe.domego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class DomegoApplication {

	public static void main(String[] args) {
		/*Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("MyLogFile.log");
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());

			// the following statement is used to log any messages
			logger.info("My first log");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("Hi How r u?");*/
		SpringApplication.run(DomegoApplication.class, args);
	}

}
