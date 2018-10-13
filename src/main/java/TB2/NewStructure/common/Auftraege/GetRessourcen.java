package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfansicht;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Speicher;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Main;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;


public class GetRessourcen implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(GetRessourcen.class);


    private EigenesDorf own;

    public GetRessourcen(EigenesDorf own) {
        this.own = own;
        run();
    }

    @Override
    public void run() {

        logger.info("Analysiere Ressourchen von: " + own.getName());

        int holz = Integer.parseInt(MainToolbar.HOLZ.getText().replace(".", ""));
        own.getSpeicher().setHolz(holz);
        int holzBar = Integer.parseInt(MainToolbar.HOLZ_PROGRESSBAR.getAttribute("style").replaceAll("[^\\d.]", ""));

        int lehm = Integer.parseInt(MainToolbar.LEHM.getText().replace(".", ""));
        own.getSpeicher().setLehm(lehm);
        int lehmBar = Integer.parseInt(MainToolbar.LEHM_PROGRESSBAR.getAttribute("style").replaceAll("[^\\d.]", ""));

        int eisen = Integer.parseInt(MainToolbar.EISEN.getText().replace(".", ""));
        own.getSpeicher().setEisen(eisen);
        int eisenBar = Integer.parseInt(MainToolbar.EISEN_PROGRESSBAR.getAttribute("style").replaceAll("[^\\d.]", ""));


        // capacity
//        MainToolbar.HOLZ.mouseOver();
//        MainToolbar.EISEN_TOOLTIP.isPresent(Duration.ofSeconds(2));
//        Main.sleep(1);
//        int capacity = Integer.parseInt(MainToolbar.EISEN_TOOLTIP.getText().replace(".", ""));
//        own.getSpeicher().setCapacity(capacity);


        // Rohstoffe checken
//        MainToolbar.OBERFLAECHE.sendText("v");
//
//        if (!Dorfansicht.HAUPTGEBAEUDE.isPresent(Duration.ofSeconds(5))) {
//            MainToolbar.OBERFLAECHE.sendText("v");
//        }
//
//        Dorfansicht.SPEICHER.click(Duration.ofSeconds(10));
//        Dorfansicht.SPEICHER2.click();
//
//        int max = 0;
//        int currentHolz = 0;
//        int currentLehm = 0;
//        int currentEisen = 0;

//        if (Speicher.SPEICHER_HOLZ.isPresent()) {
//            String[] holz = Speicher.SPEICHER_HOLZ.getText().split(" / ");
//            String[] lehm = Speicher.SPEICHER_LEHM.getText().split(" / ");
//            String[] eisen = Speicher.SPEICHER_EISEN.getText().split(" / ");

//            max = Integer.parseInt(holz[1].replace(".", ""));
//            own.getSpeicher().setCapacity(max);
//            currentHolz = Integer.parseInt(holz[0].replace(".", ""));
//            own.getSpeicher().setHolz(currentHolz);
//
//            currentLehm = Integer.parseInt(lehm[0].replace(".", ""));
//            own.getSpeicher().setLehm(currentLehm);
//
//            currentEisen = Integer.parseInt(eisen[0].replace(".", ""));
//            own.getSpeicher().setEisen(currentEisen);
//        }
//
//        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
//        MainToolbar.OBERFLAECHE.sendText("v");


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


    public EigenesDorf getOwn() {
        return own;
    }

    public void setOwn(EigenesDorf own) {
        this.own = own;
    }


}
