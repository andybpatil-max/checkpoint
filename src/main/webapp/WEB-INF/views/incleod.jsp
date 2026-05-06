<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/InclEod.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="eodPageTitle"/></h1>


<div id="detail">
<table align='center'>
    <tr>
	<th>
	    <font size=5><stripes:label for="application"/> <stripes:label for="date"/></font>
	</th>
	<td>
	    <font size=5><c:out value="${chexSelector.appl_date_f}"/></font>
	</td>
    </tr>
</table>
</div>

<div id="summary">

<c:if test='${chexSelector.eod_status=="N"}'>
<table colspan='4' width='60%' align='center' border='1' bgcolor=skyblue height='39'>
  <tr>
    <th align='center' width='25%' height=15>
	<font size=2><stripes:label for="status"/> <stripes:label for="description"/></font>
    </th>
    <th align='center' width='10%' height=15>
	<font size=2><stripes:label for="status"/> <stripes:label for="code"/></font>
    </th>
    <th align='right' width='15%' height=15>
	<font size=2><stripes:label for="amount"/></font>
    </th>
    <th align='right' width='10%' height=15>
	<font size=2><stripes:label for="check"/> <stripes:label for="count"/></font>
    </th>
   </tr>

<c:forEach var="chexSummary" items='${chexSelector.summaryrows}'>
   <tr>
    <td align="center" bgcolor='lightyellow' width='8%' height='19'>
	<stripes:link href="/econtroller/inclear/actions/Chex.action" event="SummaryView">
	    <c:out value="${chexSummary.chex_status_description}"/>
		<stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
	</stripes:link>
    </td>
    <td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
      <c:out value="${chexSummary.chex_check_status}"/></b>
    </td>
    <td align="right" bgcolor='lightyellow' width='8%' height='19'><b>
      <c:out value="${chexSummary.chex_summ_amount}"/></b>
    </td>
    <c:if test='${chexSummary.chex_check_status=="E"}'>
	<td align="right" class='red' width='10%' height='19'><b><font color='white'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${chexSummary.chex_check_status=="F"}'>
	<td align="right" class='red' width='10%' height='19'><b><font color='white'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${chexSummary.chex_check_status=="R"}'>
	<td align="right" class='yellow' width='10%' height='19'><b><font color='red'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${chexSummary.chex_check_status=="A"}'>
	<td align="right" class='lightgreen' width='10%' height='19'><b><font color='black'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${chexSummary.chex_check_status=="S"}'>
	<td align="right" class='lightgreen' width='10%' height='19'><b><font color='black'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${chexSummary.chex_check_status=="Z"}'>
	<td align="right" class='lightgreen' width='10%' height='19'>
	   <b><font color='black'>
      	   <c:out value="${chexSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
  </tr>
</c:forEach>
   <tr>
    <td align="center" bgcolor='lightyellow' width='10%' height='19' colspan=2><b>
      <stripes:label for="grandTotal"/></b>
    </td>
    <td align="right" bgcolor='lightyellow' width='8%' height='19'><b>
      <c:out value="${chexSelector.summ_total_amount}"/></b>
    </td>
    <td align="right" bgcolor='lightyellow' width='10%' height='19'>
      <c:out value="${chexSelector.summ_total_checks}"/>
    </td>
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
	    <c:if test='${chexSelector.allow_eod=="Y"}'>
		<c:if test='${chexSelector.eod_status=="N"}'>
		    <stripes:submit name="Confirm" value="Perform EOD"/>
		</c:if>
	    </c:if>
	    <c:if test='${chexSelector.eod_status=="Y"}'>
		<stripes:submit name="UnDo" value="Undo EOD"/>
		<stripes:submit name="CloseTheDay" value="Close the day"/>
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

