<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Posipay.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="posipay"/> <stripes:label for="maint"/></h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
    <div id="detail">
    <table align="center" border="1" height="3" width="55%">
	<tr bgcolor=turquoise>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="posipay"/>
		    <stripes:label for="maint"/> <stripes:label for="selCriteria"/>
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
		    <select size="1" name="posiSelector.pp_from_acct">
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			<c:if test='${posiSelector.pp_from_acct==""}'>
			    <option selected>ALL</option>
			</c:if>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			<c:if test='${posiSelector.pp_from_acct!=""}'>
			    <option>ALL</option>
			    <option selected><c:out value="${posiSelector.pp_from_acct}"/></option>
			</c:if>
			</c:if>
			<c:forEach items="${posiSelector.acctList}" var="acct">
			   <option> <c:out value="${acct}"/> </option>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="posiSelector.pp_to_acct">
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			<c:if test='${posiSelector.pp_from_acct==""}'>
			    <option selected>NONE</option>
			</c:if>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			<c:if test='${posiSelector.pp_to_acct!=""}'>
			    <option>NONE</option>
			    <option selected><c:out value="${posiSelector.pp_to_acct}"/></option>
			</c:if>
			</c:if>
			<c:forEach items="${posiSelector.acctList}" var="acct">
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
		    <stripes:text name="posiSelector.pp_from_check" value="${posiSelector.pp_from_check}"
		    		  maxlength="15"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_to_check" value="${posiSelector.pp_to_check}"
		    		  maxlength="15"/>
		</td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
	    <td align="center" width="10%" height="19">
		<stripes:text name="posiSelector.pp_currency"
			value="${posiSelector.pp_currency}" maxlength="3"/>
	    </td>
	    <td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_check_amount" value="${posiSelector.pp_check_amount}"
				maxlength="15"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="swiftRef"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_swift_ref" value="${posiSelector.pp_swift_ref}"
				size="16" maxlength="20"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="issueDate"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_issue_date" value="${posiSelector.pp_issue_date}"
		    		maxlength="15"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
		    <select size="1" name="posiSelector.pp_status">
			<option value="M">Matched</option>
			<option value="U">Unmatched</option>
			<option value="E">Error</option>
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected value="ALL">All of the above</option>
		    	</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			    <option value="ALL">All of the above</option>
			    <option selected><c:out value="${posiSelector.pp_status}"/></option>
			</c:if>
		    </select>
		</td>
		<td></td>
	</tr>
    </table>
    </div>

<%--													--%>
<%--	This is the table of selected rows. If maintenance user will be able to modify and except checks--%>
<%--	user will be able to delete. If inquiry then user will be able to view details.			--%>
<%--													--%>
<c:if test="${posiSelector.rowCount!=0}">
<c:if test='${posiSelector.actionFlag!=""}'>
	<stripes:submit name="View" value="View"/>
	<c:if test='${posiSelector.accessFlag!="inq"}'>
	    <stripes:submit name="New"  value="New Posipay"/>
	</c:if>
	<stripes:submit name='ExtractPosipay' value="Extract Posipay Data"/>  
	<hr>
	<br/>
<div id="totals" align=center>
    <table colspan='2'>
	<tr>
	    <th align=center>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td align="center">
		<c:out value="${posiSelector.rowCount}"/></b>
	    </td>
	</tr>
    </table>
