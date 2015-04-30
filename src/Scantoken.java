//package ScanToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;

public class Scantoken {      

	private static String myToken=";";
	private static String newLine="\n";
	private static boolean debug=true;
	private static int mergeString=8;
	
	
	public static Vector<EventProject> getValueToken (File file) throws IOException{
		Vector<EventProject> EventList=new Vector<EventProject>();
		
		CSVReader reader = new CSVReader(new FileReader(file), ',', '"', 0);
		
		 List<String[]> allRows = reader.readAll();
//
//		 StringBuilder stringBuilder = new StringBuilder();
//		 String combine_row = null;
	      //Read CSV line by line and use the string array as you want
		 for(String[] row : allRows){
			 if(row.toString().trim().length()>0){
		        String stringRow=null;
		        stringRow=Arrays.toString(row).replace("[", "");
		        stringRow=stringRow.replace("]", "");
		    	//System.out.println("stringRow"+stringRow.);
		        int countToken=0;
		    	 for (int i = 0; i < stringRow.length(); i++) {
					if (stringRow.charAt(i)==';'){
						countToken++;
					}
		    	 }		
					
				
		    	System.out.println("value of countToken"+countToken);
		    	 
	            String[] cellString=stringRow.split(myToken);
	            System.out.println("list size"+cellString.length);
	            String collect_all="";
	            
	            
	            if(cellString.length>mergeString){
	            	for (int i = mergeString; i < cellString.length; i++) {
						collect_all=collect_all+cellString[i];
					}
	            	System.out.println("collect all"+collect_all);
	            	EventList.add(new EventProject(cellString[0],cellString[1],cellString[2],cellString[3],cellString[4],cellString[5],cellString[6],cellString[7],collect_all));
	            }
	            else if (cellString.length<mergeString){
	            	
	            }
	            else{
	            
	            	EventList.add(new EventProject(cellString[0],cellString[1],cellString[2],cellString[3],cellString[4],cellString[5],cellString[6],cellString[7],cellString[8]));
	            	
	            }
	            
	         }
		 }
          
       
   
        return EventList;
        
    }
	public static void main(String[] args) throws IOException{
		File file = new File("D:\\org_projects_test.csv");
	    Vector<EventProject> EventList=getValueToken(file);
		
	for (int i = 0; i < EventList.size(); i++) {
			
		EventProject eachLineData= EventList.get(i);
		  System.out.println("Value of row "+i + newLine);
		  System.out.println(eachLineData.getProjectName()+newLine+
				  eachLineData.getDate()+newLine+
				  eachLineData.getSlot() +newLine+
				  eachLineData.getLink()+newLine+
				  eachLineData.getAgency()+newLine+
				  eachLineData.getAgencyLink() +newLine+
				  eachLineData.getDescriptionDate()+newLine+
				  eachLineData.getDistance()+newLine
		  			+"decripttion here"+eachLineData.getDescription()+newLine);
		  
		 }
	}
	
}
