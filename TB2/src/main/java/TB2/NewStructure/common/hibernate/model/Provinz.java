package TB2.NewStructure.common.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "Provinz")
@Inheritance(strategy = InheritanceType.JOINED)
public class Provinz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "X", nullable = false)
	private int x;

	@Column(name = "Y", nullable = false)
	private int y;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SCANNEDAT")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime scannedAt;

	public Provinz(int x, int y, String name, LocalDateTime scannedAt) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.scannedAt = scannedAt;
	}

	public Provinz() {
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

	public LocalDateTime getScannedAt() {
		return scannedAt;
	}

	public void setScannedAt(LocalDateTime scannedAt) {
		this.scannedAt = scannedAt;
	}

}
