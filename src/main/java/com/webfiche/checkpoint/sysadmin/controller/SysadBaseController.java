package com.webfiche.checkpoint.sysadmin.controller;

import com.webfiche.checkpoint.controller.BaseController;

public abstract class SysadBaseController extends BaseController {

    protected String getDbUsed() {
        return appProperties.getDbUsed();
    }

    protected boolean isNotLoggedIn() {
        return !userSession.isLoggedIn();
    }
}
