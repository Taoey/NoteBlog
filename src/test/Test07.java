package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务测试
 * 
 * @author acer
 *
 */
public class Test07 {

	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void addSrring2File(String fileName, String content) {
		FileWriter writer = null;
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				try {
					File file = new File("");
					String path = file.getAbsolutePath();
					Calendar now = Calendar.getInstance();
					String time = "" + now.get(Calendar.YEAR) + (now.get(Calendar.MONTH) + 1)
							+ now.get(Calendar.DAY_OF_MONTH) + now.get(Calendar.SECOND);

					final String filePath = path + file.separator + "log--" + time + ".txt";
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
		service.scheduleAtFixedRate(runnable, 0, 3, TimeUnit.SECONDS);
		
		
	}
}
