package com.webfiche.checkpoint.util;

//import java.util.*;

public class Amt2Txt {
    private static String[] amount		= {"","","","","","","","","","","","","","",""};
    private static String[] one29teen	= {""," One"," Two"," Three"," Four"," Five"," Six",
					   						" Seven"," Eight"," Nine", " Ten", " Eleven",
					   						" Twelve"," Thirteen"," Fourteen", " Fifteen",
					   						" Sixteen"," Seventeen"," Eighteen", " Nineteen"};
    private static String[] biggies		= {" Billion"," Ten", "", " Million", "", "", " Thousand"};
    private static String[] tenners		= {"",""," Twenty"," Thirty"," Fourty"," Fifty",
					   						" Sixty", " Seventy"," Eighty"," Ninety"};
    //
    public String GetAmtInWords (String amtNum) {
    	//System.out.println("Amount entered : '"+amtNum+"' length "+amtNum.length());
    	String amtTxt		= "";
    	String tempAmt		= "";
    	int tempInt		= 0;
    	int j, k		= 0;
    	for (int i = 0; i<amtNum.length(); i++) {
    		amount[i]	= amtNum.substring(i,i+1);
    		//System.out.println("Amount : '"+amount[i]+"'");
    	}
    	for (j = 0; j<15; j+=3) {
    		for (int i = 0; i<3; i++) {
    			//System.out.println("Amount : '"+amount[i+j]+"'");
    			if (amount[i+j].equals(" ")) {
    				continue;
    			}
    			if ((i == 0) && (j != 12)) {
    				tempInt	= Integer.parseInt(amount[i+j]);
    				if (tempInt > 0) {
    					tempAmt	= one29teen[tempInt] + " Hundred";
    				}
    			} else if (i == 1) {
    				tempInt	= Integer.parseInt(amount[i+j]+amount[i+j+1]);
    				k		= Integer.parseInt(amount[i+j]);
    				if (tempInt < 20) {	
    					tempAmt	+= one29teen[tempInt];
    				} else {
    					tempAmt	+= tenners[k];
    				}
    			} else {
    				//System.out.println("Amount : '"+amount[i+j]+"'");
    				if (!amount[i+j].equals(".")) {
    					tempInt	= Integer.parseInt(amount[i+j]);
    					//System.out.println("Amount : '"+tempInt+"'");
    					if (amount[i+j-1].equals(" ")) {
    						tempAmt	+= one29teen[Integer.parseInt(amount[i+j])];
    					} else if (Integer.parseInt(amount[i+j-1]) > 1) {
    						tempAmt	+= one29teen[Integer.parseInt(amount[i+j])];
    					}
    				}
    			}
    		}
    		if ((j < 9) && !tempAmt.equals("")) {
    			amtTxt	+= tempAmt + biggies[j];
    		}
    		if ((j == 9) && (!tempAmt.equals(""))) {
    			amtTxt	+= tempAmt;
    		}
    		//System.out.println("TempInt : '"+tempInt+"'");
    		if ((j == 12) && (!tempAmt.equals(""))) {
    			if (tempInt > 0) {
    				if (Integer.parseInt(amount[13]) > 0) {
    					amtTxt	+= " &" + tempAmt + " Cents";
    				} else if (tempInt > 1) {
    					amtTxt	+= " &" + tempAmt + " Cents";
    				} else {
    					amtTxt	+= " &" + tempAmt + " Cent";
    				}
    			} else {
    				amtTxt	+= " &" + tempAmt + " Cents";
    			}
    		}
    		tempAmt	= "";
    	}
    	amtTxt	= "Dollar(s)" + amtTxt + " Only";
    	return amtTxt;
    }
    //
    // To run at command line
    //
    public static void main(String[] args) {
    	Amt2Txt a2t	=new Amt2Txt();
    	if (args.length > 0) {
    		String amtNum	= args[0];
    		amtNum	= amtNum.replaceAll(",","");
    		if (amtNum.indexOf(".") > 0) {
    			if ((amtNum.length()-3) == amtNum.indexOf(".")) {
    			} else {
    				amtNum	= amtNum + "0";
    			}
    		} else {
    			amtNum	= amtNum + ".00";
    		}
    		amtNum	= "               ".substring(0,15-amtNum.length()) + amtNum; 
    		System.out.println("Amount entered : '"+amtNum+"' length "+amtNum.length());
    		System.out.println("Amount in Words : "+a2t.GetAmtInWords(amtNum));
    	} else {
    		System.out.println("Please enter amount a max value of 999,999,999,999.99:");
    	}
    }
}