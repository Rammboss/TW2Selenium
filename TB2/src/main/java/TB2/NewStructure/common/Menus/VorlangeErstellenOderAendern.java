package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class VorlangeErstellenOderAendern extends AbstractMenue {
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1 = new Button("Globale Vorlagenliste bearbeiten",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF = new Button("Globale Vorlagenliste Hotkey Angriff",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]/div/span", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER = new Button("Globale Vorlagenliste Anzahl Speerträger",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[1]/div/input", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT = new Button("Globale Vorlagenliste Anzahl Schwertkämpfer",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[2]/div/input", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT = new Button("Globale Vorlagenliste Anzahl Barbaren",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[2]/td[1]/div/input", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_LKAV = new Button("Globale Vorlagenliste Anzahl leichte Kavellerie",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[3]/td[1]/div/input", Button.BY_XPATH);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN = new Button("Globale Vorlagenliste Anzahl Paladin",
			"/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[6]/td[2]/div/input", Button.BY_XPATH);

	public static Button GLOBALE_VORLAGENLISTE_SPEICHERN = new Button("Globale Vorlagenliste speichern", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
