package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public  class Dorfansicht extends AbstractMenue {

	public static Button HAUPTGEBAEUDE = new Button("Hauptgebäude", "span", "class", "building-name ng-binding", "Hauptgebäude", Button.BY_CSS_SELECTOR);
	
	public static Button SPEICHER = new Button("Speicher 1", "span", "class", "building-name ng-binding", "Speicher", Button.BY_CSS_SELECTOR_WITH_CRITERIA);
	public static Button SPEICHER2 = new Button("Speicher 2", "div", "tooltip-content", "Speicher", "Speicher", Button.BY_CSS_SELECTOR_WITH_CRITERIA);

	@Override
	public void openMenu() {
		// TODO Auto-generated method stub
		
	}

}
