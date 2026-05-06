<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/inclear/actions/LoadChex.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>Load <stripes:label for="check"/> <stripes:label for="data"/> <stripes:label for="load"/></h1>
	<div id="detail">
	    <table> <%-- cellpadding="5"> --%>
		<tr>
		    <th>Process date: </th>
		    <td><stripes:text name="controlDetail.applDate" value="${controlDetail.applDate}"
			size="11" maxlength="11"/></td>
		</tr>
		<tr>
		    <th>File to load: </th>
		    <td>
		    <c:if test='${controlDetailC.fileToLoad!=""}'>
			<stripes:text name="controlDetailC.fileToLoad" value="${controlDetailC.fileToLoad}"
			    readonly="true" size="50" maxlength="120"/>
		    </c:if>
<%--
		    <c:if test='${controlDetail.fileToLoad==""}'>
			<c:if test='${controlDetail.file_loaded!=""}'>
			    <stripes:text name="controlDetail.file_loaded" value="${controlDetail.file_loaded}"
				readonly="true" size="50" maxlength="120"/>
			</c:if>
		    </c:if>
--%>
		    <c:if test='${controlDetailC.fileToLoad==""}'>
			    <stripes:text name="controlDetailC.file_loaded" value="${controlDetailC.file_loaded}"
				readonly="true" size="50" maxlength="120"/>
		    </c:if>
		    </td>
		</tr>
	    </table>
	</div>

    <center>
	<stripes:errors />
	<c:if test="${controlSelector.actionFlag=='LoadNext'}">
		<stripes:submit name="LoadFile" value="Load File" disabled="true" />
		<stripes:submit name="LoadNextFile" value="Load Next File"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files"/>
		<stripes:submit name="ViewStats" value="View Load Statistics" />
	</c:if>
	<c:if test="${controlSelector.actionFlag=='ReLoadFiles'}">
		<stripes:submit name="LoadFile" value="Load File" disabled="true" />
		<stripes:submit name="LoadNextFile" value="Load Next File" disabled="true"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files"/>
		<stripes:submit name="ViewStats" value="View Load Statistics" />
	</c:if>
	<c:if test="${controlSelector.actionFlag=='LoadFiles'}">
		<c:if test="${controlDetailC.fileToLoad==''}">
		    <stripes:submit name="LoadFile" value="Load File" disabled="true"/>
		</c:if>
		<c:if test="${controlDetailC.fileToLoad!=''}">
		    <stripes:submit name="LoadFile" value="Load File" />
		</c:if>
		<stripes:submit name="LoadNextFile" value="Load Next File" disabled="true"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files" disabled="true"/>
		<stripes:submit name="ViewStats" value="View Load Statistics" disabled="true"/>
	</c:if>
    </center>
<%--
<c:out value="${controlSelector.actionFlag}"/>
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
