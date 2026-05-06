<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepositsPost.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="deposit"/> <stripes:label for="posting"/> 
	    <stripes:label for="maint"/>
	</h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table align="center" border="1" cellspacing="1" cellpadding="1" height="3" width="40%">
	<tr>
	    <th class="header" height="19" colspan="8">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="maint"/>
		    <stripes:label for="selCriteria"/>
		</font>
	    </th>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="Checks Source"/></font></TH>
		<td>
		    <select size="1" name="acctentrySelector.chexSource">
			<c:if test='${acctentrySelector.chexSource=="RDC"}'>
			    <option selected value="RDC">Remote Deposits</option>
			    <option value="LOCKBOX">Lock Box</option>
			</c:if>
			<c:if test='${acctentrySelector.chexSource=="LOCKBOX"}'>
			    <option value="RDC">Remote Deposits</option>
			    <option selected value="LOCKBOX">Lock Box</option>
			</c:if>
		    </select>
		</td>
	</tr>
    </table>
    </div>
<c:if test='${acctentrySelector.detail_count!=0}'>
<c:if test='${acctentrySelector.actionFlag!=""}'>
<%--													--%>
<%--	This is the table of selected data. Appears after user is has made the the selection and clicks	--%>
<%--	on the view button. The MODIFY link appears each row of data which to click to modify data.	--%>
<%--													--%>
<p align="center"></p>
	<stripes:submit name="View" value="View"/>
<br/>
<br/>
<hr>
<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${acctentrySelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><fmt:formatNumber value="${acctentrySelector.detailAmount}" type="currency"/></b>
	    </td>
	</tr>
</table>
</div>

	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${acctentrySelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${acctentrySelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${acctentrySelector.detail_count}"/>
	    <c:out value=" rows "/>
	    <c:if test='${acctentrySelector.detail_count>acctentrySelector.rowsDisplayed}'>
		<c:if test='${acctentrySelector.rowEnd!=acctentrySelector.detail_count}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${acctentrySelector.detail_count<=acctentrySelector.rowsDispStr}'>
		    <stripes:text name="acctentrySelector.detail_count" size="4" maxlength="4"
			value="${acctentrySelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${acctentrySelector.detail_count>acctentrySelector.rowsDispStr}'>
		<c:if test='${acctentrySelector.rowEnd!=acctentrySelector.detail_count}'>
		    <stripes:text name="acctentrySelector.rowsDispStr" size="4" maxlength="4"
			value="${acctentrySelector.rowsDispStr}"/>
		</c:if>
		</c:if>
		<c:if test='${acctentrySelector.rowStart!=0}'>
		<c:if test='${acctentrySelector.rowEnd!=acctentrySelector.detail_count}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		</c:if>
		<c:if test='${acctentrySelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${acctentrySelector.detail_count>acctentrySelector.rowsDispStr}'>
		<c:if test='${acctentrySelector.rowEnd==acctentrySelector.detail_count}'>
		    <stripes:text name="acctentrySelector.rowsDispStr" size="4" maxlength="4"
			value="${acctentrySelector.rowsDispStr}"/>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		</c:if>
	    </c:if>
	    <c:if test='${acctentrySelector.rowStart>=1}'>
	    <c:if test='${acctentrySelector.rowsDisplayed>=acctentrySelector.detail_count}'>
	    <c:if test='${acctentrySelector.rowEnd>=acctentrySelector.detail_count}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
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
		<font size=2><stripes:label for="source"/></font>
	    </th>
	    <th align='center' width='15%' height=15 colspan=1>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2>Check <stripes:label for="sequence"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='left' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th align='left' width='25%' height=15 colspan=1>
		<font size=2><stripes:label for="debit"/> Name</font>
	    </th>
	    <th align='left' width='25%' height=15 colspan=1>
		<font size=2><stripes:label for="credit"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="debitvaluedate"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="creditvaluedate"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="count"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="timestamp"/></font>
	    </th>
	</tr>
	<c:forEach items="${acctentrySelector.postingRows}" var="AcctentryDetail" varStatus="idx0" end="${acctentrySelector.rowEnd-1}" 
			begin="${acctentrySelector.rowStart}">
	  <tr>
	    <td align="center" width='2%' height='19'>
	      <c:out value="${idx0.index+1}"/>
	    </td>
	    <td align="center" width='7%' height='19'>
	      <c:out value="${AcctentryDetail.postingBusinessdate}"/>
	    </td>
	    <td align="center" width='8%' height='19'>
	      <c:out value="${AcctentryDetail.postingApplPrefix}"/>
	    </td>
	    <td style="text-align:right" width='15%' height='19'><b>
	      <c:out value="${AcctentryDetail.postingBranch}"/></b>
	    </td>
	    <td style="text-align:right" width='6%' height='19' >
	      <c:out value="${AcctentryDetail.postingSeqNum}"/>
	    </td>
	    <td style="text-align:right" width='8%' height='19'><b>
		<fmt:formatNumber value="${AcctentryDetail.postingAmount}" type="currency"/></b>
	    </td>
	    <td  align='center' width='7%' height='19'>
	      <c:out value="${AcctentryDetail.postingCurrency}"/>
	    </td>
	    <td  align='left' width='16%' height='19'>
	      <c:out value="${AcctentryDetail.postingDebitAcct}"/>
	    </td>
	    <td  align='center' width='7%' height='19'>
	      <c:out value="${AcctentryDetail.postingCreditAcct}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${AcctentryDetail.postingDebitValueDate}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${AcctentryDetail.postingCreditValueDate}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${AcctentryDetail.postingTotalCredits}"/>
	    </td>
	    <td align="center" width='5%' height='19'>
	      <c:out value="${AcctentryDetail.postingTimeStamp}"/>
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
		<b><c:out value="${acctentrySelector.detail_count}"/></b>
	    </td>
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><fmt:formatNumber value="${acctentrySelector.detailAmount}" type="currency"/></b>
	    </td>
	</tr>
</table>
</div>
</c:if>
</c:if>

<c:if test='${acctentrySelector.detail_count!=0}'>
</c:if>

<%--										--%>
<%--	This is the submit buttons area to take the next step.			--%>
<%--	Any errors will appear between the data table and the buttons.		--%>
<%--										--%>
     <center>
	<hr>
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
	<c:if test='${acctentrySelector.actionFlag!=""}'>
	<c:if test="${acctentrySelector.detail_count!=0}">
		<stripes:submit name="GeneratePosting" value="Extract Data"/>
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
