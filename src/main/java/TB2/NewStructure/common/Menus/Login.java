package TB2.NewStructure.common.Menus;

import TB2.TB2.Button;

public class Login extends AbstractMenue {

	public static Button SPIELERNAME = new Button("Spielername", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[1]/input", Button.BY_XPATH);
	public static Button PASSWORT = new Button("Passwort", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[2]/input", Button.BY_XPATH);
	public static Button SPIELEN = new Button("Spielen", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/button", Button.BY_XPATH);
	public static Button LOGIN = new Button("Login","a", "class", "btn-orange btn-border small-icon", null, Button.BY_CSS_SELECTOR_WITH_CRITERIA);
	

	public static Button LOADING_SCREEN = new Button("Loading Screen", "//*[@id=\"screen-loading\"]", Button.BY_XPATH);

	@Override
	public void openMenu() {

	}

}
