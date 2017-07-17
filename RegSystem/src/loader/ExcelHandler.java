package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {
	
	ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String,String>>();
	public ArrayList<Object> names = new ArrayList<Object>();
	
	
	
	public ExcelHandler(){
		importData();
	}
	
	
	private void importData(){
		try{
			FileInputStream file = new FileInputStream(new File("data/name_list.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				String[] list = new String[4];
				Iterator<Cell> celIterator = row.cellIterator();
				int cellNum = 0;
				
				while(celIterator.hasNext()){
					
					Cell cell = celIterator.next();
					list[cellNum] = cell.getStringCellValue();
					//System.out.print( list[cellNum] + " ");
					cellNum++;
					
				}
				System.out.println();
				if(list[3] == null){
					list[3] = list[2];
				}
				HashMap<String,String> entry = new HashMap<String,String>();
				System.out.println(list[2] + " " + list[0]);
				entry.put("full_name", list[2] + " " + list[0]);
				entry.put("nickname", list[3]);
				
				names.add(entry.get("full_name"));
				entries.add(entry);
				
			}
			
			workbook.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	public void exportData(){
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("test");

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
		data.put("2", new Object[] {1, "Amit", "Shukla"});
		data.put("3", new Object[] {2, "Lokesh", "Gupta"});
		data.put("4", new Object[] {3, "John", "Adwards"});
		data.put("5", new Object[] {4, "Brian", "Schultz"});
		  
		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset)
		{
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr)
		    {
		       Cell cell = row.createCell(cellnum++);
		       if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Integer)
		            cell.setCellValue((Integer)obj);
		    }
		}
		try
		{
		    //Write the workbook in file system
		    FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
		    workbook.write(out);
		    workbook.close();
		    out.close();
		    System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
	
	
}
