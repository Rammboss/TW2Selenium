package TB2.NewStructure.common.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "Dorf")
//@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Dorf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "X", nullable = false)
	private int x;
	@Column(name = "Y", nullable = false)
	private int y;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PUNKTE")
	private int punkte;

	public Dorf(int x, int y, String name, int punkte) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.punkte = punkte;

	}

	public Dorf() {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + punkte;
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
		Dorf other = (Dorf) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (punkte != other.punkte)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dorf [id=" + id + ", x=" + x + ", y=" + y + ", name=" + name + "]";
	}

}
