package services;

import cucumberOptions.TestRunner;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.token.TokenDetails;

import java.io.*;
import java.util.Properties;


public class Utils extends TestRunner {

    TokenDetails token = new TokenDetails();
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", token.getKey())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        String basePath = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(basePath + "/src/test/java/services/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {
        String jsonResponse = response.asString();
        JsonPath js = new JsonPath(jsonResponse);
        return js.get(key).toString();
    }
}
