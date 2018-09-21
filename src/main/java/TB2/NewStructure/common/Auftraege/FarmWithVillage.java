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

    private EigenesDorf own;


    public List<Barbarendorf> getBabas() {
        return barbarendorfDao.findAll();
    }

    private BarbarendorfDao barbarendorfDao;

    public FarmWithVillage(EigenesDorf own, BarbarendorfDao barbarendorfDao) throws NoElementTextFound, ElementisNotClickable {
        this.own = own;
        this.barbarendorfDao = barbarendorfDao;
        run();
    }

    @Override
    public void run() throws NoElementTextFound, ElementisNotClickable {
        if (own.isBlockAttacks() || own.getFarableUnits() == null) return;

        new SelectOwnVillage(own);

        List<Barbarendorf> farmableBarb = getBabas().stream().filter(x -> x.isFarmable(own)).sorted(Comparator.comparingInt(o -> new DistanceCalculator(o, own).getDistance())).collect(Collectors.toList());
        if (farmableBarb.size() <= 0)
            return;


        logger.info("Farme mit: " + own.getName());

        AnzahlBisherigeAngriffe attackCountTask = new AnzahlBisherigeAngriffe(own);
        int remainingAttacks = 50 - attackCountTask.getAnzahlAngriffe();
        if (remainingAttacks >= 0) {

            GetUnitsFormOwnVillage unitsTask = new GetUnitsFormOwnVillage(own, own.getFarableUnits());
            HashMap<Units, Integer> units = unitsTask.getUnits();

            int proviantKavellerie = 0;
            int proviantRest = 0;

            //Berechne Proviant der Armee
            for (Map.Entry<Units, Integer> entry : units.entrySet()) {
                Units key = entry.getKey();
                int value = entry.getValue();

                switch (key) {
                    case SPEER: {
                        if (own.getFarableUnits().contains(Units.SPEER))
                            proviantRest += value * new Speerkaempfer().getProviant();
                        break;
                    }
                    case SCHWERT: {
                        if (own.getFarableUnits().contains(Units.SCHWERT))
                            proviantRest += value * new Schwertkaempfer().getProviant();
                        break;
                    }
                    case AXT: {
                        if (own.getFarableUnits().contains(Units.AXT))
                            proviantRest += value * new Axtkaempfer().getProviant();
                        break;
                    }
                    case BOGEN: {
                        if (own.getFarableUnits().contains(Units.BOGEN))
                            proviantRest += value * new Bogenschuetze().getProviant();
                        break;
                    }
                    case LKAV: {
                        if (own.getFarableUnits().contains(Units.LKAV))
                            proviantKavellerie += value * new LeichteKavallerie().getProviant();
                        break;
                    }
                    case BERITTINER_BOGEN: {
                        if (own.getFarableUnits().contains(Units.BERITTINER_BOGEN))
                            proviantKavellerie += value * new BerittenerBogen().getProviant();
                        break;
                    }
                    case SKAV: {
                        if (own.getFarableUnits().contains(Units.SKAV))
                            proviantKavellerie += value * new SchwereKavalerie().getProviant();
                        break;
                    }
                    case RAMMEN: {
                        if (own.getFarableUnits().contains(Units.RAMMEN))
                            proviantRest += value * new Rammbock().getProviant();
                        break;
                    }
                    case KATAPULT: {
                        if (own.getFarableUnits().contains(Units.KATAPULT))
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

                if (attacksForKav > 0 && proviantKavellerie > 0) {
                    HashMap<Units, Integer> kavUnits = new HashMap<>();
                    if (units.containsKey(Units.LKAV)) {
                        kavUnits.put(Units.LKAV, units.get(Units.LKAV) / attacksForKav);
                        if ((units.get(Units.LKAV) / attacksForKav) < 20)
                            kavUnits.put(Units.LKAV, 20);
                    }

                    if (units.containsKey(Units.SKAV)) {
                        kavUnits.put(Units.SKAV, units.get(Units.SKAV) / attacksForKav);
                        if ((units.get(Units.SKAV) / attacksForKav) < 20)
                            kavUnits.put(Units.SKAV, 20);
                    }

                    if (units.containsKey(Units.BERITTINER_BOGEN)) {
                        kavUnits.put(Units.BERITTINER_BOGEN, units.get(Units.BERITTINER_BOGEN) / attacksForKav);
                        if ((units.get(Units.BERITTINER_BOGEN) / attacksForKav) < 20)
                            kavUnits.put(Units.BERITTINER_BOGEN, 20);
                    }

                    new InitVorlagen(own, kavUnits);
                    remainingAttacks -= farmBabas(attacksForKav, true);


                }

                // farme mit Restruppen
                logger.info("Angriffe mit Resttruppen: " + remainingAttacks);
                if (remainingAttacks > 0 && proviantRest > 0) {
                    HashMap<Units, Integer> restUnits = new HashMap<>();
                    if (units.containsKey(Units.SPEER)) {
                        restUnits.put(Units.SPEER, units.get(Units.SPEER) / remainingAttacks);
                        if ((units.get(Units.SPEER) / remainingAttacks) < 200)
                            restUnits.put(Units.SPEER, 200);
                    }
                    if (units.containsKey(Units.AXT)) {
                        restUnits.put(Units.AXT, units.get(Units.AXT) / remainingAttacks);
                        if ((units.get(Units.AXT) / remainingAttacks) < 120)
                            restUnits.put(Units.AXT, 120);
                    }
                    new InitVorlagen(own, restUnits);
                    remainingAttacks -= farmBabas(remainingAttacks, false);
                }
                logger.info("Verbleibende Angriffe: " + remainingAttacks);
            }

        }
    }


    private int farmBabas(int counter, boolean isKav) throws ElementisNotClickable, NoElementTextFound {

        int counterStart = counter;
        logger.info("Barbarendörferanzahl:" + barbarendorfDao.findAll().size());

        List<Barbarendorf> farmableBarb = getBabas().stream().filter(x -> x.isFarmable(own)).sorted(Comparator.comparingInt(o -> new DistanceCalculator(o, own).getDistance())).collect(Collectors.toList());
        logger.info("Barbarendörferanzahl die Vorraussetzungen erfüllen:" + farmableBarb.size());

        for (Barbarendorf dorf : farmableBarb) {
            if (counter <= 0) {
                return counterStart;
            }

            new EnterKoordinaten(dorf);

            if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(5))) {
                if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent()) {
                    // einheiten bevor dem Angriff
//                    int lkavBEFORE = Integer.parseInt(MainToolbar.ANZAHL_LKAV.getText().replace(".", ""));
//                    int bkavBEFORE = Integer.parseInt(MainToolbar.ANZAHL_BERITTENER_BOGEN.getText().replace(".", ""));
//                    int skavBEFORE = Integer.parseInt(MainToolbar.ANZAHL_SKAV.getText().replace(".", ""));
//
//                    int speerBEFORE = Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", ""));
//                    int axtBEFORE = Integer.parseInt(MainToolbar.ANZAHL_AXT.getText().replace(".", ""));


                    MainToolbar.OBERFLAECHE.sendText(1);
                    if (Main.account.isWoodPC()) {
                        if (!MainToolbar.ERROR_50_ANGRIFFE.isNOTPresent(Duration.ofMillis(2000))) {
                            logger.info("Keine Truppen mehr vorhanden!");
                            return counter;
                        }
                    }

//                    long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(20);

//                    if (isKav) {
//                        int lkavAFTER = Integer.parseInt(MainToolbar.ANZAHL_LKAV.getText().replace(".", ""));
//                        int bkavAFTER = Integer.parseInt(MainToolbar.ANZAHL_BERITTENER_BOGEN.getText().replace(".", ""));
//                        int skavAFTER = Integer.parseInt(MainToolbar.ANZAHL_SKAV.getText().replace(".", ""));
//
//                        for (; lkavBEFORE == lkavAFTER && bkavBEFORE == bkavAFTER && skavBEFORE == skavAFTER && stop > System.nanoTime(); ) {
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    } else {
//                        int speerAFTER = Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", ""));
//                        int axtAFTER = Integer.parseInt(MainToolbar.ANZAHL_AXT.getText().replace(".", ""));
//                        for (; speerBEFORE == speerAFTER && axtBEFORE == axtAFTER && stop > System.nanoTime(); ) {
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }


                    counter--;

                    // Barbarendorf updaten
                    Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                    tmp.setAttackedAt(LocalDateTime.now());
                    barbarendorfDao.save(tmp);

                    if (!MainToolbar.ERROR_50_ANGRIFFE.isNOTPresent(Duration.ofMillis(1000))) {
                        logger.info("Keine Truppen mehr vorhanden!");
                        return counter;
                    }
                } else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent()) {
                    Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                    if (checkTwice(tmp)) {
                        barbarendorfDao.delete(tmp);
                        logger.info("Lösche Barbarendorf(Spieler)");
                    }

                } else if (Dorfoptionen.ACTIVE_VILLAGE.isPresent()) {
                    Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                    if (checkTwice(tmp)) {
                        barbarendorfDao.delete(tmp);
                        logger.info("Lösche Barbarendorf(Eigenes Dorf)");
                    }

                }

            } else {
                Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
                if (checkTwice(tmp)) {
                    barbarendorfDao.delete(tmp);
                    logger.info("Lösche Barbarendorf, Dorf nicht vorhanden!!!");
                }


            }
        }

        if (counter > 0) {
            return counter;
        } else {
            return counterStart;
        }
    }

    private boolean checkTwice(Barbarendorf tmp) throws ElementisNotClickable {

        Main.restartDriver();

        new EnterKoordinaten(tmp);

        if (!Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(7)))
            return true;

        return false;

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
