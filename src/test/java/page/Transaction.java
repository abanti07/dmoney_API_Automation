package page;

import Model.TransactionModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Transaction {
    String baseUrl;
    public Transaction(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public Response deposite(String token, TransactionModel model){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body(model)
                .when()
                .post("/transaction/deposit");
        return res;
    }
}
