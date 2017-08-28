package test;

import java.util.ArrayList;
import java.util.List;

import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteMetadata;
import com.evernote.thrift.TException;

import utils.Myutils;
import yx.Operate;

/**
 * 测试bug(5):经相册导入的图片不能显示原因,及解决
 * @author acer
 *
 */
public class Test04 {

	
	public static void getNoteGuid() throws Exception{
		Operate operate =new Operate();
		String notebookGuid=operate.getNoteBookGuid("test");
		List<NoteMetadata> noteList=new ArrayList<NoteMetadata>();
		noteList=operate.getNoteMetadata(notebookGuid);
		
		for (NoteMetadata noteMetadata : noteList) {
			System.out.println(noteMetadata.getTitle()+":"+noteMetadata.getGuid());
		}
	
	}
	
	public static void main(String[] args) throws Exception {
		
		String noteGuid="dd1d9980-c56b-4226-a3c4-dc85ccd0ecce";
		
		
		Operate operate =new Operate();
		String s =operate.getNoteStore().getNote(noteGuid, true, true, true, true).getContent();
		
	
		System.out.println(s);
		
		s=Myutils.content2HTML(s);
		
		System.out.println(s);
		
	}
}
