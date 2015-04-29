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
	private ArrayList<EventProject> eventList;
	private ArrayList<EventProject> validList;
	private ArrayList<EventProject> tempList;
	private StandardAnalyzer analyzer;
	private Directory index;
	private IndexWriterConfig config;
	private IndexWriter w;
	private int LinksPerPage = 10;
	private IndexReader reader;
	private IndexSearcher searcher;
	private TopScoreDocCollector collector;
	private int DocId;
	private Document tempDoc;

	public OrgSyncSearch(ArrayList<EventProject> eventList) throws IOException {
		tempList=new ArrayList<EventProject>();
		analyzer = new StandardAnalyzer();
		index = new RAMDirectory();
		validList=new ArrayList<EventProject>();
		config = new IndexWriterConfig(analyzer);

		w = new IndexWriter(index, config);
	//	this.eventList = eventList;
		
		System.out.println("processing list");
		
		for (int i = 0; i < eventList.size(); i++) {
			System.out.println("processing event "+eventList.get(i));
			if (eventList.get(i).getSlotsize() > 0) {
				eventList.get(i).setIndex(tempList.size());
				tempList.add(eventList.get(i));
			
				
				addDoc(w, tempList.get(i));
			}
		}
		w.close();
	}

	ArrayList<EventProject> search(String[] fields) throws ParseException,
			IOException {
	

		if (fields[0] == "") { // checks for project name
			validList=tempList;
		}else{
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getProjectName().equals(fields[0])) {
					validList.add(tempList.get(i));

				}
			}
		}
		tempList = validList;
		validList = new ArrayList<EventProject>();

		if (fields[2] =="") { // checks for number of slots
			validList=tempList;
		}else{
			for (int i = 0; i < tempList.size(); i++) {
				if ((tempList.get(i).getSlotsize()) >= Integer
						.parseInt(fields[2])) {
					validList.add(tempList.get(i));

				}
			}
		}
		tempList = validList;
		validList = new ArrayList<EventProject>();

		if (fields[3] =="") {// checks for agency name
			validList=tempList;
			}else{
			
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getAgency().equals(fields[5])) {
					validList.add(tempList.get(i));

				}
			}
		}

		tempList = validList;
		validList = new ArrayList<EventProject>();
		if (fields[4]==""){
			return tempList;
		}
		Query q = new QueryParser("description", analyzer).parse(fields[4]);
		reader = DirectoryReader.open(index);
		searcher = new IndexSearcher(reader);
		collector = TopScoreDocCollector.create(LinksPerPage);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		int tempIndex;
		for (int i = 0; i < hits.length; i++) {
			DocId = hits[i].doc;
			tempDoc = searcher.doc(DocId);
			tempIndex = Integer.parseInt(tempDoc.get("index"));
			for (int x = 0; x < tempList.size(); x++) {
				if (tempList.get(x).getIndex() == tempIndex) {
					validList.add(tempList.get(x));

				}
			}

		}

		return validList;
	}
	
	private static void addDoc(IndexWriter index, EventProject project) throws IOException {
		Document doc = new Document();
		
		System.out.println("adding document to lucene "+project.getIndex()+" discription "+project.getDescription());
		String contents=project.getDescription()+" "+project.getDate()+ " "+project.getAgency()+" "+project.getProjectName()+ " "+project.getAddress();
		doc.add(new TextField("description", contents, Field.Store.YES));
			  
		  
		  doc.add(new StringField("index", Integer.toString(project.getIndex()), Field.Store.YES));
		  index.addDocument(doc);
		}
}
