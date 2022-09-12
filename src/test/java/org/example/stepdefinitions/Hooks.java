package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.SharedDictionary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private WebDriver driver;

    private final SharedDictionary dict;

    //Pico container will create and inject
    public Hooks(SharedDictionary dict) {
        this.dict = dict;
    }

    @Before(order=0)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        dict.addDict("mywebdriver", driver);
        dict.addDict("baseUrl", "https://www.edgewordstraining.co.uk/demo-site");
    }

    @After(order=0)
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
        System.out.println("Quit driver");
    }
}
