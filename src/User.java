/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class User {
	
	private int pin;
	private String fname;
	private String lname;
	private String dob;
	private String address;
	private long phone;
	private String city;
	private String state;
	private String zip;
	private String open;

	
	/**
	 * Constructor for User class.
	 * 
	 * @param name
	 * @param dob
	 * @param address
	 * @param open 
	 */
	
	public User(int pin, String lname, String fname, String dob, long phone, String address, String city, String state, String zip, String open) {
		this.pin = pin;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.open = open;
	}
	
	/////////////////////////////////// GETTERS AND SETTERS ///////////////////////////////////
	
	/**
	 * Retrieves the user's PIN.
	 * 
	 * @return pin
	 */
	
	public int getPIN() {
		return pin;
	}
	
	/**
	 * Retrieves the user's name.
	 * 
	 * @return name
	 */
	
	public String getFname() {
		return fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	/**
	 * Retrieves the user's date of birth.
	 * 
	 * @return dob
	 */
	
	public String getDOB() {
		return dob;
	}
	
	/**
	 * Retrieves the user's address.
	 * 
	 * @return address
	 */
	
	public String getAddress() {
		return address;
	}
	
	public long getPhone() {
		return phone;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public String getOpen() {
		return open;
	}
	
	/**
	 * Sets the value of the user's PIN.
	 * 
	 * @param pin the new PIN
	 */
	
	public void setPIN(int pin) {
		this.pin = pin;
	}
	
	/**
	 * Sets the value of the user's name.
	 * 
	 * @param name the new name
	 */
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * Sets the value of the user's date of birth.
	 * 
	 * @param dob the new date of birth
	 */
	
	public void setDOB(String dob) {
		this.dob = dob;
	}
	
	/**
	 * Sets the value of the user's address.
	 * 
	 * @param address the new address
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void setOpen(String open) {
		this.open = open;
	}
}