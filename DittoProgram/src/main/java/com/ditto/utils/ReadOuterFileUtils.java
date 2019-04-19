package com.ditto.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author SDT14325
 * created at 19:15 2019/4/18
 *
 * 读取配置文件
 */

public class ReadOuterFileUtils {

    private static final Logger LOGGER = LogManager.getLogger(ReadOuterFileUtils.class);

    public static String getProperty(String name){
        try (
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml");
             ) {
            //对项目名称确认
//            boolean matches = path.matches(".*/appstore/.*");
            Yaml yaml = new Yaml();
            Map<String,Object> map = yaml.loadAs(in, Map.class);
            Object ymlValue = map.get(name);
            if (ymlValue == null){
                return null;
            }
            LOGGER.info("ymlValue:" + map.get(name));
            return map.get(name).toString();
        }catch (Exception e){
            return null;
        }
    }
    public static void main(String[] args) {
        String fmStatTaskId = ReadOuterFileUtils.getProperty("fmStatTaskId");
        LOGGER.info(fmStatTaskId);
    }
}
