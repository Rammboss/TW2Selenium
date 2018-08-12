package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfoptionen;
import TB2.NewStructure.common.Menus.Koordinaten;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.KoordinatenInterface;
import TB2.TB2.Main;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class SelectOwnVillage implements AuftragInterface {

    private static Logger logger = LoggerFactory.getLogger(SelectOwnVillage.class);

    private int priority;
    private LocalTime startTime;
    private KoordinatenInterface own;

    private static long avgRuntimeSEC;
    private static int successfulRuns;
    private static int failedRuns;

    public SelectOwnVillage(EigenesDorf own) throws NoElementTextFound, ElementisNotClickable {

        this.priority = 1;
        this.own = own;
        this.startTime = LocalTime.now();

        run();

    }

    public void run() throws ElementisNotClickable, NoElementTextFound {
        String tmp = MainToolbar.CURRENT_VILLAGE_KOORDS.getText().replace("(", "").replace(")", "");
        String[] coords = tmp.split("\\|");
        String currentName = MainToolbar.CURRENT_VILLAGE_NAME.getText();

        if (!currentName.equals(own.getName()) || !coords[0].equals("" + own.getX()) || !coords[1].equals("" + own.getY())) {
            new EnterKoordinaten(own);

            Dorfoptionen.ACTIVE_VILLAGE.click(Duration.ofSeconds(10));
        }


    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int compareTo(AuftragInterface ai) {
        return ai.getPriority() - this.priority;
    }

    public boolean check() throws NoElementTextFound {
        String[] coords = MainToolbar.CURRENT_VILLAGE_KOORDS.getText().replace("(", "").replace(")", "").split("|");
        if (MainToolbar.CURRENT_VILLAGE_NAME.equals(own.getName()) && coords[0].equals(own.getX()) && coords[1].equals(own.getY())) {
            SelectOwnVillage.successfulRuns++;
            SelectOwnVillage.avgRuntimeSEC += Duration.between(getStartTime(), LocalTime.now()).toSeconds() / successfulRuns;
            return true;
        } else {
            SelectOwnVillage.failedRuns++;
            return false;
        }

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void getAvgRunTime() {
        int day = (int) TimeUnit.SECONDS.toDays(SelectOwnVillage.avgRuntimeSEC);
        long hours = TimeUnit.SECONDS.toHours(SelectOwnVillage.avgRuntimeSEC) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(SelectOwnVillage.avgRuntimeSEC) - (TimeUnit.SECONDS.toHours(SelectOwnVillage.avgRuntimeSEC) * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(SelectOwnVillage.avgRuntimeSEC) - (TimeUnit.SECONDS.toMinutes(SelectOwnVillage.avgRuntimeSEC) * 60);

        logger.info("Durchschnittliche Laufzeit(h:m:s): " + hours + ":" + minute + ":" + seconds);

    }
}
