package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
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
                               String itemDescription){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");

        InventoryPage inventoryPage=new InventoryPage(driver);
        inventoryPage.checkInventory(Integer.valueOf(itemNumber),itemName,itemPrice,itemDescription);

    }

}

