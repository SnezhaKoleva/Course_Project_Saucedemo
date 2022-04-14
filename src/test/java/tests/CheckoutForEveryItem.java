package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;

public class CheckoutForEveryItem extends TestUtil {

    @DataProvider(name = "csvItems")
    public static Object [][] readItemsFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/items.csv");

    }
    @Test(dataProvider = "csvItems")

    public void checkoutForEveryItem(String itemName)
            throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        CheckoutPage checkOutPage = new CheckoutPage(driver);

        productsPage.addItemToTheCart(itemName);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1,"One added product");

        productsPage.clickTheCartLink();
        checkOutPage.checkOut();

        softAssert.assertAll();
    }
}
