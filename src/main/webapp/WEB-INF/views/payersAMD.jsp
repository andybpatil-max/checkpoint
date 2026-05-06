<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/DepositsPayer.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>
	    <stripes:label for="payer"/> <stripes:label for="maint"/>
	</h1>
<%--													--%>
<%--	This is the table of selection fields. Appears first when user is asked to make the selection 	--%>
<%--	of data to view											--%>
<%--													--%>

<div id="detail">
<table colspan='2' width='80%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <c:if test='${payerSelector.accessFlag=="inq"}'>
	    	<th class="header" colspan="2">
		    <stripes:label for="payer"/> <stripes:label for="account"/>
		    <stripes:label for="maint"/>
		</th>
	    </c:if>
	    <c:if test='${payerSelector.accessFlag!="inq"}'>
		<c:if test='${payerSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="payer"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${payerSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="payer"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${payerSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="payer"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${payerSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="payer"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${payerSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="payer"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>

<%--
	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="id"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${payerSelector.actionFlag=="Modify"}'>
		    <stripes:text name='payerDetail.payerid'
			value='${payerDetail.payerid}' size='22' />
		</c:when>
		<c:when test='${payerSelector.actionFlag=="New"}'>
		    <stripes:text name='payerDetail.payerid'
			value='${payerDetail.payerid}' size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='payerDetail.payerid'
			value='${payerDetail.payerid}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
--%>
	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="aba"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${payerSelector.actionFlag=="Modify"}'>
		    <stripes:text name='payerDetail.payeraba'
			value='${payerDetail.payeraba}' size='22' />
		</c:when>
		<c:when test='${payerSelector.actionFlag=="New"}'>
		    <stripes:text name='payerDetail.payeraba'
			value='${payerDetail.payeraba}' size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='payerDetail.payeraba'
			value='${payerDetail.payeraba}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="account"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${payerSelector.actionFlag=="Modify"}'>
		    <stripes:text name='payerDetail.payeracct'
			value='${payerDetail.payeracct}' size='22' />
		</c:when>
		<c:when test='${payerSelector.actionFlag=="New"}'>
		    <stripes:text name='payerDetail.payeracct'
			value='${payerDetail.payeracct}' size='22'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='payerDetail.payeracct'
			value='${payerDetail.payeracct}' size='22' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="name"/></th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${payerSelector.actionFlag=="Modify"}'>
		    <stripes:text name='payerDetail.payername'
			value='${payerDetail.payername}' size='60' />
		</c:when>
		<c:when test='${payerSelector.actionFlag=="New"}'>
		    <stripes:text name='payerDetail.payername'
			value='${payerDetail.payername}' size='60' maxlength='120'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='payerDetail.payername'
			value='${payerDetail.payername}' size='60' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>

	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="country"/></th>
	<td align='left' height='15'>
	    <c:choose>
	    <c:when test='${payerSelector.actionFlag=="Modify"}'>
		<stripes:text name='payerDetail.payercountry'
			value='${payerDetail.payercountry}' size='4'/>
	    </c:when>
	    <c:when test='${payerSelector.actionFlag=="New"}'>
		<stripes:text name='payerDetail.payercountry'
			value='${payerDetail.payercountry}' size='4'/>
	    </c:when>
	    <c:otherwise>
		<stripes:text name='payerDetail.payercountry'
			value='${payerDetail.payercountry}' size='4'/>
	    </c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='40%' height='19'>
		<stripes:label for="payer"/> <stripes:label for="source"/></th>
	<td align='left' height='15'>
	    <c:out value='${payerDetail.payersource}'/>
	     <size='4' readonly='true'/>
<%--
	    <stripes:text name='payerDetail.payercountry'
			value='${payerDetail.payercountry}' size='4' readonly='true'/>
--%>
	</td>
	</tr>
</table>
</div> <%-- detail --%>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value='${payerSelector.actionFlag}'/>							--%>
<%--													--%>
	
    <center>
	<stripes:errors />
	<c:if test='${payerSelector.actionFlag!="inq"}'>
	    <c:if test='${payerSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update Payer Account"/>
	    </c:if>
	    <c:if test='${payerSelector.actionFlag=="Update"}'>
		<stripes:submit name='Confirm' value="Confirm Update Payer Account"/>
	    </c:if>
	    <c:if test='${payerSelector.actionFlag=="Add"}'>
		<stripes:submit name='Confirm' value="Confirm Add Payer Account"/>
	    </c:if>
	    <c:if test='${payerSelector.actionFlag=="Delete"}'>
		<stripes:submit name='DeleteConfirm' value="Confirm Delete Payer Account"/>
	    </c:if>
	    <c:if test='${payerSelector.actionFlag=="New"}'>
		<stripes:submit name='Add' value="Add Payer Account"/>
	    </c:if>
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
