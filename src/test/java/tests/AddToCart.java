package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;


public class AddToCart extends TestUtil {
    @DataProvider(name = "csvValidUsers")
    public static Object [][] readValidUsersFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/valid.users.csv");
    }

    @Test(dataProvider = "csvValidUsers")
    public void addItemsToTheCart(String userName,String password){
        LoginPage loginPage=new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName,password);

        productsPage.addItemToTheCart("sauce-labs-backpack");

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(),1);

        productsPage.addItemToTheCart("sauce-labs-bike-light");

        softAssert.assertEquals(productsPage.getItemsInTheCart(),2);

        productsPage.addItemToTheCart("sauce-labs-onesie");
        softAssert.assertEquals(productsPage.getItemsInTheCart(),3);

        softAssert.assertAll();



    }



}