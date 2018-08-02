package TB2.NewStructure.common.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EigenesDorf")
//@PrimaryKeyJoinColumn(name = "dorf_id")
//@DiscriminatorValue("EigenesDorf")
public class EigenesDorf {


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

    @Column(name = "SPIELER")
    private String spieler;

    public EigenesDorf(int x, int y, String name, int punkte, String spieler) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.punkte = punkte;
        this.spieler = spieler;
    }

    public EigenesDorf() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EigenesDorf that = (EigenesDorf) o;
        return x == that.x &&
                y == that.y &&
                punkte == that.punkte &&
                Objects.equals(name, that.name) &&
                Objects.equals(spieler, that.spieler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name, punkte, spieler);
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

    public String getSpieler() {
        return spieler;
    }

    public void setSpieler(String spieler) {
        this.spieler = spieler;
    }
}
