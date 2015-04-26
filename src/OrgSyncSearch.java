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
	
	ArrayList<EventProject> search(){
	
	
	
	
	
		return validList;
	}
}
