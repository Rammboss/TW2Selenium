package TB2.NewStructure.common.Auftraege;

import TB2.NewStructure.common.Menus.Dorfoptionen;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.dao.BarbarendorfDao;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.DistanceCalculator;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.units.*;
import TB2.TB2.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FarmWithVillage implements AuftragInterface {
    private static Logger logger = LoggerFactory.getLogger(FarmWithVillage.class);

    private List<Units> units;

    private EigenesDorf own;

    private List<Barbarendorf> babas;

    public List<Barbarendorf> getBabas() {
        return barbarendorfDao.findAll();
    }

    public void setBabas(List<Barbarendorf> babas) {
        this.babas = babas;
    }

    private BarbarendorfDao barbarendorfDao;

    public FarmWithVillage(List<Units> units, EigenesDorf own, List<Barbarendorf> babas, BarbarendorfDao barbarendorfDao) throws NoElementTextFound, ElementisNotClickable {
        this.units = units;
        this.own = own;
        this.babas = babas;
        this.barbarendorfDao = barbarendorfDao;
        run();
    }

    @Override
    public void run() throws NoElementTextFound, ElementisNotClickable {
        new SelectOwnVillage(own);

        AnzahlBisherigeAngriffe attackCountTask = new AnzahlBisherigeAngriffe(own);
        int remainingAttacks = 50 - attackCountTask.getAnzahlAngriffe();

        GetUnitsFormOwnVillage unitsTask = new GetUnitsFormOwnVillage(own);
        HashMap<Units, Integer> units = unitsTask.getUnits();

        int proviantKavellerie = 0;
        int proviantRest = 0;

        //Berechne Proviant der Armee
        for (Map.Entry<Units, Integer> entry : units.entrySet()) {
            Units key = entry.getKey();
            int value = entry.getValue();

            switch (key) {
                case SPEER: {
                    proviantRest += value * new Speerkaempfer().getProviant();
                    break;
                }
                case SCHWERT: {
                    proviantRest += value * new Schwertkaempfer().getProviant();
                    break;
                }
                case AXT: {
                    proviantRest += value * new Axtkaempfer().getProviant();
                    break;
                }
                case BOGEN: {
                    proviantRest += value * new Bogenschuetze().getProviant();
                    break;
                }
                case LKAV: {
                    proviantKavellerie += value * new LeichteKavallerie().getProviant();
                    break;
                }
                case BERITTINER_BOGEN: {
                    proviantKavellerie += value * new BerittenerBogen().getProviant();
                    break;
                }
                case SKAV: {
                    proviantKavellerie += value * new SchwereKavalerie().getProviant();
                    break;
                }
                case RAMMEN: {
                    proviantRest += value * new Rammbock().getProviant();
                    break;
                }
                case KATAPULT: {
                    proviantRest += value * new Katapult().getProviant();
                    break;
                }
                case BERSERKER: {
                    break;
                }
                case TREBUCHET: {
                    break;
                }
                case ADELSGESCHLECHT: {
                    break;
                }
                case PALADIN: {
                    break;
                }
                default: {
                    throw new IndexOutOfBoundsException();
                }
            }


        }
        //farme mit Kavallerie
        if ((proviantRest + proviantKavellerie) > 0) {

            int attacksForKav = (int) (((double) proviantKavellerie / (proviantRest + proviantKavellerie)) * remainingAttacks);

            logger.info("Angriffe mit Kavallerie: " + attacksForKav);

            if (attacksForKav > 0) {
                HashMap<Units, Integer> kavUnits = new HashMap<>();

                kavUnits.put(Units.LKAV, units.get(Units.LKAV) / attacksForKav);
                if ((units.get(Units.LKAV) / attacksForKav) < 20)
                    kavUnits.put(Units.LKAV, 20);

                kavUnits.put(Units.SKAV, units.get(Units.SKAV) / attacksForKav);
                if ((units.get(Units.SKAV) / attacksForKav) < 20)
                    kavUnits.put(Units.SKAV, 20);

                kavUnits.put(Units.BERITTINER_BOGEN, units.get(Units.BERITTINER_BOGEN) / attacksForKav);
                if ((units.get(Units.BERITTINER_BOGEN) / attacksForKav) < 20)
                    kavUnits.put(Units.BERITTINER_BOGEN, 20);

                new InitVorlagen(own, kavUnits);
                remainingAttacks -= farmBabas(attacksForKav);


            }

            // farme mit Restruppen
            logger.info("Angriffe mit Resttruppen: " + remainingAttacks);
            if (remainingAttacks > 0) {
                HashMap<Units, Integer> restUnits = new HashMap<>();

                restUnits.put(Units.SPEER, units.get(Units.SPEER) / remainingAttacks);
                if ((units.get(Units.SPEER) / remainingAttacks) < 100)
                    restUnits.put(Units.SPEER, 100);

                restUnits.put(Units.AXT, units.get(Units.AXT) / remainingAttacks);
                if ((units.get(Units.AXT) / remainingAttacks) < 100)
                    restUnits.put(Units.AXT, 100);

                new InitVorlagen(own, restUnits);
                remainingAttacks -= farmBabas(remainingAttacks);
            }
            logger.info("Verbleibende Angriffe: " + remainingAttacks);
        }


    }

    /**
     * @param counter
     * @return den StartCounter, wenn alles gefarmt wurde. den restlichen Counter, wenn er nicht bis zum Schluss farmt
     * @throws ElementisNotClickable
     */
    public int farmBabas(int counter) throws ElementisNotClickable {

        int counterStart = counter;

        List<Barbarendorf> farmableBarb = getBabas().stream().filter(x -> x.isFarmable(own)).sorted(Comparator.comparingInt(o -> new DistanceCalculator(o, own).getDistance())).collect(Collectors.toList());

        for (Barbarendorf dorf : farmableBarb) {
            if (counter <= 0) {
                return counterStart;
            }
            if (MainToolbar.ERROR_50_ANGRIFFE.isPresent(Duration.ofMillis(600))) {
                return counter;
            }
            new EnterKoordinaten(dorf);

            if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(5))) {
                if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent()) {
                    Main.sleep(1);
                    MainToolbar.OBERFLAECHE.sendText(1);
                    counter--;

                    // Barbarendorf updaten
                    Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                    tmp.setAttackedAt(LocalDateTime.now());
                    barbarendorfDao.save(tmp);
                } else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent()) {
                    Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                    barbarendorfDao.delete(tmp);
                    logger.info("Lösche Barbarendorf(Spieler)");
                }

            } else {
                Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                barbarendorfDao.delete(tmp);
                logger.info("Lösche Barbarendorf, Dorf nicht vorhanden!!!");

            }
        }

        if (counter > 0) {
            return counter;
        } else {
            return counterStart;
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
