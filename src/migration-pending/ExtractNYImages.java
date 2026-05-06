package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.io.*;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

//
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;

//
import com.sun.media.jai.codec.*;

//
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.*;

//
import java.awt.Graphics2D;
import java.awt.Image;

//
import javax.media.jai.*;

import java.awt.image.*;
//
import java.awt.image.renderable.ParameterBlock;
import java.util.*;
//
public class ExtractNYImages {
	private static String	moduleName;
	private static String	calledFrom;
	boolean	cod_performed		= false;
	boolean	file_found			= true;
	boolean	itemData			= false;
	boolean	viewSide			= false;
	boolean	viewLength			= false;
	boolean	reachedEOF			= false;
	//
	String	db_url= null;
	String	db_driver			= null;
	String	db_user= null;
	String	db_pass= null;
	String	ftpGateway			= null;
	String	wfCgi= null;
	String	bankId= null;
	//
	char[]	image_rec			= new char[50000];
	//
	int		chars_read			= 0;
	int		chex_image_side		= 0;		// Front
	int		row_count			= 0;
	int		check_count			= 0;
	int		startImage			= 0;
	int		imageRec_seq		= 0;
	int		start= 0;
	int		checksProc			= 0;
	int		imagesProc			= 0;
	boolean	jpegCreated			= false;
	//
	String	acctNum= "";
	String	unique_isn			= "";
	String	appl_date			= "";
	String	appl_date_at_start	= "";
	String	bod_flag			= "";
	String[]checkFields			= { "", "", "", "", "", "", "",	"", "", "", "", ""};
	String	check_num			= "";
	String	dbUsed= "";
	String	ec_imageDir			= "";
	String	ec_image_file		= "";
	String[]file_info			= { "", "", "", "", "", "", "",	"", "", ""};
	String	file_info_prev		= "";
	String	file_info_curr		= "";
	String	nodeName			= "";
	String	output_path			= "";
	String	outputMICR_path		= "";
	String	REGEX= "\\s";
	String	output_file			= "";
	String	output_file_o		= "";
	String	inDir= "";
	String	outDir= "";
	String	jpegFile_o			= "";
	String	jpegFile			= "";
	String	file_name			= "";
	String	fileNum= "";
	String	tiffFile			= "CheckImage.tiff";
	String	zerosStr			= "000000000000000";
	String	zerosStr17			= "00000000000000000";
	String	temp= "";
	String	recType= "";
	String	one_rec= "";
	String	imageRec			= "";
	String	micrImageFileName	= "";
	String	micrFileName		= "";
    static int	imgWid			= 0;
    static int	imgHyt			= 0;
    static int	nimgWid			= 0;
    static int	nimgHyt			= 0;
	//
	Connection dbConn;
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ControlDetail		ctlDetail	= new ControlDetail();
	FileOutputStream	outWrite	= null;	// new
	ConvertCmd 			convert		= new ConvertCmd();
	// FileOutputStream("new_format_ascii1.dat");
	PrintStream ps	= null;	
	// declare a print stream object
	FileOutputStream	outMCR = null;
	PrintStream psMCR	= null;
	Process	proc;
	static ImageReader reader = getTiffImageReader();
    static ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    static ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						"> ExtractNYImages." + moduleName + logMsg);
	}
	//
	public boolean isNumeric(String token) {
		boolean ok = false;
		try {
			Double.valueOf(token).doubleValue();
			ok = true;
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
			// Read the EBCDIC file and Extract Images and also write out the
			// JPEG Images
			//
			file_name			= inDir + ec_image_file;
			micrImageFileName	= outDir + ec_image_file;
			PrintLog("micrImageFileName: " + micrImageFileName);
			fileNum	= micrImageFileName.substring(micrImageFileName.indexOf(".T", 1) + 2,
												  micrImageFileName.indexOf(".T", 1) + 7);
			micrFileName	= outputMICR_path + "CH" + appl_date.substring(4, 8) + fileNum;
			outWrite		= new FileOutputStream(micrImageFileName + "_temp");
			ps				= new PrintStream(outWrite);
			outMCR			= new FileOutputStream(micrFileName + "_temp");
			psMCR			= new PrintStream(outMCR);
			BufferedInputStream bis	= null;
			bis				= new BufferedInputStream(new FileInputStream(file_name));
			byte[] recLength = new byte[4];
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
					recText = new byte[len];
					bis.read(recText);
					baos.write(recText);
					one_rec = baos.toString("CP037"); // EBCDIC encoding
					startImage = one_rec.length() + 1;
					recType = one_rec.substring(0, 2);
					// PrintLog("Rec Type > "+one_rec.substring(0,4));
					if (one_rec.substring(0, 2).equals("25")) {
						imageRec_seq = 1;
						check_num = one_rec.substring(2, 17).trim();
						//int    slashPos	= 0;
						check_num	= one_rec.substring(2, 17).trim();
						if (check_num.equals("")) {
	    					temp	= one_rec.substring(27,47).trim();
							acctNum		= one_rec.substring(27,42).trim();
							check_num	= one_rec.substring(42,47).trim();
						} else {
							acctNum	= one_rec.substring(27,47).trim();
						}
						temp	= "";
						check_num	= check_num.replaceAll(" ", "");
						check_num	= check_num.replaceAll("/", "");
						check_num	= check_num.replaceAll("-", "");
						acctNum		= acctNum.replaceAll(" ", "");
						acctNum		= acctNum.replaceAll("/", "");
						acctNum		= acctNum.replaceAll("-", "");
						if (acctNum.length() < 17) {
							acctNum = zerosStr17.substring(0,17 - acctNum.length())	+ acctNum;
						}
						PrintLog("Acct Num >" + acctNum);
						if (acctNum.length() > 17) {
							acctNum = acctNum.substring(0, 17);
						}
						unique_isn	= one_rec.substring(57, 72).trim();
						jpegFile	= "";
						temp		= "";
						temp		= ("Check_" + zerosStr.substring(0, 17 - acctNum.length()) +
										acctNum +
										"_" +
										zerosStr.substring(0, 15 - check_num.length()) +
										check_num +
										"_" +
										zerosStr.substring(0,	15 - unique_isn.length()) + 
										unique_isn);
						jpegFile	= jpegFile + temp;
						newText		= one_rec.getBytes();
						outWrite.write(newText, 0, startImage - 1);
						ps.println();
						outMCR.write(newText, 0, startImage - 1);
						psMCR.println();
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
						newText = imageRec.getBytes();
						outWrite1.write(newText);
						outWrite1.close();
						if (imageRec_seq == 1) {
							jpegFile_o = jpegFile + "_front.jpg";
							//jpegFile_o = jpegFile + "_front.png";
						} else {
							jpegFile_o = jpegFile + "_back.jpg";
							//jpegFile_o = jpegFile + "_back.png";
						}
						//jpegCreated = doTiff2JPEG(outDir + jpegFile_o, outDir + tiffFile);
						jpegCreated = doIMTiff2JPEG(outDir + jpegFile_o, outDir + tiffFile);
						imageRec_seq = 2;
						// PrintLog("Will Write Rec #: "+start+" Record Type > "+one_rec.substring(0,4));
						recText1 = one_rec.substring(0, startImage).getBytes();
						outWrite.write(recText1);
						ps.println();
						outWrite.write(newText);
						ps.println();
						imagesProc++;
						//
					} else {
						if (one_rec.substring(0, 2).equals("99")) {
							reachedEOF = true;
						}
						// PrintLog("Will Write Rec #: "+start+" Record Type > "+one_rec.substring(0,4));
						newText = one_rec.getBytes();
						outWrite.write(newText, 0, startImage - 1);
						ps.println();
						outMCR.write(newText, 0, startImage - 1);
						psMCR.println();
					}
					baos.reset();
				}
			}
			bis.close();
			outWrite.close();
			ps.close();
			outMCR.close();
			psMCR.close();
			PrintLog("Total Checks Processed: " + checksProc);
			PrintLog("Total Images processed: " + imagesProc);
			PrintLog("Total records processed: " + start);
		} catch (Exception e) {
			PrintLog("IO-Ecxception " + e);
			e.printStackTrace(System.out);
		}
		if (reachedEOF) {
			File inMicrFile = new File(file_name);
			inMicrFile.renameTo(new File(file_name + "_" + appl_date));
			//
			File outMicrImageFile = new File(micrImageFileName + "_temp");
			outMicrImageFile.renameTo(new File(micrImageFileName));
			//
			PrintLog("MicrFileName  " + micrFileName);
			File outMicrFile = new File(micrFileName + "_temp");
			outMicrFile.renameTo(new File(micrFileName + ".mcr"));
			//
			FileOutput flagFile = new FileOutput(micrFileName + ".ack");
			flagFile.close();
			// }
		}
		moduleName = calledFrom;
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
				//IIOImage outputImage = new IIOImage(bufferedImage, null, null);
				IIOImage outputImage = new IIOImage(resized, null, null);
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
	public void runExtractImages(String inputDir, String outputDir,
							   String mcrOutDir, String imageFile) {
		moduleName							= "runImageloader: ";
		EcontServletContextListener escl	= new EcontServletContextListener();
		appl_date							= escl.getApplDate();
		ec_image_file						= imageFile;
		outDir								= outputDir;
		inDir								= inputDir;
		outputMICR_path						= mcrOutDir;
		PrintLog("Starting Chex Image Loader");
		start			= 0;
		checksProc		= 0;
		imagesProc		= 0;
		ExtractImages();
		// CheckForFiles();
		PrintLog("Completed Extracting Images for " + appl_date_at_start);
	}
}
