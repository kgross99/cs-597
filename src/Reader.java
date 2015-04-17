import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Reader {

	private static String myToken="::";
	private static String newLine="\n";
	private static ArrayList<EventProject> events;
	private static Boolean debug=true;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		events=new ArrayList<EventProject>();

		// TODO Auto-generated method stub
		
		  try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
		        StringBuilder sb = new StringBuilder();
		        String line="hello";
		        
		        	String event[]=new String[7];
		        	while (line!=null){
					line = br.readLine();
					for (int x=0;x<7;x++){
						if (debug){
							System.out.println("line is "+line);
						}
						
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
						
						event[x]=st.nextToken();
						
					}
					events.add(new EventProject(event[0],event[1],event[2],event[3],event[4],event[5],event[6]));
					
						
		        	}

		   if (debug){
			   System.out.println("read "+events.size()+" events");
		   }
		    
		
		  
			  
		  } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		

	}

	}


	
}
