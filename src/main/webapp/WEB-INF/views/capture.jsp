<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/PCcapture/actions/PCCapture.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${pccaptureSelector.accessFlag=="inq"}'>
	    <h1>
		<stripes:label for="remote"/> <stripes:label for="depositCapture"/> 
		<stripes:label for="inquiry"/>
	    </h1>
	 </c:if>
	<c:if test='${pccaptureSelector.accessFlag!="inq"}'>
	    <h1>
		<stripes:label for="remote"/> <stripes:label for="depositCapture"/> 
		<stripes:label for="maint"/>
	    </h1>
	</c:if>

    <div id="detail">
    <table align="center" border="1" cellspacing="1" cellpadding="1" height="3" width="40%">
	<th class="header" align=center height="19" colspan="3">
	    <font size="3">Remote 
	    	<c:if test='${pccaptureSelector.accessFlag=="inq"}'>
		    <stripes:label for="depositCapture"/> <stripes:label for="inquiry"/>
		    <stripes:label for="selCriteria"/>
		</c:if>
	    	<c:if test='${pccaptureSelector.accessFlag!="inq"}'>
		    <stripes:label for="depositCapture"/> <stripes:label for="maint"/>
		    <stripes:label for="selCriteria"/>
		</c:if>
	    </font></TH>
	<tr>
	    <th align=center height="19" colspan="1"></TH>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="from"/></font>
	    </th>
	    <th class="header" align=center height="19" colspan="1">
		<font size="3"><stripes:label for="to"/></font>
	    </th>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="batch"/></font></TH>
	    <td>
		<select size="1" name="captureSelector.batchIdFrom">
		    <c:if test='${pccaptureSelector.batchIdFrom==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.batchIdFrom!=""}'>
			<option selected><c:out value="${pccaptureSelector.batchIdFrom}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.batList}" var="batchfrom">
			<option> <c:out value="${batchfrom}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="captureSelector.batchIdTo">
		    <c:if test='${pccaptureSelector.batchIdTo==""}'>
			<option selected>NONE</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.batchIdTo!=""}'>
			<option selected><c:out value="${pccaptureSelector.batchIdTo}"/></option>
			<option>NONE</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.batList}" var="batchto">
			<option> <c:out value="${batcho}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="amount"/></font></TH>
	    <td>
		<select size="1" name="captureSelector.checkAmtFrom">
		    <c:if test='${pccaptureSelector.checkAmtFrom==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkAmtFrom!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkAmtFrom}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.amtList}" var="amtfrom">
			<option> <c:out value="${amtfrom}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="captureSelector.checkAmtTo">
		    <c:if test='${pccaptureSelector.checkAmtTo==""}'>
			<option selected>NONE</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkAmtTo!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkAmtTo}"/></option>
			<option>NONE</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.amtList}" var="amtTo">
			<option> <c:out value="${amtTo}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="processDate"/></font></TH>
	    <td>
		<select size="1" name="captureSelector.checkDateFrom">
		    <c:if test='${pccaptureSelector.checkDateFrom==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkDateFrom!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkDateFrom}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.dateList}" var="datefrom">
			<option> <c:out value="${datefrom}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="captureSelector.checkDateTo">
		    <c:if test='${pccaptureSelector.checkDateTo==""}'>
			<option selected>NONE</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkDateTo!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkDateTo}"/></option>
			<option>NONE</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.dateList}" var="dateTo">
			<option> <c:out value="${dateTo}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="check"/> <stripes:label for="number"/></font></TH>
	    <td>
		<select size="1" name="captureSelector.checkNumFrom">
		    <c:if test='${pccaptureSelector.checkNumFrom==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkNumFrom!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkNumFrom}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.cnumList}" var="numFrom">
			<option> <c:out value="${numFrom}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="captureSelector.checkNumTo">
		    <c:if test='${pccaptureSelector.checkNumTo==""}'>
			<option selected>NONE</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkNumTo!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkNumTo}"/></option>
			<option>NONE</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.cnumList}" var="numTo">
			<option> <c:out value="${numTo}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	</tr>
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="payee"/></font></TH>
	    <td>
		<select size="1" name="captureSelector.checkPayeeFrom">
		    <c:if test='${pccaptureSelector.checkPayeeFrom==""}'>
			<option selected>ALL</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkPayeeFrom!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkPayeeFrom}"/></option>
			<option>ALL</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.payeeList}" var="payeeFrom">
			<option> <c:out value="${payeeFrom}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	    <td>
		<select size="1" name="captureSelector.checkPayeeTo">
		    <c:if test='${pccaptureSelector.checkPayeeTo==""}'>
			<option selected>NONE</option>
		    </c:if>
		    <c:if test='${pccaptureSelector.checkPayeeTo!=""}'>
			<option selected><c:out value="${pccaptureSelector.checkPayeeTo}"/></option>
			<option>NONE</option>
		    </c:if>
		    <c:forEach items="${pccaptureSelector.payeeList}" var="payeeTo">
			<option> <c:out value="${payeeTo}"/> </option>
		    </c:forEach>
		</select>
	    </td>
	</tr>
<%--
	<tr>
	    <th align=center height="19" colspan="1">
		<font size="3"><stripes:label for="status"/></font></TH>
	    <td>
	    </td>
	    <td>
	    </td>
	</tr>
