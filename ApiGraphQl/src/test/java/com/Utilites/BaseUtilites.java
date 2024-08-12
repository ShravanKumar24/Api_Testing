package com.Utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseUtilites {

	RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			FileOutputStream out = new FileOutputStream(getLoggers());
			PrintStream log = new PrintStream(out);

			req = new RequestSpecBuilder().setBaseUri(getGlobalProp("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public String getGlobalProp(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/com/resources/Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public String getJSONString(Response res, String key) {

		JsonPath js = new JsonPath(res.asString());
		return js.get(key).toString();
	}

	public int getJSONInt(Response res, String key) {

		JsonPath js = new JsonPath(res.asString());
		return Integer.parseInt(js.get(key).toString());
	}
	
	public double getJSONDouble(Response res, String key) {

		JsonPath js = new JsonPath(res.asString());
		return Double.parseDouble(js.get(key).toString());
	}
	
	public static String getLoggers() {
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		String timestamp=dateFormat.format(new Date());
		String logFileName="./logs/Logging "+timestamp+".txt";
		return logFileName;
	}
	
}
