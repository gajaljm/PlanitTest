package jupitertoytestsuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jupitertoypageobjects.JupiterToyPageObject;
import resources.InitiateDriver;

public class ValidateJupiterToyFlowTest extends InitiateDriver {
	String captureValidErrorMessage = null;
	public WebDriver driver;
	public JupiterToyPageObject jupiterpageobject;

	@BeforeClass
	public void init() throws IOException {
		driver = initiateDriver();
		jupiterpageobject = new JupiterToyPageObject(driver);
	}

	// Validate cart after adding few items
	@Test(priority = 3)
	public void validateCartDetails(){
		jupiterpageobject.startShoppingButton().click();
		//Buy 2 Stuffed frog
		for (int buyCount = 0; buyCount < 2; buyCount++) {
			jupiterpageobject.buyAToy("Stuffed Frog");
		}
		//Buy 5 Fluffy Bunny
		for (int buyCount = 0; buyCount < 5; buyCount++) {
			jupiterpageobject.buyAToy("Fluffy Bunny");
		}
		//Buy 3 Valentine Bear
		for (int buyCount = 0; buyCount < 3; buyCount++) {
			jupiterpageobject.buyAToy("Valentine Bear");
		}
		String getCartValue = jupiterpageobject.itemsAddedCartValue().getText();
		Assert.assertEquals(getCartValue,"10");
		jupiterpageobject.cartTab().click();
		Assert.assertEquals(jupiterpageobject.getToyPrice("Stuffed Frog"),"$10.99");
		Assert.assertEquals(jupiterpageobject.getToyPrice("Fluffy Bunny"),"$9.99");
		Assert.assertEquals(jupiterpageobject.getToyPrice("Valentine Bear"),"$14.99");
		Assert.assertEquals(jupiterpageobject.getToySubTotalPrice("Stuffed Frog"),"$21.98");
		Assert.assertEquals(jupiterpageobject.getToySubTotalPrice("Fluffy Bunny"),"$49.95");
		Assert.assertEquals(jupiterpageobject.getToySubTotalPrice("Valentine Bear"),"$44.97");
		Assert.assertEquals(jupiterpageobject.getCartTotalValue(),"Total: 116.9");
	}

	//Test to validate error message in Contact page when mandatory fields are left blank
	@Test(priority = 1)
	public void validateContactPageErrors() throws InterruptedException {
		jupiterpageobject.contactTab().click();
		jupiterpageobject.contactSubmitButton().click();
		captureValidErrorMessage = jupiterpageobject.errorMessage().getText();
		Assert.assertEquals(captureValidErrorMessage,
				"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		int errorMessageCount = jupiterpageobject.contactPageRequiredErrorMessage().size();
		for (int errorCount = 0; errorCount < errorMessageCount; errorCount++) {
			System.out.println("Valid error messages are: "
					+ jupiterpageobject.contactPageRequiredErrorMessage().get(errorCount).getText());
		}
		jupiterpageobject.userForename().sendKeys(firstName);
		jupiterpageobject.userEmail().sendKeys(email);
		jupiterpageobject.contactMessage().sendKeys("Just curious to know!");
		jupiterpageobject.contactSubmitButton().click();
		Assert.assertEquals(jupiterpageobject.isErrorMessageDisplayed(),
				false);
		errorMessageCount = jupiterpageobject.contactPageRequiredErrorMessage().size();
		Assert.assertEquals(errorMessageCount,0);
		jupiterpageobject.jupiterHomeLogo().click();
	}

	//Test to validate happy path in Contact page
	@Test(priority = 2, invocationCount = 5)
	public void testContactPage(){
		jupiterpageobject.contactTab().click();
		jupiterpageobject.userForename().sendKeys(firstName);
		jupiterpageobject.userEmail().sendKeys(email);
		jupiterpageobject.contactMessage().sendKeys("Just curious to know!");
		jupiterpageobject.contactSubmitButton().click();
		Assert.assertEquals(jupiterpageobject.successMessageText().getText(),
				"Thanks Gajalakshmi, we appreciate your feedback.");
		jupiterpageobject.jupiterHomeLogo().click();
	}

	@AfterClass
	public void driverClose() {
		driver.close();
	}
}
