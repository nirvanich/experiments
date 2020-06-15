Feature: Application Login

  Scenario: Positive login test
    Given User is on login page
    When User login with "correctUserName" and "correctPassword"
    Then Page is opened with correct URL - "http://homepageURL"
    And Logout button is visible

  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
