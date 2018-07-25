package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Dorfoptionen extends AbstractMenue {

	public static Button MENUE_MITTE = new Button("Menu Mitte", "/html/body/div[2]/div[9]/div[4]/div/div[1]", Button.BY_XPATH);
	public static Button PRODUKTION_STEIGERN = new Button("Produktion steigern", "div", "tooltip-content", "Produktion steigern", Button.BY_CSS_SELECTOR);
	public static Button DORFINFORMATIONEN = new Button("Dorf Infos 1-2", "div", "tooltip-content", "Dorfinformationen", Button.BY_CSS_SELECTOR);

	public static Button GRUPPEN_HINZUFUEGEN = new Button("Gruppen hinztufügen", "div", "tooltip-content", "Gruppen hinzufügen", Button.BY_CSS_SELECTOR);
	public static Button ACTIVE_VILLAGE = new Button("Aktives Dorf 1 festlegen", "div", "tooltip-content", "Aktives Dorf festlegen", Button.BY_CSS_SELECTOR);

	public static Button NACHRICHT_SENDEN = new Button("Nachricht senden 1", "div", "tooltip-content", "Nachricht senden", Button.BY_CSS_SELECTOR);
	public static Button NEXT_VILLAGE = new Button("Nextes Dorf", "//*[@id=\"switch-village-next\"]", Button.BY_XPATH);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}
}