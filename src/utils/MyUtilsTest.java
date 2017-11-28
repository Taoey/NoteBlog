package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class MyUtilsTest {


	@Test	
	public void getProperty() throws FileNotFoundException, IOException {
		System.out.println(Myutils.getProperty("NoteBookName"));
	}

	
}
