package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.utils;

import static org.junit.Assert.*;

public class stepDestination extends utils {
	RequestSpecification resp;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data=new TestDataBuild();
static String place_id;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
	resp=given().spec(requestspecification()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method)  {
	APIresources resourceAPI = APIresources.valueOf(resource);
	System.out.println(resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response=resp.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response=resp.when().get(resourceAPI.getResource());
		
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {
       
		assertEquals(getJsonPath(response,keyvalue),Expectedvalue);
        }

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		 place_id=getJsonPath(response,"place_id");
		resp=given().spec(requestspecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
@Given("Delete place payload")
public void delete_place_payload() throws IOException {
   resp=given().spec(requestspecification()).body(data.deletePayload(place_id));
}
}
