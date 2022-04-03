package pages;

import org.openqa.selenium.By;
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

    private static final String ITEM_IMAGE_SRC= "//img[@src='%s']";


    public ItemInventoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void checkInventory(Integer inventoryNumber, String productName, String productPrice,
                                  String productDescription,String productImageScr) {
        String xpathOfItemName = String.format(ITEM_NAME, inventoryNumber);
        WebElement itemName = driver.findElement(By.xpath(xpathOfItemName));

        String xpathOfItemDescription = String.format(ITEM_DESCRIPTION, inventoryNumber);
        WebElement itemDescription = driver.findElement(By.xpath(xpathOfItemDescription));

        String xpathOfItemPrice = String.format(ITEM_PRICE, inventoryNumber);
        WebElement itemPrice = driver.findElement(By.xpath(xpathOfItemPrice));

        String xpathOfItemImageSrc = String.format(ITEM_IMAGE_SRC,productImageScr);
        WebElement itemImage = driver.findElement(By.xpath(xpathOfItemImageSrc));


        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.visibilityOf(itemName));
        fluentWait.until(ExpectedConditions.visibilityOf(itemPrice));
        fluentWait.until(ExpectedConditions.visibilityOf(itemDescription));
        fluentWait.until(ExpectedConditions.visibilityOf(itemImage));


        Assert.assertEquals(itemName.getText(),productName);
        Assert.assertTrue(itemDescription.getText().contains(productDescription));
        Assert.assertEquals(itemPrice.getText(),productPrice);
        Assert.assertTrue(itemImage.isDisplayed());

    }
}




