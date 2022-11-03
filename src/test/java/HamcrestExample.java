import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

//we need static import hamcrest for this
public class HamcrestExample {

    @Test
    public void hamcrestExample() {

        RestAssured
                .given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/9223372036854288964")
                .then()
                //we do have other methods like equalToIgnoringCase,  equalToIgnoringWhiteSpace, containsString, startsWith, endsWith for string comparison
                .body("name", equalToIgnoringCase("fish"))             //string comparison
                //we do have other methods like greaterThan, greaterThan, greaterThanOrEqualTo, lessThan ,lessThanOrEqualTo for number comparison
                .body("id", equalTo(9223372036854288964L)) //number comparison

//                .body("name",equalToIgnoringCase("doggie"),"id",equalTo(9223372036854247353L))
                .log().all();
    }

}
