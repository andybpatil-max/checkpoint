<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepositsPayer.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${payerSelector.accessFlag=="inq"}'>
	    <h1>
		<c:out value='${payerSelector.payerSource}'/>
		<stripes:label for="payer"/> <stripes:label for="data"/>
		<stripes:label for="inquiry"/>
	    </h1>
	 </c:if>
	<c:if test='${payerSelector.accessFlag!="inq"}'>
	    <h1>
		<c:out value='${payerSelector.payerSource}'/>
		<stripes:label for="payer"/> <stripes:label for="data"/>
		<stripes:label for="maint"/>
	    </h1>
	</c:if>

<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>

	<div id="detail">
	<table align="center" border="1" height="3" width="60%">
	    <c:if test='${payerSelector.accessFlag=="inq"}'>
		<tr>
		    <th class="header" height="19" colspan="3">
			<font size="3">
			    <stripes:label for="payer"/> <stripes:label for="data"/>
			    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${payerSelector.accessFlag!="inq"}'>
		<tr>
		    <th class="header" height="19" colspan="3">
			<font size="3">
			    <stripes:label for="payer"/> <stripes:label for="data"/>
			    <stripes:label for="maint"/> <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
		<tr>
		    <th align=center height="19" colspan="1"></TH>
		    <th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		    <th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="payer"/> <stripes:label for="aba"/></font></TH>
		    <td>
			<select size="1" name="payerSelector.payerAbaFrom">
<%--			    <c:if test='${payerSelector.actionFlag==""}'>	--%>
				<option selected>ALL</option>
				<c:forEach var="abas" items="${payerSelector.payerAbaList}">
				    <c:if test='${payerSelector.payerAbaFrom==abas}'>
					<option selected><c:out value="${payerSelector.payerAbaFrom}"/></option>
				    </c:if>
				    <c:if test='${payerSelector.payerAbaFrom!=abas}'>
					<option> <c:out value="${abas}"/> </option>
				    </c:if>
				</c:forEach>
<%--			    </c:if>
			    <c:if test='${payerSelector.actionFlag!=""}'>
				<option selected><c:out value="${payerSelector.payerAbaFrom}"/></option>
			    </c:if>
--%>
			</select>
		    </td>
		    <td>
			<select size="1" name="payerSelector.payerAbaTo">
				<option selected>NONE</option>
				<c:forEach var="abas" items="${payerSelector.payerAbaList}">
				    <c:if test='${payerSelector.payerAbaTo==abas}'>
					<option selected><c:out value="${payerSelector.payerAbaTo}"/></option>
				    </c:if>
				    <c:if test='${payerSelector.payerAbaTo!=abas}'>
					<option> <c:out value="${abas}"/> </option>
				    </c:if>
				</c:forEach>
<%--
			    <c:if test='${payerSelector.actionFlag==""}'>
			    </c:if>
			    <c:if test='${payerSelector.actionFlag!=""}'>
				<option selected><c:out value="${payerSelector.payerAbaTo}"/></option>
			    </c:if>
--%>
			</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="payer"/> <stripes:label for="account"/></font></TH>
		    <td>
			<select size="1" name="payerSelector.payerAcctFrom">
				<option selected>ALL</option>
				<c:forEach var="accts" items="${payerSelector.payerAcctList}">
				    <c:if test='${payerSelector.payerAcctFrom==accts}'>
					<option selected><c:out value="${payerSelector.payerAcctFrom}"/></option>
				    </c:if>
				    <c:if test='${payerSelector.payerAcctFrom!=accts}'>
					<option> <c:out value="${accts}"/> </option>
				    </c:if>
				</c:forEach>
<%--
			    <c:if test='${payerSelector.actionFlag==""}'>
			    </c:if>
			    <c:if test='${payerSelector.actionFlag!=""}'>
				<option selected><c:out value="${payerSelector.payerAcctFrom}"/></option>
			    </c:if>
--%>
			</select>
		    </td>
		    <td>
			<select size="1" name="payerSelector.payerAcctTo">
				<option selected>NONE</option>
				<c:forEach var="accts" items="${payerSelector.payerAcctList}">
				    <c:if test='${payerSelector.payerAcctTo==accts}'>
					<option selected><c:out value="${payerSelector.payerAcctTo}"/></option>
				    </c:if>
				    <c:if test='${payerSelector.payerAcctTo!=accts}'>
					<option> <c:out value="${accts}"/> </option>
				    </c:if>
				</c:forEach>
<%--
			    <c:if test='${payerSelector.actionFlag==""}'>
			    </c:if>
			    <c:if test='${payerSelector.actionFlag!=""}'>
				<option selected><c:out value="${payerSelector.payerAcctTo}"/></option>
			    </c:if>
