package org.polytech.pfe.domego.logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.logging.*;

public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    public static void setup() throws IOException, URISyntaxException {

        // get the global logger to configure it
        Logger logger = Logger.getGlobal();

        // suppress the logging output to the console
        /*Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }*/

        logger.setLevel(Level.INFO);
        URI uri = MyLogger.class.getResource("/public/").toURI();
        String mainPath = Paths.get(uri).toString();
        fileTxt = new FileHandler("Logging.txt");
        fileHTML = new FileHandler(mainPath + "/Logger.html");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // create an HTML formatter
        formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}