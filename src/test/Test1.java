package test;

import java.util.List;

import org.junit.Test;

import com.evernote.edam.notestore.NoteMetadata;

import dao.NoteDao;
import dao.TagDao;

public class Test1 {

	@Test
	public void main1() throws Exception{
		String noteBookName=utils.Myutils.getProperty("NoteBookName");
		String noteBookGuid =utils.NoteUtils.getNoteBookGuid(noteBookName);
		List<NoteMetadata>nList = utils.NoteUtils.getNoteMetadata(noteBookGuid);
		
		for (NoteMetadata noteMetadata : nList) {
			NoteDao.addNote(noteMetadata, 1);
			List<String> tags=utils.NoteUtils.getNoteTagNames(noteMetadata.getGuid());
			for (String tag : tags) {
				TagDao.addTag(noteMetadata.getGuid(),tag );
			}
			
		}
	}
	
	
}
