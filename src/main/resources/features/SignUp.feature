@SignUp
Feature: User Registration

  Scenario: User attempts to sign up with an existing username
    When  clicks on the 'Sign Up' button
    And the user enters a username and password and click Sign Up button
    Then an alert should appear with the message 'This user already exist.'


  Scenario: Sign Up - Empty Username
    When  clicks on the 'Sign Up' button
    And the user enters a empty username and password and click Sign Up button
    Then an alert should appear with the message 'Please fill out Username and Password.'


  Scenario: Sign Up - Empty Password
    When  clicks on the 'Sign Up' button
    And the user enters a username and empty password and click Sign Up button
    Then an alert should appear with the message 'Please fill out Username and Password.'
