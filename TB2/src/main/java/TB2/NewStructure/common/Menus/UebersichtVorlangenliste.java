package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class UebersichtVorlangenliste extends AbstractMenue {
	public static Button GLOBALE_VORLAGENLISTE_STATUS = new Button("Globale Vorlagenliste Status", "/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div/div",
			Button.BY_XPATH);

	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN = new Button("Globale Vorlagenliste bearbeiten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/a[2]",
			Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
