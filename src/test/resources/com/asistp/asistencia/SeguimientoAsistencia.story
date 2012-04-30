Scenario: Users mauro.ruiz login to system and see Welcome to Assistance System mauro.ruiz, User Mauro is Administrator

Given I open the web application
When I fill login with "mauro.ruiz"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see "Welcome to Assistance System mauro.ruiz"

Scenario: Users mauro.ruiz login to system and see Juan Sabastizagal's registers  , User Mauro is Administrator

Given I open the web application
Then I login with "mauro.ruiz" and password "123456"
Then I should see user "juan.sabastizagal" with entry hour at "08:45" and status "Temprano" 

Scenario: Users mauro.ruiz login to system and see Edward Rojas's registers  , User Mauro is Administrator

Given I open the web application
Then I login with "mauro.ruiz" and password "123456"
Then I should see user "edward.rojas" with entry hour at "09:25" and status "Tarde" 

Scenario: User mauro.ruiz after login in the system, he should see options of maintenance 

Given I open the web application
Then I login with "mauro.ruiz" and password "123456"
Then I should see "Create new Assistance" and "List all Assistances"
Then I should see "Create new Worker" and "List all Workers"
Then I should see "Create new Schedule" and "List all Schedules"