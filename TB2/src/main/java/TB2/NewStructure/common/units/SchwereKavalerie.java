package TB2.NewStructure.common.units;


public class SchwereKavalerie {
    private final int holzKosten = 200;
    private final int lehmKosten = 150;
    private final int eisenKosten = 600;
    private final int proviant = 6;

    private int bauzeitSEC = 571;

    private final int kampfstaerke = 150;

    private final int deffInfaterie = 200;
    private final int deffKavelerie = 160;
    private final int deffBogen = 180;

    private final int tragekapazitaet = 50;

    private int geschwindigkeitMin = 9;

    public SchwereKavalerie(int bauzeitSEC, int geschwindigkeitMin) {
        this.bauzeitSEC = bauzeitSEC;
        this.geschwindigkeitMin = geschwindigkeitMin;
    }

    public int getHolzKosten() {
        return holzKosten;
    }

    public int getLehmKosten() {
        return lehmKosten;
    }

    public int getEisenKosten() {
        return eisenKosten;
    }

    public int getProviant() {
        return proviant;
    }

    public int getBauzeitSEC() {
        return bauzeitSEC;
    }

    public void setBauzeitSEC(int bauzeitSEC) {
        this.bauzeitSEC = bauzeitSEC;
    }

    public int getKampfstaerke() {
        return kampfstaerke;
    }

    public int getDeffInfaterie() {
        return deffInfaterie;
    }

    public int getDeffKavelerie() {
        return deffKavelerie;
    }

    public int getDeffBogen() {
        return deffBogen;
    }

    public int getTragekapazitaet() {
        return tragekapazitaet;
    }

    public int getGeschwindigkeitMin() {
        return geschwindigkeitMin;
    }

    public void setGeschwindigkeitMin(int geschwindigkeitMin) {
        this.geschwindigkeitMin = geschwindigkeitMin;
    }
}
