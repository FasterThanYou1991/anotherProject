package Tests;

import TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(String email, String password, String productName)
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\ajankows\\Desktop\\anotherProject\\src\\test\\java\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

    /*@DataProvider
    public Object[][] getData() throws IOException {

        return new Object[][] {{"amadeusz01@gmail.com, "Amadeusz1991", "ZARA COUT 3"}, {"amadeusz.jankowski@gmail.com", "Amadeusz1991", "ADIDAS ORIGINAL"}
    }*/
     /*HashMap<String,String> map = new HashMap<String,String>();
        map.put("email","amadeusz01@gmail.com");
        map.put("password", "Amadeusz1991");
        map.put("product", "ZARA COAT 3");

        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email","amadeusz.jankowski@gmail.com");
        map1.put("password", "Amadeusz1991");
        map1.put("product", "ADIDAS ORIGINAL");*/
}
