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
	<h1><stripes:label for="folder"/> User <stripes:label for="inquiry"/></h1>
    </c:if>
    <c:if test='${folderSelector.accessFlag!="inq"}'>
	<h1><stripes:label for="folder"/> User <stripes:label for="maint"/></h1>
    </c:if>

<div id="detail">
    <table colspan='8' width='70%' align='center' border='1'>
	<tr>
	    <c:if test='${folderSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
		    <font size=3 color=blue>
			<stripes:label for="folder"/> User <stripes:label for="inquiry"/>
		    </font>
		</th>
	    </c:if>
	    <c:if test='${folderSelector.accessFlag!="inq"}'>
		<c:if test='${folderSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="newuser"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue><stripes:label for="add"/></font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue><stripes:label for="modify"/></font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="AddUser"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue><stripes:label for="add"/></font>
		    </th>
		</c:if>
	    </c:if>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="userName"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${folderSelector.actionFlag=="newuser"}'>
		<stripes:text name='folderDetail.folderUser' value='${folderDetail.folderUser}'
			size='32' maxlength='32'/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag!="newuser"}'>
		<stripes:text name='folderDetail.folderUser' value='${folderDetail.folderUser}'
			size='32' maxlength='32' readonly='true'/>
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

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="folder"/> <stripes:label for="name"/>
	</th>
	<td>
	<table>
	<tr>
	    <c:forEach items="${folderSelector.userFolderList}" var="ufD" varStatus="idx1">
		<c:if test="${not idx1.first and idx1.index % 10 == 0}">
		    </tr><tr>
		</c:if>
		<td>
		    <stripes:checkbox name="folderDetail.foldersEntitled" value='${ufD}'>
		    </stripes:checkbox>
		    <c:out value='${ufD}'/>
		</td>
	    </c:forEach>
	</tr>
	</table>
	</td>
   </table>
</div>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
	<c:out value="${folderSelector.actionFlag}"/>
	<c:out value="${folderDetail.folderName}"/>
--%>
	<c:out value='${folderSelector.actionFlag}'/>
	<center>
	<stripes:errors/>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <c:if test='${folderSelector.actionFlag=="newuser"}'>
		<stripes:submit name="AddUser"  value="Add user"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="AddUser"}'>
		<stripes:submit name="ConfirmAddUser"  value="Confirm Add User"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Delete"}'>
		<stripes:submit name="ConfirmDeleteUser" value="Confirm Delete User"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Update"}'>
		<stripes:submit name="ConfirmUpdateUser"  value="Confirm Update User"/>
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
