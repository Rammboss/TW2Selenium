package TB2.TB2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.LocalDateTime;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

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
import TB2.NewStructure.common.hibernate.dao.PointDaoImpl;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.model.Point;
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

		Main app = new Main();
		while (true) {
			try {
				app.restartDriver();
				app.runTask();
			} catch (Exception e) {
				System.out.println("Ein unerwarteter Fehler ist aufgetreten! Treiber wird in 5 sekunden neu gestartet!");
				e.printStackTrace();

			}
		}
	}

	private void runTask() throws ElementisNotClickable, NumberFormatException, NoElementTextFound {

		if (MainToolbar.BELOHNUNG_ANNEHMEN.isPresent(Duration.ofSeconds(3))) {
			MainToolbar.BELOHNUNG_ANNEHMEN.click();
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
			ausgehendenAngriffeVerbergen();

			Main.eigene = serviceEigenesDorf.findBySpieler(account.getSpielername());
			dorfListe = serviceDorf.findAll();
			barbarendoerfer = serviceBabarendorf.findAll();

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

			barbarendoerfer = serviceBabarendorf.findAll();

			rohstoffeCheckenUndAxtBauen();

			initVorlagen(this.getAnzahlAngriffe());

			rohstofflagerCheck(false);

			// Koordinaten eingen
			MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

			if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
				MainToolbar.AUF_WELTKARTE_SUCHEN.click();
			if (Main.eigene.size() != 0) {

				int counter = 0;
				System.out.println("Farmen vorhanden: " + getFarmableBabas(this.babas, Main.eigene.get(counter)).size());

				for (Barbarendorf dorf : getFarmableBabas(this.babas, Main.eigene.get(counter))) {
					Koordinaten.X_KOORDINATE.clear();
					Koordinaten.X_KOORDINATE.sendText(dorf.getX());
					Koordinaten.Y_KOORDINATE.clear();
					Koordinaten.Y_KOORDINATE.sendText(dorf.getY());
					Koordinaten.JUMP_TO.click();

					if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2)) && dorf.isFarmable(Main.eigene.get(counter))) {
						sleep(1);
						MainToolbar.OBERFLAECHE.sendText(1);

						if (MainToolbar.ERROR_50_ANGRIFFE.isPresent(Duration.ofMillis(500))) {
							sleep(5); // instead of click on message, because causes errors

							if (counter >= Main.eigene.size() - 1) {
								break;
							} else {

								// Eigenes Dorf wechseln
								counter++;
								if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
									MainToolbar.AUF_WELTKARTE_SUCHEN.click();
								Koordinaten.X_KOORDINATE.clear();
								Koordinaten.X_KOORDINATE.sendText(Main.eigene.get(counter).getX());
								Koordinaten.Y_KOORDINATE.clear();
								Koordinaten.Y_KOORDINATE.sendText(Main.eigene.get(counter).getY());
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
							Barbarendorf tmp = serviceBabarendorf.findById(dorf.getId());
							tmp.setAttackedAt(new LocalDateTime());

							serviceBabarendorf.updateDorf(tmp);
						}
					}
					// wenn ein Barbarendorf kein Barbarendorf mehr ist
					if (Dorfoptionen.NACHRICHT_SENDEN.isPresent()) {
						System.out.println("Dorf: " + dorf.getName() + " ist kein Barbarendorf mehr, wird entfernt! ");
						serviceBabarendorf.deleteDorf(dorf);

					}
				}
			}
			if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
				MainToolbar.AUF_WELTKARTE_SUCHEN.click();

			if (account.getErstesDorf() != null) {
				Koordinaten.X_KOORDINATE.clear();
				Koordinaten.X_KOORDINATE.sendText(serviceEigenesDorf.findBySpielerAndName(account.getErstesDorf().getName(), account.getSpielername()).getX());
				Koordinaten.Y_KOORDINATE.clear();
				Koordinaten.Y_KOORDINATE.sendText(serviceEigenesDorf.findBySpielerAndName(account.getErstesDorf().getName(), account.getSpielername()).getY());
				Koordinaten.JUMP_TO.click();
				Main.sleep(1);
				if (Dorfoptionen.ACTIVE_VILLAGE.isPresent(Duration.ofSeconds(5))) {
					Dorfoptionen.ACTIVE_VILLAGE.click();

				}

			}
			// checke die Points
			for (long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(5); stop > System.nanoTime();) {

				PointService servicePoint = (PointService) context.getBean("pointService");
				ProvinzService serviceProvinz = (ProvinzService) context.getBean("provinzService");

				List<Point> points = servicePoint.findAll();

				points.sort(new Comparator<Point>() {

					public int compare(Point o1, Point o2) {
						return PointDaoImpl.getDistance((int) o1.getX(), 570, (int) o1.getY(), 583) - PointDaoImpl.getDistance((int) o2.getX(), 570, (int) o2.getY(), 583);
					}
				});

				for (int i = 0; i < points.size(); i++) {
					if (points.get(i).isChecked() && points.get(i).getCheckedAt().plusHours(48).isAfter(new LocalDateTime())) {
						points.remove(i);
						i--;

					}

				}

				for (int i = 0; i < points.size() && stop > System.nanoTime(); i++) {
					if (MainToolbar.COMPLETE_BUILDING.isPresent()) {
						MainToolbar.COMPLETE_BUILDING.click();
					}

					if (!Koordinaten.X_KOORDINATE.isPresent(Duration.ofSeconds(1)))
						MainToolbar.AUF_WELTKARTE_SUCHEN.click();

					Koordinaten.X_KOORDINATE.clear();
					Koordinaten.X_KOORDINATE.sendText(points.get(i).getX());
					Koordinaten.Y_KOORDINATE.clear();
					Koordinaten.Y_KOORDINATE.sendText(points.get(i).getY());
					Koordinaten.JUMP_TO.click();

					if (Dorfoptionen.MENUE_MITTE.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Rohstofflager")
							&& !Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content").equals("Freund einladen")) {

						String dorfname = Dorfoptionen.MENUE_MITTE.getAttribute("tooltip-content");

						if (Dorfoptionen.DORFINFORMATIONEN.isPresent(Duration.ofSeconds(2)) && Dorfoptionen.GRUPPEN_HINZUFUEGEN.isPresent(Duration.ofSeconds(2))
								&& !Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2)) && !Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

							Dorfoptionen.DORFINFORMATIONEN.click();

							if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
								int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));
								Dorf tmp = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());

								EigenesDorf eigen = new EigenesDorf(points.get(i).getX(), points.get(i).getY(), dorfname, dorfpunkte, account.getSpielername());
								if (!tmp.equals(eigen)) {

									EigenesDorf eigenALT = serviceEigenesDorf.findByXandY(points.get(i).getX(), points.get(i).getY());

									if (eigenALT.getX() == -1) {
										serviceEigenesDorf.saveDorf(new EigenesDorf(points.get(i).getX(), points.get(i).getY(), dorfname, dorfpunkte, account.getSpielername()));
										System.out.println("Eigenes Dorf hinzugefügt");
									} else {
										eigenALT.setPunkte(dorfpunkte);
										eigenALT.setName(dorfname);
										serviceEigenesDorf.updateDorf(eigenALT);
										System.out.println("Eigenes Dorf geupdated");

									}

								}

							}

							MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

						} else if (Dorfoptionen.PRODUKTION_STEIGERN.isPresent(Duration.ofSeconds(2))) {

							Dorfoptionen.DORFINFORMATIONEN.click();

							if (Dorfinformationen.DORFINFORMATIONEN_PUNKTE.isPresent()) {

								int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));

								Dorf tmp = serviceDorf.findByXandY(points.get(i).getX(), points.get(i).getY());
								Barbarendorf baba = new Barbarendorf(points.get(i).getX(), points.get(i).getY(), dorfname, dorfpunkte);

								if (!tmp.equals(baba)) {

									Barbarendorf eigenALT = serviceBabarendorf.findByXandY(points.get(i).getX(), points.get(i).getY());

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

							MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

						} else if (Dorfoptionen.NACHRICHT_SENDEN.isPresent(Duration.ofSeconds(2))) {

							Dorfoptionen.DORFINFORMATIONEN.click();

							if (Dorfinformationen.DORFINFORMATIONEN_NAME.isPresent()) {
								int dorfpunkte = Integer.parseInt(Dorfinformationen.DORFINFORMATIONEN_PUNKTE.getText().replace(".", ""));
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

							MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);

						}
					} else {
						MainToolbar.OBERFLAECHE.clickCoords(0, 0);

						if (Provinzansicht.PROVINZ_BUTTON_PROVINZDOERVER.isPresent()) {

							Provinz tmp = serviceProvinz.findByXandY(points.get(i).getX(), points.get(i).getY());

							Provinz pNeu = new Provinz(points.get(i).getX(), points.get(i).getY(), Provinzansicht.PROVINZ_NAME.getText(), new LocalDateTime());

							if (!tmp.equals(pNeu)) {
								Provinz eigenALT = serviceProvinz.findByXandY(points.get(i).getX(), points.get(i).getY());

								if (eigenALT.getX() == -1) {
									serviceProvinz.saveProvinz(pNeu);
									System.out.println("Provinz hinzugefügt");

								} else {
									eigenALT.setName(Provinzansicht.PROVINZ_NAME.getText());
									serviceProvinz.updateProvinz(eigenALT);
									System.out.println("Provinz updated");

								}

							}

							MainToolbar.OBERFLAECHE.sendText(Keys.ESCAPE);
						} else {
							// System.out.println("Nichts gefunden");
						}
					}

					Point p = servicePoint.findById(points.get(i).getId());
					p.setChecked(true);
					p.setCheckedAt(new LocalDateTime());

					servicePoint.updatePoint(p);

					System.out.println("Update:" + p);
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
			System.out.println("Baue " + anzahl + " axtkämpfer");
			// baueAxt(anzahl);
		} else {
			System.out.println("Vorraussetzungen für " + anzahl + " axtkämpfer nicht erfüllt!");

		}

		MainToolbar.BAUSCHLEIFE.click();

	}

	private void baueAxt(int anzahl) throws ElementisNotClickable {

		MainToolbar.OBERFLAECHE.sendText("b");
		sleep(1);
		Kaserne.KASERNE_AXTKAEMPFER.scrollToElement("start");
		sleep(1);
		System.out.println(Kaserne.KASERNE_AXTKAEMPFER_NICHT_VORHANDEN.isPresent());
		System.out.println(Kaserne.KASERNE_AXTKAEMPFER.isPresent());

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
				System.out.println("Dauer von neuem Auftrag:" + "\nStudnen: " + zeit[0] + "\nMinuten: " + zeit[1] + "\nSekunden: " + zeit[2]);

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
			System.out.println("Dauer von aktuellem Auftrag:" + "\nStudnen: " + zeit[0] + "\nMinuten: " + zeit[1] + "\nSekunden: " + zeit[2]);
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

		System.out.println("Verbleibende Angriffe: " + verbleibendeAngriffe);
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
			login();
		}

	}

	public static WebDriver getDriver() {
		return Main.driver;
	}

}
