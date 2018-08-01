package TB2.TB2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import TB2.NewStructure.common.Menus.Dorfansicht;
import TB2.NewStructure.common.Menus.Dorfinformationen;
import TB2.NewStructure.common.Menus.Dorfoptionen;
import TB2.NewStructure.common.Menus.Einheiten;
import TB2.NewStructure.common.Menus.Einstellungen;
import TB2.NewStructure.common.Menus.EinstellungenSubSpiel;
import TB2.NewStructure.common.Menus.Kaserne;
import TB2.NewStructure.common.Menus.Koordinaten;
import TB2.NewStructure.common.Menus.Login;
import TB2.NewStructure.common.Menus.MainToolbar;
import TB2.NewStructure.common.Menus.Provinzansicht;
import TB2.NewStructure.common.Menus.Rohstofflager;
import TB2.NewStructure.common.Menus.Sammelplatz;
import TB2.NewStructure.common.Menus.Speicher;
import TB2.NewStructure.common.Menus.UebersichtVorlangenliste;
import TB2.NewStructure.common.Menus.VorlangeErstellenOderAendern;
import TB2.NewStructure.common.exceptions.ElementisNotClickable;
import TB2.NewStructure.common.exceptions.NoElementTextFound;
import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.dao.BarbarendorfDao;
import TB2.NewStructure.common.hibernate.dao.DorfDao;
import TB2.NewStructure.common.hibernate.dao.EigenesDorfDao;
import TB2.NewStructure.common.hibernate.dao.PointDao;
import TB2.NewStructure.common.hibernate.dao.ProvinzDao;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Point;
import TB2.NewStructure.common.hibernate.model.Provinz;

