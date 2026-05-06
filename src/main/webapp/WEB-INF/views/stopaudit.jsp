<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/StoppayAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="stopPay"/></h1>
<div id="detail">
	<table align="center" border="1" height="3" width="50%">
	    <tr>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			<stripes:label for="stopPay"/> <stripes:label for="audit"/>
			<stripes:label for="selCriteria"/>
		    </font>
		</th>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1"></TH>
		<th  class="header" align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="from"/></font></TH>
		<th  class="header" align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="to"/></font></TH>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3"><stripes:label for="account"/></font></TH>
		<td>
		    <select size="1" name="stopSelector.sp_from_acct">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>ALL</option>
				<c:forEach var="acct" items="${stopSelector.acctList}">
				     <option> <c:out value="${acct}"/> </option>
				</c:forEach>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_from_acct}"/></option>
			</c:if>
		    </select>
		</td>
		<td>
		    <select size="1" name="stopSelector.sp_to_acct">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>NONE</option>
			    <c:forEach var="acct" items="${stopSelector.acctList}">
				<option> <c:out value="${acct}"/> </option>
			    </c:forEach>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_to_acct}"/></option>
			</c:if>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1">
		    <font size="3">
			<stripes:label for="check"/> <stripes:label for="number"/>
		    </font>
		</th>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_from_check" maxlength="15"
			value="${stopSelector.sp_from_check}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_to_check" maxlength="15"
			value="${stopSelector.sp_to_check}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_currency" maxlength="3"
			value="${stopSelector.sp_currency}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_check_amount" maxlength="15"
			value="${stopSelector.sp_check_amount}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="swiftRef"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_swift_ref" maxlength="15"
			value="${stopSelector.sp_swift_ref}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="issueDate"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="stopSelector.sp_issue_date" maxlength="15"
			value="${stopSelector.sp_issue_date}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
		    <select size="1" name="stopSelector.sp_status">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option value="M">Matched</option>
			    <option value="U">Unmatched</option>
			    <option selected value="ALL">All of the above</option>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_status}"/></option>
			</c:if>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			      <stripes:label for="last"/> <stripes:label for="modified"/>
			</font>
		</th>
		<td>
		    <select size="1" name="stopSelector.sp_log_date">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			    <c:forEach var="date" items="${stopSelector.dateList}">
				<option> <c:out value="${date}"/> </option>
			    </c:forEach>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_log_date}"/></option>
			</c:if>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			      <stripes:label for="modified"/> By <stripes:label for="user"/>
			</font>
		</th>
		<td>
		    <select size="1" name="stopSelector.sp_log_user">
			<c:if test='${stopSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			    <c:forEach var="user" items="${stopSelector.userList}">
				<option> <c:out value="${user}"/> </option>
			    </c:forEach>
			</c:if>
			<c:if test='${stopSelector.actionFlag!=""}'>
			    <option selected><c:out value="${stopSelector.sp_log_user}"/></option>
			</c:if>
		    </select>
		</td>
	    </tr>
	</table>
</div>
	<c:if test='${stopSelector.actionFlag!=""}'>
	<div id="totals">
	    <table align='center'>
		<tr colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td></b><c:out value="${stopSelector.rowCount}"/></b></td>
		</tr>
	    </table>
	</div>
	<table colspan='8' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr>
		<th class="header" colspan=8>
		    <font size=3 color=blue>
			<stripes:label for="stopPay"/> <stripes:label for="audit"/>
			<stripes:label for="inquiry"/> <stripes:label for="selResults"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align='center' width='5%' height='19' colspan=1>
		    <font size=2><stripes:label for="account"/></font>
		</th>
		<th align='right' width='5%' height='19' colspan=1>
		    <font size=2><stripes:label for="check"/> <stripes:label for="number"/></font>
		</th>
		<th align='right' width='5%' height='19' colspan=1>
		    <font size=2><stripes:label for="currency"/></font>
		</th>
		<th align='right' width='5%' height='19' colspan=1>
		    <font size=2><stripes:label for="amount"/></font>
		</th>
		<th align='center' width='5%' height='19' colspan=1>
		    <font size=2><stripes:label for="swiftRef"/></font>
		</th>
		<th align='center' width='5%' height='19' colspan=2>
		    <font size=2><stripes:label for="payee"/></font>
		</th>
		<th align='center' width='5%' height='19' colspan=1
		    <font size=2><stripes:label for="address1"/></font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="address2"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size=2><stripes:label for="address3"/></font>
		</th>
		<th align='center' width='5%' height='19'>
		    <font size=2><stripes:label for="issueDate"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="valueDate"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="status"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="last"/> <stripes:label for="modified"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="modified"/> By <stripes:label for="user"/></font>
		</th>
		<th align='center' width='8%' height='19' colspan=1>
		    <font size='2'><stripes:label for="modified"/> By <stripes:label for="function"/></font>
		</th>
	    </tr>
 
	    <c:forEach var="StoppayDetail" items="${stopSelector.stoppayrows}">
	    <tr>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'><b>
		    <c:out value="${StoppayDetail.stopay_account_num}"/></b>
		</td>
		<td align="right" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_check_num}"/>
		</td>
		<td  align='center' valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_currency}"/>
		</td>
		<td align="right" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_check_amount_disp}"/>
		</td>
		<td  align='center' valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_swift_ref}"/>
		</td>
		<td align="center" colspan="2" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_payee}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_payee_addr1}"/>
		</td>
	    </tr>
	    <tr>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_payee_addr2}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_payee_addr3}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_issue_date}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_value_date}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_status}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='15%' height='15'><font size=1>
		    <c:out value="${StoppayDetail.stopay_last_modified}"/></font>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_mod_user}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${StoppayDetail.stopay_mod_func}"/>
		</td>
	    </tr>
	</c:forEach>
	</table>

	<div id="totals">
	    <table align='center'>
		<tr bgcolor=bluegreen colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><b><c:out value="${stopSelector.rowCount}"/></b></td>
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

