package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    WebElement userNameInput;

    @FindBy(xpath = "(//input[@class='input_error form_input'])[2]")
    WebElement passwordInput;

    @FindBy(css = "[value=Login]")
    WebElement loginBtn;

    @FindBy(xpath = "//*[contains(text(),'Epic sadface')]")
    WebElement errorLoginLabel;



   public LoginPage(WebDriver driver){
       this.driver=driver;
       PageFactory.initElements(driver,this);
   }

   public ProductsPage login(String username,String password){
       userNameInput.click();
       userNameInput.clear();
       userNameInput.sendKeys(username);

       passwordInput.click();
       passwordInput.clear();
       passwordInput.sendKeys(password);

       loginBtn.click();

       return new ProductsPage(driver);



   } public void tryLogin(String username,String password){
       userNameInput.click();
       userNameInput.clear();
       userNameInput.sendKeys(username);

       passwordInput.click();
       passwordInput.clear();
       passwordInput.sendKeys(password);

       loginBtn.click();



    }

}
