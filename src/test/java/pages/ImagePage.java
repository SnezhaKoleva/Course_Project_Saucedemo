package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class ImagePage {
    protected WebDriver driver;
    private static final String INVENTORY_ITEM_NAME="//img[@alt='%s']";
    private static final String INVENTORY_ITEM_IMAGE= "//img[@src='%s']";


    public ImagePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void checkCorrectImage(String itemName,String itemImage){

        String xpathOfItemName = String.format(INVENTORY_ITEM_NAME, itemName);
        String xpathOfItemImage = String.format(INVENTORY_ITEM_IMAGE, itemImage);

        WebElement nameOfTheItem = driver.findElement(By.xpath(xpathOfItemName));
        WebElement imageOfTheItem = driver.findElement(By.xpath(xpathOfItemImage));

        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));

        fluentWait.until(ExpectedConditions.visibilityOf(nameOfTheItem));
        fluentWait.until(ExpectedConditions.visibilityOf(imageOfTheItem));

        Assert.assertTrue(nameOfTheItem.isDisplayed());
        Assert.assertTrue(imageOfTheItem.isDisplayed());

    }


}
