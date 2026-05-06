<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/ChexHist.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1>
	<stripes:label for="check"/> <stripes:label for="data"/>
	<stripes:label for="history"/> <stripes:label for="inquiry"/>
    </h1>
    <div id="detail">
    <table align="center" border="1" height="3"  width="55%">
	<tr>
	    <th class="header" align=center height="19" colspan="3">
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="history"/> 
		    <stripes:label for="inquiry"/> <stripes:label for="selCriteria"/> 
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
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="period"/></font></TH>
	    <td>
	    	<select size="1" name="chexSelector.ch_from_period">
		    <c:forEach items="${chexSelector.histList}" var="hist">
			<c:if test='${chexSelector.ch_from_period==hist}'>
			    <option selected> <c:out value="${hist}"/> </option>
			</c:if>
			<c:if test='${chexSelector.ch_from_period!=hist}'>
			    <option> <c:out value="${hist}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="chexSelector.ch_to_period">
		    <c:forEach items="${chexSelector.histList}" var="hist">
			<c:if test='${chexSelector.ch_to_period==hist}'>
			    <option selected> <c:out value="${hist}"/> </option>
			</c:if>
			<c:if test='${chexSelector.ch_to_period!=hist}'>
			    <option> <c:out value="${hist}"/> </option>
			</c:if>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="date"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_from_date" maxlength="15"
			value="${chexSelector.ch_from_date}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_to_date"  maxlength="15"
			value="${chexSelector.ch_to_date}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="account"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_from_account"  maxlength="18"
			value="${chexSelector.ch_from_account}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_to_account"  maxlength="18"
			value="${chexSelector.ch_to_account}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3">
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_from_check"  maxlength="15"
			value="${chexSelector.ch_from_check}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_to_check"  maxlength="15"
			value="${chexSelector.ch_to_check}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1" valign="bottom">
		<font size="3"><stripes:label for="amount"/></font></TH>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_from_amount"  maxlength="15"
			value="${chexSelector.ch_from_amount}"/>
	    </td>
	    <td align="center" width="5%" height="19">
		<stripes:text name="chexSelector.ch_to_amount"  maxlength="15"
			value="${chexSelector.ch_to_amount}"/>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1"  valign="bottom">
		<font size="3"><stripes:label for="currency"/></font></TH>
	    <td align="center" width="5%" height="19" colspan="1">
		<stripes:text name="chexSelector.ch_currency"  maxlength="15"
			value="${chexSelector.ch_currency}"/>
	    </td>
	    <td>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1" rowspan="1"  valign="bottom">
		<font size="3">
		    <stripes:label for="return"/> <stripes:label for="status"/>
		</font></TH>
	    <td align="center" width="5%" height="19" colspan="1">
	    <select size="1" name="chexSelector.ch_retstat">
		<c:if test='${chexSelector.ch_retstat=="Y"}'>
			<option value="N">NONE</option>
			<option Selected value="Y">ALL</option>
		</c:if>
		<c:if test='${chexSelector.ch_retstat!="Y"}'>
			<option Selected value="N">NONE </option>
			<option value="Y">ALL</option>
		</c:if>
	    </select>
<%--
		<stripes:text name="chexSelector.ch_retstat"  maxlength="15"
			value="${chexSelector.ch_retstat}"/>
--%>
	    </td>
	    <td>
	    </td>
	</tr>
    </table>
    </div>
<c:if test='${chexSelector.detail_count!=0}'>
<c:if test='${chexSelector.actionFlag!=""}'>
     <center>
	<stripes:submit name="View" value="View"/>
	<c:if test='${chexSelector.detail_count!=0}'>
	    <stripes:submit name="ExtractChex" value="Extract Check Data"/>
	    <stripes:submit name="GeneratePDF" value="Generate Image PDF"/>
	    <stripes:submit name="GenerateDeliver" value="Generate & Deliver Image PDF"/>
	</c:if>
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
		<c:out value="${chexSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detailAmount}"/></b>
	    </td>
	</tr>
