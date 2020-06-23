Feature: Jira API Interactions

@AddDefect
  Scenario Outline: Add Jira defect
    Given Add issue payload: "<priority>", "<summary>", "<version>"
    When User calls "JiraIssueAPI" resource for issue "RAT-139" with "get" http request
    Then Response status code 201

    Examples: 
      | priority | summary         		   | version |
      | Blocker  | New Blocker Defect		 | 1.4		 |

@DeleteDefect
  Scenario Outline: Delete Jira defect
    When User calls "JiraIssueAPI" resource for issue "<issuekey>" with "delete" http request
    Then Response status code 204
    
    Examples: 
      | issuekey |
      | RAT-140  |
      | RAT-141  |
      | RAT-142  |
      
@GetDefect
  Scenario Outline: Get Jira defect
    When User calls "JiraIssueAPI" resource for issue "<issuekey>" with "get" http request
    Then Response status code 200
    
    Examples: 
      | issuekey |
      | RAT-134  |
      | RAT-135  |
      | RAT-136  |