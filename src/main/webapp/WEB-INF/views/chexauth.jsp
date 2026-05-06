<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Chex.action">
<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	<h1><stripes:label for="authorize"/> <stripes:label for="checks"/></h1>

    <c:if test="${chexSelector.detail_count!=0}" >
	<table colspan='2' width='20%' align='center' border='1' bgcolor=lightgreen height='19'>
	    <tr bgcolor=bluegreen colspan=2>
		<th align=center height=15 colspan=1>
		    <font size=2><stripes:label for="rowcount"/></font>
		</th>
		<td align="center" bgcolor='lightyellow' width='20%' height='19'><b>
		    <c:out value="${chexSelector.detail_count}"/></b>
		</td>
		<th align=center height=15 colspan=1>
		    <font size=2><stripes:label for="totalAmount"/></font>
		</th>
		<td align="center" bgcolor='lightyellow' width='20%' height='19'><b>
		    <c:out value="${chexSelector.detailAmount}"/></b>
		</td>
	    </tr>
	</table>

	<table colspan='8' width='85%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr bgcolor=bluegreen>
		<th align=center height=19 colspan=8>
		    <font size=3 color=blue>
		    	<stripes:label for="checks"/> <stripes:label for="awaitAuth"/>
		    </font>
		</th>
	    </tr>
	    <tr bgcolor=bluegreen>
		<th align='center' width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="processDate"/></font>
		</th>
		<th align='center' width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="account"/></font>
		</th>
		<th align='right' width='3%' height=15 colspan=1>
		    <font size=2>
		    	<stripes:label for="check"/> <stripes:label for="number"/>
		    </font>
		</th>
		<th align='right' width='5%' height=15 colspan=1>
		    <font size=2><stripes:label for="amount"/></font>
		</th>
		<th align='center' width='20%' height=15 colspan=1>
		    <font size=2><stripes:label for="payee"/></font>
		</th>
		<th align='center' width='3%' height=15>
		    <font size=2><stripes:label for="status"/></font>
		</th>
		<th align='center' width='5%' height=15 colspan=2>
		    <font size=2><stripes:label for="authRej"/></font>
		</th>
	    </tr>

	<c:forEach items="${chexSelector.checkrows}" var="chexDetail">
	    <tr>
		<td align="center" bgcolor='lightyellow' width='5%' height='19'>
		    <c:out value="${chexDetail.chex_proc_date}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'><b>
		    <c:out value="${chexDetail.chex_account_num}"/></b>
		</td>
		<td align="right" bgcolor='lightyellow' width='5%' height='19'>
		    <c:out value="${chexDetail.chex_check_num}"/>
		</td>
		<td align="right" bgcolor='lightyellow' width='5%' height='19'>
		    <c:out value="${chexDetail.chex_check_amount_disp}"/>
		</td>
		<td  align='center' bgcolor='lightyellow' width='20%' height='19'>
		    <c:out value="${chexDetail.chex_payee}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='5%' height='19'>
		    <c:out value="${chexDetail.chex_check_status}"/>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <stripes:link href="/econtroller/inclear/actions/Chex.action" event="AuthRejCheck">
			Reject
			<stripes:param name="account_number" value="${chexDetail.chex_account_num}"/>
			<stripes:param name="check_number" value="${chexDetail.chex_check_num}"/>
			<stripes:param name="check_unique_isn" value="${chexDetail.chex_unique_isn}"/>
			<stripes:param name="authrej" value="Rejchex"/>
		    </stripes:link>
		</td>
		<td align="center" bgcolor='lightyellow' width='10%' height='19'>
		    <stripes:link href="/econtroller/inclear/actions/Chex.action" event="AuthRejCheck">
			Authorize
			<stripes:param name="account_number" value="${chexDetail.chex_account_num}"/>
			<stripes:param name="check_number" value="${chexDetail.chex_check_num}"/>
			<stripes:param name="check_unique_isn" value="${chexDetail.chex_unique_isn}"/>
			<stripes:param name="authrej" value="Authchex"/>
		    </stripes:link>
		</td>
	    </tr>
	</c:forEach>
	</table>
	<p align="center"></p>
	<table colspan='2' width='20%' align='center' border='1' bgcolor=lightgreen height='19'>
	    <tr bgcolor=bluegreen colspan=2>
		<th align=center height=15 colspan=1>
		    <font size=2><stripes:label for="rowcount"/></font>
		</th>
		<td align="center" bgcolor='lightyellow' width='20%' height='19'><b>
		    <c:out value="${chexSelector.detail_count}"/></b>
		</td>
		<th align=center height=15 colspan=1>
		    <font size=2><stripes:label for="totalAmount"/></font>
		</th>
		<td align="center" bgcolor='lightyellow' width='20%' height='19'><b>
		    <c:out value="${chexSelector.detailAmount}"/></b>
		</td>
	    </tr>
	</table>
    </c:if>
<%--												--%>
<%--	Submit buttons area to click on to take the next step.					--%>
<%--	Any errors will appear between the data table and the buttons.				--%>
<%--												--%>

     <center>
	<stripes:errors />
	<c:if test='${chexSelector.detail_count!="0"}'>
	    <c:if test='${chexSelector.actionFlag==""}'>
		<stripes:submit name="AuthoriseAll" value='Authorize All Checks' />
		<stripes:submit name="RejectAll" value='Reject All Checks' />
	    </c:if>
	</c:if>
	<c:if test='${chexSelector.actionFlag=="authall_confirm"}'>
	    <stripes:submit name="AuthAllConfirm" value="Confirm Authorize ALL"/>
	</c:if>
	<c:if test='${chexSelector.actionFlag=="rejall_confirm"}'>
	    <stripes:submit  name="RejAllConfirm"  value="Confirm Reject ALL"/>
	</c:if>
    </center>


<%--
	*************************************************************************************************
	*************************************************************************************************
	*************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
