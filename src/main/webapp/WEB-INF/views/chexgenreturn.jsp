<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/ChexGenReturn.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
		<stripes:label for="generate"/> <stripes:label for="return"/> 
		<stripes:label for="checks"/> <stripes:label for="file"/>
	</h1>

<c:if test='${chexSelector.actionFlag!=""}'>
<p align="center"></p>
<%----%>
<c:if test="${chexSelector.detail_count > 0}">

<div id="totals">
<table align='center'>
	<tr colspan=2>
	    <th>
		<font size=2><stripes:label for="rowcount"/></font>
	    </th>
	    <td>
		<b><c:out value="${chexSelector.detail_count}"/></b>
	    </td>
	</tr>
</table>
</div>
<br/>
<table colspan='10' width='100%' align='center' border='1' height='39'>
	<tr>
	    <th class="header" height=19 colspan=10>
		<font size=3 color=blue><stripes:label for="returnItems"/></font>
	    </th>
	</tr>
	<tr>
	    <th align='center' width='4%' height=15 colspan=1>
		<font size=2><stripes:label for="processDate"/></font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2><stripes:label for="account"/></font>
	    </th>
	    <th align='right' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="number"/></font>
	    </th>
	    <th align='right' width='3%' height=15 colspan=1>
		<font size=2><stripes:label for="amount"/></font>
	    </th>
	    <th align='center' width='15%' height=15 colspan=1>
		<font size=2><stripes:label for="payee"/></font>
	    </th>
	    <th align='center' width='2%' height=15>
		<font size=2><stripes:label for="status"/></font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> by <stripes:label for="user"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2>
		    <stripes:label for="modified"/> by <stripes:label for="function"/>
		</font>
	    </th>
	    <th align='center' width='12%' height=15>
		<font size=2>
		    <stripes:label for="last"/> <stripes:label for="modified"/>
		</font>
	    </th>
	    <th align='center' width='2%' height=15>
		<font size=2><stripes:label for="view"/></font>
	    </th>
	</tr>

<c:forEach items="${chexSelector.checkrows}" var="ChexDetail"  varStatus="idx0" >
  <tr>
    <td align="center" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_proc_date}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${ChexDetail.chex_account_num}"/></b>
    </td>
    <td align="right" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_check_num}"/>
    </td>
    <td align="right" width='5%' height='19'>
      <c:out value="${ChexDetail.chex_check_amount_disp}"/>
    </td>
    <td  align='center' width='20%' height='19'>
      <c:out value="${ChexDetail.chex_payee}"/>
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
    <td align="center" width='15%' height='19'>
      <c:out value="${ChexDetail.chex_last_modified}"/>
    </td>
    <td align="center" width='5%' height='19'>
	    <stripes:link href="/econtroller/inclear/actions/ChexGenReturn.action" event="Details">
	    Details
		<stripes:param name="recIndex" value="${idx0.index}"/>
<%--
		<stripes:param name="proc_date" value="${ChexDetail.chex_proc_date}"/>
		<stripes:param name="account_number" value="${ChexDetail.chex_account_num}"/>
		<stripes:param name="check_number" value="${ChexDetail.chex_check_num}"/>
		<stripes:param name="check_unique_isn" value="${ChexDetail.chex_unique_isn}"/>
--%>
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
		<bean:write name="ChexSelector" property="detail_count"/></b>
	    </td>
	</tr>
</table>
</div>
</c:if>
</c:if>
     <center>
	<stripes:errors />
	<c:if test="${chexSelector.detail_count > 0}">
	    <stripes:submit name="GenReturns" value="Generate file of Checks to Return"/>
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
