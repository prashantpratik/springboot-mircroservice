  Feature: As an api user I want to retrieve some Git oldest user accounts

    Scenario: Get 5 oldest accounts
      When I get the service uri oldestAccounts/5
      Then the service uri returns status code 200
      And the content type is json
      And the body has json length that is equal to 5
      And the body has json path $.[0] of type object
      And the body has json path $.[0].id of type numeric
      And the body has json path $.[0].login of type string
      And the body has json path $.[0].html_url of type string
