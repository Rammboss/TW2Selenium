package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Gebaeude.Buildings;

public class BuildingTask {

    private Buildings building;

    private int level;

    public BuildingTask(Buildings building, int level) {
        this.building = building;
        this.level = level;
    }

    public Buildings getBuilding() {
        return building;
    }

    public void setBuilding(Buildings building) {
        this.building = building;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
