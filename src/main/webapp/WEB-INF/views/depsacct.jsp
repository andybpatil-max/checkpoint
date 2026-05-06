<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepsAcct.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${acctSelector.accessFlag=="inq"}'>
	    <h1>
		<stripes:label for="account"/> <stripes:label for="maping"/>
		<stripes:label for="inquiry"/>
	    </h1>
	 </c:if>
	<c:if test='${acctSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="account"/> <stripes:label for="maping"/>
		<stripes:label for="maint"/>
	    </h1>
	</c:if>

<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>

	<div id="detail">
	<table align="center" border="1" height="3" width="60%">
	    <c:if test='${acctSelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th class="header" height="19" colspan="3">
			<font size="3">
			    <stripes:label for="account"/> <stripes:label for="mapping"/>
			    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${acctSelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th class="header" height="19" colspan="3">
			<font size="3">
			    <stripes:label for="account"/> <stripes:label for="mapping"/>
			    <stripes:label for="maint"/> <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="1"></TH>
		    <th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		    <th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
		</tr>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="caseNum"/></font></TH>
		    <td>
			<select size="1" name="acctSelector.depsAcctNumFrom">
			    <c:if test='${acctSelector.actionFlag==""}'>
				<option selected>ALL</option>
				    <c:forEach var="acct" items="${acctSelector.depAcctList}">
					<option value='<c:out value="${acct.key}"/>'> 
					    <c:out value="${acct.key}"/> </option>
				    </c:forEach>
			    </c:if>
			    <c:if test='${acctSelector.actionFlag!=""}'>
				<option selected><c:out value="${acctSelector.depsAcctNumFrom}"/></option>
			    </c:if>
			</select>
		    </td>
		    <td>
			<select size="1" name="acctSelector.depsAcctNumTo">
			    <c:if test='${acctSelector.actionFlag==""}'>
				<option selected>NONE</option>
				    <c:forEach var="acct" items="${acctSelector.depAcctList}">
					<option value='<c:out value="${acct.key}"/>'> 
					    <c:out value="${acct.key}"/> </option>
				    </c:forEach>
			    </c:if>
			    <c:if test='${acctSelector.actionFlag!=""}'>
				<option selected><c:out value="${acctSelector.depsAcctNumTo}"/></option>
			    </c:if>
			</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="aba"/></font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.depsAba" maxlength="3" 
			    value="${acctSelector.depsAba}"/>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="account"/></font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.depsNum" maxlength="15"
			    value="${acctSelector.depsNum}"/>
		    </td>
		</tr>
	</table>
	</div>

    <c:if test='${acctSelector.actionFlag!=""}'>
	<div id="totals">
	    <table align='center'>
		<tr bgcolor=bluegreen colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><b><c:out value="${acctSelector.rowCount}"/></b></td>
		</tr>
	    </table>
	</div>

	<p align="center"></p>
	<div id="selres">
	<table class="sortable" colspan='6' width='100%'>
<%--
	    <c:if test='${acctSelector.accessFlag=="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=6>
			<font size=3 color=blue>
			    <stripes:label for="account"/> <stripes:label for="mapping"/>
			    <stripes:label for="inquiry"/> <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${acctSelector.accessFlag!="inq"}'>
		<tr>
		    <th class="header" align=center height=19 colspan=6>
			<font size=3 color=blue>
			    <stripes:label for="account"/> <stripes:label for="mapping"/>
			    <stripes:label for="maint"/> <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
--%>
	    <tr bgcolor=bluegreen>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="accountNum"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="client"/> <stripes:label for="name"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="aba"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="checkAccount"/>
		</th>
		<th align=center width='10%' height=15 colspan=2>
		    <stripes:label for="action"/>
		</th>
	    </tr>

	<c:forEach items="${acctSelector.depsAcctRows}" var="dAcctDetail">
	    <tr>
		<td align="center" width='10%' height='19'><b>
		    <c:out value="${dAcctDetail.deps_account}"/></b>
		</td>
		<td align="left" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_payee}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_checkaba}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_checkaccount}"/>
		</td>
		<c:if test='${acctSelector.accessFlag!="inq"}'>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/DepsAcct.action" event="Modify">
			    Delete
			    <stripes:param name="action" value="del"/>
			    <stripes:param name="acctNum" value="${dAcctDetail.deps_account}"/>
			    <stripes:param name="depAba" value="${dAcctDetail.deps_checkaba}"/>
			    <stripes:param name="depNum" value="${dAcctDetail.deps_checkaccount}"/>
			</stripes:link>
		    </td>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/DepsAcct.action" event="Modify">
			    Modify
			    <stripes:param name="action" value="mod"/>
			    <stripes:param name="acctNum" value="${dAcctDetail.deps_account}"/>
			    <stripes:param name="depAba" value="${dAcctDetail.deps_checkaba}"/>
			    <stripes:param name="depNum" value="${dAcctDetail.deps_checkaccount}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${acctSelector.accessFlag=="inq"}'>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/DepsAcct.action" event="Modify">
			    Detail
			    <stripes:param name="action" value="inq"/>
			    <stripes:param name="acctNum" value="${dAcctDetail.deps_account}"/>
			    <stripes:param name="depAba" value="${dAcctDetail.deps_checkaba}"/>
			    <stripes:param name="depNum" value="${dAcctDetail.deps_checkaccount}"/>
			</stripes:link>
		    </td>
		    <td align="center" width='5%' height='19'> </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>
	</div>

	<div id="totals">
	<table align='center'>
	    <tr bgcolor=bluegreen colspan=2>
		<th><font size=2><stripes:label for="rowcount"/></font></th>
		<td>
		    <c:out value="${acctSelector.rowCount}"/></b>
		</td>
	    </tr>
	</table>
	</div>
    </c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
	<c:if test='${acctSelector.accessFlag!="inq"}'>
	    <stripes:submit name="New" value="New Account"/>
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
