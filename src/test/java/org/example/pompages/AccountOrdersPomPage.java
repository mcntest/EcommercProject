package org.example.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountOrdersPomPage {
    WebDriver driver;
    JavascriptExecutor js;

    WebDriverWait wait;
    public AccountOrdersPomPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Locators

    final static String  orderTableCss = "div[class=woocommerce-MyAccount-content]";
    final static String  orderNumberCellCss = "table[class^=woocommerce-orders-table] > tbody > tr > td";
    @FindBy(linkText = "Orders")
    WebElement ordersLink;
    @FindBy(linkText = "My account")
    WebElement accountLink;

    @FindBy(css = orderTableCss )
    WebElement orderTable;

    @FindBy(css = orderNumberCellCss )
    WebElement firstOrderNumberCell;

    //Service Methods

    //Prepends a # to order number
    public String getOrderNumberFromTable(){
        //Go to My Accounts
        wait.until(d -> d.findElement(By.linkText("My account"))).isDisplayed();
        accountLink.click();

        wait.until(d -> d.findElement(By.linkText("Orders"))).isDisplayed();
        ordersLink.click();


        wait.until(d -> d.findElement(By.cssSelector(orderTableCss))).isDisplayed();
        String firstCell = firstOrderNumberCell.getText();
        System.out.println("First Cell " + firstCell );

       return firstCell;

    }


}
