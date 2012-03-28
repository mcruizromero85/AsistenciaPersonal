Scenario: Users mauro.ruiz login to system and see Welcome Mauro Ruiz

Given I open the web application
When I fill login with "mauro.ruiz"
Then I fill password with "123456"
When I click button "Enter"
Then I should see "Welcome Mauro Ruiz"