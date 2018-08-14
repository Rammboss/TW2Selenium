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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;

public class Button {

    private static Logger logger = LoggerFactory.getLogger(Button.class);

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

    private Button button;

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
                    .until(ExpectedConditions.elementToBeClickable(getWebelement(Main.getDriver())));

            return true;
        } catch (TimeoutException e) {
            logger.debug("Button:" + getLabel() + " konnte nicht geklickt werden!", e);
            return false;
        }
    }

    public boolean isPresent() {
        try {

            new FluentWait<WebDriver>(Main.getDriver()).withTimeout(Duration.ofMillis(500)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated((getLocator())));

            return true;
        } catch (TimeoutException e) {
            logger.debug("Button:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public boolean isPresent(Duration timeout) {

        try {

            new FluentWait<WebDriver>(Main.getDriver()).withTimeout(timeout).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated((getLocator())));

            return true;
        } catch (TimeoutException e) {
            logger.debug("Button:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public void mouseOver() {
        Actions action = new Actions(Main.getDriver());

        action.moveToElement(getWebelement(Main.getDriver())).moveToElement(Main.getDriver().findElement(By.xpath(this.getXpath()))).perform();

    }

    public void clickCoords(int x, int y) {
        Actions a = new Actions(Main.getDriver()).moveToElement(this.getWebelement(Main.getDriver()));
        a.moveByOffset(x / 2, y / 2).click().perform();
    }

    public boolean isNOTPresent(Duration timeout) {

        try {

            new FluentWait<WebDriver>(Main.getDriver()).withTimeout(timeout).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated((getLocator())));

            return true;
        } catch (TimeoutException e) {
            logger.debug("Button:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public String getText() throws NoElementTextFound {
        if (isPresent(Duration.ofSeconds(waitForClick)))
            return getWebelement(Main.getDriver()).getText();

        throw new NoElementTextFound();

    }

    public void sendText(String text) {
        if (isPresent(Duration.ofSeconds(waitForClick)))
            getWebelement(Main.getDriver()).sendKeys(text);
    }

    public void sendText(double text) {
        if (isPresent(Duration.ofSeconds(waitForClick)))
            getWebelement(Main.getDriver()).sendKeys(text + "");
    }

    public void sendText(int text) {
        if (isPresent(Duration.ofSeconds(waitForClick)))
            getWebelement(Main.getDriver()).sendKeys(text + "");
    }

    public void sendText(Keys escape) {
        if (isPresent(Duration.ofSeconds(waitForClick)))
            getWebelement(Main.getDriver()).sendKeys(escape);
    }

    public void sendText(Keys escape, int times) {
        for (int i = 0; i <= times; i++) {
            if (isPresent(Duration.ofSeconds(waitForClick)))
                getWebelement(Main.getDriver()).sendKeys(escape);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e1) {
                throw new RuntimeException(e1);
            }
        }

    }

    public boolean isDisplayed() {
        return getWebelement(Main.getDriver()).isDisplayed();
    }

    public String getCSSClass() {

        return getWebelement(Main.getDriver()).getAttribute("class");
    }

    public WebElement getWebelement(WebDriver driver) {


        List<WebElement> list;

        switch (getWayToSelect()) {

            case 1:
                return driver.findElement(By.className(getXpath()));
            case 2:
                // try {
                list = driver.findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (webElement.getAttribute(getAttribute()).equals(getAttributeValue()) && webElement.getAttribute("innerHTML").contains(criteria)) {
                        if (getButton() != null) {
                            return getWebelement(webElement, getButton());
                        }
                        return webElement;
                    }
                }
                return null;

            case 22:
                list = driver.findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (webElement.getAttribute(getAttribute()).equals(getAttributeValue())) {
                        if (getButton() != null) {
                            return getWebelement(webElement, getButton());
                        }
                        return webElement;
                    }
                }
                return null;

            case 3:
                return driver.findElement(By.id(getXpath()));
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                WebElement e = driver.findElement(By.tagName(getXpath()));
                if (getButton() != null) {
                    return getWebelement(e, getButton());
                }
                return e;
            case 7:
                WebElement tmp = driver.findElement(By.xpath(getXpath()));
                if (getButton() != null) {
                    return getWebelement(tmp, getButton());
                }
                return tmp;
            default:
                return null;
        }
    }

    public WebElement getWebelement(WebElement element, Button btn) {

        List<WebElement> list;

        switch (btn.getWayToSelect()) {

            case 1:
                return element.findElement(By.className(btn.getXpath()));
            case 2:
                list = element.findElements(By.cssSelector("" + btn.getType() + "[" + btn.getAttribute() + "='" + btn.getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (webElement.getAttribute(btn.getAttribute()).equals(btn.getAttributeValue()) && btn.getAttribute("innerHTML").contains(criteria)) {
                        return webElement;
                    }
                }
                return null;
            case 22:
                list = element.findElements(By.cssSelector("" + btn.getType() + "[" + btn.getAttribute() + "='" + btn.getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (btn.getAttribute(btn.getAttribute()).equals(btn.getAttributeValue())) {
                        return webElement;
                    }
                }
                return null;

            case 3:
                return element.findElement(By.id(btn.getXpath()));
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                return element.findElement(By.tagName(btn.getXpath()));
            case 7:
                return element.findElement(By.xpath(btn.getXpath()));
            default:
                return null;
        }
    }

    public By getLocator() {

        switch (getWayToSelect()) {

            case 1:
                return By.className(getXpath());
            case 2:
                return By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']");

            case 22:
                return By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']");
            case 3:
                return By.id(getXpath());
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                return By.tagName(getXpath());
            case 7:
                return By.xpath(getXpath());
            default:
                return null;
        }
    }

    public List<WebElement> getWebelements() {
        return Main.getDriver().findElements(By.xpath(xpath));
    }

    public void click() throws ElementisNotClickable, ElementClickInterceptedException {

        if (isPresent(Duration.ofSeconds(5))) {
            getWebelement(Main.getDriver()).click();
        } else {
            throw new ElementisNotClickable("##ACHTUNG##: Element " + getLabel() + " konnte nicht gelklickt werden!");
        }
    }

    public void click(Duration time) throws ElementisNotClickable {
        if (isPresent(time)) {
            click();
        }
    }

    public void scrollToElement(String topaligin) {
        ((JavascriptExecutor) Main.getDriver()).executeScript("arguments[0].scrollIntoView({block: \"" + topaligin + "\", behavior: \"smooth\"});", getWebelement(Main.getDriver()));
        Main.sleep(500, TimeUnit.MILLISECONDS);

    }

    public String getAttribute(String attribute) {

        return getWebelement(Main.getDriver()).getAttribute(attribute);

    }

    public void clear() {
        if (isPresent()) {
            getWebelement(Main.getDriver()).clear();

        }

    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
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
