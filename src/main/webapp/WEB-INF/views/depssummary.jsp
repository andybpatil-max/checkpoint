<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/Deposits.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${depsSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="deposit" /> <stripes:label for="Data" />
		<stripes:label for="maint" /> By <stripes:label for="statusSumm" />
	    </h1>
	</c:if>
	<c:if test='${depsSelector.accessFlag=="inq"}'>
	    <h1>
		<stripes:label for="deposit" /> <stripes:label for="Data" />
		<stripes:label for="inquiry" /> By <stripes:label for="statusSumm" />
	    </h1>
	</c:if>
	<div id="summary">
	<table colspan='4' width='60%' align='center' border='1' bgcolor=skyblue height='39'>
	  <tr>
	    <th align='center' width='25%' height=15>
		<font size=2><stripes:label for="description" /></font>
	    </th>
	    <th align='center' width='10%' height=15>
		<font size=2><stripes:label for="code" /></font>
	    </th>
	    <th align='right' width='15%' height=15>
		<font size=2><stripes:label for="amount" /></font>
	    </th>
	    <th align='right' width='10%' height=15>
		<font size=2><stripes:label for="count" /></font>
	    </th>
	   </tr>
	<c:forEach items="${depsSelector.summaryrows}" var="chexSummary">
	   <tr>
	    <c:if test='${depsSelector.accessFlag!="inq"}'>
		<c:if test='${chexSummary.chex_check_status=="A"}'>
		    <td align="center" width='8%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/Deposits.action" event="Authorise">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${chexSummary.chex_check_status!="A"}'>
		    <td align="center" width='8%' height='19'>
			<stripes:link href="/econtroller/deposits/actions/Deposits.action" event="SummaryView">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </c:if>
	    <c:if test='${depsSelector.accessFlag=="inq"}'>
		<td align="center" width='8%' height='19'>
		    <stripes:link href="/econtroller/deposits/actions/Deposits.action" event="SummaryView">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			    <stripes:param name="action" value="inq"/>
			    <stripes:param name="chexsource" value="${depsSelector.chexSource}"/>
		    </stripes:link>
		</td>
	    </c:if>
	    <td align="center" width='10%' height='19'>
	      <b><c:out value="${chexSummary.chex_check_status}" /></b>
	    </td>

	    <td align="right" width='8%' height='19'>
	      <b><c:out value="${chexSummary.chex_summ_amount}" /></b>
	    </td>

	<%-- Different to color code the status --%>

	    <c:if test='${chexSummary.chex_check_status=="C"}'>
		<td align="right" class='orange' width='10%' height='19'>
		   <b><font color='red' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="E"}'>
		<td align="right" class='red' width='10%' height='19'>
		   <b><font color='white' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="M"}'>
		<td align="right" class='yellow' width='10%' height='19'>
		   <b><font color='red' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="R"}'>
		<td align="right" class='yellow' width='10%' height='19'>
		   <b><font color='red' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="P"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
		   <b><font color='black' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="A"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
		   <b><font color='black' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="S"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
		   <b><font color='black' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="W"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
	      	   <b><font color='black' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="Z"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
	      	   <b><font color='black' size='2'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>

	  </tr>
	</c:forEach>
	   <tr>
	    <td align="center" bgcolor='lightyellow' width='10%' height='19' colspan=2>
	      <b><stripes:label for="grandTotal"/></b>
	    </td>
	    <td align="right" bgcolor='lightyellow' width='8%' height='19'>
	      <b><c:out value="${depsSelector.summ_total_amount}"/></b>
	    </td>
	    <td align="right" bgcolor='lightyellow' width='10%' height='19'>
	      <c:out value="${depsSelector.summ_total_checks}"/>
	    </td>
	  </tr>

	</table>
	</div>

     <center>
	<stripes:errors />
	<stripes:submit name="RefreshSummary" value="Refresh Summary" />
    </center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
