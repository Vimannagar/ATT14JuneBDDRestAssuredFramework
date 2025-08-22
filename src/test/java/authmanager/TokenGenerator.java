package authmanager;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ReadProperty;

import static io.restassured.RestAssured.*;

public class TokenGenerator {
	public static String access_token;
	public static Instant expiry_time;
	
	public static String getToken()
	{
		
		System.out.println("access token is :" +access_token);
		try {
		if(access_token == null || Instant.now().isAfter(expiry_time))
		{
			System.out.println("Renewing the token ....");
			
			Response resp = renewToken();
			
			JsonPath jp = resp.jsonPath();
			
			access_token = jp.getString("access_token");
			
			int expiryTimeInSeconds = jp.getInt("expires_in");
			
			expiry_time = Instant.now().plusSeconds(expiryTimeInSeconds-300);
		}
		else
		{
			System.out.println("Token is good to use and need not be generated");
		}
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to generate access token");
		}
		
		return access_token;
	}

	
	
	public static Response renewToken() throws IOException
	{
		HashMap<String, String> param = new HashMap<String, String>();
		
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", ReadProperty.getPropData("refresh_token"));
		param.put("client_id", ReadProperty.getPropData("client_id"));
		param.put("client_secret", ReadProperty.getPropData("client_secret"));
	
		
		baseURI = "https://accounts.spotify.com";
		
	Response resp = given()
		
		.header("Content-Type", "application/x-www-form-urlencoded")
		
		.formParams(param)
		
		.log().all()
		
		.when()
		
		.post("/api/token")
		
		.then()
		
		.log().all()
		
		.extract()
		
		.response();
		
		
		if(resp.statusCode()!=200)
		{
			throw new RuntimeException("Token generation API call failed ...");
		}
		
		return resp;
		
	}

}
