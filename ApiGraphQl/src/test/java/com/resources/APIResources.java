package com.resources;

public enum APIResources {

	createDataAPI("/gq/graphql"),
	getDataAPI("/gq/graphql"),
	deleteDataAPI("/gq/graphql");
	
	private String resource;
	
	APIResources(String resource){
		this.resource=resource;
	}
	

	public String getResource() {
		return resource;
	}
	
}
