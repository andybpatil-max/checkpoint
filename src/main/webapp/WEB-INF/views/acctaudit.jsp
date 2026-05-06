<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/AccountAudit.action">
<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	<h1>Account (CIF) <stripes:label for="audit"/></h1>
	<div id="detail">
	<table align="center" border="1" height="3" width="60%">
	    <tr bgcolor=turquoise>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			CIF <stripes:label for="audit"/> <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		    </font></TH>
	    </tr>
		<tr>
		    <th align=center height="19" colspan="1"></TH>
		    <th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="from"/></font></TH>
		    <th class="header" align=center height="19" colspan="1">
			<font size="3"><stripes:label for="to"/></font></TH>
		</tr>
		<tr>
		    <th align=right height="19" colspan="1">
			<font size="3">
			    <stripes:label for="account"/> <stripes:label for="number"/>
			</font></TH>
		    <td>
			<select size="1" name="acctSelector.account_num_from">
			    <option selected>ALL</option>
			    <c:forEach var="acct" items="${acctSelector.acctList}">
			        <c:if test='${acctSelector.account_num_from==acct}'>
				    <option selected><c:out value="${acctSelector.account_num_from}"/></option>
				</c:if>
				<c:if test='${acctSelector.account_num_from!=acct}'>
				    <option> <c:out value="${acct}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </td>
		    <td>
		    	<select size="1" name="acctSelector.account_num_to">
			    <option selected>NONE</option>
			    <c:forEach var="acct" items="${acctSelector.acctList}">
			        <c:if test='${acctSelector.account_num_to==acct}'>
				    <option selected><c:out value="${acctSelector.account_num_to}"/></option>
				</c:if>
				<c:if test='${acctSelector.account_num_to!=acct}'>
				    <option> <c:out value="${acct}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
		    <td align="center" width="10%" height="19">
		    	<stripes:text name="acctSelector.account_currency" maxlength="3" 
			    value="${acctSelector.account_currency}"/>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="swAddr"/></font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.account_sw_addr_sel" maxlength="15"
			    value="${acctSelector.account_sw_addr_sel}"/>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="max"/> <stripes:label for="check"/> <stripes:label for="amount"/>
			</font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.account_max_check_amt_sel" maxlength="15"
			    value="${acctSelector.account_max_check_amt_sel}"/>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="posipay"/> <stripes:label for="flag"/>
			</font></TH>
		    <td>
			<select size="1" name="acctSelector.account_posi_pay_flag_sel">
			    <option value="ALL">All</option>
			    <c:if test='${acctSelector.account_posi_pay_flag_sel=="Y"}'>
				<option selected value="Y">Yes</option>
			    </c:if>
			    <c:if test='${acctSelector.account_posi_pay_flag_sel!="Y"}'>
				<option value="Y">Yes</option>
			    </c:if>
			    <c:if test='${acctSelector.account_posi_pay_flag_sel=="N"}'>
				<option selected value="N">No</option>
			    </c:if>
			    <c:if test='${acctSelector.account_posi_pay_flag_sel!="N"}'>
				<option value="N">No</option>
			    </c:if>
			</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="min"/> <stripes:label for="posipay"/> <stripes:label for="amount"/>
			</font></TH>
		    <td align="center" width="10%" height="19">
			<stripes:text name="acctSelector.account_posi_pay_amt_min_sel" maxlength="15"
			    value="${acctSelector.account_posi_pay_amt_min_sel}"/>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="last"/> <stripes:label for="modified"/>
			</font></TH>
		    <td>
			<select size="1" name="acctSelector.account_log_date">
			    <option>ALL</option>
			    <c:forEach var="date" items="${acctSelector.dateList}">
				<c:if test='${acctSelector.account_log_date==date}'>
				    <option selected><c:out value="${acctSelector.account_log_date}"/></option>
				</c:if>
				<c:if test='${acctSelector.account_log_date!=date}'>
				    <option> <c:out value="${date}"/> </option>
				</c:if>
			    </c:forEach>
		    	</select>
		    </td>
		</tr>
		<tr>
		    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3">
			    <stripes:label for="modified"/> by <stripes:label for="user"/>
			</font></TH>
		    <td>
			<select size="1" name="acctSelector.account_log_user">
			    <option selected>ALL</option>
			    <c:forEach var="user" items="${acctSelector.userList}">
				<c:if test='${acctSelector.account_log_user==user}'>
				    <option selected> <c:out value="${user}"/> </option>
				</c:if>
				<c:if test='${acctSelector.account_log_user!=user}'>
				    <option> <c:out value="${user}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </td>
		</tr>
	</table>
	</div>
