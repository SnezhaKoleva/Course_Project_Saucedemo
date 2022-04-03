package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class ItemInventoryPage {

    protected WebDriver driver;

    private static final String ITEM_PRICE = "(//div[@class='inventory_item_price'])[%d]";

    private static final String ITEM_DESCRIPTION = "(//div[@class='inventory_item_desc'])[%d]";

    private static final String ITEM_NAME = "(//div[@class='inventory_item_name'])[%d]";


    public ItemInventoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean checkInventory(Integer inventoryNumber, String productName, String productPrice,
                                  String productDescription) {
        String xpathOfItemName = String.format(ITEM_NAME, inventoryNumber);
        WebElement itemName = driver.findElement(By.xpath(xpathOfItemName));

        String xpathOfItemDescription = String.format(ITEM_DESCRIPTION, inventoryNumber);
        WebElement itemDescription = driver.findElement(By.xpath(xpathOfItemDescription));

        String xpathOfItemPrice = String.format(ITEM_PRICE, inventoryNumber);
        WebElement itemPrice = driver.findElement(By.xpath(xpathOfItemPrice));


        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.visibilityOf(itemName));
        fluentWait.until(ExpectedConditions.visibilityOf(itemPrice));
        fluentWait.until(ExpectedConditions.visibilityOf(itemDescription));



        try {
            itemDescription.getText().contains(productDescription);

        } catch (NoSuchElementException e) {
            return false;
        }

        Assert.assertEquals(itemName.getText(),productName);
        Assert.assertEquals(itemPrice.getText(),productPrice);

        return true;
    }
}




