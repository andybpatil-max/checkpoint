<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/AccessAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="contentAccess.audit"/> <stripes:label for="audit"/>
	    <stripes:label for="inquiry"/>
	</h1>
<div id="detail">
	<table align="center" border="1" height="3" width="50%">
	    <tr>
		<th class="header" align=center height="19" colspan="3">
		    <stripes:label for="contentAccess.audit"/> <stripes:label for="audit"/>
		    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		</TH>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1"></TH>
		<th  class="header" align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="from"/></font></TH>
		<th  class="header" align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="to"/></font></TH>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="date"/></font></TH>
		<td>
		    <select size="1" name="accessSelector.fileDateFrom">
			<option>ALL</option>
			<c:forEach var="date" items="${accessSelector.dateList}">
			    <c:if test='${accessSelector.fileDateFrom!=date}'>
			        <option> <c:out value="${date}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.fileDateFrom==date}'>
				<option selected> <c:out value="${date}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="accessSelector.fileDateTo">
			<option>NONE</option>
			<c:forEach var="date" items="${accessSelector.dateList}">
			    <c:if test='${accessSelector.fileDateTo!=date}'>
			        <option> <c:out value="${date}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.fileDateTo==date}'>
				<option selected> <c:out value="${date}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3">
		    	<stripes:label for="contentAccess"/> <stripes:label for="user"/>
		    </font></TH>
		<td>
		    <select size="1" name="accessSelector.userFrom">
			<option>ALL</option>
			<c:forEach var="user" items="${accessSelector.userList}">
			    <c:if test='${accessSelector.userFrom!=user}'>
			        <option> <c:out value="${user}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.userFrom==user}'>
				<option selected> <c:out value="${user}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="accessSelector.userTo">
			<option>NONE</option>
			<c:forEach var="user" items="${accessSelector.userList}">
			    <c:if test='${accessSelector.userTo==user}'>
				<option selected> <c:out value="${user}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.userTo!=user}'>
			        <option> <c:out value="${user}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3">
		    	<stripes:label for="contentAccess"/> <stripes:label for="file"/>
		    </font></TH>
		<td>
		    <select size="1" name="accessSelector.fileNameFrom">
			<option>ALL</option>
			<c:forEach var="file" items="${accessSelector.fileList}">
			    <c:if test='${accessSelector.fileNameFrom!=file}'>
			        <option> <c:out value="${file}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.fileNameFrom==file}'>
				<option selected> <c:out value="${file}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="accessSelector.fileNameTo">
			<option>NONE</option>
			<c:forEach items="${accessSelector.fileList}" var="file">
			    <c:if test='${accessSelector.fileNameTo!=file}'>
				<option> <c:out value="${file}"/> </option>
			    </c:if>
			    <c:if test='${accessSelector.fileNameTo==file}'>
				<option selected> <c:out value="${file}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	</table>
</div>
<div id="selres">
	<c:if test='${accessSelector.actionFlag!=""}'>
	<table class="sortable" colspan='3' width='100%'>
	    <tr>
		<th>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<th>
		    <font size=2><stripes:label for="userName"/></font>
		</th>
		<th>
		    <font size=2><stripes:label for="fileName"/></font>
		</th>
		<th>
		    <font size=2><stripes:label for="file"/> <stripes:label for="date"/></font>
		</th>
		<th>
		    <font size=2>Access <stripes:label for="date"/></font>
		</th>
	    </tr>
	    <c:forEach var="axsDetail" items="${accessSelector.accessRows}">
	    <tr style="font-size:1.2em;">
		<td>
		    <c:out value="${axsDetail.accessFileBranch}"/>
		</td>
		<td>
		    <c:out value="${axsDetail.accessUser}"/>
		</td>
		<td>
		    <c:out value="${axsDetail.accessFileName}"/>
		</td>
		<td>
		    <c:out value="${axsDetail.accessFileDate}"/>
		</td>
    		<td>
     		    <c:out value="${axsDetail.accessModTime}"/>
		</td>
	    </tr>
	</c:forEach>
	</table>
	</c:if>
</div>
<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
    </center>


<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

