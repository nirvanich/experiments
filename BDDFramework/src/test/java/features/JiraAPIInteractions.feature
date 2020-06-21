Feature: Jira API Interactions

  Scenario Outline: Add issue test
    Given Add issue payload: "<priority>", "<summary>", "<version>"
    When User calls "JiraIssueAPI" resource for issue "RAT-132" with "post" http request
    Then Response status code "204"

    Examples: 
      | priority | summary         		   | version |
      | Blocker  | New Blocker Defect		 | 1.4		 |

