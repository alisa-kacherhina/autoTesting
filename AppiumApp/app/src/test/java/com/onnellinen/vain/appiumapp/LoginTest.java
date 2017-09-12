package com.onnellinen.vain.appiumapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by vain onnellinen on 08.09.2017.
 */
public class LoginTest {
    private AndroidDriver driver;
    private String packageName = "ru.bpc.mobilebank.android:id/";
    private String correctNumber = "+79262310810";
    private String correctPass = "qwe123";
    private String correctCode = "012345";
    private String wrongPass = "123123";

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "FA381W913620");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "5.0.2");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "ru.bpc.mobilebank.android");

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "ru.bpc.mobilebank.android.activity.login.LoginActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }

    @Test
    public void testCorrectLogin() {
        enterCorrectLogin();
        enterCorrectPass();
        enterCorrectActivationCode();
    }

    @Test
    public void testErrorPassLogin() {
        enterCorrectLogin();
        enterWrongPass();
    }

    @After
    public void End() {
        driver.quit();
    }

    private void setUpDemoServer() {
        //find views by id (from uiAutomatorViewer)
        By btnSetServer = By.id(packageName + "app_settings");
        By switchDemo = By.id(packageName + "simulator_mode");
        By btnSave = By.id(packageName + "save_button");

        //make steps you want to check
        driver.findElement(btnSetServer).click();
        driver.findElement(switchDemo).click();
        driver.findElement(btnSave).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void enterCorrectLogin() {
        //find views by id (from uiAutomatorViewer)
        By loginEditText = By.id(packageName + "login");
        By btnContinue = By.id(packageName + "custom_button_continue_text");

        //make steps you want to check
        driver.findElement(loginEditText).sendKeys(correctNumber);
        driver.findElement(btnContinue).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void enterCorrectPass() {
        //find views by id (from uiAutomatorViewer)
        By passEditText = By.id(packageName + "password");
        By btnContinue = By.id(packageName + "custom_button_continue");

        //make steps you want to check
        driver.findElement(passEditText).sendKeys(correctPass);
        driver.findElement(btnContinue).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void enterCorrectActivationCode() {
        //find views by id (from uiAutomatorViewer)
        By codeEditText = By.id(packageName + "confirm_code");
        By btnContinue = By.id(packageName + "custom_button_continue");

        //make steps you want to check
        driver.findElement(codeEditText).sendKeys(correctCode);
        driver.findElement(btnContinue).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void enterWrongPass() {
        //find views by id (from uiAutomatorViewer)
        By passEditText = By.id(packageName + "password");
        By btnContinue = By.id(packageName + "custom_button_continue");

        //make steps you want to check
        driver.findElement(passEditText).sendKeys(wrongPass);
        driver.findElement(btnContinue).click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
