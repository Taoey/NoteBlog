package jdbc;

import java.util.List;

import org.junit.Test;

public class TagsDaoTest {

	
	@Test
	public void addOne() throws Exception {		
		TagsDao.addTag("", "");
	}
	
	@Test
	public void addMany() {
		List<String> strList = null;
		strList.add("s1");
		strList.add("s2");
		
		//TagsDao.addTags(NoteGuid, tags);
	}
	
	
}
