//package ScanToken;








/* This class is for the actual events on the orgsync page.  Each event is put into an event object to be manipulated
 * The data in the object is the data scraped from the orgsync web site
 */









public class EventProject {
	private String projectName;
	private String date;
	private String slot;
	private int slotsize;
	private String link;
	private String agency;
	private String agencyLink;
	private String descriptionDate;
	private String distance;
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
/* EventProject constructor
 * Creates an event object using the data from the orgsync web page
 * 
 */
	public EventProject(String projectName, String date, String slot,
			String link, String agency, String agencyLink,
			String descriptionDate, String distance) {
		this.projectName = projectName;
		this.date = date;
		this.slot = slot;
		this.link = link;
		this.agency = agency;
		this.agencyLink = agencyLink;
		this.descriptionDate = descriptionDate;
		this.distance= distance;
		this.slotsize=Integer.parseInt(slot);// creates an actual int from the string of the slotsize that was passed to the constructor
		
	}

	public int getSlotsize() {
		return slotsize;
	}

	public void calculateDistance() {
		
		//TODO:  Needs everything. 

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgencyLink() {
		return agencyLink;
	}

	public void setAgencyLink(String agencyLink) {
		this.agencyLink = agencyLink;
	}

	public String getDescriptionDate() {
		return descriptionDate;
	}

	public void setDescriptionDate(String descriptionDate) {
		this.descriptionDate = descriptionDate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
public String toString(){
	return projectName;
}


}
