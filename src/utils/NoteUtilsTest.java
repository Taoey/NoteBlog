package utils;

import java.util.List;

import org.junit.Test;

import com.evernote.edam.notestore.NoteMetadata;

public class NoteUtilsTest {
	
	@Test
	//succeed
	public void getNoteBookGuid() throws Exception{
		//直接给出测试用例
		String s=NoteUtils.getNoteBookGuid("CCF突击");		
		System.out.println(s);//6963a2b9-f62b-4555-a728-21e72cd0cc30
		//从配置文件中获取目的笔记本
		String b=NoteUtils.getNoteBookGuid(Myutils.getProperty("NoteBookName"));//笔记本:印象博客2.0测试专用
		System.out.println(b);//59e00459-b1b8-427d-825e-5493af44ed25
	}
	
	@Test
	//succeed
	public void getNoteMetadata() throws Exception{
		String NoteBookGuid="59e00459-b1b8-427d-825e-5493af44ed25";//使用的是getNoteBookGuid()测试中的结果
		List<NoteMetadata> l=NoteUtils.getNoteMetadata(NoteBookGuid);
		System.out.println(l.get(0));	//NoteMetadata(guid:880a7dba-f36e-4358-b98b-75997e779422, title:测试, updated:1511686926000, tagGuids:[540e9199-bfb9-4208-90ea-ffc62d171b28, 01a7170a-f14a-4fed-b1e9-aa8ad4900eaa])
		
	}
	
	@Test
	//succeed 
	public void getNoteTagNames() throws Exception{
		List<String> s = NoteUtils.getNoteTagNames("880a7dba-f36e-4358-b98b-75997e779422");
		System.out.println(s);//[黄为涛, 印象博客2.0测试]
	}
	
	
	
	
	@Test
	public void getBlog() throws Exception{
		NoteUtils.getBlog("880a7dba-f36e-4358-b98b-75997e779422");
		System.out.println("DONE");
	}
	
	
	
	
	
	
	
	
	
	
	

}
