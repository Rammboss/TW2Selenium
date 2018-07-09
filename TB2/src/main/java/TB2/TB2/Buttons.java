package TB2.TB2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Buttons {
	public static List<Button> provinzen = new ArrayList<Button>();
	public static List<Button> ACCOUNTS = new ArrayList<Button>();

	static {
		provinzen.add(new Button("Kankan", "//*[@id=\\\"click-layer\\\"]"));
		ACCOUNTS.add(new Button("Ausloggen","/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[5]/a"));
		ACCOUNTS.add(new Button("Ausloggen","/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[6]/a"));
		ACCOUNTS.add(new Button("Ausloggen","/html/body/div[2]/div[11]/div[2]/div/div[10]/div[1]/div/div[2]/div[4]/a"));

	}
	
	public static Button LOADING_SCREEN = new Button("Loading Screen", "//*[@id=\"screen-loading\"]");
	public static Button SPIELERNAME = new Button("Spielername", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[1]/input");
	public static Button PASSWORT = new Button("Passwort", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/div[2]/input");
	public static Button SPIELEN = new Button("Spielen", "/html/body/div[3]/div[2]/header/div/div[4]/div[1]/div[1]/form/button");
	public static Button LOGIN = new Button("Login", "/html/body/div[4]/div[2]/div[2]/div[3]/div/div/div[1]/div[1]/div/div[1]/div[2]/div/ul/li[1]/a");
	public static Button HOLZ = new Button("Holz", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]");
	public static Button LEHM = new Button("Lehm", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]");
	public static Button EISEN = new Button("Eisen", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]/div[1]");
	public static Button PROVIANT = new Button("Proviant", "/html/body/div[2]/div[11]/div[1]/div[2]/div[2]/div[2]/div[4]/div[2]/div[1]");
	public static Button DORFANSICHT = new Button("Proviant", "//*[@id=\"building-label-wrapper\"]");

	
	
	
	//Buttons in Dorfansicht
	public static Button DORFANSICHTLAYER = new Button("Dorfansicht Layer", "//*[@id=\"click-layer\"]");

	public static Button SPEICHER = new Button("Speicher", "/html/body/div[2]/div[9]/div[4]/div/div[1]");
	
	public static Button SPEICHER_HOLZ = new Button("Speicher Holz", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[2]/td[1]/div/div/div[1]/span");
	public static Button SPEICHER_LEHM = new Button("Speicher Lehm", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[3]/td[1]/div/div/div[1]/span");
	public static Button SPEICHER_EISEN = new Button("Speicher Eisen", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/table[1]/tbody/tr[4]/td[1]/div/div/div[1]/span");

	
	// Kaserne
	public static Button KASERNE_AXTKAEMPFER = new Button("Axtkämpfer rekrutieren", "/html/body/div[2]/section/div/div[2]/div[1]/div/div[3]/div/div/div[2]/div[2]/div[1]/label[1]/table/tbody/tr[2]/td/div/div[1]");
	public static Button KASERNE_AXTKAEMPFER_VALUE = new Button("Axtkämpfer Anzahl", "//*[@id=\"input-unit-recruit-axe\"]");

	

	public static Button OBERFLAECHE = new Button("Spielfeld","//*[@id=\"click-layer\"]");
	public static Button ZEITLEISTE = new Button("Zeitleiste","/html/body/div[2]/footer/div/div[2]/div[3]/a");
	public static Button LOGOUT = new Button("Ausloggen","//*[@id=\"button-logout\"]");
	public static Button COMPLETE_BUILDING = new Button("Gebäude fertig stellen ","/html/body/div[2]/div[13]/div[2]/div/ul/li[1]/div[1]/div/div[1]/a");



	
	
	
	public static Button ANZAHL_SPEER = new Button("Anzahl Speerträger","/html/body/div[2]/div[12]/div/ul/li[1]/div/div");
	public static Button ANZAHL_SCHWERT = new Button("Anzahl Speerträger","/html/body/div[2]/div[12]/div/ul/li[2]/div/div");
	public static Button ANZAHL_AXT = new Button("Anzahl Axtkämpfer","/html/body/div[2]/div[12]/div/ul/li[3]/div/div");

	public static Button ROHSTOFFLAGER_EINSAMMELN = new Button("Rohstofflager einsammeln","/html/body/div[2]/section/div/div/div[1]/div/div[1]/div/div[3]/div[3]/div[1]/div/div[2]/div/a");
	public static Button ROHSTOFFLAGER_STARTEN = new Button("Rohstofflager starten","/html/body/div[2]/section/div/div/div[1]/div/div[3]/table/tbody/tr[2]/td/a");
	public static Button ROHSTOFFLAGER_TROTZDEM_ABSCHIESSEN = new Button("Rohstofflager trotzdem abschliessen","/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a");

	
	public static Button AUF_WELTKARTE_SUCHEN = new Button("Weltkarte","//*[@id=\"world-map\"]");
	public static Button X_KOORDINATE = new Button("X","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[1]/input");
	public static Button Y_KOORDINATE = new Button("Y","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[2]/input");
	public static Button JUMP_TO = new Button("Dorf auswählen","/html/body/div[2]/footer/div/div[2]/div[1]/div/div/table[1]/tbody/tr/td[3]/div");
	
	public static Button PRODUKTION_STEIGERN = new Button("Produktion steigern","/html/body/div[2]/div[9]/div[4]/div/ul/li[9]/div/div");
	
	// Für lowe Rechner
	public static Button PRODUKTION_STEIGERN2 = new Button("Produktion steigern","/html/body/div[2]/div[9]/div[5]/div/ul/li[9]/div/div");

	

	public static Button GLOBALE_VORLAGENLISTE = new Button("Globale Vorlagenliste","/html/body/div[2]/section/div/div[2]/div[1]/div/div[2]/div[2]/ul/li[2]/a");
	public static Button GLOBALE_VORLAGENLISTE_STATUS= new Button("Globale Vorlagenliste Status","/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div/div");
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN= new Button("Globale Vorlagenliste bearbeiten","/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/a[2]");
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_1 = new Button("Globale Vorlagenliste bearbeiten","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div");
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_HOTKEY_ANGRIFF= new Button("Globale Vorlagenliste Hotkey Angriff","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[5]/tbody/tr[2]/td/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]/div/span");
	
	
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SPEER= new Button("Globale Vorlagenliste Anzahl Speerträger","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[1]/div/input");
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_SCHWERT= new Button("Globale Vorlagenliste Anzahl Schwertkämpfer","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[1]/td[2]/div/input");
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_BARBAREN= new Button("Globale Vorlagenliste Anzahl Barbaren","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[2]/td[1]/div/input");
	public static Button GLOBALE_VORLAGENLISTE_BEARBEITEN_ANZAHL_PALADIN= new Button("Globale Vorlagenliste Anzahl Paladin","/html/body/div[4]/div/div/div/div/div/div[1]/div/table[3]/tbody/tr[6]/td[2]/div/input");

	
	
	public static Button GLOBALE_VORLAGENLISTE_SPEICHERN= new Button("Globale Vorlagenliste speichern","/html/body/div[4]/div/div/div/div/footer/ul/li/a");

	
	public static Button ERROR_50_ANGRIFFE= new Button("50 Angriffe","/html/body/div[2]/div[7]/div/div[3]/div");

	public static Button NEXT_VILLAGE= new Button("Nextes Dorf","//*[@id=\"switch-village-next\"]");

	
	public static Button ACTIVE_VILLAGE= new Button("Aktives Dorf 1 festlegen","/html/body/div[2]/div[9]/div[4]/div/ul/li[8]/div/div");
	public static Button ACTIVE_VILLAGE2= new Button("Aktives Dorf2 festlegen","/html/body/div[2]/div[9]/div[5]/div/ul/li[8]/div/div"); // low pc

	public static Button NACHRICHT_SENDEN = new Button("Nachricht senden 1","/html/body/div[2]/div[9]/div[4]/div/ul/li[7]/div/div");
	public static Button NACHRICHT_SENDEN2 = new Button("Nachricht senden 2","/html/body/div[2]/div[9]/div[5]/div/ul/li[7]/div/div"); //low pc

	public static Button TABLE_VILLAGES_IN_PROVINZ = new Button("Tabelle - Frende Dörfer in Provinz","/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[2]/tbody");
	public static Button TABLE_UNITS = new Button("Tabelle - Einheiten Dorf","/html/body/div[2]/section/div/div/div[1]/div[2]/div[6]/div/table/tbody");
	public static Button TABLE_UNITS_ROWS = new Button("Tabelle - Einheiten Dorf Anzahl der Angriffe","/html/body/div[2]/section/div/div/div[1]/div[2]/div[6]/div/table/tbody/tr");
	public static Button TABLE_OWN_VILLAGES_IN_PROVINZ = new Button("Tabelle - Eigene Dörfer in Provinz","/html/body/div[2]/section/div/div/div[2]/div/div[1]/table[1]/tbody");

	
	
	public static Button EINSTELLUNGEN= new Button("Einstellungen öffnen","/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[10]/a");

	public static Button EINSTELLUNGEN_SPIEL= new Button("Einstellungen Spiel Tab öffnen","/html/body/div[2]/section/div/div/div[1]/div/div[2]/div/div/div/a");

	public static Button EINSTELLUNGEN_SPIEL_ANNIMATION1= new Button("Annimationen auf 1 setzten","/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[3]/td[1]/div[2]/div/a[1]");

	public static Button EINSTELLUNGEN_SPIEL_MUSIK_SOUND= new Button("Musik an/aus","/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[2]/td[2]/div[2]/div/a[1]");
	public static Button EINSTELLUNGEN_SPIEL_EFFEKT_SOUND= new Button("Effekte Sound an/aus","/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[3]/tbody/tr[3]/td[2]/div[2]/div/a[1]");

	
	public static Button EINSTELLUNGEN_SPIEL_AUSGEHENDE_BEFEHLE_ANZEIGEN = new Button("Einstellungen Spiel Tab Ausgehende Befehle Anzeigen","/html/body/div[2]/section/div/div/div[2]/div/div[2]/table[2]/tbody/tr[8]/td[2]/label");

	public static Button EINHEITEN_UEBERSICHT = new Button("Einheiten Übersicht aktuelles Dorf","/html/body/div[2]/footer/div/div[2]/div[2]/ul/li[5]/a");

	
	


}
