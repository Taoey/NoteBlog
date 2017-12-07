package dao;

import java.util.List;

import org.junit.Test;

import javabean.Note;
import javabean.Tag;

public class MultiDaoTest {
	
	
	@Test
	public void getAllNoteByTag() throws Exception {
		List<Note> nList=MultiDao.getAllNoteByTag("540e9199-bfb9-4208-90ea-ffc62d171b28");
		System.out.println(nList.get(0).getTitle());
	}
	@Test
	public void getAllTagByNote() throws Exception{
		List<Tag> tList = MultiDao.getAllTagByNote("5c50c8f6-7ea0-4546-b071-6d4d778772f9");
		System.out.println(tList.size());
	}

}
