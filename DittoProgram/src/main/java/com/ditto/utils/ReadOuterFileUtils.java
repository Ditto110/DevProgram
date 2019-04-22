package com.ditto.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author SDT14325
 * created at 19:15 2019/4/18
 *
 * 读取配置文件
 */

public class ReadOuterFileUtils {

    private static final Logger LOGGER = LogManager.getLogger(ReadOuterFileUtils.class);

    public static String getProperty(String propName){
        try (
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("init.properties");
        ) {
            //对项目名称确认
//            boolean matches = path.matches(".*/appstore/.*");
            Properties prop = new Properties();
            prop.load(in);
            LOGGER.info("property:" + prop.getProperty(propName));
            return prop.getProperty(propName);
        }catch (Exception e){
            return null;
        }
    }
    public static void main(String[] args) {
        String taskId = ReadOuterFileUtils.getProperty("fmStatLogFilePath");
        System.out.println(taskId);
    }
}
