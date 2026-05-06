<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <h1><stripes:label for="contentAccess"/> by <stripes:label for="file"/></h1>
    <div id="detail">
    </div>

<stripes:errors />

<div id="caltab">
<table class="sortable" colspan='9' width='90%' align='center' border='1' bgcolor=lightgreen height='39'>
	<tr>
	    <th>
	    	<font size=2><stripes:label for="recNum"/></font>
	    </th>
<%--
	    <th>
		<font size=2>
		    <stripes:label for="folder"/> <stripes:label for="name"/>
		</font>
	    </th>
--%>
	    <th>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th>
		<font size=2><stripes:label for="subBranch"/></font>
	    </th>
	    <th>
		<font size=2><stripes:label for="application"/></font>
	    </th>
	    <th>
		<font size=2><stripes:label for="fileName"/></font>
	    </th>
	    <th>
		<font size=2>
		    <stripes:label for="file"/><stripes:label for="description"/>
		</font>
	    </th>
	    <th>
		<font size=2>
		    <stripes:label for="index"/> <stripes:label for="access"/>
		</font>
	    </th>
	    <th>
		<font size=2><stripes:label for="annotated"/></font>
	    </th>
	</tr>
<c:forEach items="${folderSelector.folderRows}" var="FDetail" varStatus="idx0">
  <tr>
    <td>
	<c:out value="${idx0.index+1}"/>
    </td>
<%--
    <td>
	<c:out value="${FDetail.folderName}"/>
    </td>
--%>
    <td>
      <c:out value="${FDetail.folderBr}"/>
    </td>
    <td>
      <c:out value="${FDetail.folderSBr}"/>
    </td>
    <td>
      <c:out value="${FDetail.folderAppl}"/>
    </td>
<%--
    <c:if test="${FDetail.fileInFolder!=''}">
--%>
	<td>
	    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" 
		event="ViewFileCalendar">
		<c:out value="${FDetail.folderFileX}"/> 
		<stripes:param name="ffile" value="${FDetail.folderFileX}"/>
	    </stripes:link>
	</td>
	<td>
	    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" 
		event="ViewFileCalendar">
		<c:out value="${FDetail.folderFileDesc}"/> 
		<stripes:param name="ffile" value="${FDetail.folderFileX}"/>
	    </stripes:link>
	</td>
<%--
    </c:if>
    <c:if test="${FDetail.fileInFolder==''}">
	<td>
	    <font color="red"><c:out value="${FDetail.folderFile}"/></font>
	</td>
	<td>
	    <font color="red"><c:out value="${FDetail.folderFileDesc}"/></font>
	</td>
    </c:if>
--%>
    <td >
      <c:out value="${FDetail.folderIndexed}"/>
    </td>
    <td>
	  N
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
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
