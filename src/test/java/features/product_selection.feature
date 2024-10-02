Feature: Product Selection and Purchase

  Background:
    Given the user is logged in

  Scenario: Adding a product to the shopping cart
    When the user selects the product "Saucedemo Backpack"
    And adds the product to the cart
    Then the cart count should increase to 1

  Scenario: Adding multiple products to the shopping cart
    When the user selects the product "Saucedemo Bike Light"
    And adds the product to the cart
    And the user selects the product "Saucedemo T-Shirt"
    And adds the product to the cart
    Then the cart count should increase to 2

  Scenario: Successfully checking out with products in the cart
    Given the user has 1 product in the cart
    When the user proceeds to checkout
    And enters valid shipping information
    Then the user should see the order confirmation message
