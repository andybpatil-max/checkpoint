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
		<stripes:label for="account"/> <stripes:label for="mapping"/>
		<stripes:label for="inquiry"/>
	    </h1>
	 </c:if>
	<c:if test='${acctSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="account"/> <stripes:label for="mapping"/>
		<stripes:label for="maint"/>
	    </h1>
	</c:if>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>

<div id="detail">
<table colspan='2' width='80%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <c:if test='${acctSelector.accessFlag=="inq"}'>
	    	<th class="header" colspan="2">
		    <stripes:label for="account"/> <stripes:label for="mapping"/>
		    <stripes:label for="maint"/>
		</th>
	    </c:if>
	    <c:if test='${acctSelector.accessFlag!="inq"}'>
		<c:if test='${acctSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="mapping"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="mapping"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="mapping"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="mapping"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${acctSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="mapping"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='45%' height='19'><b>
		<stripes:label for="caseNum"/></b></th>
	<td align='left' width=10% height='19'>
	    <c:if test='${acctSelector.actionFlag=="New"}'>
		<c:if test='${depsAcctDetail.deps_account==""}'>
		    <select size="1" name="depsAcctDetail.deps_account">
			<option selected>ALL</option>
			    <c:forEach var="acct" items="${acctSelector.debtorList}">
				<option value='<c:out value="${acct.key}"/>'> 
				    <c:out value="${acct.value}"/> </option>
			    </c:forEach>
		    </select>
		</c:if>
		<c:if test='${depsAcctDetail.deps_account!=""}'>
		    <stripes:text name='depsAcctDetail.deps_account' value='${depsAcctDetail.deps_account}' 
				size='22' readonly='true' />
		</c:if>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag!="New"}'>
		<stripes:text name='depsAcctDetail.deps_account' value='${depsAcctDetail.deps_account}'
			      size='22' readonly='true' />
	    </c:if>
	</td>
	</tr>

	<tr>
	<c:if test='${acctSelector.actionFlag!="New"}'>
	    <th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="client"/> <stripes:label for="name"/></th>
	    <td align='left' height='15'>
		<stripes:text name='depsAcctDetail.deps_payee' value='${depsAcctDetail.deps_payee}'
			      size='35' readonly='true' />
	    </td>
	</c:if>

	<c:if test='${acctSelector.actionFlag=="New"}'>
	<c:if test='${depsAcctDetail.deps_account!=""}'>
	    <th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="client"/> <stripes:label for="name"/></th>
	    <td align='left' height='15'>
		    <stripes:text name='depsAcctDetail.deps_payee'
			value='${depsAcctDetail.deps_payee}' size='22' readonly='true' />
	    </td>
	</c:if>
	</c:if>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="aba"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='depsAcctDetail.deps_checkaba'
			value='${depsAcctDetail.deps_checkaba}' size='22' />
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='depsAcctDetail.deps_checkaba'
			value='${depsAcctDetail.deps_checkaba}' size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='depsAcctDetail.deps_checkaba'
			value='${depsAcctDetail.deps_checkaba}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="account"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${acctSelector.actionFlag=="Modify"}'>
		    <stripes:text name='depsAcctDetail.deps_checkaccount'
			value='${depsAcctDetail.deps_checkaccount}' size='22' />
		</c:when>
		<c:when test='${acctSelector.actionFlag=="New"}'>
		    <stripes:text name='depsAcctDetail.deps_checkaccount'
			value='${depsAcctDetail.deps_checkaccount}' size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='depsAcctDetail.deps_checkaccount'
			value='${depsAcctDetail.deps_checkaccount}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="comments"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:text name='depsAcctDetail.deps_comments'
			value='${depsAcctDetail.deps_comments}' size='80' maxlength='255' />
	    </c:when>
	    <c:when test='${acctSelector.actionFlag=="New"}'>
		<stripes:text name='depsAcctDetail.deps_comments'
			value='${depsAcctDetail.deps_comments}' size='80' maxlength='255' />
	    </c:when>
	    <c:otherwise>
		<stripes:text name='depsAcctDetail.deps_comments'
			value='${depsAcctDetail.deps_comments}' size='80' readonly='true'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>
</table>
</div> <%-- detail --%>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value='${acctSelector.actionFlag}'/>							--%>
<%--													--%>
	
    <center>
	<stripes:errors />
	<c:if test='${acctSelector.actionFlag!="inq"}'>
	    <c:if test='${acctSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Update"}'>
		<stripes:submit name='Confirm' value="Confirm Update Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Add"}'>
		<stripes:submit name='Confirm' value="Confirm Add Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="Delete"}'>
		<stripes:submit name='DeleteConfirm' value="Confirm Delete Account"/>
	    </c:if>
	    <c:if test='${acctSelector.actionFlag=="New"}'>
		<stripes:submit name='Add' value="Add Mapping"/>
	    </c:if>
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
