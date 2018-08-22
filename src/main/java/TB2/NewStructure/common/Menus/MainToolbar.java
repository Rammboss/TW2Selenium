package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class MainToolbar extends AbstractMenue {

    public static Element VERBINDUNGSFEHLER = new Element("Verbindungsfehler", "div", "class", "middle-wrapper", Element.BY_CSS_SELECTOR);


    public static Element LOADING_SCREEN = new Element("Loading Screen", "//*[@id=\"screen-loading\"]", Element.BY_XPATH);

    public static Element PROFIL = new Element("Profil", "a", "tooltip-content", "Profil", Element.BY_CSS_SELECTOR);

    public static Element CURRENT_VILLAGE_NAME = new Element("Aktuelles Dorf Name", "village-name", Element.BY_ID);
    public static Element CURRENT_VILLAGE_KOORDS = new Element("Aktuelles Dorf Koordinaten", "/html/body/div[2]/div[11]/div[1]/div[2]/div[1]/div[2]/h4/span", Element.BY_XPATH);

    public static Element HOLZ = new Element("Holz", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]", Element.BY_XPATH);
    public static Element LEHM = new Element("Lehm", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]", Element.BY_XPATH);
    public static Element EISEN = new Element("Eisen", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]/div[1]", Element.BY_XPATH);
    public static Element PROVIANT = new Element("Proviant", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[4]/div[2]/div[1]", Element.BY_XPATH);

    public static Element KASERNENSLOT1 = new Element("Kaserne Slot 1", "/html/body/div[2]/div[13]/div[3]/div/div[1]/ul/li[1]", Element.BY_XPATH);

    public static Element KASERNENSLOT2 = new Element("Kaserne Slot 2", "/html/body/div[2]/div[13]/div[3]/div/div[1]/ul/li[2]/div", Element.BY_XPATH);

    public static Element BAUSCHLEIFE = new Element("Bauschleife", "/html/body/div[2]/div[13]/div[1]/div[1]/div", Element.BY_XPATH);
    public static Element REKRUTIERUNGSSCHEIFE = new Element("Rekrutierungsschleife", "/html/body/div[2]/div[13]/div[1]/div[2]/div", Element.BY_XPATH);
    public static Element OBERFLAECHE = new Element("Spielfeld", "//*[@id=\"click-layer\"]", Element.BY_XPATH);
    public static Element ZEITLEISTE = new Element("Zeitleiste", "/html/body/div[2]/footer/div/div[2]/div[3]/a", Element.BY_XPATH);
    public static Element LOGOUT = new Element("Ausloggen", "//*[@id=\"button-logout\"]", Element.BY_XPATH);
    public static Element COMPLETE_BUILDING = new Element("Gebäude fertig stellen ", "/html/body/div[2]/div[13]/div[2]/div/ul/li[1]/div[1]/div/div[1]/a", Element.BY_XPATH);

    public static Element BELOHNUNG_ANNEHMEN = new Element("Belohnung annehmen ", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Element.BY_XPATH);


    public static Element ANZAHL_SPEER = new Element("Anzahl Speerkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Speerkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_SCHWERT = new Element("Anzahl Schwertkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Schwertkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_AXT = new Element("Anzahl Axtkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Axtkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_BOGEN = new Element("Anzahl Bogenschützen", "li", "ng-repeat", "unitName in ::unitOrder", "Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_LKAV = new Element("Anzahl Leichte Kavallerie", "li", "ng-repeat", "unitName in ::unitOrder", "Leichte Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_BERITTENER_BOGEN = new Element("Anzahl Berittener Bogenschütze", "li", "ng-repeat", "unitName in ::unitOrder", "Berittener Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_SKAV = new Element("Anzahl Schwere Kavallerie", "li", "ng-repeat", "unitName in ::unitOrder", "Schwere Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_RAMMEN = new Element("Anzahl Rammbock", "li", "ng-repeat", "unitName in ::unitOrder", "Rammbock", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_KATAPULT = new Element("Anzahl Katapult", "li", "ng-repeat", "unitName in ::unitOrder", "Katapult", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_BERSERKER = new Element("Anzahl Berserker", "li", "ng-repeat", "unitName in ::unitOrder", "Berserker", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_TREBUCHET = new Element("Anzahl Trebuchet", "li", "ng-repeat", "unitName in ::unitOrder", "Trebuchet", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_ADELSGESCHLECHT = new Element("Anzahl Adelsgeschlecht", "li", "ng-repeat", "unitName in ::unitOrder", "Adelsgeschlecht", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_PALADIN = new Element("Anzahl Paladin", "li", "ng-repeat", "unitName in ::unitOrder", "Paladin", Element.BY_CSS_SELECTOR_WITH_CRITERIA);


    public static Element EINSTELLUNGEN = new Element("Einstellungen öffnen", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[10]/a", Element.BY_XPATH);
    public static Element UEBERSICHTEN = new Element("Übersichten öffnen", "a", "class", "icon-60x60-overview", Element.BY_CSS_SELECTOR);


    public static Element ERROR_50_ANGRIFFE = new Element("50 Angriffe", "div", "class", "icons  icon-44x44-error", Element.BY_CSS_SELECTOR);
    public static Element AUF_WELTKARTE_SUCHEN = new Element("Weltkarte", "//*[@id=\"world-map\"]", Element.BY_XPATH);

    public static Element EINHEITEN_UEBERSICHT = new Element("Einheiten Übersicht aktuelles Dorf", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[5]/a", Element.BY_XPATH);


    static {
        ANZAHL_SPEER.setElement(new Element("Anzahl Speerkämpfer", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_SCHWERT.setElement(new Element("Anzahl Schwertkämpfer", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_AXT.setElement(new Element("Anzahl Axtkämpfer", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_BOGEN.setElement(new Element("Anzahl Bogenschützen", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_LKAV.setElement(new Element("Anzahl Leichte Kavallerie", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_BERITTENER_BOGEN.setElement(new Element("Anzahl Berittener Bogenschütze", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_SKAV.setElement(new Element("Anzahl Schwere Kavallerie", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_RAMMEN.setElement(new Element("Anzahl Rammbock", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_KATAPULT.setElement(new Element("Anzahl Katapult", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_BERSERKER.setElement(new Element("Anzahl Berserker", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_TREBUCHET.setElement(new Element("Anzahl Trebuchet", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_ADELSGESCHLECHT.setElement(new Element("Anzahl Adelsgeschlecht", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
        ANZAHL_PALADIN.setElement(new Element("Anzahl Paladin", "div", "class", "clearfix amount", Element.BY_CSS_SELECTOR));
    }

    @Override
    public void openMenu() {

    }

}
