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

	<table align="center" border="1" height="3" width="20%">
	    <c:if test='${entitySelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="entityInq"/></font></TH>
		</tr>
	    </c:if>
	    <c:if test='${entitySelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="entityMaint"/></font></TH>
		</tr>
	    </c:if>
		<tr bgcolor=turquoise>
			<th align=center height="19" colspan="1">
				<font size="3"><stripes:label for="entityId"/></font></TH>
		</tr>
	    <td>
		<select size="1" name="entitySelector.entityId">
		    <option selected>ALL</option>
		    <c:forEach var="Entity" items="${entitySelector.entityList}">
			<c:if test='${entitySelector.entityId!=Entity}'>
			    <option><c:out value="${Entity}"/></option>
			</c:if>
			<c:if test='${entitySelector.entityId==Entity}'>
			    <option selected><c:out value="${Entity}"/></option>
			</c:if>
		    </c:forEach>
		 </select>
	    </td>
	</table>

	<c:if test='${entitySelector.actionFlag!=""}'>

	<div id="selres">

	<table class="sortable" colspan='7' width='60%' align='center' border='1' height='39'>
<%--
	    <c:if test='${entitySelector.accessFlag=="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=10>
			<font size=3 color=blue>
			    <stripes:label for="entityInq"/> <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${entitySelector.accessFlag!="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=10>
			<font size=3 color=blue>
			    <stripes:label for="entityMaint"/> <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
--%>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="entityId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="entityName"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="entityType"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=2>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>

	<c:forEach var="EntityDetail" items="${entitySelector.entityRows}">
	    <tr>
		<td>
		    <c:out value="${EntityDetail.entityId}"/></b>
		</td>
		<td>
		    <c:out value="${EntityDetail.entityName}"/></b>
		</td>
		<td>
		    <c:out value="${EntityDetail.entityType}"/>
		</td>
		<c:if test='${entitySelector.accessFlag=="inq"}'>
		    <td colspan='2'>
			<stripes:link href="/econtroller/sysadmin/actions/Entity.action" event="Modify">
			    Detail
			    <stripes:param name="entityId" value="${EntityDetail.entityId}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${entitySelector.accessFlag!="inq"}' >
		    <td>
			<stripes:link href="/econtroller/sysadmin/actions/Entity.action" event="Modify">
			    Delete
			    <stripes:param name="entityId" value="${EntityDetail.entityId}"/>
			</stripes:link>
		    </td>
		    <td>
			<stripes:link href="/econtroller/sysadmin/actions/Entity.action" event="Modify">
			    Modify
			    <stripes:param name="entityId" value="${EntityDetail.entityId}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>
<%--	<c:out value="${entitySelector.actionFlag}"/>	--%>


	<center>
	    <stripes:errors/>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${entitySelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Entity"/>
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

