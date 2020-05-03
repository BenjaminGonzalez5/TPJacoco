package pizza.spring.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PizzaSpringCommanderAcceptanceTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new ChromeDriver();
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test
	public void commanderUnePizzaAvecSucces() throws Exception {

		ResultPage resultPage = HomePage.openWith(webDriver).choisirPizza("pizzaId", "//option[. = 'Margherita']")
				.entrerTexte("nom", "Gonzalez").entrerTexte("telephone", "0102030405").validerCommande();

		assertTrue(resultPage.verifierId("recap-commande"));
	}

	@Test
	public void commanderUnePizzaSansSelectionnerDePizza() throws Exception {

		ResultPage resultPage = HomePage.openWith(webDriver).entrerTexte("nom", "Gonzalez")
				.entrerTexte("telephone", "0102030405").validerCommande();

		assertTrue(resultPage.verifierId("pizzaId.errors"));
		assertEquals("Vous devez choisir une pizza", resultPage.getValueId("pizzaId.errors"));
	}

	@Test
	public void commanderUnePizzaSansFournirDeNumeroDeTelephone() throws Exception {

		ResultPage resultPage = HomePage.openWith(webDriver).choisirPizza("pizzaId", "//option[. = 'Margherita']")
				.entrerTexte("nom", "Gonzalez").validerCommande();

		assertTrue(resultPage.verifierId("telephone.errors"));
		assertEquals("ne peut pas être vide", resultPage.getValueId("telephone.errors"));
	}

}
