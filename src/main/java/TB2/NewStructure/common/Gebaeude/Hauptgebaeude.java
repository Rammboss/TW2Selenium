package TB2.NewStructure.common.Gebaeude;

import TB2.NewStructure.common.Menus.Kaserne;
import TB2.NewStructure.common.Menus.MainToolbar;

public class Hauptgebaeude extends Building {

    private final char shortkeyOpen = 'h';

    private Kaserne kasernenMenu;

    public Hauptgebaeude(int name, int holzkosten, int lehmKosten, int eisenKosten, int proviantKosten, int bauzeitSEC) {
        super(name, holzkosten, lehmKosten, eisenKosten, proviantKosten, bauzeitSEC);
    }

    public void open() {
        MainToolbar.OBERFLAECHE.sendText(shortkeyOpen);
    }

    public void analyzeBuildings() {


    }

    public void upgradeBuilding() {

    }


}
