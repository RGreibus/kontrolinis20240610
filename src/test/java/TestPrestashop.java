import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPrestashop {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://192.168.1.147/");
    }
    @AfterEach
    void teardown(){
        driver.quit();

    }
    @Test
    public void testCreateAccountAndSignIn(){
        driver.findElement(By.cssSelector("#_desktop_user_info")).click();
        driver.findElement(By.cssSelector("#content > div > a")).click();
        driver.findElement(By.cssSelector("#field-email")).sendKeys("rasa.basa@gmail.com");
        driver.findElement(By.cssSelector("#field-password")).sendKeys("rasaBasa123");
        driver.findElement(By.cssSelector("#field-firstname")).sendKeys("Rasa");
        driver.findElement(By.cssSelector("#field-lastname")).sendKeys("Basa");
        driver.findElement(By.name("psgdpr")).click();
        driver.findElement(By.name("customer_privacy")).click();
        driver.findElement(By.cssSelector("#customer-form > footer > button")).click();

        WebElement userAccount = driver.findElement(By.className("hidden-sm-down"));
        assertTrue(userAccount.isDisplayed(), "User should mach registered User and be logged in");

        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[1]")).click();

        driver.findElement(By.cssSelector("#_desktop_user_info")).click();
        driver.findElement(By.cssSelector("#field-email")).sendKeys("rasa.basa@gmail.com");
        driver.findElement(By.id("field-password")).sendKeys("rasaBasa123");
        driver.findElement(By.cssSelector("#submit-login")).click();

        WebElement userSignInSuccessful = driver.findElement(By.className("hidden-sm-down"));
        assertTrue(userSignInSuccessful.isDisplayed(), "User should logged in");
    }
    @Test
    public void testAddToWishlistAndCart(){
        WebElement searchField = driver.findElement(By.cssSelector("#search_widget > form > input.ui-autocomplete-input"));
        searchField.sendKeys("Hummingbird printed t-shirt");
        searchField.sendKeys(Keys.RETURN);

        WebElement productLink = driver.findElement(By.cssSelector(".product-description > .product-title"));
        assertTrue(productLink.isDisplayed(), "Product is not displayed on the screen");
        assertEquals("Hummingbird Printed T-Shirt", productLink.getText(),  "Product name does not match");

        driver.findElement(By.cssSelector("#js-product-list > div.products.row > div > article > div > button")).click();

        WebElement infoMessage = driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[1]/div[7]/div[1]/div/div/div[2]/p"));
        assertTrue(infoMessage.isDisplayed(), "Info message is not displayed");
        assertEquals("You need to be logged in to save products in your wishlist.", infoMessage.getText(), "You need to be logged in to save products in your wishlist.");

        driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div/div[1]/div[7]/div[1]/div/div/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[2]/h2/a")).click();

    }
}
