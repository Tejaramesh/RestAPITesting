Feature: Validating place api's

@AddPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
 Given Add place payload with "<name>" "<language>" "<address>"
 When user calls "AddPlaceAPI" with "POST" http request
 Then the API call is success with status code 200
 And "status" in response body is "OK"
 And "scope" in response body is "APP"
 And verify place_Id created maps to "<name>" using "getPlaceAPI"

 Examples:
 | name  | language | address |
 |AAhouse| English  | World cross center |
 

@DeletePlace @Regression
 Scenario: verify if Delete place functionality is working
 
 Given Delete place payload
 When user calls "deletePlaceAPI" with "POST" http request
 Then the API call is success with status code 200
And "status" in response body is "OK"