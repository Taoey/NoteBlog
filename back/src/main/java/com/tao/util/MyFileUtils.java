package com.tao.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class MyFileUtils {
	private static final String  IMG_SERVER="http://192.168.128.128/imgs/";   //图片服务器地址
	/**
	 * 根据上传文件，获取唯一文件名
	 * @param file
	 * @return
     */
	public static String transFileName(MultipartFile file) {
		//定义文件名
		StringBuilder fileName=new StringBuilder();
		//重命名规则   时间戳   年月日时分秒毫秒+4位随机数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//获取当前日期和时间
		Date date=new Date();
		//当前日期和时间转换成指定的字符串格式
		fileName.append(sdf.format(date));
		//拼接4位随机数
		Random rd=new Random();
		for(int i=0;i<4;i++){
			fileName.append(String.valueOf(rd.nextInt(10))) ;
		}
		//获取文件的后缀名  
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		//最后拼接文件的扩展名
		fileName.append(".").append(extension);
		System.out.println("MyfileUtils:"+file.getOriginalFilename());
		System.out.println("MyfileUtils:"+fileName.toString());
		return fileName.toString();
	}
	/**
	 * 上传一个文件
	 * @param file
	 * @return  文件在服务器上的文件名
     */
	public  static String upload(MultipartFile file){
		String resultCode = null;
		String fileName = transFileName(file);
		Client client = new Client();
		String path = IMG_SERVER+fileName;
		WebResource resource = client.resource(path);
		try {
			resultCode = resource.put(String.class, file.getBytes());  //resultCode 是上传结果状态码
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  fileName;
	}
}
