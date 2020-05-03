package pizza.spring.acceptance;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {

	private WebDriver webDriver;

	public ResultPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().endsWith("Pizza Spring"));
	}

	public boolean verifierId(String id) {
		return webDriver.findElement(By.id(id)).isDisplayed();
	}

	public String getValueId(String id) {
		return webDriver.findElement(By.id(id)).getText();
	}
}
