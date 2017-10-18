package yx;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.notestore.NoteMetadata;
import com.evernote.edam.notestore.NotesMetadataList;
import com.evernote.edam.notestore.NotesMetadataResultSpec;
import com.evernote.edam.notestore.SyncState;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.Resource;
import com.evernote.thrift.TException;

import utils.Myutils;

public class Operate {

	private String authToken = null;
	private String noteStoreUrl =null;

	private NoteStoreClient noteStore;
	private UserStoreClient userStore;

	public Operate() throws Exception {
		authToken=Myutils.getProperty("token");
		noteStoreUrl=Myutils.getProperty("url");
		
		EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.YINXIANG, authToken);
		ClientFactory factory = new ClientFactory(evernoteAuth);

		noteStore = factory.createNoteStoreClient();
		userStore = factory.createUserStoreClient();
	}

	/**
	 * 获取notestore
	 * @return
	 */
	public NoteStoreClient getNoteStore() {
		return noteStore;
	}


	/**
	 * 读取笔记
	 * 
	 * @throws Exception
	 */
	public void readNotes() throws Exception {

		List<Notebook> notebooks = noteStore.listNotebooks();
		Notebook blog = null;
		// search NoteBlog
		for (Notebook notebook : notebooks) {
			// System.out.println(notebook);
			if ("1.2 待完成".equals(notebook.getName())) {
				blog = notebook;
			}
		}
		// read notes
		if (blog != null) {
			NoteFilter filter = new NoteFilter();
			filter.setNotebookGuid(blog.getGuid());
			filter.setOrder(NoteSortOrder.CREATED.getValue());
			filter.setAscending(true);

			NoteList noteList = noteStore.findNotes(filter, 0, 100);
			List<Note> notes = noteList.getNotes();

			for (Note note : notes) {
				System.out.println(note.getTitle() + " " + note.getGuid());
			}
		}

	}

	/**
	 * 保存资源到本地
	 */
	public void saveResource(Resource resource, String filedir) throws Exception {
		String filetype = resource.getMime();// 获取资源类型
		// System.out.println(mime);
		if (filetype.matches("image.*")) {
			byte[] resHash = resource.getData().getBodyHash();
			byte[] resBody = resource.getData().getBody();
			String bodyHash = Myutils.bytes2HexString(resHash).toLowerCase();
			String path="";
			if(filetype.indexOf("png")!=-1){
				path = String.format(filedir + "/%s.png", bodyHash);
			}else if(filetype.indexOf("jpeg")!=-1){
				 path = String.format(filedir + "/%s.jpeg", bodyHash);
			}else if(filetype.indexOf("gif")!=-1){
				 path = String.format(filedir + "/%s.gif", bodyHash);
			}
			
			utils.Myutils.byte2img(resBody, path);
		}

	}

	/**
	 * 获取全部笔记
	 * 
	 * @throws Exception
	 */
	public List<Note> getAllBlogs(String bookName) throws Exception {
		List<Notebook> notebooks = noteStore.listNotebooks();
		List<Note> notes =null;
		Notebook blog = null;
		// search NoteBlog
		for (Notebook notebook : notebooks) {			
			if (bookName.equals(notebook.getName())) {
				blog = notebook;
			}
		}

		if (blog != null) {

			NoteFilter filter = new NoteFilter();
			filter.setNotebookGuid(blog.getGuid());
			filter.setOrder(NoteSortOrder.CREATED.getValue());
			filter.setAscending(true);

			NoteList noteList = noteStore.findNotes(filter, 0, 100);
			notes = noteList.getNotes();

		}
		return notes;
	}



	public void getResource() throws Exception {
		String resourceGuid = "91f9bfe5-4d4a-4ec1-8b35-27082a8db480";
		Resource resource = noteStore.getResource(resourceGuid, true, false, true, false);
		byte[] fileContent = resource.getData().getBody();
		String fileType = resource.getMime();
		String fileName = resource.getAttributes().getFileName();
		byte[] b = resource.getData().getBodyHash();
		String res = Myutils.bytes2HexString(b);

		FileOutputStream fout = new FileOutputStream("d:\\1.jpg");
		// 将字节写入文件
		fout.write(fileContent);
		fout.close();
		System.out.println(res);
	}

	///////////////////////////////////////////////
	// 新开始
	///////////////////////////////////////////////
	/**
	 * 获取指定笔记本的Guid
	 * 
	 * @param name
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws TException
	 */
	public String getNoteBookGuid(String name) throws EDAMUserException, EDAMSystemException, TException {
		String guid = "";
		List<Notebook> notebooks = noteStore.listNotebooks();
		Notebook blog = null;
		// search NoteBlog
		for (Notebook notebook : notebooks) {
			if (name.equals(notebook.getName())) {
				guid = notebook.getGuid();
				// System.out.println(notebook);
			}
		}
		return guid;
	}

	/**
	 * 获取CurrentUpdataCount
	 * 
	 * @param lastUpdateCount
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws TException
	 */
	public int getCUC() throws EDAMUserException, EDAMSystemException, TException {

		SyncState syncState = noteStore.getSyncState();
		return syncState.getUpdateCount();
	}

	/**
	 * 获取笔记本中全部笔记的相关信息
	 * 
	 * @param notebookGuid
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws EDAMNotFoundException
	 * @throws TException
	 */
	public List<NoteMetadata> getNoteMetadata(String notebookGuid)
			throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
		// 构建NoteFilter 查询器
		NoteFilter noteFilter = new NoteFilter();
		noteFilter.setNotebookGuid(notebookGuid);
		noteFilter.setInactive(false);

		// 设置需要返回的结果集 带的参数
		NotesMetadataResultSpec resultSP = new NotesMetadataResultSpec();
		resultSP.setIncludeTitle(true);
		resultSP.setIncludeUpdated(true);
		resultSP.setIncludeTagGuids(true);

		// 得到查询到的元数据列表
		NotesMetadataList metaList = noteStore.findNotesMetadata(noteFilter, 0, 100, resultSP);

		List<NoteMetadata> noteList = metaList.getNotes();

		return noteList;
	}

	/**
	 * 获取保存单个博客内容和资源,并完成转换
	 * 
	 * @param noteGuid
	 * @throws Exception
	 */
	public void getBlog(String noteGuid) throws Exception {
		Note note = noteStore.getNote(noteGuid, true, true, true, true);
		// 获取内容
		String noteContent = note.getContent();
		noteContent = Myutils.content2HTML(noteContent);
		// System.out.println(noteContent);

		// 内容处理
		String filedir = Myutils.makedir(note.getGuid());// 根据博客名创建了本地博客目录
		File file=new File("");
		Myutils.string2File(noteContent, filedir +file.separator+"content.html");
		// 复制index.html到各博客文章
		Myutils.createNoteIndex(filedir+file.separator+"index.jsp", note.getTitle(),note.getGuid());
		Myutils.string2File("", filedir + file.separator + "z-"+note.getTitle()); // 创建博客名标记

		// 获取资源
		List<Resource> res = note.getResources();
		if (res != null && !res.isEmpty()) {
			// 获取图资源
			for (Resource resource : res) {
				saveResource(resource, filedir);
			}
		}

	}

	/**
	 * 根据博客名（实际上是note的Guid）删除博客目录
	 * 
	 * @param noteName
	 */
	public static void deleteNote(String noteName) {
		File file = new File("");
		String dir = String.format(file.getAbsolutePath().replace("bin", "webapps\\ROOT") + "%sblogs", file.separator, file.separator,
				file.separator);
		File notePath = new File(dir + file.separator + noteName);
		String[] fileArray = null;

		if (notePath.exists()) {
			fileArray = notePath.list();
		}

		if (fileArray != null) {// 如果有子文件先删除子文件（博客资源中默认没有子文件夹）
			for (int i = 0; i < fileArray.length; i++) {
				File tempFile = new File(notePath + file.separator + fileArray[i]);
				tempFile.delete();
			}

			notePath.delete();
		} else {// 没有子文件，直接删除该博客文件夹（虽然不可能发生，但是保险起见）
			notePath.delete();
		}
	}

	public Note getNote(String noteGuid) throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
		Note note = noteStore.getNote(noteGuid, true, true, true, true);
		
		return note;
	}

	public List<String> getNoteTagNames(String Guid) throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
		List<String> l=noteStore.getNoteTagNames(Guid);
		return l;
	}
}
