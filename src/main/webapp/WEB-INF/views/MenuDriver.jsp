<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/actions/Menu.action">
<%--		<c:out value='${user.userId}'/>	--%>
<%--		<c:out value="${rowstat.index}"/>	--%>
	    <c:if test='${user.userProd==" "}'>
		<table class='FORM'>
		    <c:if test='${user.userProd==" "}'>
			<tr>
			    <th align='center' bgcolor='steelblue'><font size='4'>
				<stripes:label for='availableApps'/>
			    </th>
			</tr>
		    </c:if>
		    <c:forEach items="${myProds.productrows}" var="productDetail" varStatus="rowstat">
			<c:if test='${productDetail.product_id!=""}'>
			    <c:if test='${productDetail.product_menu_id==""}'>
				<c:if test='${productDetail.product_pmf_link!="NA"}'>
				    <c:if test='${productDetail.product_pmf_link!=""}'>
					<tr align='center'>
					    <td class='FORM'><font size='3'>
						<stripes:link href="${productDetail.product_pmf_link}">
						    <c:out value="${productDetail.product_pmf_desc}"/> 
						</stripes:link>
					    </td>
					</tr>
				    </c:if>
				</c:if>
			    </c:if>
			</c:if>
		    </c:forEach>
<%--
		    <tr align='center'>
			<td class='FORM'><font size='3'><a href='http://10.0.0.8/sav'>webFiche</a></td>
		    </tr>
--%>
		    <tr align='center'>
			<td  class='FORM'><font size='3'>
			    <stripes:link beanclass='${myProds.logOff}' event="logout">
				Logout</stripes:link>
			</td>
		    </tr>
		</table>
	    </c:if>
	    <c:if test='${user.userProd!=" "}'>
	    	<h1>
			<c:out value="Please select a function from the Navigation pane below."/> 
		</h1>
		<%--
	        <logic:equal name="myProds" property="param_prod" value="A">
		    <%@ include file="prodA.jsp" %>
		</logic:equal>
	        <logic:equal name="myProds" property="param_prod" value="B">
		    <%@ include file="prodB.jsp" %>
		</logic:equal>
	        <logic:equal name="myProds" property="param_prod" value="C">
		    <%@ include file="prodC.jsp" %>
		</logic:equal>
	        <logic:equal name="myProds" property="param_prod" value="D">
		    <%@ include file="prodD.jsp" %>
		</logic:equal>
	        <logic:equal name="myProds" property="param_prod" value="E">
		    <%@ include file="prodE.jsp" %>
		</logic:equal>
		 --%>
	    </c:if>
<%--
		<br/>
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
