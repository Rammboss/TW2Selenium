package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Rohstofflager extends AbstractMenue {
	public static Button ROHSTOFFLAGER_EINSAMMELN = new Button("Rohstofflager einsammeln", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div/a",
			Button.BY_XPATH);
	public static Button ROHSTOFFLAGER_STARTEN = new Button("Rohstofflager starten", "/html/body/div[2]/section/div/div/div[1]/div/div[3]/table/tbody/tr[2]/td/a", Button.BY_XPATH);
	public static Button ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN = new Button("Rohstofflager trotzdem abschliessen", "/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Button.BY_XPATH);

	public static Button ITEMS_VERWENDEN = new Button("Items verwenden", "/html/body/div[2]/section/div/div/div[1]/div/div[2]/a", Button.BY_XPATH);
	public static Button ROHSTOFFLAGER_STARTEN_ZEIT = new Button("Rohstofflager Zeit des neuen Auftrages", "div", "ng-bind", "(job.duration | readableSecondsFilter)", Button.BY_CSS_SELECTOR);

	public static Button AKTUELLE_PRODUKTION = new Button("Aktuelle Produktion", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]", Button.BY_XPATH);
	public static Button AKTUELLE_PRODUKTION_ZEIT = new Button("Rohstofflager Zeit des aktuellen Auftrages",
			"/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]", Button.BY_XPATH);

	public static Button LETZTES_ITEM = new Button("Letztes Item", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[1]/div[3]/div[6]/div[2]", Button.BY_XPATH);

	public static Button BENUTZEN = new Button("Benutzen", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);

	public static Button AKTUELLE_ZEIT = new Button("Aktuelle Zeit", "/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]/span", Button.BY_XPATH);
	public static Button GEGENSTAND_BENUTZEN = new Button("Gegnstand benutzen", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}

}
