import java.util.ArrayList;




public class OrgSyncSearch {
	private  ArrayList<EventProject> eventList;
	private  ArrayList<EventProject> validList;
	private ArrayList<EventProject> tempList;
	
	public void OrgSyncSearch (ArrayList<EventProject> eventList){
		this.eventList=eventList;
		for (int i=0; i<eventList.size();i++){
			if (eventList.get(i).getSlotsize()>0){
				tempList.add(eventList.get(i));
			}
		}
	}
	
	ArrayList<EventProject> search(String[] fields){
		
	if (fields[0]!=null){
		for (int i=0;i<tempList.size();i++){
			if (tempList.get(i).getProjectName().equals(fields[0])){
				validList.add(tempList.get(i));
				
			}
		}
	}
	if (fields[5]!=null){
		for (int i=0;i<tempList.size();i++){
			if (tempList.get(i).getAgency().equals(fields[5])){
				validList.add(tempList.get(i));
				
			}
		}
	}
	
	
		
	
	
	
	
	
		return validList;
	}
}
