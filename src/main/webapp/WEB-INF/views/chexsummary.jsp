<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Chex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
<c:out value='${chexSelector.accessFlag}'/>
--%>
	<c:if test='${chexSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="check"/> <stripes:label for="data"/> 
		<stripes:label for="maint"/> by <stripes:label for="status"/> 
		<stripes:label for="summary"/> 
	    </h1>
	</c:if>
	<c:if test='${chexSelector.accessFlag=="inq"}'>
	    <h1>
		<stripes:label for="check"/> <stripes:label for="data"/> 
		<stripes:label for="inquiry"/> by <stripes:label for="status"/> 
		<stripes:label for="summary"/> 
	    </h1>
	</c:if>
	<div id="summary">
	<table colspan='4' width='60%' align='center' border='1' bgcolor=skyblue height='39'>
	  <tr>
	    <th align='center' width='25%' height=15>
		<font size="2"><stripes:label for="description"/></font>
	    </th>
	    <th align='center' width='10%' height=15>
		<font size="2"><stripes:label for="status"/> <stripes:label for="code"/></font>
	    </th>
	    <th align='right' width='15%' height=15>
		<font size="2"><stripes:label for="amount"/></font>
	    </th>
	    <th align='right' width='10%' height=15>
		<font size="2"><stripes:label for="checkCount"/></font>
	    </th>
	   </tr>
	<c:forEach items="${chexSelector.summaryrows}" var="chexSummary">
	   <tr>
	    <c:if test='${chexSelector.accessFlag!="inq"}'>
		<c:if test='${chexSummary.chex_check_status=="A"}'>
		    <td align="center" bgcolor='lightyellow' width='8%' height='19'>
			<stripes:link href="/econtroller/inclear/actions/Chex.action" event="Authorize">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${chexSummary.chex_check_status=="F"}'>
		    <td align="center" bgcolor='lightyellow' width='8%' height='19'>
			<stripes:link href="/econtroller/inclear/actions/Chex.action" event="FraudChex">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			</stripes:link>
		    </td>
		</c:if>
		<c:if test='${chexSummary.chex_check_status!="A"}'>
		<c:if test='${chexSummary.chex_check_status!="F"}'>
		    <td align="center" bgcolor='lightyellow' width='8%' height='19'>
			<stripes:link href="/econtroller/inclear/actions/Chex.action" event="SummaryView">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			</stripes:link>
		    </td>
		</c:if>
		</c:if>
	    </c:if>
	    <c:if test='${chexSelector.accessFlag=="inq"}'>
		<td align="center" bgcolor='lightyellow' width='8%' height='19'>
		    <stripes:link href="/econtroller/inclear/actions/Chex.action" event="SummaryView">
			<c:out value="${chexSummary.chex_status_description}"/>
			    <stripes:param name="check_status" value="${chexSummary.chex_check_status}"/>
			    <stripes:param name="action" value="inq"/>
		    </stripes:link>
		</td>
	    </c:if>
	    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
	      <b><c:out value="${chexSummary.chex_check_status}"/></b>
	    </td>

	    <td align="right" bgcolor='lightyellow' width='8%' height='19'>
	      <b><c:out value="${chexSummary.chex_summ_amount}"/></b>
	    </td>

	<%-- Different to color code the status --%>

	    <c:if test='${chexSummary.chex_check_status=="E"}'>
		<td align="right" class='red' width='10%' height='19'>
		   <b><font size="2" color='white'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="F"}'>
		<td align="right" class='red' width='10%' height='19'>
		   <b><font size="2" color='white'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="R"}'>
		<td align="right" class='orange' width='10%' height='19'>
		   <b><font size="2" color='black'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="A"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
		   <b><font size="2" color='black'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="S"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
		   <b><font size="2" color='black'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>
	    <c:if test='${chexSummary.chex_check_status=="Z"}'>
		<td align="right" class='lightgreen' width='10%' height='19'>
	      	   <b><font size="2" color='black'><c:out value="${chexSummary.chex_check_count}"/></font></b>
		</td>
	    </c:if>

	  </tr>
	</c:forEach>
	    <tr>
		<th align="center" width='10%' height='19' colspan=2>
		    <b><stripes:label for="grandTotal"/></b>
		</th>
		<td align="right" width='8%' height='19'>
		    <b><c:out value="${chexSelector.summ_total_amount}"/></b>
		</td>
		<td align="right" class='skyblue' width='10%' height='19'>
		    <b><font size="2" color='black'><c:out value="${chexSelector.summ_total_checks}"/>
		    </font></b>
		</td>
	    </tr>
	</table>
	</div>

     <center>
	<stripes:errors />
	<stripes:submit name="RefreshSummary" value="Refresh Summary"/>
    </center>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
