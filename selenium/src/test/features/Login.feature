Feature: Login functionality

  In order to give login option
  as an user
  I want to be able to login and register

  Scenario: Registration with valid email and password
    Given there is a user with valid email
    And the user is making registration with password WETYJ!@#333
    When the user presses Register button
    Then the user is successfully registered