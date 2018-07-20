package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Dorfinformationen extends AbstractMenue {
	public static Button DORFINFORMATIONEN_NAME = new Button("Dorf Infos Name", "/html/body/div[2]/section/div/div[2]/div/div[1]/div/table[1]/tbody/tr[1]/td[2]/div/div[1]/span[2]", Button.BY_XPATH);
	public static Button DORFINFORMATIONEN_PUNKTE = new Button("Dorf Infos Punkte", "/html/body/div[2]/section/div/div[2]/div/div[1]/div/table[1]/tbody/tr[2]/td[1]/div/div/span[2]", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
