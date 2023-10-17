import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;
    public  UserTestRunner() throws IOException {
        initConfig();
        user=new User(prop.getProperty("baseUrl"));
    }
    @Test(priority = 1)
    public void login() throws IOException, ConfigurationException {
//admin_login
        UserModel model=new UserModel();
        model.setEmail("salman@roadtocareer.net");
        model.setPassword("1234");
        String token=user.callingLoginAPI(model);
        Utils.SaveEnvVar("token",token);
    }
    @Test(priority = 2)
    public void createUser() throws IOException, ConfigurationException {

        Faker faker=new Faker();
        UserModel model=new UserModel();
        model.setName(faker.name().name());
        model.setEmail(faker.internet().emailAddress());
        model.setPassword("1234");
        model.setPhone_number("01612"+Utils.generateRandomId(100000,999999));
        model.setNid("12345678");
        model.setRole("customer");
        user.CreateUser(prop.getProperty("token"),model);

    }
    @Test(priority = 3)
    public void searchUser() throws IOException, InterruptedException {

        user.SearchUser(prop.getProperty("token"),prop.getProperty("userId"));

    }
}
