<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/Folder.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
    <c:if test='${folderSelector.accessFlag=="inq"}'>
	<h1><stripes:label for="folder"/> <stripes:label for="inquiry"/></h1>
    </c:if>
    <c:if test='${folderSelector.accessFlag!="inq"}'>
	<h1><stripes:label for="folder"/> <stripes:label for="maint"/></h1>
    </c:if>

<div id="detail">
    <table colspan='2' width='70%' align='center' border='1'>
	<tr>
	    <c:if test='${folderSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
		    <font size=3 color=blue>
			<stripes:label for="folder"/> <stripes:label for="inquiry"/>
		    </font>
		</th>
	    </c:if>
	    <c:if test='${folderSelector.accessFlag!="inq"}'>
		<c:if test='${folderSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
		   	<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="folder"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
		   	<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="folder"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
		   	<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="folder"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
		   	<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="folder"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${folderSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
		   	<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="folder"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <b><stripes:label for="folder"/> <stripes:label for="name"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${folderSelector.actionFlag=="New"}'>
		<stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32'/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag!="New"}'>
		<stripes:text name='folderDetail.folderName' value='${folderDetail.folderName}'
			size='32' maxlength='32' readonly='true'/>
	    </c:if>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <b><stripes:label for="folder"/> <stripes:label for="description"/></b>
	</th>
	<td align='left' width=10% height='19'>
	    <c:if test='${folderSelector.actionFlag=="New"}'>
		<stripes:text name='folderDetail.folderDesc' value='${folderDetail.folderDesc}'
			size='50' maxlength='64'/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag!="New"}'>
		<stripes:text name='folderDetail.folderDesc' value='${folderDetail.folderDesc}'
			size='50' maxlength='64' readonly='true'/>
	    </c:if>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="branch"/>
	</th>
	<td align='left' height='15'>
	    <stripes:text name='folderDetail.folderBr' size='3' readonly='true'/>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="subBranch"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderSBr' size='15'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderSBr' size='15'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderSBr' size='15' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="application"/>
	</th>
	<td align='left' height='15'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderAppl' size='16'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderAppl' size='16'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderAppl' size='16' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="fileName"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderFile' size='50' maxlength='120' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="file"/> <stripes:label for="description"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderFileDesc' size='50' maxlength='100'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderFileDesc' size='50' maxlength='100'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderFileDesc' size='50' maxlength='100' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="indexed"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderIndexed' size='2'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderIndexed' size='2'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderIndexed' size='2' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
	<tr>
	<th align='right' bgcolor='lightyellow' width='35%' height='19'>
	    <stripes:label for="overApp"/>
	</th>
	<td align='left' height='19'>
	    <c:choose>
		<c:when test='${folderSelector.actionFlag=="Modify"}'>
		    <stripes:text name='folderDetail.folderOverApp' size='35'/>
		</c:when>
		<c:when test='${folderSelector.actionFlag=="New"}'>
		    <stripes:text name='folderDetail.folderOverApp' size='35'/>
		</c:when>
		<c:otherwise>
		    <stripes:text name='folderDetail.folderOverApp' size='35' readonly='true'/>
		</c:otherwise>
	    </c:choose>
	</td>
	</tr>
    </table>
</div>

<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--
	<c:out value="${folderSelector.actionFlag}"/>
	<c:out value="${folderDetail.folderName}"/>
--%>
	<center>
	<stripes:errors/>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	<c:if test="${fn:containsIgnoreCase(folderDetail.folderName, 'master')}">
	    <c:if test='${folderSelector.actionFlag=="New"}'>
		<stripes:submit name="Add"  value="Add Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Modify"}'>
		<stripes:submit name='Update' value="Update"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="AddFolder"}'>
		<stripes:submit name="ConfirmAddFolder"  value="Confirm Add Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="AddFiles"}'>
		<stripes:submit name="ConfirmAddFiles"  value="Confirm Add Files"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Delete"}'>
		<stripes:submit name="ConfirmDelete" value="Confirm Delete Folder"/>
	    </c:if>
	    <c:if test='${folderSelector.actionFlag=="Update"}'>
		<stripes:submit name="Confirm"  value="Confirm Update Folder"/>
	    </c:if>
	</c:if>
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
