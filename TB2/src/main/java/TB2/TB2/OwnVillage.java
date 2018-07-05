package TB2.TB2;

import java.awt.Point;
import java.util.ArrayList;

public class OwnVillage extends Dorf{
	
	public static ArrayList<OwnVillage> OWN = new ArrayList<OwnVillage>();

	public OwnVillage(String name, int punkte, Point coordinaten) {
		super(name, punkte, coordinaten);
	}

	@Override
	public String toString() {
		return "OwnVillage [getName()=" + getName() + ", getPunkte()=" + getPunkte() + ", getCoordinaten()="
				+ getCoordinaten() + "]";
	}
	
	
	
	

}
