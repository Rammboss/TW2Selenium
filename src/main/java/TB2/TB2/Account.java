package TB2.TB2;

import TB2.NewStructure.common.Menus.Login;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;

import java.time.Duration;

public class Account {

    public static int performaceMultiplier;

    private boolean zoomOut;

    private String spielername;

    private String passwort;

    private String welt;

    private boolean useSecondMonitor;

    private boolean woodPC;

    public Account(int performaceMultiplier, boolean zoomOut, String spielername, String passwort, String welt, boolean useSecondMonitor, boolean woodPC) {
        super();
        this.zoomOut = zoomOut;
        this.useSecondMonitor = useSecondMonitor;
        this.woodPC = woodPC;
        if (performaceMultiplier < 1) {
            Account.performaceMultiplier = 1;

        } else {
            Account.performaceMultiplier = performaceMultiplier;

        }
        this.spielername = spielername;
        this.passwort = passwort;
        this.welt = welt;
    }

    public void login() throws ElementisNotClickable {

        Login.SPIELERNAME.sendText(getSpielername());
        Login.PASSWORT.sendText(getPasswort());
        Login.SPIELEN.click();

        Login.LOGIN.setCriteria(getWelt());

        if (Login.LOGIN.isPresent(Duration.ofSeconds(30))) {
            Main.sleep(1);
            Login.LOGIN.click();
        }

        Main.sleep(1);

        Login.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));

        Main.sleep(2);

    }

    public String getSpielername() {
        return spielername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getWelt() {
        return welt;
    }

    public int getPerformaceMultiplikator() {
        return performaceMultiplier;
    }

    public boolean isZoomOut() {
        return zoomOut;
    }

    public void setZoomOut(boolean zoomOut) {
        this.zoomOut = zoomOut;
    }

    public boolean isUseSecondMonitor() {
        return useSecondMonitor;
    }

    public void setUseSecondMonitor(boolean useSecondMonitor) {
        this.useSecondMonitor = useSecondMonitor;
    }

    public boolean isWoodPC() {
        return woodPC;
    }

    public void setWoodPC(boolean woodPC) {
        this.woodPC = woodPC;
    }
}
