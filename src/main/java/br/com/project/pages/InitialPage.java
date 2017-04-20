package br.com.project.pages;

import org.openqa.selenium.WebDriver;

import br.com.project.components.NavigatorMenu;

public class InitialPage extends BasePage{

	private static NavigatorMenu navigatorMenu;
	
	/**
	 * Pega o navegador da página
	 * @return Navegador da página
	 */
	public NavigatorMenu getNavigator(){
		if(navigatorMenu == null) navigatorMenu = new NavigatorMenu(this.getDriver());
		return navigatorMenu;
	}
	
	/**
	 * Construtor
	 * @param driver Driver da página
	 */
	public InitialPage(WebDriver driver) {
		super(driver);		
	}
	
}
