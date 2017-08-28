package test;

import java.util.List;

import utils.Myutils;
import yx.Operate;

/**
 * 测试media2Html()函数,为img标签添加样式
 * @author acer
 *
 */
public class Test06 {

	
	public static void main(String[] args) throws Exception {
				
		String noteGuid="473073b9-96e7-4a19-b8d7-3d4456bb901b";
		Operate operate =new Operate();
		String s =operate.getNoteStore().getNote(noteGuid, true, true, true, true).getContent();
		System.out.println(s);
		s=Myutils.media2Html(s);
		System.out.println(s);
	}
}
