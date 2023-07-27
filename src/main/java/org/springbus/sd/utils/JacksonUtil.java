package org.springbus.sd.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class JacksonUtil {
	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();
    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * bean、array、List、Map --> json
     * 
     * @return json string
	 */
    public static String toJsonString(Object obj) {
    	try {
			return getInstance().writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
        return null;
    }

    /**
     * string --> bean、Map、List(array)
     * 
     * @param clazz
     * @return obj
     * @throws Exception
     */
    public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
    	try {
			return getInstance().readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
    	return null;
    }

	/**
	 * string --> List<Bean>...
	 *
	 * @return
	 */
	public static <T> T jsonToObject(String jsonStr, Class<?> parametrized, Class<?>... parameterClasses) {
		try {
			JavaType javaType = getInstance().getTypeFactory().constructParametricType(parametrized, parameterClasses);
			return getInstance().readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
