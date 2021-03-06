package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Rohstofflager;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Main;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RohstofflagerFarmen implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(RohstofflagerFarmen.class);

    private static int neueAuftraege;

    private static LocalDateTime neueAuftraegeTime;

    private EigenesDorf own;
    private boolean push;
    private int durationInSec;


    public RohstofflagerFarmen(EigenesDorf own, boolean push) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        this.push = push;
        run();
    }

    public void run() throws NoElementTextFound, ElementisNotClickable {

        new SelectOwnVillage(getOwn());

        int ges = 0;

        MainToolbar.OBERFLAECHE.sendText("d");

        if (Rohstofflager.ROHSTOFFLAGER_EINSAMMELN.isPresent(Duration.ofMillis(2000))) {
            Rohstofflager.ROHSTOFFLAGER_EINSAMMELN.click();
            Rohstofflager.ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN.click(Duration.ofSeconds(5));
        }
        if (!Rohstofflager.AKTUELLE_PRODUKTION.getAttribute("innerHTML").contains("Aktuelle Produktion")) {
            if (neueAuftraegeTime == null || neueAuftraegeTime.plusHours(20).isBefore(LocalDateTime.now())) {
                neueAuftraegeTime = LocalDateTime.now();
                if (Rohstofflager.ANZAHL_NEUER_AUFGABEN.isPresent())
                    neueAuftraege = Integer.parseInt(Rohstofflager.ANZAHL_NEUER_AUFGABEN.getText().replaceAll("[^\\d.]", ""));
            }

            if (isPush() && Rohstofflager.LETZTES_ITEM.getAttribute("tooltip-content").equals("Reiche Ernte - steigert den Proviant in einem Dorf um 10%") && neueAuftraege >= 10) {
                if (!Rohstofflager.ROHSTOFFLAGER_STARTEN.isPresent(Duration.ofSeconds(2))) {
                    Rohstofflager.ITEMS_VERWENDEN.click(Duration.ofSeconds(2));
                    Rohstofflager.BENUTZEN.click(Duration.ofSeconds(2));
                    Rohstofflager.GEGENSTAND_BENUTZEN.click(Duration.ofSeconds(2));
                    Main.sleep(2);
                }
            }

            if (Rohstofflager.JOBS.isPresent())
                Rohstofflager.JOBS.scrollToElement("start");

            if (Rohstofflager.ROHSTOFFLAGER_STARTEN.isPresent(Duration.ofMillis(1000))) {

                String[] zeit = Rohstofflager.ROHSTOFFLAGER_STARTEN_ZEIT.getText().split(":");
                ges += Integer.parseInt(zeit[0]) * 3600 + Integer.parseInt(zeit[1]) * 60 + Integer.parseInt(zeit[2]);
                logger.info("Dauer von neuem Rohstofflage Auftrag:" + " Studnen: " + zeit[0] + " Minuten: " + zeit[1] + " Sekunden: " + zeit[2]);

                Rohstofflager.ROHSTOFFLAGER_STARTEN.click();
            }

        } else {
            String[] zeit = Rohstofflager.AKTUELLE_PRODUKTION_ZEIT.getText().split(":");
            ges += Integer.parseInt(zeit[0]) * 3600 + Integer.parseInt(zeit[1]) * 60 + Integer.parseInt(zeit[2]);
            logger.info("Dauer von aktuellem Rohstofflager Auftrag:" + " Studnen: " + zeit[0] + " Minuten: " + zeit[1] + " Sekunden: " + zeit[2]);
        }

        MainToolbar.PROFIL.scrollToElement("end");
        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
        setDurationInSec(ges);
    }

    @Override
    public boolean check() throws NoElementTextFound {
        return true;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public void setStartTime(LocalTime time) {

    }

    @Override
    public LocalTime getStartTime() {
        return null;
    }

    @Override
    public void getAvgRunTime() {

    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public EigenesDorf getOwn() {
        return own;
    }

    public void setOwn(EigenesDorf own) {
        this.own = own;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(int durationInSec) {
        this.durationInSec = durationInSec;
    }
}
