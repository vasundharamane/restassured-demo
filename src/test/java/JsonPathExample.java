import com.github.javafaker.Faker;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class JsonPathExample {


    @Test
//    http://tosca-webservice-ng.azurewebsites.net/swagger/ui/index.html#/Coffees/addCoffee
    public void jsonPathEx() {
        Faker faker = new Faker();

        String description = faker.company().buzzword();
        String name = faker.name().name();
        Response response = RestAssured
                .given()
                .body("{\n" +
                        "  \"description\": \"" + description + "\",\n" +
                        "  \"name\": \"" + name + "\"\n" +
                        "}")
                .header("content-type", "application/json")
                .post("http://tosca-webservice-ng.azurewebsites.net/api/Coffees_V4")
                .then().log().all().extract().response();

        String outPutDescription = response.jsonPath().getString("description");
        System.out.println("Description  " + outPutDescription);
        String outPutDescription2 = response.jsonPath().get("description");
        System.out.println("Description  " + outPutDescription2);
    }


    @Test
    public void jsonPathLongEx(){
        Response response = RestAssured.given()
                .get("https://reqres.in/api/unknown")
                .then().log().all().extract().response();

        Object dataObject = JsonPath.parse(response.asPrettyString()).read("$.data[?(@.pantone_value=~ /.*17.*/)]");
        String dataString = dataObject.toString();
        System.out.println("OUPUT :::::::::" + dataString);
    }
}
