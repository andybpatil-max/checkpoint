<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Rhosts.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="remoteHost"/> <stripes:label for="maintInq"/></h1>

	<table align="center" border="1" height="3" width="20%">
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="2">
		    <font size="3">
			<stripes:label for="remoteHost"/> <stripes:label for="maint"/>
			<stripes:label for="selCriteria"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="node"/></font></TH>
	    </tr>
	    <td>
		<select size="1" name="rhostSelector.rhosts_rem_node_sel">
		    <c:if test='${rhostSelector.actionFlag==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${rhostSelector.actionFlag!=""}'>
			<option selected><c:out value="${rhostSelector.rhosts_rem_node_sel}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach var="host" items="${rhostSelector.hostList}">
			<option> <c:out value="${host}"/> </option>
		    </c:forEach>
		 </select>
	    </td>
	</table>

	<c:if test='${rhostSelector.actionFlag!=""}'>

	<div id="selres">

	<table colspan='7' width='60%' align='center' border='1' height='39'>
	    <tr bgcolor=bluegreen>
		<th class="header" align=center height=19 colspan=10>
		    <font size=3 color=blue>
			<stripes:label for="remoteHost"/> <stripes:label for="maint"/>
			<stripes:label for="selResults"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="nodeId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="node"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="user"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="passwork"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="xferMode"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=2>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>

	<c:forEach var="RhostDetail" items="${rhostSelector.rhostrows}">
	    <tr>
		<td>
		    <c:out value="${RhostDetail.rhosts_node_id}"/></b>
		</td>
		<td>
		    <c:out value="${RhostDetail.rhosts_rem_node}"/></b>
		</td>
		<td>
		    <c:out value="${RhostDetail.rhosts_user_name}"/>
		</td>
		<td>
		    <c:out value="${RhostDetail.rhosts_user_passh}"/>
		</td>
		<td>
		    <c:out value="${RhostDetail.rhostsXferMode}"/>
		</td>
		<td>
		    <stripes:link href="/econtroller/sysadmin/actions/Rhosts.action" event="Modify">
			Delete
			<stripes:param name="remHost" value="${RhostDetail.rhosts_node_id}"/>
		    </stripes:link>
		</td>
		<td>
		    <stripes:link href="/econtroller/sysadmin/actions/Rhosts.action" event="Modify">
			Modify
			<stripes:param name="remHost" value="${RhostDetail.rhosts_node_id}"/>
		    </stripes:link>
		</td>
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>

	<center>
	    <stripes:errors/>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${rhostSelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Host"/>
	    </c:if>
	</center>
<%--
	    <c:out value="${rhostSelector1.actionFlag}"/>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

