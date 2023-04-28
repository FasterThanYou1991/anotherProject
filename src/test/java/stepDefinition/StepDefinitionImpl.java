package stepDefinition;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("Landing on Ecommerce Page")
    public void Landing_on_Ecommerce_Page() throws IOException {
        landingPage = lunchApplication();
    }
    //(.+) -> regular expression which allows us to use any characters.
    //If we're using regular expression we have to use "^" on the beginning and "$" in the end
    @Given("^Logged in with (.+) and (.+)$")
    public void Logged_in_with_username_and_password(String username, String password)
    {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^Add the product (.+) to Cart$")
    public void Add_the_product_productName_to_Cart(String productName)
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_productName_and_submit_the_order(String productName)
    {
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("india");
        confirmationPage = checkOutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    @And("Browser is closed")
    public void browserIsClosed() {
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        browserIsClosed();
    }
}
