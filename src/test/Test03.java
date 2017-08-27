package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Test03 {

	/**
	 * 从properities文件中获取变量值
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getProperty(String key) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(new FileReader(Test03.class.getClassLoader().getResource("config.properties").getPath()));
		
		String value =  prop.getProperty(key);
		return value;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String a=getProperty("projectName");
		System.out.println(a);
	}
}
