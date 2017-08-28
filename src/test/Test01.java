package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.evernote.edam.notestore.NoteMetadata;

import utils.Myutils;
import yx.Operate;


public class Test01 {
	public static String media2Html_2(String s) throws FileNotFoundException, IOException {

		List<String> hash = Myutils.match(s, "en-media", "hash");
		List<String> typeTemp = Myutils.match(s, "en-media", "type");
		
		Map<String,String> map=new HashMap<String,String>();
		for(int i=0;i<hash.size();i++){
			String stype=typeTemp.get(i);
			if (stype.indexOf("jpeg") != -1) {
				map.put(hash.get(i), "jpeg");
			} else if (stype.indexOf("gif") != -1) {
				map.put(hash.get(i), "gif");
			} else if (stype.indexOf("png") != -1) {
				map.put(hash.get(i), "png");
			}
			
		}
		
		
		for (String key : map.keySet()) {
			String currentHash = key;
			String currentType = map.get(key);
			String reg="<en-media.*?image.*?/>";
			Matcher m = Pattern.compile(reg).matcher(s);
			if(m.find()){
				String oldstring = m.group();
				String newstring = "<image src=\"" + currentHash + "." + currentType + "\" />";
				s = s.replace(oldstring, newstring);
			}	
			
		}
	

		return s;
	}
	public static void main(String[] args) throws Exception {
		
		String noteGuid="d7263e56-5fd3-4ee5-b48a-3f631cd367de";
		
		Operate operate =new Operate();
		String notebookGuid=operate.getNoteBookGuid("test");
		List<NoteMetadata> noteList=new ArrayList<NoteMetadata>();
		noteList=operate.getNoteMetadata(notebookGuid);
		
		for (NoteMetadata noteMetadata : noteList) {
			System.out.println(noteMetadata.getTitle()+":"+noteMetadata.getGuid());
		}
		
		String s =operate.getNoteStore().getNote(noteGuid, true, true, true, true).getContent();
		media2Html_2(s);
		
	}

}
