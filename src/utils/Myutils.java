package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Myutils {

	/**
	 * 根据博客名创建本地目录
	 * @param blogName
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String makedir(String blogName) throws FileNotFoundException, IOException{
		String progectName=Myutils.getProperty("projectName");
		File dir = new File("");		
		String path=dir.getAbsolutePath()+"\\"+progectName+"\\blogs\\";
		path=path.replace("bin", "webapps");//把bin目录替换掉
		File blogContent=new File(path+blogName);
		if(!blogContent.exists()){
			blogContent.mkdirs();
		}	
		return blogContent.getAbsolutePath();
	}
	
	/**
	 * 为每个note创建index入口
	 * @param path
	 * @param title
	 * @throws IOException
	 */
	public static void createNoteIndex(String path,String title,String guid)throws IOException{
		String str = "";
		BufferedReader bre = null;
		OutputStreamWriter pw = null;// 定义一个流
		File file=new File("");	
		String sourcePath = file.getAbsolutePath()+"\\static\\pages\\NoteIndex.txt";    //源文件地址
		sourcePath=sourcePath.replace("bin", "webapps\\ROOT");
		//sourcePath=sourcePath.replace("noteBlog", "noteBlog\\WebContent");
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(sourcePath), "UTF-8");
		bre = new BufferedReader(isr);
		
		pw = new OutputStreamWriter(new FileOutputStream(path), "utf-8");// 确认流的输出文件和编码格式，此过程创建了“test.txt”实例

		while ((str = bre.readLine()) != null) { // 判断最后一行不存在，为空结束循环
			str=str.replace("modelTitle", title);
			str=str.replace("mysid", guid);
			pw.write(str);// 将要写入文件的内容，写入到新文件
		}
		pw.close();// 关闭流
		bre.close();// 关闭流

	}
	
	/**
	 * 获取指定HTML标签的指定属性的值
	 * 
	 * @param source
	 *            要匹配的源文本
	 * @param element
	 *            标签名称
	 * @param attr
	 *            标签的属性名称
	 * @return 属性值列表
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<String> match(String source, String element, String attr) throws FileNotFoundException, IOException {
		source=source.replaceAll("image/png\"/", "image/png\" /").replaceAll("image/jpeg\"/", "image/jpeg\"  /").replaceAll("image/gif\"/", "image/gif\"  /");
		List<String> result = new ArrayList<String>();
		String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";

		Matcher m = Pattern.compile(reg).matcher(source);
		while (m.find()) {
			String r = m.group(1);
			result.add(r);
		}
		return result;
	}

	
	/**
	 * 字节类型转换为图像
	 * 
	 * @param fileContent
	 * @param path
	 * @throws IOException
	 */
	public static void byte2img(byte[] fileContent, String path) throws IOException {
		FileOutputStream fout = new FileOutputStream(path);
		// 将字节写入文件
		fout.write(fileContent);
		fout.close();
	}

	/**
	 * 将媒体标签转换为HTML标签
	 * @param s
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String media2Html(String s) throws FileNotFoundException, IOException {

		List<String> hash = Myutils.match(s, "en-media", "hash");
		List<String> typeTemp = Myutils.match(s, "en-media", "type");
		
		//Map<String,String> map=new HashMap<String,String>();
		for(int i=0;i<hash.size();i++){
			String stype=typeTemp.get(i);
			
			boolean picExist=stype.indexOf("jpeg") != -1 || stype.indexOf("gif") != -1 || stype.indexOf("png") != -1;
			if (picExist) {
				String currentHash = hash.get(i);
				String currentType = stype.replace("image/", "");
				String reg="<en-media.*?image.*?/>";
				Matcher m = Pattern.compile(reg).matcher(s);
				if(m.find()){
					String oldstring = m.group();
					String width="",height="auto";
					
					if(oldstring.indexOf("width=\"")!=-1){//有样式
						 int begin=oldstring.indexOf("width=\"");
						// System.out.println(begin);
						 int end=oldstring.indexOf("\"", begin+7);
						 //System.out.println(end);
						 width=oldstring.substring(begin+7,end);
					}
					String newstring = String.format("<image src=\"%s.%s\" width=\"%s\" height=\"%s\" class=\"img-responsive\"/>", currentHash,currentType,width,height);
					s = s.replace(oldstring, newstring);
				}			
			} 
			
		}
		
		return s;
	}


	/**
	 * 将获取的note转换为HTML文档
	 * 
	 * @param content
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String content2HTML(String content) throws FileNotFoundException, IOException {
		String noteHead="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">\r\n\r\n";
		String jsphead="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>";
		content=content.replace(noteHead, jsphead);
		content = content.replaceAll("en-note", "div");
		content = content.replaceAll("<en-todo.*?false.*?/>", "<input type='checkbox'/>");
		content = content.replaceAll("<en-todo.*?ture.*?/>", "<input type='checkbox' checked/>");
		content = media2Html(content);// 图片资源标签需特殊处理
		
		return content;
	}

	/**
	 * string 到本地文件的转换
	 * @param str
	 * @param path
	 * @throws IOException
	 */
	public static void string2File(String str,String path) throws IOException {
		File file=new File(path);
		if(!file.exists()){
			file.createNewFile();
		}
		
		OutputStream out=new FileOutputStream(file);
		BufferedWriter   rd   =   new BufferedWriter(new OutputStreamWriter(out,"utf-8"));
		rd.write(str);
		rd.close();
		out.close();
//		FileWriter fw = new FileWriter(path);
//		fw.write(str);
//		fw.close();
	}

	/**
	 * 二进制数组到string的转换
	 * @param b
	 * @return
	 */
	public static String bytes2HexString(byte[] b) {  
	    String ret = "";  
	    for (int i = 0; i < b.length; i++) {  
	        String hex = Integer.toHexString(b[ i ] & 0xFF);  
	    if (hex.length() == 1) {  
	        hex = '0' + hex;  
	    }  
	     ret += hex.toUpperCase();  
	  }  
	  return ret;  
	} 
	
	/**
	 * 从properities文件中获取变量值
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getProperty(String key) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(new FileReader(Myutils.class.getClassLoader().getResource("config.properties").getPath()));
		
		String value =  prop.getProperty(key);
		return value;
	}
	public static String getProperty2(String key) throws IOException {
		BufferedReader br = null;
		Properties datas = new Properties();
		br = new BufferedReader(new InputStreamReader(new  FileInputStream(Myutils.class.getClassLoader().getResource("config.properties").getPath()), "UTF-8"));
		datas.load(br);
		String value =  datas.getProperty(key);
		return value;
		
	}
	
	
    /**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   文件的绝对路径
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
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }   
	
    /**
     * 文件名过滤器
     * @param str
     * @return
     */
    public static String  filterPath(String str) {
    	str=str.replaceAll("\\\\", "").replaceAll("/", "").replaceAll("|", "");
    	str=str.replaceAll("<", "").replaceAll(">", "").replaceAll(":", "");
    	str=str.replaceAll("\\*", "").replaceAll("\\?", "").replaceAll("\"", "");
    	
		return str;
    	
    }

}
