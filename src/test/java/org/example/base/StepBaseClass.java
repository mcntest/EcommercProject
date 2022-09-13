package org.example.base;

import org.example.utils.SharedDictionary;
import org.openqa.selenium.WebDriver;

public class StepBaseClass {
    protected WebDriver driver;
    protected String baseUrl;
    protected final SharedDictionary dict;

    public StepBaseClass(SharedDictionary dictionary){
        this.dict = dictionary;
        //Setup Moved to Hooks file
    }

    public void setUp(){
        driver = (WebDriver) dict.readDict("mywebdriver");
        baseUrl = (String) dict.readDict("baseUrl");
    }
    public void tearDown(){
        driver.quit();
    }
}
