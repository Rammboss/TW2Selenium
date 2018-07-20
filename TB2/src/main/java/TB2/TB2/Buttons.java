package TB2.TB2;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
	public static List<Button> provinzen = new ArrayList<Button>();
	public static List<Button> ACCOUNTS = new ArrayList<Button>();

	static {
		provinzen.add(new Button("Kankan", "//*[@id=\\\"click-layer\\\"]", Button.BY_XPATH));
		ACCOUNTS.add(new Button("Ausloggen", "/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[5]/a", Button.BY_XPATH));
		ACCOUNTS.add(new Button("Ausloggen", "/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[6]/a", Button.BY_XPATH));
		ACCOUNTS.add(new Button("Ausloggen", "/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[4]/a", Button.BY_XPATH));

	}

}
