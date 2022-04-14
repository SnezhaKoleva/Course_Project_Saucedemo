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


public class CheckoutRemoveValidUsers extends TestUtil {
    @DataProvider(name = "csvValidUsersCheckout")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/valid.users.checkout.csv");
    }

    @Test(dataProvider = "csvValidUsersCheckout")
    public void checkOut(String userName,String password,
                         String oneAddedItem, String secondAddedItem,String addedRemovedItem)
            throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName,password);
        CheckoutPage checkOutPage = new CheckoutPage(driver);

        productsPage.addItemToTheCart(oneAddedItem);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1,"One added product");

        productsPage.addItemToTheCart(secondAddedItem);
        softAssert.assertEquals(productsPage.getItemsInTheCart(),2,"Two added products");

        productsPage.clickTheCartLink();

        productsPage.removeItem(addedRemovedItem);
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1,
                "One product removed,one left");

        checkOutPage.checkOut();

        softAssert.assertAll();
    }
}
