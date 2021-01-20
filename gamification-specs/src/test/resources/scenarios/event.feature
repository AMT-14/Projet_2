Feature: Event creation and list
  
  Background: 
    Given I have registered and set my application
    
  Scenario: creation of event
    Given I have an event payload
    When I POST it to the /events endpoint
    Then I receive a 201 status code