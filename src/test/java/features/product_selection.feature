Feature: Product Selection and Purchase

  Background:
    Given the user is on the product page

  @reg @cleanCart
  Scenario: Adding a product to the shopping cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart count should increase to 1

  @reg @cleanCart
  Scenario: Adding multiple products to the shopping cart
    When the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart count should increase

  Scenario: Successfully checking out with products in the cart
    Given the user has product in the cart
    When the user proceeds to checkout
    And enters valid shipping information
    Then the user should see the order confirmation message
