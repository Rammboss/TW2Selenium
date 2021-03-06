package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Uebersichten;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Main;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class AnzahlBisherigeAngriffe implements AuftragInterface {

    private static Logger logger = LoggerFactory.getLogger(AnzahlBisherigeAngriffe.class);

    private int priority;
    private LocalTime startTime;
    private EigenesDorf own;
    private int anzahlAngriffe;

    private static long avgRuntimeSEC;
    private static int successfulRuns;
    private static int failedRuns;

    public AnzahlBisherigeAngriffe(EigenesDorf dorf) throws NoElementTextFound, ElementisNotClickable {

        this.priority = 1;
        this.own = dorf;
        this.startTime = LocalTime.now();

        run();

    }

    public void run() throws NoElementTextFound, ElementisNotClickable {
        new SelectOwnVillage(own);

        MainToolbar.UEBERSICHTEN.click();

        if (!Uebersichten.NUR_AKTUELLES_DORF.isPresent()) {
            Uebersichten.BEFEHLE.click();
        }
        if (Uebersichten.NUR_AKTUELLES_DORF.getAttribute("class").equals("box-border-dark")) {
            Uebersichten.NUR_AKTUELLES_DORF.click();
        }

        if (Uebersichten.UNTERSTUETZUNG.getAttribute("class").equals("box-border-dark active")) {
            Uebersichten.UNTERSTUETZUNG.click();
        }
        if (Uebersichten.UMSIEDELUNG.getAttribute("class").equals("box-border-dark active")) {
            Uebersichten.UMSIEDELUNG.click();
        }

        int currentAttackCount = Uebersichten.TABLE.getWebelements().size();

        if (Uebersichten.SEITE2.isPresent()) {
            Uebersichten.SEITE2.click();
            Main.sleep(1);
            currentAttackCount += Uebersichten.TABLE.getWebelements().size();

        }

        logger.info("Anzahl der bisherigen Angriffe: " + currentAttackCount);

        setAnzahlAngriffe(currentAttackCount);


        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getAnzahlAngriffe() {
        return anzahlAngriffe;
    }

    public void setAnzahlAngriffe(int anzahlAngriffe) {
        this.anzahlAngriffe = anzahlAngriffe;
    }

    public int compareTo(AuftragInterface ai) {
        return ai.getPriority() - this.priority;
    }

    public boolean check() {
        if (getAnzahlAngriffe() <= 50 && getAnzahlAngriffe() >= 0) {
            AnzahlBisherigeAngriffe.successfulRuns++;
            AnzahlBisherigeAngriffe.avgRuntimeSEC += Duration.between(getStartTime(), LocalTime.now()).toSeconds() / successfulRuns;
            return true;
        } else {
            AnzahlBisherigeAngriffe.failedRuns++;
            return false;
        }

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void getAvgRunTime() {
        int day = (int) TimeUnit.SECONDS.toDays(AnzahlBisherigeAngriffe.avgRuntimeSEC);
        long hours = TimeUnit.SECONDS.toHours(AnzahlBisherigeAngriffe.avgRuntimeSEC) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(AnzahlBisherigeAngriffe.avgRuntimeSEC) - (TimeUnit.SECONDS.toHours(AnzahlBisherigeAngriffe.avgRuntimeSEC) * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(AnzahlBisherigeAngriffe.avgRuntimeSEC) - (TimeUnit.SECONDS.toMinutes(AnzahlBisherigeAngriffe.avgRuntimeSEC) * 60);

        logger.info("Durchschnittliche Laufzeit(h:m:s): " + hours + ":" + minute + ":" + seconds);
    }

    public void getFailedRuns() {
        logger.info("Auftrag: " + AnzahlBisherigeAngriffe.class.getSimpleName() + " ist " + AnzahlBisherigeAngriffe.failedRuns + "fehlgeschlagen!");
    }
}
