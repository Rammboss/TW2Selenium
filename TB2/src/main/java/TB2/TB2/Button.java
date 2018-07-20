package TB2.TB2;

import java.time.Duration;
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

public class Button {
	public static int BY_CLASS_NAME = 1;
	public static int BY_CSS_SELECTOR_WITH_CRITERIA = 2;
	public static int BY_CSS_SELECTOR = 22;

	public static int BY_ID = 3;
	public static int BY_LINKED_TEXT = 4;
	public static int BY_NAME = 5;
	public static int BY_TAG_NAME = 6;
	public static int BY_XPATH = 7;

	private String label;

	private String xpath;

	private String type;

	private String attribute;

	private String attributeValue;

	private String criteria;

	private int wayToSelect;

	private final int waitForClick = 5;

	public Button(String label, String xpath, int wayToSelect) {
		super();
		this.label = label;
		this.xpath = xpath;
		this.wayToSelect = wayToSelect;

	}

	public Button(String label, String type, String attribute, String attributeValue, String criteria, int wayToSelect) {
		super();
		this.xpath = null;
		this.label = label;
		this.type = type;
		this.attribute = attribute;
		this.attributeValue = attributeValue;
		this.criteria = criteria;
		this.wayToSelect = wayToSelect;
	}

	public Button(String label, String type, String attribute, String attributeValue, int wayToSelect) {
		super();
		this.xpath = null;
		this.label = label;
		this.type = type;
		this.attribute = attribute;
		this.attributeValue = attributeValue;
		this.wayToSelect = wayToSelect;

	}

	public boolean isClickable() {
		try {
			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(Duration.ofSeconds(1)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(getWebelement()));

			return true;
		} catch (TimeoutException e) {
			// System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden
			// werden!");
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPresent() {
		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(Duration.ofMillis(500)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated((getLocator())));

			return true;
		} catch (TimeoutException e) {
			// System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden
			// werden!");
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPresent(Duration timeout) {

		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(timeout).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated((getLocator())));

			return true;
		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden werden!");
			e.printStackTrace();
			return false;
		}
	}

	public void mouseOver() {
		Actions action = new Actions(Main.getDriver());

		action.moveToElement(getWebelement()).moveToElement(Main.getDriver().findElement(By.xpath(this.getXpath()))).perform();

	}

	public void clickCoords(int x, int y) {
		Actions a = new Actions(Main.getDriver()).moveToElement(this.getWebelement());
		a.moveByOffset(x / 2, y / 2).click().perform();
	}

	public boolean isNOTPresent(Duration timeout) {

		try {

			new FluentWait<WebDriver>(Main.getDriver()).withTimeout(timeout).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOfElementLocated((getLocator())));

			return true;
		} catch (TimeoutException e) {
			System.out.println("Button:" + this.getLabel() + " konnte nicht gefunden werden!");
			e.printStackTrace();
			return false;
		}
	}

	public String getText() {
		if (isPresent(Duration.ofSeconds(waitForClick)))
			return getWebelement().getText();

		return "Nichts gefunden";

	}

	public void sendText(String text) {
		if (isPresent(Duration.ofSeconds(waitForClick)))
			getWebelement().sendKeys(text);
	}

	public void sendText(double text) {
		if (isPresent(Duration.ofSeconds(waitForClick)))
			getWebelement().sendKeys(text + "");
	}

	public void sendText(int text) {
		if (isPresent(Duration.ofSeconds(waitForClick)))
			getWebelement().sendKeys(text + "");
	}

	public void sendText(Keys escape) {
		if (isPresent(Duration.ofSeconds(waitForClick)))
			getWebelement().sendKeys(escape);
	}

	public void sendText(Keys escape, int times) {
		for (int i = 0; i <= times; i++) {
			if (isPresent(Duration.ofSeconds(waitForClick)))
				getWebelement().sendKeys(escape);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e1) {
				throw new RuntimeException(e1);
			}
		}

	}

	public boolean isDisplayed() {
		return getWebelement().isDisplayed();
	}

	public String getCSSClass() {

		return getWebelement().getAttribute("class");
	}

	public WebElement getWebelement() {
		List<WebElement> list;

		switch (getWayToSelect()) {

		case 1:
			return null;
		case 2:
			// try {
			list = Main.getDriver().findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

			for (WebElement webElement : list) {
				if (webElement.getAttribute(getAttribute()).equals(getAttributeValue()) && webElement.getAttribute("innerHTML").contains(criteria)) {
					return webElement;
				}
			}

			// return null;

			// } catch (StaleElementReferenceException e) {
			// System.out.println("Attempting to recover from StaleElementReferenceException
			// ...");
			// return getWebelementsByAttribute(getType(), getAttribute(),
			// getAttributeValue());
			// }
		case 22:
			list = Main.getDriver().findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

			for (WebElement webElement : list) {
				if (webElement.getAttribute(getAttribute()).equals(getAttributeValue())) {
					return webElement;
				}
			}
		case 3:
			return null;
		case 4:
			return null;
		case 5:
			return null;
		case 6:
			return null;
		case 7:
			return Main.getDriver().findElement(By.xpath(getXpath()));
		default:
			return null;
		}
	}

	public By getLocator() {

		switch (getWayToSelect()) {

		case 1:
			return null;
		case 2:
			return By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']");

		case 22:
			return By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']");
		case 3:
			return null;
		case 4:
			return null;
		case 5:
			return null;
		case 6:
			return null;
		case 7:
			return By.xpath(getXpath());
		default:
			return null;
		}
	}

	public WebElement getWebelementsByText(String type, String text) {
		List<WebElement> list = Main.getDriver().findElements(By.xpath("//" + type + "[contains(text(), '" + text + "')]"));

		return list.get(0);
	}

	public List<WebElement> getWebelements() {
		return Main.getDriver().findElements(By.xpath(xpath));
	}

	public void click() {

		if (isPresent(Duration.ofSeconds(5))) {
			try {
				getWebelement().click();
			} catch (ElementClickInterceptedException e) {
				System.out.println("Button konnte nicht geklickt werden!");
				e.printStackTrace();
			}
		} else {
			System.out.println("##ACHTUNG##: Element " + getText() + " konnte nicht gelklickt werden!");
		}

	}

	public void click(int seconds) {

		if (isPresent(Duration.ofSeconds(seconds))) {
			click();
		}

	}

	public void scrollToElement(String topaligin) {
		((JavascriptExecutor) Main.getDriver()).executeScript("arguments[0].scrollIntoView({block: \"" + topaligin + "\", behavior: \"smooth\"});", getWebelement());
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

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public int getWayToSelect() {
		return wayToSelect;
	}

	public void setWayToSelect(int wayToSelect) {
		this.wayToSelect = wayToSelect;
	}

}
