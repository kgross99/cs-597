import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.ibm.icu.util.StringTokenizer;

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
	private String description;
	private int index;
	private long dist;
	private String diststring;

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
			String descriptionDate, String distance, String description) {
		this.projectName = projectName;
		this.date = date;
		this.slot = slot;
		this.link = link;
		this.agency = agency;
		this.agencyLink = agencyLink;
		this.descriptionDate = descriptionDate;
		this.distance= distance;// really address.
		this.description= description;
		System.out.println("calculating distance");
		diststring=ParseDistance(calculateDistance());
		StringTokenizer st = new StringTokenizer(diststring);
		diststring=st.nextToken();
		
		try{
		
		dist=Integer.parseInt(diststring);
		} catch (NumberFormatException e){
			dist=0;
		}
		
		
		if (this.slot.equals("Unlimited")){slotsize=99;
		
		} else{
			try{	
		this.slotsize=Integer.parseInt(slot);// creates an actual int from the string of the slotsize that was passed to the constructor
			} catch (NumberFormatException e){
				slotsize=1;
			}
			
		}
		
	}
	public long getDist() {
		return dist;
	}

	public String getDiststring() {
		return diststring;
	}

	public String getAddress(){
		return distance;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSlotsize() {
		return slotsize;
	}

	public String calculateDistance() {
		String Adress2="1910 University Dr, Boise, ID 8372";
				String Adress1=distance;
				String line, outputString = "";
	
		try {

			URL url = new URL(
					"https://maps.googleapis.com/maps/api/distancematrix/json?origins="
							+ URLEncoder.encode(Adress2, "UTF-8") + "|"
							+ URLEncoder.encode(Adress1, "UTF-8")
							+ "&destinations="
							+ URLEncoder.encode(Adress1, "UTF-8") + "|"
							+ URLEncoder.encode(Adress2, "UTF-8")
							+ "&units=imperial");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
		} catch (Exception ex) {
			return "error";
		}
			
			System.out.println("outputString "+outputString);
			return outputString;
		
		
	}
	
	private String ParseDistance(String output){
		
		try {

			String Index = output
					.substring(
							output.indexOf("\"distance\" : {                  \"text\"") + 42,
							output.indexOf("\",                  \"value\""));

			// System.out.println(Index);
			if (Index.equalsIgnoreCase("1 ft")) {
				return "0";
			} else
				return Index;

		} catch (Exception ex) {
			return "error";
		}
		
		
		
		
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

	public long getDistance() {
		return dist;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}



}
