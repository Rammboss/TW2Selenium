package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Koordinaten extends AbstractMenue {
    public static Element X_KOORDINATE = new Element("X", "/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[1]/input", Element.BY_XPATH);
    public static Element Y_KOORDINATE = new Element("Y", "/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[2]/input", Element.BY_XPATH);
    public static Element JUMP_TO_VILLAGE = new Element("Dorf auswählen", "div", "class", "size-34x34 btn-orange icon-34x34-jump-to float-right", Element.BY_CSS_SELECTOR);


    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
