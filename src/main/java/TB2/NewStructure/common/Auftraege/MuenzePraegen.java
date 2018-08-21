package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Akademie;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Main;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.security.Key;
import java.time.Duration;
import java.time.LocalTime;

public class MuenzePraegen implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(MuenzePraegen.class);

    private EigenesDorf own;

    public MuenzePraegen(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;

        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {
        if (!own.isAllowedMuenzenPraegen()) return;

        new SelectOwnVillage(own);
        GetRessourcen ressourcen = new GetRessourcen(own);
        if (ressourcen.getHolz() >= ressourcen.getMaxSpeicher() || ressourcen.getLehm() >= ressourcen.getMaxSpeicher() || ressourcen.getEisen() >= ressourcen.getMaxSpeicher()) {
            MainToolbar.OBERFLAECHE.sendText("a");

            if (Akademie.BUILDING_NOT_ACTIVATERD.isPresent(Duration.ofSeconds(2))) {
                logger.info("Kein Adelshof vorhanden");
            } else {

                Akademie.WERT_ENGEBEN.click();
                Akademie.WERT_ENGEBEN_INPUT.sendText(1);
                Akademie.MUENZE_PRAEGEN.click();


            }
            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
            MainToolbar.CURRENT_VILLAGE_NAME.isPresent(Duration.ofSeconds(1));
        }
    }

    @Override
    public boolean check() throws NoElementTextFound {
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
