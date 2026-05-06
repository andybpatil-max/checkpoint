<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/mandate/actions/Mandate.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${mandateSelector.accessFlag=="inq"}'>
	    <h1><stripes:label for="mandate"/> <stripes:label for="inquiry"/></h1>
	</c:if>
	<c:if test='${mandateSelector.accessFlag!="inq"}'>
	    <h1><stripes:label for="mandate"/> <stripes:label for="maint"/></h1>
	</c:if>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table align="center" border="1" cellspacing="1" cellpadding="1" height="3" width="40%">
	<th class="header" align=center height="19" colspan="3">
		<c:if test='${mandateSelector.accessFlag=="inq"}'>
		    <font size="3">
			<stripes:label for="mandate"/> <stripes:label for="inquiry"/>
			<stripes:label for="selCriteria"/>
		    </font>
		</c:if>
		<c:if test='${mandateSelector.accessFlag!="inq"}'>
		    <font size="3">
			<stripes:label for="mandate.maintenance"/>
			<stripes:label for="selCriteria"/>
		    </font>
		</c:if>
	</TH>
	<tr>
		<th align=center height="19" colspan="1"></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.from"/></font></TH>
		<th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.to"/></font></TH>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.umrn"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.umrnFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.umrnFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.umrnList}" var="umrnfrom">
			    <option> <c:out value="${umrnfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.umrnTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.umrnTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.umrnList}" var="umrnTo">
			    <option> <c:out value="${umrnTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.date"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.dateFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.dateFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.mdateList}" var="datefrom">
			    <option> <c:out value="${datefrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.dateTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.dateTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.mdateList}" var="dateTo">
			    <option> <c:out value="${dateTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.sponsor"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.sponsorBankFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.sponsorBankFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.sponList}" var="sponsorBankfrom">
			    <option> <c:out value="${sponsorBankfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.sponsorBankTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.sponsorBankTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.sponList}" var="sponsorBankTo">
			    <option> <c:out value="${sponsorBankTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.legalacct"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.legalAcFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.legalAcFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.accList}" var="legalAcfrom">
			    <option> <c:out value="${legalAcfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.legalAcTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.legalAcTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.accList}" var="legalAcTo">
			    <option> <c:out value="${legalAcTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.acctwith"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.accountWithFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.accountWithFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.accwList}" var="accountWithfrom">
			    <option> <c:out value="${accountWithfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.accountWithTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.accountWithTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.accwList}" var="accountWithTo">
			    <option> <c:out value="${accountWithTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.courtesyamt"/></font></TH>
		<td>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.starting"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.fromPeriodFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.fromPeriodFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.frpeList}" var="fromPeriodfrom">
			    <option> <c:out value="${fromPeriodfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.fromPeriodTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.fromPeriodTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.frpeList}" var="fromPeriodTo">
			    <option> <c:out value="${fromPeriodTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.ending"/></font></TH>
		<td>
		    <select size="1" name="mandateSelector.toPeriodFrom">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.toPeriodFrom}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${mandateSelector.topeList}" var="toPeriodfrom">
			    <option> <c:out value="${toPeriodfrom}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="mandateSelector.toPeriodTo">
			<c:if test='${mandateSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${mandateSelector.actionFlag!=""}'>
			    <option selected><c:out value="${mandateSelector.toPeriodTo}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${mandateSelector.topeList}" var="toPeriodTo">
			    <option> <c:out value="${toPeriodTo}"/> </option>
			</c:forEach>
		    </select>
		</td>
	</tr>
<%--
	<tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="mandate.status"/></font></TH>
		<td>
		</td>
		<td>
		</td>
	</tr>
--%>
</table>
</div>
<c:if test='${mandateSelector.actionFlag!=""}'>
<%--													--%>
<%--	This is the table of selected data. Appears after user is has made the the selection and clicks	--%>
<%--	on the view button. The MODIFY link appears each row of data which to click to modify data.	--%>
<%--													--%>
<p align="center"></p>
	<stripes:submit name="View" value="View"/>
