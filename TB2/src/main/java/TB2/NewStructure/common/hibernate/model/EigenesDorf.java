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

}
