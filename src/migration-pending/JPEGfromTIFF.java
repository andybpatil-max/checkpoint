package com.webfiche.checkpoint.util;

import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;

import javax.media.jai.JAI;
import java.awt.image.RenderedImage;
import javax.media.jai.Interpolation;
import javax.media.jai.PlanarImage;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
//
//import javax.imageio.*;
//import javax.media.jai.*;
//import java.awt.image.renderable.ParameterBlock;
//import javax.media.jai.RenderedOp;
//import com.sun.media.jai.codec.TIFFDecodeParam;
//

public class JPEGfromTIFF {
	private static String className = "> JPEGfromTIFF.";
	private static String moduleName;
	//JPEGfromTIFF jpTiff	= new JPEGfromTIFF();
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() + 
							className + moduleName + logMsg);
	}
	//
	public boolean doTiff2JPEG(String outDir, String outputFile, String tiffFile) {
		moduleName				= "doTiff2JPEG: ";
		String outputFileName	= "";
		String simpleFileName	= outDir + outputFile;
		String[] frontBack		= {"_front.jpg","_back.jpg"};
		PrintLog("Output Image "+simpleFileName);
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		RenderedImage renderedImage[];
		File file				= new File(tiffFile);
		try {
			FileSeekableStream s		= new FileSeekableStream(file);
			ImageDecoder imageDecoder	= ImageCodec.createImageDecoder("tiff",	s, null);
			renderedImage				= new RenderedImage[imageDecoder.getNumPages()];
			int numPages				= 0;
			for (int i = 0; i < imageDecoder.getNumPages(); i++) {
				renderedImage[i]	= imageDecoder.decodeAsRenderedImage(i);
				numPages++;
			}
			PrintLog("Num of pages " + numPages);
			for (int i = 0; i < numPages; i++) {
				outputFileName	= simpleFileName + frontBack[i];
				PrintLog("Output " + i + ": " + simpleFileName);
				Interpolation interp = Interpolation.getInstance(Interpolation.INTERP_BICUBIC);
				ParameterBlock params = new ParameterBlock();
				params.addSource(renderedImage[i]);
				float scale = 2f / 5f;
				params.add(scale);
				params.add(scale);
				params.add(0.0F);
				params.add(0.0F);
				params.add(interp);
				PlanarImage image1	= JAI.create("scale", params);
				JAI.create("filestore", image1, outputFileName, "JPEG", null);
				PrintLog("Created Image File "+i+": "+outputFileName);
			}
		} catch (IOException e) {
			PrintLog(e.getLocalizedMessage());
			return false;
		}
		return true;
	}
  	//
	public boolean DoJpegFromTiff (String tiffDir, String outputDir) {
		moduleName			= "DoJpegFromTiff: ";
		String tiffFile	= "";
		String jpegFile	= "";
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		String[] fileList	= FileUtil.GetTiffImageFiles(tiffDir);
		if (fileList != null) {	
			if (fileList.length > 0) {
				// loop through tiffDir 
				for (int i=0; i<fileList.length; i++) {
					//System.out.println(fileList[i]);
					tiffFile	= fileList[i].substring(0,fileList[i].indexOf("."));
					jpegFile	= tiffFile + ".jpg";
					doTiff2JPEG(outputDir, jpegFile, tiffFile);
				}
				return true;
			}
		}
		return false;
	}
	//
	/*
	public boolean DoJpegFromTiff (String mandateTiffs, String todaysDir) {
		return true;
	}
	 */
	//
	public static void main(String[] args) {
		JPEGfromTIFF jpTiff	= new JPEGfromTIFF();
		if (args.length < 2) {
			PrintLog("Please provide TIFF file name");
			System.exit(0);
		}
		String inFile = args[0];
		String outFile = args[1];
		String outDir	= "/temp";
		jpTiff.doTiff2JPEG(outDir, outFile, inFile);
	}
}