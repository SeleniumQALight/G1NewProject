@FullRegression
Feature: Check currency

  @R0010

  Scenario Outline: R0010 Check currency UAI and API
    Given User opens 'PrivateBank' page
    When User gets '<currensy>' to UAH Exchange Rate from UAI
    And User gets '<currensy>' to UAH Exchange Rate via API
    Then check is Rate from UAI and Rate via API for equals

    Examples:
      | currensy |
      |   EUR    |
      |   USD    |
      |   RUB    |




