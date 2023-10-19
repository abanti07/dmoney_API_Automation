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
    public Response deposite(String token, TransactionModel model,String Partnerkey){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY",Partnerkey)
                .body(model)
                .when()
                .post("/transaction/deposit");
        return res;
    }
    public Response withdraw(String token, TransactionModel model,String Partnerkey){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY",Partnerkey)
                .body(model)
                .when()
                .post("/transaction/withdraw");
        return res;
    }
    public Response sendMoney(String token, TransactionModel model,String Partnerkey){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY",Partnerkey)
                .body(model)
                .when()
                .post("/transaction/sendmoney");
        return res;
    }
    public  Response Payment(String token, TransactionModel model,String Partnerkey){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY",Partnerkey)
                .body(model)
                .when()
                .post("/transaction/payment");
        return res;
    }
    public Response checkBalance(String token, String Partnerkey,String phone_number){
        RestAssured.baseURI=baseUrl;
        Response res=given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY",Partnerkey)
                .when()
                .get("/transaction/balance/"+phone_number);
        return res;
    }
}
