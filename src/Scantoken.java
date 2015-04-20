package ScanToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Scantoken {

	private static String myToken="::";
	private static String newLine="\n";
	private static boolean debug=true;
	private static int range=8; 
	
	
	public static Vector<EventProject> getValueToken (File file){
		Vector<EventProject> EventList=new Vector<EventProject>();
		FileInputStream x = null;
		try {
			x = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Create Workbook instance holding reference to .xlsx file
        Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        String combine_row = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
            Row row = (Row) iterator.next();
            
            String stringRow=row.toString().trim();
            if (stringRow.length()>0){
            	for (Iterator<Cell> iterator2 = row.iterator(); iterator2
                    .hasNext();) {
                Cell cell = (Cell) iterator2.next();
                String[] cellString=cell.getStringCellValue().split(myToken);
                System.out.println(cellString.length);
                
                if(cellString.length==range){
                	stringBuilder.append(cell.getStringCellValue()+newLine);	
                }
                else{
                	combine_row=combine_row+cell.getStringCellValue().replace('\n', '\r');
                	if (combine_row.split(myToken).length==range){
                		stringBuilder.append(combine_row+newLine);
                		combine_row="";
                	}
                }
                               
              }
            }               
        }
       
        try {
			x.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String[] eachLIne = stringBuilder.toString().split(newLine);
        System.out.println("each line lenght"+ eachLIne.length);
       for (int i = 0; i < eachLIne.length; i++) {
    		
			// get value with token
			String[] valueToke=eachLIne[i].split(myToken);
			//if (debug){
			//	System.out.println("string size "+valueToke.length);
			//	System.out.println("string "+valueToke[0]);
			//}
			for (int j = 0; j < valueToke.length; j++) {
				System.out.println("Value "+ j +valueToke[j]);
			}
			
			EventList.add(new EventProject(valueToke[0],valueToke[1],valueToke[2],valueToke[3],valueToke[4],valueToke[5],valueToke[6],valueToke[7]));
		}
		
        return EventList;
        
    }
	//public static void main(String[] args) throws FileNotFoundException{
		//File file = new File("D:\\Working\\Odesk_testing\\Test.xlsx");
	//	Vector<EventProject> EventList=getValueToken(file);
		
	//	for (int i = 0; i < EventList.size(); i++) {
			
	//	EventProject eachLineData= EventList.get(i);
	//	  System.out.println("Value of row "+i + newLine);
	//	  System.out.println(eachLineData.getProjectName()+newLine+eachLineData.getDate()+newLine+eachLineData.getSlot()
		//		  +newLine+eachLineData.getLink()+newLine+eachLineData.getAgency()+newLine+eachLineData.getAgencyLink()
		//		  +newLine+eachLineData.getDescriptionDate()+newLine+eachLineData.getDistance()+newLine);
		  
		// }
	//}
	
}