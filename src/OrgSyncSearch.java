import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;




public class OrgSyncSearch {
	private  ArrayList<EventProject> eventList;
	private  ArrayList<EventProject> validList;
	private ArrayList<EventProject> tempList;
	
	public OrgSyncSearch (ArrayList<EventProject> eventList) throws IOException{
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory index = new RAMDirectory();

		IndexWriterConfig config = new IndexWriterConfig(analyzer);

		IndexWriter w = new IndexWriter(index, config);
		this.eventList=eventList;
		for (int i=0; i<eventList.size();i++){
			if (eventList.get(i).getSlotsize()>0){
				tempList.add(eventList.get(i));
				tempList.get(i).setIndex(i);
				addDoc(w,tempList.get(i));
			}
		}
		
		
		
	}
	
	ArrayList<EventProject> search(String[] fields){
		
	if (fields[0]!=null){ //checks for project name
		for (int i=0;i<tempList.size();i++){
			if (tempList.get(i).getProjectName().equals(fields[0])){
				validList.add(tempList.get(i));
				
			}
		}
	}
	tempList=validList;
	
	if (fields[2]!=null){ //checks for number of slots
		for (int i=0;i<tempList.size();i++){
			if (Integer.getInteger(tempList.get(i).getSlot())>= Integer.getInteger(fields[2])){
				validList.add(tempList.get(i));
				
			}
		}
	}
	tempList=validList;
	
	
	if (fields[5]!=null){//checks for agency name
		for (int i=0;i<tempList.size();i++){
			if (tempList.get(i).getAgency().equals(fields[5])){
				validList.add(tempList.get(i));
				
			}
		}
	}
	
	tempList=validList;
		
	
	
	
	
	
		return validList;
	}
	private static void addDoc(IndexWriter w, EventProject project) throws IOException {
		  Document doc = new Document();
		  doc.add(new TextField("discription", project.getDescriptionDate(), Field.Store.YES));
		  doc.add(new StringField("index", Integer.toString(project.getIndex()), Field.Store.YES));
		  w.addDocument(doc);
		}
}
