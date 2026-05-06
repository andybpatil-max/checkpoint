package com.webfiche.checkpoint.deposits.service;

//import java.io.*;
import java.io.File;
import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.deposits.beans.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.inclear.service.*;
import com.webfiche.checkpoint.service.EcontReportUtil;
//import com.webfiche.checkpoint.util.*;
//import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.util.*;

@Service
public class DepsDDAUtil {
	String moduleName;
	String className		= "> DepsDDAUtil.";
	String calledFrom		= "";
	//
	private String availSameDay	= "";
	private int    availDay[]	= {0, 0, 0, 0, 0};
	private double availLow[]	= {0.00, 0.00, 0.00, 0.00, 0.00};
	private double availHigh[]	= {0.00, 0.00, 0.00, 0.00, 0.00};
	private AcctentrySelector	acctentrySelector	= new AcctentrySelector();
	private AccountDetail acctDetail				= new AccountDetail();
	private ArrayList<DepsDetail> depsRows			= new ArrayList<DepsDetail>();
	private ArrayList<AcctentryDetail> postingRows	= new ArrayList<AcctentryDetail>();
	private ArrayList<String> postSeq				= new ArrayList<String>();
	//
	EcontServletContextListener escl	= new EcontServletContextListener();
	InclAcctUtil acUtil					= new InclAcctUtil();
	DepsChexUtil dcUtil					= new DepsChexUtil();
	//GenUtil		gUtil					= new GenUtil();
	String param			= "";
	int row_count			= 0;
	String spaceFiller		= String.format("%" + 781 + "s", "");
	String spaceFiller210	= String.format("%" + 210 + "s", "");
	String applDate			= "";
	//
	public DepsDDAUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	//
	public String makeHolidayString (String holiDays) {
		String holidayString	= "";
		String temp				= holiDays.substring(7);
		while (temp.length() > 0) {
		    holidayString	+= holiDays.substring(0, 4) + temp.substring(0, 4);
		    temp			= temp.substring(4, temp.length());
		}
		//System.out.println(holidayString);
		return holidayString;
	}
	//
	public AcctentrySelector ProcessPostings (Connection dbConn, DepsSelector depsSelector,
			AcctentrySelector acctentrySelector) {
		calledFrom				= moduleName;
		moduleName				= "ProcessPostings: ";
		this.acctentrySelector	= acctentrySelector;
		String selChexSource	= acctentrySelector.getChexSource();
		String defCurr			= acctentrySelector.getDefCurr();
		String applDate			= "";
		String chexDate			= "";
		String curKey			= "";
		String prevKey			= "";
		String chexPayeeAcct	= "";
		String postAmountStr	= "";
		String chexPayerAba		= "";
		//String chexBundleId		= "";
		String chexAmount		= "";
		double chexAmountD		= 0.00;
		//String chexSource		= "RP";
		String cPayerType		= "";
		String pPayerType		= "";
		int    checkCount		= 0;
		int    depsOsn			= 0;
		int    bundleId			= 0;
		int    totalCount		= 0;
		int	   numTiers			= 0;
		int    localSeq			= 0;
		String formattedLocalSeq= "";
		String formattedOsn		= "";
		String formattedSeq		= "";
		String valueDate[]		= {"", "", "", "", ""};
		double postAmount[]		= {0.00, 0.00, 0.00, 0.00, 0.00};
		double postingAmount	= 0.00;
		double offsetAmount		= 0.00;
		String curBizDate		= escl.getApplDate();
		String curBizDateYYMMDD	= curBizDate.substring(2);
		String nextBizDate		= escl.getNextBizDate();
		String nextBizDateYYMMDD= nextBizDate.substring(2);
		//String creditValueDate= "";
		String timeStamp		= "";
		String curAcct			= "";
		String preAcct			= "";
		String prevYearHolidays	= escl.getPrevYearHolidays();
		String currYearHolidays	= escl.getCurrYearHolidays();
		String nextYearHolidays	= escl.getNextYearHolidays();
		String prevHolidays		= makeHolidayString (prevYearHolidays);
		String currHolidays		= makeHolidayString (currYearHolidays);
		String nextHolidays		= "";
		if (!nextYearHolidays.equals("")) {
			nextHolidays		= makeHolidayString (nextYearHolidays);
		}
		String holidaysS		= prevHolidays + currHolidays + nextHolidays;
		//DecimalFormat df		= new DecimalFormat("###,###.00");
		java.util.Date today	= new java.util.Date();
		SimpleDateFormat sdf	= new SimpleDateFormat("yyMMdd_HHmmssSS");
		SimpleDateFormat sdfHHMM= new SimpleDateFormat("HHmm");
		DepsDetail detailRow	= new DepsDetail();
		depsRows				= depsSelector.getChexRowsArray();
		if (selChexSource.equals("RDC")) {
			depsOsn	= 3;
		} else {
			depsOsn	= 0;
		}
		//checkCount++;
		for (int i=0; i<depsRows.size(); i++) {
			detailRow		= depsRows.get(i);
			applDate		= detailRow.getChex_proc_date();
			chexDate		= detailRow.getChex_proc_date().substring(2);
			chexPayeeAcct	= detailRow.getChex_payeeacct();
			curAcct			= chexPayeeAcct;
			chexPayerAba	= detailRow.getChex_routing_transit();
			chexAmount		= detailRow.getChex_check_amount();
			chexAmountD		= Double.parseDouble(chexAmount);
			//chexSource		= detailRow.getChex_source();
			cPayerType		= acctentrySelector.getPayerValue(chexPayerAba);
			//PrintLog("Payer aba: "+chexPayerAba+" Payer type: "+cPayerType);
			if (cPayerType==null) {
				cPayerType	= "NORMAL";
			}
			curKey		= cPayerType + chexPayeeAcct;
			//PrintLog("Payer Type & Payee Account: "+curKey);
			//PrintLog("Payer Type for aba "+chexPayerAba+": "+cPayerType+
			//		 " Payee Acct: "+chexPayeeAcct +
			//		 " Amount: "+chexAmountD);
			if (i == 0) {
				postingRows.clear();
				postingAmount	= 0.00;
				offsetAmount	= 0.00;
				pPayerType	= cPayerType;
				prevKey		= curKey;
				preAcct		= curAcct;
				GetAvailData(dbConn, curAcct);
			}
			if (prevKey.equals(curKey) || prevKey.equals(curKey+defCurr) || 
				(prevKey+defCurr).equals(curKey)) {
				postingAmount	+= chexAmountD;
				offsetAmount	+= chexAmountD;
				checkCount++;
				totalCount++;
				bundleId		= Integer.parseInt(detailRow.getChex_bundleid().substring(6,10));
				//PrintLog("EQ Current Key: "+curKey+" bundleId: "+bundleId);
			} else {
				//PrintLog("pPayerType: "+pPayerType);
				if (pPayerType.equals("NEXTDAY")) {
					formattedOsn		= String.format("%04d", bundleId);
					if (selChexSource.equals("RDC")) {
						depsOsn	= 3;
					} else {
						depsOsn	= 0;
					}
					//depsOsn	= 0;
					formattedSeq		= String.format("%01d", depsOsn);
					//PrintLog("NEXTDAY: "+postSeq);
					if (postSeq.contains(formattedSeq+formattedOsn)) {
						//depsOsn	= 6;
						//formattedSeq		= String.format("%01d", depsOsn);
						//if (postSeq.contains(formattedSeq+formattedOsn)) {
							depsOsn	= 7;
							formattedSeq		= String.format("%01d", depsOsn);
							postSeq.add(formattedSeq+formattedOsn);
						//} else {
						//	postSeq.add(formattedSeq+formattedOsn);
						//}
					} else {
						postSeq.add(formattedSeq+formattedOsn);
					}
					//PrintLog("NextDay Item: "+formattedSeq+formattedOsn);
					localSeq++;
					formattedLocalSeq	= String.format("%06d", localSeq);
					postAmountStr		= String.format("%15.2f", postingAmount);
					AcctentryDetail acctentryDetail	= new AcctentryDetail();
					acctentryDetail.setPostingBusinessdate(chexDate);
					acctentryDetail.setPostingApplPrefix("RP");
					acctentryDetail.setPostingBranch("150");
					acctentryDetail.setPostingSeqNum(formattedSeq+formattedOsn);
					acctentryDetail.setPostingRecStatus("2");
					acctentryDetail.setPostingAmount(postAmountStr);
					//acctentryDetail.setPostingCurrency("USD");
					acctentryDetail.setPostingCurrency(defCurr);
					acctentryDetail.setPostingDebitAcct("150930161500USD");
					if (preAcct.indexOf("USD",0)>0) {
						acctentryDetail.setPostingCreditAcct(preAcct);
					} else {
						//acctentryDetail.setPostingCreditAcct(preAcct+"USD");
						acctentryDetail.setPostingCreditAcct(preAcct+defCurr);
					}
					acctentryDetail.setPostingDebitValueDate(chexDate);
					if (availSameDay.equals("Y")) {
						acctentryDetail.setPostingCreditValueDate(curBizDateYYMMDD);
					} else {
						acctentryDetail.setPostingCreditValueDate(nextBizDateYYMMDD);
					}
					if (selChexSource.equals("RDC")) {
						acctentryDetail.setPostingReasonCode("225");
					} else {
						acctentryDetail.setPostingReasonCode("250");
					}
					acctentryDetail.setPostingReqExeDate(chexDate);
					acctentryDetail.setPostingTranRefNum("RP"+applDate+formattedLocalSeq);
					acctentryDetail.setPostingRefRelTran("BATCH"+formattedOsn+
															String.format("%07d",checkCount));
					//PrintLog("OSN: "+formattedOsn+" BATCH"+formattedOsn+
					//		String.format("%07d",checkCount));
					if (selChexSource.equals("RDC")) {
						acctentryDetail.setPostingDetailsOfPayment(
								String.format("%1$-" + 140 + "s","REMOTE DEPOSIT"));
					} else {
						acctentryDetail.setPostingDetailsOfPayment(
								String.format("%1$-" + 140 + "s", "TOTAL CHECK DEPOSITS"));
					}
					//acctentryDetail.setPostingBBI(spaceFiller210);
					acctentryDetail.setPostingTotalDebits(String.format("%04d", checkCount));
					acctentryDetail.setPostingTotalCredits(String.format("%04d", checkCount));
					timeStamp	= sdf.format(today.getTime());
					//PrintLog("TimeStamp: "+timeStamp);
					acctentryDetail.setPostingTimeStamp(timeStamp.substring(0, 15));
					//acctentryDetail.setPostingBookingText(spaceFiller210);
					acctentryDetail.setPostingOriginalCurrency("USD");
					acctentryDetail.setPostingOriginalAmount(postAmountStr);
					//acctentryDetail.setPostingCheckNum("");
					//acctentryDetail.setPostingBeneficiary("");
					//acctentryDetail.setPostingOBKName("");
					//acctentryDetail.setPostingOBKAddress("");
					//acctentryDetail.setPostingLocalKey("");
					//acctentryDetail.setPostingFiller(spaceFiller);
					//acctentryDetail.PrintDetails();
					postingRows.add(acctentryDetail);
				} else {
					//PrintLog("Amount: "+postingAmount);
					postAmount[0]	= 0.00;
					postAmount[1]	= 0.00;
					postAmount[2]	= 0.00;
					postAmount[3]	= 0.00;
					postAmount[4]	= 0.00;
					valueDate[0]	= "";
					valueDate[1]	= "";
					valueDate[2]	= "";
					valueDate[3]	= "";
					valueDate[4]	= "";
					for (int tier=0; tier<5; tier++) {
						if (availDay[tier]>0) {
							if (availDay[tier]==1) {
								valueDate[tier]		= nextBizDateYYMMDD;
								if (availHigh[tier] < postingAmount) {
									postAmount[tier]	= availHigh[tier];
									postingAmount		-= availHigh[tier];
									//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
								} else {
									postAmount[tier]	= postingAmount;
									numTiers	= tier + 1;
									//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
									break;
								}
							} else {
								if (availHigh[tier] < postingAmount) {
									postAmount[tier]= availHigh[tier];
									postingAmount	-= availHigh[tier];
									valueDate[tier]	= ValiDate.addDays(curBizDate, availDay[tier]);
									while (holidaysS.indexOf(valueDate[tier],0)>=0) {
										valueDate[tier]	= ValiDate.addDays(valueDate[tier], 1);
									}
									valueDate[tier]	= valueDate[tier].substring(2);
									//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
								} else {
									postAmount[tier]	= postingAmount;
									numTiers			= tier + 1;
									valueDate[tier]		= ValiDate.addDays(curBizDate, availDay[tier]);
									while (holidaysS.indexOf(valueDate[tier],0)>=0) {
										valueDate[tier]	= ValiDate.addDays(valueDate[tier], 1);
									}
									valueDate[tier]	= valueDate[tier].substring(2);
									//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
									break;
								}
							}
						} else {
							break;
						}
					}
					for (int tier=0; tier<numTiers; tier++) {
						depsOsn++;
						formattedSeq		= String.format("%01d", depsOsn);
						//
						formattedOsn		= String.format("%04d", bundleId);
						//
						localSeq++;
						formattedLocalSeq	= String.format("%06d", localSeq);
						AcctentryDetail acctentryDetail	= new AcctentryDetail();
						postAmountStr		= String.format("%15.2f", postAmount[tier]);
						acctentryDetail.setPostingBusinessdate(chexDate);
						acctentryDetail.setPostingApplPrefix("RP");
						acctentryDetail.setPostingBranch("150");
						acctentryDetail.setPostingSeqNum(formattedSeq+formattedOsn);
						acctentryDetail.setPostingRecStatus("2");
						acctentryDetail.setPostingAmount(postAmountStr);
						//acctentryDetail.setPostingCurrency("USD");
						acctentryDetail.setPostingCurrency(defCurr);
						//acctentryDetail.setPostingDebitAcct("150930161500USD");
						acctentryDetail.setPostingDebitAcct("150930161500USD");
						if (preAcct.indexOf("USD",0)>0) {
							acctentryDetail.setPostingCreditAcct(preAcct);
						} else {
							//acctentryDetail.setPostingCreditAcct(preAcct+"USD");
							acctentryDetail.setPostingCreditAcct(preAcct+defCurr);
						}
						acctentryDetail.setPostingDebitValueDate(chexDate);
						acctentryDetail.setPostingCreditValueDate(valueDate[tier]);
						if (selChexSource.equals("RDC")) {
							acctentryDetail.setPostingReasonCode("225");
						} else {
							acctentryDetail.setPostingReasonCode("250");
						}
						acctentryDetail.setPostingReqExeDate(chexDate);
						acctentryDetail.setPostingTranRefNum("RP"+applDate+formattedLocalSeq);
						acctentryDetail.setPostingRefRelTran("BATCH"+formattedOsn+
															 String.format("%07d",checkCount));
						//PrintLog("OSN: "+formattedOsn+" BATCH"+formattedOsn+
						//		String.format("%07d",checkCount));
						if (selChexSource.equals("RDC")) {
							acctentryDetail.setPostingDetailsOfPayment(
									String.format("%1$-" + 140 + "s","REMOTE DEPOSIT"));
						} else {
							acctentryDetail.setPostingDetailsOfPayment(
									String.format("%1$-" + 140 + "s", "TOTAL CHECK DEPOSITS"));
						}
						//acctentryDetail.setPostingBBI(spaceFiller210);
						acctentryDetail.setPostingTotalDebits(String.format("%04d", checkCount));
						acctentryDetail.setPostingTotalCredits(String.format("%04d", checkCount));
						timeStamp	= sdf.format(today.getTime());
						//PrintLog("TimeStamp: "+timeStamp);
						acctentryDetail.setPostingTimeStamp(timeStamp.substring(0, 15));
						//acctentryDetail.setPostingBookingText(spaceFiller210);
						acctentryDetail.setPostingOriginalCurrency("USD");
						acctentryDetail.setPostingOriginalAmount(postAmountStr);
						//acctentryDetail.setPostingCheckNum("");
						//acctentryDetail.setPostingBeneficiary("");
						//acctentryDetail.setPostingOBKName("");
						//acctentryDetail.setPostingOBKAddress("");
						//acctentryDetail.setPostingLocalKey("");
						//acctentryDetail.setPostingFiller(spaceFiller);
						//acctentryDetail.PrintDetails();
						postingRows.add(acctentryDetail);
					}
				}
				if (selChexSource.equals("RDC")) {
					depsOsn	= 3;
				} else {
					depsOsn	= 0;
				}
				//depsOsn			= 0;
				pPayerType		= cPayerType;
				prevKey			= curKey;
				preAcct			= curAcct;
				checkCount		= 1;
				postingAmount	= chexAmountD;
				offsetAmount	+= chexAmountD;
				totalCount++;
				GetAvailData(dbConn, curAcct);
				bundleId		= Integer.parseInt(detailRow.getChex_bundleid().substring(6,10));
				//PrintLog("BR Current Key: "+curKey+" bundleId: "+bundleId);
			}
		}
		//totalCount++;
		//
		// Last Set
		//
		if (pPayerType.equals("NEXTDAY")) {
			formattedOsn		= String.format("%04d", bundleId);
			if (selChexSource.equals("RDC")) {
				depsOsn	= 3;
			} else {
				depsOsn	= 0;
			}
			//depsOsn	= 0;
			formattedSeq		= String.format("%01d", depsOsn);
			//PrintLog("NEXTDAY: "+postSeq);
			if (postSeq.contains(formattedSeq+formattedOsn)) {
				//depsOsn	= 6;
				//formattedSeq		= String.format("%01d", depsOsn);
				//if (postSeq.contains(formattedSeq+formattedOsn)) {
					depsOsn	= 7;
					formattedSeq		= String.format("%01d", depsOsn);
					postSeq.add(formattedSeq+formattedOsn);
				//} else {
				//	postSeq.add(formattedSeq+formattedOsn);
				//}
			} else {
				postSeq.add(formattedSeq+formattedOsn);
			}
			//PrintLog("NextDay Item: "+formattedSeq+formattedOsn);
			//depsOsn++;
			//formattedSeq		= String.format("%01d", depsOsn);
			//
			//formattedOsn		= String.format("%04d", bundleId);
			//
			localSeq++;
			formattedLocalSeq	= String.format("%06d", localSeq);
			postAmountStr		= String.format("%15.2f", postingAmount);
			AcctentryDetail acctentryDetail	= new AcctentryDetail();
			acctentryDetail.setPostingBusinessdate(chexDate);
			acctentryDetail.setPostingApplPrefix("RP");
			acctentryDetail.setPostingBranch("150");
			acctentryDetail.setPostingSeqNum(formattedSeq+formattedOsn);
			acctentryDetail.setPostingRecStatus("2");
			acctentryDetail.setPostingAmount(postAmountStr);
			//acctentryDetail.setPostingCurrency("USD");
			acctentryDetail.setPostingCurrency(defCurr);
			acctentryDetail.setPostingDebitAcct("150930161500USD");
			if (preAcct.indexOf("USD",0)>0) {
				acctentryDetail.setPostingCreditAcct(preAcct);
			} else {
				//acctentryDetail.setPostingCreditAcct(preAcct+"USD");
				acctentryDetail.setPostingCreditAcct(preAcct+defCurr);
			}
			acctentryDetail.setPostingDebitValueDate(chexDate);
			if (availSameDay.equals("Y")) {
				acctentryDetail.setPostingCreditValueDate(curBizDateYYMMDD);
			} else {
				acctentryDetail.setPostingCreditValueDate(nextBizDateYYMMDD);
			}
			if (selChexSource.equals("RDC")) {
				acctentryDetail.setPostingReasonCode("225");
			} else {
				acctentryDetail.setPostingReasonCode("250");
			}
			acctentryDetail.setPostingReqExeDate(chexDate);
			acctentryDetail.setPostingTranRefNum("RP"+applDate+formattedLocalSeq);
			acctentryDetail.setPostingRefRelTran("BATCH"+formattedOsn+
												 String.format("%07d",checkCount));
			//PrintLog("OSN: "+formattedOsn+" BATCH"+formattedOsn+
			//		String.format("%07d",checkCount));
			if (selChexSource.equals("RDC")) {
				acctentryDetail.setPostingDetailsOfPayment(
						String.format("%1$-" + 140 + "s","REMOTE DEPOSIT"));
			} else {
				acctentryDetail.setPostingDetailsOfPayment(
						String.format("%1$-" + 140 + "s", "TOTAL CHECK DEPOSITS"));
			}
			//acctentryDetail.setPostingBBI(spaceFiller210);
			acctentryDetail.setPostingTotalDebits(String.format("%04d", checkCount));
			acctentryDetail.setPostingTotalCredits(String.format("%04d", checkCount));
			timeStamp	= sdf.format(today.getTime());
			//PrintLog("TimeStamp: "+timeStamp);
			acctentryDetail.setPostingTimeStamp(timeStamp.substring(0, 15));
			//acctentryDetail.setPostingBookingText(spaceFiller210);
			//acctentryDetail.setPostingOriginalCurrency("USD");
			acctentryDetail.setPostingOriginalCurrency(defCurr);
			acctentryDetail.setPostingOriginalAmount(postAmountStr);
			//acctentryDetail.setPostingCheckNum("");
			//acctentryDetail.setPostingBeneficiary("");
			//acctentryDetail.setPostingOBKName("");
			//acctentryDetail.setPostingOBKAddress("");
			//acctentryDetail.setPostingLocalKey("");
			//acctentryDetail.setPostingFiller(spaceFiller);
			//acctentryDetail.PrintDetails();
			postingRows.add(acctentryDetail);
		} else {
			//PrintLog("Amount: "+postingAmount);
			postAmount[0]	= 0.00;
			postAmount[1]	= 0.00;
			postAmount[2]	= 0.00;
			postAmount[3]	= 0.00;
			postAmount[4]	= 0.00;
			valueDate[0]	= "";
			valueDate[1]	= "";
			valueDate[2]	= "";
			valueDate[3]	= "";
			valueDate[4]	= "";
			for (int tier=0; tier<5; tier++) {
				if (availDay[tier]>0) {
					if (availDay[tier]==1) {
						valueDate[tier]		= nextBizDateYYMMDD;
						if (availHigh[tier] < postingAmount) {
							postAmount[tier]	= availHigh[tier];
							postingAmount		-= availHigh[tier];
							//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
						} else {
							postAmount[tier]	= postingAmount;
							numTiers			= tier + 1;
							//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
							break;
						}
					} else {
						if (availHigh[tier] < postingAmount) {
							postAmount[tier]	= availHigh[tier];
							postingAmount		-= availHigh[tier];
							valueDate[tier]		= ValiDate.addDays(curBizDate, availDay[tier]);
							while (holidaysS.indexOf(valueDate[tier],0)>=0) {
								valueDate[tier]	= ValiDate.addDays(valueDate[tier], 1);
							}
							valueDate[tier]	= valueDate[tier].substring(2);
							//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
						} else {
							postAmount[tier]= postingAmount;
							numTiers		= tier + 1;
							valueDate[tier]	= ValiDate.addDays(curBizDate, availDay[tier]);
							while (holidaysS.indexOf(valueDate[tier],0)>=0) {
								valueDate[tier]	= ValiDate.addDays(valueDate[tier], 1);
							}
							valueDate[tier]	= valueDate[tier].substring(2);
							//PrintLog("PostAmount["+tier+"]: "+postAmount[tier]);
							break;
						}
					}
				} else {
					break;
				}
			}
			for (int tier=0; tier<numTiers; tier++) {
				depsOsn++;
				formattedSeq		= String.format("%01d", depsOsn);
				//
				formattedOsn		= String.format("%04d", bundleId);
				//
				localSeq++;
				formattedLocalSeq	= String.format("%06d", localSeq);
				AcctentryDetail acctentryDetail	= new AcctentryDetail();
				postAmountStr		= String.format("%15.2f", postAmount[tier]);
				acctentryDetail.setPostingBusinessdate(chexDate);
				acctentryDetail.setPostingApplPrefix("RP");
				acctentryDetail.setPostingBranch("150");
				acctentryDetail.setPostingSeqNum(formattedSeq+formattedOsn);
				acctentryDetail.setPostingRecStatus("2");
				acctentryDetail.setPostingAmount(postAmountStr);
				//acctentryDetail.setPostingCurrency("USD");
				acctentryDetail.setPostingCurrency(defCurr);
				acctentryDetail.setPostingDebitAcct("150930161500USD");
				if (preAcct.indexOf("USD",0)>0) {
					acctentryDetail.setPostingCreditAcct(preAcct);
				} else {
					//acctentryDetail.setPostingCreditAcct(preAcct+"USD");
					acctentryDetail.setPostingCreditAcct(preAcct+defCurr);
				}
				acctentryDetail.setPostingDebitValueDate(chexDate);
				acctentryDetail.setPostingCreditValueDate(valueDate[tier]);
				if (selChexSource.equals("RDC")) {
					acctentryDetail.setPostingReasonCode("225");
				} else {
					acctentryDetail.setPostingReasonCode("250");
				}
				acctentryDetail.setPostingReqExeDate(chexDate);
				acctentryDetail.setPostingTranRefNum("RP"+applDate+formattedLocalSeq);
				acctentryDetail.setPostingRefRelTran("BATCH"+formattedOsn+
														String.format("%07d",checkCount));
				//PrintLog("OSN: "+formattedOsn+" BATCH"+formattedOsn+
				//		String.format("%07d",checkCount));
				if (selChexSource.equals("RDC")) {
					acctentryDetail.setPostingDetailsOfPayment(
							String.format("%1$-" + 140 + "s","REMOTE DEPOSIT"));
				} else {
					acctentryDetail.setPostingDetailsOfPayment(
							String.format("%1$-" + 140 + "s", "TOTAL CHECK DEPOSITS"));
				}
				//acctentryDetail.setPostingBBI(spaceFiller210);
				acctentryDetail.setPostingTotalDebits(String.format("%04d", checkCount));
				acctentryDetail.setPostingTotalCredits(String.format("%04d", checkCount));
				timeStamp	= sdf.format(today.getTime());
				//PrintLog("TimeStamp: "+timeStamp);
				acctentryDetail.setPostingTimeStamp(timeStamp.substring(0, 15));
				//acctentryDetail.setPostingBookingText(spaceFiller210);
				acctentryDetail.setPostingOriginalCurrency("USD");
				acctentryDetail.setPostingOriginalAmount(postAmountStr);
				//acctentryDetail.setPostingCheckNum("");
				//acctentryDetail.setPostingBeneficiary("");
				//acctentryDetail.setPostingOBKName("");
				//acctentryDetail.setPostingOBKAddress("");
				//acctentryDetail.setPostingLocalKey("");
				//acctentryDetail.setPostingFiller(spaceFiller);
				//acctentryDetail.PrintDetails();
				postingRows.add(acctentryDetail);
			}
			if (selChexSource.equals("RDC")) {
				depsOsn	= 3;
			} else {
				depsOsn	= 0;
			}
			//depsOsn	= 0;
		}
		//
		// Offset Entry
		//
		//depsOsn++;
		//formattedOsn = String.format("%05d", depsOsn);
		formattedOsn		= String.format("%05d", bundleId);
		AcctentryDetail acctentryDetail	= new AcctentryDetail();
		postAmountStr		= String.format("%15.2f", offsetAmount);
		acctentryDetail.setPostingBusinessdate(chexDate);
		acctentryDetail.setPostingApplPrefix("RP");
		acctentryDetail.setPostingBranch("150");
		timeStamp	= sdfHHMM.format(today.getTime());
		if (selChexSource.equals("RDC")) {
			acctentryDetail.setPostingSeqNum("8"+timeStamp);
		} else {
			acctentryDetail.setPostingSeqNum("9"+timeStamp);
		}
		acctentryDetail.setPostingRecStatus("2");
		acctentryDetail.setPostingAmount(postAmountStr);
		//acctentryDetail.setPostingCurrency("USD");
		acctentryDetail.setPostingCurrency(defCurr);
		acctentryDetail.setPostingDebitAcct("150120059100USD");
		acctentryDetail.setPostingCreditAcct("150930161500USD");
		acctentryDetail.setPostingDebitValueDate(nextBizDateYYMMDD);
		acctentryDetail.setPostingCreditValueDate(chexDate);
		if (selChexSource.equals("RDC")) {
			acctentryDetail.setPostingReasonCode("225");
		} else {
			acctentryDetail.setPostingReasonCode("250");
		}
		acctentryDetail.setPostingReqExeDate(chexDate);
		acctentryDetail.setPostingTranRefNum("RP"+applDate+"009999");
		acctentryDetail.setPostingRefRelTran("BATCH9999" + String.format("%07d",totalCount));
		//PrintLog("OSN: "+formattedOsn+" BATCH9999" + String.format("%07d",totalCount));
		if (selChexSource.equals("RDC")) {
			acctentryDetail.setPostingDetailsOfPayment(
					String.format("%1$-" + 140 + "s","REMOTE DEPOSIT"));
		} else {
			acctentryDetail.setPostingDetailsOfPayment(
					String.format("%1$-" + 140 + "s", "TOTAL CHECK DEPOSITS"));
		}
		//acctentryDetail.setPostingBBI(spaceFiller210);
		acctentryDetail.setPostingTotalDebits(String.format("%04d", totalCount));
		acctentryDetail.setPostingTotalCredits(String.format("%04d", totalCount));
		timeStamp	= sdf.format(today.getTime());
		//PrintLog("TimeStamp: "+timeStamp);
		acctentryDetail.setPostingTimeStamp(timeStamp.substring(0, 15));
		//acctentryDetail.setPostingBookingText(spaceFiller210);
		//acctentryDetail.setPostingOriginalCurrency("USD");
		acctentryDetail.setPostingOriginalCurrency(defCurr);
		acctentryDetail.setPostingOriginalAmount(postAmountStr);
		//acctentryDetail.setPostingCheckNum("");
		//acctentryDetail.setPostingBeneficiary("");
		//acctentryDetail.setPostingOBKName("");
		//acctentryDetail.setPostingOBKAddress("");
		//acctentryDetail.setPostingLocalKey("");
		//acctentryDetail.setPostingFiller(spaceFiller);
		//acctentryDetail.PrintDetails();
		postingRows.add(acctentryDetail);
		PrintLog("PostingRows count: " + postingRows.size());
		acctentrySelector.setPostingRows(postingRows);
		acctentrySelector.setDetail_count(postingRows.size());
		acctentrySelector.setDetailAmount(postAmountStr+"");
		acctentrySelector.InitRowSEC();
		moduleName	= calledFrom;
		return acctentrySelector;
	}
	//
	public void GetAvailData (Connection dbConn, String payeeAcct) {
		//try {
			if (CheckForAccount (dbConn, payeeAcct)==true) { 
				//PrintLog("Payee Account: "+ payeeAcct);
				availSameDay	= acctDetail.getAccount_extra6();
				availDay[0]		= Integer.parseInt(acctDetail.getAccount_availday1());
				availDay[1]		= Integer.parseInt(acctDetail.getAccount_availday2());
				availDay[2]		= Integer.parseInt(acctDetail.getAccount_availday3());
				availDay[3]		= Integer.parseInt(acctDetail.getAccount_availday4());
				availDay[4]		= Integer.parseInt(acctDetail.getAccount_availday5());
				availLow[0]		= Double.parseDouble(acctDetail.getAccount_availlow1());
				availLow[1]		= Double.parseDouble(acctDetail.getAccount_availlow2());
				availLow[2]		= Double.parseDouble(acctDetail.getAccount_availlow3());
				availLow[3]		= Double.parseDouble(acctDetail.getAccount_availlow4());
				availLow[4]		= Double.parseDouble(acctDetail.getAccount_availlow5());
				availHigh[0]	= Double.parseDouble(acctDetail.getAccount_availhigh1());
				availHigh[1]	= Double.parseDouble(acctDetail.getAccount_availhigh2());
				availHigh[2]	= Double.parseDouble(acctDetail.getAccount_availhigh3());
				availHigh[3]	= Double.parseDouble(acctDetail.getAccount_availhigh4());
				availHigh[4]	= Double.parseDouble(acctDetail.getAccount_availhigh5());
				/*
				if (payeeAcct.equals("150108020900USD")) {
					for (int idx=0; idx<5; idx++) {
						PrintLog(idx+"\tDay "+availDay[idx]+"\tLow "+availLow[idx]+"\tHigh "+availHigh[idx]);
					}
				}
				*/
			} else {
				availSameDay	= "N";
				availDay[0]		= 1;
				availLow[0]		= 0.01;
				availHigh[0]	= 5000.00;
				availDay[1]		= 5;
				availLow[1]		= 5000.01;
				availHigh[1]	= 99999999.99;
				availDay[2]		= 0;
				availLow[2]		= 0.00;
				availHigh[2]	= 0.00;
				availDay[3]		= 0;
				availLow[3]		= 0.00;
				availHigh[3]	= 0.00;
				availDay[4]		= 0;
				availLow[4]		= 0.00;
				availHigh[4]	= 0.00;
			}
		//} catch (Throwable t) {
		//	PrintLog ("problem checking Account"+t);
		//	t.printStackTrace();
		//}
	}
	//
	public boolean CheckForAccount (Connection dbConn, String payeeAcct) {
		acctDetail	= null;
		try {
			if (acUtil.AccountExists(dbConn, payeeAcct)==true) { 
				acctDetail	= acctentrySelector.getAcctRow(payeeAcct);
				if (acctDetail==null) {
					acctDetail	= acctentrySelector.getAcctRow(payeeAcct+"USD");
				}
			}
			if (acctDetail!=null) {
				return (true);
			}
		} catch (Throwable t) {
			PrintLog ("problem checking Account"+t);
			t.printStackTrace();
		}
		return (false);
	}
	//
	public void CreatePostingFile(Connection dbConn, DepsSelector depsSelector,
									AcctentrySelector acctentrySelector) {
		moduleName	= "CreatePostingFile: ";
		PrintLog("Creating Posting File");
		//
		//DecimalFormat df	= new DecimalFormat("###,##0.00");
		DecimalFormat dfL	= new DecimalFormat("##,###,##0.00");
		int repPadSize		= 24;
		int repPadSize2		= 42;
		String reportHdg	= escl.getReportHdg();
		String repPad		= "";
		String repPad2		= "";
		String chexSource	= acctentrySelector.getChexSource();
		String dataDir		= acctentrySelector.getDataDir() + "/" + chexSource.toLowerCase() + "/";
		String reportDir	= acctentrySelector.getReportDir();
		String postingFile	= "/" + chexSource.toLowerCase() + "_" + "ddaposting";
		String reportName	= "/" + chexSource.toLowerCase() + "_" + "ddaposting.rpt";
		String temp			= "";
		String tempAmt		= "";
		applDate			= escl.getApplDate();
		String dbTable		= "deps_chex";
		String dbLogTable	= "deps_chexlog_"+applDate.substring(0, 6);
		//
		repPad	= GenUtil.pad(repPad, repPadSize, " ");
		repPad2	= GenUtil.pad(repPad, repPadSize2, " ");
		FileOutput writeArec	= new FileOutput(dataDir + postingFile+".temp");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(applDate);
		temp	= ("REPORT NAME:" + reportName + repPad + reportHdg + repPad2 + "PAGE:");
		eRep.setHeadings(temp);
		temp	= ("                                                     " +
					"eController OUTCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                         " +
					" DEPOSITS DDA POSTING EXTRACT REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("BUSINESS-DATE APPL-PREFIX  BRANCH SEQ   REC-ST POST-AMOUN" + 
				   "T CURRENCY DR-ACCOUNT      CR-ACCOUNT      DR-VAL-DATE CR" +
				   "-VAL-DATE REASON");
		eRep.setHeadings(temp);
		temp	= ("REQ-EXP-DATE TRAN-REF-NUM     REF-REL-NUM        DETAILS-" +
				   "OF-PAYMENT    SUM-DEBITS    SUM-CREDITS ORIG-CUR  ORIG-PO" +
				   "ST-AMT");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		String	postingBusinessDate		= "";
		String	postingApplPrefix		= "";			// 2 RP
		String	postingBranch			= "150";		// 3
		String	postingSeqNum			= "";			// 5
		String	postingRecStatus		= "2";			// 1
		String	postingAmount			= "";			// 15
		String	postingCurrency			= "USD";		// 3
		String	postingDebitAcct		= "150930161500USD";	// 15
		String	postingCreditAcct		= "";			// 15 
		String	postingDebitValueDate	= "";			// 6  Date When Batch was scanned RUN DATE
		String	postingCreditValueDate	= "";			// 6  Based on availability Schedule
		String	postingReasonCode		= "250";		// 3  Check Deposit LBOX=250 RDC=225
		String	postingReqExeDate		= "";			// 6  postingBusinessdate
		String	postingTranRefNum		= "";			// 16 "RP"+"YYYYMMDD" + "nnnnnnn"
		String	postingRefRelTran		= "";			// 16 "BATCH"+"nnnnn" + "nnnnnnn"
		String	postingDetailsOfPayment	= "";			// LBOX="TOTAL CHECK DEPOSITS" RDC="REMOTE DEPOSIT"
		//String	postingBBI				= "";			// 210 spaces
		String	postingTotalDebits		= "";			// 4
		String	postingTotalCredits		= "";			// 4
		String	postingTimeStamp		= "";			// 15  YYMMDD_HHMMSSCC
		postingRows	= acctentrySelector.getPostingRowsArray();
		String postingRec	= "";
		AcctentryDetail acctentryDetail	= new AcctentryDetail();
		for (int i=0; i<postingRows.size(); i++) {
			acctentryDetail			= postingRows.get(i);
			postingBusinessDate		= acctentryDetail.getPostingBusinessdate();
			postingApplPrefix		= acctentryDetail.getPostingApplPrefix();
			postingBranch			= acctentryDetail.getPostingBranch();
			postingSeqNum			= acctentryDetail.getPostingSeqNum();
			postingRecStatus		= acctentryDetail.getPostingRecStatus();
			postingAmount			= acctentryDetail.getPostingAmount();
			postingCurrency			= acctentryDetail.getPostingCurrency();
			postingDebitAcct		= acctentryDetail.getPostingDebitAcct();
			postingCreditAcct		= acctentryDetail.getPostingCreditAcct();
			postingDebitValueDate	= acctentryDetail.getPostingDebitValueDate();
			postingCreditValueDate	= acctentryDetail.getPostingCreditValueDate();
			postingReasonCode		= acctentryDetail.getPostingReasonCode();
			postingReqExeDate		= acctentryDetail.getPostingReqExeDate();
			postingTranRefNum		= acctentryDetail.getPostingTranRefNum();
			postingRefRelTran		= acctentryDetail.getPostingRefRelTran();
			postingDetailsOfPayment	= acctentryDetail.getPostingDetailsOfPayment();
			postingTotalDebits		= acctentryDetail.getPostingTotalDebits();
			postingTotalCredits		= acctentryDetail.getPostingTotalCredits();
			postingTimeStamp		= acctentryDetail.getPostingTimeStamp();
			//PrintLog("TimeStamp: "+postingTimeStamp);
			postingRec	= (postingBusinessDate +
							postingApplPrefix +
							postingBranch +
							postingSeqNum +
							postingRecStatus +
							postingAmount +
							postingCurrency +
							postingDebitAcct +
							postingCreditAcct +
							postingDebitValueDate +
							postingCreditValueDate +
							postingReasonCode +
							postingReqExeDate +
							postingTranRefNum +
							postingRefRelTran +
							postingDetailsOfPayment +
							spaceFiller210 +
							postingTotalDebits +
							postingTotalCredits +
							postingTimeStamp.substring(0,15) +
							spaceFiller210 +
							postingCurrency +
							postingAmount +
							spaceFiller);
			tempAmt	= dfL.format(Double.parseDouble(postingAmount));
			temp	= (postingBusinessDate + "        RP           150    " + postingSeqNum+
					   " 2    " + 
					   String.format("%1$"+13+"s",tempAmt) + 
					   " USD      150930161500USD " +
						postingCreditAcct + " " + postingDebitValueDate + "      " +
						postingCreditValueDate + "      " + postingReasonCode);
			eRep.WriteDetail(temp);
			temp	= (postingReqExeDate + "       " + postingTranRefNum + " " +
						postingRefRelTran + "   " + 
						String.format("%1$-" + 20 + "s",postingDetailsOfPayment.trim()) + "  " + 
						postingTotalDebits + "          " + 
						postingTotalCredits + "        USD       " +
						String.format("%1$"+13+"s",tempAmt)); 
			eRep.WriteDetail(temp);
			PrintLog("Posting rec len 1: "+postingRec.length());
			// Write the posting record
			// write the report Line (String.format("%1$" + 13 + "s", amt
			//	160117        RP           150    00001 2    99,999,999.99 USD      150930161500USD 150930161500USD 160117      160117      250
			//	160117       RP20160117000001 BATCH0000010000011 TOTAL CHECK DEPOSITS  0001          0001        USD     99,999,999.99
			if (postingRec.length()<1500) {
				postingRec	= GenUtil.padRight(postingRec, 1500, " ");
				PrintLog("Posting rec len2: "+postingRec.length());
			} else {
				postingRec	= postingRec.substring(0, 1500);
			}
			writeArec.writeLine(postingRec);
			postingRec	= "";
		}
		// close the files
		writeArec.close();
		File postingFileName = new File(dataDir + postingFile + ".temp");
		postingFileName.renameTo(new File(dataDir + postingFile + ".dat"));
		eRep.WriteDetail("");
		eRep.WriteDetail("");
		eRep.WriteDetail("                                                       " +
						 "     *** END OF REPORT ***");
		eRep.Close();
		acctentrySelector.clearErrors();
		acctentrySelector.setErrorVec("DDA Posting", "result.data.extracted");
		//Here update the deps_chex to complete the items for which posting 
		//	data has been extracted.
		try {
			dcUtil.InsertUpdateDeps(dbConn, dbTable, dbLogTable, depsSelector);
		} catch (Throwable t) {
			PrintLog("Error Updating Posted Checks: "+t);
		}
	}
}