package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ImagePage;
import utils.CsvHelper;

import java.io.IOException;

public class ImageItemTest extends TestUtil {


    @DataProvider(name = "csvInventory")
    public static Object[][] readInventoryFromScvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/image.inventory.csv");
    }

    @Test(dataProvider = "csvInventory")


    public void checkCorrectImageForItem(String userName,String password,
                                         String itemName, String itemImage) {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName,password);

        ImagePage imagePage =new ImagePage(driver);
        imagePage.checkCorrectImage(itemName,itemImage);
    }

}










