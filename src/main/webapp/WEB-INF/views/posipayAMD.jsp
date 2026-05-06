<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/Posipay.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <c:if test='${posiSelector.accessFlag=="inq"}'>
	<h1><stripes:label for="posipay"/> <stripes:label for="inquiry"/></h1>
    </c:if>
    <c:if test='${posiSelector.accessFlag!="inq"}'>
	<h1><stripes:label for="posipay"/> <stripes:label for="maint"/></h1>
    </c:if>

<div id="detail">
    <table colspan='2' width='70%' align='center' border='1'>
	<tr bgcolor='bluegreen'>
	    <c:if test='${posiSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="posipay"/> <stripes:label for="inquiry"/>
			</font>
		</th>
	    </c:if>
	    <c:if test='${posiSelector.accessFlag!="inq"}'>
		<c:if test='${posiSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="posipay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${posiSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="posipay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${posiSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="posipay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${posiSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="posipay"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${posiSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="posipay"/>
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
	    <c:if test='${posiSelector.actionFlag=="New"}'>
		<stripes:text name='posiDetail.pospay_account_num' value='${posiDetail.pospay_account_num}'
			size='22'/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag!="New"}'>
		<stripes:text name='posiDetail.pospay_account_num' value='${posiDetail.pospay_account_num}' 
			size='22' readonly='true'/>
	    </c:if>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<b><stripes:label for="check"/> <stripes:label for="number"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${posiSelector.actionFlag=="New"}'>
		<stripes:text name='posiDetail.pospay_check_num' value='${posiDetail.pospay_check_num}'
			size='22'/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag!="New"}'>
		<stripes:text name='posiDetail.pospay_check_num' value='${posiDetail.pospay_check_num}'
			size='22' readonly='true'/>
	    </c:if>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="currency"/>
	</th>
	<td align='left' height='15'>
		<stripes:text name='posiDetail.pospay_currency' value='${posiDetail.pospay_currency}'
			size='3'/>
<%--
	    <c:choose>
		<c:when test='${posiSelector.actionFlag=="Update"}'>
		    <stripes:text name='posiDetail.pospay_currency' size='3' readonly='true'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_currency' size='3' readonly='true'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_currency' size='3'/>
		</c:otherwise>
	    </c:choose>
--%>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="amount"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_check_amount' size='15'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_check_amount' size='15'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_check_amount' size='15' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_swift_ref' size='16'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_swift_ref' size='16'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_swift_ref' size='16' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_issue_date' size='10' maxlength='10'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_issue_date' size='10' maxlength='10'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_issue_date' size='10' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_payee' size='35'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_payee' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_payee' size='35' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr1' size='35'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr1' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_payee_addr1' size='35' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr2' size='35'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr2' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_payee_addr2' size='35' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr3' size='35'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_payee_addr3' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_payee_addr3' size='35' readonly='true'/>
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
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <stripes:text name='posiDetail.pospay_value_date' size='10' maxlength='10'/>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <stripes:text name='posiDetail.pospay_value_date' size='10' maxlength='10'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_value_date' size='10' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
		<stripes:label for="status"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${posiSelector.actionFlag=="Modify"}'>
		    <select size="1" name="posiDetail.pospay_status">
			<c:if test='${posiDetail.pospay_status=="U"}'>
			    <option value="M">Matched</option>
			    <option Selected value="U">Unmatched</option>
			</c:if>
			<c:if test='${posiDetail.pospay_status!="U"}'>
			    <option Selected value="M">Matched</option>
			    <option value="U">Yes</option>
			</c:if>
		    </select>
		</c:when>
		<c:when test='${posiSelector.actionFlag=="New"}'>
		    <select size="1" name="posiDetail.pospay_status">
			<c:if test='${posiDetail.pospay_status=="U"}'>
			    <option value="M">Matched</option>
			    <option Selected value="U">Unmatched</option>
			</c:if>
			<c:if test='${posiDetail.pospay_status!="U"}'>
			    <option Selected value="M">Matched</option>
			    <option value="U">Yes</option>
			</c:if>
		    </select>
		</c:when>
		<c:otherwise>
		    <stripes:text name='posiDetail.pospay_status' size='2' readonly='true'/>
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
<%--	<c:out value='${posiSelector.actionFlag}'/>							--%>
	<center>
	<stripes:errors/>

	<c:if test='${posiSelector.accessFlag!="inq"}'>
	    <c:if test='${posiSelector.actionFlag=="New"}'>
		<stripes:submit name="Add"  value="Add Posipay"/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag=="Add"}'>
		<stripes:submit name="Confirm"  value="Confirm Add Pospay"/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag=="Delete"}'>
		<stripes:submit name="DeleteConfirm" value="Confirm Delete Posipay"/>
	    </c:if>
	    <c:if test='${posiSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm"  value="Confirm Update Pospay"/>
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
