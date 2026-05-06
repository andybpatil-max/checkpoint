package com.webfiche.checkpoint.util;

//import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	/*
	 * ((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}) 
	 * Description ( # Start of group 
	 * (?=.*\d) # must contains one digit from 0-9 
	 * (?=.*[a-z]) # must contain one lowercase character 
	 * (?=.*[A-Z]) # must contains one uppercase characters 
	 * (?=.*[&_\\-%!#+=@~:;]) # must contains one special symbols in the list "&_\\-%!#+=@~:;" 
	 * . # match anything with previous condition checking
	 * {8,20} # length at least 8 characters and maximum of 20 ) # End of group
	 */
	private Pattern			 pattern;
	private Matcher			 matcher;
	//
	// private static final String PASSWORD_PATTERN =
	// "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[&_\\-%!#+=@~:;]).{8,20})";
    private static final String PASSWORD_PATTERN1 = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    private static final String PASSWORD_PATTERN2 = "((?=.*[&_\\-%!#+=@~:;])(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    private static final String PASSWORD_PATTERN3 = "((?=.*\\d)(?=.*[&_\\-%!#+=@~:;])(?=.*[A-Z]).{8,20})";
    private static final String PASSWORD_PATTERN4 = "((?=.*\\d)(?=.*[a-z])(?=.*[&_\\-%!#+=@~:;]).{8,20})";
	//
	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN1);
	}
	/**
	 * Validate password with regular expression
	 * 
	 * @param password password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean validate(final String password) {
		boolean pat1Match	= false;
		boolean pat2Match	= false;
		boolean pat3Match	= false;
		boolean pat4Match	= false;
		//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: "+password);
		pattern	= Pattern.compile(PASSWORD_PATTERN1);
		matcher	= pattern.matcher(password);
		if (matcher.matches()) {
			//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: P1");
			pat1Match	= true;
		    //return true;
		}
		pattern	= Pattern.compile(PASSWORD_PATTERN2);
		matcher	= pattern.matcher(password);
		if (matcher.matches()) {
			//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: P2");
			pat2Match	= true;
		    //return true;
		}
		pattern	= Pattern.compile(PASSWORD_PATTERN3);
		matcher	= pattern.matcher(password);
		if (matcher.matches()) {
			//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: P3");
			pat3Match	= true;
		    //return true;
		}
		pattern	= Pattern.compile(PASSWORD_PATTERN4);
		matcher	= pattern.matcher(password);
		if (matcher.matches()) {
			//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: P4");
			pat4Match	= true;
		    //return true;
		}
		if (pat1Match && pat2Match && pat3Match && pat4Match) {
			return true;
		}
		//System.out.println(Calendar.getInstance().getTime()+"> PasswordValidator: No Match");
		return false;
	}
	public static void main(String[] args) throws Exception {
		PasswordValidator passwordValidator = new PasswordValidator();
		if (args.length == 0) {
			System.out.println("Enter Password to validate");
			System.exit(0);
		}
		String password = args[0];
		if (passwordValidator.validate(password)) {
			//System.out.println("Password is valid : " + password);
		} else {
			//System.out.println("Password is invalid : " + password);
		}
	}
}
