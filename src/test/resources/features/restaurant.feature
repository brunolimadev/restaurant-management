Feature: Restaurant

  Scenario: Restaurant is created with valid attributes
      When I register a new restaurant
      Then I should see status code 201

  Scenario: Restaurant is found by name
    When I search for restaurants by name
    Then I should see status code 200