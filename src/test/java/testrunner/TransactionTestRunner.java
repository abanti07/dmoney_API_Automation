package testrunner;

import Model.TransactionModel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;
import page.Transaction;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner extends Setup {
    Transaction transaction;
    TransactionModel model;

    public TransactionTestRunner() throws IOException, ConfigurationException {
        initConfig();
        transaction = new Transaction(prop.getProperty("baseUrl"));
    }

    @Test(priority = 1,description = "Deposit money to agent")
    public void depositeFromAgentToCustomer() throws IOException {

        // Give 2000 tk from System account to the newly created agent
        model = new TransactionModel("SYSTEM", prop.getProperty("Agent_phone_number"), "2000");
        Response res = transaction.deposite(prop.getProperty("token"), model,prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.getString("message");
        System.out.println(message);
    }

    @Test(priority = 2,description = "Deposit money to a customer from agent")
    public void depositAgentToCustomer() throws IOException, ConfigurationException, InterruptedException {

        //Deposit 1500 tk to a customer from the agent account
        Thread.sleep(5000);
        model = new TransactionModel(prop.getProperty("Agent_phone_number"), prop.getProperty("Customer_1"), "100");
        Response res = transaction.deposite(prop.getProperty("token"), model,prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.getString("message");
        System.out.println(message);

    }

    @Test(priority = 3,description = "Money withdraw by customer")
    public void WithdrawtoAgent() throws ConfigurationException, InterruptedException {
        Thread.sleep(5000);
        //Withdraw 500 tk by the customer to the agent
        model = new TransactionModel(prop.getProperty("Customer_1"), prop.getProperty("Agent_phone_number"), "50");
        Response res = transaction.withdraw(prop.getProperty("token"),model,prop.getProperty("Partnerkey"));
        System.out.println(res.asString());
        //JsonPath jsonPath = res.jsonPath();
    }
    @Test(priority = 4,description = "Customer sends money to another Customer.")
    public void sendMoney() throws InterruptedException {
        Thread.sleep(5000);
        //Send money 500 tk to another customer
        model=new TransactionModel(prop.getProperty("Customer_1"),prop.getProperty("Customer_2"),"50");
        Response res=transaction.sendMoney(prop.getProperty("token"),model,prop.getProperty("Partnerkey"));
        System.out.println(res.asString());
    }
    @Test(priority = 5,description = "Payment to merchant by Customer")
    public void paymentByCustomer() throws InterruptedException {
        Thread.sleep(5000);
        //Payment 100 tk to a merchant (01686606905) by the recipient customer
        model = new TransactionModel(prop.getProperty("Customer_2"), "01686606905", "10");
        Response res = transaction.Payment(prop.getProperty("token"),model,prop.getProperty("Partnerkey"));
        System.out.println(res.asString());
    }
    @Test(priority = 6,description = " Check balance of customer")
    public void checkBalance(){
        // Check balance of the recipient customer
        String phone_number=prop.getProperty("Customer_2");
        Response res=transaction.checkBalance(prop.getProperty("token"),prop.getProperty("Partnerkey"),phone_number);
        System.out.println(res.asString());

    }


}
