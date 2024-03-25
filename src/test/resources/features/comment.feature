Feature: Comment

  Scenario: Comment is created with valid attributes
      When I register a new restaurant comment
      Then I should see status code success 201
