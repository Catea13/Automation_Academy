Feature: CheckOut

  Scenario Outline: Check that the checkout is possible
    When fill in <field> with <value>
    And fill in <field1> with <value1>
    And click  <button> button
    And click the button <button1>
    And click the button <button2>
    And click the button <button3>
    And fill in <field2> with <value2>
    And fill in <field3> with <value3>
    And fill in <field4> with <value4>

    And click the button <button4>
    Examples:
      | field  | value           | field1     | value1         | button  | button1       | button2                 | button3    | field2       | value2      | field3      | value3 | field4            | value4 | button4    |
      | "Name" | "standard_user" | "Password" | "secret_sauce" | "Login" | "Add to cart" | "ShoppingCartContainer" | "Checkout" | "firstNameCheckoutField" | "Ecaterina" | "lastNameCheckoutField" | "H"    | "Zip/Postal Code" | "1234" | "Continue" |

