package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class PlayerProfil extends AbstractMenue {

    public static Element TABLE_OWN_VILLAGES = new Element("Tablle der eigenen Dörfer", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[4]/tbody[1]", Element.BY_XPATH);
    public static Element PLAYERNAME = new Element("Spielername", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div/div/span[2]", Element.BY_XPATH);



    @Override
    public void openMenu() {

    }
}
