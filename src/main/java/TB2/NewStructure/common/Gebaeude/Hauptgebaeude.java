package TB2.NewStructure.common.Gebaeude;

import TB2.NewStructure.common.Menus.Kaserne;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.TB2.Button;

public class Hauptgebaeude extends Building {

    private final char shortkeyOpen = 'd';

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
