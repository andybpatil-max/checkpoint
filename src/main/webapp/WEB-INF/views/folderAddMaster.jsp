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
	<h1><stripes:label for="folder"/> <stripes:label for="maintenance"/></h1>
    </c:if>

<div id="detail">
    <table colspan='2' width='70%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <th class="header" colspan="2">
		<c:if test='${folderSelector.actionFlag=="Folder"}'>
		    <font size=3 color=blue>
			<stripes:label for="add"/> <stripes:label for="master"/>\
			<stripes:label for="folder"/>
		    </font>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="AddFolder"}'>
		    <font size=3 color=blue>
			<stripes:label for="add"/> <stripes:label for="master"/>\
			<stripes:label for="folder"/>
		    </font>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Files"}'>
		    <font size=3 color=blue>
			<stripes:label for="add"/> Files To <stripes:label for="master"/>\
			<stripes:label for="folder"/>
		    </font>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="AddFiles"}'>
		    <font size=3 color=blue>
			<stripes:label for="add"/> Files To <stripes:label for="master"/>\
			<stripes:label for="folder"/>
		    </font>
		</c:if>
	    </th>
	</tr>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="folder"/> <stripes:label for="name"/></b>
	    </th>
	    <td align='left' width=10% height='19'>
		    <stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32' readonly='true'/>
<%--
		<c:if test='${folderSelector.actionFlag=="Folder"}'>
		    <stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32'/>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Files"}'>
		    <stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32' readonly='true'/>
		</c:if>
--%>
	    </td>
	</tr>
<%----%>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="folder"/> <stripes:label for="description"/></b>
	    </th>
	    <td align='left' width=10% height='19'>
		<c:if test='${folderSelector.actionFlag=="Folder"}'>
		    <stripes:text name='folderDetail.folderDesc' value='${folderDetail.folderDesc}'
			size='64' maxlength='64'/>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Files"}'>
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
		<stripes:text name='folderDetail.folderBr' size='3' readonly='true'/>
	    </td>
	</tr>
    </table>
</div>
<div id="selres">
<table class="sortable" colspan='9' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr bgcolor=bluegreen>
<%--
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="folder"/><stripes:label for="name"/></font>
	    </th>
--%>
	    <th align='center' width='2%' height=15 colspan=1>
	    	<font size=2><stripes:label for="subBranch"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="application"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="fileName"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="file"/> <stripes:label for="description"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="indexed"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='1'>
		<font size=2><stripes:label for="overApp"/></font>
	    </th>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="FDetail" varStatus="idx0">
  <tr>
<%--
    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
	<c:out value="${idx0.index+1}"/>
    </td>
    <td align="right" bgcolor='lightyellow' width='10%' height='19'>
	<stripes:text name='folderBeans[${idx0.index}].folderDesc' value='${FDetail.folderDesc}'
			size='24' maxlength='32' readonly='true'/>
    </td>
--%>
    <td  align='center' bgcolor='lightyellow' width='10%' height='19'>
	<stripes:text name='folderBeans[${idx0.index}].folderSBr' value='${FDetail.folderSBr}'
			size='10' maxlength='10'/>
    </td>
    <td  align='center' bgcolor='lightyellow' width='10%' height='19'>
	<stripes:text name='folderBeans[${idx0.index}].folderAppl' value='${FDetail.folderAppl}'
			size='10' maxlength='10'/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
	<stripes:text name='folderBeans[${idx0.index}].folderFile' value='${FDetail.folderFile}'
			size='25' maxlength='120'/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
	<stripes:text name='folderBeans[${idx0.index}].folderFileDesc' value='${FDetail.folderFileDesc}'
			size='25' maxlength='100'/>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='1'>
	<select size="1" name="folderBeans[${idx0.index}].folderIndexed">
	    <option Selected value="N">No</option>
	    <option value="Y">Yes</option>
	</select>
    </td>
    <td align="left" bgcolor='lightyellow' width='4%' height='19' colspan='3'>
	<select size="1" name="folderBeans[${idx0.index}].folderOverApp">
	    <option Selected value="O">Overwrite</option>
	    <option value="A">Append</option>
	</select>
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
		<stripes:submit name="AddMasterFolder"  value="Add Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Files"}'>
		<stripes:submit name="AddMasterFolder"  value="Add Files"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="AddMaster"}'>
		<stripes:submit name="ConfirmAddMasterFolder" value="Confirm Add Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="AddFiles"}'>
		<stripes:submit name="ConfirmAddFolder" value="Confirm Add Files"/>
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
