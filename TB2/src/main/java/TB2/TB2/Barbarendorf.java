package TB2.TB2;

import java.awt.Point;
import java.sql.Timestamp;

public class Barbarendorf extends Dorf {

	private Timestamp attackedAt;
	
	private static final int FARM_INTERVALL = 50;

	public Barbarendorf(int punkte, Point coordinaten) {
		super("Barbarendorf", punkte, coordinaten);
		Timestamp tmp = new Timestamp(System.currentTimeMillis());
		Long duration = (long) (((180 * 60) + 00) * 1000);
		tmp.setTime(tmp.getTime() - duration);
		this.attackedAt = tmp;
	}

	public boolean isFarmable() {
		Timestamp current = new Timestamp(System.currentTimeMillis());
		if (compareTwoTimeStamps(current, attackedAt) > FARM_INTERVALL) {
			return true;

		}
		return false;
	}

	public Timestamp getAttackedAt() {
		return attackedAt;
	}

	public void setAttackedAt(Timestamp attackedAt) {
		this.attackedAt = attackedAt;
	}

	public long compareTwoTimeStamps(Timestamp currentTime, Timestamp oldTime) {
		long milliseconds1 = oldTime.getTime();
		long milliseconds2 = currentTime.getTime();

		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffMinutes;
	}

}
