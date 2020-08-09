package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;


import com.automation.RestApi.Base;
import com.automation.RestApi.Resources;
import com.automation.pojo.*;
import com.automation.utils.HelperMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Base  {
	PlacePayload place= new PlacePayload();
	static RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	static String place_id="";
	
	public void createSpecs() throws IOException {
		if(reqSpec==null) {
			reqSpec=createRequestSpecForAddPlaceApi();
		}
		resSpec=createResponseSpecForAddPlaceApi();
	}
	
	@Given("Payload for adding place with {string}, {string}, {string}")
	public void payload_for_adding_place_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		createSpecs();
		place=testData.addPlace(name, language, address);	    
	}
	
	@Given("Payload for deleting place")
	public void payload_for_deleting_place() throws IOException  {
		createSpecs();		
	}


	@When("User calls {string} API with {string} request")
	public void user_calls_api_with_request(String resourceApi, String reqType) {
		Resources api = Resources.valueOf(resourceApi);
		switch (reqType) {
		case "post":
			response = given().spec(reqSpec).body(place).when().post(api.getResource()).then().spec(resSpec).extract()
					.response();
			place_id=HelperMethods.getValue(response, "place_id").toString();
			break;

		case "get":
			response = given().spec(reqSpec).queryParam("place_id", place_id).when().get(api.getResource()).then()
					.spec(resSpec).extract().response();
			break;
		case "delete":
			response = given().spec(reqSpec).body(testData.deletePlace(place_id)).when().delete(api.getResource())
					.then().spec(resSpec).extract().response();
			break;
		}
		
	    
	}
	
	
	@Then("statusCode returned is {string}")
	public void status_code_returned_is(String code) {
	    assertEquals(response.getStatusCode(), Integer.parseInt(code));
	}
	
	
	@Then("{string} in response is {string}")
	public void in_response_is(String key, String value) {
		assertEquals(HelperMethods.getValue(response, key), value);	    	    
	}

	@Then("verify place_id created maps to {string} using {string} request")
	public void verify_place_id_created_maps_to_using_request(String name, String requestType) {		
		user_calls_api_with_request("GetPlace", "get");
		assertEquals(name, HelperMethods.getValue(response, "name").toString());
	}


}
