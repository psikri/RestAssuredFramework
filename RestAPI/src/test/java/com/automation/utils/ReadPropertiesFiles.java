package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;

public class ReadPropertiesFiles {
	
	public static String getPropertyFromGlobalFile(String propertyName) throws IOException {
		return getPropertyFromFile("global.properties", propertyName);
		
	}
	public static String getPropertyFromFile(String fileName, String propertyName) throws IOException {
		Properties p= new Properties();
		FileInputStream inp= new FileInputStream(new File(".\\propertiesFiles\\"+fileName));
		p.load(inp);
		return String.valueOf(p.get(propertyName));
	}

}
