Feature: Application registration

  Scenario: Register another application
    Given I have an application payload
    When I POST it to the /applications endpoint
    Then I receive a 200 status code

    #will fail for now, need to implement a get method on application
  Scenario: Check if the application is correctly registered
    Given I have an application payload
    When I POST it to the /applications endpoint
#    And I ask for a list of registered applications with a GET on the /registrations endpoint
    Then I see my application in the list

  Scenario: Check that it is possible to create two applications with the same name
    Given I have an application payload
    When I POST it to the /applications endpoint
    And I POST it to the /applications endpoint
    Then I receive a 200 status code