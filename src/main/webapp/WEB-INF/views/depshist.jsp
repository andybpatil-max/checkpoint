<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepsHist.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1>
	<stripes:label for="deposit"/> <stripes:label for="data"/> <stripes:label for="history"/>
	<stripes:label for="inquiry"/>
    </h1>
    <div id="detail">
    <table align="center" border="1" height="3"  width="55%">
	<tr>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="data"/> 
		    <stripes:label for="history"/> 	<stripes:label for="inquiry"/>
		    <stripes:label for="selCriteria"/>
		</font>
	    </TH>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1"></TH>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="from"/></font></th>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="to"/></font></th>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3">
		    <stripes:label for="period"/>
		</font>
	    </th>
	    <td>
	    	<select size="1" name="depsSelector.ch_from_period">
		    <c:if test='${depsSelector.actionFlag==""}'>
			<c:forEach items="${depsSelector.histList}" var="hist">
				<option> <c:out value="${hist}"/> </option>
			</c:forEach>
		    </c:if>
		    <c:if test='${depsSelector.actionFlag!=""}'>
			<option selected><c:out value="${depsSelector.ch_from_period}"/></option>	
			<c:forEach items="${depsSelector.histList}" var="hist">
			  <c:if test="${depsSelector.ch_from_period!=hist}">
				<option> <c:out value="${hist}"/> </option>
			  </c:if>
			</c:forEach>
		    </c:if>
		</select>
	    </td>
	    <td>
		<select size="1" name="depsSelector.ch_to_period">
		    <c:if test='${depsSelector.actionFlag==""}'>
			<option selected>NONE</option>
			<c:forEach items="${depsSelector.histList}" var="hist">
				<option> <c:out value="${hist}"/> </option>
			</c:forEach>
		    </c:if>
		    <c:if test='${depsSelector.actionFlag!=""}'>
			<option selected><c:out value="${depsSelector.ch_to_period}"/></option>
			<option>NONE</option>
			<c:forEach items="${depsSelector.histList}" var="hist">
			    <c:if test="${depsSelector.ch_to_period!=hist}">	
				<option> <c:out value="${hist}"/> </option>
			    </c:if>
			</c:forEach>
		    </c:if>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="date"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_date" maxlength="15"
			value="${depsSelector.ch_from_date}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_date"  maxlength="15"
			value="${depsSelector.ch_to_date}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="account"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_account"  maxlength="18"
			value="${depsSelector.ch_from_account}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_account"  maxlength="18"
			value="${depsSelector.ch_to_account}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="payee"/> <stripes:label for="account"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_fromPayee"  maxlength="18"
			value="${depsSelector.ch_fromPayee}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_toPayee"  maxlength="18"
			value="${depsSelector.ch_toPayee}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </th>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_check"  maxlength="15"
			value="${depsSelector.ch_from_check}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_check"  maxlength="15"
			value="${depsSelector.ch_to_check}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3"><stripes:label for="amount"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_from_amount"  maxlength="15"
			value="${depsSelector.ch_from_amount}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="depsSelector.ch_to_amount"  maxlength="15"
			value="${depsSelector.ch_to_amount}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan=1  valign="bottom">
		<font size="3"><stripes:label for="currency"/></font></TH>
	    <td align="center" width="5%" height="19" colspan="2">
		<stripes:text name="depsSelector.ch_currency"  maxlength="15"
			value="${depsSelector.ch_currency}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" rowspan="1"  colspan="1">
		<font size="3">
		    <stripes:label for="deposit"/> <stripes:label for="source"/>
		</font>
	    </th>
	    <td align="center" width="5%" height="19" colspan="2">
		<c:if test='${depsSelector.actionFlag==""}'>
	    	<select size="1" name="depsSelector.ch_source">
		    <option selected value="ALL">ALL</option>
		    <option value="LOCKBOX">Lockbox</option>
		    <option value="RDC">Remote Deposits</option>
		</select>
		</c:if>
		<c:if test='${depsSelector.actionFlag!=""}'>
		    <select size="1" name="depsSelector.ch_source">
			<c:if test="${depsSelector.ch_source=='ALL'}">
			    <option selected value="ALL">ALL</option>
			    <option value="LOCKBOX">Lockbox</option>
			    <option value="RDC">Remote Deposits</option>
			</c:if>
			<c:if test="${depsSelector.ch_source=='LOCKBOX'}">
			    <option value="ALL">ALL</option>
			    <option selected value="LOCKBOX">Lockbox</option>
			    <option value="RDC">Remote Deposits</option>
			</c:if>
			<c:if test="${depsSelector.ch_source=='RDC'}">
			    <option value="ALL">ALL</option>
			    <option value="LOCKBOX">Lockbox</option>
			    <option selected value="RDC">Remote Deposits</option>
			</c:if>
		    </select>
		</c:if>
	    </td>
	</tr>
    </table>
    </div>

