package javabean;

public class Note2Tag {
	private String noteGuid;
	private String tagGuid;
	public Note2Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Note2Tag(String noteGuid, String tagGuid) {
		super();
		this.noteGuid = noteGuid;
		this.tagGuid = tagGuid;
	}
	public String getNoteGuid() {
		return noteGuid;
	}
	public void setNoteGuid(String noteGuid) {
		this.noteGuid = noteGuid;
	}
	public String getTagGuid() {
		return tagGuid;
	}
	public void setTagGuid(String tagGuid) {
		this.tagGuid = tagGuid;
	}
	@Override
	public String toString() {
		return "Note2Tag [noteGuid=" + noteGuid + ", tagGuid=" + tagGuid + "]";
	}
	

}
