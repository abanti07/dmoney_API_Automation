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
       transaction=new Transaction(prop.getProperty("baseUrl"));
   }
   @Test(priority = 1,enabled = false)
    public void depositeFromAgentToCustomer() throws IOException {

       // Give 2000 tk from System account to the newly created agent
        model=new TransactionModel("SYSTEM",prop.getProperty("Agent_phone_number"),"2000");
        Response res=transaction.deposite(prop.getProperty("token"),model);
        JsonPath jsonPath=res.jsonPath();
        String message=jsonPath.getString("message");
        System.out.println(message);
    }
    @Test(priority = 2)
    public void depositAgentToCustomer() throws IOException {

      // String agent=prop.getProperty("Agent_phone_number");
      // String customer=prop.getProperty("Customer_1");

       //Deposit 1500 tk to a customer from the agent account
        model=new TransactionModel(prop.getProperty("Agent_phone_number"), prop.getProperty("Customer_1"),"1500");
       // model=new TransactionModel(agent,customer,"500");
        Response res=transaction.deposite(prop.getProperty("token"),model);
        JsonPath jsonPath=res.jsonPath();
        String message=jsonPath.getString("message");
        System.out.println(message);

    }
    @Test(priority =3,enabled = false)
    public void WithdrawtoAgent() throws ConfigurationException {
        model=new TransactionModel(prop.getProperty("Agent_phone_number"), prop.getProperty("Customer_1"),"500");
       Response res=transaction.deposite(prop.getProperty("token"),model);
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
    }

}
