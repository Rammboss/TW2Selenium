package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Koordinaten extends AbstractMenue {
	public static Button X_KOORDINATE = new Button("X", "/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[1]/input", Button.BY_XPATH);
	public static Button Y_KOORDINATE = new Button("Y", "/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[2]/input", Button.BY_XPATH);
	public static Button JUMP_TO = new Button("Dorf auswählen", "/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[3]/div", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
