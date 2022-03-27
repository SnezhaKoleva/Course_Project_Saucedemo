package tests;

import base.TestUtil;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;



public class AddToCart extends TestUtil {


    @Test
    public  void addItemsToTheCart(){
        LoginPage loginPage=new LoginPage(driver);
        ProductsPage productsPage= loginPage.login("standard_user","secret_sauce");

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
