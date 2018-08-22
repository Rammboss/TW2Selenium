package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Sammelplatz;
import TB2.NewStructure.common.Menus.UebersichtVorlangenliste;
import TB2.NewStructure.common.Menus.VorlangeErstellenOderAendern;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.units.Units;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InitVorlagen implements AuftragInterface {

    private static Logger logger = LoggerFactory.getLogger(InitVorlagen.class);

    private int priority;
    private LocalTime startTime;
    private EigenesDorf own;
    private Map<Units, Integer> units;

    private static long avgRuntimeSEC;
    private static int successfulRuns;
    private static int failedRuns;

    public InitVorlagen(EigenesDorf own, Map<Units, Integer> units) throws NoElementTextFound, ElementisNotClickable {

        this.priority = 1;
        this.own = own;
        this.units = units;
        this.startTime = LocalTime.now();
        run();

    }

    public void run() throws ElementisNotClickable {

        MainToolbar.OBERFLAECHE.sendText("r");
        Sammelplatz.GLOBALE_VORLAGENLISTE.click();
        UebersichtVorlangenliste.FARM_EDIT.click();


        VorlangeErstellenOderAendern.ANZAHL_SPEER.isPresent(Duration.ofSeconds(2));

        VorlangeErstellenOderAendern.ANZAHL_SPEER.clear();
        VorlangeErstellenOderAendern.ANZAHL_SCHWERT.clear();
        VorlangeErstellenOderAendern.ANZAHL_AXT.clear();
        VorlangeErstellenOderAendern.ANZAHL_BOGEN.clear();
        VorlangeErstellenOderAendern.ANZAHL_LKAV.clear();
        VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.clear();
        VorlangeErstellenOderAendern.ANZAHL_SKAV.clear();
        VorlangeErstellenOderAendern.ANZAHL_RAMMEN.clear();
        VorlangeErstellenOderAendern.ANZAHL_BERSERKER.scrollToElement("start");
        VorlangeErstellenOderAendern.ANZAHL_BERSERKER.clear();
        VorlangeErstellenOderAendern.ANZAHL_TREBUCHET.clear();
        VorlangeErstellenOderAendern.ANZAHL_ADELSGESCHLECHT.clear();
        VorlangeErstellenOderAendern.ANZAHL_PALADIN.clear();
        VorlangeErstellenOderAendern.ANZAHL_KATAPULT.clear();


        for (Map.Entry<Units, Integer> entry : units.entrySet()) {
            Units key = entry.getKey();
            int value = entry.getValue();

            switch (key) {

                case SPEER: {
                    VorlangeErstellenOderAendern.ANZAHL_SPEER.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_SPEER.sendText(value);
                    break;
                }
                case SCHWERT: {
                    VorlangeErstellenOderAendern.ANZAHL_SCHWERT.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_SCHWERT.sendText(value);
                    break;
                }
                case AXT: {
                    VorlangeErstellenOderAendern.ANZAHL_AXT.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_AXT.sendText(value);
                    break;
                }
                case BOGEN: {
                    VorlangeErstellenOderAendern.ANZAHL_BOGEN.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_BOGEN.sendText(value);
                    break;
                }
                case LKAV: {
                    VorlangeErstellenOderAendern.ANZAHL_LKAV.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_LKAV.sendText(value);
                    break;
                }
                case BERITTINER_BOGEN: {
                    VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_BERITTENER_BOGEN.sendText(value);
                    break;
                }
                case SKAV: {
                    VorlangeErstellenOderAendern.ANZAHL_SKAV.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_SKAV.sendText(value);
                    break;
                }
                case RAMMEN: {
                    VorlangeErstellenOderAendern.ANZAHL_RAMMEN.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_RAMMEN.sendText(value);
                    break;
                }
                case BERSERKER: {
                    VorlangeErstellenOderAendern.ANZAHL_BERSERKER.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_BERSERKER.sendText(value);
                    break;
                }
                case TREBUCHET: {
                    VorlangeErstellenOderAendern.ANZAHL_TREBUCHET.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_TREBUCHET.sendText(value);
                    break;
                }
                case ADELSGESCHLECHT: {
                    VorlangeErstellenOderAendern.ANZAHL_ADELSGESCHLECHT.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_ADELSGESCHLECHT.sendText(value);
                    break;
                }
                case PALADIN: {
                    VorlangeErstellenOderAendern.ANZAHL_PALADIN.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_PALADIN.sendText(value);
                    break;
                }
                case KATAPULT: {
                    VorlangeErstellenOderAendern.ANZAHL_KATAPULT.scrollToElement("start");
                    VorlangeErstellenOderAendern.ANZAHL_KATAPULT.sendText(value);
                    break;
                }
            }
        }

        VorlangeErstellenOderAendern.HOTKEY_1.scrollToElement("end");
        VorlangeErstellenOderAendern.HOTKEY_1.click();
        VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF.click();
        VorlangeErstellenOderAendern.SPEICHERN.click();

        UebersichtVorlangenliste.FARM_STATUS.isPresent(Duration.ofSeconds(3));

        if (UebersichtVorlangenliste.FARM_STATUS.getCSSClass().equals("switch switch-56x28 switch-horizontal")) {
            UebersichtVorlangenliste.FARM_STATUS.click();
        }

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);


    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int compareTo(AuftragInterface ai) {
        return ai.getPriority() - this.priority;
    }

    public boolean check() throws NoElementTextFound {
        // @todo check muss sich noch überlegt werden
        if (true) {
            InitVorlagen.successfulRuns++;
            InitVorlagen.avgRuntimeSEC += Duration.between(getStartTime(), LocalTime.now()).toSeconds() / successfulRuns;
            return true;
        } else {
            InitVorlagen.failedRuns++;
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
        int day = (int) TimeUnit.SECONDS.toDays(InitVorlagen.avgRuntimeSEC);
        long hours = TimeUnit.SECONDS.toHours(InitVorlagen.avgRuntimeSEC) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(InitVorlagen.avgRuntimeSEC) - (TimeUnit.SECONDS.toHours(InitVorlagen.avgRuntimeSEC) * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(InitVorlagen.avgRuntimeSEC) - (TimeUnit.SECONDS.toMinutes(InitVorlagen.avgRuntimeSEC) * 60);

        logger.info("Durchschnittliche Laufzeit(h:m:s): " + hours + ":" + minute + ":" + seconds);

    }
}
