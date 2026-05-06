<%@ include file="taglibs.jsp" %>

<stripes:layout-render name="standardlogin.jsp" title="Login">
    <stripes:layout-component name="contents">
	    <stripes:form action="/econtroller/actions/Logon.action">
<%--		<div>${user} == ${ctx.user}</div>
		<c:out value="${user.actionFlag}"/>				--%>
		<c:choose>
		    <c:when test="${user.actionFlag=='ChangePass'}"> 
			<div id="main2">
			    <table>
				<tr>
				    <th style="font-weight:bold;"><stripes:label for="userName"/>:</th>
				    <td><stripes:text name="user.userId" value="${user.userId}" readonly="true"/></td>
				</tr>
				<tr>
				    <th style="font-weight:bold;"><stripes:label for="password"/>:</th>
				    <td><stripes:password name="user.userPass" value="${user.userPass}" 
						repopulate="true" readonly="true"/></td>
				</tr>
			    </table>
			    <table>
				<tr>
				    <th colspan="2" style="font-weight:bold; text-align:center">
					<stripes:label for="passwordEnter"/>:</th>
				</tr>
				<tr>
				    <th style="font-weight:bold;"><stripes:label for="passwordNew"/>:</th>
				    <th style="font-weight:bold;"><stripes:label for="passwordVerify"/>:</th>
				</tr>
				<tr>
				    <td><stripes:password name="user.newPass" value="${user.newPass}" repopulate="true"/></td>
				    <td><stripes:password name="user.newVerify" value="${user.newPass}" repopulate="true"/></td>
				</tr>
			    </table>
			</div>
			<stripes:errors action="/econtroller/actions/Logon.action"/>
			<stripes:submit name="confirmPassword" value="Confirm Password"/>
		    </c:when>
		    <c:when  test="${user.actionFlag=='Blocked'}">
			<stripes:errors action="/econtroller/actions/Logon.action"/>
			<stripes:submit name='Login' disabled="true"/>
			<stripes:submit name="changePass" disabled="true"/>
		    </c:when>
		    <c:when  test="${user.actionFlag=='Logoff'}">
			<div id="main2">
			<table>
			    <tr>
				<th style="font-weight:bold;"><stripes:label for="userName"/>:</th>
				<td><stripes:text name="user.userId" value="${user.userId}"/></td>
			    </tr>
			    <tr>
				<th style="font-weight:bold;"><stripes:label for="password"/>:</th>
				<td><stripes:password name="user.userPass" value="${user.userPass}" repopulate="true"/></td>
			    </tr>
			</table>
			</div>
			<%-- If the security servlet attached a targetUrl, carry that along. --%>
			<stripes:errors action="/econtroller/actions/Logon.action"/>
			<stripes:submit name='logoff'  value="Logoff eController"/>
		    </c:when>
		    <c:otherwise>
			<div id="main2">
			<table>
			    <tr>
				<th style="font-weight:bold;"><stripes:label for="userName"/>:</th>
				<td><stripes:text name="user.userId" value="${user.userId}"/></td>
			    </tr>
			    <tr>
				<th style="font-weight:bold;"><stripes:label for="password"/>:</th>
				<td><stripes:password name="user.userPass" value="${user.userPass}" repopulate="false"/></td>
			    </tr>
			</table>
			</div>
			<%-- If the security servlet attached a targetUrl, carry that along. --%>
			<stripes:errors action="/econtroller/actions/Logon.action"/>
			<stripes:submit name="login" value="Login" />
			<stripes:submit name="changePass" value="Change Password" />
		    </c:otherwise>
		</c:choose>
	    </stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
