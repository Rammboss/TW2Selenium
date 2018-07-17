package TB2.NewStructure.common.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EigenesDorf")
@DiscriminatorValue("EigenesDorf")
public class EigenesDorf extends Dorf {

	@Column(name = "SPIELER")
	private String spieler;

	public EigenesDorf(int x, int y, String name, int punkte, String spieler) {
		super(x, y, name, punkte);

		this.spieler = spieler;

	}

	public EigenesDorf() {
		super(500, 500, "test", 300);
		
		this.spieler = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((spieler == null) ? 0 : spieler.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EigenesDorf other = (EigenesDorf) obj;
		if (spieler == null) {
			if (other.spieler != null)
				return false;
		} else if (!spieler.equals(other.spieler))
			return false;
		return true;
	}
	
	

}
