package com.webfiche.checkpoint.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String nodeName = "";
    private String applDate = "";
    private String smtpHost = "localhost";
    private int smtpPort = 25;
    private String smtpFrom = "noreply@localhost";
    private int minSizePw = 6;
    private int maxPastPw = 6;
    private int numTrysPw = 3;
    private String bankId = "";
    private String ourAba = "";
    private String defCurr = "USD";
    private String appSchema = "";
    private String dbUsed = "Oracle";
    private String ftpGateway = "";
    private int imagesPerPdf = 20;
    private int checksPerView = 10;
    private String imageUrl = "";
    private String dataDir = "/opt/checkpoint/data";
    private String imageDir = "/opt/checkpoint/images";
    private String urlBase = "http://localhost:8080/checkpoint";
    private String remBaseDir = "";
    private String locInputDir = "";
    private String locOutputDir = "";
    private String imgBaseDir = "";
    private String fromEmailAddress = "noreply@localhost";
    private String sysAlertEmail = "";
    private String debugFlag = "N";
    private String releaseVersion = "1.0";
    private String eodFlag = "N";
    private String bodFlag = "N";

    public String getNodeName() { return nodeName; }
    public void setNodeName(String nodeName) { this.nodeName = nodeName; }

    public String getApplDate() { return applDate; }
    public void setApplDate(String applDate) { this.applDate = applDate; }

    public String getSmtpHost() { return smtpHost; }
    public void setSmtpHost(String smtpHost) { this.smtpHost = smtpHost; }

    public int getSmtpPort() { return smtpPort; }
    public void setSmtpPort(int smtpPort) { this.smtpPort = smtpPort; }

    public String getSmtpFrom() { return smtpFrom; }
    public void setSmtpFrom(String smtpFrom) { this.smtpFrom = smtpFrom; }

    public int getMinSizePw() { return minSizePw; }
    public void setMinSizePw(int minSizePw) { this.minSizePw = minSizePw; }

    public int getMaxPastPw() { return maxPastPw; }
    public void setMaxPastPw(int maxPastPw) { this.maxPastPw = maxPastPw; }

    public int getNumTrysPw() { return numTrysPw; }
    public void setNumTrysPw(int numTrysPw) { this.numTrysPw = numTrysPw; }

    public String getBankId() { return bankId; }
    public void setBankId(String bankId) { this.bankId = bankId; }

    public String getOurAba() { return ourAba; }
    public void setOurAba(String ourAba) { this.ourAba = ourAba; }

    public String getDefCurr() { return defCurr; }
    public void setDefCurr(String defCurr) { this.defCurr = defCurr; }

    public String getAppSchema() { return appSchema; }
    public void setAppSchema(String appSchema) { this.appSchema = appSchema; }

    public String getDbUsed() { return dbUsed; }
    public void setDbUsed(String dbUsed) { this.dbUsed = dbUsed; }

    public String getFtpGateway() { return ftpGateway; }
    public void setFtpGateway(String ftpGateway) { this.ftpGateway = ftpGateway; }

    public int getImagesPerPdf() { return imagesPerPdf; }
    public void setImagesPerPdf(int imagesPerPdf) { this.imagesPerPdf = imagesPerPdf; }

    public int getChecksPerView() { return checksPerView; }
    public void setChecksPerView(int checksPerView) { this.checksPerView = checksPerView; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDataDir() { return dataDir; }
    public void setDataDir(String dataDir) { this.dataDir = dataDir; }

    public String getImageDir() { return imageDir; }
    public void setImageDir(String imageDir) { this.imageDir = imageDir; }

    public String getUrlBase() { return urlBase; }
    public void setUrlBase(String urlBase) { this.urlBase = urlBase; }

    public String getRemBaseDir() { return remBaseDir; }
    public void setRemBaseDir(String remBaseDir) { this.remBaseDir = remBaseDir; }

    public String getLocInputDir() { return locInputDir; }
    public void setLocInputDir(String locInputDir) { this.locInputDir = locInputDir; }

    public String getLocOutputDir() { return locOutputDir; }
    public void setLocOutputDir(String locOutputDir) { this.locOutputDir = locOutputDir; }

    public String getImgBaseDir() { return imgBaseDir; }
    public void setImgBaseDir(String imgBaseDir) { this.imgBaseDir = imgBaseDir; }

    public String getFromEmailAddress() { return fromEmailAddress; }
    public void setFromEmailAddress(String fromEmailAddress) { this.fromEmailAddress = fromEmailAddress; }

    public String getSysAlertEmail() { return sysAlertEmail; }
    public void setSysAlertEmail(String sysAlertEmail) { this.sysAlertEmail = sysAlertEmail; }

    public String getDebugFlag() { return debugFlag; }
    public void setDebugFlag(String debugFlag) { this.debugFlag = debugFlag; }

    public String getReleaseVersion() { return releaseVersion; }
    public void setReleaseVersion(String releaseVersion) { this.releaseVersion = releaseVersion; }

    public String getEodFlag() { return eodFlag; }
    public void setEodFlag(String eodFlag) { this.eodFlag = eodFlag; }

    public String getBodFlag() { return bodFlag; }
    public void setBodFlag(String bodFlag) { this.bodFlag = bodFlag; }
}
