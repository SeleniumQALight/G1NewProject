@PostsTest
@FullRegression
Feature: Post

  Background:
    Given User opens 'Home' page

  @R002
  @BeforeDeleteAllPostsForDefaultUser
  @AfterDeleteAllPostsForDefaultUser
  Scenario: R002 Verify number of Posts
    And Create 3 new posts via API for 'default' user and 'default' password
    When User clicks 'Profile' button
    Then User is redirected to 'Profile' page
    And User sees 3 posts in 'Posts list' on 'Profile' page


