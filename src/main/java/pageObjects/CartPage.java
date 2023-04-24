package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".totalRow button")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutEle;

    public Boolean verifyProductDisplay(String productName){
        WebElement productInCart = driver.findElement(By.xpath("//div[@class='cartSection']//h3"));
        String productInCartName = productInCart.getText();
        return productInCartName.equalsIgnoreCase(productName);
    }

    public CheckOutPage goToCheckout() {
        scrollDown(checkoutEle);
        waitForElementToBeClickAble(checkoutEle);
        checkoutEle.click();
        return new CheckOutPage(driver);
    }
}
