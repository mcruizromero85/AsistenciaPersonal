Scenario: First Login without assert, is only for balance cooky in the app. Pendind eval, why failed the first login ???!!!

Given I open the web application
When I fill login with "juan.sabastizagal"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see ""

Scenario: Users juan.sabastizagal login to system and see Welcome to Assistance System juan.sabastizagal, User Juan is Regular User

Given I open the web application
When I fill login with "juan.sabastizagal"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see "Welcome to Assistance System juan.sabastizagal"

Scenario: User juan.sabastizagal register him assistance at 8:45 a.m, He bring to work early. Hour limit = 09:00 a.m

Given I open the web application
Then I login with "juan.sabastizagal" and password "123456"
When I set Hour at "08:45" and click button "Register my Assistance" 
Then I should see "Assistance Registered at 08:45"


Scenario: User edward.rojas register him assistance at 9:25 a.m, He bring to work early. Hour limit = 09:00 a.m

Given I open the web application
Then I login with "edward.rojas" and password "123456"
When I set Hour at "09:25" and click button "Register my Assistance"
Then I should see "Assistance Registered at 09:25"

Scenario: User edward.rojas after login in the system, he not should see options of maintenance 

Given I open the web application
Then I login with "edward.rojas" and password "123456"
Then I should not see "Create new Assistance" and "List all Assistances"					   
Then I should not see "Create new Worker" and "List all Workers"
Then I should not see "Create new Schedule" and "List all Schedules"

Scenario: User edward.rojas register him assistance at 9:25 a.m, He don't see button "Register my Assistance" 

Given I open the web application
Then I login with "edward.rojas" and password "123456"
When I click button "Register my Assistance"
Then I should see "Your assistance is registered at 09:25 today"
Then I should not see "Assistance Registered at 09:25"


