<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Chex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
<c:out value="chexinq.jsp"/>
--%>
	<fmt:setLocale value="en_US"/>
	<h1>
		<stripes:label for="check"/> <stripes:label for="data"/> <stripes:label for="inquiry"/>
	</h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table align="center" border="1" cellspacing="1" cellpadding="1" height="3" width="60%">
	<c:if test='${chexSelector.accessFlag=="inq"}'>
	    <tr>
		<th class="header" align=center height="19" colspan="3" width="60%">
		    <font size="3">
			<stripes:label for="check"/> <stripes:label for="data"/> 
			<stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		    </font>
		</TH>
	    </tr>
	</c:if>
	<c:if test='${chexSelector.accessFlag!="inq"}'>
	    <tr>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			<stripes:label for="checks"/> <stripes:label for="data"/>
			<stripes:label for="maint"/> <stripes:label for="selCriteria"/>
		    </font>
		</TH>
	    </tr>
	</c:if>
	    <tr>
		<th align=center height="19" colspan="1"></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="account"/></font></TH>
		<td>
		    <select size="1" name="chexSelector.ch_from_account">
			<option selected>ALL</option>
			<c:forEach items="${chexSelector.acctList}" var="acct">
			    <c:if test="${chexSelector.ch_from_account==acct}">
				<option selected><c:out value="${acct}"/></option>
			    </c:if>
			    <c:if  test="${chexSelector.ch_from_account!=acct}">
				<option> <c:out value="${acct}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="chexSelector.ch_to_account">
			<option selected>NONE</option>
			<c:forEach items="${chexSelector.acctList}" var="acct">
			    <c:if test="${chexSelector.ch_to_account==acct}">
				<option selected><c:out value="${acct}"/></option>
			    </c:if>
			    <c:if test="${chexSelector.ch_to_account!=acct}">
				<option> <c:out value="${acct}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3">
			<stripes:label for="check"/> <stripes:label for="number"/>
		    </font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="chexSelector.ch_from_check"  maxlength="15"
			value="${chexSelector.ch_from_check}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="chexSelector.ch_to_check"  maxlength="15"
			value="${chexSelector.ch_to_check}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="chexSelector.ch_from_amount"  maxlength="15"
			value="${chexSelector.ch_from_amount}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="chexSelector.ch_to_amount"  maxlength="15"
			value="${chexSelector.ch_to_amount}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="chexSelector.ch_currency"  maxlength="3"
			value="${chexSelector.ch_currency}"/>
		</td>
		<td>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
		    <select size="1" name="chexSelector.ch_check_status">
			<option value="ALL">All</option>
			<c:if test='${chexSelector.ch_check_status!=("ALL" || "")}'>
			    <option selected><c:out value="${chexSelector.ch_check_status}"/></option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="A"}'>
			    <option value="A">To be Authorized</option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="E"}'>
			    <option value="E">Error</option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="F"}'>
			    <option value="F">Fraud Alert</option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="R"}'>
			    <option value="R">Rejected</option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="S"}'>
			    <option value="S">Payment Stopped</option>
			</c:if>
			<c:if test='${chexSelector.ch_check_status!="Z"}'>
			    <option value="Z">Complete</option>
			</c:if>
		    </select>
	    	</td>
		<td>
		</td>
	    </tr>
	</table>
	</div>
<%--													--%>
<%--	This is the table of selected data. Appears after user is has made the the selection and clicks	--%>
<%--	on the view button. The MODIFY link appears each row of data which to click to modify data.	--%>
<%--													--%>
<c:if test="${chexSelector.detail_count!=0}">
<c:if test='${chexSelector.actionFlag!=""}'>
<p align="center"></p>
	<stripes:submit name="ViewInq" value="View"/>
<hr>
<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detailAmount}"/></b>
	    </td>
	</tr>
</table>
</div>
	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${chexSelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${chexSelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${chexSelector.detail_count}"/>
	    <c:out value=" rows "/>
	    <c:if test='${chexSelector.detail_count>chexSelector.rowsDisplayed}'>
		<c:if test='${chexSelector.rowEnd!=chexSelector.detail_count}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${chexSelector.detail_count<=chexSelector.rowsDispStr}'>
		    <stripes:text name="chexSelector.detail_count" size="4" maxlength="4"
			value="${chexSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${chexSelector.detail_count>chexSelector.rowsDispStr}'>
		    <stripes:text name="chexSelector.rowsDispStr" size="4" maxlength="4"
			value="${chexSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${chexSelector.rowStart!=0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${chexSelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
	    </c:if>
	</h3>
	<br/>

<table class="sortable" colspan='12' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
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
		<font size=2>
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th style="text-align:right" width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='left' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="issueDate"/></font>
	    </th>
	    <th align='left' width='25%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="return"/> <stripes:label for="status"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="reject"/> <stripes:label for="reason"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="action"/></font>
	    </th>
	</tr>
<%--	
	<c:out value="${chexSelector.rowStart}"/>
	<c:out value="${chexSelector.rowEnd}"/>
	<c:out value="${chexSelector.rowsDisplayed}"/>
--%>
	<c:forEach items="${chexSelector.checkrows}" var="ChexDetail" varStatus="idx0" 
	    end="${chexSelector.rowEnd-1}" begin="${chexSelector.rowStart}">
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
	    <td align="center" width='15%' height='19'><b>
	      <c:out value="${ChexDetail.chex_account_num}"/></b>
	    </td>
<%--
	    <td align="center" width='15%' height='19'><b>
	      <c:out value="${ChexDetail.chex_orig_account_num}"/></b>
	    </td>
	      <fmt:formatNumber value="${ChexDetail.chex_check_amount}" type="currency"/></b>
--%>
	    <td style="text-align:right" width='6%' height='19'>
	      <c:out value="${ChexDetail.chex_check_num}"/>
	    </td>
	    <td align="center" width='6%' height='19'>
	      <c:out value="${ChexDetail.chex_check_currency}"/>
	    </td>
	    <td style="text-align:right" width='8%' height='19'><b>
	      <fmt:formatNumber value="${ChexDetail.chex_check_amount}" type="currency"/></b>
	    </td>
	    <td  align='center' width='7%' height='19'>
	      <c:out value="${ChexDetail.chex_issue_date_disp}"/>
	    </td>
	    <td  align='left' width='30%' height='19'>
	      <c:out value="${ChexDetail.chex_payee}"/>
	    </td>
	    <td  align='center' width='5%' height='19'>
	      <c:out value="${ChexDetail.chex_return_status}"/>
	    </td>
	    <td  align='center' width='5%' height='19'>
	      <c:out value="${ChexDetail.chex_reason_codes}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${ChexDetail.chex_check_status}"/>
	    </td>
	    <td align="center" width='10%' height='19'>
		<stripes:link href="/econtroller/inclear/actions/Chex.action" event="Details">
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
		<b><c:out value="${chexSelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detailAmount}"/></b>
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
	<stripes:submit name="ViewInq" value="View"/>
<%--
	<c:if test='${chexSelector.detail_count!=0}'>
		<hr>
		<%@ include file="rejectreasons.jsp" %>
	</c:if>
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
