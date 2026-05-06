package com.webfiche.checkpoint.util;

//Import JAWIN, required for communication with the A2iA COM Object interface
import org.jawin.DispatchPtr;
import org.jawin.win32.Ole32;

// Needed to read text file containing list of images
import java.io.*;
import java.util.*;
import java.text.*;
import java.util.Map;

public class GetCAR {
	private String className	= "> GetCAR.";
	private String moduleName	= "";
	public GetCAR() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}
	//
	public void ExtractCAR () throws IOException {
		moduleName	= "ExtractCAR: ";
		ZipFilesUtil zF	= new ZipFilesUtil();
		String imageDir	= (String) ((Map<String, String>) (System.getenv())).get("imagedir");
		String zipDir	= (String) ((Map<String, String>) (System.getenv())).get("zipdir");
		String a2iaBase	= (String) ((Map<String, String>) (System.getenv())).get("a2iaBase");
		GregorianCalendar calendar	= new GregorianCalendar();
		DateFormat format			= DateFormat.getInstance();
		DataInTiff setAmt			= new DataInTiff();
		format						= new SimpleDateFormat("yyyyMMdd_HHmmss");
		String procDate				= format.format(calendar.getTime());
		byte[] newText;
		String carOut;
		// String filePath = "Sources/COM/JAVA/A2iA Integration/A2iA files/";
		// String rootPath = "C:/Program Files/A2iA/A2iA CheckReader V4.7 R2/";
		//String micr				= "";
		Integer amount			= 0;
		Integer amountScore		= 0;
		//Integer payeeNameScore	= 0;
		//String payeeName		= "";
		Integer count			= 1;
		/*
		 * Variables necessary for A2iA functionality
		 */
		Integer requestId		= 0;
		Integer resultId		= 0;
		Integer channelId		= 0;
		Integer tableId			= 0;
		Integer documentId		= 0;
		Integer channelParamId	= 0;
		/*
		 * Variable to hold path of image that needs to be processed
		 */
		String imageFilenamePath;
		// Timeout variable (in milliseconds) to be used in the GetResult and
		// ScrOpenChannelExt functions
		Integer timeout			= 60000;
		// Setup reading of text file containing list of images to be processed by A2iA
		// FileReader file = new FileReader(a2iaBase+filePath+"FileList.txt");
		// BufferedReader reader = new BufferedReader(file);
		// Setup file output
		FileOutputStream writeCARrep	= new FileOutputStream(imageDir + "CARReport_" + procDate + ".txt");
		PrintLog("CAR report :" + imageDir + "CARReport_" + procDate + ".txt");
		// PrintWriter Output = new PrintWriter (new FileWriter(imageDir+"CARReport.txt"));
		PrintWriter Error	= new PrintWriter(new FileWriter("Error.txt"));
		// Specify the table file location and the Param directory
		final String TBLFileName	= "US_AMT.tbl";
		final String paramDir		= a2iaBase + "Parms/Int/Parms";
		try {
			// Create COM library for use with JAVA
			Ole32.CoInitialize();
			DispatchPtr A2iAObj		= new DispatchPtr("A2iACheckReaderCOM.APICheckReader");
			// Initialize engine with proper ParamPath directory
			A2iAObj.invoke("ScrInit", paramDir);
			//
			// Create channel parameter object
			//
			channelParamId			= (Integer) A2iAObj.invoke("ScrCreateChannelParam");
			//
			// Add dongleServer and/or paramDir and/or cpuServer inside the
			// channel Parameters object
			//
			A2iAObj.invoke("SetProperty", channelParamId, "cpu[1].cpuServer","");
			A2iAObj.invoke("SetProperty", channelParamId, "cpu[1].paramDir",paramDir);
			A2iAObj.invoke("SetProperty", channelParamId,"cpu[1].dongleServer", "127.0.0.1");
			// Open the channel
			channelId = (Integer) A2iAObj.invoke("ScrOpenChannelExt",channelParamId, 0);
			// Open the table file;
			tableId = (Integer) A2iAObj.invoke("ScrOpenDocumentTable",TBLFileName);
			// Upload persistent data, if necessary
			// A2iAObj.invoke("ScrUploadDocumentTablePersistentData", tableId,
			// channelId);
			// Initialize the document
			documentId = (Integer) A2iAObj.invoke("ScrGetDefaultDocument",tableId);
			// Loop through the image list file to process each image. Exit with
			// end of file condition.
			File f = new File(imageDir);
			String files[] = f.list();
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].indexOf("A.t") > 0) {
						// while ((imageFilenamePath = reader.readLine())!= null) {
						// Define image
						// A2iAObj.invoke("ScrDefineImage",
						// documentId,"TIFF","FILE",imageFilenamePath);
						imageFilenamePath = imageDir + files[i];
						A2iAObj.invoke("ScrDefineImage", documentId, "TIFF", "FILE", imageFilenamePath);
						// Open a request
						requestId = (Integer) A2iAObj.invoke("ScrOpenRequest", channelId, documentId);
						// Retrieve results and check for possible errors
						int istatus = 0;
						try {
							resultId = (Integer) A2iAObj.invoke("ScrGetResult", channelId, requestId, timeout);
						} catch (Exception e) {
							// Write error to error file
							PrintLog("Cannot get result for image #" + count + " at " + 
									 imageFilenamePath + ". Trying next document");
							PrintLog(e+"");
							// Change variable to check status if existing error condition occurred
							istatus = 1;
							// Increment image count to keep track of unprocessed images
							count++;
							// Continue with code;
							continue;
						}
						// Proceed with data extraction only if no error
						// occurred at GetResult stage of application
						if (istatus == 0) {
							// Extract amount and amount score
							amount		= (Integer) A2iAObj.invoke("GetProperty", resultId,
																	"documentTypeInfo.CaseCheck.check.result.amount");
							amountScore	= (Integer) A2iAObj.invoke("GetProperty", resultId,
																	"documentTypeInfo.CaseCheck.check.result.score");
							// micr = (String)A2iAObj.invoke("GetProperty",resultId,
							// 								 "documentTypeInfo.CaseCheck.check.result.micr");
							// PrintLog("MICR :"+imageFilenamePath+" "+micr);
							// Extract payee name and payee name score
							/*
							 * payeeName = (String)A2iAObj.invoke("GetProperty", resultId,
							 * "documentTypeInfo.CaseCheck.check.payeeName.result.reco");
							 *  payeeNameScore = (Integer)A2iAObj.invoke("GetProperty", resultId,
							 * "documentTypeInfo.CaseCheck.check.payeeName.result.score");
							 */
							// Output image number (count variable) for informational purposes
							PrintLog("Processing :"	+ imageFilenamePath	+ " #: " + count);
							// Output data extracted by A2iA (either to console, or to text file)
							// Output.println(imageFilenamePath + "\t" +
							carOut = (files[i] + "\t" + amount + "\t"
									+ amountScore + "\n");
							System.out.print(carOut);
							newText = carOut.getBytes();
							writeCARrep.write(newText, 0, carOut.length());
							//
							// Here update tag field 270 with amount and score
							//
							setAmt.SetTiffField(imageFilenamePath, 270,
									amount.toString(), amountScore.toString());
							// Output.println(files[i] + "\t" + amount + "\t" + amountScore);
							// PrintLog("Payee: " + payeeName + "; Score: " + payeeNameScore);
							// Output.println("Payee: " + payeeName + "; Score: " + payeeNameScore);
							// Output.println("Amount: " + amount + "; Score: " + amountScore + "\n");
						}
						// Closes a request after processing or after error occurs at GetResult phase
						A2iAObj.invoke("ScrCloseRequest", requestId);
						// Increment image count variable after processing
						count++;
					}
				}
				// Close results and error output files
				writeCARrep.close();
				// Output.close();
				Error.close();
				// Close all A2iA items in appropriate order
				A2iAObj.invoke("ScrCloseDocument", documentId);
				A2iAObj.invoke("ScrCloseDocumentTable", tableId);
				// A2iAObj.invoke("ScrFreePersistentData", channelId);
				A2iAObj.invoke("ScrFreeAllPersistents", channelId);
				A2iAObj.invoke("ScrCloseChannel", channelId);
				// Call to tear down the COM library when application is
				// finished
				Ole32.CoUninitialize();
				// Output informational item to console, indicating successful
				// execution of application
				// Here call the Zip Files
				/*
				 * try {
				 * 		Thread.sleep(1000*60*1); \\ 1 minute
				 * } catch (Throwable t) {
				 * 		PrintLog("Woke Up");
				 * }
				 */
				zF.ZipFiles(imageDir, zipDir, "CheckImages");
				PrintLog("Application Completed.");
			} else {
				PrintLog("GetCAR: No files to Process at this time");
			}
		} catch (Exception e) {
			// Global error catch routine. Handles all fatal application errors
			// Output error to console
			PrintLog("Application error: " + e);
			e.printStackTrace(System.out);
		}
	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Variables to hold results for amount, amount score, payee name, and payee name score
		GetCAR exCar	= new GetCAR();
		exCar.ExtractCAR();
	}
}
