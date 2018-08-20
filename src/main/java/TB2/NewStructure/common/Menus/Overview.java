package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Overview extends AbstractMenue {
    public static Element BEFEHLE = new Element("Befehle", "div", "class", "tab", "Befehle", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element DOERFER = new Element("Befehle", "div", "class", "tab", "Dörfer", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Element NUR_AKTUELLES_DORF = new Element("Nur aktuelles Dorf", "div", "tooltip-content", "Nur aktuelles Dorf", Element.BY_CSS_SELECTOR);
    public static Element UNTERSTUETZUNG = new Element("Unterstützung ein-/ausblenden", "div", "tooltip-content", "Unterstützung", Element.BY_CSS_SELECTOR);
    public static Element UMSIEDELUNG = new Element("Umsiedelung ein-/ausblenden", "div", "tooltip-content", "Umsiedlung", Element.BY_CSS_SELECTOR);

    public static Element TABLE = new Element("Seite 1 Table", "/html/body/div[2]/section/div/div[3]/div/div/div/div[2]/div[1]/div/div/div[1]/table/tbody/tr", Element.BY_XPATH);

    public static Element SEITE2 = new Element("Seite 1 Table", "div", "class", "btn-page btn-orange", "2", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

    @Override
    public void openMenu() {


    }

    static {
        //BEFEHLE.setElement(new Element("Befehle inner logic", "div", "ng-class", "{'box-border-light': (activeTab === tabName) || (!activeTab && (requestedTab === tabName))}", Element.BY_CSS_SELECTOR));
    }
}
