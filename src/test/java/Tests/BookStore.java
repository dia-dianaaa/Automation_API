package Tests;

import Acount.BuyBookSuccess;
import Acount.GenerateTokenSuccess;
import Acount.ResponseBodyAcount;
import Config.ApiConfig;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * DemoQA BookStore API flow scaffold for the IT School course.
 * Set apiPassword in config.properties before running the full flow.
 */
public class BookStore {

    private String username;
    private String userId;
    private String token;
    private final String[] isbns = {"9781449325862"};

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ApiConfig.get("apiBaseUrl");
    }

    @Test(enabled = false)
    public void bookStoreFlow() throws IOException {
        createAccount();
        generateToken();
        buyBook();
    }

    public void createAccount() {
        Faker faker = new Faker();
        username = faker.name().username() + faker.number().digits(5);

        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        JSONObject requestParameters = new JSONObject();
        requestParameters.put("userName", username);
        requestParameters.put("password", ApiConfig.get("apiPassword"));

        Response response = request.body(requestParameters.toJSONString())
                .post("Account/v1/User");

        System.out.println(response.body().prettyPrint());

        ResponseBodyAcount responseBody = response.body().as(ResponseBodyAcount.class);
        userId = responseBody.getUserID();
    }

    public void generateToken() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        JSONObject requestParameters = new JSONObject();
        requestParameters.put("userName", username);
        requestParameters.put("password", ApiConfig.get("apiPassword"));

        Response response = request.body(requestParameters.toJSONString())
                .post("Account/v1/GenerateToken");

        System.out.println(response.body().prettyPrint());

        GenerateTokenSuccess tokenResponse = response.body().as(GenerateTokenSuccess.class);
        token = tokenResponse.getToken();
    }

    public void buyBook() throws IOException {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token);

        String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/resources/body.json")));
        jsonBody = jsonBody.replace("PLACEHOLDER_USERID", userId);
        jsonBody = jsonBody.replace("PLACEHOLDER_ISBN", isbns[0]);

        Response response = request.body(jsonBody).post("BookStore/v1/Books");

        BuyBookSuccess buyBookSuccess = response.body().as(BuyBookSuccess.class);
        System.out.println(buyBookSuccess.getBooks());
    }
}
