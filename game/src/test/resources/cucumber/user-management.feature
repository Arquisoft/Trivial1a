# language: en
Feature: User management

Scenario: Create first user

    Given there are no users
    When I create a user "Pepe" with password "Pepe12"
    Then The number of users is 1

Scenario: Check that a user exists
	Given a list of users:
      | name    | password |
      | pepe    | pepe12   |
      | luis    | siul     |
      | mari    | 2mmm2    |
    When I login with name "pepe" and password "pepe12"
    Then I receive a welcome message

Scenario: Login with empty users
	Given there are no users
    When I login with name "pepe" and password "pepe13"
    Then I receive a failure message
    