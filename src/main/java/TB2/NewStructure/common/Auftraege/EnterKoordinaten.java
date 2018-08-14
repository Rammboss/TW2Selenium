package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfoptionen;
import TB2.NewStructure.common.Menus.Koordinaten;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.KoordinatenInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class EnterKoordinaten implements AuftragInterface {

    private static Logger logger = LoggerFactory.getLogger(EnterKoordinaten.class);

    private int priority;
    private LocalTime startTime;
    private KoordinatenInterface point;

    private static long avgRuntimeSEC;
    private static int successfulRuns;
    private static int failedRuns;

    public EnterKoordinaten(KoordinatenInterface point) throws ElementisNotClickable {

        this.priority = 1;
        this.point = point;
        this.startTime = LocalTime.now();

        run();

    }

    public void run() throws ElementisNotClickable {

        if (Koordinaten.X_KOORDINATE.isNOTPresent(Duration.ofMillis(500)) & Koordinaten.Y_KOORDINATE.isNOTPresent(Duration.ofMillis(500))) {
            MainToolbar.AUF_WELTKARTE_SUCHEN.click();
        }
        if (Koordinaten.X_KOORDINATE.isPresent() && Koordinaten.Y_KOORDINATE.isPresent() && Koordinaten.JUMP_TO_VILLAGE.isPresent()){
            Koordinaten.X_KOORDINATE.clear();
            Koordinaten.X_KOORDINATE.sendText(point.getX());
            Koordinaten.Y_KOORDINATE.clear();
            Koordinaten.Y_KOORDINATE.sendText(point.getY());
            Koordinaten.JUMP_TO_VILLAGE.click();
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
        if (Dorfoptionen.MENUE_MITTE.isPresent() && Dorfoptionen.MENUE_MITTE.getText().equals(point.getName())) {
            EnterKoordinaten.successfulRuns++;
            EnterKoordinaten.avgRuntimeSEC += Duration.between(getStartTime(), LocalTime.now()).toSeconds() / successfulRuns;
            return true;
        } else {
            EnterKoordinaten.failedRuns++;
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
        int day = (int) TimeUnit.SECONDS.toDays(EnterKoordinaten.avgRuntimeSEC);
        long hours = TimeUnit.SECONDS.toHours(EnterKoordinaten.avgRuntimeSEC) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(EnterKoordinaten.avgRuntimeSEC) - (TimeUnit.SECONDS.toHours(EnterKoordinaten.avgRuntimeSEC) * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(EnterKoordinaten.avgRuntimeSEC) - (TimeUnit.SECONDS.toMinutes(EnterKoordinaten.avgRuntimeSEC) * 60);

        logger.info("Durchschnittliche Laufzeit(h:m:s): " + hours + ":" + minute + ":" + seconds);
    }

    public void getFailedRuns() {
        logger.info("Auftrag: " + EnterKoordinaten.class.getSimpleName() + " ist " + EnterKoordinaten.failedRuns + "fehlgeschlagen!");
    }
}
