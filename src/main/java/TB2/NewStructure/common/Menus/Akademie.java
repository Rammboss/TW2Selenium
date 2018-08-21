package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Akademie extends AbstractMenue {


    public static Element BUILDING_NOT_ACTIVATERD = new Element("Akademie noch nicht gebaut", "table", "class", "tbl-border-light", "Gebäude nicht freigeschaltet.", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element WERT_ENGEBEN = new Element("Münze Anzahl", "div", "class", "size-34x34 btn-orange", "Wert eingeben", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element WERT_ENGEBEN_INPUT = new Element("Münze Anzahl", "input", "id", "input-coins", Element.BY_CSS_SELECTOR);

    public static Element MUENZE_PRAEGEN = new Element("Münze prägen", "a", "tooltip-content", "Nicht genügend Rohstoffe", "Münzen prägen", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    @Override
    public void openMenu() {

    }
}
