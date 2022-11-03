import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GSONExample {

    //using Gson().toJson and Gson().fromJson
    @Test
    public void gsonToJSONExample() {

        Faker faker = new Faker();
        Map<String, Object> bodypramMap = new HashMap<String, Object>();
//    bodypramMap.put("Id",faker.number().digits(5));
        bodypramMap.put("name", faker.funnyName().name());
        bodypramMap.put("description", faker.beer().hop() + faker.beer().malt());

        String jsonPayload = new Gson().toJson(bodypramMap);
        System.out.println("jsonPayload : " + jsonPayload);

        Response response = RestAssured
                .given()
                .body(jsonPayload)
                .header("content-type", "application/json")
                .post("http://tosca-webservice-ng.azurewebsites.net/api/Coffees_V4")
                .then().log().all().extract().response();


        Map<String, Object> bodyFromResponse = new Gson().fromJson(response.asPrettyString(), new HashMap<String, Object>().getClass());

        System.out.println("Description  : " + bodyFromResponse.get("description"));
        System.out.println("id " + bodyFromResponse.get("id"));
        System.out.println("name " + bodyFromResponse.get(("name")));
    }

    @Test
    public void withoutgsonToJSONExample() {

        Faker faker = new Faker();
        Map<String, Object> bodypramMap = new HashMap<String, Object>();
//    bodypramMap.put("Id",faker.number().digits(5));
        bodypramMap.put("name", faker.funnyName().name());
        bodypramMap.put("description", faker.beer().hop() + faker.beer().malt());

//        String jsonPayload = new Gson().toJson(bodypramMap);
//        System.out.println("jsonPayload : " + jsonPayload);

        Response response = RestAssured
                .given()
                .body(bodypramMap)
                .header("content-type", "application/json")
                .post("http://tosca-webservice-ng.azurewebsites.net/api/Coffees_V4")
                .then().log().all().extract().response();


        Map<String, Object> bodyFromResponse = new Gson().fromJson(response.asPrettyString(), new HashMap<String, Object>().getClass());

        System.out.println("Description  : " + bodyFromResponse.get("description"));
        System.out.println("id " + bodyFromResponse.get("id"));
        System.out.println("name " + bodyFromResponse.get(("name")));
    }
}
