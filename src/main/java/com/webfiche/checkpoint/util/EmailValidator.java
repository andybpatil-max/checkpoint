package com.webfiche.checkpoint.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public EmailValidator() {
	pattern = Pattern.compile(EMAIL_PATTERN);
    }
    /**
     * Validate hex with regular expression
     * 
     * @param eMail for validation
     * @return true valid eMail, false invalid eMail
     */
    public boolean validate(final String eMail) {
	matcher = pattern.matcher(eMail);
	return matcher.matches();
    }
    public static void main(String[] args)  throws Exception {
	EmailValidator emailValidator	= new EmailValidator();
	if (args.length == 0) {
	    System.out.println("Enter eMail to validate");
	    System.exit(0);
	}
	String eMail	= args[0];
	if (emailValidator.validate(eMail)) {
	    System.out.println("Email is valid : " + eMail);
	} else {
	    System.out.println("Email is invalid : " + eMail);
	}
    }
}