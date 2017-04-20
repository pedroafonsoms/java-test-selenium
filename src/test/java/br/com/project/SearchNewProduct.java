package br.com.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.project.pages.BasePage;

import br.com.project.pages.InitialPage;
import br.com.project.pages.LoginPage;
import br.com.project.pages.ProductsPage;
import br.com.project.pages.ProductsPageSearched;

public class SearchNewProduct {

	private BasePage pageBase;
	
	@Before
	public void setUp() throws Exception {
		pageBase = new BasePage();
		pageBase.initializeDriver();
		pageBase.get("http://www.centauro.com.br");
	}

	@After
	public void tearDown() throws Exception {
		pageBase.closeDriver();
	}

	@Test
	public void test() {
		
		// Realiza log-in no sistema e verifica se foi realizado.
		LoginPage loginPage = new LoginPage(pageBase.getDriver());
		loginPage.getNavigator().clickLinkLogin();
		InitialPage initialPage = loginPage.LogIn("user", 
				"senha");
		Assert.assertTrue("Não foi realizado o log-in!", loginPage.isLogged());
		
		// Realiza pesquisa do produto informado
		ProductsPageSearched productsPageSearched = initialPage.getNavigator()
				.searchProducts("tenis adidas");
		
		// Seleciona o produto informado e verifica se existe o tamanho desejado para ele.
		ProductsPage productsPage = productsPageSearched.selectProduct();
		Assert.assertTrue("Produto não encontrado!", productsPage != null);
		
		// Verifica se o tamanho para o produto desejado esta disponivel
		String size = "";
		String nameProduct = productsPage.getNameProduct();
		Assert.assertTrue("Tamanho não disponivel!", 
				productsPage.checkSizeAvailable(size));
		
		// Adiciona o produto ao carrinho de compras e faz as verificações: 
		// carrinho vazio ? produto esta no carrinho ?
		productsPage.addToCart(size);
		Assert.assertTrue("Carrinho esta vazio!",
				productsPage.getNavigatorMenu().checkSizeCart());
		Assert.assertTrue("Produto não encontrado no carrinho!",
				productsPage.getNavigatorMenu().checkShoppCart(nameProduct));
		
		// Exclui o produto do carrinho e verifica se o mesmo foi excluido
		productsPage.getNavigatorMenu().deleteProductInShoppCart(nameProduct);
		Assert.assertFalse("Produto não excluído do carrinho", 
				productsPage.getNavigatorMenu().checkShoppCart(nameProduct));
		
		// Volta a tela inicial
		productsPage.getNavigatorMenu().goInitialPage();
	}

}
