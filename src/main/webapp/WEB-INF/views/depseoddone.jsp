<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/DepsEod.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="eodPageTitle"/></h1>


<div id="detail">
<table align='center'>
    <tr>
	<th>
	    <font size=5><stripes:label for="application"/> <stripes:label for="date"/></font>
	</th>
	<td>
	    <font size=5><c:out value="${chexSelector.appl_date_f}"/></font>
	</td>
    </tr>
</table>
</div>
<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>

    <center>
	<stripes:errors/>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>

