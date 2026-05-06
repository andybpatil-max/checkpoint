package com.webfiche.checkpoint.util;

import org.apache.tika.Tika; //Including Tika
import java.io.File;

public class FileType {
	static Tika tika = new Tika();
	//
	public static String GetFileType(String fileName) {
		String fileType = tika.detect(fileName);
		System.out.println(fileName + "\t\tis of type \t\t" + fileType);
	    if (fileType.indexOf("text", 0) > -1 || fileType.indexOf("stream",0) > - 1
	    		|| fileType.indexOf("splash",0) > - 1) {
	    	return ("text");
	    } else {	
	   		return (fileType);
    	}
	}
	//
	public static void main(String[] args) {
		String path = ".";
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					String fileType = GetFileType(files);
					System.out.println(files + "\t\tis of type \t\t" + fileType);
				}
			}
		} catch (Throwable t) {
			System.out.println(t + "");
		}
	}
}
