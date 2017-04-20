package br.com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.project.components.NavigatorMenu;
import br.com.project.utils.Utils;

public class LoginPage extends BasePage{
	
	private static NavigatorMenu navigatorMenu;
	
	public NavigatorMenu getNavigator(){
		if(navigatorMenu == null) navigatorMenu = new NavigatorMenu(this.getDriver());
		return navigatorMenu;
	}
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public InitialPage LogIn(String user, String password){
		getDriver().findElement(By.id("input-type-1")).sendKeys(user);
		getDriver().findElement(By.id("optYesclient")).click();
		getDriver().findElement(By.id("input-type-4")).sendKeys(password);
		getDriver().findElement(By.id("btn_Entrar")).click();
		Utils.sleep(3000);
		return new InitialPage(getDriver());
	}
	
	public boolean isLogged(){
		return this.getDriver().findElement(
				By.className("user")) != null;
	} 
	
}
