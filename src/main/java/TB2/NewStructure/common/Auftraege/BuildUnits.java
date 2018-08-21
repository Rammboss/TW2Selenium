package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Kaserne;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.units.*;
import TB2.TB2.Element;
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
        GetRessourcen ressourcen = new GetRessourcen(own);
        int proviant = Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", ""));

        MainToolbar.OBERFLAECHE.sendText("b");
        Kaserne.KASERNE.isPresent(Duration.ofSeconds(3));

        MainToolbar.REKRUTIERUNGSSCHEIFE.click();

        boolean enoughProviant = false;


        switch (own.getRekrutierungsEinheit()) {
            case SPEER: {
                if (proviant > new Speerkaempfer().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);

                break;
            }
            case SCHWERT: {
                if (proviant > new Schwertkaempfer().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case AXT: {
                if (proviant > new Axtkaempfer().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case BOGEN: {
                if (proviant > new Bogenschuetze().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case LKAV: {
                if (proviant > new LeichteKavallerie().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case BERITTINER_BOGEN: {
                if (proviant > new BerittenerBogen().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case SKAV: {
                if (proviant > new SchwereKavalerie().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case RAMMEN: {
                if (proviant > new Rammbock().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
                break;
            }
            case KATAPULT: {
                if (proviant > new Katapult().getProviant() * own.getRekrutierungsAnzahl())
                    enoughProviant = true;

                if (enoughProviant && ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || enoughProviant && MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen"))
                    baueUnit(Kaserne.SPEER, Kaserne.SPEER_INPUT);
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

    private void baueUnit(Element unit, Element unitInput) throws ElementisNotClickable {
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
