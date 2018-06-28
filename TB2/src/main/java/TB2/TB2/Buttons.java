package TB2.TB2;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
	public static List<Button> provinzen = new ArrayList<Button>();

	static {
		provinzen.add(new Button("Kankan", "//*[@id=\\\"click-layer\\\"]", Main.driver));
	}
	
	public static Button LOADING_SCREEN = new Button("Loading Screen", "//*[@id=\"screen-loading\"]",Main.driver);
	public static Button SPIELERNAME = new Button("Spielername", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[1]/input",Main.driver);
	public static Button PASSWORT = new Button("Passwort", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[2]/input",Main.driver);
	public static Button SPIELEN = new Button("Spielen", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/button",Main.driver);
	public static Button LOGIN = new Button("Login", "/html/body/div[4]/div[2]/div[2]/div[3]/div/div/div[1]/div[1]/div/div[1]/div[2]/div/ul/li[1]/a",Main.driver);
	public static Button HOLZ = new Button("Holz", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]",Main.driver);
	public static Button OBERFLAECHE = new Button("Spielfeld","//*[@id=\"click-layer\"]",Main.driver);
	public static Button ZEITLEISTE = new Button("Zeitleiste","/html/body/div[2]/footer/div/div[2]/div[3]/a",Main.driver);

	
	
	
	public static Button ANZAHL_SPEER = new Button("Anzahl Speerträger","/html/body/div[2]/div[12]/div/ul/li[1]/div/div",Main.driver);
	public static Button ANZAHL_SCHWERT = new Button("Anzahl Speerträger","/html/body/div[2]/div[12]/div/ul/li[2]/div/div",Main.driver);
	public static Button ANZAHL_AXT = new Button("Anzahl Axtkämpfer","/html/body/div[2]/div[12]/div/ul/li[3]/div/div",Main.driver);

	public static Button ROHSTOFFLAGER_EINSAMMELN = new Button("Rohstofflager einsammeln","/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div/a",Main.driver);
	public static Button ROHSTOFFLAGER_STARTEN = new Button("Rohstofflager starten","/html/body/div[2]/section/div/div/div[1]/div/div[3]/table/tbody/tr[2]/td/a",Main.driver);
	public static Button ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN = new Button("Rohstofflager trotzdem abschliessen","/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a",Main.driver);

	
	public static Button AUF_WELTKARTE_SUCHEN = new Button("Weltkarte","//*[@id=\"world-map\"]",Main.driver);
	public static Button X_KOORDINATE = new Button("X","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[1]/input",Main.driver);
	public static Button Y_KOORDINATE = new Button("Y","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[2]/input",Main.driver);
	public static Button JUMP_TO = new Button("Dorf auswählen","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[3]/div",Main.driver);
	
	public static Button PRODUKTION_STEIGERN = new Button("Produktion steigern","/html/body/div[2]/div[9]/div[4]/div/ul/li[9]/div/div",Main.driver);

	public static Button GLOBALE_VORLAGENLISTE = new Button("Globale Vorlagenliste","/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/div[2]/ul/li[2]/a",Main.driver);
	public static Button GLOBALE_VORLAGENLISTE_STATUS= new Button("Globale Vorlagenliste Status","/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div/div",Main.driver);
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN= new Button("Globale Vorlagenliste bearbeiten","/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/a[2]",Main.driver);
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1 = new Button("Globale Vorlagenliste bearbeiten","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div",Main.driver);
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF= new Button("Globale Vorlagenliste Hotkey Angriff","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]/div/span",Main.driver);
	
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER= new Button("Globale Vorlagenliste Anzahl Speerträger","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[1]/div/input",Main.driver);
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT= new Button("Globale Vorlagenliste Anzahl Schwertkämpfer","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[2]/div/input",Main.driver);
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN= new Button("Globale Vorlagenliste Anzahl Barbaren","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[2]/td[1]/div/input",Main.driver);

	
	
	public static Button GLOBALE_VORLAGENLISTE_SPEICHERN= new Button("Globale Vorlagenliste speichern","/html/body/div[4]/div/div/div/div/footer/ul/li/a",Main.driver);

	
	public static Button ERROR_50_ANGRIFFE= new Button("50 Angriffe","/html/body/div[2]/div[7]/div/div[3]/div",Main.driver);

	public static Button NEXT_VILLAGE= new Button("Nextes Dorf","//*[@id=\"switch-village-next\"]",Main.driver);

	
	public static Button ACTIVE_VILLAGE= new Button("Aktives Dorf festlegen","/html/body/div[2]/div[9]/div[4]/div/ul/li[8]/div/div",Main.driver);
	public static Button NACHRICHT_SENDEN = new Button("Nachricht senden","/html/body/div[2]/div[9]/div[4]/div/ul/li[7]/div/div",Main.driver);

	
	
	public static Button EINSTELLUNGEN= new Button("Einstellungen öffnen","/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[10]/a",Main.driver);

	public static Button EINSTELLUNGEN_SPIEL= new Button("Einstellungen Spiel Tab öffnen","/html/body/div[2]/section/div/div/div[1]/div/div[2]/div/div/div/a",Main.driver);

	public static Button EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN = new Button("Einstellungen Spiel Tab Ausgehende Befehle Anzeigen","/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[8]/td[2]/label",Main.driver);

	public static Button EINHEITEN_UEBERSICHT = new Button("Einheiten Übersicht aktuelles Dorf","/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[5]/a",Main.driver);

	
	


}
