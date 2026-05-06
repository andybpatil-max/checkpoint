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

	<div id="detail">
	    <table colspan='2' width='50%' align='center' border='1'>
		<tr bgcolor='bluegreen'>
		    <c:if test='${prodSelector.actionFlag=="Modify"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="modify"/> <stripes:label for="product"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="Delete"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="delete"/> <stripes:label for="product"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="delete_confirm"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="delete"/> <stripes:label for="product"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="add"/> <stripes:label for="product"/>
				</font>
		   	</th>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="modify"/> <stripes:label for="product"/>
				</font>
			</th>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue>
				    <stripes:label for="add"/> <stripes:label for="product"/>
				</font>
		   	</th>
		    </c:if>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="productId"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_id' size='3'
				value='${prodDetail.product_id}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag!="new_prod"}'>
			<stripes:text name='prodDetail.product_id' size='3' readonly='true'
				value='${prodDetail.product_id}'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="menuId"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_menu_id' size='3'
				value='${prodDetail.product_menu_id}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag!="new_prod"}'>
			<stripes:text name='prodDetail.product_menu_id' size='3' readonly='true'
				value='${prodDetail.product_menu_id}'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="funcId"/></th>
		<td align='left' height='15'>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_menu_func_id' size='3'
				value='${prodDetail.product_menu_func_id}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag!="new_prod"}'>
			<stripes:text name='prodDetail.product_menu_func_id' size='3' readonly='true'
				value='${prodDetail.product_menu_func_id}'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="pnemonic"/></b></th>
		<td align='left' height='15'>
		    <c:if test='${prodSelector.actionFlag=="Modify"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="Delete"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="delete_confirm"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6' readonly='true'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
			<stripes:text name='prodDetail.product_pnemonic' size='6' readonly='true'
				value='${prodDetail.product_pnemonic}'/>
		    </c:if>
		</td>
		</tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="description"/></th>
		<td align='left' height='15'>
		    <c:if test='${prodSelector.actionFlag=="Modify"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="Delete"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="delete_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80' readonly='true'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_desc' size='80' readonly='true'
				value='${prodDetail.product_pmf_desc}'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'>
			<stripes:label for="link"/></th>
		<td align='left' height='19'>
		    <c:if test='${prodSelector.actionFlag=="Modify"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80' 
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="Delete"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80'
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="delete_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80'
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="new_prod"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80'
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80' readonly='true'
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		    <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
			<stripes:text name='prodDetail.product_pmf_link' size='80' readonly='true'
				value='${prodDetail.product_pmf_link}'/>
		    </c:if>
		</td>
		</tr>
	    </table>
	</div>

    <center>
     <stripes:errors/>
     <c:if test='${prodSelector.actionFlag=="Modify"}'>
	<stripes:submit  name="Update"  value="Update"></stripes:submit>
     </c:if>
     <c:if test='${prodSelector.actionFlag=="new_prod"}'>
	<stripes:submit name="Add" value="Add"></stripes:submit>
     </c:if>
     <c:if test='${prodSelector.actionFlag=="update_confirm"}'>
	<stripes:submit name="Confirm"  value="Confirm Update Product"></stripes:submit>
     </c:if>
     <c:if test='${prodSelector.actionFlag=="add_confirm"}'>
	<stripes:submit name="Confirm"  value="Confirm Add Product"></stripes:submit>
     </c:if>
     <c:if test='${prodSelector.actionFlag=="delete_confirm"}'>
	<stripes:submit name="ConfirmDelete" value="Confirm Delete"/>
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
