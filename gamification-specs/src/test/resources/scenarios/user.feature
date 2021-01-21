Feature: User creation and list

  Background:
    Given I have registered and set my application

  Scenario: creation and get of user with event
    Given I have an event payload and a user
    When I POST it to the /events endpoint
    Then I receive a 201 status code
    When I send a GET to the /users/{inGamifiedAppUserId} endpoint
    Then I receive the correct user payload with id match

  Scenario: try to get a wrong user
    Given I have a wrong user
    When I send a GET to the /users/{inGamifiedAppUserId} endpoint
    Then I receive a 404 status code

  Scenario: try to post twice same user
    Given I have an event payload and a user
    When I POST it to the /events endpoint
    And I POST it to the /events endpoint
    Then I receive a 201 status code

  Scenario: get all users
    When I send a GET to the /users endpoint
    Then I receive a 200 status code