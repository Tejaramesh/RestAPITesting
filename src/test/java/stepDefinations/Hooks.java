package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute the code only when placeid is null
		//Write a code that will give you place id
		stepDestination m=new stepDestination();
		
m.add_place_payload_with("Teja", "India", "Asia");
m.user_calls_with_http_request("AddPlaceAPI", "POST");
m.verify_place_id_created_maps_to_using("Teja", "getPlaceAPI");
	}

}
