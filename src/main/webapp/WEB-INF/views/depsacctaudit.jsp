<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepsAcctAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    CIF <stripes:label for="audit"/> <stripes:label for="inquiry"/>
	</h1>
	<table align="center" border="1" height="3" width="60%">
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="6">
		    <font size="3">
			<stripes:label for=""/> (CIF) <stripes:label for="audit"/>
			<stripes:label for="inquiry"/>
		    </font>
		</th>
	    </tr>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="caseNum"/></font></TH>
		    <th align=center height="19" colspan="1" rowspan="2" valign="bottom">
			<font size="3"><stripes:label for="aba"/></font></TH>
		    <th align=center height="19" colspan="1" rowspan="2" valign="bottom">
			<font size="3"><stripes:label for="account"/></font></TH>
		    <th align=center height="19" colspan="1" rowspan="2" valign="bottom">
			<font size="3">
			    <stripes:label for="last"/> <stripes:label for="modified"/>
			</font>
		    </th>
		    <th align=center height="19" colspan="1" rowspan="2" valign="bottom">
			<font size="3">
			    <stripes:label for="modified"/> By <stripes:label for="user"/>
			</font>
		    </th>
		</tr>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font>
		    </th>
		    <th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font>
		    </th>
		</tr>
		<tr>
		    <td>
			<select size="1" name="acctSelector.depsAcctNumFrom">
			    <c:if test='${acctSelector.actionFlag==""}'>
				<option selected>ALL</option>
				    <c:forEach var="acct" items="${acctSelector.acctList}">
					<option> <c:out value="${acct}"/></option>
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
				    <c:forEach var="acct" items="${acctSelector.acctList}">
					<option> <c:out value="${acct}"/></option>
				    </c:forEach>
			    </c:if>
			    <c:if test='${acctSelector.actionFlag!=""}'>
				<option selected><c:out value="${acctSelector.depsAcctNumTo}"/></option>
			    </c:if>
			</select>
		    </td>

		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.depsAba" maxlength="3" 
			    value="${acctSelector.depsAba}"/>
		    </td>

		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.depsNum" maxlength="15"
			    value="${acctSelector.depsNum}"/>
		    </td>
		    <td>
			<select size="1" name="acctSelector.account_log_date">
			    <c:if test='${acctSelector.actionFlag==""}'>
				<option selected>ALL</option>
				<c:forEach var="date" items="${acctSelector.dateList}">
				    <option> <c:out value="${date}"/> </option>
				</c:forEach>
			    </c:if>
			    <c:if test='${acctSelector.actionFlag!=""}'>
				<option selected><c:out value="${acctSelector.account_log_date}"/></option>
			    </c:if>
			</select>
		    </td>
		    <td>
			<select size="1" name="acctSelector.account_log_user">
			    <c:if test='${acctSelector.actionFlag==""}'>
				<option selected>ALL</option>
				<c:forEach var="user" items="${acctSelector.userList}">
				    <option> <c:out value="${user}"/> </option>
				</c:forEach>
			    </c:if>
			    <c:if test='${acctSelector.actionFlag!=""}'>
				<option selected><c:out value="${acctSelector.account_log_user}"/></option>
			    </c:if>
			</select>
		    </td>
		</tr>
	</table>

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
	<table colspan='7' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr>
		<th class="header" align=center height=19 colspan=7>
		    <font size=3 color=blue>
			<stripes:label for="account"/> <stripes:label for="inquiry"/>
			<stripes:label for="selResults"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="account"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="client"/> <stripes:label for="name"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="aba"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="acct"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="last"/> <stripes:label for="modified"/>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="modified"/> By <stripes:label for="user"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="modified"/> By <stripes:label for="function"/>
		    </font>
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
		<td align="center" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_modtime}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_moduser}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${dAcctDetail.deps_modfunc}"/>
		</td>
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
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
