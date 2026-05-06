<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/LoadChex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
	<c:out value="${ctr0.index}"/>
	<c:out value="${ctr.index}"/>
	<c:out value="${idx.index}"/>
	<c:out value="${ctr0.index}"/>
	<c:out value='${StDetail.statField1}'/>
--%>
<c:forEach var="StDetail" items="${statSelector.statRows}" varStatus="ctr0">
    <c:if test='${StDetail.newFile==true}'>
	<h1>
	    Check Validation Status
	</h1>
	<div id="summary">
	    <table colspan=2 width=50% align=center border='1' bgcolor=bluegreen height='146'>
	    <tr>
		<th align='center' bgColor='steelblue' height='7'  colspan='2'>
		    <font color='#00FFFF' size='3'>
	               	Check Validation Status (Cumulative)
		    </font>
	    	</th>
	    </tr>
		<tr>
		    <th class="header"><font size='3'>Result</font></th>
		    <th align=center width=9% height='15'><font size='3'>Count</font></th>
		</tr>
		<tr>
		    <td align=center>Invalid Account</td>
		    <td align=right class='red'><font color='white' size='3'>
			<b><c:out value="${StDetail.statField1}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Fraud Alert</td>
		    <td align=right class='red'><font color='white' size='3'>
			<b><c:out value="${StDetail.statField2}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Missing Check Number/Amount</td>
		    <td align=right class='red'><font color='white' size='3'><b>
			<c:out value="${StDetail.statField3}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Missing/Possible Posi Pay</td>
		    <td align=right class=orange><font color='black' size='3'><b>
			<c:out value="${StDetail.statField4}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Stale Check/s</td>
		    <td align=right class=lightgreen><font color='black' size='3'><b>
			<c:out value="${StDetail.statField5}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Cleared Check/s</td>
		    <td align=right class=lightgreen><font color='black' size='3'><b>
			<c:out value="${StDetail.statField6}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Possible Stop/Stop Payment</td>
		    <td align=right class=lightgreen><font color='black' size='3'><b>
			<c:out value="${StDetail.statField7}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Exceeded Authorized Limit</td>
		    <td  align=right class=yellow><font color='black' size='3'><b>
			<c:out value="${StDetail.statField8}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Await Authorization</td>
		    <td  align=right class=yellow><font color='black' size='3'><b>
			<c:out value="${StDetail.statField9}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Completed</td>
		    <td  align=right class=lightgreen><font color='black' size='3'><b>
			<c:out value="${StDetail.statField10}"/></font></b></td>
		</tr>
		<tr>
		    <td align=center>Total Checks</td>
		    <td  align=right class='skyblue'><font color='black' size='3'><b>
			<c:out value="${StDetail.totalChecks}"/></font></b></td>
		</tr>
	    </table>
	</div>
	<h1>Data Load Statistics</h1>
    </c:if> 	     <%-- END <c:if test='${StDetail.newFile==true}'>--%>

<%-- Start file header 		--%>

	<c:if test='${StDetail.statField1=="File Header"}'>
	    <table colspan=5 width=100% align=center border='1' bgcolor=bluegreen height='44'>
            	<tr>
		    <th align=center width=15% height='13'><font size='2'>Record type</font></th>
		    <th align=center width=15% height='13'><font size='2'>Receiving ABA</font></th>
		    <th align=center width=15% height='13'><font size='2'>Originating ABA</font></th>
		    <th align=center width=15% height='13'><font size='2'>Process Date</font></th>
		    <th align=center width=30% height='13'><font size='2'>Destination Institution Name</font></th>
            	</tr>
         	<tr>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField1}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField2}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField3}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField4}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField5}"/></td>
		</tr>
	    </table>
	</c:if>
<%--     End File Header		--%>

