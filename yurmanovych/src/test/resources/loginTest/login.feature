@LoginTest
@FullRegression
Feature: User login

  @R001
  Scenario Outline: R001 Login with invalid login
    Given User opens 'Login' page
    When User enters '<login>' login on 'Login' page
    And User enters '<password>' password on 'Login' page
    And User clicks on 'SignIn' button on 'Login' page
    Then User gets alert message with text 'Invalid username \ password'

    Examples:
      | login       | password     |
      | Wrong login | Wrong pass   |
      |             | 123456qwerty |

