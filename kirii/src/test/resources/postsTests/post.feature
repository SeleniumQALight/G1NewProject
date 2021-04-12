@PostsTest @FullRegression
Feature: Post feature

  Background:
    Given User opens 'Home' page

  @R001
    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
  Scenario: : R002 Check number of post
    And Create 2 new posts via API for 'default' user and 'default' password
    When User clicks on 'Profile' button
    Then User is redirected to 'Profile' page
    And User sees 2 posts in 'Posts list' on 'Profile' page