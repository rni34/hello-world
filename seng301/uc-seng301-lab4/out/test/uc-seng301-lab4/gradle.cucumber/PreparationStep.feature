Feature: Preparation-step CRUD operations
Scenario: Updating a preparation step description
Given I am connected to the lab1 database
And There is a recipe named "Spaghetti bolognese"
And There is a step number 1 for the recipe "Spaghetti bolognese"
When I update step number 1 description to "Add your sliced onion in a hot pan with olive oil and garlic"
Then Next time I retrieve recipe "Spaghetti bolognese" step 1 description, I'll get "Add your sliced onion in a hot pan with olive oil and garlic"