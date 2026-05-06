<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Calendar.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${calSelector.accessFlag=="inq"}'>
	    <h1><stripes:label for="calendar"/> <stripes:label for="inquiry"/></h1>
	</c:if>
	<c:if test='${calSelector.accessFlag!="inq"}'>
	    <h1><stripes:label for="calendar"/> <stripes:label for="maint"/></h1>
	</c:if>
	    <table align="center" border="1" height="3" width="20%">
		<c:if test='${calSelector.accessFlag=="inq"}'>
		    <tr bgcolor=turquoise>
			<th align=center height="19" colspan="2">
			    <font size="3">
				<stripes:label for="calendar"/> <stripes:label for="inquiry"/> 
				<stripes:label for="selCriteria"/>
			    </font></TH>
		    </tr>
		</c:if>
		<c:if test='${calSelector.accessFlag!="inq"}'>
		    <tr bgcolor=turquoise>
			<th align=center height="19" colspan="2">
			    <font size="3">
				<stripes:label for="calendar"/> <stripes:label for="maint"/> 
				<stripes:label for="selCriteria"/>
			    </font></TH>
		    </tr>
		</c:if>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="1">
			<font size="3">
			    <stripes:label for="year"/>/<stripes:label for="currency"/>
			</font></TH>
		</tr>
		<td>
		    <select size="1" name="calSelector.h_from_year">
			<option>ALL</option>
			<c:forEach items="${calSelector.yearList}" var="year">
			    <c:if test='${calSelector.h_from_year==year}'>
				<option selected><c:out value='${year}' /></option>
			    </c:if>
			    <c:if test='${calSelector.h_from_year!=year}'>
				<option> <c:out value="${year}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </table>

	<c:if test='${calSelector.actionFlag!=""}'>

	<table colspan='10' width='100%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <c:if test='${calSelector.accessFlag=="inq"}'>
		<tr>
		    <th align=center height=19 colspan=10>
			<font size="3" color="blue">
			    <stripes:label for="calendar"/> <stripes:label for="inquiry"/> 
			    <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${calSelector.accessFlag!="inq"}'>
		<tr>
		    <th align=center height=19 colspan=10>
			<font size="3" color="blue">
			    <stripes:label for="calendar"/> <stripes:label for="maint"/> 
			    <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
		<tr>
		    <th align='center' width='3%' height=15 colspan=1>
			<font size=2><stripes:label for="year"/></font>
		    </th>
		    <th align='center' width='1%' height=15 colspan=1>
			<font size=2><stripes:label for="currency"/></font>
		    </th>
		    <th align='center' width='5%' height=15 colspan=7>
			<font size=2><stripes:label for="holidays"/></font>
		    </th>
		    <th align='center' width='2%' height=15 colspan=1>
			<font size=2><stripes:label for="action"/></font>
		    </th>
		</tr>
	<c:forEach items="${calSelector.calendarrows}" var="cDetail">
	  <tr>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19' rowspan="3"
		valign="top" colspan=1><b><c:out value="${cDetail.hdYear}"/></b>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='1%' height='19' rowspan="3"
		valign="top"><c:out value="${cDetail.hdCurr}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[0]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[1]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[2]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[3]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[4]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[5]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[6]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='2%' height='19' rowspan=3 valign="bottom">
		<c:if test='${calSelector.accessFlag!="inq"}'>
		    <stripes:link href="/econtroller/sysadmin/actions/Calendar.action" event="Modify">
			Modify
			<stripes:param name="hYearCurr" value="${cDetail.hdYearCurr}"/>
		    </stripes:link>
		</c:if>
	    </td>
	  </tr>
	  <tr>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[7]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[8]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[9]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[10]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[11]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[12]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[13]}"/></td>
	  </tr>
	  <tr>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[14]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[15]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[16]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[17]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[18]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'>
		<c:out value="${cDetail.dateArray[19]}"/></td>
	    <td align="center" bgcolor='lightyellow' width='3%' height='19'> </td>
	</tr>
	</c:forEach>
	</table>
	</c:if>

	<center>
	    <stripes:errors />
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${calSelector.accessFlag!="inq"}'>
		<stripes:submit name="New" value="New Calendar"/>
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


