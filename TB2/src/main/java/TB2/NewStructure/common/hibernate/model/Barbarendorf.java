package TB2.NewStructure.common.hibernate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "Barbarendorf")
@DiscriminatorValue("Barbarendorf")
public class Barbarendorf extends Dorf {

	@Column(name = "LASTATTACK")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime attackedAt;

	private static final int FARM_INTERVALL = 50;

	private static final int MAX_DISTANCE = 405955;

	public Barbarendorf(int x, int y, int punkte) {
		super(x, y, "Barbarendorf", punkte);
		LocalDateTime tmp = new LocalDateTime(System.currentTimeMillis());
		this.attackedAt = tmp.minusMinutes(90);

	}

	public Barbarendorf(int x, int y, String name, int punkte) {
		super(x, y, name, punkte);
		LocalDateTime tmp = new LocalDateTime(System.currentTimeMillis());
		this.attackedAt = tmp.minusMinutes(90);

	}

	public Barbarendorf() {
		super(500, 500, "test", 300);
	}

	public boolean isFarmable(EigenesDorf attacker) {
		// System.out.println(
		// getDistance(attacker.getX(), this.getX(), attacker.getY(), this.getY()) + " <
		// " + MAX_DISTANCE);
		return this.getAttackedAt().plusMinutes(FARM_INTERVALL).isBefore(new LocalDateTime())
				&& getDistance(attacker.getX(), this.getX(), attacker.getY(), this.getY()) < MAX_DISTANCE;

	}

	public LocalDateTime getAttackedAt() {
		return attackedAt;
	}

	public void setAttackedAt(LocalDateTime attackedAt) {
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

	public boolean isodd(int value) {
		return value % 2 != 0;
	}

	public int getDistance(int x1, int x2, int y1, int y2) {
		double z1 = isodd(y1) ? x1 + 0.5 : x1 - 0.5;
		double z2 = isodd(y2) ? x2 + 0.5 : x2 - 0.5;

		double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(y1 - y2, 2));
		double d2 = Math.sqrt(Math.pow((x1 - x2), 2) + 0.75 * Math.pow((y1 - y2), 2));
		int erg = (int) ((d1 + d2) / 2 * 10000);
		return erg;
	}

}
