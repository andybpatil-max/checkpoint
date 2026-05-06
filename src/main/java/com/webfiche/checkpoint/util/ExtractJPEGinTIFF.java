package com.webfiche.checkpoint.util;

import java.io.*;
import java.util.Vector;
import java.awt.Point;

public class ExtractJPEGinTIFF {
	static private FileInputStream	imgTIFF					= null;
	static private byte[]			bytesTIFF				= null;
	static private Vector<Point>	startofIMG				= null;
	static private Vector<Point>	startofTIFF				= null;
	static private boolean			FileExists				= false;
	static private int				numImages				= 0;
	static private String			pos1					= null;
	static private String			pos2					= null;
	static private String			outputJPEGFileName		= "";
	static private String			outputTempTiffFileName	= "";
	static private String className = ">ExtractJPEGinTIFF.";
	static private String moduleName;
	//
	private static void PrintLog (String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()+
				className+moduleName+logMsg); }
	//
	public ExtractJPEGinTIFF() {
		//
	}
	//
	public static boolean GetJPEGFiles(String ioDir, String inFile,	String outFile) {
		moduleName = "GetJPEGFiles: ";
		int waitResult = 0;
		String size = "60%";
		try {
			imgTIFF			= new FileInputStream(ioDir + inFile);
			int fileSize	= imgTIFF.available();
			PrintLog("Input Image " + inFile);
			PrintLog("setArqTIFF> fileSize " + fileSize);
			bytesTIFF		= new byte[fileSize];
			imgTIFF.read(bytesTIFF, 0, fileSize);
		} catch (IOException error) {
			PrintLog(error.toString());
		}
		startofIMG = new Vector<Point>();
		FileExists = getStartOfJPEG();
		if (FileExists == true) {
			numImages				= startofIMG.size();
			PrintLog("Number of images: > " + numImages);
			outputTempTiffFileName	= ioDir + "temp_front.tiff";
			outputJPEGFileName		= ioDir + outFile + "_front.jpg";
			for (int idx = 0; idx < numImages; idx++) {
				try {
					// String outputJPEGFileName = "Check_"+imageNumber+".jpg";
					FileOutputStream fileTIFF = new FileOutputStream(outputTempTiffFileName);
					fileTIFF.write(getTIFF(idx));
					// fileJPEG.write(getJPEG(imageNumber));
					fileTIFF.close();
					//PrintLog("Created: "+ outputJPEGFileName);
					ProcessBuilder pb = new ProcessBuilder("convert","-resize", size, 
												outputTempTiffFileName,	outputJPEGFileName);
					pb.redirectErrorStream(true);
					try {
						Process p = pb.start();
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String line = null;
						while ((line = br.readLine()) != null) {
							System.out.println(line);
						}
						waitResult = p.waitFor();
						if (waitResult != 0) {
							System.out.println(p.waitFor());
						}
					} catch (Throwable t) {
						System.out.println(t.toString());
					}
				} catch (IOException e) {
					PrintLog("Exception Writing Front JPEG FIle: " + e.toString());
				}
				// createJpegFile(idx);
			}
			for (int idx = 0; idx < numImages; idx++) {
				try {
					outputJPEGFileName		= ioDir + outFile + "_back.jpg";
					// String outputJPEGFileName = "Check_"+imageNumber+".jpg";
					FileOutputStream fileJPEG	= new FileOutputStream(outputJPEGFileName);
					fileJPEG.write(getJPEG(idx));
					// fileJPEG.write(getJPEG(imageNumber));
					fileJPEG.close();
					PrintLog("Created: " + outputJPEGFileName);
				} catch (IOException e) {
					PrintLog("Exception Writing Back JPEG FIle: "	+ e.toString());
				}
			}
		}
		try {
			imgTIFF.close();
		} catch (IOException e) {
			PrintLog("Exception Tiff file  Close: " + e.toString());
		}
		return FileExists;
	}
	//
	private static boolean getStartOfJPEG() {
		int initial			= 0;
		int initialTiff		= 0;
		int endChar			= 0;
		int stripByte		= 0;
		int tiffSize		= 0;
		int ifdOff			= 0;
		int numEntries		= 0;
		int nextIfd			= 0;
		String firstChar	= "";
		Vector<Point> imgsVector	= new Vector<Point>();
		Vector<Point> tiffVector	= new Vector<Point>();
		for (int i = 0; i < bytesTIFF.length; i++) {
			String nextChar	= toStringHexaByte(bytesTIFF[i]);
			nextChar = nextChar.toUpperCase();
			/**
			 * Looking for start and endChar of the image JFIF/JPEG Initial: FF
			 * D8 FF E0... End: FF D9
			 */
			// Look for the start of Image
			if (firstChar.equals("49") && nextChar.equals("49")) {
				PrintLog("getStartOfTiff> offset Found 49");
				pos1	= toStringHexaByte(bytesTIFF[i + 1]).toUpperCase();
				pos2	= toStringHexaByte(bytesTIFF[i + 2]).toUpperCase();
				if (pos1.equals("2A") && pos2.equals("00")) {
					initialTiff = i - 1;
					// PrintLog("getStartOfTiff> offset "+initialTiff+"\t "+pos1+"-"+pos2);
					firstChar	= null;
					pos1		= toStringHexaByte(bytesTIFF[i + 3]).toUpperCase();
					ifdOff		= Integer.parseInt(pos1);
					pos2		= toStringHexaByte(bytesTIFF[i + ifdOff - 1]).toUpperCase();
					numEntries	= Integer.parseInt(pos2, 16);
					// PrintLog("getStartOfTiff> Num Entries \t i "+numEntries);
					nextIfd		= i + ifdOff - 1 + (numEntries * 12);
					pos1		= toStringHexaByte(bytesTIFF[nextIfd + 2]).toUpperCase();
					pos2		= toStringHexaByte(bytesTIFF[nextIfd + 3]).toUpperCase();
					// PrintLog("getStartOfTiff> IFD offset \t i "+i+" ifdOff "+ifdOff+" NextIfd1 "+nextIfd+" Pos1 "+pos1+" Pos2 "+pos2);
					bytesTIFF[nextIfd + 2]	= 0;
					bytesTIFF[nextIfd + 3]	= 0;
				} else {
					firstChar = null;
				}
			} else if (initial == 0 && stripByte == 0) {
				if (firstChar.equals("11") && nextChar.equals("01")) {
					pos1	= toStringHexaByte(bytesTIFF[i + 1]).toUpperCase();
					pos2	= toStringHexaByte(bytesTIFF[i + 2]).toUpperCase();
					if (pos1.equals("04") && pos2.equals("00")) {
						pos1	= toStringHexaByte(bytesTIFF[i + 7]).toUpperCase();
						pos2	= toStringHexaByte(bytesTIFF[i + 8]).toUpperCase();
						// PrintLog("getStartOfTiff> offset stripBytes"+"\t "+pos1+"-"+pos2);
						stripByte	= Integer.parseInt(pos2 + pos1, 16);
					} else {
						//
					}
				}
			} else if (initial == 0 && tiffSize == 0) {
				if (firstChar.equals("17") && nextChar.equals("01")) {
					pos1	= toStringHexaByte(bytesTIFF[i + 1]).toUpperCase();
					pos2	= toStringHexaByte(bytesTIFF[i + 2]).toUpperCase();
					if (pos1.equals("04") && pos2.equals("00")) {
						pos1	= toStringHexaByte(bytesTIFF[i + 7]).toUpperCase();
						pos2	= toStringHexaByte(bytesTIFF[i + 8]).toUpperCase();
						// PrintLog("getStartOfTiff> offset stripBytes"+"\t "+pos1+"-"+pos2);
						tiffSize	= Integer.parseInt(pos2 + pos1, 16);
					} else {
						//
					}
				}
			} else if (firstChar.equals("FF") && nextChar.equals("D8")) {
				String pos1	= toStringHexaByte(bytesTIFF[i + 1]).toUpperCase();
				String pos2	= toStringHexaByte(bytesTIFF[i + 2]).toUpperCase();
				if (pos1.equals("FF") && pos2.equals("E0")) {
					// PrintLog(java.util.Calendar.getInstance().getTime()+
					// moduleName+"getStartOfJPEG> offset "+i+"\t "+pos1+"-"+pos2);
					initial		= i - 1;
					firstChar	= null;
				} else {
					firstChar	= null;
				}
			} else if (initial != 0 && firstChar.equals("FF") && nextChar.equals("D9")) {
				// Look for the end of Image
				endChar		= i + 1;
				firstChar	= null;
			}
			// --------------------------------------------------------------------
			firstChar	= nextChar;
			// Ensure we have valid initial and endChar for the image JFIF/JPEG
			if (initial != 0 && endChar != 0) {
				tiffVector.add(new Point(initialTiff, initialTiff + stripByte + tiffSize));
				imgsVector.add(new Point(initial, endChar));
				initial		= 0;
				endChar		= 0;
				initialTiff	= 0;
				stripByte	= 0;
				tiffSize	= 0;
				firstChar	= "";
				nextChar	= "";
			}
			;
		}
		// Verify that we have found images
		if (imgsVector.size() > 0) {
			startofIMG	= imgsVector;
			startofTIFF	= tiffVector;
			return true;
		} else {
			return false;
		}
	}
	//
	private static String toStringHexaByte(byte bytes) {
		StringBuffer s = new StringBuffer();

		int parteAlta	= ((bytes >> 4) & 0xf) << 4;
		int parteBaixa	= bytes & 0xf;
		if (parteAlta == 0)
			s.append('0');

		String strHex	= Integer.toHexString(parteAlta | parteBaixa);
		s.append(strHex);

		return s.toString();
	}
	//
	private static byte[] getJPEGinTIFF(byte[] imgBytes, int setI, int setF) {
		moduleName = "getJPEGinTIFF: ";
		ByteArrayOutputStream bytesExtracted = null;
		try {
			bytesExtracted	= new ByteArrayOutputStream();
			bytesExtracted.write(imgBytes, setI, setF - setI);
		} catch (Exception e) {
			PrintLog(e.toString());
		}
		return bytesExtracted.toByteArray();
	}
	//
	public static byte[] getJPEG(int imageNumber) {
		if (imageNumber >= 0 && startofIMG.size() > 0) {
			byte[] temp = getJPEGinTIFF(bytesTIFF,
					((Point) startofIMG.elementAt(imageNumber)).x,
					((Point) startofIMG.elementAt(imageNumber)).y);
			return temp;
		} else {
			return null;
		}
	}
	// /************************************************************************/
	public static byte[] getTIFF(int imageNumber) {
		// ByteOrder bOrder = ByteOrder.BIG_ENDIAN;
		if (imageNumber >= 0 && startofIMG.size() > 0) {
			byte[] temp = getJPEGinTIFF(bytesTIFF,
					((Point) startofTIFF.elementAt(imageNumber)).x,
					((Point) startofTIFF.elementAt(imageNumber)).y);
			// ByteBuffer byteBuf = ByteBuffer.allocate(temp.length);
			//
			// Here change the order
			//
			// byteBuf.order(ByteOrder.BIG_ENDIAN);
			// byteBuf.put(temp,0,temp.length);
			// byte[] newBytes = byteBuf.array();
			// return newBytes;
			return temp;
		} else {
			return null;
		}
	}
}
