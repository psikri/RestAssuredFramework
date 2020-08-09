package com.automation.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HelperMethods {

	public static Object getValue(Response response, String key) {
		String apiResponse= response.asString();
		JsonPath path= new JsonPath(apiResponse);		
		return path.get(key);
	}
}
