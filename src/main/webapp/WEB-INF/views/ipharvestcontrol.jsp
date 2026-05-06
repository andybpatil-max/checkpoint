<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/HarvestAdmin.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="harvestAdmin"/></h1>

	<table align="center" border="1" height="3" width="20%">
	    <c:if test='${harvestSelector.accessFlag=="inq"}'>
		<tr>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="harvest"/> <stripes:label for="inquiry"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<tr>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="harvest"/> <stripes:label for="maint"/>
			</font>
		    </th>
		</tr>
	    </c:if>
<%--
	    <tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="harvestId"/></font></TH>
	    </tr>
	    <tr>
	    <td>
		<select size="1" name="harvestSelector.serverName">
		    <c:if test='${harvestSelector.actionFlag!=""}'>
			<option selected><c:out value="${harvestSelector.serverName}"/></option>
		    </c:if>
		    <c:forEach var="cKey" items="${harvestSelector.serverList}">
			<option> <c:out value="${cKey}"/> </option>
		    </c:forEach>
		 </select>
	    </td>
	    </tr>
--%>
	</table>
<%--	<c:if test='${harvestSelector.actionFlag!=""}'> --%>
	<div id="selres">
	<table colspan='9' width='60%' align='center' border='1' height='39'>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="harvestId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="startHour"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="endHour"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="cycleMins"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="Throttle"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="retentionYears"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="checkNulls"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="autoCreate"/></font>
		</th>
		<c:if test='${harvestSelector.accessFlag!="inq"}'>
		    <th align='center' width='10%' height=15 colspan=2>
			<font size=2>Action</font>
		    </th>
		</c:if>
	    </tr>

	<c:forEach var="hControl" items="${harvestSelector.hcRowArray}">
	    <tr>
		<td>
		    <c:out value="${hControl.serverName}"/></b>
		</td>
		<td>
		    <c:out value="${hControl.harvestBr}"/></b>
		</td>
		<td>
		    <c:out value="${hControl.startHour}"/></b>
		</td>
		<td>
		    <c:out value="${hControl.endHour}"/></b>
		</td>
		<td>
		    <c:out value="${hControl.cycleMins}"/>
		</td>
		<td>
		    <c:out value="${hControl.throttle}"/>
		</td>
		<td>
		    <c:out value="${hControl.retentionYears}"/>
		</td>
		<td>
		    <c:out value="${hControl.checkNulls}"/>
		</td>
		<td>
		    <c:out value="${hControl.autoCreate}"/>
		</td>
		<c:if test='${harvestSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:link href="/econtroller/infopoint/actions/HarvestAdmin.action"
					event="ModifyControl">
			    Delete
			    <stripes:param name="serverName" value="${hControl.serverName}"/>
			</stripes:link>
		    </td>
		    <td>
			<stripes:link href="/econtroller/infopoint/actions/HarvestAdmin.action"
					event="ModifyControl">
			    Modify
			    <stripes:param name="serverName" value="${hControl.serverName}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>

	</div>

<%--	</c:if> --%>

	<center>
	    <stripes:errors/>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<stripes:submit name="NewControl" value="New Control"/>
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

