package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductsPage {

    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";
    private static final String REMOVE_ITEM_BUTTON="//button[@id='remove-%s']";

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String productName) {

        String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));

        FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        String xpathOfRemoveItemButton=String.format(REMOVE_ITEM_BUTTON,productName);
        WebElement removeItemButton = driver.findElement(By.xpath(xpathOfRemoveItemButton));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeItemButton));
        Assert.assertTrue(removeItemButton.isDisplayed());

    }
    public void removeItem(String productName){
        String xpathOfRemoveItemButton=String.format(REMOVE_ITEM_BUTTON,productName);
        WebElement removeItemButton = driver.findElement(By.xpath(xpathOfRemoveItemButton));

        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeItemButton));
        Assert.assertTrue(removeItemButton.isDisplayed());
        removeItemButton.click();

    }

    public int getItemsInTheCart() {
        if (shoppingCartCounter.getText().isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(shoppingCartCounter.getText());
    }

    public void clickTheCartLink() {
        shoppingCartLink.click();

    }



}


