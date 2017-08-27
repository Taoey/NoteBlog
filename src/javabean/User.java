package javabean;

public class User {

	private String name;

	private String email;
	private String token;
	private String storeUrl;
	
	public User(){
		
	}
	
	public User(String name, String email, String token, String storeUrl) {
		super();
		this.name = name;
		this.email = email;
		this.token = token;
		this.storeUrl = storeUrl;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
	
}
