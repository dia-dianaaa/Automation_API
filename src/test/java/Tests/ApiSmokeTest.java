package Tests;

import Config.ApiConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class ApiSmokeTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ApiConfig.get("apiBaseUrl");
    }

    @Test
    public void homePageResponds() {
        RestAssured.given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .time(lessThan(10000L));
    }
}
