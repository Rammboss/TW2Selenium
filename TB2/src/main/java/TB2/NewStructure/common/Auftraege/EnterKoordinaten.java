package TB2.NewStructure.common.Auftraege;

import java.awt.Point;

import org.openqa.selenium.WebDriver;

public class EnterKoordinaten extends OpenSeachOnMap {

	private Point farm;

	public EnterKoordinaten(int priority, Point farm) {
		super(priority);
		this.farm = farm;
	}

	@Override
	public void run(WebDriver driver) {
		super.run(driver);

		if (Buttons.ENTER_X.check()) {
			driver.doubleClick(Buttons.ENTER_X);
			driver.enterKoordinate(farm.x);

		}
		MouseRobot.wait(Utils.DELAY_TASTENDRUCK);

		if (Buttons.ENTER_Y.check()) {
			driver.doubleClick(Buttons.ENTER_Y);
			driver.enterKoordinate(farm.y);
		}
		MouseRobot.wait(Utils.DELAY_TASTENDRUCK);
		driver.click(Buttons.SEARCH_VIllAGE);

		MouseRobot.wait(2000); // **********Warte auf GUI***********
	}

	@Override
	public boolean check() {
		return Buttons.IS_SELECTED.check();
	}

}
