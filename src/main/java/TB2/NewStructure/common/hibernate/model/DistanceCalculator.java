package TB2.NewStructure.common.hibernate.model;

public class DistanceCalculator {

    private KoordinatenInterface dorf1;

    private KoordinatenInterface dorf2;

    public DistanceCalculator(KoordinatenInterface dorf1, KoordinatenInterface dorf2) {
        this.dorf1 = dorf1;
        this.dorf2 = dorf2;
    }

    public boolean isOdd(int value) {
        return value % 2 != 0;
    }

    public int getDistance() {

        double z1 = isOdd(dorf1.getY()) ? dorf1.getX() + 0.5 : dorf1.getX() - 0.5;
        double z2 = isOdd(dorf2.getY()) ? dorf2.getX() + 0.5 : dorf2.getX() - 0.5;

        double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(dorf1.getY() - dorf2.getY(), 2));
        double d2 = Math.sqrt(Math.pow((dorf1.getX() - dorf2.getX()), 2) + 0.75 * Math.pow((dorf1.getY() - dorf2.getY()), 2));
        int erg = (int) ((d1 + d2) / 2 * 10000);
        return erg;
    }
}
