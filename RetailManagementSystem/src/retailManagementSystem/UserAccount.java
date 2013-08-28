package retailManagementSystem;

public class UserAccount {

	private String firstName;
	private String lastName;
	private String userName;
	private char[] password;
	private int userLevel;
	
	public UserAccount(String firstName, String lastName, String userName, char[] password, int userLevel) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.userLevel = userLevel;
		
	}
	
	public int getUserAccountUserLevel() {
		return userLevel;
	}

	public void setUserAccountUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserAccountFirstName() {
		return firstName;
	}
	
	public void setUserAccountFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getUserAccountLastName() {
		return lastName; 
	}
	
	public void setUserAccountLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserAccountUserName() {
		return userName;
	}
	
	public void setUserAccountUserName(String userName) {
		this.userName = userName;
	}
	
	public char[] getUserAccountPassword() {
		return password;
	}
	
	public void setUserAccountPassword(char[] password) {
		this.password = password;
	}
	
}
