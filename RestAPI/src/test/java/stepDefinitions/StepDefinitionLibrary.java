package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.automation.RestApi.Base;
import com.automation.RestApi.Resources;
import com.automation.utils.HelperMethods;
import com.automation.utils.ReadPropertiesFiles;
import com.automation.utils.TestDataExcel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class StepDefinitionLibrary extends Base{
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	
	public void createSpecs() throws IOException {
		if(requestSpec==null) {
			requestSpec=createRequestSpecForLibraryApi();
		}
		responseSpec=createResponseSpecForLibraryApi();
	}

	@Given("Details for adding a book")
	public void details_for_adding_a_book() throws IOException {
		createSpecs();
		HashMap<String, Object> payload= new HashMap<String, Object>();
		ArrayList<String> data= TestDataExcel.getData(ReadPropertiesFiles.getPropertyFromGlobalFile("excelDataFile"), "LibraryApi", "AddBook");
		payload.put("name", data.get(0));
		payload.put("isbn", data.get(1));
		payload.put("aisle", data.get(2));
		payload.put("author", data.get(3));
		requestSpec=given().spec(requestSpec).body(payload);
	}

	@When("user calls {string} request on {string}")
	public void user_calls_request_on(String requestType, String resource) {
		Resources api= Resources.valueOf(resource);
		response=requestSpec.when().post(api.getResource()).then().spec(responseSpec).extract().response();
	}

	@Then("status should be {string}")
	public void status_should_be(String code) {
		assertEquals(response.statusCode(), Integer.parseInt(code));
	   
	}

	@Then("{string} should be {string}")
	public void should_be(String key, String value) {
	    assertEquals(HelperMethods.getValue(response, key), value);
	}
}
