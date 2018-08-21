package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfansicht;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Speicher;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalTime;


public class GetRessourcen implements AuftragInterface {

    private int eisen;

    private int holz;

    private int lehm;

    private int maxSpeicher;

    private EigenesDorf own;

    public GetRessourcen(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {
        // Rohstoffe checken
        MainToolbar.OBERFLAECHE.sendText("v");

        if (!Dorfansicht.HAUPTGEBAEUDE.isPresent(Duration.ofSeconds(5))) {
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
            setMaxSpeicher(max);
            currentHolz = Integer.parseInt(holz[0].replace(".", ""));
            setHolz(currentHolz);

            currentLehm = Integer.parseInt(lehm[0].replace(".", ""));
            setLehm(currentLehm);

            currentEisen = Integer.parseInt(eisen[0].replace(".", ""));
            setEisen(currentEisen);
        }

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
        MainToolbar.OBERFLAECHE.sendText("v");


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

    public int getEisen() {
        return eisen;
    }

    public void setEisen(int eisen) {
        this.eisen = eisen;
    }

    public int getHolz() {
        return holz;
    }

    public void setHolz(int holz) {
        this.holz = holz;
    }

    public int getLehm() {
        return lehm;
    }

    public void setLehm(int lehm) {
        this.lehm = lehm;
    }

    public EigenesDorf getOwn() {
        return own;
    }

    public void setOwn(EigenesDorf own) {
        this.own = own;
    }

    public int getMaxSpeicher() {
        return maxSpeicher;
    }

    public void setMaxSpeicher(int maxSpeicher) {
        this.maxSpeicher = maxSpeicher;
    }
}
