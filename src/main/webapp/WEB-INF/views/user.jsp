<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/User.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="user"/> <stripes:label for="maint"/></h1>
	<table align="center" border="1" height="3" width="25%">
	    <tr>
		<th align=center height="19" colspan="2">
		    <font size="3">
			<stripes:label for="user"/> <stripes:label for="maint"/>
		    </font>
		</th>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="userId"/></font></th>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></th>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></th>
	    </tr>
	    <tr>
		<td>
		    <select size="1" name="userSelector.user_id_from">
			<option>ALL</option>
			<c:forEach items="${userSelector.userList}" var="users">
			    <c:if test="${userSelector.user_id_from==users}">
				<option selected><c:out value="${users}"/></option>
			    </c:if>
			    <c:if test="${userSelector.user_id_from!=users}">
				<option> <c:out value="${users}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="userSelector.user_id_to">
			<option>NONE</option>
			<c:forEach items="${userSelector.userList}" var="users">
			    <c:if test="${userSelector.user_id_to==users}">
				<option selected><c:out value="${users}"/></option>
			    </c:if>
			    <c:if test="${userSelector.user_id_to!=users}">
				<option> <c:out value="${users}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	</table>
	<p align="center"></p>

	<c:if test='${userSelector.actionFlag!=""}'>
	<div id="selres">
<%----%>
	<table class="sortable" colspan='10' width='100%' align='center' border='1' bgcolor='lightgreen' height='39'>
<%--
	    <tr>
		<th align=center height="19" colspan="8">
		    <font size="3">
			<stripes:label for="user"/> <stripes:label for="maint"/>
			<stripes:label for="selResults"/>
		    </font>
		</th>
	    </tr>
--%>
	    <tr bgcolor=bluegreen>
		<th class="header" colspan="1">
		    <font size="2"><stripes:label for="userId"/></font>
		</th>
		<th class="header" colspan="1">
		    <font size="2"><stripes:label for="firstName"/></font>
		</th>
		<th class="header" colspan="1">
		    <font size="2"><stripes:label for="lastName"/></font>
		</th>
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="passwordChanged"/></font>
		</th>
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="passwordExpires"/></font>
		</th>
<%--
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="firstLogin"/></font>
		</th>
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="lastLogin"/></font>
		</th>
--%>
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="lastFailure"/></font>
		</th>
		<th class="header" colspan=1>
		    <font size=2><stripes:label for="failedAttempts"/></font>
		</th>
		<th class="header" colspan=1>
		    <font size=2>User <stripes:label for="status"/></font>
		</th>
		<th align=center width='12%' height=15 colspan="2">
		    <font size=2><stripes:label for="action"/></font>
		</th>
<%--
	    	<th class="header" colspan="2">
		    <font size=2><stripes:label for="action"/></font>
		</th>
--%>
	    </tr>
<%--
	    <tr>
		<c:forEach items="${prodSelector.prodNames}" var="prods">
		    <c:if test='${prods.key=="A"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="B"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="C"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="D"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="E"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="F"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="G"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="H"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		</c:forEach>
	    </tr>
	    <tr bgcolor=bluegreen>
		<c:forEach items="${prodSelector.prodNames}" var="prods" varStatus="idx">
		    <c:if test='${prods.key=="I"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="J"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="K"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="L"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="M"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="N"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="O"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		    <c:if test='${prods.key=="P"}'>
			<th align=center width='12%' height=15>
			    <b><font color='green' size=2><c:out value="${prods.value}"/></font></b>
			</th>
		    </c:if>
		</c:forEach>
		<th align=center width='12%' height=15>
		    <b><font color='green' size=2> </font></b>
		</th>
		<th align=center width='12%' height=15>
		    <b><font color='green' size=2> </font></b>
		</th>
		<th align=center width='12%' height=15 colspan="2">
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>
--%>
	    <c:forEach items="${userSelector.userrows}" var="usDetail">
		<tr>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_id}"/></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_fname}"/></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_lname}"/></b>
		    </td>
		    <td align='left' width='15%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_pass_lastmodified_f}"/></b>
		    </td>
		    <td align='left' width='15%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_pass_expiry_f}"/></b>
		    </td>
<%--
		    <td align='left' width='15%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_first_login_f}"/></b>
		    </td>
		    <td align='left' width='15%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_last_success_login_f}"/></b>
		    </td>
--%>
		    <td align='left' width='15%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_last_failed_login_f}"/></b>
		    </td>
		    <td align='left' width='11%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_failed_attempts}"/></b>
		    </td>
		    <td align='left' width='11%' height='19' bgcolor=lightyellow>
			<b><c:out value="${usDetail.user_id_blocked}"/></b>
		    </td>
		    <td align="center" bgcolor='lightyellow' width='12%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/User.action" event="Delete">
			    Delete
			    <stripes:param name="userId" value="${usDetail.user_id}"/>
			</stripes:link>
		    </td>
		    <td align="center" bgcolor='lightyellow' width='12%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/User.action" event="Modify">
			    Modify
			    <stripes:param name="userId" value="${usDetail.user_id}"/>
			</stripes:link>
		    </td>
		</tr>
<%--
		<tr bgcolor='bluegreen'>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'>	<c:out value="${usDetail.user_pa_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pb_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pc_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pd_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pe_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pf_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_pg_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='green'><c:out value="${usDetail.user_ph_auth}"/></font></b>
		    </td>
		</tr>
		<tr bgcolor='bluegreen'>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='brown'><c:out value="${usDetail.user_pi_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='brown'><c:out value="${usDetail.user_pj_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='brown'><c:out value="${usDetail.user_pk_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='brown'><c:out value="${usDetail.user_pl_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
			<b><font color='brown'><c:out value="${usDetail.user_pm_auth}"/></font></b>
		    </td>
		    <td align='left' width='12%' height='19' bgcolor=lightyellow>
		    </td>
		    <td align="center" bgcolor='lightyellow' width='12%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/User.action" event="Delete">
				Delete
				<stripes:param name="userId" value="${usDetail.user_id}"/>
			</stripes:link>
		    </td>
		    <td align="center" bgcolor='lightyellow' width='12%' height='19'>
			<stripes:link href="/econtroller/sysadmin/actions/User.action" event="Modify">
				Modify
				<stripes:param name="userId" value="${usDetail.user_id}"/>
			</stripes:link>
		    </td>
		</tr>
--%>
	    </c:forEach>
	</table>
<%--	</div> --%>
	</c:if>

    <center>
	<stripes:errors/>
	<stripes:submit name="View" value="View"/>
	<c:if test='${userSelector.accessFlag!="inq"}'>
	    <stripes:submit name="New" value="Add New User"/>
	</c:if>
    </center>

<%--
<c:out value='${userSelector.accessFlag}'/>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

