package TB2.TB2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import TB2.NewStructure.common.Auftraege.*;
import TB2.NewStructure.common.Menus.*;
import TB2.NewStructure.common.hibernate.model.*;
import TB2.NewStructure.common.units.Units;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.dao.BarbarendorfDao;
import TB2.NewStructure.common.hibernate.dao.DorfDao;
import TB2.NewStructure.common.hibernate.dao.EigenesDorfDao;
import TB2.NewStructure.common.hibernate.dao.PointDao;
import TB2.NewStructure.common.hibernate.dao.ProvinzDao;

@Component
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    static {
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

        account = new Account("Rammboss", "kalterhund", "Gaillard", new EigenesDorf(583, 567, "A001", 4205, "Rammboss"));
        // account = new Account("DerZurecker", "aleyotmi1", "Gaillard", new EigenesDorf(574,576, "Geil 001",4108,"DerZurecker"));
        // account = new Account("Don Porro", "Kacklappen", "Gaillard");
    }

    public static WebDriver driver;

    public static Account account;

    public static int index;

    public static List<EigenesDorf> ownVillages;

    public List<Barbarendorf> babas;

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

    public Main() {
        Main.index = 0;
    }


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

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Main app = context.getBean(Main.class);

        while (true) {
            try {
                app.restartDriver();
                //Main.sleep(app.rohstofflagerCheck(true));
                app.disableSound();
                app.runTask(app.checkAndInitPoints());
            } catch (Throwable e) {
                logger.info("Ein unerwarteter Fehler ist aufgetreten! Treiber wird neu gestartet!");

                context.close();
                context = new AnnotationConfigApplicationContext(AppConfig.class);
                app = context.getBean(Main.class);

                logger.error("main error", e);
            }
        }
    }

    private void runTask(List<Point> points) throws ElementisNotClickable, NumberFormatException, NoElementTextFound {

        MainToolbar.BELOHNUNG_ANNEHMEN.click(Duration.ofSeconds(3));


        GetOwnVillages getOwn = new GetOwnVillages(account);
        Main.ownVillages = getOwn.getOwnVillages();
        List<Dorf> dorfListe = dorfDao.findAll();
        List<Barbarendorf> barbarendoerfer = barbarendorfDao.findAll();


        new SelectOwnVillage(Main.ownVillages.get(0));


        // Angriff timen
        // if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
        // MainToolbar.AUF_WELTKARTE_SUCHEN.click();
        //
        // Koordinaten.X_KOORDINATE.clear();
        // Koordinaten.X_KOORDINATE.sendText(590);
        // Koordinaten.Y_KOORDINATE.clear();
        // Koordinaten.Y_KOORDINATE.sendText(557);
        // Koordinaten.JUMP_TO.click();
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
        // Koordinaten.JUMP_TO.click();
        // sleep(1);
        //
        // MainToolbar.OBERFLAECHE.clickCoords(0, 0);

        // Befehle wieder anzeigen
        babas = barbarendoerfer;

        List<Units> farmableUnits = new ArrayList<>();
        farmableUnits.add(Units.SPEER);
        farmableUnits.add(Units.AXT);
        farmableUnits.add(Units.LKAV);
        farmableUnits.add(Units.BERITTINER_BOGEN);
        farmableUnits.add(Units.SKAV);

        //findOwnVillage("A001").setBlockAttacks(true);


        while (true) {
            new RohstofflagerFarmen(Main.ownVillages.get(0), false);

            for (EigenesDorf own : Main.ownVillages) {
                if (!own.isBlockAttacks()) {
                    new FarmWithVillage(farmableUnits, own, barbarendoerfer, barbarendorfDao);
                }
            }


            // checke die Points
            for (long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(10); stop > System.nanoTime(); ) {

                points.sort(Comparator.comparingInt(o -> new DistanceCalculator(o, Main.ownVillages.get(0)).getDistance()));

                points.removeIf(p -> p.isChecked() && p.getCheckedAt().plusDays(4).isAfter(LocalDateTime.now()));

                for (int i = 0; i < points.size() && stop > System.nanoTime(); i++) {
                    Point currentPoint = points.get(i);

                    if (MainToolbar.COMPLETE_BUILDING.isPresent()) {
                        MainToolbar.COMPLETE_BUILDING.click();
                    }

                    new EnterKoordinaten(currentPoint);

                    if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Rohstofflager")
                            && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Freund einladen")) {

                        String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

                        if (Dorfoptionen.DORFINFORMATIONEN.isPresent(Duration.ofSeconds(2)) && Dorfoptionen.GRUPPEN_HINZUFUEGEN.isPresent(Duration.ofSeconds(2))
                                && !Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

                            Dorfoptionen.DORFINFORMATIONEN.click();

                            if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
                                int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                                Optional<EigenesDorf> eigenAltOptional = eigenesDorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

                                eigenAltOptional.ifPresentOrElse(x -> {
                                    x.setPunkte(dorfpunkte);
                                    x.setName(dorfname);
                                    eigenesDorfDao.save(x);
                                    logger.info("Eigenes Dorf geupdated");
                                }, () -> {
                                    eigenesDorfDao.save(new EigenesDorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte, account.getSpielername()));
                                    logger.info("Eigenes Dorf hinzugefügt");
                                });
                            }

                            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

                        } else if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

                            Dorfoptionen.DORFINFORMATIONEN.click();

                            if (Dorfinformationen.DORFINFORMATIONEN_PUNKTE.isPresent()) {

                                int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                                Optional<Barbarendorf> eigenALT = barbarendorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

                                eigenALT.ifPresentOrElse(x -> {
                                    x.setPunkte(dorfpunkte);
                                    x.setName(dorfname);
                                    barbarendorfDao.save(x);
                                    logger.info("Babarendorf updated");
                                }, () -> {
                                    barbarendorfDao.save(new Barbarendorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte, LocalDateTime.now()));
                                    logger.info("Barbarendorf hinzugefügt");
                                });
                            }

                            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

                        } else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2))) {


                            Dorfoptionen.DORFINFORMATIONEN.click();

                            if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
                                int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                                Optional<Dorf> eigenALT = dorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

                                eigenALT.ifPresentOrElse(x -> {
                                    x.setPunkte(dorfpunkte);
                                    x.setName(dorfname);
                                    dorfDao.save(x);
                                    logger.info("Dorf Gegner updated");
                                }, () -> {
                                    dorfDao.save(new Dorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte));
                                    logger.info(" Dorf Gegner hinzugefügt");
                                });
                            }

                            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

                        }
                    } else {
                        MainToolbar.OBERFLAECHE.clickCoords(0, 0);

                        if (Provinzansicht.PROVINZ_BUTTON_PROVINZDOERVER.isPresent()) {

                            Optional<Provinz> eigenALT = provinzDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

                            String provinzName = Provinzansicht.PROVINZ_NAME.getText();

                            eigenALT.ifPresentOrElse(x -> {
                                x.setName(provinzName);
                                provinzDao.save(x);
                                logger.info("Provinz updated");
                            }, () -> {
                                provinzDao.save(new Provinz(currentPoint.getX(), currentPoint.getY(), provinzName, LocalDateTime.now()));
                                logger.info("Provinz hinzugefügt");
                            });

                            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

                        }


                    }

                    currentPoint.setChecked(true);
                    currentPoint.setCheckedAt(LocalDateTime.now());

                    pointDao.save(currentPoint);

                    logger.info("Update:" + currentPoint);
                }

                Main.ownVillages = new GetOwnVillages(account).getOwnVillages();
                dorfListe = dorfDao.findAll();

                barbarendoerfer = barbarendorfDao.findAll();
                babas = barbarendoerfer;


                logger.info("Driver wird neugestartet!");
                restartDriver();
            }
        }
    }

    private void rohstoffeCheckenUndAxtBauen() throws ElementisNotClickable, NoElementTextFound {
        // Rohstoffe checken
        sleep(1);

        MainToolbar.OBERFLAECHE.sendText("v");

        if (!Dorfansicht.HAUPTGEBAEUDE.isPresent()) {
            MainToolbar.OBERFLAECHE.sendText("v");
        }

        Dorfansicht.SPEICHER.click(Duration.ofSeconds(10));
        Dorfansicht.SPEICHER2.click();

        int max = 100;
        int currentHolz = 0;
        int currentLehm = 0;
        int currentEisen = 0;
        if (Speicher.SPEICHER_HOLZ.isPresent()) {
            String[] holz = Speicher.SPEICHER_HOLZ.getText().split(" / ");
            String[] lehm = Speicher.SPEICHER_LEHM.getText().split(" / ");
            String[] eisen = Speicher.SPEICHER_EISEN.getText().split(" / ");

            max = Integer.parseInt(holz[1].replace(".", ""));
            currentHolz = Integer.parseInt(holz[0].replace(".", ""));
            currentLehm = Integer.parseInt(lehm[0].replace(".", ""));
            currentEisen = Integer.parseInt(eisen[0].replace(".", ""));
        }

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
        MainToolbar.OBERFLAECHE.sendText("v");
        sleep(1);

        MainToolbar.REKRUTIERUNGSSCHEIFE.click();
        int anzahl = 50;
        if ((currentHolz >= max || currentLehm >= max || currentEisen >= max) && Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", "")) > anzahl
                || MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen")) {
            // logger.info("Baue " + anzahl + " axtkämpfer");
            // baueAxt(anzahl);
        } else {
            logger.info("Vorraussetzungen für " + anzahl + " axtkämpfer nicht erfüllt!");

        }

        MainToolbar.BAUSCHLEIFE.click();

    }

    private void baueAxt(int anzahl) throws ElementisNotClickable {

        MainToolbar.OBERFLAECHE.sendText("b");
        sleep(1);
        Kaserne.KASERNE_AXTKAEMPFER.scrollToElement("start");
        sleep(1);
        logger.info("" + Kaserne.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent());
        logger.info("" + Kaserne.KASERNE_AXTKAEMPFER.isPresent());

        if (Kaserne.KASERNE_AXTKAEMPFER.isPresent() && !Kaserne.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent()) {
            Kaserne.KASERNE_AXTKAEMPFER.click();

            Kaserne.KASERNE_AXTKAEMPFER_VALUE.clear();
            Kaserne.KASERNE_AXTKAEMPFER_VALUE.sendText(anzahl);
            Kaserne.KASERNE_AXTKAEMPFER_VALUE.sendText(Keys.ENTER);
        }

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

        MainToolbar.ZEITLEISTE.click();
        sleep(1);

        MainToolbar.ZEITLEISTE.click();
        sleep(1);

    }

    private void disableSound() throws ElementisNotClickable {
        MainToolbar.EINSTELLUNGEN.click();
        Einstellungen.EINSTELLUNGEN_SPIEL.click();
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_TIPPS_EINSCHALTEN.click();
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_ANNIMATION1.scrollToElement("start");
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_ANNIMATION1.click();
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.scrollToElement("start");
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.click();
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("start");
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.click();

        // um normale view wiederherzustellen
        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("end");

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    private void ausgehendenAngriffeVerbergen() throws ElementisNotClickable {
        MainToolbar.EINSTELLUNGEN.click();
        Einstellungen.EINSTELLUNGEN_SPIEL.click();

        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("start");

        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.click();

        EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("end");

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    private void initVorlagen(int anzahlAngriffe) throws ElementisNotClickable, NumberFormatException, NoElementTextFound {

        MainToolbar.OBERFLAECHE.sendText("r");
        Sammelplatz.GLOBALE_VORLAGENLISTE.click();
        UebersichtVorlangenliste.FARM_STATUS.click();
        UebersichtVorlangenliste.FARM_EDIT.click();

        int verbleibendeAngriffe = 50 - anzahlAngriffe;

        logger.info("Verbleibende Angriffe: " + verbleibendeAngriffe);
        sleep(1);
        if (verbleibendeAngriffe > 0) {

            // Axtkämpfer
            VorlangeErstellenOderAendern.ANZAHL_AXT.clear();
            int tmp = Integer.parseInt(MainToolbar.ANZAHL_AXT.getText().replace(".", ""));

            VorlangeErstellenOderAendern.ANZAHL_AXT.sendText(tmp / verbleibendeAngriffe);

            if (tmp < 500) {
                VorlangeErstellenOderAendern.ANZAHL_AXT.clear();
                VorlangeErstellenOderAendern.ANZAHL_AXT.sendText(100);

            }

            // Leichte Kavellerie
            VorlangeErstellenOderAendern.ANZAHL_LKAV.clear();
            tmp = Integer.parseInt(MainToolbar.ANZAHL_LKAV.getText().replace(".", ""));

            VorlangeErstellenOderAendern.ANZAHL_LKAV.sendText(tmp / verbleibendeAngriffe);

            if (tmp < 500) {
                VorlangeErstellenOderAendern.ANZAHL_LKAV.clear();
                VorlangeErstellenOderAendern.ANZAHL_LKAV.sendText(30);

            }

            // berittene Bögen
            VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.clear();
            tmp = Integer.parseInt(MainToolbar.ANZAHL_BERITTENER_BOGEN.getText().replace(".", ""));

            VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.sendText(tmp / verbleibendeAngriffe);

            if (tmp < 500) {
                VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.clear();
                VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.sendText(30);

            }

            // SPEER
            VorlangeErstellenOderAendern.ANZAHL_SPEER.clear();
            tmp = Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", ""));

            VorlangeErstellenOderAendern.ANZAHL_SPEER.sendText(tmp / verbleibendeAngriffe);

            if (tmp < 500) {
                VorlangeErstellenOderAendern.ANZAHL_SPEER.clear();

                VorlangeErstellenOderAendern.ANZAHL_SPEER.sendText(100);

            }

            // SCHWERT
            VorlangeErstellenOderAendern.ANZAHL_SCHWERT.clear();
            VorlangeErstellenOderAendern.ANZAHL_SCHWERT.sendText(0);

            tmp = Integer.parseInt(MainToolbar.ANZAHL_SCHWERT.getText().replace(".", ""));

            if (tmp < 500) {
                VorlangeErstellenOderAendern.ANZAHL_SCHWERT.clear();

                VorlangeErstellenOderAendern.ANZAHL_SCHWERT.sendText(0);

            }

            // Paladin

            VorlangeErstellenOderAendern.ANZAHL_PALADIN.clear();
            VorlangeErstellenOderAendern.ANZAHL_PALADIN.sendText(0);
        }

        VorlangeErstellenOderAendern.HOTKEY_1.scrollToElement("end");
        VorlangeErstellenOderAendern.HOTKEY_1.click();
        VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF.click();
        VorlangeErstellenOderAendern.SPEICHERN.click();

        UebersichtVorlangenliste.FARM_STATUS.isPresent();

        if (UebersichtVorlangenliste.FARM_STATUS.getCSSClass().equals("switch switch-56x28 switch-horizontal")) {
            UebersichtVorlangenliste.FARM_STATUS.click();
        }

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
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

    public void restartDriver() throws ElementisNotClickable {

        if (driver != null) {

            driver.navigate().refresh();
            sleep(5);

            Login.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));

            sleep(2);
        }

        if (driver == null) {
            Main.driver = new FirefoxDriver();
            driver.get("https://de.tribalwars2.com/");
            driver.manage().window().maximize();
            account.login();
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

    public static WebDriver getDriver() {
        return Main.driver;
    }

    public DorfDao getDorfDao() {
        return dorfDao;
    }

    public void setDorfDao(DorfDao dorfDao) {
        this.dorfDao = dorfDao;
    }

    public PointDao getPointDao() {
        return pointDao;
    }

    public void setPointDao(PointDao pointDao) {
        this.pointDao = pointDao;
    }

    public ProvinzDao getProvinzDao() {
        return provinzDao;
    }

    public void setProvinzDao(ProvinzDao provinzDao) {
        this.provinzDao = provinzDao;
    }

    public EigenesDorfDao getEigenesDorfDao() {
        return eigenesDorfDao;
    }

    public void setEigenesDorfDao(EigenesDorfDao eigenesDorfDao) {
        this.eigenesDorfDao = eigenesDorfDao;
    }

    public BarbarendorfDao getBarbarendorfDao() {
        return barbarendorfDao;
    }

    public void setBarbarendorfDao(BarbarendorfDao barbarendorfDao) {
        this.barbarendorfDao = barbarendorfDao;
    }

}
