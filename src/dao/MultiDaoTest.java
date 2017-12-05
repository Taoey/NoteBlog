package dao;

import java.util.List;

import org.junit.Test;

import javabean.Note;

public class MultiDaoTest {
	
	
	@Test
	public void getAllNoteByTag() throws Exception {
		List<Note> nList=MultiDao.getAllNoteByTag("540e9199-bfb9-4208-90ea-ffc62d171b28");
		System.out.println(nList.get(0).getTitle());
	}

}
