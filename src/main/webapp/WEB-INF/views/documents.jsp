<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/flow/actions/Document.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="document"/> <stripes:label for="management"/></h1>

	<table align="center" border="1" height="3" width="20%">
	    <c:if test='${documentSelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="document"/> <stripes:label for="inquiry"/>
			</font></TH>
		</tr>
	    </c:if>
	    <c:if test='${documentSelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="document"/> <stripes:label for="maint"/>
			</font></TH>
		</tr>
	    </c:if>
		<tr>
		    <th align=center height="19" colspan="1"></TH>
		    <th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		    <th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1">
			<font size="3">
			    <stripes:label for="document"/> <stripes:label for="id"/>
			</font></TH>
		    <td>
			<select size="1" name="documentSelector.docIdFrom">
			    <option>ALL</option>
			    <c:forEach var="acct" items="${documentSelector.docList}">
				<c:if test='${documentSelector.docIdFrom==doc}'>
				    <option selected><c:out value="${documentSelector.docIdFrom}"/></option>
				</c:if>
				<c:if test='${documentSelector.docIdFrom!=doc}'>
				    <option> <c:out value="${doc}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </td>
		    <td>
		    	<select size="1" name="documentSelector.docIdTo">
			    <option>NONE</option>
			    <c:forEach var="doc" items="${documentSelector.docList}">
				<c:if test='${documentSelector.docIdTo==doc}'>
				    <option selected><c:out value="${documentSelector.docIdTo}"/></option>
				</c:if>
				<c:if test='${documentSelector.docIdTo!=acct}'>
				    <option> <c:out value="${doc}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </td>
		</tr>
	</table>

	<c:if test='${documentSelector.actionFlag!=""}'>

	<div id="selres">

	<table colspan='7' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <c:if test='${documentSelector.accessFlag=="inq"}'>
		<tr bgcolor=bluegreen>
		    <th class="header" align=center height=19 colspan=10>
			<font>
			    <stripes:label for="document"/> <stripes:label for="inquiry"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${documentSelector.accessFlag!="inq"}'>
		<tr bgcolor=bluegreen>
		    <th class="header" align=center height=19 colspan=10>
			<font size=3>
			    <stripes:label for="document"/> <stripes:label for="maint"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <tr bgcolor=bluegreen>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="description"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="projectId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="user"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=2>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>

	<c:forEach var="RhostDetail" items="${documentSelector.rhostrows}">
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
		<c:if test='${documentSelector.accessFlag=="inq"}'>
		    <td colspan='2'>
			<stripes:link href="/econtroller/sysadmin/actions/Rhosts.action" event="Modify">
			    Detail
			    <stripes:param name="remHost" value="${RhostDetail.rhosts_node_id}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${documentSelector.accessFlag!="inq"}' >
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
		</c:if>
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>

	<center>
	    <stripes:errors/>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${documentSelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Host"/>
	    </c:if>
	    <c:out value="${documentSelector1.actionFlag}"/>
	</center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

