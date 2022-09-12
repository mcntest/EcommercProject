package org.example.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmationPomPage {

    WebDriver driver;
    JavascriptExecutor js;

    WebDriverWait wait;

    public OrderConfirmationPomPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static final String confirmationTitleCss = "h1.entry-title";

    public static final String orderNumberCss = "li[class='woocommerce-order-overview__order order'] strong";


    //Locators

    @FindBy(css =confirmationTitleCss )
    WebElement title;

    @FindBy(css =orderNumberCss )
    WebElement orderNumber;


    //Service Methods

    public String getTitle(){
        wait.until(d -> d.findElement(By.cssSelector(confirmationTitleCss))).isDisplayed();
        return title.getText();
    }
    public String getOrderNumber(){
        wait.until(d -> d.findElement(By.cssSelector(orderNumberCss))).isDisplayed();
        return orderNumber.getText();
    }
}
