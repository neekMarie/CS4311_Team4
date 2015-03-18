
public class PilotInformation {
	/*instance variables*/
	private String firstName;
	private String lastName;
	private String destinationContact;
	private String searchRescueContact;
	
	
	public PilotInformation (String fName, String lName, String destContact, String searchContact){
			firstName = fName;
			lastName = lName;
			destinationContact = destContact;
			searchRescueContact = searchContact;
	}
	/*setter functions*/
	public void setFirstName(String newName){
		firstName = newName;
	}
	
	public void setLastName(String newName){
		lastName = newName;
	}
	
	/*as we are not sure of contact info format these are unchecked for bad input*/
	public void setDestinationContact (String newContact){
		destinationContact = newContact;
	}
	
	public void setSearchRescueContact(String newContact){
		searchRescueContact = newContact;
	}
	
	/*getter functions*/
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String setDestinationContact (){
		return destinationContact;
	}
	
	public String setSearchRescueContact (){
		return searchRescueContact;
	}
	
}
