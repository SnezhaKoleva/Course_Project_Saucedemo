package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(xpath = "(//input[@class='input_error form_input'])[2]")
     private WebElement passwordInput;

    @FindBy(css = "[value=Login]")
    private WebElement loginBtn;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement userAllPagesButton;

    @FindBy(xpath = "//*[contains(text(),'Epic sadface')]")
    private WebElement errorLoginLabel;


   public LoginPage(WebDriver driver){
       this.driver = driver;
       PageFactory.initElements(driver,this);
   }

   public ProductsPage login(String username, String password){
       userNameInput.click();
       userNameInput.clear();
       userNameInput.sendKeys(username);

       passwordInput.click();
       passwordInput.clear();
       passwordInput.sendKeys(password);

       loginBtn.click();

       FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
       fluentWait.until(ExpectedConditions.visibilityOf(userAllPagesButton));
       Assert.assertTrue(userAllPagesButton.isDisplayed());

       return new ProductsPage(driver);


   } public void tryLogin(String username,String password){
       userNameInput.click();
       userNameInput.clear();
       userNameInput.sendKeys(username);

       passwordInput.click();
       passwordInput.clear();
       passwordInput.sendKeys(password);

       loginBtn.click();
       Assert.assertTrue(errorLoginLabel.isDisplayed());

    }

}
