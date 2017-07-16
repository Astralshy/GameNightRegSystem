package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	ArrayList<String[]> registry = new ArrayList<String[]>();
	
	
	public ExcelHandler(){
		importData();
		registry.add(new String[]{"Name","Time"});
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
				if(list[3] == null){
					list[3] = list[2];
				}
				if(!list[0].equals("Last Name")){
					HashMap<String,String> entry = new HashMap<String,String>();
					entry.put("full_name", list[2] + " " + list[0]);
					entry.put("nickname", list[3]);
					
					names.add(entry.get("full_name"));
					entries.add(entry);
				}
				
			}
			
			workbook.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void register(String name){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String[] input = new String[2];
		input[0] = name;
		input[1] = dateFormat.format(date);
		
		registry.add(input);
		
	}
	
	public String getNicknameForName(String name){
		
		for(HashMap<String,String> hm: entries){
			if(hm.containsValue(name)){
				return hm.get("nickname");
			}
		}
		return "";
		
	}
	
	public void exportData(){
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("test");

		  
		//Iterate over data and write to sheet
		int rownum = 0;
		for (String[] list : registry)
		{
		    Row row = sheet.createRow(rownum++);
		    int cellnum = 0;
		    for (String s : list)
		    {
		       Cell cell = row.createCell(cellnum++);
		       cell.setCellValue(s);
		    }
		}
		try
		{
		    //Write the workbook in file system
		    FileOutputStream out = new FileOutputStream(new File("registration.xlsx"));
		    workbook.write(out);
		    workbook.close();
		    out.close();
		    System.out.println("Output written successfully on disk.");
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
	
	
}
