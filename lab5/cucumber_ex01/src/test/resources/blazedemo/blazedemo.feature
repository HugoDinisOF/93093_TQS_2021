Feature: Purchase a flight

  Background: Open the browser
    Given a browser I just opened

  Scenario:
    When I navigate to "https://blazedemo.com/"
    And I choose "Boston" as the departure city
    And I choose "Dublin" as the destination city
    And I click in Find flights
    And I choose the 5 th flight
    And I submit the form to purchase the flight
    Then Then the page title should start with "BlazeDemo Confirmation"