</div>

	<p align="center"></p>
	<h3>
	<c:out value="Displaying "/>
	<c:out value="${posiSelector.rowStart+1}"/>
	<c:out value=" to "/>
	<c:out value="${posiSelector.rowEnd}"/>
	<c:out value=" of "/>
	<c:out value="${posiSelector.rowCount}"/>
	<c:out value=" rows "/>
	<c:if test='${posiSelector.rowCount>posiSelector.rowsDisplayed}'>
	    <c:if test='${posiSelector.rowEnd!=posiSelector.rowCount}'>
		<stripes:submit name="Next" value="Next"/>
	    </c:if>
	    <c:if test='${posiSelector.rowCount<=posiSelector.rowsDispStr}'>
		<stripes:text name="posiSelector.rowCount" size="4" maxlength="4"
			value="${posiSelector.rowsDispStr}"/>
	    </c:if>
	    <c:if test='${posiSelector.rowCount>posiSelector.rowsDispStr}'>
	    <c:if test='${posiSelector.rowEnd!=posiSelector.rowCount}'>
		<stripes:text name="posiSelector.rowsDispStr" size="4" maxlength="4"
			value="${posiSelector.rowsDispStr}"/>
	    </c:if>
	    </c:if>
	    <c:if test='${posiSelector.rowStart!=0}'>
	    <c:if test='${posiSelector.rowEnd!=posiSelector.rowCount}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	    <c:if test='${posiSelector.rowStart==0}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    <c:if test='${posiSelector.rowCount>posiSelector.rowsDispStr}'>
	    <c:if test='${posiSelector.rowEnd==posiSelector.rowCount}'>
		<stripes:text name="posiSelector.rowsDispStr" size="4" maxlength="4"
			value="${posiSelector.rowsDispStr}"/>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	</c:if>
	<c:if test='${posiSelector.rowStart>=1}'>
	<c:if test='${posiSelector.rowsDisplayed>=posiSelector.rowCount}'>
	<c:if test='${posiSelector.rowEnd>=posiSelector.rowCount}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	</c:if>
	</c:if>
	</h3>
	<br/>

<div id="selres">
<table class="sortable" colspan='9' width='90%' align='center' border='1' cellspacing="1" cellpadding="1">
<%--
	<tr bgcolor=bluegreen>
	    <th class="header" align=center height=19 colspan=8>
		<font size=3 color=blue>
		    <stripes:label for="posipay"/> <stripes:label for="maint"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
--%>
	<div id="selres">
	<table class="sortable" colspan='13' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
	  <tr bgcolor=bluegreen>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th style="text-alignright" width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="check"/> <stripes:label for="number"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th style="text-align:right" width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="swiftRef"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan='3'>
		<font size=2><stripes:label for="payee"/></font>
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

<c:forEach items="${posiSelector.posipayrows}" var="PosipayDetail" varStatus="idx0" 
	   	end="${posiSelector.rowEnd-1}" begin="${posiSelector.rowStart}">
  <tr>
    <td align="center" width='2%' height='19'>
	<c:out value="${idx0.index+1}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${PosipayDetail.pospay_account_num}"/></b>
    </td>
    <td style="text-align:right" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_check_num}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_currency}"/>
    </td>
    <td style="text-align:right" width='10%' height='19'>
      <fmt:formatNumber value="${PosipayDetail.pospay_check_amount}" type="currency"/>
    </td>
    <td  align='center' width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_swift_ref}"/>
    </td>
    <td align="left" width='4%' height='19' colspan='3'>
      <c:out value="${PosipayDetail.pospay_payee}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_issue_date}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_status}"/>
    </td>
    <td align="center" width='7%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/Posipay.action" event="Modify">
	    Delete
	    <stripes:param name="action" value="Delete"/>
	    <stripes:param name="acctNum" value="${PosipayDetail.pospay_account_num}"/>
	    <stripes:param name="checkNum" value="${PosipayDetail.pospay_check_num}"/>
	</stripes:link>
    </td>
    <td align="center" width='7%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/Posipay.action" event="Modify">
	    Modify
	    <stripes:param name="action" value="Modify"/>
	    <stripes:param name="acctNum" value="${PosipayDetail.pospay_account_num}"/>
	    <stripes:param name="checkNum" value="${PosipayDetail.pospay_check_num}"/>
	</stripes:link>
    </td>
  </tr>
<%--
  <tr>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_payee_addr1}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_payee_addr2}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_payee_addr3}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${PosipayDetail.pospay_value_date}"/>
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
		<c:out value="${posiSelector.rowCount}"/></b>
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
<%--	<c:out value="${posiSelector.actionFlag}"/>							--%>


     <center>
	<hr>
	<stripes:errors/>
	<stripes:submit name="View" value="View"/>
	<stripes:submit name="New"  value="New Posipay"/>
	<c:if test='${posiSelector.rowCount!=0}'>
	    <stripes:submit name='ExtractPosipay' value="Extract Posipay Data"/>  
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
