Feature: Badge interaction

  Background:
    Given there is an Application server

  Scenario: creation of badge
    Given I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 201 status code

  Scenario: Check if the badge is correctly registered
    Given I have a badge payload
    When I POST it to the /badges endpoint
    And I ask for this badge with a GET on the /badges/id endpoint

  Scenario: Check that it isn't possible to create two badges with the same name
    Given I have a badge payload
    When I POST it to the /badges endpoint
    And I POST it to the /badges endpoint
    Then I receive a 422 status code

