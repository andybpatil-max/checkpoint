<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/LoadChex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
<c:forEach items="${statSelector.statRows}" var="StatDetail" varStatus="ctr">
	<c:if test='${StatDetail.newFile==true}'>
	    <c:if test='${ctr.index!="0"}'>
		<h1>Check Validation Status (Cumulative)</h1>
	    </c:if>
	    <c:if test='${ctr.index=="0"}'>
	    	<h1>Check Validation Status</h1>
	    </c:if>
	    <h3><b>File Loaded: <c:out value="${StatDetail.statField1}"/></b></h3>
	    	<br></br>
	    <div id="summary">
            	<table colspan=2 width=50% align=center border='1' height='146'>
		    <tr>
			<th class="header"><font size='2'>Result</font></th>
			<th align=center width=9% height='15'><font size='2'>Count</font></th>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Invalid Account</td>
			<td align=right class='red'><font color='white'><b>
			    <c:out value="${StatDetail.statField2}"/></font></b></td>
		    </tr>
        	    <tr>
			<td align=center bgcolor=lightyellow>Missing Check Number/Amount</td>
			<td align=right class='red'><font color='white'><b>
			    <c:out value="${StatDetail.statField3}"/></font></b></td>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Missing/Possible Posi Pay</td>
			<td align=right class=orange><font color='black'><b>
			    <c:out value="${StatDetail.statField4}"/></font></b></td>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Stale Check/s</td>
			<td align=right class=lightgreen><font color='black'><b>
			    <c:out value="${StatDetail.statField5}"/></font></b></td>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Cleared Check/s</td>
			<td align=right class=lightgreen><font color='black'><b>
			    <c:out value="${StatDetail.statField6}"/></font></b></td>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Possible Stop/Stop Payment</td>
			<td align=right class=lightgreen><font color='black'><b>
			    <c:out value="${StatDetail.statField7}"/></font></b></td>
		    </tr>
		    <tr>
			<td align=center bgcolor=lightyellow>Exceeded Authorized Limit</td>
			<td  align=right class=yellow><font color='black'><b>
			     <c:out value="${StatDetail.statField8}"/></font></b></td>
		     </tr>
		     <tr>
			<td align=center bgcolor=lightyellow>Await Authorization</td>
			<td  align=right class=yellow><font color='black'><b>
			     <c:out value="${StatDetail.statField9}"/></font></b></td>
		     </tr>
		     <tr>
			<td align=center bgcolor=lightyellow>Completed</td>
			<td  align=right class=lightgreen><font color='black'><b>
			     <c:out value="${StatDetail.statField10}"/></font></b></td>
		     </tr>
		     <tr>
			<td align=center bgcolor=lightyellow>Total Checks</td>
			<td  align=right class='skyblue'><font color='black'><b>
			     <c:out value="${StatDetail.totalChecks}"/></font></b></td>
		     </tr>
		</table>
	    </div>
	</c:if>
	<c:if test='${StatDetail.newFile!=true}'>
		   <c:if test='${StatDetail.statField10=="1"}'>
		       <h1>Data Load Statistics</h1>
		       <table colspan=3 width=50% align=center border='1' bgcolor=bluegreen height='44'>
			<tr>
			    <th align=center width=10% height='13'><font size='2'>Record type</font></th>
			    <th align=center width=10% height='13'><font size='2'>Total Checks</font></th>
			    <th align=center width=10% height='13'><font size='2'>Total Amount</font></th>
		    	</tr>
			<tr>
			    <td><c:out value="${StatDetail.statField1}"/></font></b></td>
			    <td><c:out value="${StatDetail.statField4}"/></font></b></td>
			    <td><c:out value="${StatDetail.statField5}"/></font></b></td>
			</tr>
		   </c:if>
		   <c:if test='${StatDetail.statField10!="1"}'>
			<tr>
			    <td><c:out value="${StatDetail.statField1}"/></font></b></td>
			    <td><c:out value="${StatDetail.statField4}"/></font></b></td>
			    <td><c:out value="${StatDetail.statField5}"/></font></b></td>
			</tr>
		</table>
	    	<br></br>
	    	<hr>
		   </c:if>
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
