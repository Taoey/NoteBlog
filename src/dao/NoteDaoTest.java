package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
	
	
}