--%>
			</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="payer"/> <stripes:label for="name"/></font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="payerSelector.payerName" maxlength="50" 
			    value="${payerSelector.payerName}"/>
		    </td>
		    <td>
		    </td>
		</tr>
	</table>
	</div>

    <c:if test='${payerSelector.rowCount!=0}'>
    <c:if test='${payerSelector.actionFlag!=""}'>
	<center>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${payerSelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Account"/>
	    </c:if>
	</center>
	<hr>
	<br/>
	<div id="totals">
	    <table align='center'>
		<tr bgcolor=bluegreen colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><b><c:out value="${payerSelector.rowCount}"/></b></td>
		</tr>
	    </table>
	</div>

	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${payerSelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${payerSelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${payerSelector.rowCount}"/>
	    <c:out value=" rows "/>
	    <c:if test='${payerSelector.rowCount>payerSelector.rowsDisplayed}'>
		<c:if test='${payerSelector.rowEnd!=payerSelector.rowCount}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${payerSelector.rowCount<=payerSelector.rowsDispStr}'>
		    <stripes:text name="payerSelector.rowCount" size="4" maxlength="4"
			value="${payerSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${payerSelector.rowCount>payerSelector.rowsDispStr}'>
		    <stripes:text name="payerSelector.rowsDispStr" size="4" maxlength="4"
			value="${payerSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${payerSelector.rowStart!=0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${payerSelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
	    </c:if>
	</h3>
	<br/>

	<p align="center"></p>
	<div id="selres">
	<table class="sortable" colspan='8' width='100%'>
	<tr bgcolor=bluegreen>
		<th align='center' width='2%' height=15 colspan=1>
		    <font size=2><stripes:label for="recNum"/></font>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="aba"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="accountNum"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="payer"/> <stripes:label for="name"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="country"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="source"/>
		</th>
		<c:if test='${payerSelector.accessFlag!="inq"}'>
		    <th align=center width='10%' height=15 colspan=2>
			<stripes:label for="action"/>
		    </th>
		</c:if>
	    </tr>

	<c:forEach items="${payerSelector.payerRows}" var="payerDetail" varStatus="idx0" 
	    end="${payerSelector.rowEnd-1}" begin="${payerSelector.rowStart}">
	    <tr>
	    	<td align="center" bgcolor='lightyellow' width='2%' height='19'>
		    <c:out value="${idx0.index+1}"/>
		</td>
		<td align="center" width='10%' height='19'><b>
		    <c:out value="${payerDetail.payeraba}"/></b>
		</td>
		<td align="left" width='10%' height='19'>
		    <c:out value="${payerDetail.payeracct}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${payerDetail.payername}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${payerDetail.payercountry}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${payerDetail.payersource}"/>
		</td>

		<c:if test='${payerSelector.accessFlag!="inq"}'>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/DepositsPayer.action" event="Modify">
			    Delete
			    <stripes:param name="action" value="del"/>
			    <stripes:param name="payerAcct" value="${payerDetail.payeracct}"/>
			    <stripes:param name="payerAba" value="${payerDetail.payeraba}"/>
			</stripes:link>
		    </td>
		    <td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/DepositsPayer.action" event="Modify">
			    Modify
			    <stripes:param name="action" value="mod"/>
			    <stripes:param name="payerAcct" value="${payerDetail.payeracct}"/>
			    <stripes:param name="payerAba" value="${payerDetail.payeraba}"/>
			</stripes:link>
		    </td>
		</c:if>
<%--
		<c:if test='${payerSelector.accessFlag=="inq"}'>
		    <td align="center" width='5%' height='19' colspan='2'>
			<stripes:link href="/econtroller/deposits/actions/DepositsPayer.action" event="Modify">
			    Detail
			    <stripes:param name="action" value="inq"/>
			    <stripes:param name="payerAcct" value="${payerDetail.payeracct}"/>
			    <stripes:param name="payerAba" value="${payerDetail.payeraba}"/>
			</stripes:link>
		    </td>
		</c:if>
--%>
<%----%>
	    </tr>
	</c:forEach>
	</table>
	</div>

	<div id="totals">
	<table align='center'>
	    <tr bgcolor=bluegreen colspan=2>
		<th><font size=2><stripes:label for="rowcount"/></font></th>
		<td>
		    <c:out value="${payerSelector.rowCount}"/></b>
		</td>
	    </tr>
	</table>
	</div>
    </c:if>
    </c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<hr>
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
	<c:if test='${payerSelector.accessFlag!="inq"}'>
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
