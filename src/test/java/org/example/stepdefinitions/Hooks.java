package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    WebDriver driver;
    String baseUrl = "https://www.edgewordstraining.co.uk/demo-site";
    protected  WebDriver getDriver(){
        return driver;
    }

//Not used yet

//    @Before
//    public void setup(){
////        WebDriverManager.chromedriver().setup();
////        driver = new ChromeDriver();
//    }
//    @After
//    public void teardown(){
//       // driver.quit();
//    }
}
