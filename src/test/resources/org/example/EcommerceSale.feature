Feature: Website sales

  Background: Given I have a user account

  Scenario: Check coupon discount
    Given I am logged into my user account
    When I add an item to the cart
    And I apply a coupon code "edgewords"
    Then the correct 15% discount is applied
    And the cart total is correct

  Scenario: Check order number
    Given  I am logged into my user account
    When I add an item to the cart
    And I purchase an item
    Then the order number corresponds to the stored order number