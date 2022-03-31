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
    private WebElement checkoutBtn;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
    private WebElement codeInput;

    @FindBy(css = "[value=Continue]")
    private WebElement submitContinueBtn;

    @FindBy(css = "[name=finish]")
    private WebElement finishBtn;

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(css = "[class=error-button]")
    private WebElement errorEmptyInput;


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

    public void invalidInputValidationForCheckout(String firstName, String lastName, String code ) {


        FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(3));
        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        checkoutBtn.click();
        fluentWait.until(ExpectedConditions.elementToBeClickable(codeInput));

        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        lastNameInput.click();
        lastNameInput.sendKeys(lastName);


        codeInput.click();
        codeInput.sendKeys(code);

        submitContinueBtn.click();

        Assert.assertTrue(errorEmptyInput.isDisplayed());

    }

}
