package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class MyUtilsTest {


	@Test	
	public void getProperty() throws FileNotFoundException, IOException {
		System.out.println(Myutils.getProperty("siderText"));
	}
	@Test	
	public void getProperty2() throws FileNotFoundException, IOException {
		System.out.println(Myutils.getProperty2("siderText"));
	}

	@Test
	public void filterPath() {
		String str="123:\\\"?/";
		System.out.println(Myutils.filterPath(str));
	}
}
