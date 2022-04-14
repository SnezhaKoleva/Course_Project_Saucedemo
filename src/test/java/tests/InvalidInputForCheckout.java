package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
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
    public void invalidInputCheckoutValidation(String itemName,
                                               String firstName, String lastName, String code)
            throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user","secret_sauce");
        CheckoutPage checkOutPage = new CheckoutPage(driver);

        productsPage.addItemToTheCart(itemName);
        productsPage.clickTheCartLink();
        checkOutPage.invalidInputValidationForCheckout(firstName,lastName,code);

   }

}
