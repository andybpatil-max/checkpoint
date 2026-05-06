<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Stoppay.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="stopPay"/> <stripes:label for="maint"/></h1>
    <div id="detail">
    <table align="center" border="1" height="3" width="55%">
	<tr bgcolor=turquoise>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="stopPay"/> <stripes:label for="maint"/>
		    <stripes:label for="selCriteria"/>
		</font>
	    </th>
	</tr>
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
		    <select size="1" name="stopSelector.sp_from_acct">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_from_acct}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach items="${stopSelector.acctList}" var="acct">
			   <option> <c:out value="${acct}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="stopSelector.sp_to_acct">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_to_acct}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach items="${stopSelector.acctList}" var="acct">
			   <option> <c:out value="${acct}"/> </option>
			</c:forEach>
		    </select>
		</td>
	<tr>
	</tr>
		<th align=center height="19" colspan="1">
			<font size="3">
			<stripes:label for="check"/> <stripes:label for="number"/>
			</font>
		</th>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_from_check" value="${stopSelector.sp_from_check}"
		    		  maxlength="15"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_to_check" value="${stopSelector.sp_to_check}"
		    		  maxlength="15"/>
		</td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_currency" value="${stopSelector.sp_currency}" maxlength="3"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_check_amount" value="${stopSelector.sp_check_amount}"
				maxlength="15"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="swiftRef"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_swift_ref" value="${stopSelector.sp_swift_ref}"
				maxlength="15"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="issueDate"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_issue_date" value="${stopSelector.sp_issue_date}"
		    		maxlength="15"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
		    <select size="1" name="stopSelector.sp_status">
			<option value="M">Matched</option>
			<option value="U">Unmatched</option>
			<option value="E">Error</option>
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected value="ALL">All of the above</option>
		    	</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_status}"/></option>
			    <option value="ALL">All of the above</option>
			</c:if>
		    </select>
		</td>
		<td></td>
	</tr>
    </table>
    </div>
<c:if test="${stopSelector.rowCount!=0}">
<c:if test='${stopSelector.actionFlag!=""}'>
     <center>
	<stripes:submit name="View" value="View"/>
	<stripes:submit name="New"  value="New Stoppay"/>
	<c:if test='${stopSelector.rowCount!=0}'>
	    <stripes:submit name='ExtractStoppay' value="Extract Stoppay Data"/>  
	</c:if>
    </center>
    <hr>
    <br/>
<div id="totals" align=center>
    <table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${stopSelector.rowCount}"/></b>
	    </td>
	</tr>
    </table>
</div>

	<p align="center"></p>
	<h3>
	<c:out value="Displaying "/>
	<c:out value="${stopSelector.rowStart+1}"/>
	<c:out value=" to "/>
	<c:out value="${stopSelector.rowEnd}"/>
	<c:out value=" of "/>
	<c:out value="${stopSelector.rowCount}"/>
	<c:out value=" rows "/>
	<c:if test='${stopSelector.rowCount>stopSelector.rowsDisplayed}'>
	    <c:if test='${stopSelector.rowEnd!=stopSelector.rowCount}'>
		<stripes:submit name="Next" value="Next"/>
	    </c:if>
	    <c:if test='${stopSelector.rowCount<=stopSelector.rowsDispStr}'>
		<stripes:text name="stopSelector.rowCount" size="4" maxlength="4"
			value="${stopSelector.rowsDispStr}"/>
	    </c:if>
	    <c:if test='${stopSelector.rowCount>stopSelector.rowsDispStr}'>
	    <c:if test='${stopSelector.rowEnd!=stopSelector.rowCount}'>
		<stripes:text name="stopSelector.rowsDispStr" size="4" maxlength="4"
			value="${stopSelector.rowsDispStr}"/>
	    </c:if>
	    </c:if>
	    <c:if test='${stopSelector.rowStart!=0}'>
	    <c:if test='${stopSelector.rowEnd!=stopSelector.rowCount}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	    <c:if test='${stopSelector.rowStart==0}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    <c:if test='${stopSelector.rowCount>stopSelector.rowsDispStr}'>
	    <c:if test='${stopSelector.rowEnd==stopSelector.rowCount}'>
		<stripes:text name="stopSelector.rowsDispStr" size="4" maxlength="4"
			value="${stopSelector.rowsDispStr}"/>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	</c:if>
	<c:if test='${stopSelector.rowStart>=1}'>
	<c:if test='${stopSelector.rowsDisplayed>=stopSelector.rowCount}'>
	<c:if test='${stopSelector.rowEnd>=stopSelector.rowCount}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	</c:if>
	</c:if>
	</h3>
	<br></br>

