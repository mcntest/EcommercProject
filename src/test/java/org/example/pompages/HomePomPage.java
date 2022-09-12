package org.example.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePomPage {

    WebDriver driver;

    public HomePomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    //Locators
    @FindBy(linkText = "My account")
    WebElement accountLink;

    @FindBy(linkText = "Logout")
    WebElement logoutLink;

    @FindBy(linkText = "Shop")
    WebElement shopLink;

    @FindBy(linkText = "Cart")
    WebElement cartLink;

    //Service Methods
    public void goMyAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //js.executeScript("arguments[0].scrollIntoView()", loginLink);
        wait.until(d -> d.findElement(By.linkText("My account"))).isDisplayed();
        accountLink.click();
    }

    public void logoutAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(d -> d.findElement(By.linkText("My account"))).isDisplayed();
        accountLink.click();
        wait.until(d -> d.findElement(By.linkText("Logout"))).isDisplayed();
        logoutLink.click();
    }

    public void goShop(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //js.executeScript("arguments[0].scrollIntoView()", loginLink);
        //wait.until(d -> d.findElement(By.linkText("My account"))).isDisplayed();
        shopLink.click();
    }

    public void goCart(){
        cartLink.click();
    }

}
