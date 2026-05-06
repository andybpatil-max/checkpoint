<%@ include file="taglibs.jsp" %>

<stripes:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
    <html>
	<head>
	    <html:base />
	    <title>eController-5.0 ${title}</title>
	    <link rel="stylesheet" type="text/css" href="${ctx}/config/global-1.css"/>
	    <link rel="stylesheet" type="text/css" href="${ctx}/config/screen.css"/>
	    <script src="${ctx}/js/addanno.js"></script>
	    <script src="${ctx}/js/coloptions.js"></script>
	    <script src="${ctx}/js/sorttable.js"></script>
	    <script src="${ctx}/js/search.js"></script>
	    <script src="${ctx}/js/soptions.js"></script>
	    <script src="${ctx}/js/scroll-sneak.js"></script>
	    <script src="${ctx}/js/readCookie.js"></script>
	    <script src="${ctx}/js/scrollpos.js"></script>
	    <script src="${ctx}/js/redips-scroll.js"></script>
<%--	    <script src="${ctx}/js/jquery-ui-1.10.4.custom.js"></script> --%>
	    <stripes:layout-component name="html-head"/>
	    <!--[if !IE 7]>
		<style type="text/css">
		#wrapper {
			display:table;
			height:100%;
		}
		</style>
	    <![endif]-->
        </head>
<%--
        <body id="_theBody">
	<body onscroll="saveScrollPosition();" onload="doScroll()" onunload="window.name=document.body.scrollTop">
        <body class="sneak"> 
	<body onscroll="document.cookie='ypos=' + window.pageYOffset" onload="window.scrollTo(0,readCookie('ypos'))">
--%>
	<body>
	    <div id="contentPanel">
		<stripes:layout-component name="header">
		    <jsp:include page="headerrep.jsp"/>
		</stripes:layout-component>
		<center>
		    <stripes:layout-component name="contents"/>
		</center>
		</div> <!-- end div main from header.jsp -->
		<%@ include file="footer.jsp" %>
	    </div> <!-- <div id="contentPanel">  -->
	</body>
    </html>
</stripes:layout-definition>
