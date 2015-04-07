
public class EventProject {
	private String url;
	private String address;
	private String partner;
	private String date;
	private String time;
	private String impact;
	private String workDescription;
	private float distance;
	
		
public EventProject(String url, String address,String partner,String date, String time,String impact, String workDescription){
this.address=address;
this.url = url;
this.partner = partner;
this.date=date;
this.time=time;
this.impact=impact;
this.workDescription=workDescription;
distance=0;
}
 public void calculateDistance(){
	 
 }

/**
 * @return the url
 */
public String getUrl() {
	return url;
}


/**
 * @param url the url to set
 */
public void setUrl(String url) {
	this.url = url;
}


/**
 * @return the address
 */
public String getAddress() {
	return address;
}


/**
 * @param address the address to set
 */
public void setAddress(String address) {
	this.address = address;
}


/**
 * @return the partner
 */
public String getPartner() {
	return partner;
}


/**
 * @param partner the partner to set
 */
public void setPartner(String partner) {
	this.partner = partner;
}


/**
 * @return the date
 */
public String getDate() {
	return date;
}


/**
 * @param date the date to set
 */
public void setDate(String date) {
	this.date = date;
}


/**
 * @return the time
 */
public String getTime() {
	return time;
}


/**
 * @param time the time to set
 */
public void setTime(String time) {
	this.time = time;
}


/**
 * @return the impact
 */
public String getImpact() {
	return impact;
}


/**
 * @param impact the impact to set
 */
public void setImpact(String impact) {
	this.impact = impact;
}


/**
 * @return the workDescription
 */
public String getWorkDescription() {
	return workDescription;
}


/**
 * @param workDescription the workDescription to set
 */
public void setWorkDescription(String workDescription) {
	this.workDescription = workDescription;
}














}
