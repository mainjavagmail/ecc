package com.sensedia.ecc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileUtil {
	
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
	
	public static <T> void writeSet(Set<T> set,String filename){
		writeSet(set, filename, false);
	}
	
	public static <T> void writeSet(Set<T> set,String filename,boolean append){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename,append));
			int count = 0;
			for (T line : set) {
				bw.write(line.toString());
				if(count != set.size() -1){
					bw.newLine();
				}
				count++;
			}
			bw.close();
			bw = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> load2List(String filename, Charset encoding) {
		BufferedReader br;
		List<String> resultList = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			String line;
			while ((line = br.readLine()) != null) {
				// if(line.trim().length() == 0)continue;
				resultList.add(line);
			}
			br.close();
			br = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public static List<String> load2List(String filename){		  
		return load2List(filename,CHARSET_UTF8);
	}
	
	public static List<String> getFileNames(String rootPath){
		List<String> filenames = new ArrayList<String>();
		File f = new File(rootPath);
		File[] files = f.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				filenames.addAll(getFileNames(file.getAbsolutePath()));
			}
			else{
				filenames.add(file.getAbsolutePath());
			}
		}
		files = null;
		f = null;
		return filenames;
	}
	
	public static byte[] file2Byte(String filePath) {
		File file = new File(filePath);
		FileChannel channel = null;
		FileInputStream stream = null;
		ByteBuffer byteBuffer = null;
		try {
			if (!file.exists())
				return null;
			stream = new FileInputStream(file);
			channel = stream.getChannel();
			byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
			}
			channel.close();
			stream.close();
			return byteBuffer.array();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean byte2File(String filePath, byte[] bt) {
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileOutputStream out = new FileOutputStream(filePath, false);
			out.write(bt);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean string2File(String filePath, String content, boolean append, Charset charset) {
		File file = new File(filePath);
		File parent = file.getParentFile();
		mkdirByFile(parent.getAbsolutePath());
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(file, append), charset);
			out.write(content);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean mkdirByFile(String dirPath) {
		try {
			File file = new File(dirPath);
			if (!file.exists())
				return file.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFileByLine(String filename, List<String> contentList, boolean append) {
		mkdirByFile(filename);
		File file = new File(filename);
		try {
			FileWriter writer = new FileWriter(file, append);
			for (String content : contentList)
				writer.write(content + "\r\n");
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
