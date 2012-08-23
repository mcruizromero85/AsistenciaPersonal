Scenario: First Login without assert, is only for balance cooky in the app. Pendind eval, why failed the first login ???!!!

Given I open the web application
Then I should not see "mauro.ruiz"
When I fill login with "juan.sabastizagal"
Then I fill password with "123456"
When I click button "Ingresar"
Then I should see ""

Scenario: Control view Index to clean of the words test at begin to project 
Given I open the web application
Then I login with "mauro.ruiz" and password "123456"
Then I should not see "Hello world"     
Then I should not see "Assistance Registered at"
Then I should not see "Asistencia Registrada"
Then I should not see "Registro de Asistencia"
Then I should not see "Spring"
Then I should see "Welcome to Assistance System mauro.ruiz"
Then I should see input hidden for Test "dateAssistanceTestForHour"
Then I should see button "Register my Assistance"

