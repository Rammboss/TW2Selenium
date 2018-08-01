package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class MainToolbar extends AbstractMenue {

	public static Button LOADING_SCREEN = new Button("Loading Screen", "//*[@id=\"screen-loading\"]", Button.BY_XPATH);

	public static Button HOLZ = new Button("Holz", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]", Button.BY_XPATH);
	public static Button LEHM = new Button("Lehm", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]", Button.BY_XPATH);
	public static Button EISEN = new Button("Eisen", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]/div[1]", Button.BY_XPATH);
	public static Button PROVIANT = new Button("Proviant", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[4]/div[2]/div[1]", Button.BY_XPATH);

	public static Button KASERNENSLOT1 = new Button("Kaserne Slot 1", "li", "class", "ng-scope first", Button.BY_CSS_SELECTOR);

	public static Button KASERNENSLOT2 = new Button("Kaserne Slot 2", "/html/body/div[2]/div[13]/div[3]/div/div[1]/ul/li[2]/div", Button.BY_XPATH);
	public static Button BAUSCHLEIFE = new Button("Bauschleife", "/html/body/div[2]/div[13]/div[1]/div[1]/div", Button.BY_XPATH);
	public static Button REKRUTIERUNGSSCHEIFE = new Button("Rekrutierungsschleife", "/html/body/div[2]/div[13]/div[1]/div[2]/div", Button.BY_XPATH);
	public static Button OBERFLAECHE = new Button("Spielfeld", "//*[@id=\"click-layer\"]", Button.BY_XPATH);
	public static Button ZEITLEISTE = new Button("Zeitleiste", "/html/body/div[2]/footer/div/div[2]/div[3]/a", Button.BY_XPATH);
	public static Button LOGOUT = new Button("Ausloggen", "//*[@id=\"button-logout\"]", Button.BY_XPATH);
	public static Button COMPLETE_BUILDING = new Button("Gebäude fertig stellen ", "/html/body/div[2]/div[13]/div[2]/div/ul/li[1]/div[1]/div/div[1]/a", Button.BY_XPATH);

	public static Button BELOHNUNG_ANNEHMEN = new Button("Belohnung annehmen ", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);

	public static Button ANZAHL_SPEER = new Button("Anzahl Speerträger", "/html/body/div[2]/div[12]/div/ul/li[1]/div/div", Button.BY_XPATH);
	public static Button ANZAHL_SCHWERT = new Button("Anzahl Speerträger", "/html/body/div[2]/div[12]/div/ul/li[2]/div/div", Button.BY_XPATH);
	public static Button ANZAHL_AXT = new Button("Anzahl Axtkämpfer", "/html/body/div[2]/div[12]/div/ul/li[3]/div/div", Button.BY_XPATH);
	public static Button ANZAHL_LKAV = new Button("Anzahl Axtkämpfer", "/html/body/div[2]/div[12]/div/ul/li[5]/div/div", Button.BY_XPATH);

	
	public static Button EINSTELLUNGEN = new Button("Einstellungen öffnen", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[10]/a", Button.BY_XPATH);

	public static Button ERROR_50_ANGRIFFE = new Button("50 Angriffe", "/html/body/div[2]/div[7]/div/div[3]/div", Button.BY_XPATH);
	public static Button AUF_WELTKARTE_SUCHEN = new Button("Weltkarte", "//*[@id=\"world-map\"]", Button.BY_XPATH);

	public static Button EINHEITEN_UEBERSICHT = new Button("Einheiten Übersicht aktuelles Dorf", "/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[5]/a", Button.BY_XPATH);

	@Override
	public void openMenu() {

	}

}
