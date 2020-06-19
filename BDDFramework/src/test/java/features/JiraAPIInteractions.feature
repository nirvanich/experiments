Feature: Jira API Interactions

  Scenario Outline: Add issue test
    Given Add issue payload: "<priority>", "<summary>", "<version>"
    When User calls "CreateIssueAPI" with post http request
    Then Response status code "201"

    Examples: 
      | priority | summary         		   | version |
      | Blocker  | New Blocker Defect		 | 1.4		 |
      | Regular  | Summary from examples | 1.2		 |
      | Dust	   | CLOSE IT NOW		 			 | 2.0  	 |
