package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class UebersichtenDoerfer extends AbstractMenue {

    public static Element TABLE_OWN_WILLAGES = new Element("Tablelle Dörfer", "//table[@class='tbl-border-light tbl-striped tbl-content tbl-hover']", Element.BY_XPATH);

    public static Element BUILD_STATUS = new Element("Button zum Bauen in der Übersicht", "div", "ng-click", "toggleValue()", Element.BY_CSS_SELECTOR);

    @Override
    public void openMenu() {

    }
}
