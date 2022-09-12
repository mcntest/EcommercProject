package org.example.pompages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPomPage {

    WebDriver driver;
    JavascriptExecutor js;

    public static final String cartTitleCss = "h1.entry-title";

    public static final String productPriceCss = "form[class=woocommerce-cart-form] td.product-price";

    public static final String couponCodeInputCss = "input[name=coupon_code]";

    public static final String applyCouponCodeCss = "button[name=apply_coupon]";

    public static final String cartSubTotalCss = "tr[class=cart-subtotal] td[data-title=Subtotal]";

    public static final String cartTotalCss = "tr[class=order-total] td[data-title=Total]";

    public static final String couponValueTextCss = "tr.cart-discount.coupon-edgewords > td  > span";

    public static final String cartMessageCss = "div.woocommerce-message";

    public static final String cartEmptyMessageCss = "p[class='cart-empty woocommerce-info']";

    public static final String clearCartItemCss = "a[class=remove]";

    public static final String checkoutLinkText = "Proceed to checkout";
    WebDriverWait wait;

    public CartPomPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Locators
    @FindBy(linkText = "Cart")
    WebElement cartLink;

    @FindBy(css = cartTitleCss)
    WebElement cartTitle; //Should be "Cart"

    @FindBy(css = productPriceCss)
    WebElement productPriceText; //Should be "Cart"

    @FindBy(css = couponCodeInputCss)
    WebElement couponInputField;

    @FindBy(css = applyCouponCodeCss)
    WebElement applyCouponLink;

    @FindBy(css = cartSubTotalCss)
    WebElement cartSubTotal;

    @FindBy(css = cartTotalCss)
    WebElement cartTotal;

    @FindBy(css = cartMessageCss)
    WebElement cartMessage;

    @FindBy(css = clearCartItemCss)
    WebElement clearCartButton;

    @FindBy(css = cartEmptyMessageCss)
    WebElement cartEmptyMessage;

    @FindBy(css = couponValueTextCss)
    WebElement couponValue;

    @FindBy(linkText = checkoutLinkText)
    WebElement checkoutButton;

    //Service methods

    public void applyCoupon(String couponCode){
        wait.until(d -> d.findElement(By.cssSelector(couponCodeInputCss))).isEnabled();
        //js.executeScript("arguments[0].scrollIntoView()", couponInputField);
        couponInputField.sendKeys(couponCode);
        applyCouponLink.click();
        System.out.println("Applied coupon [" + couponCode + "]");

        wait.until(d -> d.findElement(By.cssSelector(cartMessageCss))).isDisplayed();
        System.out.println("Coupon applied");

    }
    public void clickCheckoutButton(){
        wait.until(d -> d.findElement(By.linkText(checkoutLinkText))).isDisplayed();
        checkoutButton.click();
    }

    //Clear the cart item
    public void clearCart(){
        clearCartButton.click();
        wait.until(d -> d.findElement(By.cssSelector(cartEmptyMessageCss))).isDisplayed();
    }

    //Get the cart message
    public String getCartMessage(){
        return  cartMessage.getText();
    }

    //Get the cart subtotal
    public String getCartSubtotal(){
        return cartSubTotal.getText();
    }

    //Get the cart total
    public String getCartTotal(){
        return cartTotal.getText();
    }

    public String getCouponValue(){
        wait.until(d -> d.findElement(By.cssSelector(couponValueTextCss))).isDisplayed();
        return couponValue.getText();
    }


}
