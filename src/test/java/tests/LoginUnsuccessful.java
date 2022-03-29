package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class LoginUnsuccessful extends TestUtil {

    @DataProvider(name = "csvInvalidUsers")
    public static Object[][] readInvalidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/invalid.users.csv");
    }

    @Test(dataProvider = "csvInvalidUsers")
    public void unsuccessfulLogin(String username,String password){

        LoginPage loginPage=new LoginPage(driver);
        loginPage.tryLogin(username,password);
    }
}
