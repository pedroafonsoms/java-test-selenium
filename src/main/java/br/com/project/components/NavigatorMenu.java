package br.com.project.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.project.pages.LoginPage;
import br.com.project.pages.ProductsPageSearched;
import br.com.project.utils.Utils;

public class NavigatorMenu {

	private WebDriver driver;
	
	/**
	 * Construtor
	 * @param driver Driver da tela
	 */
	public NavigatorMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Clica na opção para realizar log-in
	 * @return página de Login
	 */
	public LoginPage clickLinkLogin(){
		driver.findElement(By.linkText("Entre | Cadastre-se")).click();

		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.className("txt-emailoucpf")));
		
		Utils.sleep(3000);	
		return new LoginPage(driver);
	}
	
	/**
	 * 
	 * @param nameProduct
	 * @return
	 */
	public ProductsPageSearched searchProducts(String nameProduct){
		this.driver.findElement(By.id("sli_search_1")).sendKeys(nameProduct);
		this.driver.findElement(By.xpath(".//button[@title='Buscar']")).click();
		Utils.sleep(5000);
		return new ProductsPageSearched(this.driver);
	}

	/**
	 * Verifica se o produto esta no carrinho de compras
	 * @param nameProduct Nome do produto
	 * @return true ou false
	 */
	public boolean checkShoppCart(String nameProduct) {

		moveToShoppCart();
		
		WebElement elementsDiv = this.driver.findElement(By.xpath(
				".//div[@class='boxProduct']"));	
		
		for(WebElement elementLi : elementsDiv.findElements(By.tagName("table"))){
			for(WebElement elementBody : elementLi.findElements(By.tagName("tbody"))){
				for(WebElement elementTr : elementBody.findElements(By.tagName("tr"))){
					for(WebElement elementTd : elementTr.findElements(By.tagName("td"))){
						if((elementTd.getAttribute("class").equals("qtdNumber") 
								&& elementTd.getAttribute("data-info") != null) 
								&& elementTd.getAttribute("data-info").contains(nameProduct)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se o carrinho de compras esta vazio
	 * @return true ou false
	 */
	public boolean checkSizeCart() {
		return Integer.parseInt(
				this.driver.findElement(By.xpath(".//b[@class='box-cart-count hide-w']")).getText()) > 0;
	}
	
	/**
	 * Movimenta o mouse até o carrinho de compas
	 */
	private void moveToShoppCart(){

		WebElement element = this.driver.findElement(By.className("cart"));
		Actions actions = new Actions(this.driver);
		actions.moveToElement(element);
		actions.perform();
		Utils.sleep(5000);
		
	}

	/**
	 * Deleta o produto do carrinho de compras
	 * @param nameProduct Nome do produto
	 */
	public void deleteProductInShoppCart(String nameProduct) {
		WebElement element = this.driver.findElement(By.xpath(
				".//td[@class='qtdNumber' and contains(@data-info, '" + nameProduct + "')]"));
		
		for(WebElement elementA: element.findElements(By.tagName("a"))){
			if(elementA.getAttribute("class").contains("remove-item")){
				elementA.click();
				Utils.sleep(3000);
			}
		}
	}

	/**
	 * Vai para a página inicial
	 */
	public void goInitialPage() {
		this.driver.findElement(By.xpath(".//a[@title='Centauro']")).click();
		Utils.sleep(1500);
	}
}
