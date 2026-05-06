<%@ include file="taglibs.jsp" %>
<%-- standardlogin.jsp = (standard.jsp - footer.jsp) --%>
<stripes:layout-definition>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html>
	<head>
	    <html:base />
	    <title>eController-5.0 - ${title}</title>
	    <link rel="stylesheet" type="text/css" href="${ctx}/config/global-1.css"/>
	    <link rel="stylesheet" type="text/css" href="${ctx}/config/screen.css"/>
	    <script type="text/javascript" src="${ctx}/netpoint.js"></script>
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
        <body>
	    <div id="contentPanel">
		<stripes:layout-component name="header">
		    <jsp:include page="header.jsp"/>
		</stripes:layout-component>
		<center>
		    <div class="sectionTitle"><h1>${title}</h1></div>
		    <stripes:messages/>
		    <stripes:layout-component name="contents"/>
		</center>
		</div> <!-- end div main from header.jsp -->
	    </div> <!-- <div id="contentPanel">  -->
	</body>
    </html>
</stripes:layout-definition>
