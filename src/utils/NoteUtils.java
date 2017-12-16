package utils;

import java.io.File;
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
import com.evernote.edam.notestore.NoteMetadata;
import com.evernote.edam.notestore.NotesMetadataList;
import com.evernote.edam.notestore.NotesMetadataResultSpec;
import com.evernote.edam.notestore.SyncState;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.Resource;
import com.evernote.edam.type.Tag;
import com.evernote.thrift.TException;

public class NoteUtils {
	private static String authToken = null;
	private static NoteStoreClient noteStore;
	private static UserStoreClient userStore;
	//初始化	
	static{
		try {			
			authToken=Myutils.getProperty2("token");
			EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.YINXIANG, authToken);
			ClientFactory factory = new ClientFactory(evernoteAuth);

			noteStore = factory.createNoteStoreClient();
			userStore = factory.createUserStoreClient();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取指定笔记本的Guid
	 * 
	 * @param name
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws TException
	 */
	public static String getNoteBookGuid(String name) throws EDAMUserException, EDAMSystemException, TException {
		String guid = null;
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
	 * 获取笔记本中全部笔记的相关信息
	 * 
	 * @param notebookGuid
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws EDAMNotFoundException
	 * @throws TException
	 */
	public static List<NoteMetadata> getNoteMetadata(String notebookGuid)
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
	 * 根据笔记名字获取笔记标签列表
	 * @param Guid
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws EDAMNotFoundException
	 * @throws TException
	 */
	public static List<String> getNoteTagNames(String Guid) throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
		List<String> l=noteStore.getNoteTagNames(Guid);
		return l;
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
	public static int getCUC() throws EDAMUserException, EDAMSystemException, TException {

		SyncState syncState = noteStore.getSyncState();
		return syncState.getUpdateCount();
	}

	/**
	 * 保存资源到本地
	 */
	public static void  saveResource(Resource resource, String filedir) throws Exception {
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
	 * 获取保存单个博客内容和资源,并完成转换
	 * 
	 * @param noteGuid
	 * @throws Exception
	 */
	public static void getBlog(String noteGuid) throws Exception {
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
		
		//Myutils.string2File("", filedir + file.separator + "z-"+Myutils.filterPath(note.getTitle())); // 创建博客名标记

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

	/**
	 * 根据TagGuid获取tag名字
	 * @param tagGuid
	 * @return
	 * @throws EDAMUserException
	 * @throws EDAMSystemException
	 * @throws EDAMNotFoundException
	 * @throws TException
	 */
	public static String getTag(String tagGuid) throws EDAMUserException, EDAMSystemException, EDAMNotFoundException, TException {
		
		return noteStore.getTag(tagGuid).getName();
	}
	
}
