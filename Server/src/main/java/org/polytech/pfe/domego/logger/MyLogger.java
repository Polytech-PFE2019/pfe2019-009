package org.polytech.pfe.domego.logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.logging.*;

public class MyLogger {

    private MyLogger() {
    }

    public static void setup() throws IOException, URISyntaxException {

        // get the global logger to configure it
        Logger logger = Logger.getGlobal();

        logger.setLevel(Level.INFO);
        URI uri = MyLogger.class.getResource("/public/").toURI();
        String mainPath = Paths.get(uri).toString();
        FileHandler fileTxt = new FileHandler("Logging.txt");
        FileHandler fileHTML = new FileHandler(mainPath + "/Logger.html");

        // create a TXT formatter
        Formatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // create an HTML formatter
        Formatter formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}