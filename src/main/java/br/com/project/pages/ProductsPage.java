package br.com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.project.components.NavigatorMenu;
import br.com.project.utils.Utils;

public class ProductsPage extends BasePage{

	private static NavigatorMenu navigatorMenu;
	
	public NavigatorMenu getNavigatorMenu(){
		if(navigatorMenu == null) navigatorMenu = new NavigatorMenu(this.getDriver());
		return navigatorMenu;
	}

	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	public void addToCart(String size){
		selectSize(size);
		WebElement element = getDriver().findElement(
					By.xpath(".//span[@data-content='Adicionar ao Carrinho']"));
		
		if (!element.isDisplayed()) downPage(20);
		element.click();
		Utils.sleep(3000);
	}
	
	public void selectSize(String size){
		WebElement elementsUl = getDriver().findElement(By.xpath(
				".//ul[@class='selector-list text' and not(contains(@style, 'display: none;'))]"));	
		
		for(WebElement elementLi : elementsUl.findElements(By.tagName("li"))){
			if(size.equals("")){
				elementLi.click();
				Utils.sleep(1000);
			} else if(elementLi.getAttribute("data-value").equals(size)){
				elementLi.click();
				Utils.sleep(1000);
			}
		}
	}
	
	public boolean checkSizeAvailable(String size){
		
		WebElement elementsUl = getDriver().findElement(By.xpath(
				".//ul[@class='selector-list text'and not(contains(@style, 'display: none;'))]"));	
		
		for(WebElement elementLi : elementsUl.findElements(By.tagName("li"))){
			for(WebElement elementLabel : elementLi.findElements(By.tagName("label"))){
				if(elementLabel.getAttribute("data-value").equals(size) ||
						elementLabel.getAttribute("data-value").equals("")){
					return true;
				}
			}
		}
		return false;
	}
	
	public String getNameProduct(){
		return getDriver().findElement(By.className("product-name")).getText();
	}
	
}
