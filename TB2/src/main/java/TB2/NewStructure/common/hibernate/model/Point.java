package TB2.NewStructure.common.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

@Entity
@Table(name = "Point")
@Inheritance(strategy = InheritanceType.JOINED)
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "X", nullable = false)
	private int x;
	@Column(name = "Y", nullable = false)
	private int y;

	@Column(name = "CHECKED", nullable = false, columnDefinition = "tinyint default false")
	private boolean checked;

	@Column(name = "CHECKEDAT")
	private LocalDateTime checkedAt;

	public Point(int x, int y, boolean checked, LocalDateTime checkedAt) {
		super();
		this.x = x;
		this.y = y;
		this.checked = checked;
		this.checkedAt = checkedAt;
	}

	public Point(int x, int y, boolean checked) {
		super();
		this.x = x;
		this.y = y;
		this.checked = checked;
	}

	public Point() {
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public LocalDateTime getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(LocalDateTime checkedAt) {
		this.checkedAt = checkedAt;
	}

}
