#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Place API Feature file
Feature: Add Place API

  @AddPlace
  Scenario Outline: Place given is added
    Given Payload for adding place with "<name>", "<language>", "<Address>"
    When User calls "AddPlace" API with "post" request
    Then statusCode returned is "200"
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place_id created maps to "<name>" using "get" request

    Examples: 
      | name     | language | Address             |
      | House_A1 | English  | random_Address Abc1 |
      | House_A2 | Hindi    | random_address Abc2 |

  @DeletePlace
  Scenario Outline: place given is deleted
    Given Payload for deleting place
    When User calls "DeletePlace" API with "delete" request
    Then statusCode returned is "200"
    And "status" in response is "OK"
