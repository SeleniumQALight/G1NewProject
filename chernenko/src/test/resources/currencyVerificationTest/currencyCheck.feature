@CurrencyVerificationTest @FullRegression
Feature: NBU Currency verification.
  .

  @R003
  Scenario Outline: R003 Verification currency gotten from API request with currency displayed on the Privat landing page
    Given User gets buy and sale prices by '<currencyName API>' currency name using API request
    When User gets '<currencyName UI>' exchange value from landing page
    Then User sees two values are equal

    Examples:
      | currencyName UI | currencyName API |
      | USD             | USD              |
      | EUR             | EUR              |
      | RUB             | RUR              |






