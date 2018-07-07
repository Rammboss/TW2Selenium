package TB2.NewStructure.common.Auftraege;

import java.awt.Point;

public class EnterKoordinaten extends OpenSeachOnMap {

	private Point farm;

	public EnterKoordinaten(int priority, Point farm) {
		super(priority);
		this.farm = farm;
	}

	@Override
	public void run(MouseRobot robot) {
		super.run(robot);

		if (Buttons.ENTER_X.check()) {
			robot.doubleClick(Buttons.ENTER_X);
			robot.enterKoordinate(farm.x);

		}
		MouseRobot.wait(Utils.DELAY_TASTENDRUCK);

		if (Buttons.ENTER_Y.check()) {
			robot.doubleClick(Buttons.ENTER_Y);
			robot.enterKoordinate(farm.y);
		}
		MouseRobot.wait(Utils.DELAY_TASTENDRUCK);
		robot.click(Buttons.SEARCH_VIllAGE);

		MouseRobot.wait(2000); // **********Warte auf GUI***********
	}

	@Override
	public boolean check() {
		return Buttons.IS_SELECTED.check();
	}

}
