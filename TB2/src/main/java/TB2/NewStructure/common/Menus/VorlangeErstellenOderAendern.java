package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class VorlangeErstellenOderAendern extends AbstractMenue {


    public static Button HOTKEY_1 = new Button("Globale Vorlagenliste bearbeiten",
            "/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div", Button.BY_XPATH);

    public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF = new Button("Globale Vorlagenliste Hotkey Angriff",
            "/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]/div/span", Button.BY_XPATH);

    public static Button ANZAHL_SPEER = new Button("Globale Vorlagenliste Anzahl Speerträger",
            "div", "class", "ff-cell-fix cell-space-44x44", "Speerkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_SCHWERT = new Button("Globale Vorlagenliste Anzahl Schwertkämpfer",
            "div", "class", "ff-cell-fix cell-space-44x44", "Schwertkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_AXT = new Button("Globale Vorlagenliste Anzahl Axtkämpfer ",
            "div", "class", "ff-cell-fix cell-space-44x44", "Axtkämpfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_BOGEN = new Button("Globale Vorlagenliste Anzahl Bogenschütze ",
            "div", "class", "ff-cell-fix cell-space-44x44", "Bogenschütze ", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_LKAV = new Button("Globale Vorlagenliste Anzahl Leichte Kavallerie",
            "div", "class", "ff-cell-fix cell-space-44x44", "Leichte Kavallerie", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_BERITTENER_BOGEN = new Button("Globale Vorlagenliste Anzahl Berittener Bogenschütze",
            "div", "class", "ff-cell-fix cell-space-44x44", "Berittener Bogenschütze", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_SKAV = new Button("Globale Vorlagenliste Anzahl Schwere Kavallerie",
            "div", "class", "ff-cell-fix cell-space-44x44", "Schwere Kavallerie", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_RAMMEN = new Button("Globale Vorlagenliste Anzahl Rammbock",
            "div", "class", "ff-cell-fix cell-space-44x44", "Rammbock", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_BERSERKER = new Button("Globale Vorlagenliste Anzahl Berserker",
            "div", "class", "ff-cell-fix cell-space-44x44", "Berserker", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_TREBUCHET = new Button("Globale Vorlagenliste Anzahl Trebuchet",
            "div", "class", "ff-cell-fix cell-space-44x44", "Trebuchet", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button ANZAHL_ADELSGESCHLECHT = new Button("Globale Vorlagenliste Anzahl Adelsgeschlecht",
            "div", "class", "ff-cell-fix cell-space-44x44", "Adelsgeschlecht", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_PALADIN = new Button("Globale Vorlagenliste Anzahl Paladin",
            "div", "class", "ff-cell-fix cell-space-44x44", "Paladin", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button ANZAHL_KATAPULT = new Button("Globale Vorlagenliste Anzahl Katapult",
            "div", "class", "ff-cell-fix cell-space-44x44", "Katapult", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button SPEICHERN = new Button("Globale Vorlagenliste speichern", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);

    static {
        ANZAHL_SPEER.setButton(new Button("Input Speer", "input", Button.BY_TAG_NAME));
        ANZAHL_SCHWERT.setButton(new Button("Input Schwert", "input", Button.BY_TAG_NAME));
        ANZAHL_AXT.setButton(new Button("Input Axt", "input", Button.BY_TAG_NAME));
        ANZAHL_BOGEN.setButton(new Button("Input Bogen", "input", Button.BY_TAG_NAME));
        ANZAHL_LKAV.setButton(new Button("Input Leichte Kavallerie", "input", Button.BY_TAG_NAME));
        ANZAHL_BERITTENER_BOGEN.setButton(new Button("Input Berittener Bogenschütze", "input", Button.BY_TAG_NAME));
        ANZAHL_SKAV.setButton(new Button("Input Schwere Kavallerie", "input", Button.BY_TAG_NAME));
        ANZAHL_RAMMEN.setButton(new Button("Input Rammbock", "input", Button.BY_TAG_NAME));
        ANZAHL_BERSERKER.setButton(new Button("Input Berserker", "input", Button.BY_TAG_NAME));
        ANZAHL_TREBUCHET.setButton(new Button("Input Trebuchet", "input", Button.BY_TAG_NAME));
        ANZAHL_ADELSGESCHLECHT.setButton(new Button("Input Adelsgeschlecht", "input", Button.BY_TAG_NAME));
        ANZAHL_PALADIN.setButton(new Button("Input Paladin", "input", Button.BY_TAG_NAME));
        ANZAHL_KATAPULT.setButton(new Button("Input Katapult", "input", Button.BY_TAG_NAME));
    }

    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