</table>
</div>

	<h3>
	    <c:out value="Displaying "/>
	    <c:out value="${chexSelector.rowStart+1}"/>
	    <c:out value=" to "/>
	    <c:out value="${chexSelector.rowEnd}"/>
	    <c:out value=" of "/>
	    <c:out value="${chexSelector.detail_count}"/>
	    <c:out value=" rows "/>
	    <c:if test='${chexSelector.detail_count>chexSelector.rowsDisplayed}'>
		<c:if test='${chexSelector.rowEnd!=chexSelector.detail_count}'>
		    <stripes:submit name="Next" value="Next"/>
		</c:if>
		<c:if test='${chexSelector.detail_count<=chexSelector.rowsDispStr}'>
		    <stripes:text name="chexSelector.detail_count" size="4" maxlength="4"
			value="${chexSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${chexSelector.detail_count>chexSelector.rowsDispStr}'>
		    <stripes:text name="chexSelector.rowsDispStr" size="4" maxlength="4"
			value="${chexSelector.rowsDispStr}"/>
		</c:if>
		<c:if test='${chexSelector.rowStart!=0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
		<c:if test='${chexSelector.rowStart==0}'>
		    <stripes:submit name="Prev" value="Prev"/>
		</c:if>
	    </c:if>
	    <c:if test='${chexSelector.rowStart>1}'>
		<stripes:submit name="Prev" value="Prev"/>
	    </c:if>
	</h3>
	<br/>

<%--
<table colspan='12' width='100%' align='center' border='1' height='39'>
	<tr bgcolor=bluegreen>
	    <th align=center height=19 colspan="12">
		<font size=3 color=blue>
		    <stripes:label for="check"/> <stripes:label for="data"/>
		    <stripes:label for="history"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selResults"/>
		</font>
	    </th>
	</tr>
</table>
--%>
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
	    <th align="right" width='8%' height=15 colspan=1>
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
	    <th align='center' width='5%' height=15>
	    	<font size=2>
		    <stripes:label for="return"/> <stripes:label for="status"/>
		</font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> by <stripes:label for="user"/>
		</font>
	    </th>
	    <th align='center' width='8%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> by <stripes:label for="function"/>
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

<c:forEach items="${chexSelector.checkrows}" var="ChexDetail" varStatus="idx0" end="${chexSelector.rowEnd-1}" 
	begin="${chexSelector.rowStart}">
<%--
<c:forEach items="${chexSelector.checkrows}" var="ChexDetail">
--%>
  <tr>
    <td align="center" width='2%' height='19'>
      <c:out value="${idx0.index+1}"/>
    </td>
    <td align="center" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_proc_date}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${ChexDetail.chex_account_num}"/></b>
    </td>
    <td style="text-align:right" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_check_num}"/>
    </td>
    <td align="left" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_check_currency}"/>
    </td>
    <td style="text-align:right" width='5%' height='19'>
      <b><fmt:formatNumber value="${ChexDetail.chex_check_amount}" type="currency"/></b>
    </td>
    <td  align='center' width='20%' height='19'>
      <c:out value="${ChexDetail.chex_payee}"/>
    </td>
    <td  align='center' width='5%' height='19'>
      <c:out value="${ChexDetail.chex_return_status}"/>
    </td>
    <td align="center" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_check_status}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${ChexDetail.chex_mod_user}"/>
    </td>
    <td align="center" width='10%' height='19'>
      <c:out value="${ChexDetail.chex_mod_func}"/>
    </td>
    <td align="center" width='20%' height='19'>
      <c:out value="${ChexDetail.chex_last_modified}"/>
    </td>
    <td align="center" width='5%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/ChexHist.action" event="Details">
	    Details
		<stripes:param name="proc_date" value="${ChexDetail.chex_proc_date}"/>
		<stripes:param name="account_number" value="${ChexDetail.chex_account_num}"/>
		<stripes:param name="check_number" value="${ChexDetail.chex_check_num}"/>
		<stripes:param name="check_unique_isn" value="${ChexDetail.chex_unique_isn}"/>
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
		<c:out value="${chexSelector.detail_count}"/></b>
	    </td>
	    <th>
		<font size=2><stripes:label for="totalAmount"/></font>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detailAmount}"/></b>
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
	<c:if test='${chexSelector.detail_count!=0}'>
	    <stripes:submit name="ExtractChex" value="Extract Check Data"/>
	    <stripes:submit name="GeneratePDF" value="Generate Image PDF"/>
	    <stripes:submit name="GenerateDeliver" value="Generate & Deliver Image PDF"/>
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
