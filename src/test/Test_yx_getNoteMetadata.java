package test;

import java.util.List;

import com.evernote.edam.type.Note;

import yx.Operate;
public class Test_yx_getNoteMetadata {

	
	public static void main(String[] args) throws Exception {
		//测试笔记：e75848e0-57ca-4028-8cfc-e5a2035aa67b,3fc16925-0afa-4f73-8f1a-c4062f6433e4
		Operate op=new Operate();
		Note note=op.getNote("e75848e0-57ca-4028-8cfc-e5a2035aa67b");
		
		//System.out.println(note);
		System.out.println(op.getNoteTagNames("e75848e0-57ca-4028-8cfc-e5a2035aa67b"));
		
	}
}
