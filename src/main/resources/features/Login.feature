@Login
Feature: Login
  Scenario:User successfully login up with valid details
     When clicks on the 'Log In' button
  Then the user logs in with valid credentials and sees the 'Log Out' and 'Welcome' buttons

  Scenario: Login - Invalid Username
    When clicks on the 'Log In' button
    And the user enters a invalid username and password and click Log in button
    Then an alert should appear with the message 'User does not exist.'


  Scenario: Login - Invalid Password
    When clicks on the 'Log In' button
    And the user enters username and invalid password and click Log in button
    Then an alert should appear with the message 'Wrong password.'


  Scenario: Login - Empty Username
    When clicks on the 'Log In' button
    And the user enters a empty username and password and click Log in button
    Then an alert should appear with the message 'Please fill out Username and Password.'


  Scenario: Login - Empty Password
    When clicks on the 'Log In' button
    And the user enters a username and empty password and click Log in button
    Then an alert should appear with the message 'Please fill out Username and Password.'
