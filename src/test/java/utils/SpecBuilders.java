package utils;

import java.util.HashMap;

import authmanager.TokenGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {
	@Step
	public static RequestSpecification reqSpec()
	{
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer "+TokenGenerator.getToken());
		return  new RequestSpecBuilder()

		.setBaseUri("https://api.spotify.com")
		.setBasePath("v1")
		.addHeaders(headers)
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL)
		.build();

	}
	
	@Step
	public static ResponseSpecification resSpec(int expectedStatusCode)
	{
	return new ResponseSpecBuilder()

		.expectStatusCode(expectedStatusCode)
		.expectContentType(ContentType.JSON)
		.log(LogDetail.ALL)
		 .build();
	}


}
