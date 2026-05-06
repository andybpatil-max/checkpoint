<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Entity.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="entity"/></h1>
	<div id="detail">
	    <table colspan='2' width='50%' align='center' border='1'>
		<tr bgcolor='bluegreen'>
		<c:if test='${entitySelector.accessFlag=="inq"}'>
		    <th  class="header" align='center' height='19' colspan='2'>
			<font size=3 color=blue><stripes:label for="entityInq"/></font>
		    </th>
		</c:if>
		<c:if test='${entitySelector.accessFlag!="inq"}'>
		    <c:if test='${entitySelector.actionFlag=="Modify"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="modify"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Delete"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="delete"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="New"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="add"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Update"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="modify"/></font>
			</th>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Add"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="add"/></font>
		   	</th>
		    </c:if>
		</c:if>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="entityId"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${entitySelector.actionFlag=="New"}'>
			<stripes:text name='entityDetail.entityId' value='${entityDetail.entityId}'
				size='30' maxlength="30"/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag!="New"}'>
			<stripes:text name='entityDetail.entityId' value='${entityDetail.entityId}'
				size='30' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="entityName"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${entitySelector.actionFlag=="Modify"}'>
		    	<stripes:text name='entityDetail.entityName' value='${entityDetail.entityName}'
				size="30" maxlength="15"/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Delete"}'>
		    	<stripes:text name='entityDetail.entityName' value='${entityDetail.entityName}'
				size="30" maxlength="15"/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="New"}'>
			<stripes:text name='entityDetail.entityName' value='${entityDetail.entityName}'
				size='30'  maxlength="15"/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Update"}'>
			<stripes:text name='entityDetail.entityName' value='${entityDetail.entityName}'
				size='30' readonly='true'/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Add"}'>
			<stripes:text name='entityDetail.entityName' value='${entityDetail.entityName}'
				size='30' readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="entityType"/></b></th>
		<td align='left' width=10% height='19'>
<%--		    <c:if test='${entitySelector.actionFlag=="Modify"}'>		--%>
			<select size="1" name="entityDetail.entityType">
			    <c:if test="${entityDetail.entityType != ''}" >
				<option selected><c:out value="${entityDetail.entityType}"/></option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'BRANCH'}" >
				<option>BRANCH</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'REGION'}" >
				<option>REGION</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'DEPARTMENT'}" >
				<option>DEPARTMENT</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'DIVISION'}" >
				<option>DIVISION</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'CLASS'}" >
				<option>CLASS</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'CATEGORY'}" >
				<option>CATEGORY</option>
			    </c:if>
			</select>
<%--
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Delete"}'>
		    	<stripes:text name='entityDetail.entityType' value='${entityDetail.entityType}'
				size="15" maxlength="15"/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="New"}'>
			<select size="1" name="entityDetail.entityType">
			    <c:if test="${entityDetail.entityType != ''}" >
				<option selected><c:out value="${entityDetail.entityType}"/></option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'BRANCH'}" >
				<option>BRANCH</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'REGION'}" >
				<option>REGION</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'DEPARTMENT'}" >
				<option>DEPARTMENT</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'DIVISION'}" >
				<option>DIVISION</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'CLASS'}" >
				<option>CLASS</option>
			    </c:if>
			    <c:if test="${entityDetail.entityType != 'CATEGORY'}" >
				<option>CATEGORY</option>
			    </c:if>
			</select>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Update"}'>
			<stripes:text name='entityDetail.entityType' value='${entityDetail.entityType}'
				size='15' readonly='true'/>
		    </c:if>
		    <c:if test='${entitySelector.actionFlag=="Add"}'>
			<stripes:text name='entityDetail.entityType' value='${entityDetail.entityType}' 
				size='15' readonly='true'/>
		    </c:if>
--%>
		</td>
		</tr>
	    </table>
	</div>
<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value="${entitySelector.actionFlag}"/>							--%>

	<center>
	    <c:if test='${entitySelector.accessFlag!="inq"}'>
		<c:if test='${entitySelector.actionFlag=="Modify"}'>
		    <stripes:submit name="Update" value="Update Entity"/>
		</c:if>
		<c:if test='${entitySelector.actionFlag=="Update"}'>
		    <stripes:submit name="Confirm" value="Confirm Update Entity"/>
		</c:if>
		<c:if test='${entitySelector.actionFlag=="Add"}'>
		    <stripes:submit name="Confirm" value="Confirm Add Entity"/>
		</c:if>
		<c:if test='${entitySelector.actionFlag=="New"}'>
		    <stripes:submit name="Add" value="Add Entity"/>
		</c:if>
		<c:if test='${entitySelector.actionFlag=="Delete"}'>
		    <stripes:submit name="ConfirmDelete"  value="Confirm Delete Entity"/>
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
