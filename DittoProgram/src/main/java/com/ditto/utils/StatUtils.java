package com.ditto.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class StatUtils {
    private static final Logger logger = LogManager.getLogger(StatUtils.class);
    
    public static void  stat(String plat, String... args) {
        try{
        if (args != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("STATIS ");
            sb.append(plat);
            sb.append(" ");
            for (String s : args) {
                sb.append(StatEncoder.encode(s));
                sb.append("-");
            }
            logger.info(sb.substring(0, sb.length() - 1));
        } else {
        	logger.info("STATIS null");
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static class StatEncoder {
    	public static String encode(String arg) {
    		if (arg != null) {
    			int length = arg.length();
    			StringBuilder sb = new StringBuilder(length);
    			for (int i = 0; i < length; i++) {
    				char c = arg.charAt(i);
    				if (c == '-') {
    					c = '_';
    				}
    				sb.append(c);
    			}

    			return sb.toString();
    		}

    		return null;
    	}
    }
    
    public static void MakeSureFileExit(String fileName) {
        File lFile = new File(fileName);
        if (!lFile.exists()) {
            //make parent dirs if necessary
            File dir = new File(lFile.getParent());
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                	logger.debug("successful to mkdirs:" + lFile.getParent());
                } else {
                	logger.error("fail to mkdirs:" + lFile.getParent());
                }
            }
            try {
                if (lFile.createNewFile()) {
                	logger.debug("Successful to create file " + fileName);
                } else {
                	logger.error("Error to create file " + fileName);
                }
            } catch (IOException ex) {
            	logger.error("Error to create file " + fileName + ",exception :", ex);
            }
        }
    }
}
