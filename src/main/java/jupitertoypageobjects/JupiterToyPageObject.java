package jupitertoypageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JupiterToyPageObject {
	public WebDriver driver;
	// locators to find the element in the web page
	By startShoppingButton = By.xpath("//a[contains(text(), 'Start Shopping »')]");
	By totalCartValue = By.xpath("//strong[@class='total ng-binding']");
	By itemsAddedCartValue = By.xpath("//span[@class='cart-count ng-binding']");

	By cartTab = By.xpath("//a[@href='#/cart']");

	By errorMessage = By.xpath("//div[@class='alert alert-error ng-scope']");

	By userForename = By.xpath("//input[@id='forename']");
	By userEmail = By.xpath("//input[@id='email']");

	By contactTab = By.xpath("//a[@href='#/contact']");
	By contactMessage = By.xpath("//textarea[@id='message']");
	By contactSubmitButton = By.xpath("//a[contains(text(), 'Submit')]");
	By contactPageRequiredErrorMessage = By.xpath("//span[@class='help-inline ng-scope']");

	By jupiterHomeLogo = By.xpath("//a[@href='#']");
	By successMessageText = By.xpath("//div[@class='alert alert-success']");

	// create constructor to pass driver object to another test case
	public JupiterToyPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement buyAToyElement(String toyName){
		return this.driver.findElement(By.xpath("//h4[text()='"+toyName+"']/following-sibling::p/a[@ng-click='add(item)']"));
	}

	public void buyAToy(String toyName){
		buyAToyElement(toyName).click();
	}

	public String getToyPrice(String toyName){
		return this.driver.findElement(By.xpath("//td[contains(text(),'"+toyName+"')]/following-sibling::td[1]")).getText();
	}
	public String getToySubTotalPrice(String toyName){
		return this.driver.findElement(By.xpath("//td[contains(text(),'"+toyName+"')]/following-sibling::td[3]")).getText();
	}
	public String getCartTotalValue(){
		return this.driver.findElement(totalCartValue).getText();
	}
	public WebElement jupiterHomeLogo() {
		return this.driver.findElement(jupiterHomeLogo);
	}

	public WebElement startShoppingButton() {
		return this.driver.findElement(startShoppingButton);
	}

	public WebElement itemsAddedCartValue() {
		return this.driver.findElement(itemsAddedCartValue);
	}

	public WebElement cartTab() {
		return this.driver.findElement(cartTab);
	}

	public WebElement userForename() {
		return this.driver.findElement(userForename);
	}

	public WebElement userEmail() {
		return this.driver.findElement(userEmail);
	}

	public WebElement errorMessage() {
		return this.driver.findElement(errorMessage);
	}

	public WebElement successMessageText() {
		return this.driver.findElement(successMessageText);
	}

	public WebElement contactMessage() {
		return this.driver.findElement(contactMessage);
	}

	public WebElement contactTab() {
		return this.driver.findElement(contactTab);
	}

	public WebElement contactSubmitButton() {
		return this.driver.findElement(contactSubmitButton);
	}

	public List<WebElement> contactPageRequiredErrorMessage() {
		return this.driver.findElements(contactPageRequiredErrorMessage);
	}

	public Boolean isErrorMessageDisplayed() {
		try{
			if(this.driver.findElement(errorMessage) != null)
				return true;
			else
				return false;
		}
		catch(Exception e){
			return false;
		}
	}
}
