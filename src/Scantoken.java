package Mytoken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Scantoken {

	private static String myToken="::";
	private static String newLine="\n";
	
	public static Vector getValueToken(File file){
		Vector outputData=new Vector();
		
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
			outputData.add(valueToke);
		}
		
		return outputData;
		
	
	}
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("D:\\Working\\Odesk_testing\\ServiceLearning.txt");
		Vector outputData=getValueToken(file);
		
		for (int i = 0; i < outputData.size(); i++) {
			
		  String[] eachLineData=(String[]) outputData.get(i);
		  System.out.println("Value of row "+i + newLine);
		  
		  for (int j = 0; j < eachLineData.length; j++) {
			System.out.println(eachLineData[j] + newLine);
			  
		  }
			
		}
	}
	
}
