Feature: Currencies Rates Comparison

  @R003
  Scenario Outline: R003 Compare PrivatBank Expected and Actual Currencies Rates
    Given User gets '<currency>' rates via PrivatBank API
    When 'PrivatBank' main page opens
    Given User gets '<currency>' rates via PrivatBank UI
    Then User compares PrivatBank Expected and Actual '<currency>' Buy and Sale rates

    Examples:
      | currency  |
      | EUR       |
      | USD       |
      | RUR       |


