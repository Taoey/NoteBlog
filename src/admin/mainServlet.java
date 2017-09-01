package admin;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evernote.edam.notestore.NoteMetadata;

import jdbc.NotesDao;
import utils.Myutils;
import yx.Operate;

/**
 * Servlet implementation class mainServlet
 */
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
    }

    
//	public void init() {

//		
//	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//创建日志文件,注意日志文件的生成时间和该Servlet的启动时间相关

		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				try {
					String progectName=Myutils.getProperty("projectName");
					File file = new File("");					
					String path=file.getAbsolutePath()+"\\"+progectName+"\\logs\\";
					path=path.replace("bin", "webapps");//把bin目录替换掉
					
					Calendar now = Calendar.getInstance();
					int year=now.get(Calendar.YEAR);
					int month=(now.get(Calendar.MONTH) + 1);
					int day=now.get(Calendar.DAY_OF_MONTH);
					int second=now.get(Calendar.SECOND); //非必要.用于测试
					
					String time =""+year+month+day+second;

					String filePath = path + file.separator + "log--" + time + ".txt";
					File f = new File(filePath);
					
					if (!f.exists()) {
						f.createNewFile();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 0, 3, TimeUnit.SECONDS); //需修单位改为小时

		
		
		
		
		try {
			
			// 获取对应笔记本
			Operate op;
			try {
				op = new Operate();
				String bookGuid = op.getNoteBookGuid("test");
				// System.out.println(bookGuid);
	
				// 同步设置
				int lastUpdataCount = 0;
				int currentUpdataCount = 0;
	
				while (true) {
					currentUpdataCount = op.getCUC();
					if (lastUpdataCount < currentUpdataCount) {// 更新了
						System.out.println("yse");
						lastUpdataCount = currentUpdataCount;
						// 重新获取Note列表
						List<NoteMetadata> noteMetadataList = op.getNoteMetadata(bookGuid);
	
						// 与数据库信息对比
						
						for (int i = 0; i < noteMetadataList.size(); i++) {
							NoteMetadata curNoteMetadata = noteMetadataList.get(i);
							int condition = NotesDao.getSqlNoteCondition(curNoteMetadata);
	
							if (condition == -1) {						
								NotesDao.addNote(curNoteMetadata, 1);// 添加到数据库
								op.getBlog(curNoteMetadata.getGuid());// 保存到本地资源
							} else if (condition == 1) {
								op.deleteNote(curNoteMetadata.getGuid());// 删除原来的资源
								op.getBlog(curNoteMetadata.getGuid()); // 重新获取资源
								NotesDao.updateNote(curNoteMetadata, 1);//修改数据库数据
								
							} else {
								//只标记该笔记存在
								NotesDao.updateIsHave(curNoteMetadata, 1);
							}
						} // end for
						
						// 查询数据库中云端不存在的笔记
						List<String> invalidGuidList=NotesDao.getNoHaveNotes();
						// 根据查询结果删除本地的资源
						if(invalidGuidList!=null){
							for(int i=0;i<invalidGuidList.size();i++){
								op.deleteNote(invalidGuidList.get(i));
								NotesDao.deleteNote(invalidGuidList.get(i));
							}
						}
						
						
						//将所有笔记标记为0
						NotesDao.updateAllIsHave(0);
						
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
