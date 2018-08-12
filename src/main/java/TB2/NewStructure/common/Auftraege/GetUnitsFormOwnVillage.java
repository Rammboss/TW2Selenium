package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.units.Units;

import java.time.LocalTime;
import java.util.HashMap;

public class GetUnitsFormOwnVillage implements AuftragInterface {

    private EigenesDorf own;
    private HashMap<Units, Integer> units;

    public GetUnitsFormOwnVillage(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        this.units = new HashMap<>();

        run();
    }

    @Override
    public void run() throws NoElementTextFound, ElementisNotClickable {

        new SelectOwnVillage(own);

        HashMap<Units, Integer> tmp = new HashMap<>();

        tmp.put(Units.SPEER, Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", "")));
        tmp.put(Units.SCHWERT, Integer.parseInt(MainToolbar.ANZAHL_SCHWERT.getText().replace(".", "")));
        tmp.put(Units.AXT, Integer.parseInt(MainToolbar.ANZAHL_AXT.getText().replace(".", "")));
        tmp.put(Units.BOGEN, Integer.parseInt(MainToolbar.ANZAHL_BOGEN.getText().replace(".", "")));
        tmp.put(Units.LKAV, Integer.parseInt(MainToolbar.ANZAHL_LKAV.getText().replace(".", "")));
        tmp.put(Units.BERITTINER_BOGEN, Integer.parseInt(MainToolbar.ANZAHL_BERITTENER_BOGEN.getText().replace(".", "")));
        tmp.put(Units.SKAV, Integer.parseInt(MainToolbar.ANZAHL_SKAV.getText().replace(".", "")));
        tmp.put(Units.RAMMEN, Integer.parseInt(MainToolbar.ANZAHL_RAMMEN.getText().replace(".", "")));
        tmp.put(Units.KATAPULT, Integer.parseInt(MainToolbar.ANZAHL_KATAPULT.getText().replace(".", "")));
        tmp.put(Units.BERSERKER, Integer.parseInt(MainToolbar.ANZAHL_BERSERKER.getText().replace(".", "")));
        tmp.put(Units.TREBUCHET, Integer.parseInt(MainToolbar.ANZAHL_TREBUCHET.getText().replace(".", "")));
        tmp.put(Units.ADELSGESCHLECHT, Integer.parseInt(MainToolbar.ANZAHL_ADELSGESCHLECHT.getText().replace(".", "")));
        tmp.put(Units.PALADIN, Integer.parseInt(MainToolbar.ANZAHL_PALADIN.getText().replace(".", "")));

        setUnits(tmp);
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

    public HashMap<Units, Integer> getUnits() {
        return units;
    }

    public void setUnits(HashMap<Units, Integer> units) {
        this.units = units;
    }
}
