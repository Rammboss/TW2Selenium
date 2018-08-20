package TB2.NewStructure.common.hibernate.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Provinz")
@Inheritance(strategy = InheritanceType.JOINED)
public class Provinz implements KoordinatenInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "X", nullable = false)
    private int x;

    @Column(name = "Y", nullable = false)
    private int y;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCANNEDAT")
    private LocalDateTime scannedAt;

    public Provinz(int x, int y, String name, LocalDateTime scannedAt) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        this.scannedAt = scannedAt;
    }

    public Provinz() {
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

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getScannedAt() {
        return scannedAt;
    }

    public void setScannedAt(LocalDateTime scannedAt) {
        this.scannedAt = scannedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Provinz other = (Provinz) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
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

}
