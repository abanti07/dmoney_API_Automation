package testrunner;

import Model.AdminModel;
import Model.UserModel;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;
import page.User;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;

    public UserTestRunner() throws IOException, ConfigurationException {
        initConfig();
        user = new User(prop.getProperty("baseUrl"));
    }

    @Test(priority = 1, description = "Admin can login with valid creds!")
    public void login() throws IOException, ConfigurationException, InterruptedException {
        //admin_login
//        String name="admin";
//        String phone_number="016718"+Utils.generateRandomId(10000,99999);
//        String role="Admin";
//        String nid="12345678";
//        String email="salman@roadtocareer.net";
//        String password="1234";
        Thread.sleep(5000);
       AdminModel model = new AdminModel("salman@roadtocareer.net", "1234");

        String token = user.callingLoginAPI(model);
        System.out.println(token);
        Utils.SaveEnvVar("token", token); //save token in config file
    }

    @Test(priority = 2, description = "Admin created new customer with proper information")
    public void createCustomer() throws IOException, ConfigurationException, InterruptedException {
        Thread.sleep(10000);
        Faker faker = new Faker();
        String name = faker.name().name();
        String email = "Test" + faker.internet().emailAddress();
        String password = "1234";
        String phone_number = "01713" + Utils.generateRandomId(1000, 9999) + "77";
        String nid = "123456789";
        String role = "Customer";

        UserModel model = new UserModel(name, email, password, phone_number, nid, role);
        Response res = user.CreateUser(prop.getProperty("token"), model, prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        String customer_phone_number1 = jsonPath.get("user.phone_number");
        System.out.println(customer_phone_number1);
        Utils.SaveEnvVar("Customer_1", customer_phone_number1);

    }

    @Test(priority = 3, description = "Admin created new customer with valid information")
    public void createCustomer_2() throws ConfigurationException, InterruptedException {
        Thread.sleep(10000);

        Faker faker = new Faker();
        String name = faker.name().name();
        String email = "Customer" + faker.internet().emailAddress();
        String password = "1234";
        String phone_number = "01813" + Utils.generateRandomId(1000, 9999) + "77";
        String nid = "123456789";
        String role = "Customer";

        UserModel Model = new UserModel(name, email, password, phone_number, nid, role);

        Response res = user.CreateUser(prop.getProperty("token"), Model, prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        //Thread.sleep(2000);
        String customer_phone_number2 = jsonPath.get("user.phone_number");
        System.out.println(customer_phone_number2);
        Utils.SaveEnvVar("Customer_2", customer_phone_number2);
    }

    @Test(priority = 4, description = "Admin created an agent with proper information")
    public void CreateAgent() throws ConfigurationException, InterruptedException {
        Thread.sleep(10000);

        Faker faker = new Faker();
        String name = faker.name().name();
        String email = "agent" + faker.internet().emailAddress();
        String password = "1234";
        String phone_number = "01313" + Utils.generateRandomId(1000, 9999) + "77";
        String nid = "123456789";
        String role = "Agent";
        UserModel userModel = new UserModel(name, email, password, phone_number, nid, role);

        Response res = user.CreateUser(prop.getProperty("token"), userModel, prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        //Thread.sleep(3000);
        String agent_phone_number = jsonPath.get("user.phone_number");
        Utils.SaveEnvVar("Agent_phone_number", agent_phone_number);
    }

}
