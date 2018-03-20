package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class MyUtilsTest {


	@Test	
	public void getProperty() throws FileNotFoundException, IOException {
		System.out.println(Myutils.getProperty("token"));
	}
	@Test	
	public void getProperty2() throws FileNotFoundException, IOException {
		System.out.println(Myutils.getProperty2("token"));
	}

	@Test
	public void filterPath() {
		String str="123:\\\"?/";
		System.out.println(Myutils.filterPath(str));
	}
	@Test
	public void createNoteIndex() {
		File file=new File("");	
		String sourcePath = file.getAbsolutePath()+file.separator+"static"+file.separator+"pages"+file.separator+"NoteIndex.txt";    //源文件地址
		System.out.println(sourcePath);
		sourcePath=sourcePath.replace("bin", "webapps"+file.separator+"ROOT");
		System.out.println(sourcePath);
	}
}
