package stepDefinitions;

import java.io.IOException;


import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeAction() throws IOException {
		if(StepDefinition.place_id.isEmpty()) {
			StepDefinition step= new StepDefinition();
			step.createSpecs();
			step.payload_for_adding_place_with("hooks test", "java", "eclipse");
			step.user_calls_api_with_request("AddPlace", "post");
			step.status_code_returned_is("200");
			step.verify_place_id_created_maps_to_using_request("hooks test", "GetPlace");
			
		}
	}
}
