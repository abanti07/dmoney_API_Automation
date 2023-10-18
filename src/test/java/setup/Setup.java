package setup;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeTest;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Setup {
   public Properties prop;
    @BeforeTest
    public void initConfig() throws IOException, ConfigurationException {
        prop =new Properties();
        FileInputStream fileInputStream=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fileInputStream);
        Utils.SaveEnvVar("Partnerkey","ROADTOSDET");
    }

}
