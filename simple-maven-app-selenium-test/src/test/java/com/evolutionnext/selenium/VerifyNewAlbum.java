package com.evolutionnext.selenium;

import com.evolutionnext.junit.category.ChromeTest;
import com.evolutionnext.junit.category.FirefoxTest;
import com.evolutionnext.junit.category.IETest;
import com.evolutionnext.junit.category.SafariTest;
import com.evolutionnext.selenium.WindowsTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class VerifyNewAlbum {

    private String baseUrl;
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://54.188.233.107:8080";
    }

    @Before
    @Category(value={FirefoxTest.class})
    public void setUpFirefox() {
        driver = new FirefoxDriver();
    }


    @Before
    @Category(value={IETest.class})
    public void setUpIETest() {
        driver = new InternetExplorerDriver();
    }

    @Before
    @Category(value={SafariTest.class})
    public void setUpSafariTest() {
        driver = new SafariDriver();
    }

    @Before
    @Category(value = {ChromeTest.class})
    public void setUpChrome() {
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        switch (osName) {
            case "Windows":
                setChromeSystemProperty("chromedriver-windows32");
                break;
            case "MacOSX":
                setChromeSystemProperty("chromedriver-mac32");
                break;
            case "Linux":
                if (osArch.equals("amd64")) {
                    setChromeSystemProperty("chromedriver-linux64");
                } else {
                    setChromeSystemProperty("chromedriver-linux32");
                }
                break;
        }
        driver = new ChromeDriver();
    }


    @SuppressWarnings("ConstantConditions")
    private void setChromeSystemProperty(String osName) {
        System.setProperty("webdriver.chrome.driver",
                getClass().getClassLoader().getResource(osName + "/chromedriver").getPath());
    }


    @Test
    public void testPage() throws InterruptedException {
        driver.get(baseUrl + "/simple-maven-app/create.xhtml");
        driver.findElement(By.name("album_form:j_idt6")).clear();
        driver.findElement(By.name("album_form:j_idt6")).sendKeys("Senses Working Overtime");
        driver.findElement(By.name("album_form:j_idt8")).click();
        Thread.sleep(5000);
        assertThat(driver.getTitle()).isEqualTo("List of Albums");
        driver.quit();
    }

}
