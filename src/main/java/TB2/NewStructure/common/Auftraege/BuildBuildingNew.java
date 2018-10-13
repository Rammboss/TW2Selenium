package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.*;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collection;

public class BuildBuildingNew implements AuftragInterface {


    public BuildBuildingNew() throws NoElementTextFound, ElementisNotClickable {
        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {

        MainToolbar.UEBERSICHTEN.click();
        Uebersichten.DOERFER.isPresent(Duration.ofSeconds(5));
        Uebersichten.DOERFER.click();

        if (UebersichtenDoerfer.BUILD_STATUS.getCSSClass().equals("switch switch-56x28 switch-horizontal")) {
            UebersichtenDoerfer.BUILD_STATUS.click();
        }

        WebElement element = UebersichtenDoerfer.TABLE_OWN_WILLAGES.getWebelement(Main.driver);

        Collection<WebElement> trs = element.findElements(By.tagName("tr"));
        String name = "";
        int punkte = 0;
        int x = 0;
        int y = 0;

        for (WebElement e : trs) {
            Collection<WebElement> tds = e.findElements(By.tagName("td"));

            //scroll element into view
            ((JavascriptExecutor) Main.getDriver()).executeScript("arguments[0].scrollIntoView({block: \"start\", behavior: \"instant\"});", e);

//            new FluentWait<>(Main.getDriver())
//                    .withTimeout(Duration.ofMillis(1000)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(e));


            int counter = 0;

            for (WebElement td : tds) {
                counter++;

                switch (counter) {
                    case 1: {

                        System.out.println(td.getText());

                        break;
                    }
                    case 2: {
                        System.out.println(td.getText());

                        break;
                    }
                    case 3: {
                        System.out.println(td.getText());

                        break;
                    }
                    case 4: {
                        System.out.println(td.getText());

                        break;
                    }
                    default: {
                        System.out.println(td.getText());
                    }
                }


            }
        }


    }


    @Override
    public boolean check() throws NoElementTextFound {
        return false;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public void setStartTime(LocalTime time) {

    }

    @Override
    public LocalTime getStartTime() {
        return null;
    }

    @Override
    public void getAvgRunTime() {

    }
}
