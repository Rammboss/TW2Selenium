package TB2.TB2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button {

	private String label;

	private String xpath;

	private WebDriver driver;

	private final int waitForClick = 2;

	public Button(String label, String xpath, WebDriver driver) {
		super();
		this.label = label;
		this.xpath = xpath;
		this.driver = driver;
	}

	public boolean isPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2, 200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			return true;
		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden werden!");
			return false;
		}
	}

	public boolean isPresent(int time, TimeUnit unit) {

		boolean present = false;

		for (long stop = System.nanoTime() + unit.toNanos(time); stop > System.nanoTime();) {
			if (isPresent())
				return true;
		}
		return present;
	}

	public boolean isNOTPresent(int time, TimeUnit unit) {

		boolean present = true;

		for (long stop = System.nanoTime() + unit.toNanos(time); stop > System.nanoTime();) {
			if (isPresent())
				return false;
		}
		return present;
	}

	public boolean isPresent(int sec) {

		return isPresent(sec, TimeUnit.SECONDS);

	}

	public String getText() {
		if (isPresent(2))
			return driver.findElement(By.xpath(xpath)).getText();

		return "Nichts gefunden";

	}

	public void sendText(String text) {
		if (isPresent(waitForClick))
			driver.findElement(By.xpath(xpath)).sendKeys(text);
	}

	public void sendText(double text) {
		if (isPresent(waitForClick))
			driver.findElement(By.xpath(xpath)).sendKeys(text + "");
	}

	public void sendText(int text) {
		if (isPresent(waitForClick))
			driver.findElement(By.xpath(xpath)).sendKeys(text + "");
	}

	public void sendText(Keys escape) {
		if (isPresent(waitForClick))
			driver.findElement(By.xpath(xpath)).sendKeys(escape);
	}

	public void sendText(Keys escape, int times) {
		for (int i = 0; i <= times; i++) {
			if (isPresent(waitForClick))
				driver.findElement(By.xpath(xpath)).sendKeys(escape);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public String getCSSClass() {
		return driver.findElement(By.xpath(xpath)).getAttribute("class");
	}

	public void click() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2, 200);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht geklickt werden!");
		}

	}



	public void scrollToElement() {
		WebElement e = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
		Main.sleep(500, TimeUnit.MILLISECONDS);
	}

	public void clear() {

		driver.findElement(By.xpath(xpath)).clear();

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
