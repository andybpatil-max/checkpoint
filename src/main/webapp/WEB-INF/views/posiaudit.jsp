<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/PosipayAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="posipay"/> <stripes:label for="audit"/>
	    <stripes:label for="inquiry"/>
	</h1>
<div id="detail">
	<table align="center" border="1" height="3" width="50%">
	    <tr>
		<th class="header" align=center height="19" colspan="3">
		    <font size="3">
			<stripes:label for="posipay"/> <stripes:label for="audit"/>
			<stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
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
			    <option selected><c:out value="${posiSelector.pp_from_acct}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach var="acct" items="${posiSelector.acctList}">
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
			    <option selected><c:out value="${posiSelector.pp_to_acct}"/></option>
			    <option>NONE</option>
			</c:if>
			<c:forEach var="acct" items="${posiSelector.acctList}">
			    <option> <c:out value="${acct}"/> </option>
			</c:forEach>
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
		    <stripes:text name="posiSelector.pp_from_check" maxlength="15"
			value="${posiSelector.pp_from_check}"/>
		</td>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_to_check" maxlength="15"
			value="${posiSelector.pp_to_check}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="currency"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_currency" maxlength="3"
			value="${posiSelector.pp_currency}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="amount"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_check_amount" maxlength="15"
			value="${posiSelector.pp_check_amount}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="swiftRef"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_swift_ref" maxlength="15"
			value="${posiSelector.pp_swift_ref}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="issueDate"/></font></TH>
		<td align="center" width="10%" height="19">
		    <stripes:text name="posiSelector.pp_issue_date" maxlength="15"
			value="${posiSelector.pp_issue_date}"/>
		</td>
	    </tr>
	    <tr>
		<th align=center height="19" colspan="1" rowspan="1" valign="bottom">
			<font size="3"><stripes:label for="status"/></font></TH>
		<td>
		    <select size="1" name="posiSelector.pp_status">
			<option value="M">Matched</option>
			<option value="U">Unmatched</option>
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected value="ALL">All of the above</option>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			    <option selected><c:out value="${posiSelector.pp_status}"/></option>
			    <option value="ALL">All of the above</option>
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
		    <select size="1" name="posiSelector.pp_log_date">
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			    <option selected><c:out value="${posiSelector.pp_log_date}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach var="date" items="${posiSelector.dateList}">
			    <option> <c:out value="${date}"/> </option>
			</c:forEach>
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
		    <select size="1" name="posiSelector.pp_log_user">
			<c:if test='${posiSelector.actionFlag==""}'>
			    <option selected>ALL</option>
			</c:if>
			<c:if test='${posiSelector.actionFlag!=""}'>
			    <option selected><c:out value="${posiSelector.pp_log_user}"/></option>
			    <option>ALL</option>
			</c:if>
			<c:forEach var="user" items="${posiSelector.userList}">
			    <option> <c:out value="${user}"/> </option>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	</table>
</div>
<%--
	<stripes:submit name="View" value="View"/>
	<hr>
	<br/>
	<br/>
--%>
	<c:if test='${posiSelector.actionFlag!=""}'>
	<div id="totals">
	    <table align='center'>
		<tr colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td></b><c:out value="${posiSelector.rowCount}"/></b></td>
		</tr>
	    </table>
	</div>
	<table colspan='8' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr>
		<th class="header" colspan=8>
		    <font size=3 color=blue>
			<stripes:label for="posipay"/> <stripes:label for="audit"/>
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
 
	    <c:forEach var="PosipayDetail" items="${posiSelector.posipayrows}">
	    <tr>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'><b>
		    <c:out value="${PosipayDetail.pospay_account_num}"/></b>
		</td>
		<td align="right" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_check_num}"/>
		</td>
		<td  align='center' valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_currency}"/>
		</td>
		<td align="right" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_check_amount_disp}"/>
		</td>
		<td  align='center' valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_swift_ref}"/>
		</td>
		<td align="center" colspan="2" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_payee}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_payee_addr1}"/>
		</td>
	    </tr>
	    <tr>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_payee_addr2}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_payee_addr3}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_issue_date}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_value_date}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_status}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='15%' height='15'><font size=1>
		    <c:out value="${PosipayDetail.pospay_last_modified}"/></font>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_mod_user}"/>
		</td>
		<td align="center" valign="bottom" bgcolor='lightyellow' width='10%' height='15'>
		    <c:out value="${PosipayDetail.pospay_mod_func}"/>
		</td>
	    </tr>
	</c:forEach>
	</table>

	<div id="totals">
	    <table align='center'>
		<tr bgcolor=bluegreen colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><b><c:out value="${posiSelector.rowCount}"/></b></td>
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
	<hr>
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

