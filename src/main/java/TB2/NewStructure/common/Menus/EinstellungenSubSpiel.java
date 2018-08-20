package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class EinstellungenSubSpiel extends AbstractMenue {
	public static Element EINSTELLUNGEN_SPIEL_ANNIMATION1 = new Element("Annimationen auf 1 setzten", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[3]/td[1]/div[2]/div/a[1]",
			Element.BY_XPATH);

	public static Element EINSTELLUNGEN_SPIEL_MUSIK_SOUND = new Element("Musik an/aus", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[2]/td[2]/div[2]/div/a[1]",
			Element.BY_XPATH);
	public static Element EINSTELLUNGEN_SPIEL_EFFEKT_SOUND = new Element("Effekte Sound an/aus", "/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[3]/td[2]/div[2]/div/a[1]",
			Element.BY_XPATH);

	public static Element EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN = new Element("Einstellungen Spiel Tab Ausgehende Befehle Anzeigen",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[8]/td[2]/label", Element.BY_XPATH);

	public static Element EINSTELLUNGEN_SPIEL_TIPPS_EINSCHALTEN = new Element("Einstellungen Spiel Tipps anzeigen ein/aus",
			"/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[1]/tbody/tr[3]/td[2]/label", Element.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}
}
