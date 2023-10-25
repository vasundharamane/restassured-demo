import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class MockServerDemo {
    @Test
    public void MockServerDemo() {

        //set expectations
        System.out.println("********************SET EXPECTATIONS********************");
        RestAssured
                .given().log().all()
                .body("{\n" +
                        "    \"httpRequest\": {\n" +
                        "        \"method\": \"POST\",\n" +
                        "        \"path\": \"/api/external-endpoint-getUserDetails\"\n" +
                        "    },\n" +
                        "    \"httpResponse\": {\n" +
                        "        \"statusCode\": 200,\n" +
                        "        \"body\": \"{ \\\"firstName\\\": \\\"FirstName\\\", \\\"lastName\\\": \\\"LastName\\\" }\",\n" +
                        "        \"headers\": {\n" +
                        "            \"Content-Type\": \"application/json\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"times\": {\n" +
                        "        \"remainingTimes\": 1,\n" +
                        "        \"unlimited\": false\n" +
                        "    }\n" +
                        "}")
                .put("http://localhost:1080/mockserver/expectation")
                .then().log().all().statusCode(201);

        //call external API
        System.out.println("********************Call EXTERNAL API********************");
        RestAssured.given().log().all().header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"panNumber\": \"ABCD1234\"\n" +
                        "}")
                .when().post("http://localhost:8080/api/make-external-call")
                .then().log().all().statusCode(200);
    }
}