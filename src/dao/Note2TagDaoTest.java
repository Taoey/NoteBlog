package dao;

import java.util.List;

import org.junit.Test;

import com.evernote.edam.type.Note;

public class Note2TagDaoTest {

	
	@Test
	public void addN2T() throws Exception{
		//Note2TagDao.addN2T("2", "1");    //首先要保证要插入的数据存在于相关的表中(涉及到了外键)
	}
	
	
	@Test
	public void isExist() throws Exception{
		int a=Note2TagDao.isExist("1", "2");
		System.out.println(a);
	}
	
	@Test
	public void getAllTagGuid() throws Exception{
		List<String> strList = Note2TagDao.getAllTagGuid("5c50c8f6-7ea0-4546-b071-6d4d778772f9");		
		System.out.println(strList.toString());
		
	}
	@Test
	public void delete() throws Exception {
		Note2TagDao.delete("5c50c8f6-7ea0-4546-b071-6d4d778772f9", "540e9199-bfb9-4208-90ea-ffc62d171b28");
	}
}
