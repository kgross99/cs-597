

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Scantoken {

	private static String myToken="::";
	private static String newLine="\n";
	private static Boolean debug=true;
	
	public static Vector getValueToken(File file){
		Vector<EventProject> EventList=new Vector<EventProject>();
		
		Scanner inFile1 = null;
		try {
			inFile1 = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get all value and input in StringBuilder
		StringBuilder stringBuilder = new StringBuilder();
		
		while(inFile1.hasNext()) {
			
			  String line = inFile1.nextLine();//get new line
			  line=line.trim();// remove line with space/blank
			  if(line.length()>0){ 
			    stringBuilder.append(line+newLine);
			  }
		    
		}

		//Get all lines to String
		String[] eachLIne = stringBuilder.toString().split(newLine);
		
		for (int i = 0; i < eachLIne.length; i++) {
		
			// get value with token
			String[] valueToke=eachLIne[i].split(myToken);
			if (debug){
				System.out.println("string size "+valueToke.length);
				System.out.println("string "+valueToke[0]);
			}
			
			EventList.add(new EventProject(valueToke[0],valueToke[1],valueToke[2],valueToke[3],valueToke[4],valueToke[5],valueToke[6]));
		}
		
		return EventList;
		
	
	}
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("ServiceLearning.txt");
		Vector EventList=getValueToken(file);
		
		for (int i = 0; i < EventList.size(); i++) {
			
		  String[] eachLineData=(String[]) EventList.get(i);
		  System.out.println("Value of row "+i + newLine);
		  
		  for (int j = 0; j < eachLineData.length; j++) {
			System.out.println(eachLineData[j] + newLine);
			  
		  }
			
		}
	}
	
}
