package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Einheiten extends AbstractMenue {
	public static Button TABLE_UNITS = new Button("Tabelle - Einheiten Dorf", "/html/body/div[2]/section/div/div/div[1]/div[2]/div[6]/div/table/tbody", Button.BY_XPATH);
	public static Button TABLE_UNITS_ROWS = new Button("Tabelle - Einheiten Dorf Anzahl der Angriffe", "/html/body/div[2]/section/div/div/div[1]/div[2]/div[6]/div/table/tbody/tr", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
