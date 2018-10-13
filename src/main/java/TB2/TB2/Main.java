package TB2.TB2;

import TB2.NewStructure.common.Auftraege.*;
import TB2.NewStructure.common.Gebaeude.Buildings;
import TB2.NewStructure.common.Menus.Einstellungen;
import TB2.NewStructure.common.Menus.EinstellungenSubSpiel;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.dao.*;
import TB2.NewStructure.common.hibernate.model.DistanceCalculator;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Point;
import TB2.NewStructure.common.units.Units;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static Account account;
    public static List<EigenesDorf> ownVillages;
    public static WebDriver driver;

    public static int screenshotCounter = 0;

    static {
        account = new Account(1, "Rammboss", "kalterhund", "Jasenov", true, false, true, false, 7);

    }

    @Autowired
    private DorfDao dorfDao;

    @Autowired
    private PointDao pointDao;

    @Autowired
    private ProvinzDao provinzDao;

    @Autowired
    private EigenesDorfDao eigenesDorfDao;

    @Autowired
    private BarbarendorfDao barbarendorfDao;

    private EigenesDorf nextVillage;

    public List<Point> checkAndInitPoints() {
        List<Point> pointlist = new ArrayList<>();

        if (pointDao.count() == 0) {
            for (int x = 0; x <= 1000; x++) {
                for (int y = 0; y <= 1000; y++) {
                    pointlist.add(new Point(x, y, false));
                }
            }
            logger.info("Speichere Points....");
            pointDao.saveAll(pointlist);
        } else {
            pointlist = pointDao.findAll();
        }
        return pointlist;
    }


    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = null;
        Main app = null;

        while (true) {
            try {
                if (context == null || app == null) {
                    context = new AnnotationConfigApplicationContext(AppConfig.class);

                    app = context.getBean(Main.class);
                }
                restartDriver();

//                Main.ownVillages = new GetOwnVillages(account).getOwnVillages();
//                Main.sleep(new RohstofflagerFarmen(app.findOwnVillage("Effi's A011"),true).getDurationInSec());


                app.runTask();
            } catch (BeanCreationException e) {
                logger.info("Der Pi erstellt gerade ein Backup, warte 10 minuten!");
                sleep(10, TimeUnit.MINUTES);
            } catch (Throwable e) {

                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                // Now you can do whatever you need to do with it, for example copy somewhere
                FileUtils.copyFile(scrFile, new File("C:\\Temp\\bot\\" + screenshotCounter++ + ".png"));


                EigenesDorf last = app.getNextVillage();

                logger.info("Ein unerwarteter Fehler ist aufgetreten! Treiber wird neu gestartet!");
                if (context != null) {
                    context.close();
                    context = new AnnotationConfigApplicationContext(AppConfig.class);
                }
                if (app != null) {
                    app = context.getBean(Main.class);
                    app.setNextVillage(last);
                }


                logger.error("main error", e);
            }
        }
    }


    private void runTask() throws ElementisNotClickable, NumberFormatException, NoElementTextFound {


        // Angriff timen
        // if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
        // MainToolbar.AUF_WELTKARTE_SUCHEN.click();
        //
        // Koordinaten.X_KOORDINATE.clear();
        // Koordinaten.X_KOORDINATE.sendText(590);
        // Koordinaten.Y_KOORDINATE.clear();
        // Koordinaten.Y_KOORDINATE.sendText(557);
        // Koordinaten.JUMP_TO_VILLAGE.click();
        //
        // Dorfoptionen.TRUPPEN_SCHICKEN.click(2);
        //
        // List<WebElement> list =
        // TruppenSchicken.EINHEITEN_TABELLE.getWebelement().findElements(By.tagName("li"));
        //
        // for (WebElement webElement : list) {
        // if (webElement.getAttribute("innerHTML").contains("Axtkämpfer")) {
        //
        // List<WebElement> axt = webElement.findElements(By.tagName("input"));
        // axt.get(1).click();
        // axt.get(1).sendKeys("1000");
        // }
        // }
        //
        // MainToolbar.AUF_WELTKARTE_SUCHEN.click();
        //
        // Koordinaten.X_KOORDINATE.clear();
        // Koordinaten.X_KOORDINATE.sendText(584);
        // Koordinaten.Y_KOORDINATE.clear();
        // Koordinaten.Y_KOORDINATE.sendText(568);
        // Koordinaten.JUMP_TO_VILLAGE.click();
        // sleep(1);
        //
        // MainToolbar.OBERFLAECHE.clickCoords(0, 0);

        List<Units> farableUnitsONLYOFF = Arrays.asList(Units.AXT, Units.LKAV, Units.BERITTINER_BOGEN);
        List<Units> farableUnitsONLYOFFandSKAV = Arrays.asList(Units.AXT, Units.LKAV, Units.BERITTINER_BOGEN, Units.SKAV);
        List<Units> farableUnitsBOTH = Arrays.asList(Units.SPEER, Units.AXT, Units.LKAV, Units.BERITTINER_BOGEN, Units.SKAV);
        List<Units> farableUnitsALL = Arrays.asList(Units.SPEER, Units.AXT, Units.SCHWERT, Units.BOGEN, Units.LKAV, Units.BERITTINER_BOGEN, Units.SKAV, Units.PALADIN);

        GetOwnVillages getOwn = new GetOwnVillages(account);
        Main.ownVillages = getOwn.getOwnVillages();

        //A001
//      findOwnVillage("Effi's A001").setBlockAttacks(true);
        findOwnVillage("Effi's A001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's A001").setFarableUnits(farableUnitsALL);
        findOwnVillage("Effi's A001").setRekrutierungsEinheit(Units.AXT);
        findOwnVillage("Effi's A001").setRekrutierungsAnzahl(1);
        findOwnVillage("Effi's A001").setForceRekrutierung(false);
        findOwnVillage("Effi's A001").setRekrutierungsscheifenLimit(1);
//        findOwnVillage("Effi's A001").setBuildingTask(List.of(
//                new BuildingTask(Buildings.HOLZFAELLER, 15),
//                new BuildingTask(Buildings.EISENMINE, 15),
//                new BuildingTask(Buildings.LEHMGRUBE, 15)));


        while (true) {

            System.gc();
            // mit den Eignenen Dörfer Farmen

//            new BuildBuildingNew();


            for (int i = 0; i < Main.ownVillages.size(); i++) {

                EigenesDorf current = Main.ownVillages.get(i);

                if (getNextVillage() == null || current.getX() == getNextVillage().getX() && current.getY() == getNextVillage().getY()) {

                    new RohstofflagerFarmen(current, false);
                    new BuildBuildings(current);
                    new BuildUnits(current);
                    new MuenzePraegen(current);
                    new FarmWithVillage(current, barbarendorfDao);

                    if (i + 1 >= Main.ownVillages.size()) {
                        setNextVillage(null);

                    } else {
                        setNextVillage(Main.ownVillages.get(i + 1));
                    }
                }
            }

//            new RohstofflagerFarmen(findOwnVillage("Effi's G002"), true);

            // Points vorbereiten
            List<Point> points = checkAndInitPoints();
            points.sort(Comparator.comparingInt(o -> new DistanceCalculator(o, Main.ownVillages.get(0)).getDistance()));
            points.removeIf(p -> p.isChecked() && p.getCheckedAt().plusDays(1).isAfter(LocalDateTime.now()));
            System.gc(); // um Arbeitsspeicher zu leeren (byte[] - Array)!!!

            // Punkte 10 minuten lang checken oder bis alle punkte durchlaufen sind
            if (!account.isWoodPC()) {
                long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(account.getScoutWorldTime());

                for (int i = 0; i < points.size() && stop > System.nanoTime(); i++) {
                    new CheckPoint(points.get(i), barbarendorfDao, provinzDao, pointDao, eigenesDorfDao, dorfDao, account);
                }
            }
            logger.info("Driver wird neugestartet!");
            restartDriver();
        }
    }

    private static void disableSound() throws ElementisNotClickable {
        MainToolbar.EINSTELLUNGEN.click();
        Einstellungen.EINSTELLUNGEN_SPIEL.isPresent(Duration.ofSeconds(5));

        Einstellungen.EINSTELLUNGEN_SPIEL.click();
        EinstellungenSubSpiel.TIPPS_EINSCHALTEN.click();

        EinstellungenSubSpiel.ANIMATIONEN_AKTIVIEREN.scrollToElement("start");
        EinstellungenSubSpiel.ANIMATIONEN_AKTIVIEREN.click();

        EinstellungenSubSpiel.ANNIMATION1.scrollToElement("start");
        EinstellungenSubSpiel.ANNIMATION1.click();

        EinstellungenSubSpiel.ZOOM_ANIMATIONEN_AKTIVIEREN.scrollToElement("start");
        EinstellungenSubSpiel.ZOOM_ANIMATIONEN_AKTIVIEREN.click();

        EinstellungenSubSpiel.NEBEL_AKTIVIEREN.scrollToElement("start");
        EinstellungenSubSpiel.NEBEL_AKTIVIEREN.click();

        EinstellungenSubSpiel.BAUM_AKTIVIEREN.scrollToElement("start");
        EinstellungenSubSpiel.BAUM_AKTIVIEREN.click();

        EinstellungenSubSpiel.AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("start");
        EinstellungenSubSpiel.AUSGEHENDE_BEFEHLE_ANZEIGEN.click();

        EinstellungenSubSpiel.HANDELSBEFEHLE_AKTIVIEREN.scrollToElement("start");
        EinstellungenSubSpiel.HANDELSBEFEHLE_AKTIVIEREN.click();

        EinstellungenSubSpiel.MUSIK_SOUND.scrollToElement("start");
        EinstellungenSubSpiel.MUSIK_SOUND.click();

        EinstellungenSubSpiel.EFFEKT_SOUND.scrollToElement("start");
        EinstellungenSubSpiel.EFFEKT_SOUND.click();

        // um normale view wiederherzustellen
        EinstellungenSubSpiel.EFFEKT_SOUND.scrollToElement("end");

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    private void ausgehendenAngriffeVerbergen() {
        MainToolbar.EINSTELLUNGEN.click();
        Einstellungen.EINSTELLUNGEN_SPIEL.click();

        EinstellungenSubSpiel.AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("start");

        EinstellungenSubSpiel.AUSGEHENDE_BEFEHLE_ANZEIGEN.click();

        EinstellungenSubSpiel.AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("end");

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    public static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
        }
    }

    static void sleep(int i, TimeUnit milliseconds) {
        try {
            milliseconds.sleep(i);

        } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
        }
    }

    public static void restartDriver() throws ElementisNotClickable {

        if (driver != null) {

            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            String browserName = cap.getBrowserName();
            switch (browserName) {
                case "firefox":
                    try {
                        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
                        Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
                        Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                        driver = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    driver.navigate().refresh();
//                    sleep(2);
//                    MainToolbar.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));
//                    MainToolbar.BELOHNUNG_ANNEHMEN.click(Duration.ofSeconds(3));
                    break;
                case "chrome":
                    driver.navigate().refresh();
                    sleep(2);
                    MainToolbar.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));
                    MainToolbar.BELOHNUNG_ANNEHMEN.click(Duration.ofSeconds(3));


                    break;
                default:
                    driver.quit();
                    driver = null;
                    System.gc();
                    break;
            }

            sleep(2);

        }

        if (driver == null) {
            if (account.isUseChrome()) {
//                System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                Main.driver = new ChromeDriver();
            } else {
//                System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

//                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//                capabilities.setCapability("marionette", true);

//                FirefoxOptions options = new FirefoxOptions();
//                options.setLogLevel(FirefoxDriverLogLevel.ERROR);
                Main.driver = new FirefoxDriver();

                driver.manage().deleteAllCookies();
            }
            if (account.isUseSecondMonitor())
                driver.manage().window().setPosition(new org.openqa.selenium.Point(2100, 0));
            driver.get("https://de.tribalwars2.com/");
            driver.manage().window().maximize();

            System.gc();
            account.login();

            MainToolbar.BELOHNUNG_ANNEHMEN.click(Duration.ofSeconds(3));

            disableSound();
        }

    }

    public EigenesDorf findOwnVillage(String name) {
        if (Main.ownVillages.isEmpty())
            return null;

        for (EigenesDorf own : Main.ownVillages) {

            if (own.getName().equals(name)) {
                return own;
            }

        }
        return null;
    }

    public EigenesDorf findOwnVillage(int x, int y) {
        if (Main.ownVillages.isEmpty())
            return null;

        for (EigenesDorf own : Main.ownVillages) {

            if (own.getX() == x && own.getY() == y) {
                return own;
            }

        }
        return null;
    }

    public static WebDriver getDriver() {
        return Main.driver;
    }

    public EigenesDorf getNextVillage() {
        return nextVillage;
    }

    public void setNextVillage(EigenesDorf nextVillage) {
        this.nextVillage = nextVillage;
    }
}
