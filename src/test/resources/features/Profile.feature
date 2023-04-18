@Profile @WIP
Feature: Buggy Cars Rating User Profile

  Scenario Outline: Update profile
    Given I'm in Buggy Cars Rating profile page
    When I update gender "<Gender>", age "<Age>" and address "<Address>"
    Then I should be able to view save data

    Examples:
      | Gender | Age | Address  |
      | Male   | 32  | Auckland |
      | Female   | 35  | Wellington |