package com.webfiche.checkpoint.util;

import java.io.FilenameFilter;
import java.io.File;
import java.io.*;
import java.util.regex.*;
import java.util.*;

public final class FileUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	static String fileString;
	static String preStr;
	static String postStr;

	static class FileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//"> FileUtil.FilenameFilter : DIR "+
			//dir + " NAME "+name+" FileString "+fileString);
			return (name.indexOf(fileString) >= 0);
		}
	}
	public static String[] GetFileNames(String filePath, String fileName) {
		/*
		 * System.out.println(java.util.Calendar.getInstance().getTime()+
		 * "> FileUtil.GetFileNames : Path "+ filePath + " NAME "+fileName);
		 */
		File f1					= new File(filePath);
		fileString				= fileName;
		String[] fileList;
		FilenameFilter filter	= new FileFilter();
		fileList				= f1.list(filter);
		if (fileList == null) {
			System.out.println(java.util.Calendar.getInstance().getTime() + 
							   "> FileUtil.FilenameFilter : Found None");
			// Do nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		/*
		 * for (i = 0 ; i<fileList.length; i++) {
		 * System.out.println(java.util.Calendar.getInstance().getTime()+
		 * "> FileUtil.FilenameFilter : FILELIST "+fileList[i]); }
		 */
		return fileList;
	}
	//
	static class WCFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			/*
			System.out.println(java.util.Calendar.getInstance().getTime()+
								"> FileUtil.GetPosiFiles.WCFilter : "+	preStr + "*" + postStr);
			*/
			return Pattern.matches(preStr + ".*" + postStr, name);
		}
	}
	public static String[] GetFNamesWC(String filePath, String fileNamePre, String fileNameSuf) {
		File f1		= new File(filePath);
		preStr		= fileNamePre;
		postStr		= fileNameSuf;
		/*
		System.out.println(java.util.Calendar.getInstance().getTime()+
							"> FileUtil.GetPosiFiles.GetFNamesWC : "+ preStr+"*"+postStr);
		*/
		String[] fileList;
		FilenameFilter filter	= new WCFilter();
		fileList				= f1.list(filter);
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class MicrImageFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFileFilter"+"File Name: "+name);
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFile Filter : "+
			// preStr+"*(txt|tab|csv|sw)");
			return Pattern.matches(preStr + ".*\\.(img|mcr|dat)", name);
		}
	}
	public static String[] GetMicrImageFiles(String filePath, String fileNamePre) {
		File f1 = new File(filePath);
		preStr = fileNamePre;
		String[] fileList;
		FilenameFilter filter = new MicrImageFileFilter();
		fileList = f1.list(filter);
		// for (i = 0; i < fileList.length; i++) {
		// System.out.println
		// ("> FileUtil.GetPosiFiles.PosiFile Filter LIST : "+fileList[i]);
		// }
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class TiffImageFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return Pattern.matches(".*\\.(tif|tiff)", name);
		}
	}
	public static String[] GetTiffImageFiles(String filePath) {
		File f1 = new File(filePath);
		//preStr = fileNamePre;
		String[] fileList;
		FilenameFilter filter = new TiffImageFileFilter();
		fileList = f1.list(filter);
		// for (i = 0; i < fileList.length; i++) {
		// System.out.println ("> FileUtil.GetPosiFiles.PosiFile Filter LIST : "+fileList[i]);
		// }
		if (fileList == null) {
			//
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class EarmarkFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//"> FileUtil.GetEarmarkFiles.EarmarkFileFilter"+"File Name: "+name);
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//"> FileUtil.GetEarmarkFiles.EarmarkFileFilter : "+
			//preStr+"*(txt|tab|csv|sw)");
			return Pattern.matches(preStr + ".*\\.(dat)", name);
		}
	}
	public static String[] GetEarmarkFiles(String filePath) {
		File f1		= new File(filePath);
		preStr		= "inclearmark";
		String[] fileList;
		FilenameFilter filter = new EarmarkFileFilter();
		fileList	= f1.list(filter);
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		//for (int i = 0; i < fileList.length; i++) {
		//	System.out.println("> FileUtil.GetPosiFiles.PosiFile Filter LIST : "+fileList[i]);
		//}
		return fileList;
	}
	//
	static class MicrXMLFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFileFilter"+"File Name: "+name);
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFile Filter : "+
			// preStr+"*(txt|tab|csv|sw)");
			return Pattern.matches(preStr + ".*\\.(xml)", name);
		}
	}

	public static String[] GetMicrXMLFiles(String filePath, String fileNamePre) {
		File f1 = new File(filePath);
		preStr = fileNamePre;
		String[] fileList;
		FilenameFilter filter = new MicrXMLFileFilter();
		fileList = f1.list(filter);
		// for (i = 0; i < fileList.length; i++) {
		// System.out.println
		// ("> FileUtil.GetPosiFiles.PosiFile Filter LIST : "+fileList[i]);
		// }
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class PosiFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFileFilter"+"File Name: "+name);
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFile Filter : "+
			// preStr+"*(txt|tab|csv|sw)");
			return Pattern.matches(preStr + ".*\\.(txt|tab|csv|sw)", name);
		}
	}
	public static String[] GetPosiFiles(String filePath, String fileNamePre) {
		File f1	= new File(filePath);
		preStr	= fileNamePre;
		String[] fileList;
		FilenameFilter filter	= new PosiFileFilter();
		fileList = f1.list(filter);
		// for (i = 0; i < fileList.length; i++) {
		// System.out.println
		// ("> FileUtil.GetPosiFiles.PosiFile Filter LIST : "+fileList[i]);
		// }
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class PayerFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return Pattern.matches(preStr + ".*\\.(txt|tab|csv|sw)", name);
		}
	}
	public static String[] GetPayerFiles(String filePath, String fileNamePre) {
		File f1	= new File(filePath);
		preStr	= fileNamePre;
		String[] fileList;
		FilenameFilter filter	= new PosiFileFilter();
		fileList = f1.list(filter);
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class FolderFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//"> FileUtil.GetPosiFiles.PosiFileFilter"+"File Name: "+name);
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> FileUtil.GetPosiFiles.PosiFile Filter : "+
			// preStr+"*(txt|tab|csv|sw)");
			//return Pattern.matches(preStr + ".*\\.*", name);
			//return Pattern.matches(preStr + ".*\\.(gz)", name);
			return Pattern.matches(preStr + ".(gz)", name);
		}
	}
	public static String[] GetFolderFiles(String filePath, String fileNamePre) {
		//System.out.println	("> FileUtil.GetFolderFiles : "+filePath+" name "+fileNamePre);
		File f1	= new File(filePath);
		preStr	= fileNamePre;
		String[] fileList;
		FilenameFilter filter	= new FolderFileFilter();
		fileList = f1.list(filter);
		if (fileList == null) {
			// Do Nothing
		} else {
			/*
			for (int i = 0; i < fileList.length; i++) {
				System.out.println	("> FileUtil.GetFolderFiles : "+i+" "+fileList[i]);
			}
			*/
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	static class FolderFileWCFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			/*
			System.out.println(java.util.Calendar.getInstance().getTime()+
								"> FileUtil.GetPosiFiles.WCFilter : "+	preStr + "*" + postStr);
			*/
			return Pattern.matches(preStr + ".*" + postStr + ".(gz)", name);
		}
	}
	public static String[] GetFolderFilesWC(String filePath, String fileNamePre, String fileNameSuf) {
		File f1		= new File(filePath);
		preStr		= fileNamePre;
		postStr		= fileNameSuf;
		/**/
		System.out.println(java.util.Calendar.getInstance().getTime()+
							"> FileUtil.GetPosiFiles.GetFNamesWC : "+ preStr+"*"+postStr);
		/**/
		String[] fileList;
		FilenameFilter filter	= new FolderFileWCFilter();
		fileList				= f1.list(filter);
		if (fileList == null) {
			// Do Nothing
		} else {
			if (fileList.length > 1) {
				Arrays.sort(fileList);
			}
		}
		return fileList;
	}
	//
	public static ArrayList<String> GetDirs(String filePath) {
		File dir			= new File(filePath);
		String[] children	= dir.list();
		Arrays.sort(children);
		ArrayList<String> applList	= new ArrayList<String>();
		//
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			// System.out.println("");
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				// String filename = children[i];
				// System.out.println(children[i]);
				applList.add(children[i]);
			}
		}
		return (applList);
	}
}
