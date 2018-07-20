package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Speicher extends AbstractMenue {
	public static Button SPEICHER_HOLZ = new Button("Speicher Holz", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[2]/td[1]/div/div/div[1]/span", Button.BY_XPATH);
	public static Button SPEICHER_LEHM = new Button("Speicher Lehm", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[3]/td[1]/div/div/div[1]/span", Button.BY_XPATH);
	public static Button SPEICHER_EISEN = new Button("Speicher Eisen", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[4]/td[1]/div/div/div[1]/span", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
