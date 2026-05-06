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
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="2">
		    <font size="3"><stripes:label for="dbtable.inq"/></font></TH>
	    </tr>
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

	<table colspan='4' width='60%' align='center' border='1' height='39'>
	    <tr>
		<th class="header" align=center height=19 colspan=4>
		    <font size=3 color=blue><stripes:label for="dbtable.inq"/></font>
		</th>
	    </tr>
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
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>

	<center>
	    <stripes:errors />
	    <stripes:submit name="ViewInq" value="View"/>
	</center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

