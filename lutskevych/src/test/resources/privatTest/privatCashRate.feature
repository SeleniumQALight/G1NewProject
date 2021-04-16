@CashRateTest @FullRegression
Feature: Case rate feature

    @R003
    Scenario Outline: R003 Check cash rate
      Given User sends API request and saves received cash rates for '<currency>'
      And User saves cash rates for '<currency>' from UI
      Then User check that values from API and UI are equals

       Examples:
          | currency    |
          |     USD     |
          |     EUR     |