--%>
</table>
</div>
<c:if test='${pccaptureSelector.actionFlag!=""}'>
<c:if test='${pccaptureSelector.detail_count!=0}'>
<%--													--%>
<%--	This is the table of selected data. Appears after user is has made the the selection and clicks	--%>
<%--	on the view button. The MODIFY link appears each row of data which to click to modify data.	--%>
<%--													--%>
<p align="center"></p>
	<stripes:submit name="View" value="View"/>
<br/>
<br/>
<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${pccaptureSelector.detail_count}"/></b>
	    </td>
<%--
	    <th>
		<stripes:label for="totalamount"/>
	    </th>
	    <td>
		<b><c:out value="${pccaptureSelector.detailAmount}"/></b>
	    </td>
--%>
	</tr>
</table>
</div>

<%--
	<c:out value="${pccaptureSelector.rowStart}"/>
	<c:out value="${pccaptureSelector.rowEnd}"/>
	<c:out value="${pccaptureSelector.rowsDisplayed}"/>
--%>
	<h3>
	<c:out value="Displaying "/>
	<c:out value="${pccaptureSelector.rowStart+1}"/>
	<c:out value=" to "/>
	<c:out value="${pccaptureSelector.rowEnd}"/>
	<c:out value=" of "/>
	<c:out value="${pccaptureSelector.detail_count}"/>
	<c:out value=" rows "/>
	<c:if test='${pccaptureSelector.rowEnd!=captureSelector.detail_count}'>
	    <stripes:submit name="Next" value="Next"/>
	</c:if>
	<c:if test='${pccaptureSelector.rowEnd==captureSelector.detail_count}'>
	    <stripes:submit name="Next" value="Next" disabled="true"/>
	</c:if>
<%--
	<c:out value="${pccaptureSelector.rowsDisplayed}"/>
	<c:out value="${pccaptureSelector.rowsDispStr}"/>
--%>
	<stripes:text name="captureSelector.rowsDispStr" size="4" maxlength="4"
		value="${pccaptureSelector.rowsDispStr}"/>
	<c:if test='${pccaptureSelector.rowStart!=0}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	<c:if test='${pccaptureSelector.rowStart==0}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	</h3>
	<br/>
<table class="sortable" colspan='8' width='95%' align='center' border="1" cellspacing="1" cellpadding="1">
	<tr>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="batch"/> <stripes:label for="id"/>
		</font>
	    </th>
	    <th align='center' width='2%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="check"/> <stripes:label for="number"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="amount"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="micr"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="issueDate"/>
		</font>
	    </th>
	    <th align='right' width='15%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="payee"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="payeeAcct"/>
		</font>
	    </th>
	    <th align='center' width='5%' height=15>
		<font size=2><stripes:label for="details"/></font>
	    </th>
	</tr>
<%--	
	<c:out value="${pccaptureSelector.rowStart}"/>
	<c:out value="${pccaptureSelector.rowEnd}"/>
	<c:out value="${pccaptureSelector.rowsDisplayed}"/>
--%>
	<c:forEach items="${pccaptureSelector.captureRows}" var="PCCaptureDetail" varStatus="idx0" 
		   end="${pccaptureSelector.rowEnd-1}" begin="${pccaptureSelector.rowStart}">
	  <tr>
	    <td align="center" bgcolor='lightyellow' width='2%' height='19'>
		<c:out value="${idx0.index+1}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='7%' height='19'>
		<c:out value="${PCCaptureDetail.batchId}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
		<c:out value="${PCCaptureDetail.checkAmt}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='6%' height='19'><b>
		<c:out value="${PCCaptureDetail.checkdate}"/></b>
	    </td>
	    <td align="right" bgcolor='lightyellow' width='6%' height='19'>
		<c:out value="${PCCaptureDetail.checkNum}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='15%' height='19'>
		<c:out value="${PCCaptureDetail.checkPayee}"/>
	    </td>
	    <td align="center" bgcolor='lightyellow' width='10%' height='19'>
		<c:if test='${pccaptureSelector.accessFlag=="inq"}'>
		    <stripes:link href="/econtroller/capture/actions/Capture.action" event="Details">
			Details
			    <stripes:param name="recIndex" value="${idx0.index}"/>
		    </stripes:link>
		</c:if>
		<c:if test='${pccaptureSelector.accessFlag!="inq"}'>
		    <stripes:link href="/econtroller/capture/actions/Capture.action" event="Modify">
			Modify
			    <stripes:param name="procDate" value="${PCCaptureDetail.procDate}"/>
			    <stripes:param name="imageId" value="${PCCaptureDetail.batchId}"/>
			    <stripes:param name="recIndex" value="${idx0.index}"/>
		    </stripes:link>
		</c:if>
	    </td>
	  </tr>
	</c:forEach>
</table>

<div id="totals">
<table colspan='4' align='center'>
	<tr>
	    <th>
		<stripes:label for="rowcount"/>
	    </th>
	    <td>
		<b><c:out value="${pccaptureSelector.detail_count}"/></b>
	    </td>
<%--
	    <th>
		<stripes:label for="totalAmount"/>
	    </th>
	    <td>
		<b><c:out value="${pccaptureSelector.detailAmount}"/></b>
	    </td>
--%>
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
	<stripes:errors />
	<stripes:submit name="View" value="View"/>
    </center>


<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
