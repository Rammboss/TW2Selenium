package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Gebaeude.Building;
import TB2.NewStructure.common.Gebaeude.Buildings;
import TB2.NewStructure.common.Gebaeude.Ressourcen;
import TB2.NewStructure.common.Menus.Hauptgebaeude;
import TB2.NewStructure.common.Menus.InfoBuilding;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Element;
import TB2.TB2.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildBuildings implements AuftragInterface {

    private EigenesDorf own;

    private Map<Buildings, Integer> buildingLevels;


    private static Logger logger = LoggerFactory.getLogger(BuildBuildings.class);

    public BuildBuildings(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {

        if (own.getPunkte() >= 9564)
            return;

        new SelectOwnVillage(own);

        MainToolbar.OBERFLAECHE.sendText("h");


        Hauptgebaeude.HAUPTGEBAEUDE_LEVEL.isPresent(Duration.ofSeconds(3));

        Hauptgebaeude.MARKT_INFO.scrollToElement("start");

        if (Main.getDriver().findElements(By.cssSelector("div[ng-repeat='buildJob in buildingQueue']")).size() >= 2) {
            Hauptgebaeude.HAUPTGEBAEUDE_INFO.scrollToElement("end");
            MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
            return;
        }

        Hauptgebaeude.HAUPTGEBAEUDE_INFO.scrollToElement("end");

        setBuildingLevels(getLevelsFromBuildungs());

        new GetRessourcen(own);


        //manuelles Ausbauen
        if (own.getBuildingTask() != null) {
            for (BuildingTask task : own.getBuildingTask()) {
                if (getBuildingLevels().get(task.getBuilding()) < task.getLevel()) {
                    if (MainToolbar.BAUSCHLEIFENSLOT1.getAttribute("tooltip-content").equals("Fenster zum Hauptgebäude öffnen")
                            || MainToolbar.BAUSCHLEIFENSLOT2.getAttribute("tooltip-content").equals("Fenster zum Hauptgebäude öffnen"))

                        build(task.getBuilding(), false);
                }
            }

        } else {// automatisches ausbauen

            List<BuildingTask> buildingOrder = getBuildingOrder();


            build(getNextBuildingTask(buildingOrder), true);
        }

        Hauptgebaeude.HAUPTGEBAEUDE_LEVEL.scrollToElement("end");
        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
    }

    private void build(Buildings nextBuildingTask, boolean autoBuildNext) throws ElementisNotClickable, NoElementTextFound {
        if (nextBuildingTask == null)
            return;
        switch (nextBuildingTask) {

            case HAUPTGEBAEUDE: {
                innerBuild(Hauptgebaeude.HAUPTGEBAEUDE_AUSBAUEN, Hauptgebaeude.HAUPTGEBAEUDE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case HOLZFAELLER: {
                innerBuild(Hauptgebaeude.HOLZFAELLER_AUSBAUEN, Hauptgebaeude.HOLZFAELLER_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case LEHMGRUBE: {
                innerBuild(Hauptgebaeude.LEHMGRUBE_AUSBAUEN, Hauptgebaeude.LEHMGRUBE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case EISENMINE: {
                innerBuild(Hauptgebaeude.EISENMINE_AUSBAUEN, Hauptgebaeude.EISENMINE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case BAUERNHOF: {
                innerBuild(Hauptgebaeude.BAUERNHOF_AUSBAUEN, Hauptgebaeude.BAUERNHOF_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case SPEICHER: {
                innerBuild(Hauptgebaeude.SPEICHER_AUSBAUEN, Hauptgebaeude.SPEICHER_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case KIRCHE: {
                innerBuild(Hauptgebaeude.KIRCHE_AUSBAUEN, Hauptgebaeude.KIRCHE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case SAMMELPLATZ: {
                innerBuild(Hauptgebaeude.SAMMELPLATZ_AUSBAUEN, Hauptgebaeude.SAMMELPLATZ_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case KASERNE: {
                innerBuild(Hauptgebaeude.KASERNE_AUSBAUEN, Hauptgebaeude.KASERNE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case STATUE: {
                innerBuild(Hauptgebaeude.STATUE_AUSBAUEN, Hauptgebaeude.STATUE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case LAZARETT: {
                innerBuild(Hauptgebaeude.LAZARETT_AUSBAUEN, Hauptgebaeude.LAZARETT_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case WALL: {
                innerBuild(Hauptgebaeude.WALL_AUSBAUEN, Hauptgebaeude.WALL_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case MARKT: {
                innerBuild(Hauptgebaeude.MARKT_AUSBAUEN, Hauptgebaeude.MARKT_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case TAVERNE: {
                innerBuild(Hauptgebaeude.TAVERNE_AUSBAUEN, Hauptgebaeude.TAVERNE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case AKADEMIE: {
                innerBuild(Hauptgebaeude.AKADEMIE_AUSBAUEN, Hauptgebaeude.AKADEMIE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            case ORDENSHALLE: {
                innerBuild(Hauptgebaeude.ORDENSHALLE_AUSBAUEN, Hauptgebaeude.ORDENSHALLE_INFO, autoBuildNext, nextBuildingTask);
                break;
            }
            default: {
                logger.info("Gebäude nicht vorhanden!!!");
            }
        }

    }

    private void innerBuild(Element ausbauen, Element info, boolean autoBuildNext, Buildings nextBuildingTask) throws NoElementTextFound, ElementisNotClickable {

        Map<Ressourcen, Integer> res = getRessourcen(info);

        int proviant = Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", ""));
        if (checkRessourcen(res)) {
            ausbauen.click();
        } else if (!autoBuildNext) {
            logger.info("Nicht genug Ressourcen für: " + nextBuildingTask);
            logger.info("Auto Build nicht mehr aktiv!");
        } else if (res.get(Ressourcen.PROVIANT) > proviant && buildingLevels.get(Buildings.BAUERNHOF) < 30 || proviant < 1000 && buildingLevels.get(Buildings.BAUERNHOF) < 30) {
            logger.info("Nicht genug Ressourcen für: " + nextBuildingTask);
            build(Buildings.BAUERNHOF, false);

        } else if (own.getSpeicher().getCapacity() < res.get(Ressourcen.HOLZ) ||
                own.getSpeicher().getCapacity() < res.get(Ressourcen.LEHM) ||
                own.getSpeicher().getCapacity() < res.get(Ressourcen.EISEN) && buildingLevels.get(Buildings.SPEICHER) < 30) {
            logger.info("Nicht genug Ressourcen für: " + nextBuildingTask);
            build(Buildings.SPEICHER, false);

        } else {
            logger.info("Nicht genug Ressourcen für: " + nextBuildingTask);
            getBuildingLevels().put(nextBuildingTask, getBuildingLevels().get(nextBuildingTask) + 100);
            build(getNextBuildingTask(getBuildingOrder()), true);
        }
    }

    private boolean checkRessourcen(Map<Ressourcen, Integer> res) throws NoElementTextFound, ElementisNotClickable {

        return res.get(Ressourcen.HOLZ) <= own.getSpeicher().getHolz() &&
                res.get(Ressourcen.EISEN) <= own.getSpeicher().getEisen() &&
                res.get(Ressourcen.LEHM) <= own.getSpeicher().getLehm() &&
                res.get(Ressourcen.PROVIANT) <= Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", ""));
    }

    private Map<Ressourcen, Integer> getRessourcen(Element info) throws ElementisNotClickable, NoElementTextFound {
        Map<Ressourcen, Integer> tmp = new HashMap<>();
        info.scrollToElement("start");
        info.click();
        InfoBuilding.PROVIANTKOSTEN.isPresent(Duration.ofSeconds(3));

        tmp.put(Ressourcen.HOLZ, Integer.parseInt(InfoBuilding.HOLZKOSTEN.getText().replace(".", "")));
        tmp.put(Ressourcen.LEHM, Integer.parseInt(InfoBuilding.LEHMKOSTEN.getText().replace(".", "")));
        tmp.put(Ressourcen.EISEN, Integer.parseInt(InfoBuilding.EISENKOSTEN.getText().replace(".", "")));
        tmp.put(Ressourcen.PROVIANT, Integer.parseInt(InfoBuilding.PROVIANTKOSTEN.getText().replace(".", "")));

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

        MainToolbar.ZEITLEISTE.click();
        Main.sleep(1);
        MainToolbar.ZEITLEISTE.click();


        return tmp;


    }

    private Buildings getNextBuildingTask(List<BuildingTask> buildingOrder) {

        for (BuildingTask task : buildingOrder) {
            if (getBuildingLevels().get(task.getBuilding()) < task.getLevel()) {
                return task.getBuilding();
            }
        }

        return null;
    }


    private Map<Buildings, Integer> getLevelsFromBuildungs() throws NoElementTextFound, ElementisNotClickable {
        Map<Buildings, Integer> tmp = new HashMap<>();

        Hauptgebaeude.HAUPTGEBAEUDE_LEVEL.scrollToElement("end");
        tmp.put(Buildings.HAUPTGEBAEUDE, getLevel(Hauptgebaeude.HAUPTGEBAEUDE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.HOLZFAELLER, getLevel(Hauptgebaeude.HOLZFAELLER_LEVEL.getText().split("\n")));
        tmp.put(Buildings.LEHMGRUBE, getLevel(Hauptgebaeude.LEHMGRUBE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.EISENMINE, getLevel(Hauptgebaeude.EISENMINE_LEVEL.getText().split("\n")));

        Hauptgebaeude.BAUERNHOF_LEVEL.scrollToElement("end");
        tmp.put(Buildings.BAUERNHOF, getLevel(Hauptgebaeude.BAUERNHOF_LEVEL.getText().split("\n")));
        tmp.put(Buildings.SPEICHER, getLevel(Hauptgebaeude.SPEICHER_LEVEL.getText().split("\n")));

        if (Hauptgebaeude.KIRCHE_LEVEL.isPresent()) {
            tmp.put(Buildings.KIRCHE, getLevel(Hauptgebaeude.KIRCHE_LEVEL.getText().split("\n")));
            tmp.put(Buildings.KAPELLE, 0);
        } else {
            tmp.put(Buildings.KAPELLE, getLevel(Hauptgebaeude.KAPELLE_LEVEL.getText().split("\n")));
            tmp.put(Buildings.KIRCHE, 0);
        }
        tmp.put(Buildings.SAMMELPLATZ, getLevel(Hauptgebaeude.SAMMELPLATZ_LEVEL.getText().split("\n")));

        Hauptgebaeude.KASERNE_LEVEL.scrollToElement("end");
        tmp.put(Buildings.KASERNE, getLevel(Hauptgebaeude.KASERNE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.STATUE, getLevel(Hauptgebaeude.STATUE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.LAZARETT, getLevel(Hauptgebaeude.LAZARETT_LEVEL.getText().split("\n")));
        tmp.put(Buildings.WALL, getLevel(Hauptgebaeude.WALL_LEVEL.getText().split("\n")));

        Hauptgebaeude.MARKT_LEVEL.scrollToElement("end");
        tmp.put(Buildings.MARKT, getLevel(Hauptgebaeude.MARKT_LEVEL.getText().split("\n")));
        tmp.put(Buildings.TAVERNE, getLevel(Hauptgebaeude.TAVERNE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.AKADEMIE, getLevel(Hauptgebaeude.AKADEMIE_LEVEL.getText().split("\n")));
        tmp.put(Buildings.ORDENSHALLE, getLevel(Hauptgebaeude.ORDENSHALLE_LEVEL.getText().split("\n")));

        Hauptgebaeude.HAUPTGEBAEUDE_INFO.scrollToElement("end");

        return tmp;
    }

    private int getLevel(String[] split) {

        if (split.length == 1) {
            return Integer.parseInt(split[0]);
        } else {
            return Integer.parseInt(split[0]) + Integer.parseInt(split[1].replace("+", ""));
        }


    }

    private List<BuildingTask> getBuildingOrder() {

        List<BuildingTask> tmp = new ArrayList<>();
        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 1));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 1));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 1));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 2));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 2));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 2));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 3));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 3));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 3));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 4));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 4));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 4));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 5));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 5));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 5));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 1));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 2));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 3));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 4));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 5));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 1));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 2));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 3));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 4));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 5));

        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 1));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 2));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 3));

        tmp.add(new BuildingTask(Buildings.SAMMELPLATZ, 1));

        tmp.add(new BuildingTask(Buildings.KASERNE, 1));
        tmp.add(new BuildingTask(Buildings.KASERNE, 2));
        tmp.add(new BuildingTask(Buildings.KASERNE, 3));
        tmp.add(new BuildingTask(Buildings.KASERNE, 4));
        tmp.add(new BuildingTask(Buildings.KASERNE, 5));

        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 4));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 5));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 6));

        tmp.add(new BuildingTask(Buildings.STATUE, 1));

        tmp.add(new BuildingTask(Buildings.LAZARETT, 1));

        tmp.add(new BuildingTask(Buildings.WALL, 1));
        tmp.add(new BuildingTask(Buildings.WALL, 2));
        tmp.add(new BuildingTask(Buildings.WALL, 3));
        tmp.add(new BuildingTask(Buildings.WALL, 4));
        tmp.add(new BuildingTask(Buildings.WALL, 5));

        tmp.add(new BuildingTask(Buildings.MARKT, 1));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 6));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 6));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 6));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 7));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 7));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 7));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 8));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 8));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 8));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 6));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 7));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 8));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 6));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 7));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 8));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 9));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 10));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 9));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 10));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 9));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 9));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 9));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 10));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 10));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 10));

        tmp.add(new BuildingTask(Buildings.WALL, 6));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 11));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 12));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 13));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 14));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 15));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 11));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 12));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 13));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 14));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 15));

        tmp.add(new BuildingTask(Buildings.SAMMELPLATZ, 2));
        tmp.add(new BuildingTask(Buildings.SAMMELPLATZ, 3));
        tmp.add(new BuildingTask(Buildings.SAMMELPLATZ, 4));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 11));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 11));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 11));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 12));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 12));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 12));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 13));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 13));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 13));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 14));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 14));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 14));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 15));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 15));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 15));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 16));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 16));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 16));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 17));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 17));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 17));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 16));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 17));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 18));

        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 7));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 8));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 9));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 10));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 11));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 12));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 16));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 17));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 18));

        tmp.add(new BuildingTask(Buildings.STATUE, 2));
        tmp.add(new BuildingTask(Buildings.STATUE, 3));

        tmp.add(new BuildingTask(Buildings.MARKT, 2));
        tmp.add(new BuildingTask(Buildings.MARKT, 3));
        tmp.add(new BuildingTask(Buildings.MARKT, 4));
        tmp.add(new BuildingTask(Buildings.MARKT, 5));
        tmp.add(new BuildingTask(Buildings.MARKT, 6));
        tmp.add(new BuildingTask(Buildings.MARKT, 7));

        tmp.add(new BuildingTask(Buildings.LAZARETT, 2));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 3));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 4));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 5));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 6));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 1));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 2));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 3));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 4));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 5));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 6));
        tmp.add(new BuildingTask(Buildings.TAVERNE, 7));

        tmp.add(new BuildingTask(Buildings.KASERNE, 6));
        tmp.add(new BuildingTask(Buildings.KASERNE, 7));
        tmp.add(new BuildingTask(Buildings.KASERNE, 8));
        tmp.add(new BuildingTask(Buildings.KASERNE, 9));
        tmp.add(new BuildingTask(Buildings.KASERNE, 10));
        tmp.add(new BuildingTask(Buildings.KASERNE, 11));
        tmp.add(new BuildingTask(Buildings.KASERNE, 12));
        tmp.add(new BuildingTask(Buildings.KASERNE, 13));
        tmp.add(new BuildingTask(Buildings.KASERNE, 14));
        tmp.add(new BuildingTask(Buildings.KASERNE, 15));

        tmp.add(new BuildingTask(Buildings.SAMMELPLATZ, 5));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 19));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 20));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 21));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 22));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 23));

        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 13));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 14));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 15));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 16));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 17));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 18));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 19));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 20));

        tmp.add(new BuildingTask(Buildings.AKADEMIE, 1));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 19));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 20));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 21));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 22));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 23));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 24));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 25));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 26));

        tmp.add(new BuildingTask(Buildings.KASERNE, 16));
        tmp.add(new BuildingTask(Buildings.KASERNE, 17));
        tmp.add(new BuildingTask(Buildings.KASERNE, 18));
        tmp.add(new BuildingTask(Buildings.KASERNE, 19));
        tmp.add(new BuildingTask(Buildings.KASERNE, 20));
        tmp.add(new BuildingTask(Buildings.KASERNE, 21));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 24));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 27));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 25));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 28));

        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 29));
        tmp.add(new BuildingTask(Buildings.BAUERNHOF, 30));

        tmp.add(new BuildingTask(Buildings.WALL, 7));
        tmp.add(new BuildingTask(Buildings.WALL, 8));
        tmp.add(new BuildingTask(Buildings.WALL, 9));
        tmp.add(new BuildingTask(Buildings.WALL, 10));

        tmp.add(new BuildingTask(Buildings.KASERNE, 22));
        tmp.add(new BuildingTask(Buildings.KASERNE, 23));

        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 21));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 22));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 23));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 24));
        tmp.add(new BuildingTask(Buildings.HAUPTGEBAEUDE, 25));

        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 1));

        tmp.add(new BuildingTask(Buildings.KASERNE, 24));
        tmp.add(new BuildingTask(Buildings.KASERNE, 25));

        tmp.add(new BuildingTask(Buildings.WALL, 11));
        tmp.add(new BuildingTask(Buildings.WALL, 12));
        tmp.add(new BuildingTask(Buildings.WALL, 13));

        tmp.add(new BuildingTask(Buildings.SPEICHER, 26));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 27));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 28));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 29));
        tmp.add(new BuildingTask(Buildings.SPEICHER, 30));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 8));
        tmp.add(new BuildingTask(Buildings.WALL, 14));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 9));
        tmp.add(new BuildingTask(Buildings.WALL, 15));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 10));
        tmp.add(new BuildingTask(Buildings.WALL, 16));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 11));
        tmp.add(new BuildingTask(Buildings.WALL, 17));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 12));
        tmp.add(new BuildingTask(Buildings.WALL, 17));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 13));
        tmp.add(new BuildingTask(Buildings.WALL, 18));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 14));
        tmp.add(new BuildingTask(Buildings.WALL, 19));

        tmp.add(new BuildingTask(Buildings.WALL, 20));

        tmp.add(new BuildingTask(Buildings.STATUE, 4));
        tmp.add(new BuildingTask(Buildings.STATUE, 5));

        tmp.add(new BuildingTask(Buildings.MARKT, 8));
        tmp.add(new BuildingTask(Buildings.MARKT, 9));
        tmp.add(new BuildingTask(Buildings.MARKT, 10));
        tmp.add(new BuildingTask(Buildings.MARKT, 11));
        tmp.add(new BuildingTask(Buildings.MARKT, 12));
        tmp.add(new BuildingTask(Buildings.MARKT, 13));
        tmp.add(new BuildingTask(Buildings.MARKT, 14));
        tmp.add(new BuildingTask(Buildings.MARKT, 15));

        tmp.add(new BuildingTask(Buildings.LAZARETT, 7));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 8));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 18));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 18));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 19));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 19));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 20));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 20));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 21));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 21));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 22));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 22));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 23));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 23));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 18));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 24));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 24));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 19));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 25));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 25));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 20));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 26));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 26));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 21));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 27));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 27));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 22));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 28));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 28));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 23));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 29));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 29));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 24));

        tmp.add(new BuildingTask(Buildings.HOLZFAELLER, 30));
        tmp.add(new BuildingTask(Buildings.EISENMINE, 30));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 25));

        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 26));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 27));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 28));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 29));
        tmp.add(new BuildingTask(Buildings.LEHMGRUBE, 30));

        tmp.add(new BuildingTask(Buildings.LAZARETT, 9));
        tmp.add(new BuildingTask(Buildings.LAZARETT, 10));

        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 2));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 3));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 4));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 5));

        tmp.add(new BuildingTask(Buildings.MARKT, 16));
        tmp.add(new BuildingTask(Buildings.MARKT, 17));
        tmp.add(new BuildingTask(Buildings.MARKT, 18));
        tmp.add(new BuildingTask(Buildings.MARKT, 19));
        tmp.add(new BuildingTask(Buildings.MARKT, 20));

        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 6));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 7));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 8));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 9));
        tmp.add(new BuildingTask(Buildings.ORDENSHALLE, 10));

        tmp.add(new BuildingTask(Buildings.TAVERNE, 15));


        return tmp;
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

    public Map<Buildings, Integer> getBuildingLevels() {
        return buildingLevels;
    }

    public void setBuildingLevels(Map<Buildings, Integer> buildingLevels) {
        this.buildingLevels = buildingLevels;
    }
}
