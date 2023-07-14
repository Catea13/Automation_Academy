Feature: CheckOut

  Scenario: Check that the checkout is possible
    When    fill in "Name" with "standard_user"
    And  fill in "Password" with "secret_sauce"
    And  click  "Login" button
    And click the button "Add to cart"
    And click the button "ShoppingCartContainer"
    And click the button "Checkout"
    And fill in "First Name" with "Ecaterina"
    And fill in "Last Name" with "H"
    And fill in "Zip/Postal Code" with "1234"
    And the user fills out the questionnaire "Checkout" with following data
    Examples:
      | field           | value     |
      | First Name      | Ecaterina |
      | Last Name       | H         |
      | Zip/Postal Code | 1234      |
    And click the button "Continue"

