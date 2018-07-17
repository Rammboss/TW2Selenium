package TB2.TB2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDateTime;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Provinz;
import TB2.NewStructure.common.hibernate.service.BarbarendorfService;
import TB2.NewStructure.common.hibernate.service.DorfService;
import TB2.NewStructure.common.hibernate.service.EigenesDorfService;
import TB2.NewStructure.common.hibernate.service.PointService;
import TB2.NewStructure.common.hibernate.service.ProvinzService;

public class Main {

	static {
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

		driver = new FirefoxDriver();
		account = new Account("Rammboss", "kalterhund", "Gaillard");
		// account = new Account("DerZurecker", "aleyotmi1", "Gaillard");
		// account = new Account("Don Porro", "Kacklappen", "Gaillard");

	}

	public static WebDriver driver;

	public static Account account;

	public static int index;

	public static List<EigenesDorf> eigene;

	public List<Barbarendorf> babas;

	public Main() {
		Main.index = 0;
	}

	public void login() {
		driver.get("https://de.tribalwars2.com/");

		Buttons.SPIELERNAME.sendText(account.getSpielername());
		Buttons.PASSWORT.sendText(account.getPasswort());
		Buttons.SPIELEN.click();

		if (Buttons.LOGIN.isPresent(8))
			sleep(1);
		Buttons.LOGIN.getWebelementsByAttributeWithCriteria("a", "class", "btn-orange btn-border small-icon",
				account.getWelt()).click();

		sleep(1);
		long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(30);

		while (Buttons.LOADING_SCREEN.isPresent() && stop > System.nanoTime()) {
			sleep(1);
		}
		sleep(2);

	}

	public static void main(String[] args) {

		Main app = new Main();
		try {
			app.runTask();
		} catch (Exception e) {
			System.out.println("Ein unerwarteter Fehler ist aufgetreten! Treiber wird in 5 sekunden neu gestartet!");
			sleep(5);
			e.printStackTrace();
			app.restartDriver();
			app.runTask();
		}

	}

