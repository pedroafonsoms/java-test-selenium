package br.com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.project.components.NavigatorMenu;
import br.com.project.utils.Utils;

public class ProductsPageSearched extends BasePage{
	
	private static NavigatorMenu navigatorMenu;
	
	public NavigatorMenu getNavigator(){
		if(navigatorMenu == null) navigatorMenu = new NavigatorMenu(this.getDriver());
		return navigatorMenu;
	}
	
	public ProductsPageSearched(WebDriver driver) {
		super(driver);
	}

	public ProductsPage selectProduct() {
		
		downPage(20);
		WebElement elementsUl = getDriver().findElement(By.xpath(
				".//ul[@class='showcase-itens clearfix table-view']"));	
		
		String[] splitText = elementsUl.getText().split("sem juros");
		String text = splitText[Utils.getRandomNumber(0, 47)];
		
		for (WebElement elementLi : elementsUl.findElements(By.tagName("li"))) {
			if (elementLi.getText().contains(text.trim())){
				elementLi.click();
				Utils.sleep(3000);
				return new ProductsPage(getDriver());
			}
		}
		return null;
	}
		
}
