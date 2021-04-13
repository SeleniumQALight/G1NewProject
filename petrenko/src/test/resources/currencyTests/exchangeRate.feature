Feature: ExchangeRate.


  @R003

  Scenario Outline: R003 Check exchange rate

    Given User remembers '<currency>' kurs from PrivatBank by API
    When  User open 'Home' page
    Given User remembers '<currency>' kurs from 'HomePage' page
    Then  check '<currency>' API with UI

    Examples:
      | currency |  | currency || currency |
      | USD      |  | USD      || USD      |
      | EUR      |  | EUR      || EUR      |
      | RUB      |  | RUB      || RUB      |
#      | BTC      |  | BTC      || BTC      |