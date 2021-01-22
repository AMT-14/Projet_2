Feature: Scorescale creation

  Background:
    Given I have registered and set my application

  Scenario: creation of a scoreScale
    Given I have a score payload
    When I POST it to the /scoreScales endpoint
    Then I receive a 201 status code

#  Scenario: creation of a scoreScale and GET
#    Given I have a score payload
#    When I POST it to the /scoreScales endpoint
#    Then I receive a 201 status code
##    When I send a GET to the /scoreScales/{id} endpoint
#    Then I receive the correct score payload with id match