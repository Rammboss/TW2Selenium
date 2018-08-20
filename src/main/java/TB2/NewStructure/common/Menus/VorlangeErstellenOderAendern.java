package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class VorlangeErstellenOderAendern extends AbstractMenue {


    public static Element HOTKEY_1 = new Element("Globale Vorlagenliste bearbeiten",
            "/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div", Element.BY_XPATH);

    public static Element GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF = new Element("Globale Vorlagenliste Hotkey Angriff",
            "/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]/div/span", Element.BY_XPATH);

    public static Element ANZAHL_SPEER = new Element("Globale Vorlagenliste Anzahl Speerträger",
            "div", "class", "ff-cell-fix cell-space-44x44", "Speerkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_SCHWERT = new Element("Globale Vorlagenliste Anzahl Schwertkämpfer",
            "div", "class", "ff-cell-fix cell-space-44x44", "Schwertkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_AXT = new Element("Globale Vorlagenliste Anzahl Axtkämpfer ",
            "div", "class", "ff-cell-fix cell-space-44x44", "Axtkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_BOGEN = new Element("Globale Vorlagenliste Anzahl Bogenschütze ",
            "div", "class", "ff-cell-fix cell-space-44x44", "Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_LKAV = new Element("Globale Vorlagenliste Anzahl Leichte Kavallerie",
            "div", "class", "ff-cell-fix cell-space-44x44", "Leichte Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_BERITTENER_BOGEN = new Element("Globale Vorlagenliste Anzahl Berittener Bogenschütze",
            "div", "class", "ff-cell-fix cell-space-44x44", "Berittener Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_SKAV = new Element("Globale Vorlagenliste Anzahl Schwere Kavallerie",
            "div", "class", "ff-cell-fix cell-space-44x44", "Schwere Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_RAMMEN = new Element("Globale Vorlagenliste Anzahl Rammbock",
            "div", "class", "ff-cell-fix cell-space-44x44", "Rammbock", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_BERSERKER = new Element("Globale Vorlagenliste Anzahl Berserker",
            "div", "class", "ff-cell-fix cell-space-44x44", "Berserker", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_TREBUCHET = new Element("Globale Vorlagenliste Anzahl Trebuchet",
            "div", "class", "ff-cell-fix cell-space-44x44", "Trebuchet", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ANZAHL_ADELSGESCHLECHT = new Element("Globale Vorlagenliste Anzahl Adelsgeschlecht",
            "div", "class", "ff-cell-fix cell-space-44x44", "Adelsgeschlecht", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_PALADIN = new Element("Globale Vorlagenliste Anzahl Paladin",
            "div", "class", "ff-cell-fix cell-space-44x44", "Paladin", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element ANZAHL_KATAPULT = new Element("Globale Vorlagenliste Anzahl Katapult",
            "div", "class", "ff-cell-fix cell-space-44x44", "Katapult", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element SPEICHERN = new Element("Globale Vorlagenliste speichern", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Element.BY_XPATH);

    static {
        ANZAHL_SPEER.setElement(new Element("Input Speer", "input", Element.BY_TAG_NAME));
        ANZAHL_SCHWERT.setElement(new Element("Input Schwert", "input", Element.BY_TAG_NAME));
        ANZAHL_AXT.setElement(new Element("Input Axt", "input", Element.BY_TAG_NAME));
        ANZAHL_BOGEN.setElement(new Element("Input Bogen", "input", Element.BY_TAG_NAME));
        ANZAHL_LKAV.setElement(new Element("Input Leichte Kavallerie", "input", Element.BY_TAG_NAME));
        ANZAHL_BERITTENER_BOGEN.setElement(new Element("Input Berittener Bogenschütze", "input", Element.BY_TAG_NAME));
        ANZAHL_SKAV.setElement(new Element("Input Schwere Kavallerie", "input", Element.BY_TAG_NAME));
        ANZAHL_RAMMEN.setElement(new Element("Input Rammbock", "input", Element.BY_TAG_NAME));
        ANZAHL_BERSERKER.setElement(new Element("Input Berserker", "input", Element.BY_TAG_NAME));
        ANZAHL_TREBUCHET.setElement(new Element("Input Trebuchet", "input", Element.BY_TAG_NAME));
        ANZAHL_ADELSGESCHLECHT.setElement(new Element("Input Adelsgeschlecht", "input", Element.BY_TAG_NAME));
        ANZAHL_PALADIN.setElement(new Element("Input Paladin", "input", Element.BY_TAG_NAME));
        ANZAHL_KATAPULT.setElement(new Element("Input Katapult", "input", Element.BY_TAG_NAME));
    }

    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
