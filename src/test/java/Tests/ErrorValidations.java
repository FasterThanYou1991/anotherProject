package Tests;

import TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorValidation"})
    public void submitOrder() throws IOException
    {
        String productName = "ZARA COAT 3";
        landingPage.loginApplication("amadeu.jankowski@gmail.com", "Amadeusz1991");
        landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
    }
    @Test(groups = {"ErrorValidation"})
    public void productErrorValidation() throws IOException
    {

        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("amadeusz.jankowski@gmail.com", "Amadeusz1991");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