<c:if test="${depsSelector.detail_count!=0}">
<c:if test='${depsSelector.actionFlag!=""}'>
     <center>
	<stripes:submit name="View" value="View"/>
    </center>
<hr>
<p align="center"></p>
<div id="totals">
<table align='center'>
	<tr colspan=2>
	    <th>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detailAmount}"/></b>
	    </td>
	</tr>
</table>
</div>
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
<table colspan='12' width='100%' align='center' border='1' height='39' class="sortable">
	<tr bgcolor=bluegreen>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th align='center' width='4%' height=15 colspan=1>
		<font size=2><stripes:label for="processDate"/></font>
	    </th>
	    <th align='center' width='8%' height=15 colspan=1>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='8%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </th>
	    <th align='center' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th align='right' width='8%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='center' width='20%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> By <stripes:label for="user"/>
		</font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> By <stripes:label for="function"/>
		</font>
	    </th>
	    <th align='center' width='20%' height=15>
		<font size=2>
		    <stripes:label for="last"/> <stripes:label for="modified"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="view"/></font>
	    </th>
	</tr>

<c:forEach items="${depsSelector.checkrows}" var="DepsDetail" varStatus="idx0" end="${depsSelector.rowEnd-1}" 
	begin="${depsSelector.rowStart}">
  <tr>
    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
      <c:out value="${idx0.index+1}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
      <c:out value="${DepsDetail.chex_proc_date}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
      <c:out value="${DepsDetail.chex_account_num}"/></b>
    </td>
    <td align="right" bgcolor='lightyellow' width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_num}"/>
    </td>
    <td align="left" bgcolor='lightyellow' width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_currency}"/>
    </td>
    <td align="right" bgcolor='lightyellow' width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_amount_disp}"/>
    </td>
    <td  align='center' bgcolor='lightyellow' width='20%' height='19'>
      <c:out value="${DepsDetail.chex_payee}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
      <c:out value="${DepsDetail.chex_check_status}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${DepsDetail.moduser}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${DepsDetail.modfunc}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='20%' height='19'>
      <c:out value="${DepsDetail.modtime}"/>
    </td>
    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
	<stripes:link href="/econtroller/deposits/actions/DepsHist.action" event="Details">
	    Details
		<stripes:param name="proc_date" value="${DepsDetail.chex_proc_date}"/>
		<stripes:param name="account_number" value="${DepsDetail.chex_account_num}"/>
		<stripes:param name="check_number" value="${DepsDetail.chex_check_num}"/>
		<stripes:param name="check_unique_isn" value="${DepsDetail.chex_unique_isn}"/>
		<stripes:param name="recIndex" value="${idx0.index}"/>
	</stripes:link>
    </td>
  </tr>
</c:forEach>
</table>

<div id="totals">
<table align='center'>
	<tr colspan=2>
	    <th>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<c:out value="${depsSelector.detailAmount}"/></b>
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
	<stripes:submit name="View" value="View"/>
    </center>
<%--
	<c:out value="${depsSelector.actionFlag}"/>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