<%-- Start Cash Letter Header>		--%>
	<c:if test='${StDetail.statField1=="Cash Letter Header"}'>
	    <table colspan=6 width=100% align=center border='1' bgcolor=bluegreen height='44'>
            	<tr>
		    <th align=center width=15% height='13'><font size='2'>Record type</font></th>
		    <th align=center width=15% height='13'><font size='2'>Receiving ABA</font></th>
		    <th align=center width=15% height='13'><font size='2'>Originating ABA</font></th>
		    <th align=center width=15% height='13'><font size='2'>Cash Letter Business Date</font></th>
		    <th align=center width=30% height='13'><font size='2'>Cash Letter Creation Date</font></th>
		    <th align=center width=10% height='13'><font size='2'>Time</font></th>
            	</tr>
		<tr>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField1}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField2}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField3}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField4}"/></td>
		    <td align=center  height='19'>
			<c:out value="${StDetail.statField5}"/></td>
		    <td align=center  height='19'>
		        <c:out value="${StDetail.statField6}"/></td>
		</tr>
	    </table>
      </c:if>
<%-- End index value 2		--%>

	<c:if test='${(StDetail.statField1=="Bundle Header Record")}'>
	    <table colspan=6 width=100% align=center border='1' bgcolor=bluegreen height='43'>
	</c:if>
	<c:if test='${(StDetail.statField1=="Bundle Header Record") ||
	      		(StDetail.statField1=="Data Load Statistics") ||
			(StDetail.statField1=="Bundle Control Record")}'>
		<c:if test='${(StDetail.statField1=="Bundle Header Record")}'>
		<tr>
		    <th align=center width=15% height='16'><font size='2'> Record Type</font></th>
		    <th align=center width=15% height='16'><font size='2'> Bundle ID</font></th>
		    <th align=center width=15% height='16'><font size='2'> Bundle Seq</font></th>
		    <th align=center width=15% height='16'><font size='2'> </font></th>
		    <th align=center width=15% height='16'><font size='2'> Check Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> Check Amount</font></th>
		</tr>
		<tr>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField1}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField2}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField3}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="" /></td>
            	    <td  align=center width=15% height='8'><c:out value="" /></td>
           	    <td  align=center width=15% height='8'><c:out value="" /></td>
		</tr>
		</c:if>
		<c:if test='${(StDetail.statField1=="Data Load Statistics")}'>
		<tr>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField1}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField2}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField3}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField5}" /></td>
           	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField6}" /></td>
		</tr>
		</c:if>
		<c:if test='${(StDetail.statField1=="Bundle Control Record")}'>
		<tr>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField1}" /></td>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField2}" /></td>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField3}" /></td>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField4}" /></td>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField5}" /></td>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField6}" /></td>
		</tr>
		</table>
		</c:if>
	</c:if>
    	<c:if test='${StDetail.statField1=="Cash Letter Control"}'>
	    </table>
            <table colspan=6 width=100% align=center border='1' height='43'>
		<tr>
		    <th align=center width=15% height='16'><font size='2'> Record Type</font></th>
		    <th align=center width=15% height='16'><font size='2'> Bundle Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> Cash Letter Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> Total Records</font></th>
		    <th align=center width=15% height='16'><font size='2'> File Records</font></th>
		    <th align=center width=15% height='16'><font size='2'> Cash Letter Total Amount</font></th>
		</tr>
		<tr>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField1}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField2}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField3}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField4}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField5}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField6}" /></td>
		</tr>
	    </table>
	</c:if>
    	<c:if test='${StDetail.statField1=="File Control"}'>
	    </table>
            <table colspan=5 width=100% align=center border='1' height='43'>
		<tr>
		    <th align=center width=15% height='16'><font size='2'> Record Type</font></th>
		    <th align=center width=15% height='16'><font size='2'> Cash Letter Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> Total Record Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> Total Item Count</font></th>
		    <th align=center width=15% height='16'><font size='2'> File Debit Total Amount</font></th>
<%--		    <th align=center width=15% height='16'><font size='2'> Cash Letter Total Amount</font></th>	--%>
		</tr>
		<tr>
		    <td  align=center width=15% height='8'><c:out value="${StDetail.statField1}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField2}" /></td>
<%--           	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField3}" /></td>	--%>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField4}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField5}" /></td>
            	    <td  align=center width=15% height='8'><c:out value="${StDetail.statField6}" /></td>
		</tr>
	    </table>
	</c:if>
</c:forEach>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
