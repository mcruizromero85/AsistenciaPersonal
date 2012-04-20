Scenario: First Login without assert, is only for balance cooky in the app. Pendind eval, why failed the first login ???!!!

Given I open the web application
When I fill login with "juan.sabastizagal"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see ""

Scenario: Users juan.sabastizagal login to system and see Welcome juan.sabastizagal, User Juan is Regular User

Given I open the web application
When I fill login with "juan.sabastizagal"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see "Welcome juan.sabastizagal"

Scenario: Users mauro.ruiz login to system and see Welcome mauro.ruiz, User Mauro is Administrator

Given I open the web application
When I fill login with "mauro.ruiz"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see "Welcome mauro.ruiz"


Scenario: User juan.sabastizagal register him assistance at 8:45 a.m, He bring to work early. Hour limit = 9:00 a.m

Given I open the web application
Then I login with "juan.sabastizagal" and password "123456"
Then I should see "Registro de Asistencia"
When I set Hour "8:45" and click button "Registrar Asistencia" 
Then I should see "Asistencia Registrada"
Then I should see "Hora: 8:45"

Scenario: User edward.rojas register him assistance at 9:25 a.m, He bring to work early. Hour limit = 9:00 a.m

Given I open the web application
Then I login with "edward.rojas" and password "123456"
Then I should see "Registro de Asistencia"
When I set Hour "9:25" and click button "Registrar Asistencia"
Then I should see "Asistencia Registrada"
Then I should see "Hora: 9:25"