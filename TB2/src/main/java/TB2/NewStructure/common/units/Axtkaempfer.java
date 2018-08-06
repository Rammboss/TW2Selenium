package TB2.NewStructure.common.units;


public class Axtkaempfer {
    private final int holzKosten = 60;
    private final int lehmKosten = 30;
    private final int eisenKosten = 40;
    private final int proviant = 1;
    private int bauzeitSEC = 144;

    private final int kampfstaerke = 45;

    private final int deffInfaterie = 10;
    private final int deffKavelerie = 5;
    private final int deffBogen = 10;

    private final int tragekapazitaet = 20;

    private int geschwindigkeitMin = 14;

    public Axtkaempfer(int bauzeitSEC, int geschwindigkeitMin) {
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
