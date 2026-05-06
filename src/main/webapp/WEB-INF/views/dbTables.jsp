<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/DbTable.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="dbtable"/></h1>

	<table align="center" border="1" height="3" width="20%">
	    <c:if test='${dbSelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="dbtable.inq"/></font></TH>
		</tr>
	    </c:if>
	    <c:if test='${dbSelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="dbtable.maint"/></font></TH>
		</tr>
	    </c:if>
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="Application"/></font></TH>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="table"/></font></TH>
	    </tr>
	    <tr>
	    <td>
		<select size="1" name="dbSelector.dbAppl">
		    <option>ALL</option>
		    <c:forEach var="appl" items="${dbSelector.applList}">
			<c:if test='${dbSelector.dbAppl==appl}'>
			    <option selected><c:out value="${appl}"/> </option>
			</c:if>
			<c:if test='${dbSelector.dbAppl!=appl}'>
			    <option><c:out value="${appl}"/> </option>
			</c:if>
		    </c:forEach>
		 </select>
	    </td>
	    <td>
		<select size="1" name="dbSelector.dbTable">
		    <option>ALL</option>
		    <c:forEach var="table" items="${dbSelector.dbList}">
			<c:if test='${dbSelector.dbTable==table}'>
			    <option selected><c:out value="${table}"/> </option>
			</c:if>
			<c:if test='${dbSelector.dbTable!=table}'>
			    <option><c:out value="${table}"/> </option>
			</c:if>
		    </c:forEach>
		 </select>
	    </td>
	    </tr>
	</table>

	<c:if test='${dbSelector.actionFlag!=""}'>

	<div id="selres">

	<c:if test='${dbSelector.accessFlag!="inq"}'>
	<table colspan='6' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
	</c:if>
	<c:if test='${dbSelector.accessFlag=="inq"}'>
	<table colspan='4' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
	</c:if>
	    <c:if test='${dbSelector.accessFlag=="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=4>
			<font size=3 color=blue><stripes:label for="dbtable.inq"/></font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${dbSelector.accessFlag!="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=6>
			<font size=3 color=blue><stripes:label for="dbtable.maint"/></font>
		    </th>
		</tr>
	    </c:if>
	    <tr bgcolor=bluegreen>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="Application"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="table"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="sql"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="initsql"/></font>
		</th>
	    <c:if test='${dbSelector.accessFlag!="inq"}'>
		<th align='center' width='10%' height=15 colspan=2>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </c:if>
	    </tr>

	<c:forEach var="DbDetail" items="${dbSelector.dbRows}">
	    <tr>
		<td>
		    <c:out value="${DbDetail.dbAppl}"/></b>
		</td>
		<td>
		    <c:out value="${DbDetail.dbTable}"/></b>
		</td>
		<td>
		    <c:out value="${DbDetail.dbSql}"/></b>
		</td>
		<td>
		    <c:out value="${DbDetail.dbInitSql}"/></b>
		</td>
		<c:if test='${dbSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:link href="/econtroller/sysadmin/actions/DbTable.action" event="Delete">
			    Delete
			    <stripes:param name="dbTable" value="${DbDetail.dbTable}"/>
			</stripes:link>
		    </td>
		    <td>
			<stripes:link href="/econtroller/sysadmin/actions/DbTable.action" event="Modify">
			    Modify
			    <stripes:param name="dbTable" value="${DbDetail.dbTable}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>

	<center>
	    <stripes:errors />
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${dbSelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Table"/>
		<stripes:submit name="ExtractTableData" value="Extract Data"/>
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

