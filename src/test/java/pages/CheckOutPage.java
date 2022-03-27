package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckOutPage {


    protected WebDriver driver;

    @FindBy(css = "[name=checkout]")
    WebElement checkoutBtn;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
    WebElement codeInput;

    @FindBy(css = "[value=Continue]")
    WebElement submitContinueBtn;

    @FindBy(css = "[name=finish]")
    WebElement finishBtn;

    @FindBy(id = "back-to-products")
    WebElement backHomeBtn;

    @FindBy(xpath = "//button[@class='error-button']")
    WebElement errorEmptyInput;


    public CheckOutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void checkOut() throws InterruptedException
    {
        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();

        firstNameInput.click();
        firstNameInput.sendKeys("nnn");


        lastNameInput.click();
        lastNameInput.sendKeys("jjj");

        fluentWait.until(ExpectedConditions.elementToBeClickable(codeInput));
        codeInput.click();
        codeInput.sendKeys("mmmm");


        fluentWait.until(ExpectedConditions.elementToBeClickable(submitContinueBtn));
        submitContinueBtn.click();
        Thread.sleep(3000);


        fluentWait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        finishBtn.click();
        Thread.sleep(3000);
        fluentWait.until(ExpectedConditions.elementToBeClickable(backHomeBtn));
        backHomeBtn.click();


    }
    public void invalidDataInputForCheckout(String firstName,String lastName,String code ){
        FluentWait fluentWait=new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();

        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        lastNameInput.click();
        lastNameInput.sendKeys(lastName);

        fluentWait.until(ExpectedConditions.elementToBeClickable(codeInput));
        codeInput.click();
        codeInput.sendKeys(code);

        Assert.assertTrue(errorEmptyInput.isDisplayed());

    }


}
