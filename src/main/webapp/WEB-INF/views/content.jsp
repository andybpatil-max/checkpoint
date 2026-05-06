<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
    <h2>${user.userId}</h2>
--%>
    <h1>User <stripes:label for="contentAccess"/></h1>
    <div id="detail">
    </div>
<stripes:errors />

<div id="main1">
    <table colspan='3' align="center" border="1" width="90%">
	<tr>
	    <th align='center' width='5%' height='15' colspan='1'>
		<font size=2><stripes:label for="branch"/></font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2>
		    <stripes:label for="fast"/> <stripes:label for="access"/>
		</font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2>
		    By <stripes:label for="date"/>
		</font>
	    </th>
	    <th align='right' width='5%' height=15 colspan=1>
		<font size=2>
		    By <stripes:label for="report"/>
		</font>
	    </th>
	</tr>

    <c:forEach items="${folderSelector.folderRows}" var="fRows" varStatus="idx0">
	<tr>
	    <td align="center" width='10%' height='19'><b>
		<c:out value="${fRows.folderBr}"/></b>
	    </td>
	    <td align="center" width='10%' height='19'>
		<b>
		<center>
		    <stripes:link class="rapid" 
			href="/econtroller/infopoint/actions/ContentNavigation.action" event="ViewNew">
			<stripes:param name="folderBr" value="${fRows.folderBr}"/>.
		    </stripes:link>
		</center>
		</b>
		</center>
	    </td>
	    <td align="center" width='10%' height='19'><b>
		<center><stripes:link class="cal" href="/econtroller/infopoint/actions/ContentNavigation.action"
			event="ViewCalendar">
			<stripes:param name="folderBr" value="${fRows.folderBr}"/>. 
		</stripes:link>
		</center>
		</b>
	    </td>
	    <td align="center" width='10%' height='19'>
	    	<b>
		<center>
		    <stripes:link class="rep" href="/econtroller/infopoint/actions/ContentNavigation.action" 
				event="ViewFolder">
				<stripes:param name="folderBr" value="${fRows.folderBr}"/>.
		    </stripes:link>
		</center>
		</b>
		</center>
	    </td>
	</tr>
    </c:forEach>

    </table>
</div>	<%-- <div id="main1"> --%>


<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
