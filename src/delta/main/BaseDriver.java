package delta.main;

import org.testng.annotations.DataProvider;

public class BaseDriver {
	
	@DataProvider
	public String[][] getScenarios()
	{
		String[][] data=new String[2][1];
		data[0][0]="Scenario1";
		data[1][0]="Scenario1";
		
		return data;
		
	}

}
