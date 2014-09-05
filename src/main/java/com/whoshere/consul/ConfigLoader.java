package com.whoshere.consul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
	public static Config loadConfig(String filePath) throws IOException {
		File configFile = new File(filePath);
		
		if (!configFile.exists()) {
			throw new FileNotFoundException("cannot find file at " + filePath);
		}
		
		FileInputStream fis = new FileInputStream(configFile);
		StringWriter sw = new  StringWriter();
		IOUtils.copy(fis, sw);
		
		String configJson = sw.toString();
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(configJson, Config.class);
	}
}
