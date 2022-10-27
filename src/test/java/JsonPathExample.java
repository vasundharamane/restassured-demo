import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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
        String outPutDescription2 = (String) response.jsonPath().get("description");
        System.out.println("Description  " + outPutDescription2);

    }
}
