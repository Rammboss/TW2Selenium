package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Kaserne extends AbstractMenue {
	public static Element KASERNE_AXTKAEMPFER = new Element("Axtkämpfer rekrutieren",
			"/html/body/div[2]/section/div/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/div[1]/label[1]/table/tbody/tr[2]/td/div/div[1]", Element.BY_XPATH);
	public static Element KASERNE_AXTKAEMPFER_NICHT_VORHANDEN = new Element("Axtkämpfer nicht vorhanden",
			"/html/body/div[2]/section/div/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/div[1]/label[1]/table/tbody/tr[3]/td/div[2]", Element.BY_XPATH);

	public static Element KASERNE_AXTKAEMPFER_VALUE = new Element("Axtkämpfer Anzahl", "//*[@id=\"input-unit-recruit-axe\"]", Element.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
