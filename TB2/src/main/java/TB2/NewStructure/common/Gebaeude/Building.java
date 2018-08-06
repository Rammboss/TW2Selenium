package TB2.NewStructure.common.Gebaeude;

public abstract class Building {

    private int name;

    private int holzkosten;

    private int lehmKosten;

    private int eisenKosten;

    private int proviantKosten;

    private int bauzeitSEC;

    public Building(int name, int holzkosten, int lehmKosten, int eisenKosten, int proviantKosten, int bauzeitSEC) {
        this.name = name;
        this.holzkosten = holzkosten;
        this.lehmKosten = lehmKosten;
        this.eisenKosten = eisenKosten;
        this.proviantKosten = proviantKosten;
        this.bauzeitSEC = bauzeitSEC;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getHolzkosten() {
        return holzkosten;
    }

    public void setHolzkosten(int holzkosten) {
        this.holzkosten = holzkosten;
    }

    public int getLehmKosten() {
        return lehmKosten;
    }

    public void setLehmKosten(int lehmKosten) {
        this.lehmKosten = lehmKosten;
    }

    public int getEisenKosten() {
        return eisenKosten;
    }

    public void setEisenKosten(int eisenKosten) {
        this.eisenKosten = eisenKosten;
    }

    public int getProviantKosten() {
        return proviantKosten;
    }

    public void setProviantKosten(int proviantKosten) {
        this.proviantKosten = proviantKosten;
    }

    public int getBauzeitSEC() {
        return bauzeitSEC;
    }

    public void setBauzeitSEC(int bauzeitSEC) {
        this.bauzeitSEC = bauzeitSEC;
    }
}
