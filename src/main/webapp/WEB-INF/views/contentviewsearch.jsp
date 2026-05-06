<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standardrep.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="viewing" />
	<c:out value="${folderSelector.fileViewed}" />
	 for 
	<c:out value="${folderSelector.folderDate}" />
    </h1>
<stripes:errors />
<div>
<table colspan="8" width="100%">
    <tr colspan="8" width="100%">
	<td>
	<h3>
	    <c:out value="Extract to: "/>
	    <select size="1" name="folderSelector.extractRep">
		<option selected value="PDF"> <c:out value="PDF File" /> </option>
		<option value="Excel">Tab Delimited File</option>
		<option value="Print">Print Hardcopy</option>
	    </select>
	    <c:out value="Print to Queue: "/>
	    <stripes:text name="folderSelector.printQue" size="20" maxlength="50"
		value="${folderSelector.printQue}" />
	    <stripes:submit name="ExtractFromSearch" value="Perform"/>
	</h3>
	</td>
    </tr>
    <c:if test="${folderSelector.extFileNameX!=''}">
	<tr>
	    <td>
		<stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" 
			event="ViewExtractFromView">
			<c:out value="${folderSelector.extFileNameX}"/> 
			<stripes:param name="ffile" value="${folderSelector.extFileNameX}" />
		</stripes:link>
<%--
--%>
	    </td>
    	</tr>
    </c:if>
</table>
</div>
<%--
    Content Page navigation
--%>
<c:if test="${folderSelector.outRadio=='STEP'}">
    <h3>
	<stripes:submit name="PrevSearch" value="Prev" />
	<stripes:submit name="NextSearch" value="Next" />
    </h3><br/>
</c:if>
<div class="repmain">
<c:set var="greenBar" value="0" scope="request"/>
<div class="pre" style="background-color:white;">
    <c:if test="${folderSelector.pageLineRadio=='PAGE'}">
	<c:forEach items="${folderSelector.subPageMap1}" var="pgFound" varStatus="pgIdx">
	    <c:forEach items="${folderSelector.repLines}" var="repDetail" varStatus="idx0"
			begin="${pgFound.value[0]}" end="${pgFound.value[1]}">
		<c:choose>
		    <c:when test="${repDetail.foundSearch==true}">
    <b><c:out value="${repDetail.repLine}" /></b>
		    </c:when>
		    <c:otherwise>
    <c:out value="${repDetail.repLine}" />
		    </c:otherwise>
		</c:choose>
	    </c:forEach>
	</c:forEach>
    </c:if>
    <c:if test="${folderSelector.pageLineRadio=='LINES'}">
	<c:forEach items="${folderSelector.foundLines}" var="fLines" varStatus="idx00">
	    <c:if test="${folderSelector.repLines[fLines.key].getFoundSearch()==true}">
    <b><c:out value="${fLines.value}" /></b>
	    <br/>
	    </c:if>
	    <c:if test="${folderSelector.repLines[fLines.key].getFoundSearch()!=true}">
    <c:out value="${fLines.value}" />
	    <br/>
	    </c:if>
	</c:forEach>
    </c:if>
</div>
</div>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
