Feature: AddToCartProduct
  Scenario: check that user can add to cart product
    When    fill in "Name" with "standard_user"
    And  fill in "Password" with "secret_sauce"
    And  click  "Login" button
    And click the button "Add to cart"
    And click the button "ShoppingCartContainer"

