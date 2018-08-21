package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public  class Dorfansicht extends AbstractMenue {

	public static Element HAUPTGEBAEUDE = new Element("Hauptgebäude", "span", "class", "building-name", "Hauptgebäude", Element.BY_CSS_SELECTOR);
	
	public static Element SPEICHER = new Element("Speicher 1", "span", "class", "building-name", "Speicher", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
	public static Element SPEICHER2 = new Element("Speicher 2", "div", "tooltip-content", "Speicher", "Speicher", Element.BY_CSS_SELECTOR_WITH_CRITERIA);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub
		
	}

}
