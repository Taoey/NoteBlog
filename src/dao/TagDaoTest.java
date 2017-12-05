package dao;

import java.util.List;

import org.junit.Test;

import javabean.Tag;

public class TagDaoTest {

	@Test
	public void addTag() throws Exception {
		TagDao.addTag("540e9199-bfb9-4208-90ea-ffc62d171b28", "涛");
	}
	
	@Test
	public void isExist() throws Exception{
		int a = TagDao.isExist("540e9199-bfb9-4208-90ea-ffc62d171b28");
		System.out.println(a);
	}
	
	@Test
	public void getAllTag() throws Exception{
		List<Tag> tagList=TagDao.getAllTag();
		System.out.println(tagList.get(0).getName());
		
	}
}
