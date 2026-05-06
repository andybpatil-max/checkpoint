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
		<c:out value=" for "/>
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
		<option> <c:out value="PDF" /> </option>
		<option> <c:out value="csv" /> </option>
		<option> <c:out value="text" /> </option>
		<option> <c:out value="Print" /> </option>
	    </select>
	    <c:out value="Print to Queue: "/>
	    <stripes:text name="folderSelector.printQue" size="20" maxlength="50"
		value="${folderSelector.printQue}" />
	    <stripes:submit name="Extract" value="Perform"/>
	</h3>
	</td>
    </tr>
<%--
    <tr>
	<td>
	<h3>
	    <stripes:submit name="ComplexSearch" value="Complex Search"/>
	    <c:if test="${folderSelectror.fileIndx=='Y'}">
		<stripes:submit name="IndexAccess" value="Index Access"/>
	    </c:if>
	    <stripes:submit name="Annotations" value="Annotations"/>
	</h3>
	</td>
    </tr>

    <tr colspan="8">
	<td>
	    <stripes:submit name="ComplexSearch" value="Complex Search"/>
	    <c:if test="${folderSelectror.fileIndx=='Y'}">
		<stripes:submit name="IndexAccess" value="Index Access"/>
	    </c:if>
	    <stripes:submit name="Annotations" value="Annotations"/>
	</td>
    </tr>
 --%>
</table>
</div>
<%--
    Content Page navigation
--%>
    <h3>
	<c:out value="Displaying "/><c:out value="${folderSelector.pageStart}"/><c:out value=" to "/>
	<c:out value="${folderSelector.pageEnd}"/><c:out value=" of "/>
	<c:out value="${folderSelector.repPages}"/><c:out value=" pages "/>
	<c:if test='${folderSelector.pageStart!=0}'>
	    <stripes:submit name="PrevSearch" value="Prev" />
	</c:if>
	<c:if test='${folderSelector.pageStart==0}'>
	    <stripes:submit name="PrevSearch" value="Prev" />
	</c:if>
	<stripes:text name="folderSelector.rowsDispStr" size="6" maxlength="6"
		value="${folderSelector.rowsDispStr}" />
	<c:if test='${folderSelector.pageEnd!=folderSelector.repPages}'>
	    <stripes:submit name="NextSearch" value="Next" />
	</c:if>
	<c:if test='${folderSelector.pageEnd==folderSelector.repPages}'>
	    <stripes:submit name="NextSearch" value="Next" disabled="true"/>
	</c:if>
    </h3><br/>

<div class="repmain">
<%--
	    <c:out value="${folderSelector.outRadio}"/>
	    <c:out value="${folderSelector.annotRadio}"/>
	    <c:out value="${folderSelector.pageLineRadio}"/>
--%>
<c:set var="greenBar" value="0" scope="request"/>

<div class="pre" style="background-color:white;">
<c:if test="${folderSelector.pageLineRadio=='PAGE'}">
    <c:forEach items="${folderSelector.repLines}" var="repDetail" varStatus="idx0"
		begin="${folderSelector.rowStart}" end="${folderSelector.rowEnd-1}"> 
	<c:choose>
	    <c:when test="${repDetail.foundSearch==true}">
		<b><c:out value="${repDetail.searchResult}" /></b>
	    </c:when>
	    <c:otherwise>
		<c:out value="${repDetail.repLine}" />
	    </c:otherwise>
	</c:choose>
    </c:forEach>
</c:if>
<c:if test="${folderSelector.pageLineRadio=='LINES'}">
    <c:forEach items="${folderSelector.foundLines}" var="fLines" varStatus="idx0">
	   <b>
		<c:out value="${fLines}" />
	   </b><br/>
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
