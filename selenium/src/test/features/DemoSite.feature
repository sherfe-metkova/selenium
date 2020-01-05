Feature: Registration and login in demo site
  In order to
  As a
  I want

  Scenario: Shopping items
    Given there is a user on the registration page
    And the user selects the following data
      | FirstName | LastName | Email                 | Phone      | Gender | Country     | Year | Month | Day | Password   |
      | James     | Jones    | james.jones@gmail.com | 0888789654 | Male   | Afghanistan | 1989 | January    | 12   | Cactuss*13 |
    When the user presses button Submit
    Then the user is registered for the demo site