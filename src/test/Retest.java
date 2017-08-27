package test;

import java.util.List;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteMetadata;
import com.evernote.edam.notestore.NotesMetadataList;
import com.evernote.edam.notestore.NotesMetadataResultSpec;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;

public class Retest {

	private String authToken = "S=s68:U=de5222:E=16517564940:C=15dbfa51b58:P=1cd:A=en-devtoken:V=2:H=8eb81037d80007090698e7e9841ee413";
	private String noteStoreUrl = "https://app.yinxiang.com/shard/s68/notestore";

	private NoteStoreClient noteStore;
	private UserStoreClient userStore;

	public Retest() throws Exception {

		EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.YINXIANG, authToken);
		ClientFactory factory = new ClientFactory(evernoteAuth);

		noteStore = factory.createNoteStoreClient();
		userStore = factory.createUserStoreClient();
	}

	public String getNoteGuid(String name) throws EDAMUserException, EDAMSystemException, TException{
		
		
		String noteBlogGuid="";
		
		List<Notebook> notebooks = noteStore.listNotebooks();
		Notebook blog = null;
		//search NoteBlog
		for (Notebook notebook : notebooks) {
			//System.out.println(notebook);
			if(name.equals(notebook.getName())){
				blog=notebook;
			}
		}
		if(blog!=null){
			noteBlogGuid=blog.getGuid();			
		}
		
		return noteBlogGuid;
	}
	
	public void isSync(String notebookGuid) throws Exception {

		//构建NoteFilter 查询器
		NoteFilter noteFilter=new NoteFilter();
		noteFilter.setNotebookGuid(notebookGuid);
		noteFilter.setInactive(false);
		
		//设置需要返回的结果集 带的参数
		NotesMetadataResultSpec resultSP=new NotesMetadataResultSpec();
		resultSP.setIncludeTitle(true);
		
		//得到查询到的元数据列表
		NotesMetadataList metaList=noteStore.findNotesMetadata(noteFilter, 0, 100, resultSP);
		
		List<NoteMetadata> noteList=metaList.getNotes();
		for (NoteMetadata noteMetadata : noteList) {
			System.out.println(noteMetadata);			
		}
	}

	public static void main(String[] args) throws Exception {

		Retest t = new Retest();
		
		String testNoteBookGuid="e7fa1b38-a2d5-4918-9714-ec798fe2bdaa";
		
		t.isSync(testNoteBookGuid);

	}

}
