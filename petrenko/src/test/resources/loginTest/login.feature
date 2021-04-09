@LoginTest @FullRegression

Feature: User login.

  @R001
  Scenario Outline: R001 Login with invalid Login

    Given User opens 'Login' page
    When User enters '<login>' login into 'Login'  input on 'Login' page
    And User enters '<passWord>' login into 'passWord'  input on 'Login' page
    And  User click on 'SignIn' button on 'Login' page
    Then Users sees alert message with text 'Invalid username \ password'

    Examples:
      | login       | passWord       |
      | Wrong login | Wrong passWord |
      |             | 123456qwerty   |