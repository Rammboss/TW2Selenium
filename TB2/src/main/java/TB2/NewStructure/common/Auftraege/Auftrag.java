package TB2.NewStructure.common.Auftraege;

import org.openqa.selenium.WebDriver;

public abstract class Auftrag implements AuftragInterface {

	private int priority;
	private long time;

	public Auftrag(int priority) {

		this.priority = priority;
		this.time = 0;

	}

	public abstract void run(WebDriver driver);

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int compareTo(AuftragInterface ai) {
		int prioDiff = ai.getPriority() - this.priority;
		if (prioDiff == 0) {
			long tmp = (this.getTime() - ai.getTime());
			tmp = tmp << 32;
			tmp = tmp >> 32;
			return (int) tmp;
		}
		return prioDiff;
	}

	public boolean check() {
		return true;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

}
