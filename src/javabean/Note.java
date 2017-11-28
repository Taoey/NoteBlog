package javabean;

public class Note {
	private String title;
	private String guid;
	
	public Note(String title, String guid) {
		super();
		this.title = title;
		this.guid = guid;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	
	
}
