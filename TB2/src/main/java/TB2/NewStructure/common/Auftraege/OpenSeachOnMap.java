package TB2.NewStructure.common.Auftraege;

import org.openqa.selenium.WebDriver;

import TB2.TB2.Buttons;

public class OpenSeachOnMap extends Auftrag {

	public OpenSeachOnMap(int priority) {
		super(priority);
	}

	@Override
	public boolean check() {
		return Buttons.X_KOORDINATE.isPresent() && Buttons.Y_KOORDINATE.isPresent();
	}

	@Override
	public void run(WebDriver driver) {
		
		if (!Buttons.X_KOORDINATE.isPresent() || !Buttons.Y_KOORDINATE.isPresent()) {
			Buttons.AUF_WELTKARTE_SUCHEN.click();
		}

	}

}
