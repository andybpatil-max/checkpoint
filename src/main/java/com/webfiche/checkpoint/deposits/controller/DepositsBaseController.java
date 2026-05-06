package com.webfiche.checkpoint.deposits.controller;

import com.webfiche.checkpoint.controller.BaseController;

public abstract class DepositsBaseController extends BaseController {

    protected String getDbUsed()       { return appProperties.getDbUsed(); }
    protected String getApplDate()     { return appProperties.getApplDate(); }
    protected String getBankId()       { return appProperties.getBankId(); }
    protected String getDefCurr()      { return appProperties.getDefCurr(); }
    protected String getImageUrl()     { return appProperties.getImageUrl(); }
    protected String getImgBaseDir()   { return appProperties.getImgBaseDir(); }
    protected String getOurAba()       { return appProperties.getOurAba(); }
    protected String getLocOutputDir() { return appProperties.getLocOutputDir(); }
    protected String getAppSchema()    { return appProperties.getAppSchema(); }
    protected boolean isNotLoggedIn()  { return !userSession.isLoggedIn(); }
    protected String getUserId() {
        return userSession.getUser() != null ? userSession.getUser().getUserId() : "";
    }
}
