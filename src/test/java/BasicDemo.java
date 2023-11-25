/**
 * Basic demo to start with Rest Assured
 */

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicDemo {
    public static void main(String[] args) {
        //given = all  input details
        //when = submit the api by providing resource and http method
        //then = validate the response

//---------------- validate if add place api is working ---------------------------
//        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//                .body(Payload.addPlace())
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200)
//                .body("scope", equalTo("APP"));
//        //  .header("Server", equalTo("Apache/2.4.41 (Ubuntu)"));


//--> Add place ->update place with new address and - > get place with updated address catch the response in a variable.
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(addPlace())
                .when().post("maps/api/place/add/json")
                .getCookies().get("JSESSIONID");

        System.out.println(response);
        // ---- you need to parse the json got from system.out in previous step as this is json body
        JsonPath js = new JsonPath(response); //for parsing json and this requires string as an argument
        String place_id = js.getString("place_id");
        System.out.println(place_id);

//---> Update place api------------------------
        String newAddress = "7 winter walk, USA";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"place_id\": \"" + place_id + "\",\n" +
                        "    \"address\": \"" + newAddress + "\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


//----------------> Get the api or updated address
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = rawToJson(getPlaceResponse); // calling from reusable methods class
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        //verify the actual address is equal to new address, so that is possible only through testing frameworks junit or testng
        Assert.assertEquals(actualAddress, newAddress);

    }


    public static String addPlace() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String coursePrice() {
        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";
    }


    public static JsonPath rawToJson(String response) {
        JsonPath js1 = new JsonPath(response);
        return js1;
    }
}
