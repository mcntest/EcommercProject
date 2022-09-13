package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pompages.*;
import org.example.utils.SharedDictionary;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderFeatureDefinitionSteps {
    HomePomPage homePage;
    LoginPomPage loginPage;

    ShopPomPage shopPage;

    CartPomPage cartPage;

    CheckoutPomPage checkoutPage;

    OrderConfirmationPomPage confirmationPage;

    AccountOrdersPomPage accountOrdersPage;
    WebDriver driver;
    String baseUrl;// = "https://www.edgewordstraining.co.uk/demo-site";

    JavascriptExecutor js;

    private final SharedDictionary dict;

    public OrderFeatureDefinitionSteps(SharedDictionary dictionary) {
        this.dict = dictionary;
        //Setup Moved to Hooks file
        driver = (WebDriver) dict.readDict("mywebdriver");
        baseUrl = (String) dict.readDict("baseUrl");
        homePage = new HomePomPage(driver);
        loginPage = new LoginPomPage(driver);
        shopPage = new ShopPomPage(driver);
        cartPage = new CartPomPage(driver);
        checkoutPage = new CheckoutPomPage(driver);
        confirmationPage = new OrderConfirmationPomPage(driver);
        accountOrdersPage = new AccountOrdersPomPage(driver);
        js = (JavascriptExecutor) driver;
    }



    /*
    Logout after each test and quit driver
     */
    @After(order=1)
    public void tearDown() {
        //Clear cart
        homePage.goCart();
        try{
            cartPage.clearCart();
            System.out.println("Cart clear");
        }catch (Exception e){
            System.out.println("Could not clear cart " + e.getMessage());
        }

        //Logout
        homePage.logoutAccount();
        System.out.println("Logged out");

        //Quit driver - moved to Hooks
        //driver.quit();
        //System.out.println("Quit driver");
    }
    @Given("I am logged into my user account")
    public void i_am_logged_into_my_user_account() {
        String email = "peter.mcnaught@2itesting.com";
        String pw = "U5j*4rufdp[";
        driver.get(baseUrl);
        homePage.goMyAccount();
        loginPage.dismissNoSalesWarning();
        loginPage.doLogin(email,pw );
        System.out.println("Login complete");

    }

    @When("I add an item to the cart")
    public void i_add_an_item_to_the_cart() {
        homePage.goShop();
        System.out.println("Enter shop");
        shopPage.searchProductByName("cap");
        shopPage.addToCart();

        String productAddedMessage = shopPage.getProductAddedMessage();
        MatcherAssert.assertThat("Could not verify product in cart", productAddedMessage.endsWith(" has been added to your cart."));
        shopPage.viewCart();

    }

    @When("I apply a coupon code {string}")
    public void i_apply_a_coupon_code(String coupon) {

        System.out.println("Applying coupon");
        cartPage.applyCoupon(coupon);

    }

    /*
    Put assertions in @Then methods
     */
    @Then("the correct {double}% discount is applied")
    public void the_correct_discount_is_applied(double discountPercent) {
        double itemValue = 16.00;
        double itemDiscount = discountPercent/100;
        double itemDiscountValue = itemValue * itemDiscount;
        String subtotalAsString = NumberFormat.getCurrencyInstance(new Locale("en", "GB"))
                .format(itemValue);
        String discountAsString = NumberFormat.getCurrencyInstance(new Locale("en", "GB"))
                .format(itemDiscountValue);


        //Assert the discount is applied correctly
        String message = cartPage.getCartMessage();
        System.out.println("Message is: " + message);
        MatcherAssert.assertThat("Could not apply coupon " , message.startsWith("Coupon code applied successfully"));

        String cartSubtotal = cartPage.getCartSubtotal();
        System.out.println("Subtotal: " + cartSubtotal);
        MatcherAssert.assertThat("Incorrect subtotal displayed " , cartSubtotal.equals(subtotalAsString));

        String couponDiscount = cartPage.getCouponValue();
        System.out.println("Discount: " + couponDiscount);
        MatcherAssert.assertThat("Incorrect coupon value " +discountAsString  , couponDiscount.equals(discountAsString));

    }

    @Then("the cart total is correct")
    public void the_cart_total_is_correct() {
        double cartTotalValue = 17.55;
        String cartTotalAsString = NumberFormat.getCurrencyInstance(new Locale("en", "GB"))
                .format(cartTotalValue);

        String cartTotal = cartPage.getCartTotal();
        System.out.println("Total: " + cartTotal);
        MatcherAssert.assertThat("Incorrect cart total value" , cartTotal.equals(cartTotalAsString));
    }


    //Assumes we have an item in the cart and have the cart displayed
    @When("I purchase an item")
    public void i_purchase_an_item() {
        System.out.println("Purchase an item");
        cartPage.clickCheckoutButton();
        checkoutPage.fillAddressDetails("1 High Street", "Musselburgh", "EH217UL", "01316657654", "peter.mcnaught@yahoo.co.uk");
        checkoutPage.clickOrderButton();

        //get the order details on the confirmation page
        String title = confirmationPage.getTitle();
        String orderNumber = confirmationPage.getOrderNumber();
        System.out.println("Title " + title);
        System.out.println("Number " + orderNumber);

        //Store order number for future reference
        dict.addDict("OrderNumber",orderNumber );
    }

    @Then("the order number corresponds to the stored order number")
    public void the_order_corresponds_to_the_stored_number() {
        String orderNumber = (String)dict.readDict("OrderNumber");
        System.out.println("Checking order corresponds to " + orderNumber);
        String tableOrderNumber = accountOrdersPage.getOrderNumberFromTable();
        MatcherAssert.assertThat("Order number could not be found in order table" , tableOrderNumber.equals("#" + orderNumber));
    }

}
