package TB2.TB2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Button {

	private String label;

	private String xpath;

	private final int waitForClick = 5;

	public Button(String label, String xpath) {
		super();
		this.label = label;
		this.xpath = xpath;
	}

	public boolean isPresent() {
		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(500, TimeUnit.MILLISECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

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
			return Main.getDriver().findElement(By.xpath(xpath)).getText();

		return "Nichts gefunden";

	}

	public void sendText(String text) {
		if (isPresent(waitForClick))
			Main.getDriver().findElement(By.xpath(xpath)).sendKeys(text);
	}

	public void sendText(double text) {
		if (isPresent(waitForClick))
			Main.getDriver().findElement(By.xpath(xpath)).sendKeys(text + "");
	}

	public void sendText(int text) {
		if (isPresent(waitForClick))
			Main.getDriver().findElement(By.xpath(xpath)).sendKeys(text + "");
	}

	public void sendText(Keys escape) {
		if (isPresent(waitForClick))
			Main.getDriver().findElement(By.xpath(xpath)).sendKeys(escape);
	}

	public void sendText(Keys escape, int times) {
		for (int i = 0; i <= times; i++) {
			if (isPresent(waitForClick))
				Main.getDriver().findElement(By.xpath(xpath)).sendKeys(escape);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}

	}

	public String getCSSClass() {
		return Main.getDriver().findElement(By.xpath(xpath)).getAttribute("class");
	}

	public void click() {
		try {
			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(ElementNotInteractableException.class).ignoring(ElementClickInterceptedException.class)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht geklickt werden!");
		}

	}

	public void scrollToElement(String topaligin) {
		WebElement e = Main.getDriver().findElement(By.xpath(xpath));
		((JavascriptExecutor) Main.getDriver())
				.executeScript("arguments[0].scrollIntoView({block: \"" + topaligin + "\", behavior: \"smooth\"});", e);
		Main.sleep(500, TimeUnit.MILLISECONDS);
	}

	public void clear() {

		Main.getDriver().findElement(By.xpath(xpath)).clear();

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

}