	private void runTask() {
		this.login();
		if (Buttons.BELOHNUNG_ANNEHMEN.isPresent()) {
			Buttons.BELOHNUNG_ANNEHMEN.click();
			sleep(5);

		}

		this.disableSound();

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DorfService serviceDorf = (DorfService) context.getBean("dorfService");
		EigenesDorfService serviceEigenesDorf = (EigenesDorfService) context.getBean("eigenesDorfService");
		BarbarendorfService serviceBabarendorf = (BarbarendorfService) context.getBean("barbarenDorfService");

		Main.eigene = serviceEigenesDorf.findBySpieler(account.getSpielername());
		List<Dorf> dorfListe = serviceDorf.findAll();
		List<Barbarendorf> barbarendoerfer = serviceBabarendorf.findAll();
		if (Main.eigene.size() != 0) {
			account.setErstesDorf(Main.eigene.get(0));

		}

		if (Main.eigene.size() == 0 || dorfListe.size() == 0) {
			this.ausgehendenAngriffeVerbergen();

			// dorfListe = this.initProvinzen(provinzen);
			Main.eigene = serviceEigenesDorf.findBySpieler(account.getSpielername());
			dorfListe = serviceDorf.findAll();
			barbarendoerfer = serviceBabarendorf.findAll();

			this.ausgehendenAngriffeVerbergen();

		}
		if (Main.eigene.size() != 0) {
			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();
			Buttons.X_KOORDINATE.clear();
			Buttons.X_KOORDINATE.sendText(Main.eigene.get(0).getX());
			Buttons.Y_KOORDINATE.clear();
			Buttons.Y_KOORDINATE.sendText(Main.eigene.get(0).getY());
			Buttons.JUMP_TO.click();
		}

		if (Buttons.ACTIVE_VILLAGE.isPresent(5)) {
			Buttons.ACTIVE_VILLAGE.click();

		}
		if (Buttons.ACTIVE_VILLAGE2.isPresent(5)) {
			Buttons.ACTIVE_VILLAGE2.click();

		}

		// Befehle wieder anzeigen
		this.babas = barbarendoerfer;

		while (true) {

			this.disableSound();

			barbarendoerfer = serviceBabarendorf.findAll();

			this.rohstoffeCheckenUndAxtBauen();

			this.initVorlagen(this.getAnzahlAngriffe());

			this.rohstofflagerCheck();

			// Koordinaten eingen
			Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();
			if (Main.eigene.size() != 0) {

				int counter = 0;
				System.out
						.println("Farmen vorhanden: " + getFarmableBabas(this.babas, Main.eigene.get(counter)).size());
				for (Barbarendorf dorf : getFarmableBabas(this.babas, Main.eigene.get(counter))) {
					Buttons.X_KOORDINATE.clear();
					Buttons.X_KOORDINATE.sendText(dorf.getX());
					Buttons.Y_KOORDINATE.clear();
					Buttons.Y_KOORDINATE.sendText(dorf.getY());
					Buttons.JUMP_TO.click();
					Main.sleep(300, TimeUnit.MILLISECONDS);

					if (Buttons.PRODUKTION_STEIGERN.isPresent(1000, TimeUnit.MILLISECONDS)
							|| Buttons.PRODUKTION_STEIGERN2.isPresent(1000, TimeUnit.MILLISECONDS)
									&& dorf.isFarmable(Main.eigene.get(counter))) {
						sleep(1);
						Buttons.OBERFLAECHE.sendText(1);

						if (Buttons.ERROR_50_ANGRIFFE.isPresent(500, TimeUnit.MILLISECONDS)) {
							sleep(5); // instead of click on message, because causes errors

							if (counter >= Main.eigene.size() - 1) {
								break;
							} else {

								// Eigenes Dorf wechseln
								counter++;
								if (!Buttons.X_KOORDINATE.isPresent(1))
									Buttons.AUF_WELTKARTE_SUCHEN.click();
								Buttons.X_KOORDINATE.clear();
								Buttons.X_KOORDINATE.sendText(Main.eigene.get(counter).getX());
								Buttons.Y_KOORDINATE.clear();
								Buttons.Y_KOORDINATE.sendText(Main.eigene.get(counter).getY());
								Buttons.JUMP_TO.click();
								if (Buttons.ACTIVE_VILLAGE.isPresent(5)) {
									Buttons.ACTIVE_VILLAGE.click();

								}
								if (Buttons.ACTIVE_VILLAGE2.isPresent(5)) {
									Buttons.ACTIVE_VILLAGE2.click();

								}
								sleep(1);
								this.rohstoffeCheckenUndAxtBauen();

								this.initVorlagen(this.getAnzahlAngriffe());

								if (!Buttons.X_KOORDINATE.isPresent(1))
									Buttons.AUF_WELTKARTE_SUCHEN.click();
							}

						} else {
							Barbarendorf tmp = serviceBabarendorf.findById(dorf.getId());
							tmp.setAttackedAt(new LocalDateTime());

							serviceBabarendorf.updateDorf(tmp);
						}
					}
				}
			}
			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();

			if (account.getErstesDorf() != null) {
				Buttons.X_KOORDINATE.clear();
				Buttons.X_KOORDINATE.sendText(serviceEigenesDorf
						.findBySpielerAndName(account.getErstesDorf().getName(), account.getSpielername()).getX());
				Buttons.Y_KOORDINATE.clear();
				Buttons.Y_KOORDINATE.sendText(serviceEigenesDorf
						.findBySpielerAndName(account.getErstesDorf().getName(), account.getSpielername()).getY());
				Buttons.JUMP_TO.click();
				Main.sleep(1);
				if (Buttons.ACTIVE_VILLAGE.isPresent(5)) {
					Buttons.ACTIVE_VILLAGE.click();

				}
				if (Buttons.ACTIVE_VILLAGE2.isPresent(5)) {
					Buttons.ACTIVE_VILLAGE2.click();

				}
			}

			// checkDoerfer(600, dorfListe, this);
			for (long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(30); stop > System.nanoTime();) {

				PointService servicePoint = (PointService) context.getBean("pointService");
				ProvinzService serviceProvinz = (ProvinzService) context.getBean("provinzService");

				List<TB2.NewStructure.common.hibernate.model.Point> points = servicePoint.findAll();

				for (int i = 0; i < points.size(); i++) {
					if (points.get(i).isChecked()
							&& points.get(i).getCheckedAt().plusHours(48).isBefore(new LocalDateTime())) {
						points.remove(i);
						i--;

					}

				}

				for (int i = 0; i < points.size() && stop > System.nanoTime(); i++) {
					if (Buttons.COMPLETE_BUILDING.isPresent()) {
						Buttons.COMPLETE_BUILDING.click();
					}

					if (!Buttons.X_KOORDINATE.isPresent(1))
						Buttons.AUF_WELTKARTE_SUCHEN.click();

					Buttons.X_KOORDINATE.clear();
					Buttons.X_KOORDINATE.sendText(points.get(i).getX());
					Buttons.Y_KOORDINATE.clear();
					Buttons.Y_KOORDINATE.sendText(points.get(i).getY());
					Buttons.JUMP_TO.click();

					if (Buttons.MENUE_MITTE.isPresent(2)
							&& !Buttons.MENUE_MITTE.getAttribute("tooltip-content").equals("Rohstofflager")
							&& !Buttons.MENUE_MITTE.getAttribute("tooltip-content").equals("Freund einladen")) {

						String dorfname = Buttons.MENUE_MITTE.getAttribute("tooltip-content");

						if (Buttons.DORFINFORMATIONEN.isPresentByCSSSelector("div", "tooltip-content",
								"Dorfinformationen", 2000)
								&& Buttons.GRUPPEN_HINZUFUEGEN.isPresentByCSSSelector("div", "tooltip-content",
										"Gruppen hinzufügen", 2000)
								&& !Buttons.NACHRICHT_SENDEN.isPresentByCSSSelector("div", "tooltip-content",
										"Nachricht senden", 2000)
								&& !Buttons.PRODUKTION_STEIGERN.isPresentByCSSSelector("div", "tooltip-content",
										"Produktion steigern", 2000)) {

							Buttons.DORFINFORMATIONEN
									.getWebelementsByAttribute("div", "tooltip-content", "Dorfinformationen").click();

							if (Buttons.DORFINFORMATIONEN_NAME.isPresent()) {
								int dorfpunkte = Integer
										.parseInt(Buttons.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));
								Dorf tmp = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());

								EigenesDorf eigen = new EigenesDorf(points.get(i).getX(), points.get(i).getY(),
										dorfname, dorfpunkte, account.getSpielername());
								if (!tmp.equals(eigen)) {

									EigenesDorf eigenALT = serviceEigenesDorf.findByXandY(points.get(i).getX(),
											points.get(i).getY());

									if (eigenALT.getX() == -1) {
										serviceEigenesDorf.saveDorf(new EigenesDorf(points.get(i).getX(),
												points.get(i).getY(), dorfname, dorfpunkte, account.getSpielername()));
										System.out.println("Eigenes Dorf hinzugefügt");
									} else {
										eigenALT.setPunkte(dorfpunkte);
										eigenALT.setName(dorfname);
										serviceEigenesDorf.updateDorf(eigenALT);
										System.out.println("Eigenes Dorf geupdated");

									}

								}

							}

							Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

						} else if (Buttons.PRODUKTION_STEIGERN
								.getWebelementsByAttribute("div", "tooltip-content", "Produktion steigern")
								.isDisplayed()) {

							Buttons.DORFINFORMATIONEN
									.getWebelementsByAttribute("div", "tooltip-content", "Dorfinformationen").click();

							if (Buttons.DORFINFORMATIONEN_PUNKTE.isPresent()) {

								int dorfpunkte = Integer
										.parseInt(Buttons.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

								Dorf tmp = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());
								Barbarendorf baba = new Barbarendorf(points.get(i).getX(), points.get(i).getY(),
										dorfname, dorfpunkte);

								if (!tmp.equals(baba)) {

									Barbarendorf eigenALT = serviceBabarendorf.findByXandY(points.get(i).getX(),
											points.get(i).getY());

									if (eigenALT.getX() == -1) {
										serviceBabarendorf.saveDorf(baba);
										System.out.println("Barbarendorf hinzugefügt");
									} else {
										eigenALT.setPunkte(dorfpunkte);
										eigenALT.setName(dorfname);
										serviceBabarendorf.updateDorf(eigenALT);
										System.out.println("Babarendorf updated");

									}

								}

							}

							Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

						} else if (Buttons.NACHRICHT_SENDEN
								.getWebelementsByAttribute("div", "tooltip-content", "Nachricht senden")
								.isDisplayed()) {

							Buttons.DORFINFORMATIONEN
									.getWebelementsByAttribute("div", "tooltip-content", "Dorfinformationen").click();

							if (Buttons.DORFINFORMATIONEN_NAME.isPresent()) {
								int dorfpunkte = Integer
										.parseInt(Buttons.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));
								Dorf tmp = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());
								Dorf dorf = new Dorf(points.get(i).getX(), points.get(i).getY(), dorfname, dorfpunkte);

								if (!tmp.equals(dorf)) {
									Dorf eigenALT = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());

									if (eigenALT.getX() == -1) {
										serviceDorf.saveDorf(dorf);
										System.out.println(" Dorf Gegner hinzugefügt");
									} else {
										eigenALT.setPunkte(dorfpunkte);
										eigenALT.setName(dorfname);
										serviceDorf.updateDorf(eigenALT);
										System.out.println("Dorf Gegner updated");

									}

								}

							}

							Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

						}
					} else {
						Buttons.OBERFLAECHE.click();

						if (Buttons.PROVINZ_BUTTON_PROVINZDOERVER.isPresent()) {

							Provinz tmp = serviceProvinz.findByXandY(points.get(i).getX(), points.get(i).getY());

							Provinz pNeu = new Provinz(points.get(i).getX(), points.get(i).getY(),
									Buttons.PROVINZ_NAME.getText(), new LocalDateTime());

							if (!tmp.equals(pNeu)) {
								Provinz eigenALT = serviceProvinz.findByXandY(points.get(i).getX(),
										points.get(i).getY());

								if (eigenALT.getX() == -1) {
									serviceProvinz.saveProvinz(pNeu);
									System.out.println("Provinz hinzugefügt");

								} else {
									eigenALT.setName(Buttons.PROVINZ_NAME.getText());
									serviceProvinz.updateProvinz(eigenALT);
									System.out.println("Provinz updated");

								}

							}

							Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);
						} else {
							// System.out.println("Nichts gefunden");
						}
					}

					TB2.NewStructure.common.hibernate.model.Point p = servicePoint.findById(points.get(i).getId());
					p.setChecked(true);
					p.setCheckedAt(new LocalDateTime());

					servicePoint.updatePoint(p);
				}

				sleep(5);
			}
			context.close();
			context = new AnnotationConfigApplicationContext(AppConfig.class);
			serviceDorf = (DorfService) context.getBean("dorfService");
			serviceEigenesDorf = (EigenesDorfService) context.getBean("eigenesDorfService");
			serviceBabarendorf = (BarbarendorfService) context.getBean("barbarenDorfService");

			Main.eigene = serviceEigenesDorf.findBySpieler(account.getSpielername());
			dorfListe = serviceDorf.findAll();
			barbarendoerfer = serviceBabarendorf.findAll();

			System.out.println("Driver wird neugestartet!");
			this.restartDriver();
		}
	}

	private void rohstoffeCheckenUndAxtBauen() {
		// Rohstoffe checken
		sleep(1);

		Buttons.OBERFLAECHE.sendText("v");
		sleep(1);
		if (!Buttons.DORFANSICHT.isPresent()) {
			Buttons.OBERFLAECHE.sendText("v");
		}
		sleep(5);

		Buttons.SPEICHER.getWebelementsByText("span", "Speicher").click();
		Buttons.SPEICHER.getWebelementsByAttribute("div", "tooltip-content", "Speicher").click();

		// Buttons.SPEICHER.click();
		// Buttons.SPEICHER1.click();
		// sleep(1);
		//
		// Buttons.SPEICHER2.click();
		// Buttons.SPEICHER2_1.click();
		// Buttons.SPEICHER2_3.click();

		sleep(1);
		int max = 100;
		int currentHolz = 0;
		int currentLehm = 0;
		int currentEisen = 0;
		if (Buttons.SPEICHER_HOLZ.isPresent()) {
			String[] holz = Buttons.SPEICHER_HOLZ.getText().split(" / ");
			String[] lehm = Buttons.SPEICHER_LEHM.getText().split(" / ");
			String[] eisen = Buttons.SPEICHER_EISEN.getText().split(" / ");

			max = Integer.parseInt(holz[1].replace(".", ""));
			currentHolz = Integer.parseInt(holz[0].replace(".", ""));
			currentLehm = Integer.parseInt(lehm[0].replace(".", ""));
			currentEisen = Integer.parseInt(eisen[0].replace(".", ""));
		}

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);
		Buttons.OBERFLAECHE.sendText("v");
		sleep(1);

		Buttons.REKRUTIERUNGSSCHEIFE.click();
		int anzahl = 50;
		if ((currentHolz >= max || currentLehm >= max || currentEisen >= max)
				&& Integer.parseInt(Buttons.PROVIANT.getText().replace(".", "")) > anzahl
				|| Buttons.KASERNENSLOT1.compareAttribute("tooltip-content", "Kaserne öffnen")) {
			System.out.println("Baue " + anzahl + " axtkämpfer");
			baueAxt(anzahl);
		} else {
			System.out.println("Vorraussetzungen für " + anzahl + " axtkämpfer nicht erfüllt!");

		}

		Buttons.BAUSCHLEIFE.click();

	}

	private void baueAxt(int anzahl) {

		Buttons.OBERFLAECHE.sendText("b");
		sleep(1);
		Buttons.KASERNE_AXTKAEMPFER.scrollToElement("start");
		sleep(1);
		System.out.println(Buttons.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent());
		System.out.println(Buttons.KASERNE_AXTKAEMPFER.isPresent());

		if (Buttons.KASERNE_AXTKAEMPFER.isPresent() && !Buttons.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent()) {
			Buttons.KASERNE_AXTKAEMPFER.click();

			Buttons.KASERNE_AXTKAEMPFER_VALUE.clear();
			Buttons.KASERNE_AXTKAEMPFER_VALUE.sendText(anzahl);
			Buttons.KASERNE_AXTKAEMPFER_VALUE.sendText(Keys.ENTER);
		}

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

		Buttons.ZEITLEISTE.click();
		sleep(1);

		Buttons.ZEITLEISTE.click();
		sleep(1);

	}

	private void disableSound() {
		Buttons.EINSTELLUNGEN.click();
		Buttons.EINSTELLUNGEN_SPIEL.click();
		Buttons.EINSTELLUNGEN_SPIEL_ANNIMATION1.scrollToElement("end");
		Buttons.EINSTELLUNGEN_SPIEL_ANNIMATION1.click();
		Buttons.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.scrollToElement("start");
		Buttons.EINSTELLUNGEN_SPIEL_MUSIK_SOUND.click();
		Buttons.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("start");
		Buttons.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.click();

		// um normale view wiederherzustellen
		Buttons.EINSTELLUNGEN_SPIEL_EFFEKT_SOUND.scrollToElement("end");

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private void rohstofflagerCheck() {
		// Rohstofflager farmen
		Buttons.OBERFLAECHE.sendText("d");
		if (Buttons.ROHSTOFFLAGER_EINSAMMELN.isPresent(300, TimeUnit.MILLISECONDS)) {
			Buttons.ROHSTOFFLAGER_EINSAMMELN.click();
		}
		Main.sleep(1);
		if (Buttons.ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN.isPresent(300, TimeUnit.MILLISECONDS)) {
			Buttons.ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN.click();
		}
		Main.sleep(1);
		if (Buttons.ROHSTOFFLAGER_STARTEN.isPresent(300, TimeUnit.MILLISECONDS)) {
			Buttons.ROHSTOFFLAGER_STARTEN.scrollToElement("end");
			Buttons.ROHSTOFFLAGER_STARTEN.click();
		}

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private static void checkDoerfer(int sec, List<Dorf> dorfListe, Main app) {

		for (int i = 0; i < dorfListe.size(); i++) {
			if (dorfListe.get(i).getName().equals("Barbarendorf")) {
				dorfListe.remove(i);
				i--;
			}
		}
		if (Main.index >= dorfListe.size()) {
			Main.index = 0;
		}
		long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(sec);
		while (Main.index < dorfListe.size() && stop > System.nanoTime()) {

			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();

			Buttons.X_KOORDINATE.clear();
			Buttons.X_KOORDINATE.sendText(dorfListe.get(Main.index).getX());
			Buttons.Y_KOORDINATE.clear();
			Buttons.Y_KOORDINATE.sendText(dorfListe.get(Main.index).getY());
			sleep(500, TimeUnit.MILLISECONDS);
			Buttons.JUMP_TO.click();

			if (Buttons.NACHRICHT_SENDEN.isPresent(2, TimeUnit.SECONDS)
					|| Buttons.NACHRICHT_SENDEN2.isPresent(2, TimeUnit.SECONDS)) {
				Main.index++;
				continue;
			}

			Barbarendorf baba = new Barbarendorf(dorfListe.get(Main.index).getPunkte(),
					dorfListe.get(Main.index).getX(), dorfListe.get(Main.index).getY());

			if (Buttons.PRODUKTION_STEIGERN.isPresent(1000, TimeUnit.MILLISECONDS)
					|| Buttons.PRODUKTION_STEIGERN2.isPresent(1000, TimeUnit.MILLISECONDS)
							&& !app.babas.contains(baba)) {

				System.out.println("Füge Babarendorf " + baba.getName() + "hinzu!");
				app.babas.add(baba);
				Main.index++;

			}
		}

	}

	private int getAnzahlAngriffe() {
		Buttons.EINHEITEN_UEBERSICHT.click();
		sleep(2);

		int rowCount = Buttons.TABLE_UNITS_ROWS.getWebelements().size();

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);
		System.out.println("Anzahl der bisherigen Angriffe: " + rowCount);
		return rowCount;

	}

	private static List<Barbarendorf> getFarmableBabas(List<Barbarendorf> babas, EigenesDorf eigene2) {
		List<Barbarendorf> tmp = new ArrayList<Barbarendorf>();
		for (Barbarendorf barbarendorf : babas) {

			if (barbarendorf.isFarmable(eigene2)) {
				tmp.add(barbarendorf);
			}

		}
		return tmp;
	}

	private void ausgehendenAngriffeVerbergen() {
		Buttons.EINSTELLUNGEN.click();
		Buttons.EINSTELLUNGEN_SPIEL.click();

		Buttons.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("start");

		Buttons.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.click();

		Buttons.EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN.scrollToElement("end");

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private void initVorlagen(int anzahlAngriffe) {
		Buttons.OBERFLAECHE.sendText("r");
		Buttons.GLOBALE_VORLAGENLISTE.click();
		Buttons.GLOBALE_VORLAGENLISTE_STATUS.click();
		Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN.click();

		int verbleibendeAngriffe = 50 - anzahlAngriffe;

		System.out.println("Verbleibende Angriffe: " + verbleibendeAngriffe);
		sleep(1);
		if (verbleibendeAngriffe > 0) {
			// Barbaren

			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.clear();
			int tmp = Integer.parseInt(Buttons.ANZAHL_AXT.getText().replace(".", ""));

			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.sendText(tmp / verbleibendeAngriffe);

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.clear();
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_AXT.sendText(50);

			}
			// SPEER
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER
					.sendText(Integer.parseInt(Buttons.ANZAHL_SPEER.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(Buttons.ANZAHL_SPEER.getText().replace(".", ""));

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();

				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.sendText(20);

			}
			// SCHWERT
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(
					Integer.parseInt(Buttons.ANZAHL_SCHWERT.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(Buttons.ANZAHL_SCHWERT.getText().replace(".", ""));

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();

				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(20);

			}

			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN.clear();
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN.sendText(1);
		}

		Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1.scrollToElement("end");
		Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1.click();
		Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF.click();
		Buttons.GLOBALE_VORLAGENLISTE_SPEICHERN.click();
		sleep(1500, TimeUnit.MILLISECONDS);

		if (Buttons.GLOBALE_VORLAGENLISTE_STATUS.getCSSClass().equals("switch switch-56x28 switch-horizontal")) {
			Buttons.GLOBALE_VORLAGENLISTE_STATUS.click();
		}

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);
		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private List<Dorf> initProvinzen(List<Point> points) {

		List<Dorf> dorfListe = new ArrayList<Dorf>();
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DorfService serviceDorf = (DorfService) context.getBean("dorfService");
		EigenesDorfService serviceEigenesDorf = (EigenesDorfService) context.getBean("eigenesDorfService");
		BarbarendorfService serviceBabarendorf = (BarbarendorfService) context.getBean("barbarenDorfService");

		for (Point point : points) {

			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();
			Buttons.X_KOORDINATE.clear();
			Buttons.X_KOORDINATE.sendText(point.getX());
			Buttons.Y_KOORDINATE.clear();
			Buttons.Y_KOORDINATE.sendText(point.getY());
			Buttons.JUMP_TO.click();
			Main.sleep(2);

			Buttons.OBERFLAECHE.click();

			Main.sleep(2);
			// Eigene Dörfer durchsuchen
			if (Buttons.TABLE_OWN_VILLAGES_IN_PROVINZ.isPresent()) {
				WebElement element = Buttons.TABLE_OWN_VILLAGES_IN_PROVINZ.getWebelement();

				String elementSource = element.getAttribute("innerHTML");

				Pattern pattern = Pattern.compile("ng-binding\">(.*?)</td>");
				Matcher matcher = pattern.matcher(elementSource);
				int counter = 0;
				String name = "";
				int punkte = 0;

				while (matcher.find()) {

					switch (counter % 3) {
					case 0:
						name = matcher.group(1);
						break;
					case 1:
						punkte = Integer.parseInt(matcher.group(1).replace(".", ""));
						break;
					case 2:
						String[] test = matcher.group(1).split("|");
						int x = Integer.parseInt(test[0] + test[1] + test[2]);
						int y = Integer.parseInt(test[6] + test[7] + test[8]);

						EigenesDorf village = new EigenesDorf(x, y, name, punkte, account.getSpielername());

						serviceEigenesDorf.saveDorf(village);

						break;

					default:
						break;
					}
					counter++;
				}
			}

			Main.eigene.sort(new Comparator<EigenesDorf>() {

				public int compare(EigenesDorf o1, EigenesDorf o2) {
					return o1.getName().compareTo(o2.getName());
				}

			});

			// Fremde Dörfer durchsuchen
			WebElement element = Buttons.TABLE_VILLAGES_IN_PROVINZ.getWebelement();

			String elementSource = element.getAttribute("innerHTML");

			Pattern pattern = Pattern.compile("ng-binding\">(.*?)</td>");
			Matcher matcher = pattern.matcher(elementSource);
			int counter = 0;
			String name = "";
			int punkte = 0;

			while (matcher.find()) {

				switch (counter % 3) {
				case 0:
					name = matcher.group(1);
					break;
				case 1:
					punkte = Integer.parseInt(matcher.group(1).replace(".", ""));
					break;
				case 2:
					String[] test = matcher.group(1).split("|");
					int x = Integer.parseInt(test[0] + test[1] + test[2]);
					int y = Integer.parseInt(test[6] + test[7] + test[8]);
					if (name.equals("Barbarendorf")) {
						serviceBabarendorf.saveDorf(new Barbarendorf(x, y, punkte));
					} else {
						serviceDorf.saveDorf(new Dorf(x, y, name, punkte));

					}
					break;

				default:
					break;
				}
				counter++;
			}

			Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

		}

		return dorfListe;

	}

	public static void sleep(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public static void sleep(int i, TimeUnit milliseconds) {
		try {
			milliseconds.sleep(i);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void restartDriver() {
		driver.close();
		Main.driver = null;
		sleep(10);

		Main.driver = new FirefoxDriver();
		sleep(1);
		this.login();
	}

	public static WebDriver getDriver() {
		return Main.driver;
	}

}
