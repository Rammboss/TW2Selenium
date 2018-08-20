package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class TruppenSchicken extends AbstractMenue {

	public static Element EINHEITEN_TABELLE = new Element("Verfügbare Einheiten Tabelle", "ul", "class", "unit-operate-slider", Element.BY_CSS_SELECTOR);
	
	
	public static Element ANGREIFEN = new Element("Angreifen","/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Element.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
