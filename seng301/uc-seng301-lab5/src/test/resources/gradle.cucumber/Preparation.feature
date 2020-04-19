Feature: Preparation-step
Scenario: Adding a new preparation step
Given I am connected to the lab1 database
And There is a recipe named "Spaghetti bolognese" with 4 steps
When I add a new step "Add salt" to recipe "Spaghetti bolognese"
Then There is a step number 5 for the recipe "Spaghetti bolognese"
And Recipe "Spaghetti bolognese" step number 5 description is "Add salt"