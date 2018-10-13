package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Kaserne;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.units.*;
import TB2.TB2.Element;
import TB2.TB2.Main;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

public class BuildUnits implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(BuildUnits.class);

    private EigenesDorf own;

    public BuildUnits(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {
        if (own.getRekrutierungsEinheit() == null || own.getRekrutierungsAnzahl() <= 0) return;

        new SelectOwnVillage(own);
        new GetRessourcen(own);

        int proviant = Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", ""));

        MainToolbar.OBERFLAECHE.sendText("b");
        Kaserne.KASERNE.isPresent(Duration.ofSeconds(3));

        Kaserne.RAMMEN.scrollToElement("start");

        Kaserne.RAMMEN.isPresent(Duration.ofSeconds(3));

        if (Kaserne.DAUER_TRUPPEN_BAUSCHLEIFE.isPresent()) {
            if (checkTime()) {
                Kaserne.SPEER.scrollToElement("end");
                MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
                return;
            }
        }

        Kaserne.KASERNE.scrollToElement("end");


        MainToolbar.REKRUTIERUNGSSCHEIFE.isPresent(Duration.ofSeconds(5));
        MainToolbar.REKRUTIERUNGSSCHEIFE.click();

        boolean enoughProviantAndRessourcen = false;

        switch (own.getRekrutierungsEinheit()) {
            case SPEER: {
                Speerkaempfer unit = new Speerkaempfer();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }
                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case SCHWERT: {
                Schwertkaempfer unit = new Schwertkaempfer();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.SCHWERT, Kaserne.SCHWERT_INPUT);
                break;
            }
            case AXT: {
                Axtkaempfer unit = new Axtkaempfer();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.AXTKAEMPFER, Kaserne.AXTKAEMPFER_INPUT);
                break;
            }
            case BOGEN: {
                Bogenschuetze unit = new Bogenschuetze();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.BOGEN, Kaserne.BOGEN_INPUT);
                break;
            }
            case LKAV: {
                LeichteKavallerie unit = new LeichteKavallerie();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.LKAV, Kaserne.LKAV_INPUT);
                break;
            }
            case BERITTINER_BOGEN: {
                BerittenerBogen unit = new BerittenerBogen();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.BERITTINER_BOGEN, Kaserne.BERITTINER_BOGEN_INPUT);
                break;
            }
            case SKAV: {
                SchwereKavalerie unit = new SchwereKavalerie();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.SKAV, Kaserne.SKAV_INPUT);
                break;
            }
            case RAMMEN: {
                Rammbock unit = new Rammbock();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.RAMMEN, Kaserne.RAMMEN_INPUT);
                break;
            }
            case KATAPULT: {
                Katapult unit = new Katapult();

                if (own.isForceRekrutierung()) {
                    if (proviant > unit.getProviant() && enoughRessourchen(unit, 1))
                        enoughProviantAndRessourcen = true;
                } else {
                    if (proviant > unit.getProviant() * own.getRekrutierungsAnzahl() && enoughRessourchen(unit, own.getRekrutierungsAnzahl()))
                        enoughProviantAndRessourcen = true;
                }

                if (enoughProviantAndRessourcen)
                    baueUnit(Kaserne.KATAPULT, Kaserne.KATAPULT_INPUT);
                break;
            }
            default: {
                logger.info("Einheit existiert nicht zur Ausbildung in der Kaserne!!!");
                break;
            }


        }

        Kaserne.KASERNE.scrollToElement("end");
        MainToolbar.BAUSCHLEIFE.click();
        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

    }

    private boolean checkTime() {

        String[] times = Kaserne.DAUER_TRUPPEN_BAUSCHLEIFE.getText().replace("Alle Einheiten abgeschlossen in: ", "").split(":");
        switch (times.length) {
            case 1: {// sekunden (sollte unmöglich sein!)

                return false;

            }
            case 2: { // min
                return false;
            }
            case 3: { // stunden

                return Integer.parseInt(times[0]) > own.getRekrutierungsscheifenLimit();
            }
            case 4: { // tage

                return true;

            }
            default: {
                return false;
            }
        }


    }

    private boolean enoughRessourchen(RekrutableUnits unit, int rekrutierungsAnzahl) {

        int holzKosten = unit.getHolzKosten() * rekrutierungsAnzahl;
        int lehmKosten = unit.getLehmKosten() * rekrutierungsAnzahl;
        int eisenKosten = unit.getEisenKosten() * rekrutierungsAnzahl;

        return holzKosten < own.getSpeicher().getHolz() && lehmKosten < own.getSpeicher().getLehm() && eisenKosten < own.getSpeicher().getEisen();


    }


    private void baueUnit(Element unit, Element unitInput) {
        unit.scrollToElement("start");
        unit.isPresent(Duration.ofSeconds(3));
        unit.click();
        if (unit.getAttribute("class").contains("unit-unavailable")) {

            logger.info("Einheit: " + unit.getClass().getName() + " kann noch nicht rekrutiert werden!!!");

        } else {
            unitInput.sendText(own.getRekrutierungsAnzahl());
            unitInput.sendText(Keys.ENTER);
        }
    }

    @Override
    public boolean check() {
        return false;
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
}
