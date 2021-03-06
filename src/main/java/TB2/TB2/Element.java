package TB2.TB2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Element {

    private static Logger logger = LoggerFactory.getLogger(Element.class);

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

    private Element element;

    private Actions action;

    public Element(String label, String xpath, int wayToSelect) {
        super();
        this.action = new Actions(Main.getDriver());
        this.label = label;
        this.xpath = xpath;
        this.wayToSelect = wayToSelect;

    }

    public Element(String label, String type, String attribute, String attributeValue, String criteria, int wayToSelect) {
        super();
        this.action = new Actions(Main.getDriver());
        this.xpath = null;
        this.label = label;
        this.type = type;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.criteria = criteria;
        this.wayToSelect = wayToSelect;
    }

    public Element(String label, String type, String attribute, String attributeValue, int wayToSelect) {
        super();
        this.action = new Actions(Main.getDriver());
        this.xpath = null;
        this.label = label;
        this.type = type;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.wayToSelect = wayToSelect;

    }

    public boolean isPresent() {


        try {

            new FluentWait<>(Main.getDriver()).withTimeout(Duration.ofMillis(500 * Account.performaceMultiplier)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated((getLocator())));

            if (getWebelement(Main.getDriver()) == null) {
                return false;
            } else if (getElement() != null && getElement().getWebelement(Main.getDriver()) == null) {
                return false;
            }
            return true;

        } catch (TimeoutException e) {
            logger.debug("Element:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public boolean isPresent(Duration timeout) {


        try {

            new FluentWait<>(Main.getDriver()).withTimeout(Duration.ofSeconds(timeout.toSeconds() * Account.performaceMultiplier)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated((getLocator())));
            if (getWebelement(Main.getDriver()) == null) {
                return false;
            } else if (getElement() != null && getElement().getWebelement(Main.getDriver()) == null) {
                return false;
            }
            return true;
        } catch (TimeoutException e) {
            logger.debug("Element:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public void mouseOver() {
        action.moveToElement(getWebelement(Main.getDriver())).build().perform();
    }

    public void clickCoords(int x, int y) {
        Actions a = new Actions(Main.getDriver());
        a.moveToElement(getWebelement(Main.getDriver()));
        a.moveByOffset(x / 2, y / 2).click().perform();
    }

    public boolean isNOTPresent(Duration timeout) {

        try {
            new FluentWait<>(Main.getDriver()).withTimeout(Duration.ofSeconds(timeout.toSeconds() * Account.performaceMultiplier)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated((getLocator())));

            return true;
        } catch (TimeoutException e) {
            logger.debug("Element:" + getLabel() + " konnte nicht gefunden werden!", e);
            return false;
        }
    }

    public String getText() {
        return getWebelement(Main.getDriver()).getText();
    }

    public void sendText(String text) {
        getWebelement(Main.getDriver()).sendKeys(text);
    }

    public void sendText(int text) {
        getWebelement(Main.getDriver()).sendKeys(text + "");
    }

    public void sendText(Keys escape) {
        getWebelement(Main.getDriver()).sendKeys(escape);
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

                list = driver.findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (webElement.getAttribute(getAttribute()).equals(getAttributeValue()) && webElement.getAttribute("innerHTML").contains(criteria)) {
                        if (getElement() != null) {
                            return getWebelement(webElement, getElement());
                        }
                        return webElement;
                    }
                }
                return null;

            case 22:
                list = driver.findElements(By.cssSelector("" + getType() + "[" + getAttribute() + "='" + getAttributeValue() + "']"));

                for (WebElement webElement : list) {
                    if (webElement.getAttribute(getAttribute()).equals(getAttributeValue())) {
                        if (getElement() != null) {
                            return getWebelement(webElement, getElement());
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
                if (getElement() != null) {
                    return getWebelement(e, getElement());
                }
                return e;
            case 7:
                WebElement tmp = driver.findElement(By.xpath(getXpath()));
                if (getElement() != null) {
                    return getWebelement(tmp, getElement());
                }
                return tmp;
            default:
                return null;
        }
    }

    public WebElement getWebelement(WebElement element, Element btn) {

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

    public void click() {

        getWebelement(Main.getDriver()).click();
    }

    public void click(Duration time) {
        if (isPresent(time)) {
            click();
        }
    }

    public void scrollToElement(String topaligin) {
        ((JavascriptExecutor) Main.getDriver()).executeScript("arguments[0].scrollIntoView({block: \"" + topaligin + "\", behavior: \"instant\"});", getWebelement(Main.getDriver()));
//        isPresent(Duration.ofSeconds(5));
//        Main.sleep(500, TimeUnit.MILLISECONDS);

    }

    public String getAttribute(String attribute) {

        return getWebelement(Main.getDriver()).getAttribute(attribute);

    }

    public void clear() {
        getWebelement(Main.getDriver()).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        getWebelement(Main.getDriver()).clear();


    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
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
