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

import jdbc.NotesDao;
import utils.JDBCUtil;
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

			String path = String.format(filedir + "/%s.png", bodyHash);
			utils.Myutils.byte2img(resBody, path);
		}

	}

	/**
	 * 获取全部笔记
	 * 
	 * @throws Exception
	 */
	public void getAllBlogs() throws Exception {
		List<Notebook> notebooks = noteStore.listNotebooks();
		Notebook blog = null;
		// search NoteBlog
		for (Notebook notebook : notebooks) {
			// System.out.println(notebook);
			if ("test".equals(notebook.getName())) {
				blog = notebook;
			}
		}

		if (blog != null) {

			NoteFilter filter = new NoteFilter();
			filter.setNotebookGuid(blog.getGuid());
			filter.setOrder(NoteSortOrder.CREATED.getValue());
			filter.setAscending(true);

			NoteList noteList = noteStore.findNotes(filter, 0, 100);
			List<Note> notes = noteList.getNotes();
			// for (Note note : notes) {
			// getBlog(note.getGuid());
			// }
			for (int i = 0; i < notes.size(); i++) {
				getBlog(notes.get(i).getGuid());
			}

		}
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
	 * 获取笔记的相关信息
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

		// 输出测试
		// for (NoteMetadata noteMetadata : noteList) {
		// System.out.println(noteMetadata);
		// }
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
		//System.out.println(filedir);
		Myutils.string2File(noteContent, filedir + "\\content.html");
		// 复制index.html到各博客文章（未完成）
		Myutils.createNoteIndex(filedir+"\\index.jsp", note.getTitle());
		Myutils.string2File("", filedir + "\\" + "z-"+note.getTitle()); // 创建博客名标记

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

//	public static void main(String[] args) throws Exception {
//		// 获取对应笔记本
//		Operate op = new Operate();
//		String bookGuid = op.getNoteBookGuid("test");
//		// System.out.println(bookGuid);
//
//		// 同步设置
//		int lastUpdataCount = 0;
//		int currentUpdataCount = 0;
//
//		while (true) {
//			currentUpdataCount = op.getCUC();
//			if (lastUpdataCount < currentUpdataCount) {// 更新了
//				System.out.println("yse");
//				lastUpdataCount = currentUpdataCount;
//				// 重新获取Note列表
//				List<NoteMetadata> noteMetadataList = op.getNoteMetadata(bookGuid);
//
//				// 与数据库信息对比
//				
//				for (int i = 0; i < noteMetadataList.size(); i++) {
//					NoteMetadata curNoteMetadata = noteMetadataList.get(i);
//					int condition = NotesDao.getSqlNoteCondition(curNoteMetadata);
//
//					if (condition == -1) {						
//						NotesDao.addNote(curNoteMetadata, 1);// 添加到数据库
//						op.getBlog(curNoteMetadata.getGuid());// 保存到本地资源
//					} else if (condition == 1) {
//						op.deleteNote(curNoteMetadata.getGuid());// 删除原来的资源
//						op.getBlog(curNoteMetadata.getGuid()); // 重新获取资源
//						NotesDao.updateNote(curNoteMetadata, 1);//修改数据库数据
//						
//					} else {
//						//只标记该笔记存在
//						NotesDao.updateIsHave(curNoteMetadata, 1);
//					}
//				} // end for
//				
//				// 查询数据库中云端不存在的笔记
//				List<String> invalidGuidList=NotesDao.getNoHaveNotes();
//				// 根据查询结果删除本地的资源
//				if(invalidGuidList!=null){
//					for(int i=0;i<invalidGuidList.size();i++){
//						op.deleteNote(invalidGuidList.get(i));
//						NotesDao.deleteNote(invalidGuidList.get(i));
//					}
//				}
//				
//				
//				//将所有笔记标记为0
//				NotesDao.updateAllIsHave(0);
//				
//			} else {// 没更新
//				System.out.println("No");
//			}
//			Thread.sleep(1000 * 30 * 1);
//		}
//
//	}

}
