package loltest;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerF{

	public Logger logger;
	
	private FileHandler fh;
	
	private String name;
	
	public LoggerF(String name){
		this.name = name;
		this.logger = Logger.getLogger(name);
		this.logger.setLevel(Level.ALL);
		setUp();
	}

    private void setUp(){
        try {  
        	
            // This block configure the logger with handler and formatter  
            fh = new FileHandler("C:/temp/test/MyLogFile.log");  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

            // the following statement is used to log any messages  
            logger.info("My first log");  

        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  

        logger.info("Hi How r u?"); 
    }


}
