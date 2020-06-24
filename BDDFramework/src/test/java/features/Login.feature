Feature: Application Login

@RAT-90 
  Scenario: Positive login test
    Given User is on login page
    When User login with "correctUserName" and "correctPassword"
    Then Page is opened with correct URL - "http://homepageURL"
    And Logout button is visible

@RAT-134 
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
    
@RAT-135
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"

@RAT-136
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
    
@RAT-137
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
    