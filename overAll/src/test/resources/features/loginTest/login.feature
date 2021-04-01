@FullRegression
Feature: User Login.


    @R001
    Scenario: R001 Login with invalid Login

        Given User opens 'Home' page
        When User enters 'Wrong Login' text in input 'username' in Login form on 'Home' page
        And User enters 'User' text in input 'password' in Login form on 'Home' page
        When User clicks on button 'Sign up' in Login Form on 'Home' page
        Then User sees alert with text 'Invalid username / password'






