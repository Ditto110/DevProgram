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

    public static String getProperty(String prop){
        try (
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml");
             ) {
            //对项目名称确认
//            boolean matches = path.matches(".*/appstore/.*");
            Yaml yaml = new Yaml();
            Map<String,Object> map = yaml.loadAs(in, Map.class);
            Object ymlValue = map.get("fmStatCommon");
            if (ymlValue == null){
                return null;
            }
            Map<String, Object> param = (Map<String, Object>) ymlValue;
            Object value = param.get(prop);
            if (value == null){
                return null;
            }
            LOGGER.info("ymlValue:" + value);
            return value.toString();
        }catch (Exception e){
            return null;
        }
    }
    public static void main(String[] args) {
        String taskId = ReadOuterFileUtils.getProperty("taskId");
        System.out.println(taskId);
    }
}
