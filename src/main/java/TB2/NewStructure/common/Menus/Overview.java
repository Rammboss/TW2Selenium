package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Overview extends AbstractMenue {
    public static Button BEFEHLE = new Button("Befehle", "div", "class", "tab", "Befehle", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Button DOERFER = new Button("Befehle", "div", "class", "tab", "Dörfer", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    public static Button NUR_AKTUELLES_DORF = new Button("Nur aktuelles Dorf", "div", "tooltip-content", "Nur aktuelles Dorf", Button.BY_CSS_SELECTOR);
    public static Button TABLE = new Button("Seite 1 Table", "/html/body/div[2]/section/div/div[3]/div/div/div/div[2]/div[1]/div/div/div[1]/table/tbody/tr", Button.BY_XPATH);

    public static Button SEITE2 = new Button("Seite 1 Table", "div", "class", "btn-page btn-orange", "2", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

    @Override
    public void openMenu() {


    }

    static {
        //BEFEHLE.setButton(new Button("Befehle inner logic", "div", "ng-class", "{'box-border-light': (activeTab === tabName) || (!activeTab && (requestedTab === tabName))}", Button.BY_CSS_SELECTOR));
    }
}
