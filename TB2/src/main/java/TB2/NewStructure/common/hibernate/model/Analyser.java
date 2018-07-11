package TB2.NewStructure.common.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANALYSER")
public class Analyser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "X")
	private int x;
	@Column(name = "Y")
	private int y;
	@Column(name = "BREITE")
	private int breite;
	@Column(name = "RICHTUNG")
	private int richtung;
	@Column(name = "RICHTUNGSCOUNTER")
	private int richtungscounter;

	public Analyser(int x, int y, int breite, int richtung, int richtungscounter) {
		super();
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.richtung = richtung;
		this.richtungscounter = richtungscounter;
	}

	public Analyser() {
		super();
	}

	@Override
	public String toString() {
		return "Analyser [id=" + id + ", x=" + x + ", y=" + y + ", breite=" + breite + ", richtung=" + richtung
				+ ", richtungscounter=" + richtungscounter + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + breite;
		result = prime * result + id;
		result = prime * result + richtung;
		result = prime * result + richtungscounter;
		result = prime * result + x;
		result = prime * result + y;
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
		Analyser other = (Analyser) obj;
		if (breite != other.breite)
			return false;
		if (id != other.id)
			return false;
		if (richtung != other.richtung)
			return false;
		if (richtungscounter != other.richtungscounter)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getRichtung() {
		return richtung;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}

	public int getRichtungscounter() {
		return richtungscounter;
	}

	public void setRichtungscounter(int richtungscounter) {
		this.richtungscounter = richtungscounter;
	}

}
