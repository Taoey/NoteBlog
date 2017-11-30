package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evernote.edam.notestore.NoteMetadata;

import dao.Note2TagDao;
import dao.NoteDao;
import dao.TagDao;
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
							
							String curNoteGuid = curNoteMetadata.getGuid();
							
							if (condition == -1) {//针对没有的笔记						
								NoteDao.addNote(curNoteMetadata, 1);// 添加笔记信息到数据库								
								//添加笔记标签
								List<String> tagGuidList = curNoteMetadata.getTagGuids();
								if(tagGuidList!=null && !tagGuidList.isEmpty()) {
									for(int j=0;j<tagGuidList.size();j++) {
										String curTagGuid = tagGuidList.get(j);
										//查询是否存在此标签
										if(TagDao.isExist(curTagGuid)==0) {//不存在
											TagDao.addTag(curTagGuid,NoteUtils.getTag(curTagGuid));
											Note2TagDao.addN2T(curNoteGuid, curTagGuid);
										}
										else {//标签存在,但不确定关系是否存在
											int exist=Note2TagDao.isExist(curNoteGuid,curTagGuid);
											if(exist==0) {//不存在
												Note2TagDao.addN2T(curNoteGuid, curTagGuid);
												
											}											
										}
									}
								}							
								NoteUtils.getBlog(curNoteMetadata.getGuid());// 保存到本地资源
							} else if (condition == 1) {//更新了
								//添加笔记标签
								List<String> tagGuidList = curNoteMetadata.getTagGuids();
								if(tagGuidList!=null && !tagGuidList.isEmpty()) {
									for(int j=0;j<tagGuidList.size();j++) {
										String curTagGuid = tagGuidList.get(j);
										//查询是否存在此标签
										if(TagDao.isExist(curTagGuid)==0) {//不存在
											TagDao.addTag(curTagGuid,NoteUtils.getTag(curTagGuid));
											Note2TagDao.addN2T(curNoteGuid, curTagGuid);
										}
										else {//标签存在,但不确定关系是否存在
											int exist=Note2TagDao.isExist(curNoteGuid,curTagGuid);
											if(exist==0) {//不存在
												Note2TagDao.addN2T(curNoteGuid, curTagGuid);
												
											}											
										}

									}
								}
								
								/*删除冗余的关系*/
								//1.找出冗余tagGuid;
								List<String> sqlNoteTagList = Note2TagDao.getAllTagGuid(curNoteGuid);								
								if(sqlNoteTagList!=null && !sqlNoteTagList.isEmpty()) {
									for(int i1=0;i1<tagGuidList.size();i1++) {
										int index=sqlNoteTagList.indexOf(tagGuidList.get(i1));
										if(index!=-1) {
											sqlNoteTagList.remove(index);
										}												
									}
									
									//2.删除冗余关系
									for(int i2=0;i2<sqlNoteTagList.size();i2++) {
										Note2TagDao.delete(curNoteGuid, sqlNoteTagList.get(i2));
									}								
									
								}
								
								
								
								//NoteUtils.deleteNote(curNoteMetadata.getGuid());// 删除原来的资源
								//NoteUtils.getBlog(curNoteMetadata.getGuid()); // 重新获取资源
								NoteDao.updateNote(curNoteMetadata, 1);//修改数据库数据
								
							} else {//该条笔记没有更新,但是位置可能发生了变化(从一个笔记本移动到了另一个笔记本)
								//添加笔记标签
								List<String> tagGuidList = curNoteMetadata.getTagGuids();
								if(tagGuidList!=null && !tagGuidList.isEmpty()) {
									for(int j=0;j<tagGuidList.size();j++) {
										String curTagGuid = tagGuidList.get(j);
										//查询是否存在此标签
										if(TagDao.isExist(curTagGuid)==0) {//不存在
											TagDao.addTag(curTagGuid,NoteUtils.getTag(curTagGuid));
											Note2TagDao.addN2T(curNoteGuid, curTagGuid);
										}
										else {//标签存在,但不确定关系是否存在
											int exist=Note2TagDao.isExist(curNoteGuid,curTagGuid);
											if(exist==0) {//不存在
												Note2TagDao.addN2T(curNoteGuid, curTagGuid);
												
											}											
										}

									}
								}	
								
								
								
								/*删除冗余的关系*/
								//1.找出冗余tagGuid;
								List<String> sqlNoteTagList = Note2TagDao.getAllTagGuid(curNoteGuid);								
								if(sqlNoteTagList!=null && !sqlNoteTagList.isEmpty()) {
									for(int i1=0;i1<tagGuidList.size();i1++) {
										int index=sqlNoteTagList.indexOf(tagGuidList.get(i1));
										if(index!=-1) {
											sqlNoteTagList.remove(index);
										}												
									}
									
									//2.删除冗余关系
									for(int i2=0;i2<sqlNoteTagList.size();i2++) {
										Note2TagDao.delete(curNoteGuid, sqlNoteTagList.get(i2));
									}								
									
								}
								
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
