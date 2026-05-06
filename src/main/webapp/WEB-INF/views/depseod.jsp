<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepsEod.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="Deposits"/> (RDC & LOCKBOX) EOD </h1>


<div id="detail">
<table align='center'>
    <tr>
	<th>
	    <font size=5><stripes:label for="application"/> Date</font>
	</th>
	<td>
	    <font size=5><c:out value="${depsSelector.applDate_f}"/></font>
	</td>
    </tr>
</table>
</div>

<div id="summary">

<c:if test='${depsSelector.eod_status!="Y"}'>
<table colspan='4' width='60%' align='center' border='1' bgcolor=skyblue height='39'>
  <tr>
    <th align='center' width='25%' height=15>
	<font size=2>
	    <stripes:label for="description"/></font>
    </th>
    <th align='center' width='10%' height=15>
	<font size=2><stripes:label for="code"/></font>
    </th>
    <th align='right' width='15%' height=15>
	<font size=2><stripes:label for="amount"/></font>
    </th>
    <th align='right' width='10%' height=15>
	<font size=2><stripes:label for="count"/></font>
    </th>
   </tr>

<c:forEach var="depsSummary" items='${depsSelector.summaryrows}'>
   <tr>
    <td align="center" width='8%' height='19'>
	<c:out value="${depsSummary.chex_status_description}"/>
    </td>
    <td align="center" width='10%' height='19'><b>
      <c:out value="${depsSummary.chex_check_status}"/></b>
    </td>
    <td align="right" width='8%' height='19'><b>
      <c:out value="${depsSummary.chex_summ_amount}"/></b>
    </td>
    <c:if test='${depsSummary.chex_check_status=="E"}'>
	<td align="right" class='red' width='10%' height='19'>
	    <b><font color='white'  size="2">
      	    <c:out value="${depsSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${depsSummary.chex_check_status=="C"}'>
	<td align="right" class='yellow' width='10%' height='19'>
	    <b><font color='red'  size="2">
      	    <c:out value="${depsSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${depsSummary.chex_check_status=="P"}'>
	<td align="right" class='lightgreen' width='10%' height='19'>
	   <b><font color='black'  size="2">
      	   <c:out value="${depsSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${depsSummary.chex_check_status=="S"}'>
	<td align="right" class='lightgreen' width='10%' height='19'>
	    <b><font color='black'>
      	    <c:out value="${depsSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
    <c:if test='${depsSummary.chex_check_status=="Z"}'>
	<td align="right" class='lightgreen' width='10%' height='19'>
	    <b><font color='black' size="2">
      	    <c:out value="${depsSummary.chex_check_count}"/></font></b>
	</td>
    </c:if>
  </tr>
</c:forEach>
   <tr>
    <td align="center" width='10%' height='19' colspan=2><b>
	<stripes:label for="grandTotal"/></b>
    </td>
    <td align="right" width='8%' height='19'><b>
	<c:out value="${depsSelector.summ_total_amount}"/></b>
    </td>
    <td align="right" width='10%' height='19'>
    	<b><font color='black' size="2">
	<c:out value="${depsSelector.summ_total_checks}"/>
	</b>
    </td>
  </tr>
</table>
</div>

</c:if>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--
<c:out value='${depsSelector.allow_eod}'/>
<c:out value='${depsSelector.eod_status}'/>
													--%>
    <center>
	<hr>
	<stripes:errors />
	
	<c:if test='${depsSelector.allow_eod=="Y"}'>
	    <c:if test='${depsSelector.eod_status=="N"}'>
		<stripes:submit name="Confirm" value="Perform EOD"/>
	    </c:if>
	</c:if>
<%--
	<c:if test='${depsSelector.eod_status=="Y"}'>
	    <stripes:submit name="UnDo" value="Undo EOD"/>
	    <stripes:submit name="CloseTheDay" value="Close the day"/>
	</c:if>
--%>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

