package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Rohstofflager extends AbstractMenue {
    public static Element ROHSTOFFLAGER_EINSAMMELN = new Element("Rohstofflager einsammeln", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div/a",
            Element.BY_XPATH);
    public static Element ROHSTOFFLAGER_STARTEN = new Element("Rohstofflager starten", "div", "class", "available-jobs", Element.BY_CSS_SELECTOR);

    public static Element JOBS = new Element("Rohstofflager starten", "div", "class", "available-jobs", Element.BY_CSS_SELECTOR);
    public static Element ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN = new Element("Rohstofflager trotzdem abschliessen", "/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Element.BY_XPATH);

    public static Element ITEMS_VERWENDEN = new Element("Items verwenden", "/html/body/div[2]/section/div/div/div[1]/div/div[2]/a", Element.BY_XPATH);
    public static Element ROHSTOFFLAGER_STARTEN_ZEIT = new Element("Rohstofflager Zeit des neuen Auftrages", "div", "ng-bind", "(job.duration | readableSecondsFilter)", Element.BY_CSS_SELECTOR);

    public static Element AKTUELLE_PRODUKTION = new Element("Aktuelle Produktion", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]", Element.BY_XPATH);
    public static Element AKTUELLE_PRODUKTION_ZEIT = new Element("Rohstofflager Zeit des aktuellen Auftrages",
            "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]", Element.BY_XPATH);

    public static Element LETZTES_ITEM = new Element("Letztes Item", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[1]/div[3]/div[6]/div[2]", Element.BY_XPATH);

    public static Element BENUTZEN = new Element("Benutzen", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Element.BY_XPATH);

    public static Element AKTUELLE_ZEIT = new Element("Aktuelle Zeit", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/span", Element.BY_XPATH);
    public static Element GEGENSTAND_BENUTZEN = new Element("Gegnstand benutzen", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Element.BY_XPATH);

    public static Element ANZAHL_NEUER_AUFGABEN = new Element("Anzahl der Items", "a", "class", "btn-green fade-in-scale reroll", Element.BY_CSS_SELECTOR);


    static {
        ANZAHL_NEUER_AUFGABEN.setElement(new Element("Anzahl der Items 2", "span", "class", "text", Element.BY_CSS_SELECTOR));
        ROHSTOFFLAGER_STARTEN.setElement(new Element("First Job", "table", "class", "job-cell tbl-border-dark job-active", Element.BY_CSS_SELECTOR));
    }

    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
