package com.Payloads.Request;

import org.testng.annotations.DataProvider;

import com.DataProviders.TestDataProvider;

public class SetData {
	
	TestDataProvider  ts=new TestDataProvider();
	
	@DataProvider(name="Location_data")
	public Object[][] createLocation() throws Exception {
		
		Object[][] data=ts.getData("./ExcelData/Data2.xlsx", "LocationData");
		return data;
		
	}
	
	@DataProvider(name="Character_data")
	public Object[][] createCharacter() throws Exception {
		
		Object[][] data=ts.getData("./ExcelData/Data2.xlsx", "CharacterData");
		return data;
		
	}
	
	@DataProvider(name="Episode_data")
	public Object[][] createEpisode() throws Exception {
		
		Object[][] data=ts.getData("./ExcelData/Data2.xlsx", "EpisodeData");
		return data;
		
	}
}
