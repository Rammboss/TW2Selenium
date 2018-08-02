package TB2.NewStructure.common.hibernate.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Barbarendorf")
//@PrimaryKeyJoinColumn(name = "dorf_id")
//@DiscriminatorValue("Barbarendorf")
public class Barbarendorf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "X", nullable = false)
    private int x;
    @Column(name = "Y", nullable = false)
    private int y;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PUNKTE")
    private int punkte;

    @Column(name = "LASTATTACK")
    private LocalDateTime attackedAt;

    private static final int FARM_INTERVALL = 60;

    private static final int MAX_DISTANCE = 505955;

    public Barbarendorf(int x, int y, String name, int punkte, LocalDateTime attackedAt) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.punkte = punkte;
        this.attackedAt = LocalDateTime.now().minusMinutes(90);
    }

    public Barbarendorf() {
    }

    public boolean isFarmable(EigenesDorf attacker) {

        return this.getAttackedAt().plusMinutes(FARM_INTERVALL).isBefore(LocalDateTime.now()) && getDistance(attacker.getX(), this.getX(), attacker.getY(), this.getY()) < MAX_DISTANCE;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barbarendorf that = (Barbarendorf) o;
        return x == that.x &&
                y == that.y &&
                punkte == that.punkte &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name, punkte);
    }

    public LocalDateTime getAttackedAt() {
        return attackedAt;
    }

    public void setAttackedAt(LocalDateTime attackedAt) {
        this.attackedAt = attackedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
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
