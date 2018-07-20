package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Rohstofflager extends AbstractMenue {
	public static Button ROHSTOFFLAGER_EINSAMMELN = new Button("Rohstofflager einsammeln", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div/a",
			Button.BY_XPATH);
	public static Button ROHSTOFFLAGER_STARTEN = new Button("Rohstofflager starten", "/html/body/div[2]/section/div/div/div[1]/div/div[3]/table/tbody/tr[2]/td/a", Button.BY_XPATH);
	public static Button ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN = new Button("Rohstofflager trotzdem abschliessen", "/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
