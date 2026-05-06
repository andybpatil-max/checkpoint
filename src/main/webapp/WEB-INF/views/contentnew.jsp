<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standardscrollctl.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="fastAccess"/></h1>
    <div id="detail">
    </div>
<stripes:errors />
<%-- <div id="caltab"> --%>
    <div id="detail">
<table class="sortable" colspan='9' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr>
	    <th class="header">
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
	    <th class="header">
		<font size=2>
		    <stripes:label for="fileName"/>
		</font>
	    </th>
	    <th class="header">
		<font size=2>
		    <stripes:label for="file"/> <stripes:label for="description"/>
		</font>
	    </th>
	    <th>
		<font size=2><stripes:label for="newerDates"/></font>
	    </th>
	    <th class="header">
		<font size=2><stripes:label for="dates"/></font>
	    </th>
	    <th>
		<font size=2><stripes:label for="priorDates"/></font>
	    </th>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="FDetail" varStatus="idx0">
  <tr>
    <td >
	<c:out value="${idx0.index+1}"/>
    </td>
    <td class="header">
	<c:out value="${FDetail.folderFileX}"/>
    </td>
    <td class="header">
	<c:out value="${FDetail.folderFileDesc}"/>
    </td>
    <td>
	<c:if test="${FDetail.dateStart==0}" >
	<center>
	    <c:out value=" "/>
<%--	    <img src="${ctx}/config/gifs/Record-Normal-Red-icon16.png"/> --%>
	</center>
	</c:if>
	<c:if test="${FDetail.dateStart!=0}" >
	<center>
	    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="PrevDates">
			<img src="${ctx}/config/gifs/Actions-arrow-left-icon16.png" border="0"/>
		<stripes:param name="rowIdx" value="${idx0.index}"/>
	    </stripes:link>
	</center>
	</c:if>
    </td>
    <td>
	<c:forEach items="${FDetail.fileDates}" var="fDate" varStatus="idx" begin="${FDetail.dateStart}" end="${FDetail.dateEnd}">
	    <b>
	    <stripes:link
		href="/econtroller/infopoint/actions/ContentNavigation.action"
				event="ViewFile">
		    <c:out value="${FDetail.fmtDates.get(idx.index)}"/> <c:out value=" "/>
		    <stripes:param name="fdate" value="${fDate}"/>
		    <stripes:param name="ffile" value="${FDetail.folderFileX}"/>
		    <stripes:param name="findx" value="N"/>
	    </stripes:link>
	    </b>
	</c:forEach>
    </td>
    <td>
	<c:choose>
	    <c:when test="${FDetail.fileDates.size()==0}" >
		<center>
		    <c:out value=" "/>
<%--		    <img src="${ctx}/config/gifs/Record-Normal-Red-icon16.png"/> --%>
		</center>
	    </c:when>
<%--		    <img src="${ctx}/config/gifs/Record-Normal-Red-icon16.png"/> --%>
<%--
	    <c:when test="${FDetail.fileDates.size()<=FDetail.dateEnd}" >
		<center>
		    <c:out value=" "/>
		</center>
	    </c:when>
--%>
	    <c:when test="${(FDetail.numDates-1)<=FDetail.dateEnd}" >
		<center>
		    <c:out value=" "/>
		</center>
	    </c:when>
	    <c:otherwise>
		<center>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" 
			event="NextDates">
			<img src="${ctx}/config/gifs/Actions-arrow-right-icon16.png" border="0"/>
			<stripes:param name="rowIdx" value="${idx0.index}"/>
	    	    </stripes:link>
		</center>
	    </c:otherwise>
	</c:choose>
    </td>
  </tr>
</c:forEach>
</table>
</div>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
<!-- SCROLL SNEAK JS: you can change `location.hostname` to any unique string or leave it as is -->
<%--
<script>
(function() {
    var sneaky = new ScrollSneak(location.hostname), tabs = document.getElementById('tabs').getElementsByTagName('li'), i = 0, len = tabs.length;
    for (; i < len; i++) {
        tabs[i].onclick = sneaky.sneak;
    }
    
    document.getElementById('next').onclick = sneaky.sneak;
})();
</script>
--%>
<!-- END OF SCROLL SNEAK JS -->
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
