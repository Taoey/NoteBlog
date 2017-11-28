package javabean;

public class Tag {
	
	private String guid;
	private String name;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tag(String guid, String name) {
		super();
		this.guid = guid;
		this.name = name;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
