Feature: Error Validations

  @ErrorValidation
  Scenario Outline:


    Given Landing on Ecommerce Page
    Given Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username                     | password     |
      | amadeusz.jankowski@gmail.com | Amadeusz1991 |
