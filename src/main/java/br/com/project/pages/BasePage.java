package br.com.project.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.project.utils.Utils;

public class BasePage {

	private WebDriver driver;
	
	/**
	 * Construtor sem parâmetros
	 */
	public BasePage() {
	}
	
	/**
	 * Construtor 
	 * @param driver Driver da página
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Navega até a tela.
	 * @param url Url da tela 
	 */
	public void get(String url){
		this.driver.navigate().to(url);
	}
	
	/**
	 * Fecha instância do driver
	 */
	public void closeDriver(){
		this.driver.close();
	}
	
	/**
	 * Pega o driver da pagina atual
	 * @return driver da pagina
	 */
	public WebDriver getDriver(){
		return this.driver;
	}
	
	public void downPage(int size){
		Actions action = new Actions(this.driver);
		for(int i = 0; i<size ; i++) action.sendKeys(Keys.ARROW_DOWN).build().perform();
		Utils.sleep(500);
	}
	
	/**
	 * Inicializa o driver da pagina com os comportamentos desejados.
	 */
	public void initializeDriver(){
		DesiredCapabilities capabilities =  new DesiredCapabilities();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		this.driver = new ChromeDriver(capabilities);
	}
	
}
