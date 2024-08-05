package org.example;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;
//import utils.PropertyManager;
import java.sql.Driver;
import java.time.Duration;
import java.net.URL;


public class CssSelectors {
    public static WebDriver webDriver;

    @BeforeClass
    public void chrome(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/org/example/drivers/chromedriver.exe");

        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--start-maximized");

        webDriver=new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
    }

    @Test
    public void click(){
        webDriver.get("https://demoqa.com/elements");

        WebElement buttons=webDriver.findElement(By.cssSelector("li:nth-of-type(5)>.text"));
        buttons.click();

        WebElement clickMe=webDriver.findElement(By.cssSelector("div.col-md-6 div:nth-of-type(3) >button"));
        clickMe.click();

        WebElement showMessage=webDriver.findElement(By.cssSelector("#dynamicClickMessage"));
        showMessage.click();

        Assert.assertTrue(showMessage.isDisplayed());
        Assert.assertEquals(showMessage.getText(),"You have done a dynamic click");

    }

    @Test
    public void record(){
        webDriver.get("https://demoqa.com/webtables");


        WebElement add=webDriver.findElement(By.cssSelector("#addNewRecordButton"));
        add.click();

        //region[Veri girme]
        WebElement fName=webDriver.findElement(By.cssSelector("#firstName"));
        fName.click();
        fName.sendKeys("Özen");

        WebElement lName=webDriver.findElement(By.cssSelector("#lastName"));
        lName.click();
        lName.sendKeys("Taştan");

        WebElement mail=webDriver.findElement(By.cssSelector("#userEmail"));
        mail.click();
        mail.sendKeys("ozentastan1@gmail.com");

        WebElement age=webDriver.findElement(By.cssSelector("#age"));
        age.click();
        age.sendKeys("27");

        WebElement salary=webDriver.findElement(By.cssSelector("#salary"));
        salary.click();
        salary.sendKeys("123456789");

        WebElement dep=webDriver.findElement(By.cssSelector("#department"));
        dep.click();
        dep.sendKeys("Meva");

//endregion

        WebElement sub=webDriver.findElement(By.cssSelector("#submit"));
        sub.click();
        WebElement depText =webDriver.findElement(By.cssSelector(".rt-tr-group:nth-child(4) div:nth-of-type(6)"));
        String showDepText=depText.getText();

        WebElement edit=webDriver.findElement(By.cssSelector("#edit-record-4"));
        edit.click();

        WebElement dep2=webDriver.findElement(By.cssSelector("#department"));
        dep2.click();
        dep2.sendKeys("Meva-Ökc");

        WebElement sub2=webDriver.findElement(By.cssSelector("#submit"));
        sub2.click();

        WebElement dep2Text =webDriver.findElement(By.cssSelector(".rt-tr-group:nth-child(4) div:nth-of-type(6)"));
        String showDep2Text=dep2Text.getText();

        Assert.assertFalse(EqualsBuilder.reflectionEquals(showDepText,showDep2Text),"Veri değiştirildi");

    }

    @AfterClass
    public void quitDriver() {
        webDriver.quit();
    }
}
