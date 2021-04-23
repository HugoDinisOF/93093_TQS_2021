Feature: Basic Arithmetic
  Background: a Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 3 and 2
    Then the result is 5

  Scenario: Subtraction
    When I subtract 7 to 2
    Then the result is 5

  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>

    Examples:
    | a | b | c  |
    | 1 | 2 | 3  |
    | 3 | 7 | 10 |
