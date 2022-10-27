import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class QueryParamNPathParam {

//    Query Parameter : to fetch a specific record , to filter out record
//    we can separate out multiple query parameter with &
//    http://webservice.toscacloud.com/rest/swagger/ui/index#/

    @Test
    public void queryParamExampla() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        RestAssured.given()
                .queryParam("status", "available")
                .when()
                .get("/findByStatus")
                .then()
                .log().all();
    }


    @Test
    public void pathParamExample() {
        RestAssured.given()
                .pathParams("country", "IN").pathParams("pin", "110005")
                .when()
                .get("http://api.zippopotam.us/{country}/{pin}")
                .then()
                .log().all();
    }
}
