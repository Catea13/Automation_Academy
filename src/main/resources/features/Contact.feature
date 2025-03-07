@Contact
Feature: Contact

  Scenario: Contact-New Message
    When clicks on the 'Contact' button
    And fill in the New Message Popup with the contact email, contact name, and message
    And clicks on the 'Send Message' button
    Then an alert should appear with the message 'Thanks for the message!!'

  @missingEmail
  Scenario: Contact - Missing Email
    When clicks on the 'Contact' button
    And fill in the New Message Popup with the contact name, and message
    And clicks on the 'Send Message' button
    Then an alert should appear with the message 'Please enter a valid email address.'


  Scenario: Contact - Missing Name
    When clicks on the 'Contact' button
    And fill in the New Message Popup with the contact email and message
    And clicks on the 'Send Message' button
    Then an alert should appear with the message 'Please enter a valid name address.'


  Scenario: Contact - Missing Message
    When clicks on the 'Contact' button
    And fill in the New Message Popup with the contact email and contact name
    And clicks on the 'Send Message' button
    Then an alert should appear with the message 'Please enter message.'

  @invalidEmailFormat
  Scenario: Contact - Invalid Email Format
    When clicks on the 'Contact' button
    And fill in the New Message Popup with the invalid email format, valid contact name, and valid message
    And clicks on the 'Send Message' button
    Then an alert should appear with the message 'Please enter a valid email address'
