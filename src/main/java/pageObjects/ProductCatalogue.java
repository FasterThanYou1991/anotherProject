package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".mb-3 ")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector("section#products div.container div.row");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public void buttonAddToCart(String productName)
    {
        // 1st name of the product, which we want to select
        // 2nd based on that product we have to select "add to cart" button
        List<WebElement> productCards = driver.findElements(By.cssSelector(".card-body"));
        for(WebElement productCard : productCards)
        {
            String cardText = productCard.getText();
            if(cardText.contains(productName))
            {
                //click on particular button "add to cart"
                WebElement addToCartButton = productCard.findElement(By.xpath(".//button[contains(text(), ' Add To Cart')]"));
                addToCartButton.click();
                break;
            }
        }
    }

    public void addProductToCart(String productName)
    {
        buttonAddToCart(productName);
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
