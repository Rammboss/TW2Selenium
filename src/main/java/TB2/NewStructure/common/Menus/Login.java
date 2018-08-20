package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class Login extends AbstractMenue {

	public static Element SPIELERNAME = new Element("Spielername", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[1]/input", Element.BY_XPATH);
	public static Element PASSWORT = new Element("Passwort", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[2]/input", Element.BY_XPATH);
	public static Element SPIELEN = new Element("Spielen", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/button", Element.BY_XPATH);
	public static Element LOGIN = new Element("Login","a", "class", "btn-orange btn-border small-icon", null, Element.BY_CSS_SELECTOR_WITH_CRITERIA);
	

	public static Element LOADING_SCREEN = new Element("Loading Screen", "//*[@id=\"screen-loading\"]", Element.BY_XPATH);

	@Override
	public void openMenu() {

	}

}
