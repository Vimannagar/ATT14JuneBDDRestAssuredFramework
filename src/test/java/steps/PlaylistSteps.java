package steps;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.spotify.pojo.Playlist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.DateAndTimeProvider;
import utils.ReadProperty;
import utils.SpecBuilders;

public class PlaylistSteps {
	
	RequestSpecification reqs;
	Response res;
	static String playlistId;
@Given("create playlist api payload")
public void create_playlist_api_payload() throws IOException {
	Playlist reqPlaylist = new Playlist();
	
	reqPlaylist.setName(ReadProperty.getPropData("playlistPrefix")+DateAndTimeProvider.getCurrentDateAndTime());
	reqPlaylist.setDescription(ReadProperty.getPropData("playlistDescription"));
	reqPlaylist.setPublic(false);
	
	 reqs = given(SpecBuilders.reqSpec())
	
	.body(reqPlaylist);
	
}

@When("user calls with POST http request for create playlist")
public void user_calls_with_post_http_request_for_create_playlist() {
	 res = reqs.when()
	.post("/users/313penavj7tfel24yubkojsvbygi/playlists");
}

@Then("API call executed with status code {int}")
public void api_call_executed_with_status_code(Integer int1) {
	Playlist playlistObject = res.as(Playlist.class);
	
	res.then()
    .spec(SpecBuilders.resSpec(int1));
	
	 playlistId = playlistObject.getId();
    
    
}

@Given("GET playlist api payload")
public void get_playlist_api_payload() {
reqs =  given(SpecBuilders.reqSpec())
   
   .pathParam("pId", playlistId);
}

@When("user calls with GET http request")
public void user_calls_with_get_http_request() {
  res = reqs.when()
   
   .get("playlists/{pId}");
}

@Then("API call executes with status code {int}")
public void api_call_executes_with_status_code(Integer int1) {
    res.then()
    .spec(SpecBuilders.resSpec(int1));
   Playlist playlist = res.as(Playlist.class);
   
  String playlistIdFromResponse = playlist.getId();
  
  assertEquals(playlistIdFromResponse, playlistId);
}

@Given("update playlist api payload")
public void update_playlist_api_payload() throws IOException {
   
	Playlist reqPlaylist = new Playlist();
	
	reqPlaylist.setName(ReadProperty.getPropData("updatedname")+DateAndTimeProvider.getCurrentDateAndTime());
	reqPlaylist.setDescription(ReadProperty.getPropData("playlistDescription"));
	reqPlaylist.setPublic(false);
	
	reqs = given(SpecBuilders.reqSpec())
	
	.body(reqPlaylist)
	
	.pathParam("pId", playlistId);
	
}

@When("user calls with PUT http request")
public void user_calls_with_put_http_request() {
	res = reqs.when()
	
	.put("playlists/{pId}");
}

@Then("API call should executes with status code {int}")
public void api_call_should_executes_with_status_code(Integer int1) {
    
	res.then()
	.statusCode(int1);
	
	
}



}
