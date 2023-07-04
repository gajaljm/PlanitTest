package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class InitiateDriver {
	public WebDriver driver;
	public String baseURL, browserName, userName, password, firstName, lastName, email, address, cardNumber, telephone;

	public WebDriver initiateDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\NGK\\Downloads\\PlanitTestingSelenium-main\\PlanitTestingSelenium-main\\Automation\\src\\main\\java\\data.properties");
		prop.load(fis);
		baseURL = prop.getProperty("baseURL");
		userName = prop.getProperty("userName");
		password = prop.getProperty("password");
		firstName = prop.getProperty("firstName");
		lastName = prop.getProperty("lastName");
		email = prop.getProperty("email");
		address = prop.getProperty("address");
		cardNumber = prop.getProperty("cardNumber");
		telephone = prop.getProperty("telephone");
		browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\NGK\\Downloads\\chromedriver_win32\\chromedriver.exe");
			try {
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--headless");
				options.addArguments("--test-type");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-first-run");
				options.addArguments("--no-default-browser-check");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--start-maximized");
				options.addArguments("--allow-insecure-localhost");
				options.addArguments("--window-size=1280,800");
				options.setCapability("acceptInsecureCerts", true);
				options.setCapability(ChromeOptions.CAPABILITY, options);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new ChromeDriver(options);
			} catch (Exception e) {
				throw new Error(e);
			}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		return driver;
	}
}
