package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Provinzansicht extends AbstractMenue {
	public static Element PROVINZ_BUTTON_PROVINZDOERVER = new Element("Provinzdörfer", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div/a", Element.BY_XPATH);
	public static Element PROVINZ_NAME = new Element("Provinzname", "/html/body/div[2]/section/div/header/h2", Element.BY_XPATH);
	public static Element TABLE_VILLAGES_IN_PROVINZ = new Element("Tabelle - Frende Dörfer in Provinz", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[2]/tbody", Element.BY_XPATH);
	public static Element TABLE_OWN_VILLAGES_IN_PROVINZ = new Element("Tabelle - Eigene Dörfer in Provinz", "/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[1]/tbody", Element.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
