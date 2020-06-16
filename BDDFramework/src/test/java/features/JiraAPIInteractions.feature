Feature: Jira API Interactions
	
	Scenario: Add defect test
		Given Add defect payload
		When User calls "AddDefectAPI" with post http request
		Then the API call is success with status code "201"