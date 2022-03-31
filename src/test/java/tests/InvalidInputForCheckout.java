package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;


import java.io.IOException;

public class InvalidInputForCheckout extends TestUtil {

    @DataProvider(name ="csvInvalidInput")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/invalid.input.checkout.csv");
    }
    @Test(dataProvider = "csvInvalidInput")
    public void invalidInputCheckoutValidation(String firstName, String lastName, String code)
            throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage =loginPage.login("standard_user","secret_sauce");

        productsPage.addItemToTheCart("sauce-labs-bike-light");

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        productsPage.clickTheCartLink();
        Thread.sleep(3000);
        checkOutPage.invalidInputValidationForCheckout(firstName,lastName,code);
        Thread.sleep(3000);
   }

}
