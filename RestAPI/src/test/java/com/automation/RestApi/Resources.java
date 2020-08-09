package com.automation.RestApi;

public enum Resources {	
	AddPlace("/maps/api/place/add/json"),
	DeletePlace("/maps/api/place/delete/json"),
	GetPlace("/maps/api/place/get/json"),
	UpdatePlace("/maps/api/place/update/json");
	
	private String resourcePath;
	
	Resources(String path){
		resourcePath=path;
	}
	
	public String getResource() {
		return resourcePath;
	}

}
