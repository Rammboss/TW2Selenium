package TB2.TB2;

import TB2.NewStructure.common.Menus.Login;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;

import java.time.Duration;

public class Account {

    public static int performaceMultiplier;

    private String spielername;

    private String passwort;

    private String welt;

    public Account(int performaceMultiplier, String spielername, String passwort, String welt) {
        super();
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
}
