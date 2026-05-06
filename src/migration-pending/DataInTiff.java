package com.webfiche.checkpoint.util;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.imageio.stream.*;
import com.sun.media.imageio.plugins.tiff.*;
//
public class DataInTiff {
	private InputStream in;
	//
	public void SetTiffField(String tiffImage, int tiffTag, String cAmt, String aScore)
		throws Exception {
		String inputImage		= tiffImage;
		String outputFile		= tiffImage;
		in						= new FileInputStream(inputImage);
		int dataLength			= in.available();
		byte imageDataBytes[]	= new byte[dataLength];
		int byteRead			= 0;
		int totalBytesRead		= 0;
		while (totalBytesRead < dataLength) {
			byteRead		= in.read(imageDataBytes, totalBytesRead, dataLength);
			totalBytesRead	+= byteRead;
		}
		ByteArrayInputStream inputByteStream		= new ByteArrayInputStream(imageDataBytes);
		Iterator<?> imageReaderItr					= ImageIO.getImageReadersByFormatName("tif");
		ImageReader imageReader						= (ImageReader) imageReaderItr.next();
		MemoryCacheImageInputStream imageInStream	= new MemoryCacheImageInputStream(inputByteStream);
		imageReader.setInput(imageInStream);
		int numberOfPages							= imageReader.getNumImages(true);
		IIOMetadata iioMetaData						= imageReader.getImageMetadata(0);
		BufferedImage bufferedImage					= null;
		for (int pages = 0; pages < numberOfPages; pages++) {
			IIOImage iio				= imageReader.readAll(pages, new ImageReadParam());
			bufferedImage				= (BufferedImage) iio.getRenderedImage();
			IIOMetadata imageMetadata	= imageReader.getImageMetadata(pages);
			TIFFDirectory ifd			= TIFFDirectory.createFromMetadata(imageMetadata);
			int tagVal					= tiffTag;
			if (ifd.containsTIFFField(tagVal)) {
				String fieldValue	= ifd.getTIFFField(tagVal).getValueAsString(0);
				System.out.println("FieldValue Tag " + tagVal + ": " + fieldValue);
				//
				// If a MICR field already exists then extract that and add new
				// Amt and score to it
				//
				if (fieldValue.startsWith("MICR")) {
					String[] tag270	= fieldValue.split("\\ ");
					fieldValue		= tag270[0];
					fieldValue		= fieldValue.replaceAll(".*MICR=","MICR=");
					System.out.println("FieldValue MICR " + fieldValue);
				}
				// Here attempt to update the field value
				String newFieldVal		= fieldValue + " AMOUNT=" + cAmt + " SCORE=" + aScore;
				String[] newFieldValue	= new String[] { newFieldVal };
				ifd.removeTIFFField(tagVal);
				TIFFField newField		= new TIFFField(ifd.getTag(tagVal),
														TIFFTag.TIFF_ASCII, 1, newFieldValue);
				ifd.addTIFFField(newField);
				iioMetaData				= ifd.getAsMetadata();
			} else {
				String newFieldVal		= "AMOUNT=" + cAmt + " SCORE=" + aScore;
				String[] newFieldValue	= new String[] { newFieldVal };
				TIFFField newField		= new TIFFField(ifd.getTag(tagVal),
														TIFFTag.TIFF_ASCII, 1, newFieldValue);
				ifd.addTIFFField(newField);
				iioMetaData				= ifd.getAsMetadata();
			}
		}
		// System.out.println("Saving the annotated Image.");
		Iterator<?> writers		= ImageIO.getImageWritersBySuffix("tif");
		ImageWriter writer		= (ImageWriter) writers.next();
		OutputStream out		= new FileOutputStream(outputFile);
		ImageOutputStream ios	= ImageIO.createImageOutputStream(out);
		writer.setOutput(ios);
		IIOImage iioImage		= null;
		iioImage				= new IIOImage(bufferedImage, null, iioMetaData);
		writer.write(null, iioImage, null);
		((ImageOutputStream) writer.getOutput()).flush();
		((ImageOutputStream) writer.getOutput()).close();
	}
	//
	public void SetTiffField(String tiffImage, int tiffTag, String tagContent)
		throws Exception {
		String inputImage		= tiffImage;
		String outputFile		= tiffImage;
		in						= new FileInputStream(inputImage);
		int dataLength			= in.available();
		byte imageDataBytes[]	= new byte[dataLength];
		int byteRead			= 0;
		int totalBytesRead		= 0;
		while (totalBytesRead < dataLength) {
			byteRead		= in.read(imageDataBytes, totalBytesRead, dataLength);
			totalBytesRead	+= byteRead;
		}
		ByteArrayInputStream inputByteStream		= new ByteArrayInputStream(imageDataBytes);
		Iterator<?> imageReaderItr					= ImageIO.getImageReadersByFormatName("tif");
		ImageReader imageReader						= (ImageReader) imageReaderItr.next();
		MemoryCacheImageInputStream imageInStream	= new MemoryCacheImageInputStream(inputByteStream);
		imageReader.setInput(imageInStream);
		int numberOfPages							= imageReader.getNumImages(true);
		IIOMetadata iioMetaData						= imageReader.getImageMetadata(0);
		BufferedImage bufferedImage					= null;
		for (int pages = 0; pages < numberOfPages; pages++) {
			IIOImage iio				= imageReader.readAll(pages, new ImageReadParam());
			bufferedImage				= (BufferedImage) iio.getRenderedImage();
			IIOMetadata imageMetadata	= imageReader.getImageMetadata(pages);
			TIFFDirectory ifd			= TIFFDirectory.createFromMetadata(imageMetadata);
			int tagVal					= tiffTag;
			if (ifd.containsTIFFField(tagVal)) {
				String fieldValue	= ifd.getTIFFField(tagVal).getValueAsString(0);
				System.out.println("FieldValue Tag " + tagVal + ": " + fieldValue);
				ifd.removeTIFFField(tagVal);
			}
			TIFFField newField		= new TIFFField(ifd.getTag(tagVal),
													TIFFTag.TIFF_ASCII, 1, tagContent);
			ifd.addTIFFField(newField);
			iioMetaData				= ifd.getAsMetadata();
		}
		// System.out.println("Saving the annotated Image.");
		Iterator<?> writers		= ImageIO.getImageWritersBySuffix("tif");
		ImageWriter writer		= (ImageWriter) writers.next();
		OutputStream out		= new FileOutputStream(outputFile);
		ImageOutputStream ios	= ImageIO.createImageOutputStream(out);
		writer.setOutput(ios);
		IIOImage iioImage		= null;
		iioImage				= new IIOImage(bufferedImage, null, iioMetaData);
		writer.write(null, iioImage, null);
		((ImageOutputStream) writer.getOutput()).flush();
		((ImageOutputStream) writer.getOutput()).close();
	}
	//
	public String GetTiffField(String tiffImage, int tiffTag) throws Exception {
		String inputImage		= tiffImage;
		in = new FileInputStream(inputImage);
		//
		int dataLength			= in.available();
		byte imageDataBytes[]	= new byte[dataLength];
		int byteRead			= 0;
		int totalBytesRead		= 0;
		while (totalBytesRead < dataLength) {
			byteRead			= in.read(imageDataBytes, totalBytesRead, dataLength);
			totalBytesRead		+= byteRead;
		}
		ByteArrayInputStream inputByteStream	= new ByteArrayInputStream(imageDataBytes);
		Iterator<?> imageReaderItr				= ImageIO.getImageReadersByFormatName("tif");
		ImageReader imageReader					= (ImageReader) imageReaderItr.next();
		MemoryCacheImageInputStream imageInStream	= new MemoryCacheImageInputStream(inputByteStream);
		imageReader.setInput(imageInStream);
		int numberOfPages				= imageReader.getNumImages(true);
		// IIOMetadata iioMetaData		= imageReader.getImageMetadata(0);
		// BufferedImage bufferedImage	= null;
		// Graphics2D graphics2D		= null;
		// ArrayList<?> imageList		= new ArrayList<Object>();
		for (int pages = 0; pages < numberOfPages; pages++) {
			// IIOImage iio				= imageReader.readAll(pages, new ImageReadParam());
			// bufferedImage			= (BufferedImage) iio.getRenderedImage();
			IIOMetadata imageMetadata	= imageReader.getImageMetadata(pages);
			TIFFDirectory ifd			= TIFFDirectory.createFromMetadata(imageMetadata);
			int tagVal					= tiffTag;
			if (ifd.containsTIFFField(tagVal)) {
				String fieldValue		= ifd.getTIFFField(tagVal).getValueAsString(0);
				System.out.println("FieldValue Tag " + tagVal + ": " + fieldValue);
				return (fieldValue);
				// Here attempt to update the field value
			} else {
				System.out.println("Tag " + tiffTag + " not found");
			}
		}
		in.close();
		return ("");
	}
	public String GetTiffField(byte[] in, int tiffTag) throws Exception {
		ByteArrayInputStream inputByteStream		= new ByteArrayInputStream(in);
		Iterator<?> imageReaderItr					= ImageIO.getImageReadersByFormatName("tif");
		ImageReader imageReader						= (ImageReader) imageReaderItr.next();
		MemoryCacheImageInputStream imageInStream	= new MemoryCacheImageInputStream(inputByteStream);
		imageReader.setInput(imageInStream);
		int numberOfPages							= imageReader.getNumImages(true);
		System.out.println("Number Of Pages " + numberOfPages);
		for (int pages = 0; pages < numberOfPages; pages++) {
			IIOMetadata imageMetadata	= imageReader.getImageMetadata(pages);
			TIFFDirectory ifd			= TIFFDirectory.createFromMetadata(imageMetadata);
			int tagVal					= tiffTag;
			if (ifd.containsTIFFField(tagVal)) {
				String fieldValue	= ifd.getTIFFField(tagVal).getValueAsString(0);
				System.out.println("FieldValue Tag " + tagVal + ": " + fieldValue);
				return (fieldValue);
				// Here attempt to update the field value
			} else {
				System.out.println("Tag " + tiffTag + " not found");
			}
		}
		return ("");
	}
}
