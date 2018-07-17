package TB2.TB2;

import TB2.NewStructure.common.hibernate.model.EigenesDorf;

public class Account {

	private String spielername;

	private String passwort;
	
	private EigenesDorf erstesDorf;

	public Account(String spielername, String passwort) {
		super();
		this.spielername = spielername;
		this.passwort = passwort;
	}

	public String getSpielername() {
		return spielername;
	}

	public void setSpielername(String spielername) {
		this.spielername = spielername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public EigenesDorf getErstesDorf() {
		return erstesDorf;
	}

	public void setErstesDorf(EigenesDorf erstesDorf) {
		this.erstesDorf = erstesDorf;
	}
	

}
