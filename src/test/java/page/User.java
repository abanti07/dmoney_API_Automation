package page;

import Model.AdminModel;
import Model.UserModel;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User {
    String baseUrl;

    public User(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String callingLoginAPI(AdminModel model) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .body(model)
                .when()
                .post("/user/login")
                .then().assertThat().statusCode(200)
                .extract().response();
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        return token;

    }

    public Response CreateUser(String token, UserModel model,String Partnerkey) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", Partnerkey)
                .body(model)
                .when()
                .post("/user/create");
        System.out.println(res.asString());
        return res;
    }

    public String phone_number(Response res) {
        //RestAssured.baseURI = baseUrl;
        JsonPath jsonPath = res.jsonPath();
        String phone_number = jsonPath.get("user.phone_number");
        return phone_number;
    }


    public void SearchUser(String token, String userId) throws InterruptedException {
        Thread.sleep(3000);
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json").header("Authorization", token)
                .when()
                .get("/user/search/id/" + userId);

    }


}
