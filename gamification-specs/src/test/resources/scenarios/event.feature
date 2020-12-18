Feature: Event creation and list
  
  Background: 
    Given there is an Application server
    
  Scenario: creation of event
    Given I have an event payload
    When I POST it to the /events endpoint
    Then I receive a 201 status code