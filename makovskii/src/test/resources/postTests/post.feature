@PostsTest @FullRegression
Feature: Post feature

  Background:
      Given User opens 'Home' page

  @R002
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser
  Scenario: R002 Check number of posts
    And Create 2 new posts via API for 'default' user and 'default' password
    When User click on 'Profile' button
    Then User is redirect to 'Profile' page
    And User sees 0 posts in 'Posts list' on 'Profile' page


