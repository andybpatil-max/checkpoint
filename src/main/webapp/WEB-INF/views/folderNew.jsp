<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/Folder.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>

    <c:if test='${folderSelector.accessFlag=="inq"}'>
	<h1><stripes:label for="folder"/> <stripes:label for="inquiry"/></h1>
    </c:if>
    <c:if test='${folderSelector.accessFlag!="inq"}'>
	<h1><stripes:label for="folder"/> <stripes:label for="maint"/></h1>
    </c:if>

<div id="detail">
    <table colspan='2' width='70%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <th class="header" colspan="2">
		<font size=3 color=blue>
		    <stripes:label for="folder"/> <stripes:label for="maint"/>
		</font>
	    </th>
	</tr>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="folder"/> <stripes:label for="name"/></b>
	    </th>
	    <td align='left' width=10% height='19'>
		<c:if test='${folderSelector.actionFlag=="Folder"}'>
		    <stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32'/>
		</c:if>
		<c:if test='${folderSelector.actionFlag!="Folder"}'>
		    <stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32' readonly='true'/>
		</c:if>
	    </td>
	</tr>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="description"/></b>
	    </th>
	    <td align='left' width=10% height='19'>
		<c:if test='${folderSelector.actionFlag=="Folder"}'>
		    <stripes:text name='folderDetail.folderDesc' value='${folderDetail.folderDesc}'
			size='64' maxlength='64'/>
		</c:if>
		<c:if test='${folderSelector.actionFlag!="Folder"}'>
		    <stripes:text name='folderDetail.folderDesc' value='${folderDetail.folderDesc}'
			size='64' maxlength='64' readonly='true'/>
		</c:if>
	    </td>
	</tr>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="branch"/>
	    </th>
	    <td align='left' height='15'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Update"}'>
		    <stripes:text name='folderDetail.folderBr' size='3' readonly='true'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="Folder"}'>
		    <stripes:text name='folderDetail.folderBr' size='3'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderBr' size='3'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
<%--
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="subBranch"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderSBr' size='15'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderSBr' size='15'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderSBr' size='15' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="application"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderAppl' size='16'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderAppl' size='16'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderAppl' size='16' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="fileName"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="file"/> <stripes:label for="description"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderFileDesc' size='50' maxlength='100'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderFileDesc' size='50' maxlength='100'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderFileDesc' 
		    	size='50' maxlength='100' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="indexed"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderIndexed' size='2'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderIndexed' size='2'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderIndexed' size='2' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="overApp"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderOverApp' size='35'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderOverApp' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderOverApp' size='35' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
--%>
    </table>
</div>
<div id="selres">
<table class="sortable" colspan='9' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr bgcolor=bluegreen>
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="select"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="folder.subBranch"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="application"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='2'>
		<font size=2><stripes:label for="fileName"/></font>
	    </th>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="FolderDetail" varStatus="idx0">
  <tr>
    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
	<c:out value="${idx0.index+1}"/>
    </td>
    <td>
<%--
        <c:if test='${folderSelector.actionFlag=="Add"}'>
	    <stripes:checkbox name="folderSelector.fileInFolder" value="${idx0.index+1}" disabled="disabled">
	    </stripes:checkbox>
	</c:if>
        <c:if test='${folderSelector.actionFlag!="Add"}'>
--%>
	    <stripes:checkbox name="folderSelector.fileInFolder" value="${idx0.index+1}">
	    </stripes:checkbox>
<%--
	</c:if>
--%>
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
  </tr>
</c:forEach>
</table>
</div>
<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
	<c:out value="${folderSelector.actionFlag}"/>
--%>
	<center>
	<stripes:errors/>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <c:if test='${folderSelector.actionFlag=="Folder"}'>
		<stripes:submit name="AddSubFolder"  value="Add Sub Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Files"}'>
		<stripes:submit name="ConfirmAddFilesToSubFolder" value="Confirm Add files to Sub Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Add"}'>
		<stripes:submit name="ConfirmAddSubFolder" value="Confirm Add Sub Folder"/>
	    </c:if>
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
