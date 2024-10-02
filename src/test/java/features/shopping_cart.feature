Feature: Shopping Cart Management

  Background:
    Given the user is logged in

  Scenario: Removing a product from the shopping cart
    Given the user has 2 products in the cart
    When the user removes "Saucedemo T-Shirt" from the cart
    Then the cart count should decrease to 1

  Scenario: Attempting to checkout with an empty cart
    Given the user has no products in the cart
    When the user tries to proceed to checkout
    Then an appropriate message should be displayed stating "Your cart is empty!"

  Scenario: Verifying the cart displays correct products
    Given the user has added "Saucedemo Backpack" to the cart
    When the user views the cart
    Then the cart should display "Saucedemo Backpack"
