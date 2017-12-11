package javabean;

public class Admin {
	private String name;
	private String passwd;
	public Admin(String name, String passwd) {
		super();
		this.name = name;
		this.passwd = passwd;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
