package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Provinzansicht extends AbstractMenue {
	public static Button PROVINZ_BUTTON_PROVINZDOERVER = new Button("Provinzdörfer", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div/a", Button.BY_XPATH);
	public static Button PROVINZ_NAME = new Button("Provinzname", "/html/body/div[2]/section/div/header/h2", Button.BY_XPATH);
	public static Button TABLE_VILLAGES_IN_PROVINZ = new Button("Tabelle - Frende Dörfer in Provinz", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[2]/tbody", Button.BY_XPATH);
	public static Button TABLE_OWN_VILLAGES_IN_PROVINZ = new Button("Tabelle - Eigene Dörfer in Provinz", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[1]/tbody", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