@Component
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	static {
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

		account = new Account("Rammboss", "kalterhund", "Gaillard", new EigenesDorf(583, 567, "A005", 2088, "Rammboss"));
		// account = new Account("DerZurecker", "aleyotmi1", "Gaillard");
		// account = new Account("Don Porro", "Kacklappen", "Gaillard");
	}

	public static WebDriver driver;

	public static Account account;

	public static int index;

	public static List<EigenesDorf> eigene;

	public List<Barbarendorf> babas;

	@Autowired
	private DorfDao dorfDao;

	@Autowired
	private PointDao pointDao;

	@Autowired
	private ProvinzDao provinzDao;

	@Autowired
	private EigenesDorfDao eigenesDorfDao;

	@Autowired
	private BarbarendorfDao barbarendorfDao;

	public Main() {
		Main.index = 0;
	}

	public static boolean isodd(int value) {
		return value % 2 != 0;
	}

	public static int getDistance(int x1, int x2, int y1, int y2) {
		double z1 = isodd(y1) ? x1 + 0.5 : x1 - 0.5;
		double z2 = isodd(y2) ? x2 + 0.5 : x2 - 0.5;

		double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(y1 - y2, 2));
		double d2 = Math.sqrt(Math.pow((x1 - x2), 2) + 0.75 * Math.pow((y1 - y2), 2));
		int erg = (int) ((d1 + d2) / 2 * 10000);
		return erg;
	}

	public List<Point> checkAndInitPoints() {
		List<Point> pointlist = new ArrayList<Point>();

		if (pointDao.count() == 0) {
			for (int x = 0; x <= 1000; x++) {

				for (int y = 0; y <= 1000; y++) {
					pointlist.add(new Point(x, y, false));
				}

			}

			logger.info("Speichere Points....");
			pointDao.saveAll(pointlist);
		} else {
			pointlist = pointDao.findAll();
		}

		return pointlist;
	}

	public void login() throws ElementisNotClickable {

		Login.SPIELERNAME.sendText(account.getSpielername());
		Login.PASSWORT.sendText(account.getPasswort());
		Login.SPIELEN.click();

		Login.LOGIN.setCriteria(account.getWelt());

		if (Login.LOGIN.isPresent(Duration.ofSeconds(30))) {
			sleep(1);
			Login.LOGIN.click();
		}

		sleep(1);

		Login.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));

		sleep(2);

	}

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Main app = context.getBean(Main.class);

		while (true) {
			try {
				app.restartDriver();
				app.runTask(app.checkAndInitPoints());
			} catch (Throwable e) {
				logger.info("Ein unerwarteter Fehler ist aufgetreten! Treiber wird in 5 sekunden neu gestartet!");

				context.close();
				context = new AnnotationConfigApplicationContext(AppConfig.class);
				app = context.getBean(Main.class);

				logger.error("main error", e);
			}
		}
	}

	private void runTask(List<Point> points) throws ElementisNotClickable, NumberFormatException, NoElementTextFound {

		if (MainToolbar.BELOHNUNG_ANNEHMEN.isPresent(Duration.ofSeconds(3))) {
			MainToolbar.BELOHNUNG_ANNEHMEN.click();
			sleep(5);

		}

		this.disableSound();

		Main.eigene = eigenesDorfDao.findBySpieler(account.getSpielername());
		List<Dorf> dorfListe = dorfDao.findAll();
		List<Barbarendorf> barbarendoerfer = barbarendorfDao.findAll();
		if (Main.eigene.size() != 0) {
			account.setErstesDorf(Main.eigene.get(0));

		}

		if (Main.eigene.size() == 0 || dorfListe.size() == 0) {
			ausgehendenAngriffeVerbergen();

			Main.eigene = eigenesDorfDao.findBySpieler(account.getSpielername());
			dorfListe = dorfDao.findAll();
			barbarendoerfer = barbarendorfDao.findAll();

			ausgehendenAngriffeVerbergen();

		}
		if (Main.eigene.size() != 0) {
			if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
				MainToolbar.AUF_WELTKARTE_SUCHEN.click();
			Koordinaten.X_KOORDINATE.clear();
			Koordinaten.X_KOORDINATE.sendText(Main.eigene.get(0).getX());
			Koordinaten.Y_KOORDINATE.clear();
			Koordinaten.Y_KOORDINATE.sendText(Main.eigene.get(0).getY());
			Koordinaten.JUMP_TO.click();
		}

		if (Dorfoptionen.ACTIVE_VILLAGE.isPresent(Duration.ofSeconds(5))) {
			Dorfoptionen.ACTIVE_VILLAGE.click();

		}

		// Angriff timen
		// if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
		// MainToolbar.AUF_WELTKARTE_SUCHEN.click();
		//
		// Koordinaten.X_KOORDINATE.clear();
		// Koordinaten.X_KOORDINATE.sendText(590);
		// Koordinaten.Y_KOORDINATE.clear();
		// Koordinaten.Y_KOORDINATE.sendText(557);
		// Koordinaten.JUMP_TO.click();
		//
		// Dorfoptionen.TRUPPEN_SCHICKEN.click(2);
		//
		// List<WebElement> list =
		// TruppenSchicken.EINHEITEN_TABELLE.getWebelement().findElements(By.tagName("li"));
		//
		// for (WebElement webElement : list) {
		// if (webElement.getAttribute("innerHTML").contains("Axtkämpfer")) {
		//
		// List<WebElement> axt = webElement.findElements(By.tagName("input"));
		// axt.get(1).click();
		// axt.get(1).sendKeys("1000");
		// }
		// }
		//
		// MainToolbar.AUF_WELTKARTE_SUCHEN.click();
		//
		// Koordinaten.X_KOORDINATE.clear();
		// Koordinaten.X_KOORDINATE.sendText(584);
		// Koordinaten.Y_KOORDINATE.clear();
		// Koordinaten.Y_KOORDINATE.sendText(568);
		// Koordinaten.JUMP_TO.click();
		// sleep(1);
		//
		// MainToolbar.OBERFLAECHE.clickCoords(0, 0);

		// Befehle wieder anzeigen
		babas = barbarendoerfer;

		while (true) {

			barbarendoerfer = barbarendorfDao.findAll();

			rohstoffeCheckenUndAxtBauen();

			initVorlagen(this.getAnzahlAngriffe());

			rohstofflagerCheck(false);

			// Koordinaten eingen
			MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

			if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
				MainToolbar.AUF_WELTKARTE_SUCHEN.click();
			if (Main.eigene.size() != 0) {

				final AtomicInteger counter = new AtomicInteger();

				List<Barbarendorf> farmableBarb = babas.stream().filter(x -> x.isFarmable(eigene.get(counter.get()))).collect(Collectors.toList());

				for (Barbarendorf dorf : farmableBarb) {
					Koordinaten.X_KOORDINATE.clear();
					Koordinaten.X_KOORDINATE.sendText(dorf.getX());
					Koordinaten.Y_KOORDINATE.clear();
					Koordinaten.Y_KOORDINATE.sendText(dorf.getY());
					Koordinaten.JUMP_TO.click();

					if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2)) && dorf.isFarmable(Main.eigene.get(counter.get()))) {
						sleep(1);
						MainToolbar.OBERFLAECHE.sendText(1);

						if (MainToolbar.ERROR_50_ANGRIFFE.isPresent(Duration.ofMillis(500))) {
							sleep(5); // instead of click on message, because causes errors

							if (counter.get() >= Main.eigene.size() - 1) {
								break;
							} else {

								// Eigenes Dorf wechseln
								counter.incrementAndGet();
								if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
									MainToolbar.AUF_WELTKARTE_SUCHEN.click();
								Koordinaten.X_KOORDINATE.clear();
								Koordinaten.X_KOORDINATE.sendText(Main.eigene.get(counter.get()).getX());
								Koordinaten.Y_KOORDINATE.clear();
								Koordinaten.Y_KOORDINATE.sendText(Main.eigene.get(counter.get()).getY());
								Koordinaten.JUMP_TO.click();
								if (Dorfoptionen.ACTIVE_VILLAGE.isPresent(Duration.ofSeconds(5))) {
									Dorfoptionen.ACTIVE_VILLAGE.click();

								}

								sleep(1);
								// rohstoffeCheckenUndAxtBauen();

								initVorlagen(getAnzahlAngriffe());

								if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
									MainToolbar.AUF_WELTKARTE_SUCHEN.click();
							}

						} else {
							Barbarendorf tmp = barbarendorfDao.findById(dorf.getId()).get();
							tmp.setAttackedAt(LocalDateTime.now());

							barbarendorfDao.save(tmp);
						}
					}
					// wenn ein Barbarendorf kein Barbarendorf mehr ist
					if (Dorfoptionen.NACHRICHT_SENDEN.isPresent()) {
						logger.info("Dorf: " + dorf.getName() + " ist kein Barbarendorf mehr, wird entfernt! ");
						barbarendorfDao.delete(dorf);

					}
				}
			}
			if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
				MainToolbar.AUF_WELTKARTE_SUCHEN.click();

			if (account.getErstesDorf() != null) {
				Koordinaten.X_KOORDINATE.clear();
				Koordinaten.X_KOORDINATE.sendText(account.getErstesDorf().getX());
				Koordinaten.Y_KOORDINATE.clear();
				Koordinaten.Y_KOORDINATE.sendText(account.getErstesDorf().getY());
				Koordinaten.JUMP_TO.click();
				Main.sleep(1);
				if (Dorfoptionen.ACTIVE_VILLAGE.isPresent(Duration.ofSeconds(5))) {
					Dorfoptionen.ACTIVE_VILLAGE.click();

				}

			}
			// checke die Points
			for (long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(5); stop > System.nanoTime();) {

				points.sort(new Comparator<Point>() {

					public int compare(Point o1, Point o2) {
						return getDistance((int) o1.getX(), 570, (int) o1.getY(), 583) - getDistance((int) o2.getX(), 570, (int) o2.getY(), 583);
					}
				});

				for (Iterator<Point> it = points.iterator(); it.hasNext();) {
					Point p = it.next();
					if (p.isChecked() && p.getCheckedAt().plusHours(48).isAfter(LocalDateTime.now())) {
						it.remove();
					}
				}

				for (int i = 0; i < points.size() && stop > System.nanoTime(); i++) {
					Point currentPoint = points.get(i);

					if (MainToolbar.COMPLETE_BUILDING.isPresent()) {
						MainToolbar.COMPLETE_BUILDING.click();
					}

					if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
						MainToolbar.AUF_WELTKARTE_SUCHEN.click();

					Koordinaten.X_KOORDINATE.clear();
					Koordinaten.X_KOORDINATE.sendText(currentPoint.getX());
					Koordinaten.Y_KOORDINATE.clear();
					Koordinaten.Y_KOORDINATE.sendText(currentPoint.getY());
					Koordinaten.JUMP_TO.click();

					if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Rohstofflager") && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Freund einladen")) {

						String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

						if (Dorfoptionen.DORFINFORMATIONEN.isPresent(Duration.ofSeconds(2)) && Dorfoptionen.GRUPPEN_HINZUFUEGEN.isPresent(Duration.ofSeconds(2))
								&& !Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

							Dorfoptionen.DORFINFORMATIONEN.click();

							if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
								int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

								Optional<EigenesDorf> eigenAltOptional = eigenesDorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

								eigenAltOptional.ifPresentOrElse(x -> {
									x.setPunkte(dorfpunkte);
									x.setName(dorfname);
									eigenesDorfDao.save(x);
									logger.info("Eigenes Dorf geupdated");
								}, () -> {
									eigenesDorfDao.save(new EigenesDorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte, account.getSpielername()));
									logger.info("Eigenes Dorf hinzugefügt");
								});
							}
						}

						MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

					} else if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

						String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

						Dorfoptionen.DORFINFORMATIONEN.click();

						if (Dorfinformationen.DORFINFORMATIONEN_PUNKTE.isPresent()) {

							int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

							Optional<Barbarendorf> eigenALT = barbarendorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

							eigenALT.ifPresentOrElse(x -> {
								x.setPunkte(dorfpunkte);
								x.setName(dorfname);
								barbarendorfDao.save(x);
								logger.info("Babarendorf updated");
							}, () -> {
								barbarendorfDao.save(new Barbarendorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte));
								logger.info("Barbarendorf hinzugefügt");
							});
						}

						MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

					} else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2))) {

						String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

						Dorfoptionen.DORFINFORMATIONEN.click();

						if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
							int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

							Optional<Dorf> eigenALT = dorfDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

							eigenALT.ifPresentOrElse(x -> {
								x.setPunkte(dorfpunkte);
								x.setName(dorfname);
								dorfDao.save(x);
								logger.info("Dorf Gegner updated");
							}, () -> {
								dorfDao.save(new Dorf(currentPoint.getX(), currentPoint.getY(), dorfname, dorfpunkte));
								logger.info(" Dorf Gegner hinzugefügt");
							});
						}

						MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

					} else {
						MainToolbar.OBERFLAECHE.clickCoords(0, 0);

						if (Provinzansicht.PROVINZ_BUTTON_PROVINZDOERVER.isPresent()) {

							Optional<Provinz> eigenALT = provinzDao.findByXAndY(currentPoint.getX(), currentPoint.getY());

							String provinzName = Provinzansicht.PROVINZ_NAME.getText();

							eigenALT.ifPresentOrElse(x -> {
								x.setName(provinzName);
								provinzDao.save(x);
								logger.info("Provinz updated");
							}, () -> {
								provinzDao.save(new Provinz(currentPoint.getX(), currentPoint.getY(), provinzName, LocalDateTime.now()));
								logger.info("Provinz hinzugefügt");
							});

							MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

						}

						currentPoint.setChecked(true);
						currentPoint.setCheckedAt(LocalDateTime.now());

						pointDao.save(currentPoint);

						logger.info("Update:" + currentPoint);
					}

					// sleep(5);
				}

				Main.eigene = eigenesDorfDao.findBySpieler(account.getSpielername());
				dorfListe = dorfDao.findAll();
				barbarendoerfer = barbarendorfDao.findAll();

				logger.info("Driver wird neugestartet!");
				this.restartDriver();
			}
		}
	}

	private void rohstoffeCheckenUndAxtBauen() throws ElementisNotClickable, NoElementTextFound {
		// Rohstoffe checken
		sleep(1);

		MainToolbar.OBERFLAECHE.sendText("v");
		sleep(1);
		if (!Dorfansicht.HAUPTGEBAEUDE.isPresent()) {
			MainToolbar.OBERFLAECHE.sendText("v");
		}
		sleep(5);

		Dorfansicht.SPEICHER.click();
		Dorfansicht.SPEICHER2.click();

		sleep(1);
		int max = 100;
		int currentHolz = 0;
		int currentLehm = 0;
		int currentEisen = 0;
		if (Speicher.SPEICHER_HOLZ.isPresent()) {
			String[] holz = Speicher.SPEICHER_HOLZ.getText().split(" / ");
			String[] lehm = Speicher.SPEICHER_LEHM.getText().split(" / ");
			String[] eisen = Speicher.SPEICHER_EISEN.getText().split(" / ");

			max = Integer.parseInt(holz[1].replace(".", ""));
			currentHolz = Integer.parseInt(holz[0].replace(".", ""));
			currentLehm = Integer.parseInt(lehm[0].replace(".", ""));
			currentEisen = Integer.parseInt(eisen[0].replace(".", ""));
		}

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
		MainToolbar.OBERFLAECHE.sendText("v");
		sleep(1);

		MainToolbar.REKRUTIERUNGSSCHEIFE.click();
		int anzahl = 50;
		if ((currentHolz >= max || currentLehm >= max || currentEisen >= max) && Integer.parseInt(MainToolbar.PROVIANT.getText().replace(".", "")) > anzahl
				|| MainToolbar.KASERNENSLOT1.getAttribute("innerHTML").contains("Kaserne öffnen")) {
			logger.info("Baue " + anzahl + " axtkämpfer");
			// baueAxt(anzahl);
		} else {
			logger.info("Vorraussetzungen für " + anzahl + " axtkämpfer nicht erfüllt!");

		}

		MainToolbar.BAUSCHLEIFE.click();

	}

	private void baueAxt(int anzahl) throws ElementisNotClickable {

		MainToolbar.OBERFLAECHE.sendText("b");
		sleep(1);
		Kaserne.KASERNE_AXTKAEMPFER.scrollToElement("start");
		sleep(1);
		logger.info("" + Kaserne.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent());
		logger.info("" + Kaserne.KASERNE_AXTKAEMPFER.isPresent());

		if (Kaserne.KASERNE_AXTKAEMPFER.isPresent() && !Kaserne.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent()) {
			Kaserne.KASERNE_AXTKAEMPFER.click();

			Kaserne.KASERNE_AXTKAEMPFER_VALUE.clear();
			Kaserne.KASERNE_AXTKAEMPFER_VALUE.sendText(anzahl);
			Kaserne.KASERNE_AXTKAEMPFER_VALUE.sendText(Keys.ENTER);
		}

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

		MainToolbar.ZEITLEISTE.click();
		sleep(1);

		MainToolbar.ZEITLEISTE.click();
		sleep(1);

	}

	private void disableSound() throws ElementisNotClickable {
		MainToolbar.EINSTELLUNGEN.click();
		Einstellungen.EINSTELLUNGEN_SPIEL.click();
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_TIPPS_EINSCHALTEN.click();
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_ANNIMATION1.scrollToElement("start");
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_ANNIMATION1.click();
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.scrollToElement("start");
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.click();
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("start");
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.click();

		// um normale view wiederherzustellen
		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("end");

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private int rohstofflagerCheck(boolean push) throws ElementisNotClickable, NoElementTextFound {
		// Rohstofflager farmen
		int ges = 0;

		MainToolbar.OBERFLAECHE.sendText("d");

		if (Rohstofflager.ROHSTOFFLAGER_EINSAMMELN.isPresent(Duration.ofMillis(2000))) {
			Rohstofflager.ROHSTOFFLAGER_EINSAMMELN.click();
			Main.sleep(5);
		}
		if (!Rohstofflager.AKTUELLE_PRODUKTION.getAttribute("innerHTML").contains("Aktuelle Produktion")) {

			Main.sleep(1);
			if (Rohstofflager.ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN.isPresent(Duration.ofMillis(300))) {
				Rohstofflager.ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN.click();
			}

			Main.sleep(1);
			if (Rohstofflager.ROHSTOFFLAGER_STARTEN.isPresent(Duration.ofMillis(300))) {
				Rohstofflager.ROHSTOFFLAGER_STARTEN.scrollToElement("end");

				String[] zeit = Rohstofflager.ROHSTOFFLAGER_STARTEN_ZEIT.getText().split(":");
				ges += Integer.parseInt(zeit[0]) * 3600 + Integer.parseInt(zeit[1]) * 60 + Integer.parseInt(zeit[2]);
				logger.info("Dauer von neuem Auftrag:" + "\nStudnen: " + zeit[0] + "\nMinuten: " + zeit[1] + "\nSekunden: " + zeit[2]);

				Rohstofflager.ROHSTOFFLAGER_STARTEN.click();

				MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

				return ges;
			}
			if (push && Rohstofflager.LETZTES_ITEM.getAttribute("tooltip-content").equals("Reiche Ernte - steigert den Proviant in einem Dorf um 10%")) {
				if (!Rohstofflager.ROHSTOFFLAGER_STARTEN.isPresent(Duration.ofSeconds(2))) {
					Rohstofflager.ITEMS_VERWENDEN.click(2);
					Rohstofflager.BENUTZEN.click(2);
					Rohstofflager.GEGENSTAND_BENUTZEN.click(2);
				}
			}
		} else {

			String[] zeit = Rohstofflager.AKTUELLE_PRODUKTION_ZEIT.getText().split(":");
			ges += Integer.parseInt(zeit[0]) * 3600 + Integer.parseInt(zeit[1]) * 60 + Integer.parseInt(zeit[2]);
			logger.info("Dauer von aktuellem Auftrag:" + "\nStudnen: " + zeit[0] + "\nMinuten: " + zeit[1] + "\nSekunden: " + zeit[2]);
			MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

			return ges;
		}

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
		return ges;

	}

	private int getAnzahlAngriffe() throws ElementisNotClickable {
		MainToolbar.EINHEITEN_UEBERSICHT.click();
		sleep(2);

		int rowCount = Einheiten.TABLE_UNITS_ROWS.getWebelements().size();

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
		logger.info("Anzahl der bisherigen Angriffe: " + rowCount);
		return rowCount;

	}

	private void ausgehendenAngriffeVerbergen() throws ElementisNotClickable {
		MainToolbar.EINSTELLUNGEN.click();
		Einstellungen.EINSTELLUNGEN_SPIEL.click();

		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("start");

		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.click();

		EinstellungenSubSpiel.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("end");

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private void initVorlagen(int anzahlAngriffe) throws ElementisNotClickable, NumberFormatException, NoElementTextFound {
		MainToolbar.OBERFLAECHE.sendText("r");
		Sammelplatz.GLOBALE_VORLAGENLISTE.click();
		UebersichtVorlangenliste.GLOBALE_VORLAGENLISTE_STATUS.click();
		UebersichtVorlangenliste.GLOBALE_VORLAGENLISTE_BEARBEITEN.click();

		int verbleibendeAngriffe = 50 - anzahlAngriffe;

		logger.info("Verbleibende Angriffe: " + verbleibendeAngriffe);
		sleep(1);
		if (verbleibendeAngriffe > 0) {
			// Barbaren
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.clear();
			int tmp = Integer.parseInt(MainToolbar.ANZAHL_AXT.getText().replace(".", ""));

			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.sendText(tmp / verbleibendeAngriffe);

			if (tmp < 500) {
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.clear();
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.sendText(100);

			}

			// Leichte Kavellerie
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_LKAV.clear();
			tmp = Integer.parseInt(MainToolbar.ANZAHL_LKAV.getText().replace(".", ""));

			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_LKAV.sendText(tmp / verbleibendeAngriffe);

			if (tmp < 500) {
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_LKAV.clear();
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_LKAV.sendText(15);

			}

			// SPEER
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();
			//
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.sendText(Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(MainToolbar.ANZAHL_SPEER.getText().replace(".", ""));

			if (tmp < 500) {
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();

				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.sendText(50);

			}
			// SCHWERT
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(Integer.parseInt(MainToolbar.ANZAHL_SCHWERT.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(MainToolbar.ANZAHL_SCHWERT.getText().replace(".", ""));

			if (tmp < 500) {
				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();

				VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(100);

			}

			// Paladin

			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN.clear();
			VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN.sendText(0);
		}

		VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1.scrollToElement("end");
		VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1.click();
		VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF.click();
		VorlangeErstellenOderAendern.GLOBALE_VORLAGENLISTE_SPEICHERN.click();
		sleep(1500, TimeUnit.MILLISECONDS);

		if (UebersichtVorlangenliste.GLOBALE_VORLAGENLISTE_STATUS.getCSSClass().equals("switch switch-56x28 switch-horizontal")) {
			UebersichtVorlangenliste.GLOBALE_VORLAGENLISTE_STATUS.click();
		}

		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
		MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	public static void sleep(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e1) {
			throw new RuntimeException(e1);
		}
	}

	public static void sleep(int i, TimeUnit milliseconds) {
		try {
			milliseconds.sleep(i);
		} catch (InterruptedException e1) {
			throw new RuntimeException(e1);
		}
	}

	public void restartDriver() throws ElementisNotClickable {

		if (driver != null) {

			driver.navigate().refresh();
			sleep(5);

			Login.LOADING_SCREEN.isNOTPresent(Duration.ofSeconds(30));

			sleep(2);
		}

		if (driver == null) {
			Main.driver = new FirefoxDriver();
			driver.get("https://de.tribalwars2.com/");
			driver.manage().window().maximize();
			login();
		}

	}

	public static WebDriver getDriver() {
		return Main.driver;
	}

	public DorfDao getDorfDao() {
		return dorfDao;
	}

	public void setDorfDao(DorfDao dorfDao) {
		this.dorfDao = dorfDao;
	}

	public PointDao getPointDao() {
		return pointDao;
	}

	public void setPointDao(PointDao pointDao) {
		this.pointDao = pointDao;
	}

	public ProvinzDao getProvinzDao() {
		return provinzDao;
	}

	public void setProvinzDao(ProvinzDao provinzDao) {
		this.provinzDao = provinzDao;
	}

	public EigenesDorfDao getEigenesDorfDao() {
		return eigenesDorfDao;
	}

	public void setEigenesDorfDao(EigenesDorfDao eigenesDorfDao) {
		this.eigenesDorfDao = eigenesDorfDao;
	}

	public BarbarendorfDao getBarbarendorfDao() {
		return barbarendorfDao;
	}

	public void setBarbarendorfDao(BarbarendorfDao barbarendorfDao) {
		this.barbarendorfDao = barbarendorfDao;
	}

}
