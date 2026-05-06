<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepositsAudit.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
	<c:out value="${depsSelector.chexSource}"/>
--%>
    <h1>
	<c:if test="${depsSelector.chexSource=='LOCKBOX'}">
	      <stripes:label for="Lockbox"/> <stripes:label for="data"/>
	</c:if>
	<c:if test="${depsSelector.chexSource=='RDC'}">
	      <c:out value="${depsSelector.chexSource}"/> <stripes:label for="data"/>
	</c:if>
	<stripes:label for="audit"/> <stripes:label for="inquiry"/>
    </h1>
<div id="detail">
    <table align="center" border="1" height="3"  width="55%">
	<tr>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="data"/> 
		    <stripes:label for="audit"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selCriteria"/>
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
		<font size="3"><stripes:label for="period"/></font></TH>
	    <td>
	    	<select size="1" name="depsSelector.ch_from_period">
		    <c:forEach items="${depsSelector.histList}" var="hist">
			<c:if test='${depsSelector.ch_from_period==hist}'>
			    <option selected> <c:out value="${hist}"/> </option>
			</c:if>
			<c:if test='${depsSelector.ch_from_period!=hist}'>
			    <option> <c:out value="${hist}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	    <td></td>
<%--
	    <td>
		<select size="1" name="depsSelector.ch_to_period">
		    <c:forEach items="${depsSelector.histList}" var="hist">
			<c:if test='${depsSelector.ch_to_period==hist}'>
			    <option selected> <c:out value="${hist}"/> </option>
			</c:if>
			<c:if test='${depsSelector.ch_to_period!=hist}'>
			    <option> <c:out value="${hist}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
--%>
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
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font></TH>
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
	    <th align=center height="19" colspan="1" rowspan=2  valign="bottom">
		<font size="3"><stripes:label for="currency"/></font></TH>
	    <td align="center" width="5%" height="19" colspan="2">
		<stripes:text name="depsSelector.ch_currency"  maxlength="15"
			value="${depsSelector.ch_currency}"
			style='text-transform:uppercase'/>
	    </td>
	</tr>
    </table>
</div>


<c:if test='${depsSelector.actionFlag!=""}'>
<c:if test="${depsSelector.detail_count>0}">
     <center>
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
    </center>
	<br></br>
	<div id="totals">
	    <table align='center'>
		<tr colspan=2>
		    <th>
			<font size=2><stripes:label for="rowcount"/></font>
		    </th>
		    <td>
			<c:out value="${depsSelector.detail_count}"/></b>
		    </td>
		</tr>
	    </table>
	</div>

	<table colspan='12' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr bgcolor=bluegreen>
	    <th align=center height=19 colspan=12>
		<font size=3 color=blue>
		    <stripes:label for="check"/> <stripes:label for="data"/> 
		    <stripes:label for="audit"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
	<tr bgcolor=bluegreen>
	    <th align='center' width='4%' height=15 colspan=1>
		<font size=2><stripes:label for="processDate"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="check"/> <stripes:label for="number"/></font>
	    </th>
	    <th align='right' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="currency"/></font>
	    </th>
	    <th align='right' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='right' width='8%' height=15 colspan=1>
		<font size=2><stripes:label for="issueDate"/></font>
	    </th>
	    <th align='center' width='12%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="reject"/> </font><stripes:label for="reason"/>
		</font>
	    </th>
	    <th align='center' width='2%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> By <stripes:label for="user"/>
		</font>
	    </th>
	    <th align='center' width='15%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> By <stripes:label for="function"/>
		</font>
	    </th>
	    <th align='center' width='25%' height=15>
		<font size=2>
		    <stripes:label for="last"/> <stripes:label for="modified"/>
		</font>
	    </th>
	</tr>

	<c:forEach var="ChexDetail" items="${depsSelector.checkrows}">
	    <tr>
		<td align="center" width='5%' height='19'>
		    <c:out value="${ChexDetail.chex_proc_date}"/>
		</td>
		<td align="center" width='10%' height='19'><b>
		    <c:out value="${ChexDetail.chex_account_num}"/></b>
		</td>
		<td style="text-align:right" width='5%' height='19'>
		    <c:out value="${ChexDetail.chex_check_num}"/>
		</td>
		<td align="center" width='3%' height='19'>
		    <c:out value="${ChexDetail.chex_check_currency}"/>
		</td>
		<td style="text-align:right" width='5%' height='19'>
		    <fmt:formatNumber value="${ChexDetail.chex_check_amount}" type="currency"/>
		</td>
		<td  align='center' width='8%' height='19'>
		    <c:out value="${ChexDetail.chex_issue_date}"/>
		</td>
		<td  align='center' width='12%' height='19'>
		    <c:out value="${ChexDetail.chex_payee}"/>
		</td>
		<td  align='center' width='5%' height='19'>
		    <c:out value="${ChexDetail.chex_reason_codes}"/>
		</td>
		<td align="center" width='5%' height='19'>
		    <c:out value="${ChexDetail.chex_check_status}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${ChexDetail.moduser}"/>
		</td>
		<td align="center" width='10%' height='19'>
		    <c:out value="${ChexDetail.modfunc}"/>
		</td>
		<td align="center" width='15%' height='19'>
		    <c:out value="${ChexDetail.modtime}"/>
		</td>
	    </tr>
	</c:forEach>
	</table>

	<div id="totals">
	    <table align='center'>
		<tr colspan=2>
		    <th><font size=2><stripes:label for="rowcount"/></font></th>
		    <td><c:out value="${depsSelector.detail_count}"/></b></td>
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
	<stripes:errors />
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
