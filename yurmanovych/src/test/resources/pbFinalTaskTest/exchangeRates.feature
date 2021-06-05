@PrivatbankTest
  Feature: ExchangeRatesTest

    @R001
    Scenario: R001 Get API exchange rates
      Given Exchange rates are received from Privatbank API
      When Privatbank 'Main Page' is open and the exchange rates caption is visible
      Then Verify the exchange rates on the 'Main Page' are same as received from the API
