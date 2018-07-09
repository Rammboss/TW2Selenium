package TB2.TB2;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	static {
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

		driver = new FirefoxDriver();

	}

	public static WebDriver driver;

	public static int index;

	public List<Barbarendorf> babas;

	public Main() {
		Main.index = 0;
	}

	public void login() {
		driver.get("https://de.tribalwars2.com/");

		Buttons.SPIELERNAME.sendText("Rammboss");
		Buttons.PASSWORT.sendText("kalterhund");
		Buttons.SPIELEN.click();

		if (Buttons.LOGIN.isPresent(4))
			Buttons.LOGIN.click();
		sleep(1);
		long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(30);
		;
		while (Buttons.LOADING_SCREEN.isPresent() && stop > System.nanoTime()) {
			sleep(1);
		}

	}

	public static void main(String[] args) {

		Main app = new Main();
		try {
			app.runTask();
		} catch (Exception e) {
			System.out.println("Ein unerwarteter Fehler ist aufgetreten!");
			e.printStackTrace();
			app.restartDriver();
			app.runTask();
		}

	}

	private void runTask() {
		this.login();
		this.disableSound();

		this.ausgehendenAngriffeVerbergen();

		// Privinzen einlesen
		List<Point> provinzen = new ArrayList<Point>();
		provinzen.add(new Point(584, 568)); // Hohnsurfing
		provinzen.add(new Point(572, 567)); // Folzol
		provinzen.add(new Point(579, 555)); // Markmarkhohn
		provinzen.add(new Point(595, 566)); // Balbalzol
		provinzen.add(new Point(590, 554)); // Kanheim
		provinzen.add(new Point(580, 580)); // Daufingbal
		provinzen.add(new Point(568, 578)); // Foldauheim


		List<Dorf> dorfListe = this.initProvinzen(provinzen);

		// Befehle wieder anzeigen
		this.ausgehendenAngriffeVerbergen();

		this.babas = this.getBabarendoerfer(dorfListe);

		while (true) {
			this.disableSound();

			// Rohstoffe checken
			sleep(1);

			Buttons.OBERFLAECHE.sendText("v");
			sleep(1);
			if (!Buttons.DORFANSICHT.isPresent()) {
				Buttons.OBERFLAECHE.sendText("v");
			}
			sleep(1);
			Buttons.DORFANSICHTLAYER.clickCoords(100, 300);
			sleep(1);

			String[] holz = Buttons.SPEICHER_HOLZ.getText().split(" / ");
			String[] lehm = Buttons.SPEICHER_LEHM.getText().split(" / ");
			String[] eisen = Buttons.SPEICHER_EISEN.getText().split(" / ");

			int max = Integer.parseInt(holz[1].replace(".", ""));
			int currentHolz = Integer.parseInt(holz[0].replace(".", ""));
			int currentLehm = Integer.parseInt(lehm[0].replace(".", ""));

			int currentEisen = Integer.parseInt(eisen[0].replace(".", ""));

			Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);
			Buttons.OBERFLAECHE.sendText("v");

			if ((currentHolz >= max || currentLehm >= max || currentEisen >= max)
					&& Integer.parseInt(Buttons.PROVIANT.getText()) > 20) {
				System.out.println("Baue 20 axtkämpfer");
				baue20Axt();
			} else {
				System.out.println("Vorraussetzungen für 20 axtkämpfer nicht erfüllt!");

			}

			this.initVorlagen(this.getAnzahlAngriffe());

			this.rohstofflagerCheck();

			// Koordinaten eingen
			Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();

			int counter = 0;
			System.out.println("Farmen vorhanden: " + getFarmableBabas(this.babas).size());
			for (Barbarendorf dorf : getFarmableBabas(this.babas)) {
				Buttons.X_KOORDINATE.clear();
				Buttons.X_KOORDINATE.sendText(dorf.getCoordinaten().getX());
				Buttons.Y_KOORDINATE.clear();
				Buttons.Y_KOORDINATE.sendText(dorf.getCoordinaten().getY());
				Buttons.JUMP_TO.click();
				Main.sleep(300, TimeUnit.MILLISECONDS);
				if (Buttons.PRODUKTION_STEIGERN.isPresent(1000, TimeUnit.MILLISECONDS)
						|| Buttons.PRODUKTION_STEIGERN2.isPresent(1000, TimeUnit.MILLISECONDS) && dorf.isFarmable()) {
					Buttons.OBERFLAECHE.sendText(1);

					if (Buttons.ERROR_50_ANGRIFFE.isPresent(500, TimeUnit.MILLISECONDS)) {
						sleep(5); // instead of click on message, because causes errors

						if (counter >= OwnVillage.OWN.size() - 1) {
							break;
						} else {

							// Eigenes Dorf wechseln
							counter++;
							if (!Buttons.X_KOORDINATE.isPresent(1))
								Buttons.AUF_WELTKARTE_SUCHEN.click();
							Buttons.X_KOORDINATE.clear();
							Buttons.X_KOORDINATE.sendText(OwnVillage.OWN.get(counter).getCoordinaten().getX());
							Buttons.Y_KOORDINATE.clear();
							Buttons.Y_KOORDINATE.sendText(OwnVillage.OWN.get(counter).getCoordinaten().getY());
							Buttons.JUMP_TO.click();
							if (Buttons.ACTIVE_VILLAGE.isPresent(5)) {
								Buttons.ACTIVE_VILLAGE.click();

							}
							if (Buttons.ACTIVE_VILLAGE2.isPresent(5)) {
								Buttons.ACTIVE_VILLAGE2.click();

							}

							this.initVorlagen(this.getAnzahlAngriffe());

							if (!Buttons.X_KOORDINATE.isPresent(1))
								Buttons.AUF_WELTKARTE_SUCHEN.click();
						}

					} else {
						dorf.setAttackedAt(new Timestamp(System.currentTimeMillis()));
					}
				}
			}
			if (!Buttons.X_KOORDINATE.isPresent(1))
				Buttons.AUF_WELTKARTE_SUCHEN.click();
			Buttons.X_KOORDINATE.clear();
			Buttons.X_KOORDINATE.sendText(OwnVillage.OWN.get(0).getCoordinaten().getX());
			Buttons.Y_KOORDINATE.clear();
			Buttons.Y_KOORDINATE.sendText(OwnVillage.OWN.get(0).getCoordinaten().getY());
			Buttons.JUMP_TO.click();
			Main.sleep(1);
			if (Buttons.ACTIVE_VILLAGE.isPresent(5)) {
				Buttons.ACTIVE_VILLAGE.click();

			}
			if (Buttons.ACTIVE_VILLAGE2.isPresent(5)) {
				Buttons.ACTIVE_VILLAGE2.click();

			}

			System.out.println("Dorfliste size:" + dorfListe.size());
			System.out.println("Aktueller Stand: " + Main.index);
			
			// checkDoerfer(600, dorfListe, this);
			for (long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(15); stop > System.nanoTime();) {

				if (Buttons.COMPLETE_BUILDING.isPresent()) {
					Buttons.COMPLETE_BUILDING.click();
				}
				sleep(5);
			}

			System.out.println("Driver wird neugestartet!");
			this.restartDriver();
		}
	}

	private void baue20Axt() {

		Buttons.OBERFLAECHE.sendText("b");
		sleep(1);
		Buttons.KASERNE_AXTKAEMPFER.scrollToElement("start");
		if (Buttons.KASERNE_AXTKAEMPFER.isPresent()) {
			Buttons.KASERNE_AXTKAEMPFER.click();
		}
		Buttons.KASERNE_AXTKAEMPFER_VALUE.clear();
		Buttons.KASERNE_AXTKAEMPFER_VALUE.sendText(20);
		Buttons.KASERNE_AXTKAEMPFER_VALUE.sendText(Keys.ENTER);

		Buttons.OBERFLAECHE.sendText(Keys.ESCAPE);

	}

	private void disableSound() {
		Buttons.EINSTELLUNGEN.click();
		Buttons.EINSTELLUNGEN_SPIEL.click();
		Buttons.EINSTELLUNGEN_SPIEL_ANNIMATION1.scrollToElement("start");
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
			Buttons.X_KOORDINATE.sendText(dorfListe.get(Main.index).getCoordinaten().getX());
			Buttons.Y_KOORDINATE.clear();
			Buttons.Y_KOORDINATE.sendText(dorfListe.get(Main.index).getCoordinaten().getY());
			sleep(500, TimeUnit.MILLISECONDS);
			Buttons.JUMP_TO.click();

			if (Buttons.NACHRICHT_SENDEN.isPresent(2, TimeUnit.SECONDS)
					|| Buttons.NACHRICHT_SENDEN2.isPresent(2, TimeUnit.SECONDS)) {
				Main.index++;
				continue;
			}

			Barbarendorf baba = new Barbarendorf(dorfListe.get(Main.index).getPunkte(),
					dorfListe.get(Main.index).getCoordinaten());
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

	private static List<Barbarendorf> getFarmableBabas(List<Barbarendorf> babas) {
		List<Barbarendorf> tmp = new ArrayList<Barbarendorf>();
		for (Barbarendorf barbarendorf : babas) {

			if (barbarendorf.isFarmable()) {
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

			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN.clear();
			int tmp = Integer.parseInt(Buttons.ANZAHL_AXT.getText().replace(".", ""));
			System.out.println(tmp);
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN.sendText(tmp / verbleibendeAngriffe);

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN.clear();
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN.sendText(20);

			}
			// SPEER
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER
					.sendText(Integer.parseInt(Buttons.ANZAHL_SPEER.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(Buttons.ANZAHL_SPEER.getText().replace(".", ""));

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.clear();

				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER.sendText(10);

			}
			// SCHWERT
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();
			Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(
					Integer.parseInt(Buttons.ANZAHL_SCHWERT.getText().replace(".", "")) / verbleibendeAngriffe);

			tmp = Integer.parseInt(Buttons.ANZAHL_SCHWERT.getText().replace(".", ""));

			if (tmp < 500) {
				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.clear();

				Buttons.GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT.sendText(10);

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
				Point coordinaten = null;

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
						coordinaten = new Point(x, y);
						OwnVillage village = new OwnVillage(name, punkte, coordinaten);
						OwnVillage.OWN.add(village);
						break;

					default:
						break;
					}
					counter++;
				}
			}

			OwnVillage.OWN.sort(new Comparator<OwnVillage>() {

				public int compare(OwnVillage o1, OwnVillage o2) {
					return o1.getName().compareTo(o2.getName());
				}

			});

			for (OwnVillage dorf : OwnVillage.OWN) {

				System.out.println(dorf);

			}

			// Fremde Dörfer durchsuchen
			WebElement element = Buttons.TABLE_VILLAGES_IN_PROVINZ.getWebelement();

			String elementSource = element.getAttribute("innerHTML");

			Pattern pattern = Pattern.compile("ng-binding\">(.*?)</td>");
			Matcher matcher = pattern.matcher(elementSource);
			int counter = 0;
			String name = "";
			int punkte = 0;
			Point coordinaten = null;

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
					coordinaten = new Point(x, y);
					dorfListe.add(new Dorf(name, punkte, coordinaten));
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

	public List<Barbarendorf> getBabarendoerfer(List<Dorf> liste) {
		List<Barbarendorf> babas = new ArrayList<Barbarendorf>();

		for (Dorf dorf : liste) {
			if (dorf.getName().equals("Barbarendorf")) {
				babas.add(new Barbarendorf(dorf.getPunkte(), dorf.getCoordinaten()));
			}

		}
		return babas;

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
