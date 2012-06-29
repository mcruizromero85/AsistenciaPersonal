package com.asistp.asistencia.pages;

import junit.framework.AssertionFailedError;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.condition.ConditionRunner;

public class HomePage extends Page {

	public HomePage(Selenium selenium, ConditionRunner conditionRunner) {
		super(selenium, conditionRunner);
	}
	
	public void open() {
		open("http://localhost:9001/asistencia/");
	}

	public void verifyPage() {
		textIsVisible("Hello world!");
	}

	public void buttonIsNotVisible(String text) {
		 		
		try{
			clickButton(text);
		}catch (Exception e) {
			return;
		}		
		throw new RuntimeException("Existe el Boton");
	}

	public void buttonIsVisible(String text) {
		try{
			clickButton(text);
		}catch (Exception e) {
			throw new RuntimeException("No Existe el Boton");
		}		
		return;
	}
	
	public void textIsNoVisibleThisSystem(String text){
		
		try{
			textIsVisible(text);
		}catch (Exception e) {
			return;
		}catch (AssertionFailedError e) {
			return;
		}
		throw new RuntimeException("Existe el text: " + text);
	}

	public void logout() {
		clickLink("Logout");
	}

}