<c:if test='${acctSelector.actionFlag!=""}'>
	<div id="totals">
	    <table align="center" colspan='2'>
		<tr>
		    <th align=center>
			<font size=2><stripes:label for="rowcount"/></font>
		    </th>
		    <td align="center">
			<c:out value="${acctSelector.rowCount}"/></b>
		    </td>
		</tr>
	    </table>
	</div>
<div id="selres">
	<table colspan='5' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr bgcolor=bluegreen>
		<th class="header" align=center height=19 colspan=8>
		    <font size=3>CIF
			<stripes:label for="audit"/> <stripes:label for="inquiry"/> <stripes:label for="results"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="account"/> <stripes:label for="number"/>
		    </font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="client"/> <stripes:label for="name"/>
		    </font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="swAddr"/></font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="account"/> <stripes:label for="manager"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>Alternate 
			<stripes:label for="account"/> <stripes:label for="manager"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="date"/> <stripes:label for="opened"/>
		    </font>
		</th>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="date"/> <stripes:label for="closed"/>
		    </font>
		</th>
		<th align="center" width='8%' height=15 colspan=1>
		    <font size=2><stripes:label for="currency"/></font>
		</th>
		<th align=right width='8%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="max"/> <stripes:label for="check"/> <stripes:label for="amount"/>
		    </font>
		</th>
		<th align=center width='5%' height=15 colspan=1>
		    <font size=2>
			<stripes:label for="posipay"/> <stripes:label for="flag"/>
		    </font>
		</th>
		<th align=right width='5%' height=15>
		    <font size=2>
			<stripes:label for="min"/> <stripes:label for="posipay"/> <stripes:label for="amount"/>
		    </font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2>
			<stripes:label for="last"/> <stripes:label for="modified"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="stmtEmail"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="stmtFreq"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="stmtMail"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="stmtFreq"/>
		</th>
		<th align=right width='10%' height=15 colspan=1>
		    <stripes:label for="stmtFax"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="stmtFreq"/>
		</th>
	    </tr>
	    <tr>
		<th align=center width='5%' height=15>
		    <font size=2><stripes:label for="modified"/> By</font>
		</th>
		<th align=center width='5%' height=15>
		    <font size=2><stripes:label for="modified"/> By Function</font>
		</th>
		<th align=left width='10%' height=17 colspan=4>
		    <font size=2>
			<stripes:label for="user"/> <stripes:label for="comments"/>
		    </font>
		</th>
	    </tr>
	<c:forEach var="AccountDetail" items="${acctSelector.accountrows}">
	    <tr>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
		    <c:out value="${AccountDetail.account_num}"/></b>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_client_name}"/>
		</td>
		<td align='center' bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_sw_addr}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_acc_rep}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_alt_acc_rep}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_effective_date}"/>
		</td>
	    </tr>
	    <tr>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_closed_date}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_currency}"/>
		</td>
		<td align="right" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_max_checkamt_disp}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_posi_pay_flag}"/>
		</td>
		<td align="right" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_posi_payamtmin_disp}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='15%' height='19'><font size=1>
		    <c:out value="${AccountDetail.account_last_modified}"/></font>
		</td>
	    </tr>

	    <tr>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_stmt_email}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_stmt_emailfreqD}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_emailfreqW}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_emailfreqM}"/>
		</td>
		<td align="right" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_stmt_mail1}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19' colspan='1'>
		    <c:out value="${AccountDetail.account_stmt_mailfreqD}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_mailfreqW}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_mailfreqM}"/>
		</td>
		<td align="right" bgcolor='lightyellow' width='2%' height='19' colspan='1'>
		    <c:out value="${AccountDetail.account_stmt_fax}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_stmt_faxfreqD}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_faxfreqW}"/>
			/
		    <c:out value="${AccountDetail.account_stmt_faxfreqM}"/>
		</td>
	    </tr>

	    <tr>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_mod_user}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <c:out value="${AccountDetail.account_mod_func}"/>
		</td>
		<td align="left" bgcolor='lightyellow' width='10%' height='19' colspan='4'>
		    <c:out value="${AccountDetail.account_user_comments}"/>
		</td>
	    </tr>
	</c:forEach>
	</table>
</div>
	<div id="totals">
	<table align="center" colspan='2'>
	    <tr>
		<th align=center>
		    <font size=2><stripes:label for="rowcount"/></font>
		</th>
		<td align="center">
		    <c:out value="${acctSelector.rowCount}"/></b>
		</td>
	    </tr>
	</table>
	</div>
</c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--	<c:out value='${acctSelector.actionFlag}'/>							--%>
<%--	<c:out value='${acctSelector.accessFlag}'/>							--%>

     <center>
	<stripes:errors />
	<hr>
	<stripes:submit name="View" value="View"/>
    </center>

<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
