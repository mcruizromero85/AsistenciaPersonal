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
	
	@When("I set Hour \"$horaAsistenciaAuxiliar\" and click button \"$boton\"")
	public void iSetHourAndClickButton(String horaAsistenciaAuxiliar,String boton){
		home.type("fechaAsistenciaAuxiliarPruebas", horaAsistenciaAuxiliar);
		home.clickButton(boton);
	}
	
	@Then("I should see user \"$user\" with entry hour at \"$hour\" and status \"$status\"")
	public void iShouldSeeUserWithEntryHourAndStatus(String user,String hour, String status){
		home.textIsVisible(user+"|"+hour+"|"+status);		
	}
}
