<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/ChexReleaseEarmark.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<fmt:setLocale value="en_US"/>
	<h1><stripes:label for="releaseearmark"/></h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table class="sortable" colspan='2' width='60%' align='center' border="1" cellspacing="1" cellpadding="1">
	<tr>
	    <th class="header" height=19 colspan='3'>
		<font size="3">
		    <stripes:label for="release"/> <stripes:label for="earmark"/> <stripes:label for="files"/> 
		</font>
	    </th>
	</tr>
	<tr>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3">
		    <stripes:label for="file"/> <stripes:label for="name"/> 
		</font>
	    </TH>
	    <th class="header" align=center height="19" colspan="1">
		<font size=3>
		    <stripes:label for="action"/>
		</font>
	    </th>
	</tr>
	<c:if test="${earmarkSelector.fileCount>=0}">
	<c:forEach items="${earmarkSelector.relFile}" var="EarmarkDetail" varStatus="idx0">
	    <tr>
		<c:if test='${EarmarkDetail.file2Release!=""}'>
		    <td width='7%' height='19'>
		    	<font size="3">
			    <c:out value="${EarmarkDetail.file2Release}"/> 
			</font>
		    </td>
		    <td>
		    	<font size="3">
			<stripes:link href="/econtroller/inclear/actions/ChexReleaseEarmark.action" event="ReleaseFile">
			ReleaseFile
			    <stripes:param name="releaseFileName" value="${EarmarkDetail.file2Release}"/>
			</stripes:link>
			</font>
		    </td>
		</c:if>
	    <tr>
	</c:forEach>
	</c:if>
    </table>
    </div>
     <center>
	<hr>
	<stripes:errors />
    </center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
