package stepDefinations;


import constants.api_endpoints.EnumClassResources;
import helper.TestDataPayload;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import constants.api_endpoints.EnumClassResources;
import helper.TestDataPayload;
import org.testng.Assert;
import services.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PlaceApiDefinition extends Utils {
    RequestSpecification requestBuilder;
    ResponseSpecification responseBuilder;
    Response response;
    TestDataPayload data = new TestDataPayload();
    static String place_id;

    Scenario scenario;

    //cucumber-hooks
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("Add Place Payload with {string}  {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        try {
            requestBuilder = given().spec(requestSpecification())
                    .body(data.addPlacePayLoad(name, language, address));
        } catch (Exception e) {
            scenario.log("There are some issues in creating the payload body " + e.getMessage());
            Assert.fail("There are some issues in creating the payload body " + e.getMessage());
        }
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        try {
            //constructor will be called with value of resource which you pass
            EnumClassResources resourceAPI = EnumClassResources.valueOf(resource);
            System.out.println(resourceAPI.getResource());
            responseBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
            if (method.equalsIgnoreCase("POST")) {
                response = requestBuilder.when().post(resourceAPI.getResource());
//                    .then().spec(responseBuilder).extract().response();
            } else if (method.equalsIgnoreCase("GET")) {
                response = requestBuilder.when().get(resourceAPI.getResource());
//                    .then().spec(responseBuilder).extract().response();
            } else if (method.equalsIgnoreCase("PUT")) {
                response = requestBuilder.when().put(resourceAPI.getResource());
//                    .then().spec(responseBuilder).extract().response();
            }
        } catch (Exception e) {
            scenario.log("There are some issues in calling the HTTP Method " + e.getMessage());
            Assert.fail("There are some issues in calling the HTTP Method " + e.getMessage());
        }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        try {
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            scenario.log("There are some issues in validating the status code " + e.getMessage());
            Assert.fail("There are some issues in validating the status code " + e.getMessage());
        }
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        try {
            //String addPlaceResponse = response.asString();
            //JsonPath js = new JsonPath(addPlaceResponse);
            assertEquals(getJsonPath(response, keyValue), expectedValue);
        } catch (Exception e) {
            scenario.log("There are some issues in validating the response body " + e.getMessage());
            Assert.fail("There are some issues in validating the response body " + e.getMessage());
        }
    }


    @Then("verify place_Id created using addPlaceApi contains matching {string} using {string}")
    public void verify_place_id_created_that_matches_with_using(String expectedName, String apiResourceName) throws IOException {
        try {
            place_id = getJsonPath(response, "place_id");
            System.out.println("Place id captured from Add place api is " + place_id);
            requestBuilder = given().spec(requestSpecification()).queryParam("place_id", place_id);
            user_calls_with_http_request(apiResourceName, "Get");
            String actualName = getJsonPath(response, "name");
            System.out.println("Actual name is " + actualName);
            System.out.println("Expected name is " + expectedName);
            assertEquals(actualName, expectedName);
        } catch (Exception e) {
            scenario.log("There are some issues in validating the response body " + e.getMessage());
            Assert.fail("There are some issues in validating the response body " + e.getMessage());
        }
    }


    @Given("UpdatePlace Payload")
    public void update_place_payload() throws IOException {
        try {
            requestBuilder = given().spec(requestSpecification()).queryParam("place_id", place_id)
                    .body(data.updatePlacePayload(place_id));
        } catch (Exception e) {
            scenario.log("There are some issues in creating the payload body " + e.getMessage());
            Assert.fail("There are some issues in creating the payload body " + e.getMessage());
        }
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        try {
            requestBuilder = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
        } catch (Exception e) {
            scenario.log("There are some issues in creating the payload body " + e.getMessage());
            Assert.fail("There are some issues in creating the payload body " + e.getMessage());
        }
    }
}
