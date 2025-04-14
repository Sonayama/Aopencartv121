package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	
@DataProvider(name="LoginData")
public String[][] getloginData() throws IOException
{
	String path=".\\testData\\Opencart_LoginData.xlsx";
	
	ExcelUtil xlutil=new ExcelUtil(path);
	int totalrows=xlutil.getRowCount("Sheet1");
	int totalcolumns=xlutil.getCellcount("Sheet1", 1);
	String logindata[][]=new String[totalrows][totalcolumns];
	for(int i=1;i<=totalrows;i++)
	{
		for(int j=0;j<totalcolumns;j++)
		{
			logindata[i-1][j]=xlutil.getCelldata("Sheet1", i, j);
		}
	}
	
	
	return logindata;
	
}
}