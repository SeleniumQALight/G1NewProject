  @LoginTest @FullRegression
  Feature: User Login

    @R001
    Scenario Outline: R001 Login with invalid Login
    Given User opens 'Login' page
      When User enters '<login>' text in input 'username' in Login form on 'Home' page
      And User enters 'pass<passWord>' text in input 'password' in Login form on 'Home' page
      When User clicks on button 'Sign up' in Login Form on 'Home' page
      Then User sees alert with text 'Invalid username \ password'

      Examples:
        | login       | passWord     |
        | Wrong Login | User         |
        |             | 123456qwerty |