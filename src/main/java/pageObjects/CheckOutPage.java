package pageObjects;

import AbstractComponents.AbstractComponent;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submit;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        waitForWebElementToAppear(country);
        a.sendKeys(country,"india").build().perform();
        setSelectMatchingCountry(countryName);
    }

    public void setSelectMatchingCountry(String name) {
        List<WebElement> options = driver.findElements(By.cssSelector(".ta-item span"));
        for (WebElement option : options)
        {
            if(option.getText().equalsIgnoreCase(name))
            {
                option.click();
                break;
            }
        }
    }

    public ConfirmationPage submitOrder(){
        Actions actions = new Actions(driver);
        scrollDown(submit);
        actions.moveToElement(submit).perform();
        actions.click();
        submit.click();
        return new ConfirmationPage(driver);
    }
}
