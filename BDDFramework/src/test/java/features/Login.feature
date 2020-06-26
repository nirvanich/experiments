Feature: Application Login

@Regression
@Issue("#RAT-90") 
  Scenario: Positive login test
    Given User is on login page
    When User login with "correctUserName" and "correctPassword"
    Then Page is opened with correct URL - "http://homepageURL"
    And Logout button is visible

@Issue("#RAT-134")
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"

@Issue("#RAT-135") 
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"

@Issue("#RAT-136")
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
    And Response status code 201
    
@Issue("#RAT-137")
  Scenario: Negative login test
    Given User is on login page
    When User login with "incorrectUserName" and "incorrectPassword"
    Then Page is opened with correct URL - "http://errorPageURL"
    And Correct error message is displayed - "incorrectCredentialsErrorMessage"
    