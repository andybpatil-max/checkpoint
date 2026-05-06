<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Currency.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	    <h1>
		<stripes:label for="currency"/> <stripes:label for="management"/>
	    </h1>
	    <table align="center" border="1" height="3" width="20%">
		<c:if test='${currSelector.accessFlag=="inq"}'>
		    <tr bgcolor=turquoise>
			<th align=center height="19" colspan="2">
			    <font size="3">
				<stripes:label for="currency"/> <stripes:label for="inquiry"/>
				<stripes:label for="selCriteria"/>
			    </font>
			</th>
		    </tr>
		</c:if>
		<c:if test='${currSelector.accessFlag!="inq"}'>
		    <tr bgcolor=turquoise>
			<th align=center height="19" colspan="2">
			    <font size="3">
				<stripes:label for="currency"/> <stripes:label for="maint"/>
				<stripes:label for="selCriteria"/>
			    </font>
		    </tr>
		</c:if>
		<tr bgcolor=turquoise>
			<th align=center height="19" colspan="2">
				<font size="3"><stripes:label for="currency"/></font></TH>
		</tr>
		<td>
		    <select size="1" name="currSelector.currFrom">
			<option selected>ALL</option>
			<c:forEach items="${currSelector.currList}" var="curr">
			    <c:if test='${currSelector.currFrom==curr}'>
				<option selected><c:out value='${currSelector.currFrom}' /></option>
			    </c:if>
			    <c:if test='${currSelector.currFrom!=curr}'>
				<option> <c:out value="${curr}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td>
		    <select size="1" name="currSelector.currTo">
			<option selected>NONE</option>
			<c:forEach items="${currSelector.currList}" var="curr">
			    <c:if test='${currSelector.currTo==curr}'>
				<option selected><c:out value='${currSelector.currTo}' /></option>
			    </c:if>
			    <c:if test='${currSelector.currTo!=curr}'>
				<option> <c:out value="${curr}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </table>

	    <c:if test='${currSelector.actionFlag!=""}'>
		<div id="selres">
		    <table class="sortable" colspan='6' width='80%' align='center' border='1'
		    	   bgcolor=lightgreen height='39'>
<%--
			<c:if test='${currSelector.actionFlag=="inq"}'>
			    <tr bgcolor=bluegreen>
				<th class="header" align=center height=19 colspan=7>
				    <font size=3 color=blue>
					<stripes:label for="currency"/> <stripes:label for="inquiry"/>
					<stripes:label for="selResults"/>
				    </font>
				</th>
			    </tr>
			</c:if>
			<c:if test='${currSelector.actionFlag!="inq"}'>
			    <tr bgcolor=bluegreen>
				<th class="header" align=center height=19 colspan=7>
				    <font size=3 color=blue>
					<stripes:label for="currency"/> <stripes:label for="maint"/>
					<stripes:label for="selResults"/>
				    </font>
				</th>
			    </tr>
			</c:if>
--%>
			    <tr>
				<th align='left' width='3%' height=15 colspan=1>
				    <font size=2><stripes:label for="recNum"/></font>
				</th>
				<th align='left' width='3%' height=15 colspan=1>
				    <font size=2><stripes:label for="currency"/></font>
				</th>
				<th align='left' width='1%' height=15 colspan=1>
				    <font size=2><stripes:label for="country"/></font>
				</th>
				<th align='left' width='5%' height=15 colspan=1>
				    <font size=2><stripes:label for="name"/></font>
				</th>
				<th align='left' width='5%' height=15 colspan=1>
				    <font size=2><stripes:label for="number"/></font>
				</th>
				<th align='left' width='5%' height=15 colspan=1>
				    <font size=2><stripes:label for="minorUnit"/></font>
				</th>
			    </tr>
			    <c:forEach items="${currSelector.currRows}" var="CDetail" varStatus="idx0">
			        <tr>
				    <td><c:out value="${idx0.index+1}"/></td>
				    <td><c:out value="${CDetail.currCode}"/></td>
				    <td><c:out value="${CDetail.currCountry}"/></td>
				    <td><c:out value="${CDetail.currName}"/></td>
				    <td><c:out value="${CDetail.currNumber}"/></td>
				    <td><c:out value="${CDetail.currMinorUnit}"/></td>
				</tr>
			    </c:forEach>
		    </table>
		</div>
	    </c:if>
	    <center>
	    <stripes:errors />
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${currSelector.accessFlag!="inq"}'>
		<stripes:submit name="NewCurrency" value="New Currency"/>
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
