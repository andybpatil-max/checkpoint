<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Account.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="account"/> (CIF) <stripes:label for="inquiry"/></h1>

<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>
	<div id="detail">
	<table align="center" border="1" height="3" width="60%">
	<tr>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">CIF <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/>
		</font>
	    </TH>
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
		<font size="3">
		    <stripes:label for="account"/> <stripes:label for="number"/>
		</font></TH>
	    <td>
		<select size="1" name="acctSelector.account_num_from">
		    <option>ALL</option>
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
		    <option>NONE</option>
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
	    <td>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3"><stripes:label for="swAddr"/></font></TH>
	    <td align="center" width="10%" height="19">
		<stripes:text name="acctSelector.account_sw_addr_sel" maxlength="15"
		    value="${acctSelector.account_sw_addr_sel}"/>
	    </td>
	    <td>
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
	    <td>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3">
		    <stripes:label for="posipay"/> <stripes:label for="flag"/>
		</font></TH>
	    <td>
		<select size="1" name="acctSelector.account_posi_pay_flag_sel">
		    <option value="Y">Yes</option>
		    <option value="N">No</option>
		    <c:if test='${acctSelector.actionFlag==""}'>
			<option selected value="ALL">All of the above</option>
		    </c:if>
		    <c:if test='${acctSelector.actionFlag!=""}'>
		    	<option selected><c:out value="${acctSelector.account_posi_pay_flag_sel}"/></option>
			<option value="ALL">All of the above</option>
		    </c:if>
		</select>
	    </td>
	    <td>
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
	    <td>
	    </td>
	</tr>
	</table>
	</div>

<%--													--%>
<%--	This is the table of selected rows. If maintenance user will be able to modify and except checks--%>
<%--	user will be able to delete. If inquiry then user will be able to view details.			--%>
<%--													--%>
    <c:if test='${acctSelector.rowCount!=0}'>
    <c:if test='${acctSelector.actionFlag!=""}'>
	<center>
	    <stripes:submit name="ViewInq" value="View"/>
	</center>
	<hr>
	<br/>
	<div id="totals">
	    <table align='center'>
		<tr colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><b><c:out value="${acctSelector.rowCount}"/></b></td>
		</tr>
	    </table>
	</div>

	<p align="center"></p>

	<h3>
	<c:out value="Displaying "/>
	<c:out value="${acctSelector.rowStart+1}"/>
	<c:out value=" to "/>
	<c:out value="${acctSelector.rowEnd}"/>
	<c:out value=" of "/>
	<c:out value="${acctSelector.rowCount}"/>
	<c:out value=" rows "/>
	<c:if test='${acctSelector.rowCount>acctSelector.rowsDisplayed}'>
	    <c:if test='${acctSelector.rowEnd!=acctSelector.rowCount}'>
		<stripes:submit name="Next" value="Next"/>
	    </c:if>
	    <c:if test='${acctSelector.rowCount<=acctSelector.rowsDispStr}'>
		<stripes:text name="acctSelector.rowCount" size="4" maxlength="4"
			value="${acctSelector.rowsDispStr}"/>
	    </c:if>
	    <c:if test='${acctSelector.rowCount>acctSelector.rowsDispStr}'>
	    <c:if test='${acctSelector.rowEnd!=acctSelector.rowCount}'>
		<stripes:text name="acctSelector.rowsDispStr" size="4" maxlength="4"
			value="${acctSelector.rowsDispStr}"/>
	    </c:if>
	    </c:if>
	    <c:if test='${acctSelector.rowStart!=0}'>
	    <c:if test='${acctSelector.rowEnd!=acctSelector.rowCount}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	    <c:if test='${acctSelector.rowStart==0}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    <c:if test='${acctSelector.rowCount>acctSelector.rowsDispStr}'>
	    <c:if test='${acctSelector.rowEnd==acctSelector.rowCount}'>
		<stripes:text name="acctSelector.rowsDispStr" size="4" maxlength="4"
			value="${acctSelector.rowsDispStr}"/>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	    </c:if>
	</c:if>
	<c:if test='${acctSelector.rowStart>=1}'>
	<c:if test='${acctSelector.rowsDisplayed>=acctSelector.rowCount}'>
	<c:if test='${acctSelector.rowEnd>=acctSelector.rowCount}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	</c:if>
	</c:if>
	</h3>
	<br/>
<%--													--%>
<%--	This is the table of selected rows. If maintenance user can Delete or modify. If inquiry user  	--%>
<%--	can view row details										--%>
<%--													--%>
	<div id="selres">
	<table class="sortable" colspan='12' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
	    <tr  height=20>
	    	<th align='center' width='2%' height=15 colspan=1>
		    <font size=2><stripes:label for="recNum"/></font>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="account"/> <stripes:label for="number"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="client"/> <stripes:label for="name"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="account"/> <stripes:label for="manager"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="date"/> <stripes:label for="opened"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="date"/> <stripes:label for="closed"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="date"/> <stripes:label for="blocked"/>
		</th>
		<th align=right width='10%' height=15 colspan=1>
		    <stripes:label for="max"/> <stripes:label for="check"/> <stripes:label for="amount"/>
		</th>
		<th align=center width='10%' height=15 colspan=1>
		    <stripes:label for="posipay"/> <stripes:label for="flag"/>
		</th>
		<th align=left width='10%' height=17 colspan=1>
		    <stripes:label for="days"/> <stripes:label for="check"/> <stripes:label for="valid"/>
		</th>
		<th align=center width='10%' height=15 colspan=2>
		    <stripes:label for="user"/> <stripes:label for="action"/>
		</th>
	    </tr>

	<c:forEach items="${acctSelector.accountrows}" var="acctDetail" varStatus="idx0" 
		   	end="${acctSelector.rowEnd-1}" begin="${acctSelector.rowStart}">
	    <tr>
		<td align="center" bgcolor='lightyellow' width='2%' height='19'>
		    <c:out value="${idx0.index+1}"/>
		</td>
		<td align="center" width='10%' height='19'><b>
		    <c:out value="${acctDetail.account_num}"/></b>
		</td>
		<td align="left" width='10%' height='19'>
		    <c:out value="${acctDetail.account_client_name}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${acctDetail.account_acc_rep}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${acctDetail.account_effective_date}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${acctDetail.account_closed_date}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${acctDetail.account_blocked_date}"/>
		</td>
		<td align="right" width='10%' height='19'>
		    <c:out value="${acctDetail.account_max_checkamt_disp}"/>
		</td>
		<td align="center" width='10%' height='19' colspan='1'>
		    <c:out value="${acctDetail.account_posi_pay_flag}"/>
		</td>
		<td align="left" width='10%' height='19' colspan='1'>
		    <c:out value="${acctDetail.account_dayscheckvalid}"/>
		</td>
		<td align="center" width='5%' height='19'>
			<stripes:link href="/econtroller/inclear/actions/Account.action" event="Details">
			    Detail
			    <stripes:param name="action" value="inq"/>
			    <stripes:param name="acctNum" value="${acctDetail.account_num}"/>
			    <stripes:param name="recIndex" value="${idx0.index}"/>
			</stripes:link>
		</td>
	    </tr>
	</c:forEach>
	</table>
	</div>

	<div id="totals">
	<table align='center'>
	    <tr colspan=2>
		<th><font size=2><stripes:label for="rowcount"/></font></th>
		<td>
		    <c:out value="${acctSelector.rowCount}"/></b>
		</td>
	    </tr>
	</table>
	</div>
    </c:if>
    </c:if>
    <center>
	<hr>
	<stripes:errors />
	<stripes:submit name="ViewInq" value="View"/>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
