package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    @FindBy(css = "h1.ng-star-inserted")
    WebElement yourOrdersHeader;

    public boolean verifyOrderDisplay(String productName){
        for(WebElement element : productNames)
        {
            if(element.getText().equalsIgnoreCase(productName))
            {
                return true;
            }
        }
        return false;
    }

}
