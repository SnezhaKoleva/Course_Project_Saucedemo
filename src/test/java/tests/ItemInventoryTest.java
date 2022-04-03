package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ItemInventoryPage;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class ItemInventoryTest extends TestUtil {
    @DataProvider(name = "inventoryItem")
    public static Object[][] readInventoryFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/inventory.item.csv");
    }
    @Test(dataProvider = "inventoryItem")
    public void inventoryCheck(String itemNumber,String itemName,String itemPrice,
                               String itemDescription,String itemImage){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");

        ItemInventoryPage itemInventoryPage =new ItemInventoryPage(driver);
        itemInventoryPage.checkInventory(Integer.valueOf(itemNumber),itemName,itemPrice,
                itemDescription,itemImage);

    }

}

