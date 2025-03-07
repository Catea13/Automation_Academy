@Phone_Purchase
  Feature: Phone Purchase

  Scenario: Successful phone purchase
    Given user is logged in
    When user add random product to cart
    And user places the order from cart
    Then purchase confirmation is received