package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.PlayerProfil;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.DistanceCalculator;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.TB2.Account;
import TB2.TB2.Main;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class GetOwnVillages implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(GetOwnVillages.class);

    private List<EigenesDorf> ownVillages;

    private Account acc;

    public GetOwnVillages(Account acc) throws ElementisNotClickable {
        this.acc = acc;
        this.ownVillages = new ArrayList<>();

        run();
    }

    @Override
    public void run() throws ElementisNotClickable {


        MainToolbar.PROFIL.click();
        PlayerProfil.PLAYERNAME.isPresent(Duration.ofMinutes(1));
        PlayerProfil.TABLE_OWN_VILLAGES.isPresent();
        WebElement element = PlayerProfil.TABLE_OWN_VILLAGES.getWebelement(Main.driver);

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

                        name = td.getText();

                        break;
                    }
                    case 2: {
                        punkte = Integer.parseInt(td.getText().replace(".", ""));

                        break;
                    }
                    case 3: {

                        x = Integer.parseInt(td.getText().split(" \\| ")[0]);
                        y = Integer.parseInt(td.getText().split(" \\| ")[1]);
                        getOwnVillages().add(new EigenesDorf(x, y, name, punkte, getAcc().getSpielername()));

                        break;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        throw new IndexOutOfBoundsException();
                    }
                }


            }

        }
        // um fenster wieder normal zu scrollen
        PlayerProfil.PLAYERNAME.scrollToElement("end");

        MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);


    }


    @Override
    public boolean check() throws NoElementTextFound {
        return true;
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

    public List<EigenesDorf> getOwnVillages() {
        ownVillages.stream().sorted(Comparator.comparingInt(EigenesDorf::getPunkte)).collect(Collectors.toList());
        return ownVillages;
    }

    public void setOwnVillages(List<EigenesDorf> ownVillages) {
        this.ownVillages = ownVillages;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
}
