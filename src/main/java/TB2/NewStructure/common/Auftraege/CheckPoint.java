package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfinformationen;
import TB2.NewStructure.common.Menus.Dorfoptionen;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Provinzansicht;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.dao.*;
import TB2.NewStructure.common.hibernate.model.*;
import TB2.TB2.Account;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class CheckPoint implements AuftragInterface {

    private static Logger logger = LoggerFactory.getLogger(CheckPoint.class);

    private Point point;

    private BarbarendorfDao barbarendorfDao;

    private ProvinzDao provinzDao;

    private PointDao pointDao;

    private EigenesDorfDao eigenesDorfDao;

    private DorfDao dorfDao;

    private Account account;


    public CheckPoint(Point point, BarbarendorfDao barbarendorfDao, ProvinzDao provinzDao, PointDao pointDao, EigenesDorfDao eigenesDorfDao, DorfDao dorfDao, Account account) throws NoElementTextFound, ElementisNotClickable {
        this.point = point;
        this.barbarendorfDao = barbarendorfDao;
        this.provinzDao = provinzDao;
        this.pointDao = pointDao;
        this.eigenesDorfDao = eigenesDorfDao;
        this.dorfDao = dorfDao;
        this.account = account;

        run();
    }

    @Override
    public void run() throws ElementisNotClickable, NoElementTextFound {
        new EnterKoordinaten(point);

        if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Rohstofflager")
                && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Freund einladen")) {

            String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

            if (Dorfoptionen.DORFINFORMATIONEN.isPresent(Duration.ofSeconds(2)) && Dorfoptionen.GRUPPEN_HINZUFUEGEN.isPresent(Duration.ofSeconds(2))
                    && !Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

                Dorfoptionen.DORFINFORMATIONEN.click();

                if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
                    int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                    Optional<EigenesDorf> eigenAltOptional = eigenesDorfDao.findByXAndY(point.getX(), point.getY());

                    eigenAltOptional.ifPresentOrElse(x -> {
                        x.setPunkte(dorfpunkte);
                        x.setName(dorfname);
                        eigenesDorfDao.save(x);
                        logger.info("Eigenes Dorf geupdated");
                    }, () -> {
                        eigenesDorfDao.save(new EigenesDorf(point.getX(), point.getY(), dorfname, dorfpunkte, account.getSpielername()));
                        logger.info("Eigenes Dorf hinzugefügt");
                    });
                }

                MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

            } else if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

                Dorfoptionen.DORFINFORMATIONEN.click();

                if (Dorfinformationen.DORFINFORMATIONEN_PUNKTE.isPresent()) {

                    int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                    Optional<Barbarendorf> eigenALT = barbarendorfDao.findByXAndY(point.getX(), point.getY());

                    eigenALT.ifPresentOrElse(x -> {
                        x.setPunkte(dorfpunkte);
                        x.setName(dorfname);
                        barbarendorfDao.save(x);
                        logger.info("Babarendorf updated");
                    }, () -> {
                        barbarendorfDao.save(new Barbarendorf(point.getX(), point.getY(), dorfname, dorfpunkte, LocalDateTime.now()));
                        logger.info("Barbarendorf hinzugefügt");
                    });
                }

                MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

            } else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2))) {


                Dorfoptionen.DORFINFORMATIONEN.click();

                if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
                    int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

                    Optional<Dorf> eigenALT = dorfDao.findByXAndY(point.getX(), point.getY());

                    eigenALT.ifPresentOrElse(x -> {
                        x.setPunkte(dorfpunkte);
                        x.setName(dorfname);
                        dorfDao.save(x);
                        logger.info("Dorf Gegner updated");
                    }, () -> {
                        dorfDao.save(new Dorf(point.getX(), point.getY(), dorfname, dorfpunkte));
                        logger.info(" Dorf Gegner hinzugefügt");
                    });
                }

                MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

            }
        } else {
            MainToolbar.OBERFLAECHE.clickCoords(0, 0);

            if (Provinzansicht.PROVINZ_BUTTON_PROVINZDOERVER.isPresent()) {

                Optional<Provinz> eigenALT = provinzDao.findByXAndY(point.getX(), point.getY());

                String provinzName = Provinzansicht.PROVINZ_NAME.getText();

                eigenALT.ifPresentOrElse(x -> {
                    x.setName(provinzName);
                    provinzDao.save(x);
                    logger.info("Provinz updated");
                }, () -> {
                    provinzDao.save(new Provinz(point.getX(), point.getY(), provinzName, LocalDateTime.now()));
                    logger.info("Provinz hinzugefügt");
                });

                MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

            }


        }
        point.setChecked(true);
        point.setCheckedAt(LocalDateTime.now());

        pointDao.save(point);

        logger.info("Update:" + point);

    }

    @Override
    public boolean check() {
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
