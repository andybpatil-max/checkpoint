<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/sysadmin/actions/DbTable.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="dbtable"/></h1>
	<div id="detail">
	    <table colspan='2' width='50%' align='center' border='1'>
		<tr>
		<c:if test='${dbSelector.accessFlag=="inq"}'>
		    <th  class="header" align='center' height='19' colspan='2'>
			<font size=3 color=blue><stripes:label for="dbtable.inq"/></font>
		    </th>
		</c:if>
		<c:if test='${dbSelector.accessFlag!="inq"}'>
		    <c:if test='${dbSelector.actionFlag=="Modify"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="dbtable.modify"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Delete"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="dbtable.delete"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="New"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="dbtable.add"/></font>
		   	</th>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Update"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="dbtable.modify"/></font>
			</th>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Add"}'>
		    	<th class="header" align='center' height='19' colspan='2'>
				<font size=3 color=blue><stripes:label for="dbtable.add"/></font>
		   	</th>
		    </c:if>
		</c:if>
		</tr>
<%--
		c:out value="${dbSelector.dbAppl}"
		c:out value="${dbSelector.accessFlag}"
--%>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="Application"/></b></th>
		<td>
		    <c:if test='${dbSelector.accessFlag!="inq"}'>
			<select size="1" name="dbDetail.dbAppl">
			    <c:forEach var="appl" items="${dbSelector.applList}">
				<c:if test='${dbSelector.dbAppl==appl}'>
				    <option selected><c:out value="${appl}"/> </option>
				</c:if>
				<c:if test='${dbSelector.dbAppl!=appl}'>
				    <option><c:out value="${appl}"/> </option>
				</c:if>
			    </c:forEach>
			</select>
		    </c:if>
		    <c:if test='${dbSelector.accessFlag=="inq"}'>
			<stripes:text name='dbDetail.dbAppl' value='${dbDetail.dbAppl}'
				size='80' maxlength="80"  readonly='true'/>
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="table"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${dbSelector.actionFlag=="New"}'>
			<stripes:text name='dbDetail.dbTable' value='${dbDetail.dbTable}'
				size='80' maxlength="30"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag!="New"}'>
			<stripes:text name='dbDetail.dbTable' value='${dbDetail.dbTable}'
				size='80' readonly='true' />
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="sql"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${dbSelector.actionFlag=="Modify"}'>
			<c:if test='${dbSelector.accessFlag!="inq"}'>
			    <stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size="100" maxlength="255"/>
			</c:if>
			<c:if test='${dbSelector.accessFlag=="inq"}'>
			    <stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size="100" maxlength="255" readonly='true'/>
			</c:if>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Delete"}'>
		    	<stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size="100" maxlength="255"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="New"}'>
			<stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size='100'  maxlength="255"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Update"}'>
			<stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size='100' readonly='true' />
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Add"}'>
			<stripes:text name='dbDetail.dbSql' value='${dbDetail.dbSql}'
				size='100' readonly='true' />
		    </c:if>
		</td>
		</tr>
		<tr>
		<th align='right' bgcolor='lightyellow' width='20%' height='19'><b>
			<stripes:label for="initsql"/></b></th>
		<td align='left' width=10% height='19'>
		    <c:if test='${dbSelector.actionFlag=="Modify"}'>
		    	<stripes:text name='dbDetail.dbInitSql' value='${dbDetail.dbInitSql}'
				size="100" maxlength="255"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Delete"}'>
		    	<stripes:text name='dbDetail.dbInitSql' value='${dbDetail.dbInitSql}'
				size="100" maxlength="255"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="New"}'>
			<stripes:text name='dbDetail.dbInitSql' value='${dbDetail.dbInitSql}'
				size='100'  maxlength="255"/>
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Update"}'>
			<stripes:text name='dbDetail.dbInitSql' value='${dbDetail.dbInitSql}'
				size='100' readonly='true' />
		    </c:if>
		    <c:if test='${dbSelector.actionFlag=="Add"}'>
			<stripes:text name='dbDetail.dbInitSql' value='${dbDetail.dbInitSql}'
				size='100' readonly='true' />
		    </c:if>
		</td>
		</tr>
	    </table>
	</div>


<%--													--%>
<%--	Submit buttons area to click on to take the next step.						--%>
<%--	Any errors will appear between the data table and the buttons.					--%>
<%--													--%>
<%--	<c:out value="${dbSelector.actionFlag}"/>							--%>
<%--	<c:out value="${dbSelector.actionFlag}"/>							--%>
<%--	<c:out value="${dbSelector.accessFlag}"/>							--%>

	<center>
	    <stripes:errors />
	    <c:if test='${dbSelector.accessFlag!="inq"}'>
		<c:if test='${dbSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="Update" value="Update Table"/>
		</c:if>
		<c:if test='${dbSelector.actionFlag=="Update"}'>
		    <stripes:submit name="Confirm" value="Confirm Update Table"/>
		</c:if>
		<c:if test='${dbSelector.actionFlag=="Add"}'>
		    <stripes:submit name="Confirm" value="Confirm Add Table"/>
		</c:if>
		<c:if test='${dbSelector.actionFlag=="New"}'>
		    <stripes:submit name="Add" value="Add Table"/>
		</c:if>
		<c:if test='${dbSelector.actionFlag=="Delete"}'>
		    <stripes:submit name="ConfirmDelete"  value="Confirm Delete Table"/>
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
