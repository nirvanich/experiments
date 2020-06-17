Feature: Jira API Interactions

  Scenario: Add defect test
    Given Add defect payload
    When User calls "AddDefectAPI" with post http request
    Then Response status code "201"
