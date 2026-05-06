<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/Product.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="product"/> <stripes:label for="maintInq"/></h1>
	<table align="center" border="1" width="27%">
	    <c:if test='${prodSelector.accessFlag=="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="3">
			<font size="3">
			    <stripes:label for="product"/></font> <stripes:label for="inquiry"/>
			    <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<tr bgcolor=turquoise>
		    <th align=center height="19" colspan="3">
			<font size="3">
			    <stripes:label for="product"/></font> <stripes:label for="inquiry"/>
			    <stripes:label for="selCriteria"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <tr bgcolor=turquoise>
		<th align=center height="15">
		    <font size="3"><stripes:label for="productId"/></font></TH>
		<th align=center height="15">
		    <font size="3"><stripes:label for="menuId"/></font></TH>
		<th align=center height="15">
		    <font size="3"><stripes:label for="funcId"/></font></TH>
	    </tr>
	    <tr>
	    	<td>
		    <select size="1" name="prodSelector.product_id_sel">
			<option>ALL</option>
			<c:forEach items="${prodSelector.prodNames}" var="prods">
			    <c:if test='${prodSelector.product_id_sel==prods.key}' >
			    	  <option selected value='<c:out value="${prods.key}"/>'> 
				  	  <c:out value="${prods.value}"/> </option>
			    </c:if>
			    <c:if test='${prodSelector.product_id_sel!=prods.key}' >
			    	  <option value='<c:out value="${prods.key}"/>'> 
				  	  <c:out value="${prods.value}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
		<td align="center" height="19">
		    <stripes:text name="prodSelector.product_menu_id_sel" value="${prodSelector.product_menu_id_sel}"/>
		</td>
		<td align="center" height="19">
		    <stripes:text name="prodSelector.product_menu_func_id_sel" value="${prodSelector.product_menu_func_id_sel}"/>
		</td>
	    </tr>
	</table>

<c:if test='${prodSelector.actionFlag!=""}'>
    <center>
    <stripes:submit name="View" value="View"/>
    <c:if test='${prodSelector.accessFlag!="inq"}'>
	<stripes:submit name="New" value="New Product"/>
    </c:if>
    </center>
    <BR/>

<div id="selres">
    <table colspan='8' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr bgcolor=bluegreen>
	    <c:if test='${prodSelector.accessFlag=="inq"}'>
		<th class="header" align=center height=19 colspan=8>
		    <font size=3 color=blue>
			<stripes:label for="product"/> <stripes:label for="inquiry"/>
			<stripes:label for="selResults"/>
		    </font>
		</th>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<th class="header" align=center height=19 colspan=8>
		    <font size=3 color=blue>
			<stripes:label for="product"/> <stripes:label for="maint"/>
			<stripes:label for="selResults"/>
		    </font>
		</th>
	    </c:if>
	</tr>
	<tr bgcolor=bluegreen>
	    <th align='left' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="pnemonic"/></font>
	    </th>
	    <th align='left' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="productId"/></font>
	    </th>
	    <th align='left' width='4%' height=15 colspan=1>
		<font size=2><stripes:label for="menuId"/></font>
	    </th>
	    <th align='left' width='2%' height=15 colspan=1>
		<font size=2><stripes:label for="funcId"/></font>
	    </th>
	    <th align='left' width='20%' height=15 colspan=1>
		<font size=2><stripes:label for="description"/></font>
	    </th>
	    <th align='left' width='20%' height=15>
		<font size=2><stripes:label for="link"/></font>
	    </th>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<th align='center' width='5%' height=15>
		    <font size=2><stripes:label for="delete"/></font>
		</th>
		<th align='center' width='5%' height=15>
		    <font size=2><stripes:label for="modify"/></font>
		</th>
	    </c:if>
	</tr>
    <c:forEach items="${prodSelector.productrows}" var="prodDetail">
	  <tr>
	    <td align="left" bgcolor='lightyellow' width='2%' height='19' size=2>
	      <c:out value="${prodDetail.product_pnemonic}"/>
	    </td>
	    <td align="left" bgcolor='lightyellow' width='2%' height='19'>
	      <c:out value="${prodDetail.product_id}"/>
	    </td>
	    <td align="left" bgcolor='lightyellow' width='4%' height='19'>
	      <c:out value="${prodDetail.product_menu_id}"/>
	    </td>
	    <td align="left" bgcolor='lightyellow' width='2%' height='19'>
	      <c:out value="${prodDetail.product_menu_func_id}"/>
	    </td>
	    <td  align='left' bgcolor='lightyellow' width='30%' height='19'>
	      <c:out value="${prodDetail.product_pmf_desc}"/>
	    </td>
	    <td align="left" bgcolor='lightyellow' width='30%' height='19'>
	      <c:out value="${prodDetail.product_pmf_link}"/>
	    </td>
	    <c:if test='{productSelector.accessFlag=="inq"}'>
		<td align="left" bgcolor='lightyellow' width='30%' height='19'>
		</td>
		<td align="left" bgcolor='lightyellow' width='30%' height='19'>
		</td>
	    </c:if>
	    <c:if test='${prodSelector.accessFlag!="inq"}'>
		<td align="center" bgcolor='lightyellow' width='5%' height='19'>
		    <stripes:link href="/econtroller/sysadmin/actions/Product.action" event="Delete">
			Delete
			<stripes:param name="prodId" value="${prodDetail.product_id}"/>
			<stripes:param name="prodMenuId" value="${prodDetail.product_menu_id}"/>
			<stripes:param name="prodMenuFuncId" value="${prodDetail.product_menu_func_id}"/>
		    </stripes:link>
		</td>
		<td align="center" bgcolor='lightyellow' width='5%' height='19'>
		    <stripes:link href="/econtroller/sysadmin/actions/Product.action" event="Modify">
			Modify
			<stripes:param name="prodId" value="${prodDetail.product_id}"/>
			<stripes:param name="prodMenuId" value="${prodDetail.product_menu_id}"/>
			<stripes:param name="prodMenuFuncId" value="${prodDetail.product_menu_func_id}"/>
		    </stripes:link>
		</td>
	    </c:if>
	  </tr>
    </c:forEach>
    </table>
</div>
</c:if>

    <center>
    <stripes:errors/>
    <stripes:submit name="View" value="View"/>
    <c:if test='${prodSelector.accessFlag!="inq"}'>
	<stripes:submit name="New" value="New Product"/>
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
