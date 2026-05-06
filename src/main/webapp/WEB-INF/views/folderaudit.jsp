<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/Folder.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="folder"/> <stripes:label for="audit"/> <stripes:label for="inquiry"/></h1>
    <div id="detail">
    <table align="center" border="1" height="3" width="55%">
	<tr bgcolor=turquoise>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="folder"/> <stripes:label for="audit"/> 
		    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		</font>
	    </th>
	</tr>
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
			<font size="3">
			    <stripes:label for="folder"/> <stripes:label for="name"/>
			</font>
		</th>
		<td>
		    <select size="1" name="folderSelector.folderFrom">
			<c:if test='${folderSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${folderSelector.actionFlag!=""}'>
			    <option selected><c:out value="${folderSelector.folderFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${folderSelector.logFolderList}" var="folder">
			   <option> <c:out value="${folder}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="folderSelector.folderTo">
			<c:if test='${folderSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${folderSelector.actionFlag!=""}'>
			    <option selected><c:out value="${folderSelector.folderTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${folderSelector.logFolderList}" var="folder">
			   <option> <c:out value="${folder}"/> </option>
			</c:forEach>
		    </select>
		</td>
	<tr>
	</tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="subBranch"/></font></TH>

		<td align="center" width="10%" height="19">
		    <stripes:text name="folderSelector.folderSBr" value="${folderSelector.folderSBr}"
		    		  maxlength="15"/>
		</td>
<%--
		<td align="center" width="10%" height="19">
		    <stripes:text name="folderSelector.folderSBrTo" value="${folderSelector.folderSBrTo}"
		    		  maxlength="15"/>
		</td>
--%>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="application"/></font></TH>
		<td align="center" width="10%" height="19">
		    <select size="1" name="folderSelector.folderAppl">
			<c:if test='${folderSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${folderSelector.actionFlag!=""}'>
			    <option selected><c:out value="${folderSelector.folderAppl}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${folderSelector.applList}" var="appl">
			   <option> <c:out value="${appl}"/> </option>
			</c:forEach>
		    </select>
		</td>
<%--
		<td align="center" width="10%" height="19">
		    <stripes:text name="folderSelector.folderAppl" value="${folderSelector.folderAppl}"
		    		   maxlength="15"/>
		</td>
--%>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="fileName"/>
			</font>
		</th>
		<td align="center" width="10%" height="19">
		    <stripes:text name="folderSelector.folderFile" value="${folderSelector.folderFile}"
				maxlength="120"/>
		</td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3">
		    <stripes:label for="last"/> <stripes:label for="modified"/>
		</font>
	    </th>
	    <td>
		<select size="1" name="folderSelector.folderModD">
		    <option>ALL</option>
		    <c:forEach var="date" items="${folderSelector.logDatesList}">
			<c:if test='${folderSelector.folderModD==date}'>
			    <option selected><c:out value="${folderSelector.folderModD}"/></option>
			</c:if>
			<c:if test='${folderSelector.folderModD!=date}'>
			    <option> <c:out value="${date}"/> </option>
			</c:if>
		    </c:forEach>
	    	</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3">
		    <stripes:label for="modified"/> By <stripes:label for="user"/>
		</font>
	    </th>
	    <td>
		<select size="1" name="folderSelector.folderModU">
		    <option selected>ALL</option>
		    <c:forEach var="user" items="${folderSelector.logUsersList}">
			<c:if test='${folderSelector.folderModU==user}'>
			    <option selected> <c:out value="${user}"/> </option>
			</c:if>
			<c:if test='${folderSelector.folderModU!=user}'>
			    <option> <c:out value="${user}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	</tr>
    </table>
    </div>
	<stripes:errors/>
<c:if test='${folderSelector.actionFlag!=""}'>
      <p></p>
     <center>
	<stripes:submit name="ShowAudit" value="View Audit"/>
    </center>
<br/>
<div id="totals" align=center>
    <table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${folderSelector.rowCount}"/></b>
	    </td>
	</tr>
    </table>
</div>

<div id="selres">
<table class="sortable" colspan='9' width='90%' align='center' border='1'>
	<tr bgcolor=bluegreen>
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2>
		    <stripes:label for="folder"/> <stripes:label for="name"/>
		</font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="description"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="subBranch"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="application"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='3'>
		<font size=2><stripes:label for="fileName"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='3'>
		<font size=2><stripes:label for="file"/> <stripes:label for="description"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="modified"/> By <stripes:label for="function"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="modified"/> On </font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="modified"/> By <stripes:label for="user"/>/font>
	    </th>
<%--
	    <th align='center' width='4%' height=15 colspan=2>
		<font size='2'> Action</font>
	    </th>
--%>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="FolderDetail" varStatus="idx0">
<%--		   	end="${acctSelector.rowEnd-1}" begin="${acctSelector.rowStart}"> --%>
  <tr>
    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
	<c:out value="${idx0.index+1}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
	<c:out value="${FolderDetail.folderName}"/></b>
    </td>
    <td align="right" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${FolderDetail.folderDesc}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${FolderDetail.folderBr}"/>
    </td>
    <td align="right" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${FolderDetail.folderSBr}"/>
    </td>
    <td  align='center' bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${FolderDetail.folderAppl}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='3'>
      <c:out value="${FolderDetail.folderFile}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='3'>
      <c:out value="${FolderDetail.folderFileDesc}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
      <c:out value="${FolderDetail.folderModFunc}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
      <c:out value="${FolderDetail.folderModTime}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
      <c:out value="${FolderDetail.folderModUser}"/>
    </td>
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
		<c:out value="${folderSelector.rowCount}"/></b>
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
	<stripes:submit name="ShowAudit" value="View Audit"/>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
