package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class MainToolbar extends AbstractMenue {

    public static Button LOADING_SCREEN = new Button("Loading Screen", "//*[@id=\"screen-loading\"]", Button.BY_XPATH);

    public static Button PROFIL = new Button("Profil", "a", "tooltip-content", "Profil", Button.BY_CSS_SELECTOR);

    public static Button CURRENT_VILLAGE_NAME = new Button("Aktuelles Dorf Name", "village-name", Button.BY_ID);
    public static Button CURRENT_VILLAGE_KOORDS = new Button("Aktuelles Dorf Koordinaten", "/html/body/div[2]/div[11]/div[1]/div[2]/div[1]/div[2]/h4/span", Button.BY_XPATH);

    public static Button HOLZ = new Button("Holz", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]", Button.BY_XPATH);
    public static Button LEHM = new Button("Lehm", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]", Button.BY_XPATH);
    public static Button EISEN = new Button("Eisen", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]/div[1]", Button.BY_XPATH);
    public static Button PROVIANT = new Button("Proviant", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[4]/div[2]/div[1]", Button.BY_XPATH);

    public static Button KASERNENSLOT1 = new Button("Kaserne Slot 1", "li", "class", "ng-scope first", Button.BY_CSS_SELECTOR);

    public static Button KASERNENSLOT2 = new Button("Kaserne Slot 2", "/html/body/div[2]/div[13]/div[3]/div/div[1]/ul/li[2]/div", Button.BY_XPATH);
    public static Button BAUSCHLEIFE = new Button("Bauschleife", "/html/body/div[2]/div[13]/div[1]/div[1]/div", Button.BY_XPATH);
    public static Button REKRUTIERUNGSSCHEIFE = new Button("Rekrutierungsschleife", "/html/body/div[2]/div[13]/div[1]/div[2]/div", Button.BY_XPATH);
    public static Button OBERFLAECHE = new Button("Spielfeld", "//*[@id=\"click-layer\"]", Button.BY_XPATH);
    public static Button ZEITLEISTE = new Button("Zeitleiste", "/html/body/div[2]/footer/div/div[2]/div[3]/a", Button.BY_XPATH);
    public static Button LOGOUT = new Button("Ausloggen", "//*[@id=\"button-logout\"]", Button.BY_XPATH);
    public static Button COMPLETE_BUILDING = new Button("Gebäude fertig stellen ", "/html/body/div[2]/div[13]/div[2]/div/ul/li[1]/div[1]/div/div[1]/a", Button.BY_XPATH);

    public static Button BELOHNUNG_ANNEHMEN = new Button("Belohnung annehmen ", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);


    public static Button ANZAHL_SPEER = new Button("Anzahl Speerkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Speerkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_SCHWERT = new Button("Anzahl Schwertkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Schwertkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_AXT = new Button("Anzahl Axtkämpfer", "li", "ng-repeat", "unitName in ::unitOrder", "Axtkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_BOGEN = new Button("Anzahl Bogenschützen", "li", "ng-repeat", "unitName in ::unitOrder", "Bogenschütze", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_LKAV = new Button("Anzahl Leichte Kavallerie", "li", "ng-repeat", "unitName in ::unitOrder", "Leichte Kavallerie", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_BERITTENER_BOGEN = new Button("Anzahl Berittener Bogenschütze", "li", "ng-repeat", "unitName in ::unitOrder", "Berittener Bogenschütze", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_SKAV = new Button("Anzahl Schwere Kavallerie", "li", "ng-repeat", "unitName in ::unitOrder", "Schwere Kavallerie", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_RAMMEN = new Button("Anzahl Rammbock", "li", "ng-repeat", "unitName in ::unitOrder", "Rammbock", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_KATAPULT = new Button("Anzahl Katapult", "li", "ng-repeat", "unitName in ::unitOrder", "Katapult", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_BERSERKER = new Button("Anzahl Berserker", "li", "ng-repeat", "unitName in ::unitOrder", "Berserker", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_TREBUCHET = new Button("Anzahl Trebuchet", "li", "ng-repeat", "unitName in ::unitOrder", "Trebuchet", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_ADELSGESCHLECHT = new Button("Anzahl Adelsgeschlecht", "li", "ng-repeat", "unitName in ::unitOrder", "Adelsgeschlecht", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_PALADIN = new Button("Anzahl Paladin", "li", "ng-repeat", "unitName in ::unitOrder", "Paladin", Button.BY_CSS_SELECTOR_WITH_CRITERIA);


    public static Button EINSTELLUNGEN = new Button("Einstellungen öffnen", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[10]/a", Button.BY_XPATH);
    public static Button UEBERSICHTEN = new Button("Übersichten öffnen", "a", "class", "icon-60x60-overview", Button.BY_CSS_SELECTOR);


    public static Button ERROR_50_ANGRIFFE = new Button("50 Angriffe", "div", "class", "icons  icon-44x44-error", Button.BY_CSS_SELECTOR);
    public static Button AUF_WELTKARTE_SUCHEN = new Button("Weltkarte", "//*[@id=\"world-map\"]", Button.BY_XPATH);

    public static Button EINHEITEN_UEBERSICHT = new Button("Einheiten Übersicht aktuelles Dorf", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[5]/a", Button.BY_XPATH);


    static {
        ANZAHL_SPEER.setButton(new Button("Anzahl Speerkämpfer", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_SCHWERT.setButton(new Button("Anzahl Schwertkämpfer", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_AXT.setButton(new Button("Anzahl Axtkämpfer", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_BOGEN.setButton(new Button("Anzahl Bogenschützen", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_LKAV.setButton(new Button("Anzahl Leichte Kavallerie", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_BERITTENER_BOGEN.setButton(new Button("Anzahl Berittener Bogenschütze", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_SKAV.setButton(new Button("Anzahl Schwere Kavallerie", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_RAMMEN.setButton(new Button("Anzahl Rammbock", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_KATAPULT.setButton(new Button("Anzahl Katapult", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_BERSERKER.setButton(new Button("Anzahl Berserker", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_TREBUCHET.setButton(new Button("Anzahl Trebuchet", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_ADELSGESCHLECHT.setButton(new Button("Anzahl Adelsgeschlecht", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
        ANZAHL_PALADIN.setButton(new Button("Anzahl Paladin", "div", "class", "clearfix amount", Button.BY_CSS_SELECTOR));
    }

    @Override
    public void openMenu() {

    }

}
