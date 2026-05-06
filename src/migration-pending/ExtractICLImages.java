package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.util.Iterator;
import java.io.*;
//
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

import javax.media.jai.*;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import com.sun.media.jai.codec.*;

import java.awt.image.renderable.ParameterBlock;

//import java.awt.image.*;
//import java.awt.image.renderable.ParameterBlock;
//import java.util.*;
//import java.lang.*;
//
//
public class ExtractICLImages {
	private static String	moduleName;
	private static String	calledFrom;
	boolean	cod_performed		= false;
	boolean	reachedEOF			= false;
	//
	String	db_url				= null;
	String	db_driver			= null;
	String	db_user				= null;
	String	db_pass				= null;
	String	bankId				= null;
	//
	char[]	image_rec			= new char[50000];
	//
	int		startImage			= 0;
	int		imageRec_seq		= 0;
	int		start				= 0;
	int		checksProc			= 0;
	int		imagesProc			= 0;
	boolean	jpegCreated			= false;
	//
	String	checksProcTemp		= "";
	String	acctNum				= "";
	String	unique_isn			= "";
	String	applDate			= "";
	String	applDateAtStart		= "";
	String	bod_flag			= "";
	String[]checkFields			= { "", "", "", "", "", "", "",	"", "", "", "", ""};
	String	check_num			= "";
	String	dbUsed				= "";
	String	inImageDir			= "";
	String	inImageFile			= "";
	String	outputMICRDir		= "";
	String	inDir				= "";
	String	outDir				= "";
	String	jpegFile_o			= "";
	String	jpegFile			= "";
	String	file_name			= "";
	//String	fileNum				= "";
	String	tiffFile			= "CheckImage.tiff";
	String	zerosStr			= "000000000000000";
	String	zerosStr17			= "00000000000000000";
	String	zerosStr20			= "00000000000000000000";
	String	processDate			= "";
	String	temp				= "";
	String	recType				= "";
	String	one_rec				= "";
	String	imageRec			= "";
	String	outImageFile		= "";
	String	micrFileName		= "";
    static int	imgWid			= 0;
    static int	imgHyt			= 0;
    static int	nimgWid			= 0;
    static int	nimgHyt			= 0;
	//
	Connection dbConn;
	SysadControlUtil	ctlUtil= new SysadControlUtil();
	ControlDetail		ctlDetail			= new ControlDetail();
	FileOutputStream	outWrite			= null;	// new
			// FileOutputStream("new_format_ascii1.dat");
	PrintStream ps	= null;	
	// declare a print stream object
	FileOutputStream	outMCR = null;
	PrintStream psMCR	= null;
	Process	proc;
	//
    static ImageReader reader = getTiffImageReader();
    static ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    static ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						"> ExtractICLImages." + moduleName + logMsg);
	}
	//
	public boolean isNumeric(String token) {
		boolean ok = false;
		try {
			Double.valueOf(token).doubleValue();
			ok	= true;
		} catch (Exception e) {
			//
		}
		return ok;
	}
	//
	public void ExtractImages() {
		moduleName = "ExtractImages: ";
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		try {
			//
			// Read the EBCDIC file, Extract Images and write out the JPEG Images
			//
			file_name			= inDir + inImageFile;
			outImageFile		= outDir + inImageFile;
			PrintLog("outImageFileName: " + outImageFile);
			//fileNum	= outImageFile.substring(outImageFile.indexOf("_", 1) + 1,
			//								 outImageFile.indexOf("_", 1) + 2);
			//micrFileName			= outputMICRDir + inImageFile;
			outWrite				= new FileOutputStream(outImageFile + "_temp");
			ps						= new PrintStream(outWrite);
			//outMCR					= new FileOutputStream(micrFileName + "_temp");
			//psMCR					= new PrintStream(outMCR);
			BufferedInputStream bis	= null;
			bis						= new BufferedInputStream(new FileInputStream(file_name));
			byte[] recLength		= new byte[4];
			int len;
			byte[] recText;
			byte[] recText1;
			byte[] newText;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (bis.read(recLength) == 4) {
				// PrintLog("Status > Successful Read "+start);
				len = ((recLength[0] & 0xFF) << 24)
						| ((recLength[1] & 0xFF) << 16)
						| ((recLength[2] & 0xFF) << 8) | (recLength[3] & 0xFF);
				if (len > 0) {
					start++;
					recText		= new byte[len];
					bis.read(recText);
					baos.write(recText);
					one_rec		= baos.toString("CP037"); // EBCDIC encoding
					startImage	= one_rec.length() + 1;
					recType		= one_rec.substring(0, 2);
					// PrintLog("Rec Type > "+one_rec.substring(0,4));
					if (one_rec.substring(0, 4).equals("0103")) {
						processDate		= one_rec.substring(23, 31);
						// Create a directory for the process date
						// PrintLog("in chexRecType_0101="+process_date_01);
						//process_date_25		= process_date_01;
					}
					if (one_rec.substring(0, 2).equals("25")) {
						imageRec_seq = 1;
						PrintLog("Rec Type  > "+one_rec);
						//int    slashPos	= 0;
						//int	   startAt	= 0;
						check_num	= one_rec.substring(2, 17).trim();
						check_num	= check_num.replaceAll(" ", "");
						try {
							if (check_num.equals("")) {
								temp		= one_rec.substring(27,47).trim();
								if (one_rec.substring(46,47).equals("/")) {
									acctNum	= one_rec.substring(27,47).trim();
									check_num	= zerosStr;
								} else {
									acctNum		= one_rec.substring(27,42).trim();
									check_num	= one_rec.substring(42,47).trim();
								}
							} else {
								acctNum	= one_rec.substring(27,47).trim();
							}
						} catch (Exception e) {
							PrintLog("Exception in runtime : " + e);
							checksProcTemp	= checksProc + 1 + "";
							acctNum		= checksProcTemp;
							acctNum		= zerosStr20.substring(0,20 - acctNum.length())	+ acctNum;
							check_num	= checksProcTemp;
							check_num	= zerosStr.substring(0, 15 - check_num.length()) +
											check_num;
						}
						//PrintLog("Check Num >"+check_num);
						check_num	= check_num.replaceAll(" ", "");
						check_num	= check_num.replaceAll("/", "");
						check_num	= check_num.replaceAll("-", "");
						acctNum		= acctNum.replaceAll(" ", "");
						acctNum		= acctNum.replaceAll("/", "");
						acctNum		= acctNum.replaceAll("-", "");
						temp	= "";
						//PrintLog("Check Num >"+check_num);
						if (acctNum.length() < 20) {
							acctNum	= zerosStr20.substring(0,20 - acctNum.length())	+ acctNum;
						}
						//PrintLog("Acct Num >" + acctNum);
						if (acctNum.length() > 20) {
							acctNum	= acctNum.substring(0, 20);
						}
						unique_isn	= one_rec.substring(57, 72).trim();
						//PrintLog("Check Num >"+check_num + " Acct Num >" + acctNum + " Unique ISN >" + unique_isn);
						PrintLog("Account Num: " + acctNum + " Check Num: " + check_num + " Unique ISN: "+unique_isn);
						jpegFile	= "";
						temp		= "";
						temp		= ("Check_" + zerosStr.substring(0, 20 - acctNum.length()) +
										acctNum	+ "_" +
										zerosStr.substring(0, 15 - check_num.length()) +
										check_num + "_" +
										zerosStr.substring(0, 15 - unique_isn.length()) + 
										unique_isn);
						jpegFile	= jpegFile + temp;
						newText		= one_rec.getBytes();
						outWrite.write(newText, 0, startImage - 1);
						ps.println();
						//outMCR.write(newText, 0, startImage - 1);
						//psMCR.println();
						checksProc++;
					} else if (one_rec.substring(0, 2).equals("52")) {
						temp		= baos.toString();
						startImage	= temp.indexOf("II");
						//
						imageRec	= "";
						imageRec	= temp.substring(startImage, temp.length());
						// PrintLog("FOUND "+one_rec.substring(0,2)+" StartImage "+startImage);
						// PrintLog("Image size "+imageRec.length());
						FileOutputStream outWrite1 = new FileOutputStream(outDir + tiffFile);
						newText		= imageRec.getBytes();
						outWrite1.write(newText);
						outWrite1.close();
						if (imageRec_seq == 1) {
							jpegFile_o	= jpegFile + "_front.jpg";
							//jpegFile_o	= jpegFile + "_front.png";
						} else {
							jpegFile_o	= jpegFile + "_back.jpg";
							//jpegFile_o	= jpegFile + "_back.png";
						}
						//findFile		= new File(localDir + file2Process + "_" + applDateAtStart);
						//PrintLog("Find Processed File: >" + findFile + "<");
						//fileExists		= findFile.exists();
						//jpegCreated		= doTiff2JPEG(outDir + jpegFile_o, outDir + tiffFile);
						jpegCreated		= doIMTiff2JPEG(outDir + jpegFile_o, outDir + tiffFile);
						imageRec_seq	= 2;
						// PrintLog("Will Write Rec #: "+start+" Record Type > "+one_rec.substring(0,4));
						recText1		= one_rec.substring(0, startImage).getBytes();
						outWrite.write(recText1);
						ps.println();
						outWrite.write(newText);
						ps.println();
						imagesProc++;
						//
					} else {
						if (one_rec.substring(0, 2).equals("99")) {
							reachedEOF	= true;
						}
						// PrintLog("Will Write Rec #: "+start+" Record Type > "+one_rec.substring(0,4));
						newText			= one_rec.getBytes();
						outWrite.write(newText, 0, startImage - 1);
						ps.println();
						//outMCR.write(newText, 0, startImage - 1);
						//psMCR.println();
					}
					baos.reset();
				}
			}
			bis.close();
			outWrite.close();
			ps.close();
			//outMCR.close();
			//psMCR.close();
			PrintLog("Total Checks Processed: " + checksProc);
			PrintLog("Total Images processed: " + imagesProc);
			PrintLog("Total records processed: " + start);
		} catch (Exception e) {
			PrintLog("IO-Ecxception " + e);
			e.printStackTrace(System.out);
		}
		if (reachedEOF) {
			File inMicrFile	= new File(file_name);
			inMicrFile.renameTo(new File(file_name + "_" + applDate));
			//
			File outMicrImageFile	= new File(outImageFile + "_temp");
			outMicrImageFile.renameTo(new File(outImageFile));
			//
			PrintLog("MicrFileName  " + micrFileName);
			//File outMicrFile	= new File(micrFileName + "_temp");
			//outMicrFile.renameTo(new File(micrFileName + ".mcr"));
			//
			//FileOutput flagFile	= new FileOutput(micrFileName + ".ack");
			//flagFile.close();
			// }
		}
		moduleName	= calledFrom;
	}
	//
	public static boolean doTiff2JPEG(String filename, String tiffFile) {
		calledFrom	= moduleName;
		moduleName	= "doTiff2JPEG: ";
		// PrintLog("Input Image "+tiffFile);
		File file	= new File(tiffFile);
		FileSeekableStream s	= null;
		TIFFDecodeParam param	= null;
		// RenderedImage op		= null;
		String simplefilename	= filename;
		PrintLog("Output Image " + simplefilename);
		RenderedOp image		= null;
		// float edgeLength		= 1*1;
		try {
			s = new FileSeekableStream(file);
			ImageDecoder dec	= ImageCodec.createImageDecoder("tiff", s, param);
			int numPages		= dec.getNumPages();
			// PrintLog("Num of pages "+numPages);
			for (int i = 0; i < numPages; i++) {
				// op = new
				// NullOpImage(dec.decodeAsRenderedImage(i),null,null,OpImage.OP_COMPUTE_BOUND);
				image					= JAI.create("stream", s);
				Interpolation interp	= Interpolation.getInstance(Interpolation.INTERP_BICUBIC);
				ParameterBlock params	= new ParameterBlock();
				params.addSource(image);
				float scale = 2f / 5f;
				params.add(scale);
				params.add(scale);
				params.add(0.0F);
				params.add(0.0F);
				params.add(interp);
				PlanarImage image1 = JAI.create("scale", params);
				JAI.create("filestore", image1, simplefilename, "JPEG", null);
			}
		} catch (IOException e) {
			PrintLog(e.getLocalizedMessage());
			moduleName = calledFrom;
			return false;
		}
		file.delete();
		moduleName = calledFrom;
		return true;
	}
	//
	public static boolean doIMTiff2JPEG(String oFileName, String tiffFile) {
		calledFrom	= moduleName;
		moduleName	= "doIMTiff2JPEG: ";
		//PrintLog("Input Image "+tiffFile);
		//PrintLog("Output File: "+oFileName);
		IMOperation op = new IMOperation();
	    ConvertCmd convert = new ConvertCmd();
		// create the operation, add images and operators/options
		op.addImage(tiffFile);
		//op.format("PNG");// set the format.
		//op.quality(75.00);// set the quality.
		op.format("JPEG");// set the format.
		op.quality(50.00);// set the quality.
		op.addImage(oFileName);
		try {
		    convert.run(op);
		} catch (Exception e) {
		    //PrintLog(e.getLocalizedMessage());
			e.printStackTrace(System.out);
			moduleName = calledFrom;
			return false;
		}
		// PrintLog("Input Image "+tiffFile);
		File file	= new File(tiffFile);
		file.delete();
		moduleName = calledFrom;
		return true;
	}
	//
	public static boolean doTiff2JPEG2 (String fileName, String tiffFile) {
		calledFrom	= moduleName;
		moduleName	= "doTiff2JPEG: ";
		String simpleFileName	= fileName;
		PrintLog("Output Image " + simpleFileName);
		File tFile	= new File(tiffFile);
		jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpgWriteParam.setCompressionQuality(0.7f);
    	try (ImageInputStream iis = ImageIO.createImageInputStream(new File(tiffFile))) {
    		//ImageReader reader = getTiffImageReader();
			reader.reset();
			reader.setInput(iis);
    		int pages = reader.getNumImages(true);
			for (int imageIndex = 0; imageIndex < pages; imageIndex++) {
				BufferedImage bufferedImage = reader.read(imageIndex);
				imgWid	= bufferedImage.getWidth();
				imgHyt	= bufferedImage.getHeight();
				//PrintLog("Image Height: "+imgHyt+" Image Width: "+imgWid);
			
				nimgWid	= (int) (imgWid*(0.5));
				nimgHyt	= (int) (imgHyt*(0.5));
				//PrintLog("Image Height: "+nimgHyt+" Image Width: "+nimgWid);
				BufferedImage resized = resize(bufferedImage, nimgHyt, nimgWid);
				//ImageIO.write(bufferedImage, "jpg", new File(simpleFileName));
				jpgWriter.setOutput(ImageIO.createImageOutputStream(new File(simpleFileName)));
				IIOImage outputImage = new IIOImage(resized, null, null);
				//IIOImage outputImage = new IIOImage(bufferedImage, null, null);
				jpgWriter.write(null, outputImage, jpgWriteParam);
			}
			//return (true);
		} catch (Exception e) {
			PrintLog("Problem extracting image "+e);
			e.printStackTrace();
			moduleName = calledFrom;
			return (false);
		}
		tFile.delete();
		moduleName = calledFrom;
		return (true);
    }
    //
    private static ImageReader getTiffImageReader() {
    	Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByFormatName("TIFF");
		if (!imageReaders.hasNext()) {
			throw new UnsupportedOperationException("No TIFF Reader found!");
		}
		return imageReaders.next();
	}
    //
    private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		//BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
    }
    //
	public void runImageloader(String inputDir, String outputDir,
							   String mcrOutDir, String imageFile) {
		EcontServletContextListener escl	= new EcontServletContextListener();
		moduleName		= "runImageloader: ";
		applDate		= escl.getApplDate();
		inImageFile		= imageFile;
		outDir			= outputDir;
		inDir			= inputDir;
		outputMICRDir	= mcrOutDir;
		start			= 0;
		checksProc		= 0;
		imagesProc		= 0;
		PrintLog("Starting Chex Image Loader");
		ExtractImages();
		PrintLog("Completed Extracting Images for " + applDateAtStart);
	}
}
