package org.example.pompages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;

public class LoginPomPage {
    WebDriver driver;

    public LoginPomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Locators
    @FindBy(linkText = "Dismiss")
    public WebElement dismissLink;

    @FindBy(linkText = "My Account")
    public WebElement accountLink;

    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(css = "button[name=login]")
    public WebElement submitButton;

    //Service Methods
    public LoginPomPage setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPomPage setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void submitForm(){
        submitButton.click();
    }

    public void dismissNoSalesWarning(){dismissLink.click();}

    //Helpers
    public void doLogin(String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        setUsername(username);
        setPassword(password);
        wait.until(d -> d.findElement(By.cssSelector("button[name=login]"))).isDisplayed();
        submitForm();
    }

}
