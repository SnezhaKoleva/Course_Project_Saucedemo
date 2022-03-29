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

public class InputValidationCheckout extends TestUtil {

    @DataProvider(name = "inputInvalidDataForCheckout")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/checkout.error.input.csv");
    }
    @Test(dataProvider = "csvErrorInput")
    public void inputInvalidDataForCheckout(String firstName, String lastName, String code)
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
