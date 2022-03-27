package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ProductsPage {

    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartCounter;


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

    }

    public int getItemsInTheCart() {
        if (shoppingCartCounter.getText().isEmpty()) {
            return 0;
        } else
            return Integer.parseInt(shoppingCartCounter.getText());
    }
    public void letsCheckOut(){
        shoppingCartLink.click();
    }
}