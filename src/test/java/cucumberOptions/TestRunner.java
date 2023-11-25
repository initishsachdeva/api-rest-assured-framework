package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import pojo.token.TokenDetails;

import static io.restassured.RestAssured.given;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinations",
        monochrome = true,
        plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
/**
    @BeforeSuite
    public String generateToken() {
        String access_token = null;
        TokenDetails tokenDetails = new TokenDetails();
        try {
            RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
            String authServer = "auth";

            String response1 = given()
                    .header("Content-Type", "application/json")
                    .body(
                            "{\n" +
                                    "    \"username\" : \"" + tokenDetails.getUsername() + "\",\n" +
                                    "    \"password\" : \"" + tokenDetails.getPassword() + "\"\n" +
                                    "}"
                    )
                    .when().post(authServer)
                    .then()
                    .extract().response().asString();


            JsonPath js = new JsonPath(response1); //for parsing json and this requires string as an argument
            access_token = js.getString("token");
            System.out.println("access token is " + access_token);
        } catch (Exception e) {
            System.out.println("Exception in getting the token: " + " " + e.getMessage());
            Assert.fail("Unable to retrieve the token " + e.getMessage());
        }
        return access_token;
    }
 **/
}