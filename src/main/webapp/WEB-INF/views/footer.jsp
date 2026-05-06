<%--
<%@ include file="taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/config/global-1.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/config/screen.css"/>
<br>
--%>
<br>
<c:if test='${user.userProd!=" "}'>
    <div class="container my">
	<hr style="color:red;background-color:red;height:1px;margin-right:0;padding-right:0;">
	<c:forEach items="${myProds.productrows}" var="ProductDetail" varStatus="idx0" end="${myProds.lastFunc}" 
			begin="${myProds.offSet0}">
	    <c:if test='${ProductDetail.product_menu_id!=""}'>
		<c:if test='${ProductDetail.product_menu_func_id==""}'>
		    <c:if test='${ProductDetail.product_pmf_link!="NA"}'>
			<c:if test='${ProductDetail.menuRank > "1"}'>
			    </ul>
			    </div>	<!-- span-6 -->
			</c:if>
			<c:if test='${ProductDetail.menuRank=="6"}'>
			    <hr style="color:red;background-color:red;height:1px;margin-right:0;padding-right:0;">
			</c:if>
			<div class="span-6">
			<ul>
			    <h1 class="footerH1"><c:out value="${ProductDetail.product_pmf_desc}"/></h1>
		    </c:if>
		</c:if>
	    </c:if>
	    <c:if test='${ProductDetail.product_menu_func_id!=""}'>
		<c:if test='${ProductDetail.product_pmf_link!="NA"}'>
		    <li>
			<stripes:link beanclass='${ProductDetail.product_pmf_link}'>
			    <c:if test='${ProductDetail.product_pmf_param!=" "}'>
				<stripes:param name="action" value="${ProductDetail.product_pmf_param}"/>
			    </c:if>
			    <c:out value="${ProductDetail.product_pmf_desc}" />
			</stripes:link>
		    </li>
		</c:if>
	    </c:if>
	</c:forEach>
	</ul>
	</div>	<!-- span-6 -->

	<c:if test='${myProds.numMenus=="5"}'>
	    <hr style="color:red;background-color:red;height:1px;margin-right:0;padding-right:0;">
	</c:if>

	<div class="span-6">
	<ul>
	    <h1 class="footerH1">Start Over</h1>
<%--	    <br/> --%>
	    <li><stripes:link beanclass='${myProds.goHome}'>
		<stripes:param name="prod" value=""/>Home</stripes:link>
	    </li>
	    <li><stripes:link beanclass='${myProds.logOff}' event="logout">
				Logout</stripes:link>
	    </li>
	</ul>
	</div>	<!-- span-6 --> 
    </div> 	<!-- container my -->
</c:if>
