package TB2.TB2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button {

	private String label;

	private String xpath;

	private final int waitForClick = 5;

	public Button(String label, String xpath) {
		super();
		this.label = label;
		this.xpath = xpath;
	}

	public boolean isClickable() {
		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(1000, TimeUnit.MILLISECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			return true;
		} catch (TimeoutException e) {
			// System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden
			// werden!");
			return false;
		}
	}

	public boolean isPresent() {
		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(500, TimeUnit.MILLISECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			return true;
		} catch (TimeoutException e) {
			// System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden
			// werden!");
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

	public void mouseOver() {
		Actions action = new Actions(Main.getDriver());

		action.moveToElement(getWebelement()).moveToElement(Main.getDriver().findElement(By.xpath(this.getXpath())))
				.perform();
		;
	}

	public void clickCoords(int x, int y) {
		Actions a = new Actions(Main.getDriver()).moveToElement(this.getWebelement());
		a.moveByOffset(x / 2, y / 2).click().perform();
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
			return getWebelement().getText();

		return "Nichts gefunden";

	}

	public void sendText(String text) {
		if (isPresent(waitForClick))
			getWebelement().sendKeys(text);
	}

	public void sendText(double text) {
		if (isPresent(waitForClick))
			getWebelement().sendKeys(text + "");
	}

	public void sendText(int text) {
		if (isPresent(waitForClick))
			getWebelement().sendKeys(text + "");
	}

	public void sendText(Keys escape) {
		if (isPresent(waitForClick))
			getWebelement().sendKeys(escape);
	}

	public void sendText(Keys escape, int times) {
		for (int i = 0; i <= times; i++) {
			if (isPresent(waitForClick))
				getWebelement().sendKeys(escape);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}

	}

	public String getCSSClass() {
		return getWebelement().getAttribute("class");
	}

	public WebElement getWebelement() {
		return Main.getDriver().findElement(By.xpath(xpath));
	}

	public List<WebElement> getWebelements() {
		return Main.getDriver().findElements(By.xpath(xpath));
	}

	public void click() {
		try {
			new WebDriverWait(Main.getDriver(), 5).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
					.click();
			// new FluentWait<WebDriver>(Main.getDriver()).withTimeout(5, TimeUnit.SECONDS)
			// .pollingEvery(500,
			// TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
			// .ignoring(ElementNotInteractableException.class).ignoring(ElementClickInterceptedException.class)

		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht geklickt werden!(Zeitüberschreitung)");
		} catch (ElementClickInterceptedException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht geklickt werden!(not clickable)");

		}

	}

	public void scrollToElement(String topaligin) {
		((JavascriptExecutor) Main.getDriver()).executeScript(
				"arguments[0].scrollIntoView({block: \"" + topaligin + "\", behavior: \"smooth\"});", getWebelement());
		Main.sleep(500, TimeUnit.MILLISECONDS);

	}

	public String getAttribute(String attribute) {

		return getWebelement().getAttribute(attribute);
	}

	public boolean compareAttribute(String attr, String value) {

		String tmp = this.getWebelement().getAttribute(attr);

		if (tmp != null) {
			return tmp.equals(value);

		}
		// falls element nicht existiert etc
		return false;

	}

	public void clear() {
		if (isPresent()) {
			getWebelement().clear();

		}

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