<div id="selres">
<table class="sortable"	colspan='9'  width='90%' align='center' border='1' cellspacing="1" cellpadding="1">
<%--
	<tr bgcolor=bluegreen>
	    <th class="header" align=center height=19 colspan=8>
		<font size=3 color=blue>
		    <stripes:label for="stopPay"/> <stripes:label for="maint"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
--%>
	<tr bgcolor=bluegreen>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="check"/> <stripes:label for="number"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="swiftRef"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan="1">
		<font size=2><stripes:label for="issueDate"/></font>
	    </th>
	    <th align='center' width='8%' height=15 colspan=1>
		<font size='2'><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='4%' height=15 colspan=2>
		<font size='2'> Action</font>
	    </th>
	</tr>
<%--<c:forEach items="${stopSelector.stoppayrows}" var="StoppayDetail"> --%>
<c:forEach items="${stopSelector.stoppayrows}" var="StoppayDetail" varStatus="idx0" 
	   	end="${stopSelector.rowEnd-1}" begin="${stopSelector.rowStart}">
  <tr>
    <td align="center" width='2%' height='19'>
	<c:out value="${idx0.index+1}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${StoppayDetail.stopay_account_num}"/></b>
    </td>
    <td style="text-align:right" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_check_num}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_currency}"/>
    </td>
    <td style="text-align:right" width='10%' height='19'>
      <b><fmt:formatNumber value="${StoppayDetail.stopay_check_amount}" type="currency"/>
    </td>
    <td  align='center' width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_swift_ref}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_issue_date}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_status}"/>
    </td>
    <td align="center" width='7%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/Stoppay.action" event="Modify">
	    Delete
	    <stripes:param name="action" value="Delete"/>
	    <stripes:param name="acctNum" value="${StoppayDetail.stopay_account_num}"/>
	    <stripes:param name="checkNum" value="${StoppayDetail.stopay_check_num}"/>
	</stripes:link>
    </td>
    <td align="center" width='7%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/Stoppay.action" event="Modify">
	    Modify
	    <stripes:param name="action" value="Modify"/>
	    <stripes:param name="acctNum" value="${StoppayDetail.stopay_account_num}"/>
	    <stripes:param name="checkNum" value="${StoppayDetail.stopay_check_num}"/>
	</stripes:link>
    </td>
  </tr>
<%--
  <tr>
    <td align="left" width='4%' height='19' colspan='3'>
      <c:out value="${StoppayDetail.stopay_payee}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_payee_addr1}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_payee_addr2}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_payee_addr3}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${StoppayDetail.stopay_value_date}"/>
    </td>
  </tr>
--%>
</c:forEach>
</table>
</div>

<div id="totals" align=center>
<table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${stopSelector.rowCount}"/></b>
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
<%--	<c:out value="${stopSelector.actionFlag}"/>							--%>
<%--	<c:out value="${stopSelector.accessFlag}"/>							--%>
<%--	<c:out value="${stopSelector.accessFlag}"/>							--%>

     <center>
	<hr>
	<stripes:errors/>
	<stripes:submit name="View" value="View"/>
	<stripes:submit name="New"  value="New Stoppay"/>
	<c:if test='${stopSelector.rowCount!=0}'>
	    <stripes:submit name='ExtractStoppay' value="Extract Stoppay Data"/>  
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
