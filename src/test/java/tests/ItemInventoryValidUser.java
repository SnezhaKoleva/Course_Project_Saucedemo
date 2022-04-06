package tests;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ItemInventoryPage;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class ItemInventoryValidUser extends TestUtil {
    @DataProvider(name = "inventoryItem")
    public static Object[][] readInventoryFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readScvFile("src/test/resources/inventory.valid.user.csv");
    }
    @Test(dataProvider = "inventoryItem")
    public void inventoryCheck(String userName,String password,
                               String itemNumber,String itemName,String itemPrice,
                               String itemDescription,String itemImage){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName,password);

        ItemInventoryPage itemInventoryPage = new ItemInventoryPage(driver);
        itemInventoryPage.checkInventory(Integer.valueOf(itemNumber),itemName,itemPrice,
                itemDescription,itemImage);

    }

}

