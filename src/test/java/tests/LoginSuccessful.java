package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;
import java.io.IOException;

public class LoginSuccessful extends TestUtil {

    @DataProvider(name = "csvValidUsers")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/valid.users.csv");
    }

    @Test(dataProvider="csvValidUsers")
    public void successfulLogin(String username,String password){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);
}
}
