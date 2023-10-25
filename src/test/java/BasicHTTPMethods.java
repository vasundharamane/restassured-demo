import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class BasicHTTPMethods {

    //******************************************************GET Demo***************************************************
    //Getting users using GET Method -  gherkin format
    @Test
    public void testGET() {

//               RequestSpecification requestSpecification = RestAssured.given()
//                .baseUri("https://reqres.in/api")
//                .basePath("/users")
//               .header("Content-Type","application/json");

        Response response = RestAssured.given()
//                .spec(requestSpecification)
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .extract().response();

        response.prettyPrint();

//        System.out.println("Status code " + response.statusCode());
//        System.out.println("Time " +  response.getTime());
//        System.out.println("Body " +  response.getBody().asString());
//        System.out.println("Status line " + response.statusLine());
//        System.out.println("Content Type " +  response.getContentType());

    }

    //Getting users using GET method -
    @Test
    public void TestGETWithoutGherkin() {

        RequestSpecification requestSpecification = RestAssured.given();            //create a request specification
        requestSpecification.baseUri("https://reqres.in/api");                   //Adding URI

        //calling get method on URI
        Response response = requestSpecification.get("/users?page=2");  //calling get method

        String res = response.asPrettyString();         //print response
        System.out.println("Response body  " + res);


        //to perform any validation we need validatable response
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
    }

    //******************************************************POST Demo***************************************************
    //create User
    @Test
    public void testPOST() {

        Response response = RestAssured.given()
//                .spec(requestSpecification)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post("https://reqres.in/api/users")
                .then()
                .extract().response();

        response.prettyPrint();
    }

    //******************************************************PUT Demo***************************************************
    //Update User
    @Test
    public void testUPDATE() {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().put("https://reqres.in/api/users/1")
                .then().extract().response();

        response.prettyPrint();
    }

    //******************************************************DELETE Demo***************************************************
    //Delete User
    @Test
    public void testDELETE() {
        Response response = RestAssured.given()
                .when().delete("https://reqres.in/api/users/1")
                .then().extract().response();

        System.out.println("Status code " + response.statusCode());
    }

}
