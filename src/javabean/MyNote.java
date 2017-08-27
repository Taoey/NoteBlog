package javabean;

public class MyNote {

	private String title;
	private String guid;
	private String tagsGuid;
	private String url;
	public MyNote(){
		
	}
	public MyNote(String title, String guid, String tagsGuid, String url) {
		super();
		this.title = title;
		this.guid = guid;
		this.tagsGuid = tagsGuid;
		this.url = url;
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
	public String getTagsGuid() {
		return tagsGuid;
	}
	public void setTagsGuid(String tagsGuid) {
		this.tagsGuid = tagsGuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
