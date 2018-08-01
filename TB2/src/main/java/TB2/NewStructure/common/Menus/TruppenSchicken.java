package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class TruppenSchicken extends AbstractMenue {

	public static Button EINHEITEN_TABELLE = new Button("Verfügbare Einheiten Tabelle", "ul", "class", "unit-operate-slider", Button.BY_CSS_SELECTOR);
	
	
	public static Button ANGREIFEN = new Button("Angreifen","/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
