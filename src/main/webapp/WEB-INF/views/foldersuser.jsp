<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/FolderUsers.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <c:if test='${folderSelector.accessFlag=="inq"}'>
	<h1>
	    <stripes:label for="user"/> <stripes:label for="folders"/>
	    <stripes:label for="inquiry"/>
	</h1>
    </c:if>
    <c:if test='${folderSelector.accessFlag!="inq"}'>
	<h1>
	    <stripes:label for="user"/> <stripes:label for="folders"/>
	    <stripes:label for="maint"/>
	</h1>
    </c:if>
    <div id="detail">
    <table align="center" border="1" height="3" width="55%">
	<c:if test='${folderSelector.accessFlag=="inq"}'>
	    <tr bgcolor=turquoise>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			<stripes:label for="user"/> <stripes:label for="folders"/>
			<stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		    </font>
		</th>
	    </tr>
	</c:if>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <tr>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			<stripes:label for="user"/> <stripes:label for="folders"/>
			<stripes:label for="maint"/> <stripes:label for="selCriteria"/>
		    </font>
	    </tr>
	</c:if>
	<tr>
		<th align=center height="19" colspan="1"></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
	</tr>
	</tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="branch"/></font></TH>
		<td align="center" width="10%" height="19">
		    <select size="1" name="folderSelector.folderBr">
			<c:if test='${folderSelector.actionFlag==""}'>
			    <c:if test='${folderSelector.folderBr!=""}'>
				<option selected><c:out value="${folderSelector.folderBr}"/></option>
			    </c:if>
			    <c:if test='${folderSelector.folderBr==""}'>
				<option selected>ALL</option>
			    </c:if>
			</c:if>
			<c:if test='${folderSelector.actionFlag!=""}'>
			    <option selected><c:out value="${folderSelector.folderBr}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${folderSelector.entityList}" var="branch">
			   <option> <c:out value="${branch}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="userName"/></font></TH>
		<td>
		    <select size="1" name="folderSelector.userFrom">
			<option>ALL</option>
			<c:forEach items="${folderSelector.userList}" var="user">
			    <c:if test='${folderSelector.userFrom==user.key}'>
				<option selected><c:out value="${folderSelector.userFrom}"/></option>
			    </c:if>
			    <c:if test='${folderSelector.userFrom!=user.key}'>
				<option> <c:out value="${user.key}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="folderSelector.userTo">
			<option selected>NONE</option>
			<c:forEach items="${folderSelector.userList}" var="user">
			    <c:if test='${folderSelector.userTo==user.key}'>
				<option selected><c:out value="${folderSelector.userTo}"/></option>
			    </c:if>
			    <c:if test='${folderSelector.userTo!=user.key}'>
				<option> <c:out value="${user.key}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	<tr>
	<tr>
		<th align=center height="19" colspan="1">
		    <font size="3">
			<stripes:label for="folder"/> <stripes:label for="name"/>
		    </font>
		</th>
		<td>
		    <select size="1" name="folderSelector.folderFrom">
			<option>ALL</option>
			<c:forEach items="${folderSelector.folderList}" var="folder">
			    <c:if test='${folderSelector.folderFrom==folder.key}'>
				<option selected><c:out value="${folderSelector.folderFrom}"/></option>
			    </c:if>
			    <c:if test='${folderSelector.folderFrom!=folder.key}'>
				<option> <c:out value="${folder.key}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
<%--
		<td>
		    <select size="1" name="folderSelector.folderTo">
			<c:if test='${folderSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${folderSelector.actionFlag!=""}'>
			    <option selected><c:out value="${folderSelector.folderTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${folderSelector.folderList}" var="folder">
			   <option> <c:out value="${folder.key}"/> </option>
			</c:forEach>
		    </select>
		</td>
--%>
	<tr>
    </table>
    </div>
	<stripes:errors/>
<c:if test='${folderSelector.actionFlag!=""}'>
      <p></p>
     <center>
	<stripes:submit name="View" value="View"/>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <stripes:submit name="NewUser"  value="New User"/>
	</c:if>
    </center>
<br/>
<div id="totals" align=center>
    <table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowCount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${folderSelector.userRowCount}"/></b>
	    </td>
	</tr>
    </table>
</div>

<div id="selres">
<table class="sortable" colspan='11' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr>
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2><stripes:label for="userName"/></font>
	    </th>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="folder"/> <stripes:label for="name"/></font>
	    </th>
	    <c:if test='${folderSelector.accessFlag!="inq"}'>
		<th align='center' width='4%' height=15 colspan=2>
		    <font size='2'> Action</font>
		</th>
	    </c:if>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="fRows" varStatus="idx0">
    <tr>
	<td align="center" bgcolor='lightyellow' width='2%' height='19'>
	    <c:out value="${idx0.index+1}"/>
	</td>
	<td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
	    <c:out value="${fRows.folderUser}"/></b>
	</td>
	<td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
	    <c:out value="${fRows.folderBr}"/></b>
	</td>
	<td>
	    <table>
		<tr>
		    <c:forEach items="${fRows.foldersEntitled}" var="ufD" varStatus="idx1">
			<c:if test="${ufD!=''}">
			    <c:if test="${not idx1.first and idx1.index % 10 == 0}">
				</tr><tr>
			    </c:if>
			    <td><c:out value='${ufD}'/></td>
			</c:if>
	    	    </c:forEach>
		</tr>
	    </table>
	</td>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <td align="center" bgcolor='lightyellow' width='7%' height='19'>
		<stripes:link href="/econtroller/infopoint/actions/FolderUsers.action" event="Delete">
		    Delete
		    <stripes:param name="recIndex" value="${idx0.index}"/>
		    <stripes:param name="folderUser" value="${fRows.folderUser}"/>
		    <stripes:param name="folderBr" value="${fRows.folderBr}"/>
		</stripes:link>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='7%' height='19'>
		<stripes:link href="/econtroller/infopoint/actions/FolderUsers.action" event="Modify">
		    Modify
		    <stripes:param name="recIndex" value="${idx0.index}"/>
		    <stripes:param name="folderUser" value="${fRows.folderUser}"/>
		    <stripes:param name="folderBr" value="${fRows.folderBr}"/>
		</stripes:link>
	    </td>
	</c:if>
    </tr>
</c:forEach>
</table>
</div>

<div id="totals" align=center>
<table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${folderSelector.userRowCount}"/></b>
	    </td>
	</tr>
</table>
</div>
</c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value="${folderSelector.actionFlag}"/>							--%>

     <center>
<%--	<stripes:errors/> --%>
	<stripes:submit name="View" value="View"/>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <stripes:submit name="NewUser"  value="New User"/>
	</c:if>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
