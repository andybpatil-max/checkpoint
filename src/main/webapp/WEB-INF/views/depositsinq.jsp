<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/Deposits.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="deposit"/> <stripes:label for="data"/> 
	    <stripes:label for="inquiry"/>
	</h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table align="center" border="1" cellspacing="1" cellpadding="1" height="3" width="40%">
	<tr>
	    <th class="header" height="19" colspan="6">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selCriteria"/>
		</font>
	    </th>
	</tr>
	<tr>
		<th align=center height="19" colspan="1"></th>
		<th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		<th class="header" height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="account"/></font></TH>
		<td>
		    <select size="1" name="depsSelector.ch_from_account">
			<c:if test='${depsSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_from_account==''}">
			    <option selected>ALL</option>
			</c:if>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_from_account!=''}">
			    <option>ALL</option>
			</c:if>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_from_account!=''}">
			    <option selected><c:out value="${depsSelector.ch_from_account}"/></option>
			</c:if>
			</c:if>
			<c:forEach items="${depsSelector.payerAcctList}" var="acct">
			    <option> <c:out value="${acct}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="depsSelector.ch_to_account">
			<c:if test='${depsSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_to_account==''}">
			    <option selected>NONE</option>
			</c:if>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_to_account!=''}">
			    <option>NONE</option>
			</c:if>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			<c:if test="${depsSelector.ch_to_account!=''}">
			    <option selected><c:out value="${depsSelector.ch_to_account}"/></option>
			</c:if>
			</c:if>
			<c:forEach items="${depsSelector.payerAcctList}" var="acct">
			    <option> <c:out value="${acct}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3">Check <stripes:label for="number"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="depsSelector.ch_from_check"  maxlength="15"
				value="${depsSelector.ch_from_check}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="depsSelector.ch_to_check"  maxlength="15"
				value="${depsSelector.ch_to_check}"/>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="depsSelector.ch_from_amount"  maxlength="15"
			value="${depsSelector.ch_from_amount}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="depsSelector.ch_to_amount"  maxlength="15"
			value="${depsSelector.ch_to_amount}"/>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="payee"/></font></TH>
		<td align="center" width="10%" height="19">
		    <select size="1" name="depsSelector.ch_payee">
			<c:if test='${depsSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${depsSelector.actionFlag!=""}'>
			    <option selected><c:out value="${depsSelector.ch_payee}"/></option>
			</c:if>
			<c:forEach var="cred" items="${depsSelector.payeeList}">
			    <option value='<c:out value="${cred}"/>'> 
				<c:out value="${cred}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		</td>
	</tr>
	</tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
			<select size="1" name="depsSelector.ch_check_status">
				<option value="A">Invalid Payee Account</option>
				<option value="C">Batch Cancelled</option>
				<option value="E">Error</option>
				<option value="P">Posting Ready</option>
				<option value="W">Warehouse Ready</option>
			    <c:if test='${depsSelector.actionFlag==""}'>
				<option selected value="ALL">ALL</option>
			    </c:if>
			    <c:if test='${depsSelector.actionFlag!=""}'>
				<option selected><c:out value="${depsSelector.ch_check_status}"/></option>
			    </c:if>
			</select>
		</td>
		<td>
		</td>
    </table>
    </div>
<c:if test='${depsSelector.detail_count!=0}'>
<c:if test='${depsSelector.actionFlag!=""}'>
<%--													--%>
<%--	This is the table of selected data. Appears after user is has made the the selection and clicks	--%>
<%--	on the view button. The MODIFY link appears each row of data which to click to modify data.	--%>
<%--													--%>
<hr>
<p align="center"></p>
	<stripes:submit name="ViewInq" value="View"/>
<hr>
<br/>
<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${depsSelector.detailAmount}"/></b>
	    </td>
	</tr>
