<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/deposits/actions/LoadDeposits.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1>Load Check Data</h1>
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
		    <c:if test='${controlDetailD.fileToLoad!=""}'>
			<stripes:text name="controlDetailD.fileToLoad" value="${controlDetailD.fileToLoad}"
			    readonly="true" size="40" maxlength="120"/>
		    </c:if>
<%--
		    <c:if test='${controlDetailD.fileToLoad==""}'>
			<c:if test='${controlDetailD.file_loaded!=""}'>
			    <stripes:text name="controlDetailD.file_loaded" value="${controlDetailD.file_loaded}"
				readonly="true" size="40" maxlength="120"/>
			</c:if>
		    </c:if>
--%>
		    <c:if test='${controlDetailD.fileToLoad==""}'>
			    <stripes:text name="controlDetailD.file_loaded" value="${controlDetailD.file_loaded}"
				readonly="true" size="40" maxlength="120"/>
		    </c:if>
		    </td>
		</tr>
	    </table>
	</div>

    <center>
	<stripes:errors />
<%--
	    <c:out value='${controlDetailD.file_loaded}'/>
<br>
	    <c:out value='${controlDetailD.fileToLoad}'/>
<br>
--%>
	<c:if test="${controlSelector.actionFlag=='LoadNextFiles'}">
		<stripes:submit name="LoadFile" value="Load File" disabled="true" />
		<stripes:submit name="LoadNextFile" value="Load Next File"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files"/>
	</c:if>
	<c:if test="${controlSelector.actionFlag=='ReLoadFiles'}">
		<stripes:submit name="LoadFile" value="Load File" disabled="true" />
		<stripes:submit name="LoadNextFile" value="Load Next File" disabled="true"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files"/>
	</c:if>
	<c:if test="${controlSelector.actionFlag=='LoadFiles'}">
		<c:if test='${controlDetailD.fileToLoad==""}'>
		      <stripes:submit name="LoadFile" value="Load File" disabled="true"/>
		</c:if>
		<c:if test='${controlDetailD.fileToLoad!=""}'>
		      <stripes:submit name="LoadFile" value="Load File" />
		</c:if>
		<stripes:submit name="LoadNextFile" value="Load Next File" disabled="true"/>
		<stripes:submit name="ReLoadFiles" value="Reload Files" disabled="true"/>
	</c:if>
    </center>

<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
