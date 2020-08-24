package com.automation.RestApi;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.automation.utils.ReadPropertiesFiles;
import com.automation.utils.TestDataBuild;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Base {
	
	protected TestDataBuild testData=new TestDataBuild();
	
	public RequestSpecification createRequestSpecForAddPlaceApi() throws IOException {		
		PrintStream stream=new PrintStream(new File(ReadPropertiesFiles.getPropertyFromGlobalFile("loggerFileAddPlace")));
		return new RequestSpecBuilder().addQueryParam("key", "qaclick123").setAccept(ContentType.JSON)
				.setBaseUri(ReadPropertiesFiles.getPropertyFromGlobalFile("baseURIAddPlaceApi"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		
	}
	
	public ResponseSpecification createResponseSpecForAddPlaceApi() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
	}
	
	public RequestSpecification createRequestSpecForLibraryApi() throws IOException {		
		PrintStream stream=new PrintStream(new File(ReadPropertiesFiles.getPropertyFromGlobalFile("loggerFileLibraryApi")));
		return new RequestSpecBuilder().setAccept(ContentType.JSON)
				.setBaseUri(ReadPropertiesFiles.getPropertyFromGlobalFile("baseURILibraryApi"))
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();		
	}
	
	public ResponseSpecification createResponseSpecForLibraryApi() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
	}
	

	
	 

}
