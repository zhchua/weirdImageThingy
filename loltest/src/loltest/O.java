package loltest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class O{

	public Logger L;
	
	private String name;
	
	private String runDT;
	
	public O(String name){
		this.name = name;
		this.L = Logger.getLogger(name);
		this.L.setLevel(Level.ALL);
		generateRunDT();
		setUp();
		this.L.info("O : " + name);
	}

	/** Turns an integer of 0 - 99 into a two-character string
	 *  
	 * <pre>Example: int 5 -> "05", int 87 -> "87" </pre>
	 * 
	 * @param num
	 * @return
	 */
	private String getTwoDgtStr(int num){
		if(num < 10){
			return "0" + String.valueOf(num);
		}
		else return String.valueOf(num);
	}
	
	private void generateRunDT(){
		LocalDateTime now = LocalDateTime.now();
		this.runDT = String.valueOf(now.getYear()) 
				+ getTwoDgtStr(now.getMonthValue())
				+ getTwoDgtStr(now.getDayOfMonth())
				+ getTwoDgtStr(now.getHour())
				+ getTwoDgtStr(now.getMinute())
				+ getTwoDgtStr(now.getSecond());
	}
	
	private String getRunDT(){
		if(this.runDT == null){
			generateRunDT();
		}
		return this.runDT;
	}
	
    private void setUp(){
        try {  
            // This block configure the logger with handler and formatter  
            FileHandler fh = new FileHandler(System.getProperty("user.dir") 
            		+ File.separator + "logs" + File.separator
            		+ this.getRunDT() + ".log");  
            this.L.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

            // the following statement is used to log any messages  
            this.L.info("O.setUp complete");  

        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
    }
    
    /** Executes System.out.println with given string.
     * 
     * @param string
     */
    public void P(String string){
    	this.L.info("O.P : " + string);
    	System.out.println(string);
    }
}
