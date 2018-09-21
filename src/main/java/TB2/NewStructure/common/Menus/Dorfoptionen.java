package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Dorfoptionen extends AbstractMenue {

	public static Element MENUE_MITTE = new Element("Menue Mitte", "div","class", "menu-highlight", Element.BY_CSS_SELECTOR );

	public static Element PRODUKTION_STEIGERN = new Element("Produktion steigern", "div", "tooltip-content", "Produktion steigern", Element.BY_CSS_SELECTOR);
	public static Element DORFINFORMATIONEN = new Element("Dorf Infos", "div", "tooltip-content", "Dorfinformationen", Element.BY_CSS_SELECTOR);

	public static Element GRUPPEN_HINZUFUEGEN = new Element("Gruppen hinztufügen", "div", "tooltip-content", "Gruppen hinzufügen", Element.BY_CSS_SELECTOR);
	public static Element ACTIVE_VILLAGE = new Element("Aktives Dorf 1 festlegen", "div", "tooltip-content", "Aktives Dorf festlegen", Element.BY_CSS_SELECTOR);

	public static Element NACHRICHT_SENDEN = new Element("Nachricht senden 1", "div", "tooltip-content", "Nachricht senden", Element.BY_CSS_SELECTOR);
	public static Element NEXT_VILLAGE = new Element("Nextes Dorf", "//*[@id=\"switch-village-next\"]", Element.BY_XPATH);

	
	public static Element TRUPPEN_SCHICKEN = new Element("Truppen schicken", "div", "tooltip-content", "Truppen schicken", Element.BY_CSS_SELECTOR);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub

	}
}
