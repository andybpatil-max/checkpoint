package com.webfiche.checkpoint.util;

import java.io.Serializable;

public final class StringContainsUtil implements Serializable {
	private static final long serialVersionUID = 1L;
    public boolean StringContains (String sourceStr, String subStr) {
	if (sourceStr.startsWith(subStr)) {
	    return true;
	} else if (sourceStr.indexOf(subStr) > 0) {
	    return true;
	} else {
	    return false;
	}
    }
}
