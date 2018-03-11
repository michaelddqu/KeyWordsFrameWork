package util;

import org.apache.log4j.Logger;

public class Log {      // P328
	private static Logger Log = Logger.getLogger(Log.class.getName());
	
	public static void startTestCase(String testCaseName) {
		Log.info("------------------           \"" + testCaseName
				+ " \"EXECUTION STARTS        ---------------------------");
	}

	public static void endTestCase(String testCaseName) {
		Log.info("------------------           \"" + testCaseName
				+ " \"EXECUTION FINISHES        ---------------------------");	
	}
	    
	public static void info(String message) {
		Log.info(message);	
	}

	public static void error(String message) {
		Log.error(message);	
	}
	
	public static void debug(String message) {
		Log.debug(message);	
	}
}
