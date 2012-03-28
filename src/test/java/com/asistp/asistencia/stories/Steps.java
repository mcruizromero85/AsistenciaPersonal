package com.asistp.asistencia.stories;



import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.asistp.asistencia.pages.HomePage;
import com.asistp.asistencia.pages.PageFactory;


public class Steps 
{
	private final PageFactory pageFactory;
	private HomePage home;
	
	public Steps(PageFactory pageFactory) 
	{
		this.pageFactory = pageFactory;
	}

	@Given("I open the web application")
	public void openWebApplication() {
		
		home = pageFactory.home();
		home.open();
	}

	@Then("I should see \"$text\"")
	public void iShouldSeeTheFollowingText(String text){
		home.textIsVisible(text);
		
	}
	@When("I click button \"$text\"")
	public void iClickButton(String text){
		home.clickButton("proceed");
	}
	
	@When("I fill login with \"$text\"")
	public void iFillLoginWith(String text){
		home.type("j_username", text);
	}
	
	@Then("I fill password with \"$text\"")
	public void iFillPasswordWith(String text){
		home.type("j_password", text);
	}
}
