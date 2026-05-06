<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Stoppay.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <c:if test='${stopSelector.accessFlag=="inq"}'>
	<h1><stripes:label for="stopPay"/> <stripes:label for="inquiry"/></h1>
    </c:if>
    <c:if test='${stopSelector.accessFlag!="inq"}'>
	<h1><stripes:label for="stopPay"/> <stripes:label for="maint"/></h1>
    </c:if>

<div id="detail">
    <table colspan='2' width='70%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <c:if test='${stopSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="stopPay"/> <stripes:label for="inquiry"/>
			</font>
		</th>
	    </c:if>
	    <c:if test='${stopSelector.accessFlag!="inq"}'>
		<c:if test='${stopSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="stopPay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${stopSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="stopPay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${stopSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="stopPay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${stopSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="stopPay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${stopSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="stopPay"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="account"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${stopSelector.actionFlag=="New"}'>
		<stripes:text name='stopDetail.stopay_account_num' value='${stopDetail.stopay_account_num}'
			size='22'/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag!="New"}'>
		<stripes:text name='stopDetail.stopay_account_num' value='${stopDetail.stopay_account_num}' 
			size='22' readonly='true'/>
	    </c:if>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="check"/> <stripes:label for="number"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${stopSelector.actionFlag=="New"}'>
		<stripes:text name='stopDetail.stopay_check_num' value='${stopDetail.stopay_check_num}'
			size='22'/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag!="New"}'>
		<stripes:text name='stopDetail.stopay_check_num' value='${stopDetail.stopay_check_num}'
			size='22' readonly='true'/>
	    </c:if>
	</td>
	</tr>

	<c:if test='${stopSelector.actionFlag=="New"}'>
	<tr>
	    <th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b>Ending <stripes:label for="check"/> <stripes:label for="number"/></b>
	    </th>
	<td align='left' width=10% height='19'>
	    <stripes:text name='stopDetail.stopay_check_nume' value='${stopDetail.stopay_check_nume}'
		size='22'/>
	</td>
	</tr>
	</c:if>

	<c:if test='${stopSelector.actionFlag=="Add"}'>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b>Ending <stripes:label for="check"/> <stripes:label for="number"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <stripes:text name='stopDetail.stopay_check_nume' value='${stopDetail.stopay_check_nume}'
		size='22' readonly='true'/>
	</td>
	</tr>
	</c:if>

	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="currency"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_currency' size='3' readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_currency' size='3' readonly='true'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_currency' size='3'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="amount"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_check_amount' size='15'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_check_amount' size='15'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_check_amount' size='15'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="swiftRef"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_swift_ref' size='16'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_swift_ref' size='16'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_swift_ref' size='16'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="issueDate"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_issue_date' size='10' maxlength='10'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_issue_date' size='10' maxlength='10'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_issue_date' size='10' maxlength='10'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="payee"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_payee' size='35'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_payee' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_payee' size='35'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="address1"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr1' size='35'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr1' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_payee_addr1' size='35'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="address2"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr2' size='35'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr2' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_payee_addr2' size='35'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="address3"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr3' size='35'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_payee_addr3' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_payee_addr3' size='35'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="valueDate"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_value_date' size='10' maxlength='10'  readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_value_date' size='10' maxlength='10'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_value_date' size='10' maxlength='10'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="expiryDate"/>
	</th>
	<td align='left' height='19'>
	    <stripes:text name='stopDetail.stopay_expiry_date' size='10' maxlength='10'  readonly='true'/>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="status"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${stopSelector.actionFlag=="Update"}'>
		    <stripes:text name='stopDetail.stopay_status' size='2' readonly='true'/>
		</c:when>
		<c:when test='${stopSelector.actionFlag=="Add"}'>
		    <stripes:text name='stopDetail.stopay_status' size='2' readonly='true'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='stopDetail.stopay_status' size='2'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
    </table>
</div>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--    <c:out value="${stopSelector.actionFlag}"/>							--%>

	<center>
	<stripes:errors/>
	<c:if test='${stopSelector.accessFlag!="inq"}'>
	    <c:if test='${stopSelector.actionFlag=="New"}'>
		<stripes:submit name="Add"  value="Add Stoppay"/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag=="Add"}'>
		<stripes:submit name="Confirm"  value="Confirm Add Stoppay"/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag=="Delete"}'>
		<stripes:submit name="DeleteConfirm" value="Confirm Delete Stoppay"/>
	    </c:if>
	    <c:if test='${stopSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm"  value="Confirm Update Stoppay"/>
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
