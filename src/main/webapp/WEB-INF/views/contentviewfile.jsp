<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standardrep.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<stripes:hidden  name="folderSelector.submitId" id="subId" />
    <h1><stripes:label for="viewing"/>
		<c:out value="${folderSelector.fileViewed}"/>
		<c:out value=" for "/>
		<c:out value="${folderSelector.folderDate}"/>
    </h1>
<stripes:errors/>
<div>
<table colspan="8" width="100%">
<c:if test='${!folderSelector.annOn}'>
    <tr colspan="8" width="100%">
	<td>
	<h3>
	    <c:out value="Extract to: "/>
	    <select size="1" name="folderSelector.extractRep">
		<option selected value="PDF">PDF File</option>
		<option value="Excel">Tab Delimited File</option>
		<option value="Print">Print Hardcopy</option>
	    </select>
	    <c:out value="Print to Queue: "/>
	    <stripes:text name="folderSelector.printQue" size="20" maxlength="50"
		value="${folderSelector.printQue}"/>
	    <stripes:submit name="ExtractFromView" value="Perform"/>
	</h3>
	</td>
    </tr>
<%----%>
    <c:if test="${folderSelector.extFileNameX!=''}">
	<tr>
	    <td>
		<stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" 
			event="ViewExtractFromView">
			<c:out value="${folderSelector.extFileNameX}"/> 
			<stripes:param name="ffile" value="${folderSelector.extFileNameX}"/>
		</stripes:link>
<%--
--%>
	    </td>
    	</tr>
    </c:if>
</c:if>
    <tr>
	<td>
	<h3>
	    <stripes:submit name="ComplexSearch" value="Complex Search"/>
	    <c:if test="${folderSelector.fileIndx=='Y'}">
		<stripes:submit name="IndexAccess" value="Index Access"/>
	    </c:if>
	    <stripes:submit name="Annotations" value="Annotations"/>
	</h3>
	</td>
    </tr>
<%--
    <tr colspan="8">
	<td>
	    <stripes:submit name="ComplexSearch" value="Complex Search"/>
	    <c:if test="${folderSelector.fileIndx=='Y'}">
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
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	<c:if test='${folderSelector.pageStart==0}'>
	    <stripes:submit name="Prev" value="Prev"/>
	</c:if>
	<stripes:text name="folderSelector.rowsDispStr" size="6" maxlength="6"
		value="${folderSelector.rowsDispStr}"/>
	<c:if test='${folderSelector.pageEnd!=folderSelector.repPages}'>
	    <stripes:submit name="Next" value="Next"/>
	</c:if>
	<c:if test='${folderSelector.pageEnd==folderSelector.repPages}'>
	    <stripes:submit name="Next" value="Next" disabled="true"/>
	</c:if>
    </h3><br/>

<c:set var="greenBar" value="1" scope="request"/>
<div class="repmain">
<div id="colbutton" style="display:block;">
    <stripes:button name="folderSelector.actionFlag" value="Show Column finder" 
			onclick="coloptions('columns');"/>
</div>
<div id="columns" onclick="coloptions('colbutton');" class="pre" style="background-color:white; font:Courier New, monospace; line-height:.60; font-size:1em; display:none;">
<%--
    000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111</br>
    000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222333</br>
    123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012</br>
--%>
    0000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDD
</div>
<c:forEach items="${folderSelector.repLines}" var="repDetail" varStatus="idx0"
	   begin="${folderSelector.rowStart}" end="${folderSelector.rowEnd-1}">
    <c:if test="${idx0.first}">
	<div class="blue">
	    <c:out value="Page: "/><c:out value="${repDetail.pageNum}"/><br>
	</div>
	<div class="pre" style="background-color:white;">
    </c:if>
    <c:choose>
    <c:when test="${not idx0.first and idx0.index % 4 == 0}">
	</div>
	<c:if test="${repDetail.repFF}">
	    <div class="blue">
		<c:out value="Page: "/><c:out value="${repDetail.pageNum}"/>
	    </div>
	</c:if>
	<c:choose>
	    <c:when test="${greenBar=='0'}">
		<div class="pre" style="background-color:white;">
		<c:set var="greenBar" value="1"/>
	    </c:when>
	    <c:otherwise>
		<div class="pre" style="background-color:lightgreen;">
		<c:set var="greenBar" value="0"/>
	    </c:otherwise>
	</c:choose>
    </c:when>
    <c:otherwise>
    	<c:if test="${not idx0.first and repDetail.repFF}">
	    </div>
	    <div class="blue">
		<c:out value="Page: "/><c:out value="${repDetail.pageNum}"/><br>
	    </div>
	    <c:choose>
		<c:when test="${greenBar=='1'}">
		    <div class="pre" style="background-color:lightgreen;">
		    <c:set var="greenBar" value="0"/>
		</c:when>
		<c:otherwise>
		    <div class="pre" style="background-color:white;">
		    <c:set var="greenBar" value="1"/>
		</c:otherwise>
	    </c:choose>
	</c:if>
    </c:otherwise>
    </c:choose>
    <c:out value="${repDetail.repLine}"/>
    <c:if test="${idx0.last}">
	</div>
    </c:if>
<%--
    <c:if test='${folderSelector.annOn}'></pre></td><div id="A${idx0.index}"><td><stripes:text style="width: 7px; height:7px;" name='folderSelector.newAnno' onclick="addAnno('A${idx0.index}')"/><stripes:submit name="AddAnnoText" value="Add" id="A${idx0.index}"/><c:out value="${repDetail.repLine}"/></td></div></c:if>
--%>
    </c:forEach>
</div>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
