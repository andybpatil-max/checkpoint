package com.webfiche.checkpoint.util;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;

public class XMLUtil
{
    private static String xmlTag	= "";
    private Vector <Vector<String>> tagValue		= new Vector <Vector<String>>();
    private Vector <String> tvPair		= new Vector <String>();
    private String moduleName			= "> XMLUtil.GetXMLFields: ";
    //
    public Vector <Vector <String>>GetXMLFields (String XMLString) {
    	try {
    		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    		//System.out.println (java.util.Calendar.getInstance().getTime()+
    		//			    moduleName+"Parsing XML Data: ");
    		//
    		// Parse the input
    		//
    		DefaultHandler dHandler	= null;;
    		SAXParser saxParser		= parserFactory.newSAXParser();
    		InputStream is		= new ByteArrayInputStream(XMLString.getBytes());
    		//
    		dHandler = new DefaultHandler() {
    			//boolean id;
    			//boolean name;
    			//boolean mail;
    			//int x	= 0;
    			String strVal	= "";		    
    			public void startElement(String uri, 
		    						 	String localName, 
		    						 	String element_name, 
		    						 	Attributes attributes) throws SAXException{
    				//
    				// This is the Start Tag
    				//
    				/*
					System.out.println("Start1: " + element_name.trim()+"\t");
    				 */
    			}
    			//
    			public void characters(char[] ch, int start, int len) throws SAXException{
    				String str = new String (ch, start, len);
    				if (!str.trim().equals("")) {
    					//System.out.println("VALUE "+str.trim());
    					strVal	= str;
    				} else {
    					//System.out.println(" ");
    				}
    			}
    			public void endElement(String uri, String localName, String qName) {
    				//
    				// Matching End Tag
    				//
    				xmlTag	= qName.trim();
    				if (!strVal.equals("")) {
    					tvPair.add(xmlTag);
    					tvPair.add(strVal);
    					tagValue.add(tvPair);
    					tvPair	= new Vector <String>();
    					strVal	= "";
    				}
    			}
    		};
    		saxParser.parse(is, dHandler);
    	} catch (Exception e){
    		System.out.println (java.util.Calendar.getInstance().getTime()+
	    			    		moduleName+"XML String has no elements");
    		e.printStackTrace();
    		tagValue.clear();
    	}
    	return (tagValue);
    }
}
