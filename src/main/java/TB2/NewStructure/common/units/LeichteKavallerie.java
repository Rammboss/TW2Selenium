package TB2.NewStructure.common.units;


public class LeichteKavallerie implements RekrutableUnits{
    public static Units TYPE = Units.LKAV;

    private final int holzKosten = 125;
    private final int lehmKosten = 100;
    private final int eisenKosten = 250;
    private final int proviant = 4;
    private int bauzeitSEC = 342;

    private final int kampfstaerke = 130;

    private final int deffInfaterie = 30;
    private final int deffKavelerie = 40;
    private final int deffBogen = 30;

    private final int tragekapazitaet = 50;

    private int geschwindigkeitMin = 8;

    public LeichteKavallerie() {
    }

    public LeichteKavallerie(int bauzeitSEC, int geschwindigkeitMin) {
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
