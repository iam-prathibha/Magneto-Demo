package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginTestData")
	public String [][] getData() throws IOException{
		String path="./test-data/LoginTestData.xlsx";
		
		ExcelUtilityFile excel= new ExcelUtilityFile(path);
		
		int rowsCount= excel.getRowCount("Sheet1");
		int colsCount= excel.getCellCount("Sheet1", 1);
		
		
		
		String loginData[][]= new String[rowsCount][colsCount];
		
		for(int i=1;i<=rowsCount;i++)
		{
			for(int j=0;j<colsCount;j++)
			{
				loginData[i-1][j]= excel.getCellData("Sheet1", i, j);
				//System.out.print(loginData[i-1][j]);
			}
			//System.out.println("");
		}
		
		return loginData;
	}
	
}
