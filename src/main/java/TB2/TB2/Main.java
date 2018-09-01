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
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Main implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static Account account;
    public static List<EigenesDorf> ownVillages;
    public static WebDriver driver;

    static {
        account = new Account(1, "Rammboss", "kalterhund", "Gaillard", true, false, true, true);
        if (account.isUseChrome()) {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        }
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


    public static void main(String[] args) {
        AbstractApplicationContext context = null;
        Main app = null;

        while (true) {
            try {
                if (context == null || app == null) {
                    context = new AnnotationConfigApplicationContext(AppConfig.class);

                    app = context.getBean(Main.class);
                }
                restartDriver();

                //Main.sleep(app.rohstofflagerCheck(true));
                app.runTask();
            } catch (BeanCreationException e) {
                logger.info("Der Pi erstellt gerade ein Backup, warte 10 minuten!");
                sleep(10, TimeUnit.MINUTES);
            } catch (Throwable e) {
                logger.info("Ein unerwarteter Fehler ist aufgetreten! Treiber wird neu gestartet!");
                if (context != null) {
                    context.close();
                    context = new AnnotationConfigApplicationContext(AppConfig.class);
                }
                if (app != null)
                    app = context.getBean(Main.class);

                logger.error("main error", e);
            }
        }
    }


    private void runTask() throws ElementisNotClickable, NumberFormatException, NoElementTextFound, AWTException {


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

        GetOwnVillages getOwn = new GetOwnVillages(account);
        Main.ownVillages = getOwn.getOwnVillages();

        //A001
//        findOwnVillage(583, 567).setBlockAttacks(true);
        findOwnVillage(583, 567).setAllowedMuenzenPraegen(true);
        findOwnVillage(583, 567).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(583, 567).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(583, 567).setRekrutierungsAnzahl(30);
        //example
//        findOwnVillage(583, 567).setBuildingTask(Collections.singletonList(new BuildingTask(Buildings.BAUERNHOF, 30), new BuildingTask(Buildings.SPEICHER,30)));

        //A002
        findOwnVillage(583, 565).setFarableUnits(farableUnitsONLYOFFandSKAV);
        findOwnVillage(583, 565).setAllowedMuenzenPraegen(true);
        findOwnVillage(583, 565).setRekrutierungsEinheit(Units.SKAV);
        findOwnVillage(583, 565).setRekrutierungsAnzahl(15);

        //A003
//      findOwnVillage(585,562).setBlockAttacks(true);
        findOwnVillage(585, 562).setAllowedMuenzenPraegen(true);
        findOwnVillage(585, 562).setFarableUnits(farableUnitsBOTH);
        findOwnVillage(585, 562).setRekrutierungsEinheit(Units.SKAV);
        findOwnVillage(585, 562).setRekrutierungsAnzahl(15);

        //A004
        findOwnVillage(583, 569).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(583, 569).setAllowedMuenzenPraegen(true);
        findOwnVillage(583, 569).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(583, 569).setRekrutierungsAnzahl(20);

        //A005
        findOwnVillage(584, 570).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(584, 570).setAllowedMuenzenPraegen(true);
        findOwnVillage(584, 570).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(584, 570).setRekrutierungsAnzahl(15);

        //A006
        findOwnVillage(584, 569).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(584, 569).setAllowedMuenzenPraegen(true);
        findOwnVillage(584, 569).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(584, 569).setRekrutierungsAnzahl(20);

        //A007
        findOwnVillage(585, 568).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(585, 568).setAllowedMuenzenPraegen(true);
        findOwnVillage(585, 568).setRekrutierungsEinheit(Units.AXT);
        findOwnVillage(585, 568).setRekrutierungsAnzahl(50);

        //A008
        findOwnVillage(586, 568).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(586, 568).setAllowedMuenzenPraegen(true);
        findOwnVillage(586, 568).setRekrutierungsEinheit(Units.SKAV);
        findOwnVillage(586, 568).setRekrutierungsAnzahl(15);

        //A009
        findOwnVillage(582, 571).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(582, 571).setAllowedMuenzenPraegen(true);
        findOwnVillage(582, 571).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(582, 571).setRekrutierungsAnzahl(20);

        //A010
        findOwnVillage(582, 573).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(582, 573).setAllowedMuenzenPraegen(true);
        findOwnVillage(582, 573).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(582, 573).setRekrutierungsAnzahl(20);

        //A011
        findOwnVillage(582, 572).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(582, 572).setAllowedMuenzenPraegen(true);
        findOwnVillage(582, 572).setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage(582, 572).setRekrutierungsAnzahl(20);

        //A012
        findOwnVillage(581, 571).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(581, 571).setAllowedMuenzenPraegen(true);
        findOwnVillage(581, 571).setRekrutierungsEinheit(Units.AXT);
        findOwnVillage(581, 571).setRekrutierungsAnzahl(50);

        //A013
        findOwnVillage(587, 565).setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage(587, 565).setAllowedMuenzenPraegen(true);
        findOwnVillage(587, 565).setRekrutierungsEinheit(Units.AXT);
        findOwnVillage(587, 565).setRekrutierungsAnzahl(50);
        findOwnVillage(587, 565).setForceRekrutierung(true);

        findOwnVillage("Effi's A014").setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage("Effi's A014").setAllowedMuenzenPraegen(true);
        findOwnVillage("Effi's A014").setRekrutierungsEinheit(Units.AXT);
        findOwnVillage("Effi's A014").setRekrutierungsAnzahl(15);
        findOwnVillage("Effi's A014").setForceRekrutierung(true);
        findOwnVillage("Effi's A014").setRekrutierungsscheifenLimit(24);

        findOwnVillage("Effi's B001").setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage("Effi's B001").setAllowedMuenzenPraegen(true);
        findOwnVillage("Effi's B001").setRekrutierungsEinheit(Units.SKAV);
        findOwnVillage("Effi's B001").setRekrutierungsAnzahl(15);

        findOwnVillage("Effi's B002").setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage("Effi's B002").setAllowedMuenzenPraegen(true);
        findOwnVillage("Effi's B002").setRekrutierungsEinheit(Units.AXT);
        findOwnVillage("Effi's B002").setRekrutierungsAnzahl(15);
        findOwnVillage("Effi's B002").setForceRekrutierung(true);

        findOwnVillage("Effi's C001").setFarableUnits(farableUnitsONLYOFF);
        findOwnVillage("Effi's C001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's C001").setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage("Effi's C001").setRekrutierungsAnzahl(20);
        findOwnVillage("Effi's C001").setForceRekrutierung(true);
        findOwnVillage("Effi's C001").setRekrutierungsscheifenLimit(24);

        findOwnVillage("Effi's D001").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's D001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's D001").setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage("Effi's D001").setRekrutierungsAnzahl(20);
        findOwnVillage("Effi's D001").setForceRekrutierung(true);
        findOwnVillage("Effi's D001").setRekrutierungsscheifenLimit(24);

        findOwnVillage("Effi's E001").setBlockAttacks(true);
        findOwnVillage("Effi's E001").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's E001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's E001").setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage("Effi's E001").setRekrutierungsAnzahl(20);

        findOwnVillage("Effi's E002").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's E002").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's E002").setRekrutierungsEinheit(Units.SKAV);
        findOwnVillage("Effi's E002").setRekrutierungsAnzahl(15);

        findOwnVillage("Effi's E003").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's E003").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's E003").setRekrutierungsEinheit(Units.AXT);
        findOwnVillage("Effi's E003").setRekrutierungsAnzahl(15);
        findOwnVillage("Effi's E003").setForceRekrutierung(true);

        findOwnVillage("Effi's F001").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's F001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's F001").setRekrutierungsEinheit(Units.AXT);
        findOwnVillage("Effi's F001").setRekrutierungsAnzahl(50);
        findOwnVillage("Effi's F001").setForceRekrutierung(true);

        findOwnVillage("Effi's G001").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's G001").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's G001").setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage("Effi's G001").setRekrutierungsAnzahl(20);
        findOwnVillage("Effi's G001").setForceRekrutierung(true);

        findOwnVillage("Effi's G002").setFarableUnits(farableUnitsBOTH);
        findOwnVillage("Effi's G002").setAllowedMuenzenPraegen(false);
        findOwnVillage("Effi's G002").setRekrutierungsEinheit(Units.LKAV);
        findOwnVillage("Effi's G002").setRekrutierungsAnzahl(20);
        findOwnVillage("Effi's G002").setForceRekrutierung(true);


        while (true) {

            System.gc();
            // mit den Eignenen Dörfer Farmen
            for (EigenesDorf own : Main.ownVillages) {
                new BuildUnits(own);
                new BuildBuildings(own);
                new FarmWithVillage(own, barbarendorfDao);
                new MuenzePraegen(own);
                new RohstofflagerFarmen(own, true);

            }
            // Points vorbereiten
            List<Point> points = checkAndInitPoints();
            points.sort(Comparator.comparingInt(o -> new DistanceCalculator(o, Main.ownVillages.get(0)).getDistance()));
            points.removeIf(p -> p.isChecked() && p.getCheckedAt().plusDays(4).isAfter(LocalDateTime.now()));
            System.gc(); // um Arbeitsspeicher zu leeren (byte[] - Array)!!!

            // Punkte 10 minuten lang checken oder bis alle punkte durchlaufen sind
            if (!account.isWoodPC()) {
                long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(10);

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

    private void ausgehendenAngriffeVerbergen() throws ElementisNotClickable {
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
            if (browserName.equals("firefox")) {
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
                    driver = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (browserName.equals("chrome")) {
                driver.navigate().refresh();
                sleep(2);
                MainToolbar.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));
                MainToolbar.BELOHNUNG_ANNEHMEN.click(Duration.ofSeconds(3));


            } else {
                driver.quit();
                driver = null;
                System.gc();
            }

            sleep(2);

        }

        if (driver == null) {
            if (account.isUseChrome()) {
                Main.driver = new ChromeDriver();
            } else {
                Main.driver = new FirefoxDriver();
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


    @Override
    public void run() {

    }
}