</table>
</div>
<%--
<c:out value="${depsSelector.rowStart}"/>
<c:out value="${depsSelector.rowEnd}"/>
<c:out value="${depsSelector.rowsDisplayed}"/>
<c:out value="${depsSelector.detail_count}"/>
--%>
	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${depsSelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${depsSelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${depsSelector.detail_count}"/>
	    <c:out value=" rows "/>
	    <c:if test='${depsSelector.detail_count>depsSelector.rowsDisplayed}'>
		<c:if test='${depsSelector.rowEnd!=depsSelector.detail_count}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${depsSelector.detail_count<=depsSelector.rowsDispStr}'>
		    <stripes:text name="depsSelector.detail_count" size="4" maxlength="4"
			value="${depsSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${depsSelector.detail_count>depsSelector.rowsDispStr}'>
		<c:if test='${depsSelector.rowEnd!=depsSelector.detail_count}'>
		    <stripes:text name="depsSelector.rowsDispStr" size="4" maxlength="4"
			value="${depsSelector.rowsDispStr}"/>
		</c:if>
		</c:if>
		<c:if test='${depsSelector.rowStart!=0}'>
		<c:if test='${depsSelector.rowEnd!=depsSelector.detail_count}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		</c:if>
		<c:if test='${depsSelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${depsSelector.detail_count>depsSelector.rowsDispStr}'>
		<c:if test='${depsSelector.rowEnd==depsSelector.detail_count}'>
		    <stripes:text name="depsSelector.rowsDispStr" size="4" maxlength="4"
			value="${depsSelector.rowsDispStr}"/>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		</c:if>
	    </c:if>
	    <c:if test='${depsSelector.rowStart>=1}'>
	    <c:if test='${depsSelector.rowsDisplayed>=depsSelector.detail_count}'>
	    <c:if test='${depsSelector.rowEnd>=depsSelector.detail_count}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	    </c:if>
	</h3>
	<br/>
<table class="sortable" colspan='12' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
<%--
	<tr>
	    <th align=center height=19 colspan='11'>
		<font size=3 color=blue>
		    <stripes:label for="deposit"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
    <table class="sortable">
--%>
	<tr>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="processDate"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="aba"/></font>
	    </th>
	    <th align='center' width='15%' height=15 colspan=1>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2>Check <stripes:label for="number"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='left' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="issueDate"/></font>
	    </th>
	    <th align='left' width='25%' height=15 colspan=1>
		<font size=2><stripes:label for="payer"/> Name</font>
	    </th>
	    <th align='left' width='25%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="modify"/></font>
	    </th>
	</tr>

	<c:forEach items="${depsSelector.checkrows}" var="ChexDetail" varStatus="idx0" end="${depsSelector.rowEnd-1}" 
			begin="${depsSelector.rowStart}">
	  <tr>
	    <td align="center" width='2%' height='19'>
	      <c:out value="${idx0.index+1}"/>
	    </td>
	    <td align="center" width='7%' height='19'>
	      <c:out value="${ChexDetail.chex_proc_date_disp}"/>
	    </td>
	    <td align="center" width='8%' height='19'>
	      <c:out value="${ChexDetail.chex_routing_transit}"/>
	    </td>
	    <td style="text-align:right" width='15%' height='19'><b>
	      <c:out value="${ChexDetail.chex_account_num}"/></b>
	    </td>
	    <td style="text-align:right" width='6%' height='19' >
	      <c:out value="${ChexDetail.chex_check_num}"/>
	    </td>
	    <td align="center" width='6%' height='19'>
	      <c:out value="${ChexDetail.chex_check_currency}"/>
	    </td>
	    <td style="text-align:right" width='8%' height='19'><b>
	      <fmt:formatNumber value="${ChexDetail.chex_check_amount}" type="currency"/></b>
	    </td>
	    <td align='center' width='7%' height='19'>
	      <c:out value="${ChexDetail.chex_issue_date_disp}"/>
	    </td>
	    <td style="text-align:left" width='16%' height='19'>
	      <c:out value="${ChexDetail.chexPayerName}"/>
	    </td>
	    <td style="text-align:left" width='10%' height='19'>
	      <c:out value="${ChexDetail.chex_payee}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${ChexDetail.chex_check_status}"/>
	    </td>
	    <td align="center" width='10%' height='19'>
		<stripes:link href="/econtroller/deposits/actions/Deposits.action" event="Details">
			Details
			    <stripes:param name="proc_date" value="${ChexDetail.chex_proc_date}"/>
			    <stripes:param name="account_number" value="${ChexDetail.chex_account_num}"/>
			    <stripes:param name="check_number" value="${ChexDetail.chex_check_num}"/>
			    <stripes:param name="check_unique_isn" value="${ChexDetail.chex_unique_isn}"/>
			    <stripes:param name="recIndex" value="${idx0.index}"/>
		</stripes:link>
	    </td>
	  </tr>
	</c:forEach>
</table>

<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${depsSelector.detailAmount}"/></b>
	    </td>
	</tr>
</table>
</div>
</c:if>
</c:if>

<%--										--%>
<%--	This is the submit buttons area to take the next step.			--%>
<%--	Any errors will appear between the data table and the buttons.		--%>
<%--										--%>
     <center>
	<hr>
	<stripes:errors />
	<stripes:submit name="ViewInq" value="View"/>
<%--
	<%@ include file="rejectreasons.jsp" %>
--%>
    </center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
