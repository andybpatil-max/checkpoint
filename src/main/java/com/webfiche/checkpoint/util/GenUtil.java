package com.webfiche.checkpoint.util;
//
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.Normalizer;
//import java.text.*;
//import java.lang.Number;

public final class GenUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Pattern dmy;
	private Pattern mdy;
	private Pattern ymd;
	private Pattern number;
	private Pattern amount;
	private Pattern amountSw;
	private Matcher matcher;
	/*
	 * 	DMY (0?[1-9]|[12][0-9]|3[01])[-/.](0?[1-9]|1[012])[-/.]((19|20)\\d\\d);
		MDY (0?[1-9]|1[012])[-/.](0?[1-9]|[12][0-9]|3[01])[-/.]((19|20)\\d\\d);
		YMD ((19|20)\\d\\d)[-/.](0?[1-9]|[12][0-9]|3[01])[-/.](0?[1-9]|1[012]);
	 */
	private static final String validDMY		= "(0?[1-9]|[12][0-9]|3[01])[-/.](0?[1-9]|1[012])[-/.]((19|20)?\\d\\d)";
	private static final String validMDY		= "(0?[1-9]|1[012])[-/.](0?[1-9]|[12][0-9]|3[01])[-/.]((19|20)?\\d\\d)";
	private static final String validYMD		= "((19|20)?\\d\\d)[-/.](0?[1-9]|1[012])[-/.](0?[1-9]|[12][0-9]|3[01])";
	/*
	private static final String regExYMD		= "^\\d{2/4}[-/.]\\d{1,2}[-/.]\\d{1,2}$";
	private static final String regExMDY		= "^\\d{1,2}[-/.]\\d{1,2}[-/.]\\d{2/4}$";
	private static final String regExDMY		= "^\\d{1,2}[-/.]\\d{1,2}[-/.]\\d{2/4}$";
	*/
	private static final String regExAmountSw	= "(^\\d{1,3}(\\.?\\d{3})*(,\\d{2})?$)";
	private static final String regExAmount		= "(^\\d{1,3}(,?\\d{3})*(\\.\\d{2})?$)";
	private static final String regExNumber		= "^\\d+$";
	private String moduleName	= "";
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> UploadNYChex." + moduleName + logMsg);
	}
	//
	public GenUtil() {
		dmy			= Pattern.compile(validDMY);
		mdy			= Pattern.compile(validMDY);
		ymd			= Pattern.compile(validYMD);
		number		= Pattern.compile(regExNumber);
		amount		= Pattern.compile(regExAmount);
		amountSw	= Pattern.compile(regExAmountSw);
	}
	//
	public static String pad(String in, int max_len, String padder) {
		// Left Pads with (max_len - in.length()) padder chars
		String temp		= "";
		int pad_count	= 0;
		//
		// If negative max_len just send back the in string
		//
		if (max_len < 1) {
			return (in);
		}
		//
		// If max_len < than in len then send back the left max_len chars of in
		//
		if (max_len < in.length()) {
			temp	= in.substring(0, max_len);
			return (temp);
		}
		//
		// Here pad the required number of padder char on the left and send back
		//
		pad_count = max_len - in.length();
		for (int idx = 0; idx < pad_count; idx++) {
			temp	+= padder;
		}
		temp	+= in;
		return (temp);
	}
	//
	public static String padRight(String in, int max_len, String padder) {
		// Left Pads with (max_len - in.length()) padder chars
		String temp		= "";
		int pad_count	= 0;
		//
		// If negative max_len just send back the in string
		//
		if (max_len < 1) {
			return (in);
		}
		//
		// If max_len < than in len then send back the left max_len chars of in
		//
		if (max_len < in.length()) {
			temp	= in.substring(0, max_len);
			return (temp);
		}
		//
		// Here pad the required number of padder char on the right and send back
		//
		pad_count = max_len - in.length();
		for (int idx = 0; idx < pad_count; idx++) {
			temp	+= padder;
		}
		temp	= in + temp;
		return (temp);
	}
	//
	public static String padLeft(String in, int max_len, String padder) {
		// Left Pads with (max_len - in.length()) padder chars
		String temp		= "";
		int pad_count	= 0;
		//
		// If negative max_len just send back the in string
		//
		if (max_len < 1) {
			return (in);
		}
		//
		// If max_len < than in len then send back the left max_len chars of in
		//
		if (max_len < in.length()) {
			temp	= in.substring(0, max_len);
			return (temp);
		}
		//
		// Here pad the required number of padder char on the right and send back
		//
		pad_count = max_len - in.length();
		for (int idx = 0; idx < pad_count; idx++) {
			temp	+= padder;
		}
		temp	= temp + in;
		return (temp);
	}
	//
	public static String strip_commas(String amount) {
		String temp	= "";
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// " Strip Commas InPUT ---> "+amount);
		for (int idx = 0; idx < amount.length(); idx++) {
			if (!amount.substring(idx, idx + 1).equals(",")) {
				temp	+= amount.substring(idx, idx + 1);
			}
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// " Strip Commas OutPUT ---> "+temp);
		return temp;
	}
	//
	public static String stripSlashes(String dataField) {
		String temp = "";
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// " Strip Commas InPUT ---> "+amount);
		if (dataField != null) {
			for (int idx = 0; idx < dataField.length(); idx++) {
				if (!dataField.substring(idx, idx + 1).equals("/")) {
					temp	+= dataField.substring(idx, idx + 1);
				}
			}
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// " Strip Commas OutPUT ---> "+temp);
			return temp;
		} else {
			return dataField;
		}
	}
	//
	public static String[] splitByLength (String inStr, int len) {
	    String[] result	= new String[(int)Math.ceil((double)inStr.length()/(double)len)];
	    for (int i=0; i<result.length; i++) {
	        result[i]	= inStr.substring(i*len, Math.min(inStr.length(), (i+1)*len));
	    }
	    return result;
	}
	//
    public boolean isDigits(String token) { 
		matcher	= number.matcher(token);
		return matcher.matches();
	}
	//
    public boolean isValidDMY(String token) { 
		matcher	= dmy.matcher(token);
		return matcher.matches();
    }
    //
    public boolean isValidMDY(String token) { 
		matcher	= mdy.matcher(token);
		return matcher.matches();
    }
    //
    public boolean isValidYMD(String token) { 
		matcher = ymd.matcher(token);
		return matcher.matches();
    }
    //
    public boolean isAmountSw(String token) { 
		matcher = amountSw.matcher(token);
		return matcher.matches();
	}
	//
    public boolean isAmount(String token) { 
		matcher = amount.matcher(token);
		return matcher.matches();
	}
	//
	public boolean isNumeric(String token) {
		moduleName	= "isNumeric: ";
		boolean ok = false;
		try {
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//					" Argument "+token);
			Double.valueOf(token).doubleValue();
			ok	= true;
		} catch (Exception e) {
		}
		return ok;
	}
	//
	public boolean isInteger(String token) {
		boolean ok	= false;
		try {
			Integer.parseInt(token);
			ok	= true;
		} catch (Exception e) {
		}
		return ok;
	}
	//
	public String getNumericAmount(String amount2Format) {
		/*
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number;
		try {
			number = format.parse(amount2Format);
			System.out.println(number.toString());
			return number.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		if (amount2Format == null) {
			amount2Format	= " ";
		}
		//System.out.println("GenUtil: "+amount2Format );
		amount2Format	= amount2Format.replaceAll(",", "");
		amount2Format	= amount2Format.replaceAll("\\$", "");
		return (amount2Format);
	}
	//
	public String getNumber(String alphanum) {
		String ret_str	= "";
		// System.out.println("Alphanum : "+alphanum);
		for (int i = 0; i < alphanum.length(); i++) {
			if (isNumeric(alphanum.substring(i, i + 1)) == true) {
				ret_str	+= alphanum.substring(i, i + 1);
				// System.out.println("Ret Str : "+ret_str);
			}
		}
		return (ret_str);
	}
	//
	public boolean inRange(String lowerBound, String upperBound, String input) {
	    // (First, be sure to check for null values)
	    return input.compareToIgnoreCase(lowerBound) >= 0 && input.compareToIgnoreCase(upperBound) <= 0;
	}
	//
	public boolean inRange(int lowerBound, int upperBound, int input) {
	    // (First, be sure to check for null values)
	    return (input >= lowerBound && input <= upperBound);
	}
	//
	public boolean inRange(Double lowerBound, Double upperBound, Double input) {
	    // (First, be sure to check for null values)
	    return (input >= lowerBound && input <= upperBound);
	}
	//
	public boolean dirExists (String dirSpec) {
		moduleName	= "dirExists: ";
		boolean mkDir	= false;
		File repDir	= new File(dirSpec);
		if (!repDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > "	+ dirSpec);
			mkDir	= repDir.mkdirs();
			if (mkDir == false) {
				PrintLog("Error creating Directory " + dirSpec);
			}
		}
		return mkDir;
	}
	//
	public String unAccent(String s) {
		//
		// JDK1.5
		//   use sun.text.Normalizer.normalize(s, Normalizer.DECOMP, 0);
		//
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

}
