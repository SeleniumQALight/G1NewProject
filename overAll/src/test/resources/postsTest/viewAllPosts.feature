  @PostsTest @FullRegression
  Feature: Posts

    Background:
      Given User opens 'Home' page


    @R002
    @BeforeDeletingAllPostForDefaultUser
    @AfterDeletingAllPostForDefaultUser
    Scenario: R002 The View of all posts
      And Create 4 new posts via API for 'default' user and 'default' passWord
      When User click on 'Profile' button
      Then User is redirect to 'Profile' page
      And User sees 4 posts in 'Posts' list on 'Profile' page