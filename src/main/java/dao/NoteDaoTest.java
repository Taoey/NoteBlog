package dao;

import java.util.List;

import org.junit.Test;

import com.evernote.edam.notestore.NoteMetadata;

import javabean.Note;

public class NoteDaoTest {
	
	@Test
	//succeed  单个插入
	public void addNote() throws Exception{
		NoteMetadata n= new NoteMetadata();
		n.setGuid("2acc2c3-1bac-4f77-a734-98d9383e10a5");
		n.setTitle("test");
		n.setUpdated(122);
		NoteDao.addNote(n, 1);
	}
	@Test
	public void getRandNotes() throws Exception{
		List<Note> nList = NoteDao.getRandNotes();
		System.out.println(nList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getRecentNotes() throws Exception{
		Note n = NoteDao.getRecentNotes().get(0);
		int year = n.getTime().getYear()+1900;
		int month = n.getTime().getMonth() + 1;
		int day = n.getTime().getDate();
		System.out.println(year);
		
	}
	
	
}
