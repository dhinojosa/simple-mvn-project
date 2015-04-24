package com.evolutionnext.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.fest.assertions.Assertions.assertThat;

public class VerifyNewAlbum {

    private String baseUrl;
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        baseUrl = System.getenv("selenium_baseurl");
        if (baseUrl == null) throw new NullPointerException("selenium_baseurl not set");

        String selenium_browser = System.getenv("selenium_browser");
        if (selenium_browser == null) throw new NullPointerException("selenium_browser not set");

        switch (selenium_browser) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "Chrome":
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
                break;
            default:
                throw new RuntimeException("No browser specified");
        }
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
