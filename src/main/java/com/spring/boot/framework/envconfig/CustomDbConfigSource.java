package com.spring.boot.framework.envconfig;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDbConfigSource implements ConfigSource {

	private static final int ENV_CONFIG_ORDER = 500;
	private static final String PROPERTIES_FILE_PATH = "/envconfig.properties";
	Properties properties = null;
	private static final Logger logger = LoggerFactory.getLogger(CustomDbConfigSource.class);
	
	public CustomDbConfigSource() throws Throwable {
		logger.info("initializing properties config sourcr");
		try {
			InputStream is = this.getClass().getResourceAsStream(PROPERTIES_FILE_PATH);
			Throwable var2 = null;
			try {
				if( null != is) {
					this.properties = new Properties();
					this.properties.load(is);
				}
			}catch(Throwable ex) {
			  var2 = ex;
			  throw var2;
		   }finally {
				if(null != is) {
					if(null != var2) {
						try {
							is.close();
						}catch(Throwable th) {
							var2.addSuppressed(th);
						}
					}
				}
		   }
	}catch(Exception var14) {
		var14.printStackTrace();
		logger.error("Initialization of envconfig properties failed : " + var14.getMessage());
	}
	
		
	}
	
	@Override
	public String getName() {
		return CustomDbConfigSource.class.getSimpleName();
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> returnValue = new HashMap<>();
		if(this.properties != null) {
			Set<Entry<Object, Object>> propertiesSet = this.properties.entrySet();
			propertiesSet.stream().forEach((entry) -> {
				String value = (String) returnValue.put((String)entry.getKey(), (String)entry.getValue());
			}); 
		}
		return returnValue;
	}

	@Override
	public String getValue(String propertyName) {
		if(this.properties == null) {
		return null;
		}else {
			String value = (String)this.properties.get(propertyName);
			if(value != null) {
				logger.info("Config properties returning > {} ", propertyName, value);
			}
			while(value != null && value.startsWith("${") && value.endsWith("}")) {
				value = value.substring(2, value.length() -1 );
				if(value != null) {
					value = (String)ConfigProvider.getConfig().getValue(value, String.class);
				}
			}
			return value;
		}
		//return null;
	}

	public Set<String> getPropertyNames() {
		Set<String> returnValue = new HashSet<String>();
		if(this.properties != null) {
			Set<Object> propertiesKeySet = this.properties.keySet();
			propertiesKeySet.stream().forEach(key -> {
				returnValue.add((String)key);
			});
		}
		return returnValue;
	}
	
	public int getOrdinal() {
		return ENV_CONFIG_ORDER;
	}
}
