package javabean;

import java.sql.Timestamp;

public class Note {
	private String title;
	private String guid;
	private Timestamp time;
	
	
	public Note(String title, String guid, Timestamp time) {
		super();
		this.title = title;
		this.guid = guid;
		this.time = time;
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

	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Note [title=" + title + ", guid=" + guid + ", time=" + time + "]";
	}
	

	
	
}
