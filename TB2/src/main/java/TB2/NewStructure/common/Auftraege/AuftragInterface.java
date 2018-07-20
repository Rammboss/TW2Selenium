package TB2.NewStructure.common.Auftraege;

import org.openqa.selenium.WebDriver;


public interface AuftragInterface {
	
	public void run(WebDriver driver);
	
	public boolean check();
	public int getPriority();

	public void setPriority(int priority);
	
	public long getTime();
	public void setTime(long time);
	


}
