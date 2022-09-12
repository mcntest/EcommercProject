package org.example.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPomPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    public CheckoutPomPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public static final String placeOrderButtonCss = "button[id=place_order]";
    public static final String billingAddress1 = "input[id=billing_address_1]";
    public static final String billingCity = "input[id=billing_city]";
    public static final String billingPostCode = "input[id=billing_postcode]";
    public static final String billingPhone = "input[id=billing_phone]";
    public static final String billingEmail = "input[id=billing_email]";


    //Locators
    @FindBy(css = placeOrderButtonCss)
    WebElement orderButton;

    @FindBy(css = billingAddress1)
    WebElement address1;

    @FindBy(css = billingCity)
    WebElement city;

    @FindBy(css = billingPostCode)
    WebElement postcode;

    @FindBy(css = billingPhone)
    WebElement phone;
    @FindBy(css = billingEmail)
    WebElement email;
    //Service Methods



    public void fillAddressDetails(String address1String, String cityString, String postcodeString, String phoneString,
            String emailAddressString){
        wait.until(d -> d.findElement(By.cssSelector(billingAddress1))).isDisplayed();
        address1.clear();
        address1.sendKeys(address1String);
        city.clear();
        city.sendKeys(cityString);
        postcode.clear();
        postcode.sendKeys(postcodeString);
        phone.clear();
        phone.sendKeys(phoneString);

        email.clear();
        email.sendKeys(emailAddressString);

    }

    public void clickOrderButton(){
        wait.until(d -> d.findElement(By.cssSelector(placeOrderButtonCss))).isDisplayed();
        orderButton.submit();

    }
}
