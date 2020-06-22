Feature: Jira API Interactions

  Scenario Outline: Jira issue test
    Given Add issue payload: "<priority>", "<summary>", "<version>"
    When User calls "JiraIssueAPI" resource for issue "RAT-139" with "get" http request
    Then Response status code 200

    Examples: 
      | priority | summary         		   | version |
      | Blocker  | New Blocker Defect		 | 1.4		 |

