Feature: Restaurant

  Scenario: Restaurant is created with valid attributes
      When I register a new restaurant
      Then I should see status code 201
