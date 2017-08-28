package test;
/**
 * 测试match方法中的正则的正确性
 */

import java.util.List;

import utils.Myutils;
import yx.Operate;

public class Test05 {

	public static void main(String[] args) throws Exception {
		String a=Myutils.getProperty("test");
		
		String noteGuid="dd1d9980-c56b-4226-a3c4-dc85ccd0ecce";
		Operate operate =new Operate();
		String s =operate.getNoteStore().getNote(noteGuid, true, true, true, true).getContent();
		
		List<String> aa=Myutils.match(s, "en-media", "type");		
		
		
		System.out.println();
	}
}
