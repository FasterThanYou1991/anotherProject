Feature: Purchase the order from ecommerce website

  Background:
    Given Landing on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submit the order
    Given Logged in with <username> and <password>
    When Add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    And Browser is closed


    Examples:
      | username                     | password     |productName|
      | amadeusz.jankowski@gmail.com | Amadeusz1991 |ZARA COAT 3|