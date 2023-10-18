package testrunner;

import Model.AdminModel;
import Model.TransactionModel;
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
    public void login() throws IOException, ConfigurationException {
        //admin_login
        AdminModel model = new AdminModel("salman@roadtocareer.net", "1234");
        // UserModel model=new UserModel();
//        model.setEmail("salman@roadtocareer.net");
//        model.setPassword("1234");
        String token = user.callingLoginAPI(model);
        Utils.SaveEnvVar("token", token);
    }

    @Test(priority = 2, description = "Admin created new customer with proper information")
    public void createCustomer() throws IOException, ConfigurationException {
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
        String phonenumber = jsonPath.get("user.phone_number");
        System.out.println(phonenumber);
        Utils.SaveEnvVar("Customer_1", phonenumber);

    }

    @Test(priority = 3, description = "Admin created new customer with valid information")
    public void createCustomer_2() throws ConfigurationException, InterruptedException {

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
        Thread.sleep(5000);
        String phonenumber = jsonPath.get("user.phone_number");
        System.out.println(phonenumber);
        Utils.SaveEnvVar("Customer_2", phonenumber);
    }

    @Test(priority = 4, description = "Admin created an agent with proper information")
    public void CreateAgent() throws ConfigurationException, InterruptedException {

        Faker faker = new Faker();
        String name = faker.name().name();
        String email = "agent" + faker.internet().emailAddress();
        String password = "1234";
        String phone_number = "01313" + Utils.generateRandomId(1000, 9999) + "77";
        String nid = "123456789";
        String role = "Customer";
        UserModel userModel = new UserModel(name, email, password, phone_number, nid, role);

        Response res = user.CreateUser(prop.getProperty("token"), userModel, prop.getProperty("Partnerkey"));
        JsonPath jsonPath = res.jsonPath();
        Thread.sleep(3000);
        String phonenumber = jsonPath.get("user.phone_number");
        Utils.SaveEnvVar("Agent_phone_number", phonenumber);
    }

    @Test(priority = 4, enabled = false, description = "Admin tried to find a user with userId.")
    public void searchUser() throws IOException, InterruptedException {
        user.SearchUser(prop.getProperty("token"), prop.getProperty("userId"));
    }

}
