<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Group.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="userGroup"/> <stripes:label for="maintInq"/></h1>

	<table align="center" border="1" width="25%">
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="userGroup"/> <stripes:label for="maint"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="userGroup"/> <stripes:label for="inquiry"/>
			</font>
		    </TH>
		</tr>
	    </c:if>
	    <tr bgcolor=turquoise>
		<th align=center height="15">
		    <font size="3"><stripes:label for="prodId"/></font></TH>
		<th align=center height="15">
		    <font size="3"><stripes:label for="groupId"/></font></TH>
	    </tr>
	    <tr>
		<td align=center>
		    <c:out value="${prodSelector.product_id_sel}"/>
		</td>
		<td align=center>
		    <c:if test='${prodSelector.actionFlag!="new_group"}'>
			<stripes:text name="prodSelector.group_name" value="${prodSelector.group_name}"
										readonly="true"/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="new_group"}'>
			<stripes:text name="prodSelector.group_name" value="${prodSelector.group_name}"/>
		    </c:if>
		</td>
	    </tr>
	</table>

<div id="selres">
    <table colspan='2' width='35%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr bgcolor=bluegreen>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<th class="header" align=center height=19 colspan=7>
		    <font size=3 color=blue>
			<stripes:label for="userGroup"/> <stripes:label for="maint"/>
			<stripes:label for="userGroup.selResults"/>
		    </font>
		</th>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag=="inq"}'>
		<th class="header" align=center height=19 colspan=7>
		    <font size=3 color=blue>
			<stripes:label for="userGroup"/> <stripes:label for="inquiry"/>
			<stripes:label for="userGroup.selResults"/>
		    </font>
		</th>
	    </c:if>
	</tr>
	<tr bgcolor=bluegreen>
	    <th align='left' width='80%' height=15 colspan=1>
		<font size=2><stripes:label for="prodMenuId"/></font>
	    </th>
	    <th align='left' width='80%' height=15 colspan=1>
		<font size=2><stripes:label for="funcId"/></font>
	    </th>
	</tr>
	<c:forEach items="${prodSelector.productrows}" var="PDetail" varStatus="idx0">
	    <tr>
		<td>
		    <c:if test='${PDetail.product_type!="F"}'>
		    	<c:out value='${PDetail.product_pmf_desc}'/>
		    </c:if>
		</td>
		<td align="left" bgcolor='lightyellow' width='1%' height='19'>
		    <c:if test='${PDetail.product_type=="F"}'>
			<stripes:checkbox name="prodSelector.prod_pmf_entitled" 
			     value='${PDetail.product_key}'>
			</stripes:checkbox>
			<c:out value='${PDetail.product_pmf_desc}'/>
		    </c:if>
		</td>
	    </tr>
	</c:forEach>
<%--
	<c:forEach items="${prodSelector.productrows}" var="PDetail" varStatus="idx0">
	    <tr>
		<td  align='left' bgcolor='lightyellow' width='80%' height='19'>
		    <c:out value='${PDetail.product_pmf_desc}'/>
		</td>
		<td align="left" bgcolor='lightyellow' width='1%' height='19'>
		    <c:if test='${PDetail.product_type=="F"}'>
			<stripes:checkbox name="prodSelector.prod_pmf_entitled" 
			     value='${PDetail.product_key}'>
			</stripes:checkbox>
		    </c:if>
		</td>
	    </tr>
	</c:forEach>
--%>
    </table>
</div>

<div>
    <center>
    <stripes:errors/>
<%--
    <c:out value='${prodSelector.actionFlag}'/>
--%>
    <c:if test='${prodSelector.accessFlag!="inq"}'>
	<c:if test='${prodSelector.group_name!="MASTER"}'>
	    <c:if test='${prodSelector.actionFlag==""}'>
	        <stripes:submit name="View" value="View" ></stripes:submit>
	    </c:if>
	    <c:if test='${prodSelector.actionFlag=="View"}'>
		<stripes:submit name="View" value="View" ></stripes:submit>
	    </c:if>
	    <c:if test='${prodSelector.actionFlag=="Modify"}'>
	   	<stripes:submit name="Update" value="Update"></stripes:submit>
	    </c:if>
	
	    <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
		<stripes:submit name='Confirm' value="Update Confirm"></stripes:submit>
	    </c:if>
	
	    <c:if test='${prodSelector.actionFlag=="new_group"}'>
		<stripes:submit name="Update" value="Add Group"></stripes:submit>
	    </c:if>
	    <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
		<stripes:submit name="Confirm" value="Confirm Add Group"></stripes:submit>
	    </c:if>
	    <c:if test='${prodSelector.actionFlag=="Delete"}'>
		<stripes:submit name="ConfirmDelete"  value="Confirm Delete Group"></stripes:submit>
	    </c:if>
	</c:if>
    </c:if>

    <c:if test='${prodSelector.actionFlag==""}'>
	<stripes:submit name='View' value="View" ></stripes:submit>
    </c:if>
    </center>
</div>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
