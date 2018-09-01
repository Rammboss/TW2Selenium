package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class InfoBuilding extends AbstractMenue {

    public static Element HOLZKOSTEN = new Element("Holzkosten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/table[4]/tbody/tr[2]/td[1]/div/span[2]", Element.BY_XPATH);
    public static Element LEHMKOSTEN = new Element("Lehmkosten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/table[4]/tbody/tr[2]/td[2]/div/span[2]", Element.BY_XPATH);
    public static Element EISENKOSTEN = new Element("Eisenkosten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/table[4]/tbody/tr[2]/td[3]/div/span[2]", Element.BY_XPATH);
    public static Element PROVIANTKOSTEN = new Element("Provinatkosten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/table[4]/tbody/tr[3]/td[1]/div/span[2]", Element.BY_XPATH);


    @Override
    public void openMenu() {

    }
}
