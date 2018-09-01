package TB2.NewStructure.common.Gebaeude;

public class Speicher {

    private int capacity;
    private int holz;
    private int lehm;
    private int eisen;

    public Speicher(int capacity, int holz, int lehm, int eisen) {
        this.capacity = capacity;
        this.holz = holz;
        this.lehm = lehm;
        this.eisen = eisen;
    }

    public Speicher() {
        this.capacity = -1;
        this.holz = -1;
        this.lehm = -1;
        this.eisen = -1;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHolz() {
        return holz;
    }

    public void setHolz(int holz) {
        this.holz = holz;
    }

    public int getLehm() {
        return lehm;
    }

    public void setLehm(int lehm) {
        this.lehm = lehm;
    }

    public int getEisen() {
        return eisen;
    }

    public void setEisen(int eisen) {
        this.eisen = eisen;
    }
}
