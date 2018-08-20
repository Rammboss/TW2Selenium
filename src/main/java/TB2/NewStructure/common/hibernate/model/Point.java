package TB2.NewStructure.common.hibernate.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Point")
@Inheritance(strategy = InheritanceType.JOINED)
public class Point  implements KoordinatenInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "X", nullable = false)
    private int x;
    @Column(name = "Y", nullable = false)
    private int y;

    @Column(name = "CHECKED", nullable = false, columnDefinition = "tinyint default false")
    private boolean checked;

    @Column(name = "CHECKEDAT")
    private LocalDateTime checkedAt;

    public Point(int x, int y, boolean checked, LocalDateTime checkedAt) {
        super();
        this.x = x;
        this.y = y;
        this.checked = checked;
        this.checkedAt = checkedAt;
    }

    public Point(int x, int y, boolean checked) {
        super();
        this.x = x;
        this.y = y;
        this.checked = checked;
    }

    public Point() {
        super();
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

    @Override
    public String getName() {
        return "";
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public boolean isOdd(int value) {
        return value % 2 != 0;
    }

    public int getDistance(int x1, int x2, int y1, int y2) {
        double z1 = isOdd(y1) ? x1 + 0.5 : x1 - 0.5;
        double z2 = isOdd(y2) ? x2 + 0.5 : x2 - 0.5;

        double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(y1 - y2, 2));
        double d2 = Math.sqrt(Math.pow((x1 - x2), 2) + 0.75 * Math.pow((y1 - y2), 2));
        int erg = (int) ((d1 + d2) / 2 * 10000);
        return erg;
    }

    @Override
    public String toString() {
        return "Point [id=" + id + ", x=" + x + ", y=" + y + ", checked=" + checked + ", checkedAt=" + checkedAt + "]";
    }

}
