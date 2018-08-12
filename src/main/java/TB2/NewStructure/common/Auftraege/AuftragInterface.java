package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;


public interface AuftragInterface {

    void run() throws ElementisNotClickable, NoElementTextFound;

    boolean check() throws NoElementTextFound;

    int getPriority();

    void setPriority(int priority);

    void setStartTime(LocalTime time);

    LocalTime getStartTime();

    void getAvgRunTime();


}