<br/>
<br/>
<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${mandateSelector.detail_count}"/></b>
	    </td>
<%--
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${mandateSelector.detailAmount}"/></b>
	    </td>
--%>
	</tr>
</table>
</div>

<%--
	<c:out value="${mandateSelector.rowStart}"/>
	<c:out value="${mandateSelector.rowEnd}"/>
	<c:out value="${mandateSelector.rowsDisplayed}"/>
--%>
	<h3>
	<c:out value="Displaying "/>
	<c:out value="${mandateSelector.rowStart+1}"/>
	<c:out value=" to "/>
	<c:out value="${mandateSelector.rowEnd}"/>
	<c:out value=" of "/>
	<c:out value="${mandateSelector.detail_count}"/>
	<c:out value=" rows "/>
	<c:if test='${mandateSelector.rowEnd!=mandateSelector.detail_count}'>
	    <stripes:submit name="Next" value="Next"/>
	</c:if>
	<c:if test='${mandateSelector.rowEnd==mandateSelector.detail_count}'>
	    <stripes:submit name="Next" value="Next" disabled="true"/>
	</c:if>
<%--
	<c:out value="${mandateSelector.rowsDisplayed}"/>
	<c:out value="${mandateSelector.rowsDispStr}"/>
--%>
	<stripes:text name="mandateSelector.rowsDispStr" size="4" maxlength="4"
		value="${mandateSelector.rowsDispStr}"/>
	<c:if test='${mandateSelector.rowStart!=0}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	<c:if test='${mandateSelector.rowStart==0}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	</h3>
	<br/>
<table class="sortable" colspan='8' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
	<tr>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.umrn"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.date"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.sponsor"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.legalacct"/></font>
	    </th>
	    <th align='right' width='15%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.acctwith"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="mandate.courtesyamt"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="details"/></font>
	    </th>
	</tr>
<%--	
	<c:out value="${mandateSelector.rowStart}"/>
	<c:out value="${mandateSelector.rowEnd}"/>
	<c:out value="${mandateSelector.rowsDisplayed}"/>
--%>
	<c:forEach items="${mandateSelector.mandateRows}" var="MandateDetail" varStatus="idx0" 
		   end="${mandateSelector.rowEnd-1}" begin="${mandateSelector.rowStart}">
	  <tr>
	    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
	      <c:out value="${idx0.index+1}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='7%' height='19'>
	      <c:out value="${MandateDetail.umrn}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
	      <c:out value="${MandateDetail.manDate}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='6%' height='19'><b>
	      <c:out value="${MandateDetail.sponsorBank}"/></b>
	    </td>
	    <td align="right" bgcolor='lightyellow' width='6%' height='19'>
	      <c:out value="${MandateDetail.legalAcNum}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='15%' height='19'>
	      <c:out value="${MandateDetail.accountWith}"/>
	    </td>
	    <td align="right" bgcolor='lightyellow' width='8%' height='19'><b>
	      <c:out value="${MandateDetail.courtesyAmount}"/></b>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
		<c:if test='${mandateSelector.accessFlag=="inq"}'>
		    <stripes:link href="/econtroller/mandate/actions/Mandate.action" event="Details">
			Details
			    <stripes:param name="recIndex" value="${idx0.index}"/>
		    </stripes:link>
		</c:if>
		<c:if test='${mandateSelector.accessFlag!="inq"}'>
		    <stripes:link href="/econtroller/mandate/actions/Mandate.action" event="Modify">
			Modify
			    <stripes:param name="procDate" value="${MandateDetail.procDate}"/>
			    <stripes:param name="imageId" value="${MandateDetail.imageId}"/>
			    <stripes:param name="recIndex" value="${idx0.index}"/>
		    </stripes:link>
		</c:if>
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
		<b><c:out value="${mandateSelector.detail_count}"/></b>
	    </td>
<%--
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${mandateSelector.detailAmount}"/></b>
	    </td>
--%>
	</tr>
</table>
</div>
</c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

     <center>
	<stripes:errors/>
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
