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
	ArrayList<String> registered = new ArrayList<String>();
	HashMap<Integer,Integer> teamCounts = new HashMap<Integer,Integer>();
	int currentTeam = 1;
	
	public ExcelHandler(){
		importData();
		registry.add(new String[]{"Name","Time","Team Number"});
		teamCounts.put(1, 5);
		teamCounts.put(2, 5);
		teamCounts.put(3, 5);
		teamCounts.put(4, 5);
		teamCounts.put(5, 5);
		teamCounts.put(6, 5);
		initExportFile();
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
				if(list[0].equals("")){
					break;
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
	
	private boolean hasAllocation(){
		int count = 0;
		for(Integer key: teamCounts.keySet()){
			if(teamCounts.get(key) == 0){
				count += 1;
			}
		}
		return count == 6;
		
	}
	
	private int getCurrentTeam(){
		for(int i = currentTeam; i < 7; i++){
			int team = i;
			if(teamCounts.get(team) != 0){
				teamCounts.replace(team, teamCounts.get(team), teamCounts.get(team)-1);
				currentTeam = i+1;
				if(currentTeam == 7){
					currentTeam = 1;
				}
				return team;
			}
		}
		for(int i = 1; i <= currentTeam; i++){
			int team = i;
			if(teamCounts.get(team) != 0){
				teamCounts.replace(team, teamCounts.get(team), teamCounts.get(team)-1);
				currentTeam = i+1;
				if(currentTeam == 7){
					currentTeam = 1;
				}
				return team;
			}
		}
		return -1;
	}
	
	public boolean isRegistered(String name){
		return registered.contains(name);
	}
	
	public void register(String name){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String[] input = new String[3];
		input[0] = name;
		input[1] = dateFormat.format(date);
		int team = getCurrentTeam();
		if(team != -1){
			input[2] = Integer.toString(team);
		}
		
		registry.add(input);
		registered.add(name);
	}
	
	public String getNicknameForName(String name){
		
		for(HashMap<String,String> hm: entries){
			if(hm.containsValue(name)){
				return hm.get("nickname");
			}
		}
		return "";
		
	}
	
	
	public void initExportFile(){
		
		FileInputStream file = null;
		
		try{
			file = new FileInputStream(new File("registration.xlsx"));
		}
		catch(IOException e){
			return;
		}
		
		try{
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				String[] list = new String[3];
				Iterator<Cell> celIterator = row.cellIterator();
				int cellNum = 0;
				
				while(celIterator.hasNext()){
					
					Cell cell = celIterator.next();
					list[cellNum] = cell.getStringCellValue();
					//System.out.print( list[cellNum] + " ");
					cellNum++;
					
				}
				
				if(list[0].equals("")){
					break;
				}
				else if(!list[0].equals("Name")){
					registry.add(list);
					if(list[2] != null){
						if(!list[2].equals("")){
							int team = Integer.parseInt(list[2]);
							teamCounts.replace(team, teamCounts.get(team), teamCounts.get(team)-1);
						}
					}
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
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
