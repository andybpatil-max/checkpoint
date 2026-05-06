<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Group.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="userGroup"/> <stripes:label for="management"/></h1>

	<table align="center" border="1" width="27%">
	    <tr bgcolor=turquoise>
		<c:if test='${prodSelector.accessFlag=="inq"}'>
		    <th align=center height="19" colspan="3">
			<font size="3">
			    <stripes:label for="userGroup"/> <stripes:label for="inquiry"/>
			    <stripes:label for="selCriteria"/>
			</font>
		    </TH>
		</c:if>
		<c:if test='${prodSelector.accessFlag!="inq"}'>
		    <th align=center height="19" colspan="3">
			<font size="3">
			    <stripes:label for="userGroup"/> <stripes:label for="maint"/>
			    <stripes:label for="selCriteria"/>
			</font>
		</c:if>
	    </tr>
	    <tr bgcolor=turquoise>
		<th align=center height="15">
		    <font size="3">
			<stripes:label for="productId"/>
		    </font>
		</th>
	    </tr>
	    <tr>
		<td>
		    <select size="1" name="prodSelector.product_id_sel">
			<c:forEach items="${prodSelector.prodNames}" var="prods">
			    <c:if test='${prodSelector.product_id_sel==prods.key}'>
				<option selected value='<c:out value="${prods.key}"/>'>
				    <c:out value="${prods.value}"/></option>
			    </c:if>
			    <c:if test='${prodSelector.product_id_sel!=prods.key}'>
				<option value='<c:out value="${prods.key}"/>'> 
					<c:out value="${prods.value}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	</table>

<c:if test='${prodSelector.actionFlag!=""}'>

    <div id="selres">
	<table colspan='4' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <c:if test='${prodSelector.accessFlag=="inq"}'>
		<tr bgcolor=bluegreen>
		    <th class="header" align=center height=19 colspan=7>
			<font size="3" color=blue>
			    <stripes:label for="userGroup"/> <stripes:label for="inquiry"/>
			    <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<tr bgcolor=bluegreen>
		    <th class="header" align=center height=19 colspan=7>
			<font size="3" color=blue>
			    <stripes:label for="userGroup"/> <stripes:label for="maint"/>
			    <stripes:label for="selResults"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <tr bgcolor=bluegreen>
		<th align='left' width='2%' height=15 colspan=1>
		    <font size=2><stripes:label for="productId"/></font>
		</th>
		<th align='left' width='2%' height=15 colspan=1>
		    <font size=2><stripes:label for="groupId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=2>
		    <font size=2><stripes:label for="action"/></font>
		</th>
	    </tr>
	    <c:forEach items="${groupSelector.grouprows}" var="groupDetail">
		<tr>
		    <td align="left" bgcolor='lightyellow' width='2%' height='19' size=2>
			<c:out value="${groupDetail.gdProd}"/>
		    </td>
		    <td align="left" bgcolor='lightyellow' width='4%' height='19'>
			<c:out value="${groupDetail.gdGroup_id}"/>
		    </td>
			<c:if test='${prodSelector.accessFlag=="inq"}'>
			    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
				<stripes:link href="/econtroller/sysadmin/actions/Group.action" event="Modify">
				    View
				    <stripes:param name="prodId" value="${groupDetail.gdProd}"/>
				    <stripes:param name="groupId" value="${groupDetail.gdGroup_id}"/>
				</stripes:link>
			</c:if>
		    </td>
		    <c:if test='${prodSelector.accessFlag!="inq"}'>
			<c:if test='${groupDetail.gdGroup_id!="MASTER"}'>
			    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
				<stripes:link href="/econtroller/sysadmin/actions/Group.action" event="Modify">
				    Modify
				    <stripes:param name="prodId" value="${groupDetail.gdProd}"/>
				    <stripes:param name="groupId" value="${groupDetail.gdGroup_id}"/>
				</stripes:link>
			    </td>
			    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
				<stripes:link href="/econtroller/sysadmin/actions/Group.action" event="Delete">
				    Delete
				    <stripes:param name="prodId" value="${groupDetail.gdProd}"/>
				    <stripes:param name="groupId" value="${groupDetail.gdGroup_id}"/>
				</stripes:link>
			    </td>
			</c:if>
			<c:if test='${groupDetail.gdGroup_id=="MASTER"}'>
			    <td align="center" bgcolor='lightyellow' width='5%' height='19'>
				<stripes:link href="/econtroller/sysadmin/actions/Group.action" event="Modify">
				    View
				    <stripes:param name="prodId" value="${groupDetail.gdProd}"/>
				    <stripes:param name="groupId" value="${groupDetail.gdGroup_id}"/>
				</stripes:link>
			    </td>
			    <td></td>
			</c:if>
		    </c:if>
		</tr>
	    </c:forEach>
	</table>
    </div>
</c:if>

    <center>
	<stripes:errors/>
	<stripes:submit name="ShowGroups" value="Show Groups"/>
	<c:if test='${prodSelector.accessFlag!="inq"}'>
	    <stripes:submit name="New" value="New Group"/>
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

