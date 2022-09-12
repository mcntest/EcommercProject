package org.example.pompages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPomPage {

    WebDriver driver;

    public static final String productTitleCss = ".product_title";
    public static final String addToCartButtonCss = "button[name=add-to-cart]";

    public static final String viewCartLinkCss = "a[class=cart-contents]";

    public static final String productAddedMessageCss = "div.woocommerce-message";
    WebDriverWait wait;

    public ShopPomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Locators

    @FindBy(id = "woocommerce-product-search-field-0")
    WebElement shopSearchForm;

    /*
    This doesnt work - it simply displays the product image and not the ad to cart form
     */
    @FindBy(css = "form[class=search-form] input[type=search]")
    WebElement shopSearchFormDoesntWork;

    @FindBy(css = productTitleCss)
    WebElement productTitle;

    @FindBy(css = addToCartButtonCss)
    WebElement addToCartButton;

    @FindBy(css = viewCartLinkCss)
    WebElement viewCartLink;

    @FindBy(css = productAddedMessageCss)
    WebElement productAddedMessage;



    //Service Methods
    public void searchProductByName(String productName){
        System.out.println("Searching product..."  + productName);
        //find search input
        shopSearchForm.sendKeys(productName + Keys.ENTER);

        //js.executeScript("arguments[0].scrollIntoView()", loginLink);
        wait.until(d -> d.findElement(By.cssSelector(productTitleCss))).isDisplayed();
        System.out.println("Product is: " + productTitle.getText());
    }

    public void addToCart()  {
        addToCartButton.click();
       String message = this.getProductAddedMessage();
    }

    //To be re-used for assertions
    public String getProductAddedMessage(){
        wait.until(d -> d.findElement(By.cssSelector(productAddedMessageCss))).isDisplayed();
        String message = productAddedMessage.getText();
        System.out.println(message);
        return message;
    }

    public void viewCart(){
        viewCartLink.click();
    }



}
