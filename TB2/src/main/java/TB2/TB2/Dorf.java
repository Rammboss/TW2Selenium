package TB2.TB2;

import java.awt.Point;

public class Dorf {

	private String name;
	private int punkte;
	private Point coordinaten;

	public Dorf(String name, int punkte, Point coordinaten) {
		super();
		this.name = name;
		this.punkte = punkte;
		this.coordinaten = coordinaten;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinaten == null) ? 0 : coordinaten.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dorf other = (Dorf) obj;
		if (coordinaten == null) {
			if (other.coordinaten != null)
				return false;
		} else if (!coordinaten.equals(other.coordinaten))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public Point getCoordinaten() {
		return coordinaten;
	}

	public void setCoordinaten(Point coordinaten) {
		this.coordinaten = coordinaten;
	}

}
