package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;


public class CheckOutForValidUsers extends TestUtil {
    @DataProvider(name = "csvValidUsers")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/valid.users.csv");
    }

    @Test(dataProvider = "csvValidUsers")
    public void checkOut(String userName,String password) throws InterruptedException {

        LoginPage loginPage=new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName,password);
        CheckOutPage checkOutPage =new CheckOutPage(driver);

        productsPage.addItemToTheCart("sauce-labs-bolt-t-shirt");

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1);


        productsPage.addItemToTheCart("test.allthethings()-t-shirt-(red)");

        softAssert.assertEquals(productsPage.getItemsInTheCart(),2);

        productsPage.clickTheCartLink();

        productsPage.removeItem("test.allthethings()-t-shirt-(red)");
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1);

        checkOutPage.checkOut();

        softAssert.assertAll();
    }
}
