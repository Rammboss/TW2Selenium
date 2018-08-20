package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Speicher extends AbstractMenue {
	public static Element SPEICHER_HOLZ = new Element("Speicher Holz", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[2]/td[1]/div/div/div[1]/span", Element.BY_XPATH);
	public static Element SPEICHER_LEHM = new Element("Speicher Lehm", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[3]/td[1]/div/div/div[1]/span", Element.BY_XPATH);
	public static Element SPEICHER_EISEN = new Element("Speicher Eisen", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[4]/td[1]/div/div/div[1]/span", Element.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
