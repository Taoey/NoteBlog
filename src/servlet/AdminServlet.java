package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evernote.edam.notestore.NoteMetadata;

import dao.NoteDao;
import utils.Myutils;
import utils.NoteUtils;


public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 获取对应笔记本			
			try {				
				String bookGuid = NoteUtils.getNoteBookGuid(Myutils.getProperty("NoteBookName"));
				// System.out.println(bookGuid);
	
				// 同步设置
				int lastUpdataCount = 0;
				int currentUpdataCount = 0;
	
				while (true) {
					currentUpdataCount = NoteUtils.getCUC();
					if (lastUpdataCount < currentUpdataCount) {// 更新了
						System.out.println("yse");
						lastUpdataCount = currentUpdataCount;
						// 重新获取Note列表
						List<NoteMetadata> noteMetadataList = NoteUtils.getNoteMetadata(bookGuid);
	
						// 与数据库信息对比
						
						for (int i = 0; i < noteMetadataList.size(); i++) {
							NoteMetadata curNoteMetadata = noteMetadataList.get(i);
							int condition = NoteDao.getSqlNoteCondition(curNoteMetadata);
	
							if (condition == -1) {		
								NoteDao.addNote(curNoteMetadata, 1);// 添加到数据库
								NoteUtils.getBlog(curNoteMetadata.getGuid());// 保存到本地资源
							} else if (condition == 1) {
								NoteUtils.deleteNote(curNoteMetadata.getGuid());// 删除原来的资源
								NoteUtils.getBlog(curNoteMetadata.getGuid()); // 重新获取资源
								NoteDao.updateNote(curNoteMetadata, 1);//修改数据库数据
								
							} else {
								//只标记该笔记存在
								NoteDao.updateIsHave(curNoteMetadata, 1);
							}
						} // end for
						
						// 查询数据库中云端不存在的笔记
						List<String> invalidGuidList=NoteDao.getNoHaveNotes();
						// 根据查询结果删除本地的资源
						if(invalidGuidList!=null){
							for(int i=0;i<invalidGuidList.size();i++){
								NoteUtils.deleteNote(invalidGuidList.get(i));
								NoteDao.deleteNote(invalidGuidList.get(i));
							}
						}
						
						
						//将所有笔记标记为0
						NoteDao.updateAllIsHave(0);
						
					} else {// 没更新
						System.out.println("No");
					}
					Thread.sleep(1000 * 30 * 1);
				}
	
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
