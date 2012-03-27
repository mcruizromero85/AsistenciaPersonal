package com.asistp.asistencia.stories;



import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

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
}
