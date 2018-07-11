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

	public Barbarendorf(int x, int y, int punkte) {
		super(x, y, "Barbarendorf", punkte);
		LocalDateTime tmp = new LocalDateTime(System.currentTimeMillis());
		this.attackedAt = tmp.minusMinutes(90);

	}

	public Barbarendorf() {
		super(500, 500, "test", 300);
	}

	public boolean isFarmable() {
		return this.getAttackedAt().plusMinutes(FARM_INTERVALL).isBefore(new LocalDateTime());

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

}
