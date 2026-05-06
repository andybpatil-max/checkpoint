<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/UserAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="user"/> <stripes:label for="management"/></h1>

	<table align="center" border="1" height="3" width="25%">
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="2">
		    <font size="3">
			<stripes:label for="user"/> <stripes:label for="audit"/>
			<stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		    </font></th>
	    </tr>
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="2">
			<font size="3"><stripes:label for="userId"/></font></TH>
	    </tr>
	    <tr bgcolor=turquoise>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
	    </tr>
	    <tr>
		<td>
		    <select size="1" name="userSelector.user_id_from">
			<option>ALL</option>
			<c:forEach var="users" items="${userSelector.userList}">
			    <c:if test='${userSelector.user_id_from==users}'>
				<option selected><c:out value="${users}"/></option>
			    </c:if>
			    <c:if test='${userSelector.user_id_from!=users}' >
				<option><c:out value="${users}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="userSelector.user_id_to">
			<option>NONE</option>
			<c:forEach var="users" items="${userSelector.userList}">
			    <c:if test='${userSelector.user_id_to==users}'>
				<option selected><c:out value="${users}"/></option>
			    </c:if>
			    <c:if test='${userSelector.user_id_to!=users}' >
				<option><c:out value="${users}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	</table>

	<c:if test='${userSelector.actionFlag!=""}'>

	<div id="selres">
	    <table colspan='7' width='100%' align='center' border='1' bgcolor='lightgreen' height='39'>
<%--
		<tr bgcolor=turquoise>
		    <th class="header" align=center height="19" colspan="7">
			<font size="3">
			    <stripes:label for="user"/> <stripes:label for="audit"/>
			    <stripes:label for="inquiry"/> <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
--%>
		<tr bgcolor=bluegreen>
		    <th align=center width="12%"height="19" colspan="1">
			<font size="2"><stripes:label for="userId"/></font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font size=2><stripes:label for="passwordChanged"/></font>
		    </th>
		    <th align=center width='8%' height=15 colspan=1>
			<font size=2><stripes:label for="passwordExpires"/></font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font size=2><stripes:label for="firstLogin"/></font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font size=2><stripes:label for="lastLogin"/></font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font size=2><stripes:label for="lastFailure"/></font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font size=2><stripes:label for="failedAttempts"/></font>
		    </th>
		</tr>
		<tr bgcolor=bluegreen>
		    <th align=center width='12%' height=15 colspan=1>
			<font color='green' size=2>User <stripes:label for="status"/></font>
		    </th>
		    <c:forEach var="PN" items="${prodSelector.prodNames}">
			<c:if test='${PN.key=="A"}'>
			    <th align=center width='12%' height=15>
				<b><font color='green' size=2><c:out value="${PN.value}"/></font></b>
			    </th>
			</c:if>
			<c:if test='${PN.key=="C"}'>
			    <th align=center width='12%' height=15>
				<b><font color='green' size=2><c:out value="${PN.value}"/></font></b>
			    </th>
			</c:if>
			<c:if test='${PN.key=="D"}'>
			    <th align=center width='12%' height=15>
				<b><font color='green' size=2><c:out value="${PN.value}"/></font></b>
			    </th>
			</c:if>
		    </c:forEach>
		    <th align=center width='12%' height=15 colspan=1>
			<font color='green' size=2>
			    <stripes:label for="modified"/> By <stripes:label for="user"/>
			</font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font color='green' size=2>
			    <stripes:label for="modified"/> By <stripes:label for="function"/>
			</font>
		    </th>
		    <th align=center width='12%' height=15 colspan=1>
			<font color='green' size=2>
			    <stripes:label for="last"/> <stripes:label for="modified"/>
			</font>
		    </th>
		</tr>
		<c:forEach var="UserDetail" items="${userSelector.userrows}">
		    <tr>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_id}"/></b>
			</td>
			<td align='left' width='15%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_pass_lastmodified_f}"/></b>
			</td>
			<td align='left' width='8%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_pass_expiry_f}"/></b>
			</td>
			<td align='left' width='15%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_first_login_f}"/></b>
			</td>
			<td align='left' width='15%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_last_success_login_f}"/></b>
			</td>
			<td align='left' width='15%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_last_failed_login_f}"/></b>
			</td>
			<td align='left' width='11%' height='19' bgcolor=lightyellow>
			    <b><c:out value="${UserDetail.user_failed_attempts}"/></b>
			</td>
		    </tr>
		    <tr bgcolor='bluegreen'>
			<td align='left' width='11%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_id_blocked}"/></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_pa_auth}"/></font></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_pd_auth}"/></font></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_pc_auth}"/></font></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_mod_user}"/></font></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_mod_func}"/></font></b>
			</td>
			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <b><font color='green'>
			    <c:out value="${UserDetail.user_last_modified}"/></font></b>
			</td>
<%--			<td align='left' width='12%' height='19' bgcolor=lightyellow>
			    <c:out value="UserSelector.rowCount}"/>
			</td>	--%>
		    </tr>
		</c:forEach>
	    </table>
	</div>
	</c:if>

	<center>
	    <stripes:errors/>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${userSelector.actionFlag!=""}'>
		<stripes:submit name="PrintReport" value="Print Report"/>
	    </c:if>
	</center>

<%--
	<c:out value='${userSelector.user_id_from}'/>
	<c:out value='${userSelector.user_id_to}'/>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
