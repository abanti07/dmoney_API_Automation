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
    public User(String baseUrl){
        this.baseUrl=baseUrl;


    }

    public String  callingLoginAPI(UserModel model) throws ConfigurationException {
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .body(model)
                .when()
                .post("/user/login")
                .then().assertThat().statusCode(200)
                .extract().response();
        System.out.println(res.asString());

        JsonPath jsonPath=res.jsonPath();
        String token=jsonPath.get("token");
        return token;

    }
    @Test(priority = 2)
    public void CreateUser(String token,UserModel model) throws ConfigurationException {
        RestAssured.baseURI=baseUrl;

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body(model)
                .when()
                .post("/user/create");
        System.out.println(res.asString());

        JsonPath jsonPath=res.jsonPath();
        int userId=jsonPath.get("user.id");
        Utils.SaveEnvVar("userId",String.valueOf(userId));
    }
    @Test(priority = 3)
    public void SearchUser(String token,String userId) throws InterruptedException {
        Thread.sleep(3000);
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json").header("Authorization",token)
                .when()
                .get("/user/search/id/"+userId);

    }
}
