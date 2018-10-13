package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Kaserne extends AbstractMenue {

    public static Element KASERNE = new Element("Kasernen Fenster", "section", "class", "building-barracks twx-window screen left", Element.BY_CSS_SELECTOR);

    public static Element DAUER_TRUPPEN_BAUSCHLEIFE = new Element("Truppen Bauschleife", "div", "class", "queue-info", Element.BY_CSS_SELECTOR);

    public static Element TECHNOLOGIE_FORTSCHRITT = new Element("Truppen Bauschleife", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[5]/h5", Element.BY_XPATH);


    public static Element SPEER = new Element("Speer rekrutieren",
            "label", "for", "input-unit-recruit-spear", "Speerkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element SPEER_INPUT = new Element("Speer rekrutieren",
            "label", "for", "input-unit-recruit-spear", "Speerkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element SCHWERT = new Element("SCHWERT rekrutieren",
            "label", "for", "input-unit-recruit-sword", "Schwertkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element SCHWERT_INPUT = new Element("SCHWERT rekrutieren",
            "label", "for", "input-unit-recruit-sword", "Schwertkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element AXTKAEMPFER = new Element("Axtkämpfer rekrutieren",
            "label", "for", "input-unit-recruit-axe", "Axtkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element AXTKAEMPFER_INPUT = new Element("Axtkämpfer rekrutieren",
            "label", "for", "input-unit-recruit-axe", "Axtkämpfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element BOGEN = new Element("BOGEN rekrutieren",
            "label", "for", "input-unit-recruit-archer", "Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element BOGEN_INPUT = new Element("BOGEN rekrutieren",
            "label", "for", "input-unit-recruit-archer", "Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element LKAV = new Element("LKAV rekrutieren",
            "label", "for", "input-unit-recruit-light_cavalry", "Leichte Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element LKAV_INPUT = new Element("LKAV rekrutieren",
            "label", "for", "input-unit-recruit-light_cavalry", "Leichte Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element BERITTINER_BOGEN = new Element("BERITTENER_BOGEN rekrutieren",
            "label", "for", "input-unit-recruit-mounted_archer", "Berittener Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element BERITTINER_BOGEN_INPUT = new Element("BERITTENER_BOGEN rekrutieren",
            "label", "for", "input-unit-recruit-mounted_archer", "Berittener Bogenschütze", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element SKAV = new Element("Schwere Kavallerie rekrutieren",
            "label", "for", "input-unit-recruit-heavy_cavalry", "Schwere Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element SKAV_INPUT = new Element("Schwere Kavallerie rekrutieren",
            "label", "for", "input-unit-recruit-heavy_cavalry", "Schwere Kavallerie", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element RAMMEN = new Element("RAMMEN rekrutieren",
            "label", "for", "input-unit-recruit-ram", "Rammbock", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element RAMMEN_INPUT = new Element("RAMMEN rekrutieren",
            "label", "for", "input-unit-recruit-ram", "Rammbock", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element KATAPULT = new Element("KATAPULT rekrutieren",
            "label", "for", "input-unit-recruit-catapult", "Katapult", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element KATAPULT_INPUT = new Element("KATAPULT rekrutieren",
            "label", "for", "input-unit-recruit-catapult", "Katapult", Element.BY_CSS_SELECTOR_WITH_CRITERIA);


    static {
        SPEER_INPUT.setElement(new Element("Input Speer", "input", Element.BY_TAG_NAME));
        SCHWERT_INPUT.setElement(new Element("Input Schwert", "input", Element.BY_TAG_NAME));
        AXTKAEMPFER_INPUT.setElement(new Element("Input Axt", "input", Element.BY_TAG_NAME));
        BOGEN_INPUT.setElement(new Element("Input Bogen", "input", Element.BY_TAG_NAME));
        LKAV_INPUT.setElement(new Element("Input LKAV", "input", Element.BY_TAG_NAME));
        BERITTINER_BOGEN_INPUT.setElement(new Element("Input berittener Bogen", "input", Element.BY_TAG_NAME));
        SKAV_INPUT.setElement(new Element("Input SKAV", "input", Element.BY_TAG_NAME));
        RAMMEN_INPUT.setElement(new Element("Input Rammen", "input", Element.BY_TAG_NAME));
        KATAPULT_INPUT.setElement(new Element("Input Katapult", "input", Element.BY_TAG_NAME));


    }

    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
