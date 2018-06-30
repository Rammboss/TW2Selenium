package TB2.TB2;

import java.awt.Point;

public class Provinz {

	private String name;

	private Point coordinaten;

	public Provinz(String name, int x, int y) {
		super();
		this.name = name;
		this.coordinaten = new Point(x, y);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCoordinaten() {
		return coordinaten;
	}

	public void setCoordinaten(Point coordinaten) {
		this.coordinaten = coordinaten;
	}

}
