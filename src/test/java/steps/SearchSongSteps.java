package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.SpecBuilders;
import io.cucumber.datatable.DataTable;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

public class SearchSongSteps {
	RequestSpecification reqs;
	Response response;
	String songNameValue;

@Given("Get a search song payload")
public void get_a_search_song_payload(DataTable dataTable) {
	List<Map<String, String>> data = dataTable.asMaps();
	
	Map<String, String> firstLine = data.get(0);
	
	 songNameValue = firstLine.get("songname");
	
	String typeValue = firstLine.get("type");
	
	String artistValue = firstLine.get("artist");
	
	
	
   reqs = given(SpecBuilders.reqSpec())
    
    .queryParams("q",songNameValue, "type", typeValue, "artist", artistValue );
}

@When("user calls with GET request")
public void user_calls_with_get_request() {
	 response = reqs.when()
	.get("search");
}

@Then("API executes with status code as {int}")
public void api_executes_with_status_code_as(Integer int1) {
   
	response.then()
	.spec(SpecBuilders.resSpec(int1))
	.assertThat()
	.body(Matchers.containsString(songNameValue+"ksjdfhkjsdfh"));
	
}

}
