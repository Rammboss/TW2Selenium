package TB2.NewStructure.common.hibernate.model;

import TB2.NewStructure.common.Auftraege.BuildingTask;
import TB2.NewStructure.common.Gebaeude.Speicher;
import TB2.NewStructure.common.units.Units;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EigenesDorf")
//@PrimaryKeyJoinColumn(name = "dorf_id")
//@DiscriminatorValue("EigenesDorf")
public class EigenesDorf implements KoordinatenInterface {


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

    @Transient
    private boolean blockAttacks;

    @Transient
    private Units rekrutierungsEinheit;

    @Transient
    private int rekrutierungsAnzahl;

    @Transient
    private List<Units> farableUnits;

    @Transient
    private boolean allowedMuenzenPraegen;

    @Transient
    private List<BuildingTask> buildingTask;

    @Transient
    private Speicher speicher;

    @Transient
    private boolean forceRekrutierung;

    @Transient
    private int rekrutierungsscheifenLimit;

    public EigenesDorf(int x, int y, String name, int punkte, String spieler) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.punkte = punkte;
        this.spieler = spieler;
        this.blockAttacks = false;
        this.rekrutierungsEinheit = null;
        this.buildingTask = null;
        this.rekrutierungsAnzahl = 0;
        this.allowedMuenzenPraegen = false;
        this.farableUnits = null;
        this.speicher = new Speicher();
        this.forceRekrutierung = false;
        this.rekrutierungsscheifenLimit = 8;
    }

    public EigenesDorf() {
        this.blockAttacks = false;
        this.rekrutierungsEinheit = null;
        this.rekrutierungsAnzahl = 0;
        this.allowedMuenzenPraegen = false;
        this.farableUnits = null;
        this.buildingTask = null;
        this.speicher = new Speicher();
        this.forceRekrutierung = false;
        this.rekrutierungsscheifenLimit = 8;

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

    public boolean isBlockAttacks() {
        return blockAttacks;
    }

    public void setBlockAttacks(boolean blockAttacks) {
        this.blockAttacks = blockAttacks;
    }

    public Units getRekrutierungsEinheit() {
        return rekrutierungsEinheit;
    }

    public void setRekrutierungsEinheit(Units rekrutierungsEinheit) {
        this.rekrutierungsEinheit = rekrutierungsEinheit;
    }

    public int getRekrutierungsAnzahl() {
        return rekrutierungsAnzahl;
    }

    public void setRekrutierungsAnzahl(int rekrutierungsAnzahl) {
        this.rekrutierungsAnzahl = rekrutierungsAnzahl;
    }

    public boolean isAllowedMuenzenPraegen() {
        return allowedMuenzenPraegen;
    }

    public void setAllowedMuenzenPraegen(boolean allowedMuenzenPraegen) {
        this.allowedMuenzenPraegen = allowedMuenzenPraegen;
    }

    public List<Units> getFarableUnits() {
        return farableUnits;
    }

    public void setFarableUnits(List<Units> farableUnits) {
        this.farableUnits = farableUnits;
    }

    public List<BuildingTask> getBuildingTask() {
        return buildingTask;
    }

    public void setBuildingTask(List<BuildingTask> buildingTask) {
        this.buildingTask = buildingTask;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }

    public boolean isForceRekrutierung() {
        return forceRekrutierung;
    }

    public void setForceRekrutierung(boolean forceRekrutierung) {
        this.forceRekrutierung = forceRekrutierung;
    }

    public int getRekrutierungsscheifenLimit() {
        return rekrutierungsscheifenLimit;
    }

    public void setRekrutierungsscheifenLimit(int rekrutierungsscheifenLimit) {
        this.rekrutierungsscheifenLimit = rekrutierungsscheifenLimit;
    }
}
