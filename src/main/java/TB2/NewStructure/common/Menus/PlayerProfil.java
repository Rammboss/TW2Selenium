package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class PlayerProfil extends AbstractMenue {

    public static Button TABLE_OWN_VILLAGES = new Button("Tablle der eigenen Dörfer", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[4]/tbody[1]", Button.BY_XPATH);
    public static Button PLAYERNAME = new Button("Spielername", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[1]/tbody[1]/tr[1]/td[2]/div/div/span[2]", Button.BY_XPATH);



    @Override
    public void openMenu() {

    }
}
