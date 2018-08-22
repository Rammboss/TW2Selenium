package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class EinstellungenSubSpiel extends AbstractMenue {
	public static Element ANNIMATION1 = new Element("Annimationen auf 1 setzten", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[3]/td[1]/div[2]/div/a[1]",
			Element.BY_XPATH);

	public static Element MUSIK_SOUND = new Element("Musik an/aus", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[2]/td[2]/div[2]/div/a[1]",
			Element.BY_XPATH);
	public static Element EFFEKT_SOUND = new Element("Effekte Sound an/aus", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[3]/td[2]/div[2]/div/a[1]",
			Element.BY_XPATH);

	public static Element AUSGEHENDE_BEFEHLE_ANZEIGEN = new Element("Einstellungen Spiel Tab Ausgehende Befehle Anzeigen",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[8]/td[2]/label", Element.BY_XPATH);

	public static Element TIPPS_EINSCHALTEN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[1]/tbody/tr[3]/td[2]/label", Element.BY_XPATH);

	public static Element ANIMATIONEN_AKTIVIEREN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[2]/label", Element.BY_XPATH);

	public static Element ZOOM_ANIMATIONEN_AKTIVIEREN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[4]/td[2]/label", Element.BY_XPATH);

	public static Element NEBEL_AKTIVIEREN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[5]/td[2]/label", Element.BY_XPATH);

	public static Element BAUM_AKTIVIEREN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[6]/td[2]/label", Element.BY_XPATH);

	public static Element HANDELSBEFEHLE_AKTIVIEREN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[9]/td[2]/label", Element.BY_XPATH);


	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}
}
