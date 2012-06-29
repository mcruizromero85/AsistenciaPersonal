package com.asistp.asistencia.stories;



import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.asistp.asistencia.pages.HomePage;
import com.asistp.asistencia.pages.PageFactory;


public class Steps 
{
	private final PageFactory pageFactory;
	private HomePage home;
	
	private Logger logger = Logger.getLogger(Steps.class);
	
	public Steps(PageFactory pageFactory) 
	{
		this.pageFactory = pageFactory;
	}
	
	@Given("I open the web application and loading data init for all test")
	public void openWebApplicationAndLoadingDataInitInMemory() {
		home = pageFactory.home();
		home.open();
		
	}

	@Given("I open the web application")
	public void openWebApplication() {
		
		home = pageFactory.home();
		home.open();
	}
	@When("I set Hour at \"$horaAsistenciaAuxiliar\" and click button \"$boton\"")
	public void iSetHourAtAndClickButton(String horaAsistenciaAuxiliar,String boton){
		try {
			Thread.sleep(2000);	
			home.type("dateAssistanceTestForHour",horaAsistenciaAuxiliar);
			Thread.sleep(2000);
			home.clickButton(boton);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("I should see input hidden for Test \"$text\"")
	public void iShouldSeeInputHiddenForTest(String text){
		home.type(text, "textExample");
	}
	
	@Then("I should see \"$text\" and \"$text2\"")
	public void iShouldSeeTheFollowingTextAndText2(String text,String text2){
		home.textIsVisible(text);
		home.textIsVisible(text2);
	}
	
	@Then("I should not see \"$text\" and \"$text2\"")
	public void iShouldNotSeeTheFollowingTextAndText2(String text, String text2){
		home.textIsNotVisible(text);
		home.textIsNotVisible(text2);
	}
	

	@Then("I should see \"$text\"")
	public void iShouldSeeTheFollowingText(String text){
		home.textIsVisible(text);
	}
	@Then("I should not see \"$text\"")
	public void iShouldNotSeeTheFollowingText(String text){
		home.textIsNoVisibleThisSystem(text);		
	}
	
	@Then("I should see button \"$text\"")
	public void iShouldSeeButtonWithTheFollowingText(String text){
		home.buttonIsVisible(text);
	}
	
	@Then("I should not see button \"$text\"")
	public void iShouldNotSeeButtonWithTheFollowingText(String text){
		home.buttonIsNotVisible(text);
	}
	
	@When("I click button \"$text\"")
	public void iClickButton(String text){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		home.clickButton(text);
	}
	
	@When("I fill login with \"$text\"")
	public void iFillLoginWith(String text){
		home.type("j_username", text);
	}
	
	@Then("I fill password with \"$text\"")
	public void iFillPasswordWith(String text){
		home.type("j_password", text);
	}
	
	@Then("I login with \"$usuario\" and password \"$password\"")
	public void iLoginWith(String usuario,String password){
		home.type("j_username", usuario);
		home.type("j_password", password);
		home.clickButton("Ingresar");
	}
	

	
	@Then("I should see user \"$user\" with entry hour at \"$hour\" and status \"$status\"")
	public void iShouldSeeUserWithEntryHourAndStatus(String user,String hour, String status){
		home.textIsVisible(user);
		home.textIsVisible(hour);
		home.textIsVisible(status);		
	}

	
	@Then("I logout")
	public void iLogout(){
		home.logout();
	}
}
