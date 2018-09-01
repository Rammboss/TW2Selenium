package TB2.NewStructure.common.units;


public class Rammbock implements RekrutableUnits{
    public static Units TYPE = Units.RAMMEN;

    private final int holzKosten = 300;
    private final int lehmKosten = 200;
    private final int eisenKosten = 200;
    private final int proviant = 5;
    private int bauzeitSEC = 457;

    private final int kampfstaerke = 2;

    private final int deffInfaterie = 20;
    private final int deffKavelerie = 50;
    private final int deffBogen = 20;

    private final int tragekapazitaet = 0;

    private int geschwindigkeitMin = 24;

    public Rammbock() {
    }

    public Rammbock(int bauzeitSEC, int geschwindigkeitMin) {
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
