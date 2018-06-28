package TB2.TB2;

import java.awt.Point;
import java.util.Stack;

public class OwnVillage extends Dorf{
	
	public static Stack<OwnVillage> OWN = new Stack<OwnVillage>();
	
	static {
		OWN.add(new OwnVillage("001", 1800, new Point(426, 451)));
		OWN.add(new OwnVillage("002", 1800, new Point(428, 451)));

	}

	public OwnVillage(String name, int punkte, Point coordinaten) {
		super(name, punkte, coordinaten);
	}
	
	

}
