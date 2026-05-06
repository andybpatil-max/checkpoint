<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/HarvestAdmin.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="harvestAdmin"/></h1>

	<table align="center" border="1" height="3" width="20%">
	    <c:if test='${harvestSelector.accessFlag=="inq"}'>
		<tr>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="harvest"/> <stripes:label for="inquiry"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<tr>
		    <th align=center height="19" colspan="2">
			<font size="3">
			    <stripes:label for="harvest"/> <stripes:label for="maint"/>
			</font>
		    </th>
		</tr>
	    </c:if>
	    <tr>
		<th align=center height="19" colspan="1">
			<font size="3"><stripes:label for="harvestId"/></font></TH>
	    </tr>
	    <td>
		<select size="1" name="harvestSelector.serverName">
		    <c:forEach var="cKey" items="${harvestSelector.hcRows}">
			<c:if test='${harvestSelector.actionFlag!=""}'>
			    <c:if test='${harvestControlDetail.serverName!=cKey.key}'>
				<option> <c:out value="${cKey.key}"/> </option>
			    </c:if>
			    <c:if test='${harvestControlDetail.serverName==cKey.key}'>
				<option selected> <c:out value="${harvestControlDetail.serverName}"/> </option>
			    </c:if>
			</c:if>
			<c:if test='${harvestSelector.actionFlag==""}'>
			    <option> <c:out value="${cKey.key}"/> </option>
			</c:if>
		    </c:forEach>
		 </select>
	    </td>
	</table>

	<c:if test='${harvestSelector.actionFlag!=""}'>
	<div id="selres">

	<table colspan='9' width='60%' align='center' border='1' height='39'>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="harvestId"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="startHour"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="endHour"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="cycleMins"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="throttle"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="retentionYears"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="checkNulls"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="autoCreate"/></font>
		</th>
	    </tr>
	    <tr>
		<td>
		    <c:out value="${harvestControlDetail.serverName}"/></b>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.harvestBr}"/></b>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.startHour}"/></b>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.endHour}"/></b>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.cycleMins}"/>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.throttle}"/>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.retentionYears}"/>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.checkNulls}"/>
		</td>
		<td>
		    <c:out value="${harvestControlDetail.autoCreate}"/>
		</td>
	    </tr>
	</table>

	<table colspan='9' width='60%' align='center' border='1' bgcolor=lightgreen height='39'>
	    <tr bgcolor=bluegreen>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="subBranch"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="application"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="node"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="remDir"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="fileName"/> or *</font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="frequency"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="startCycle"/></font>
		</th>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="flagFile"/></font>
		</th>
		<c:if test='${harvestSelector.accessFlag!="inq"}'>
		    <th align='center' width='10%' height=15 colspan=2>
			<font size=2>Action</font>
		    </th>
		</c:if>
	    </tr>

	<c:forEach var="HarvestDetail" items="${harvestSelector.harvestRows}" varStatus="idx">
	    <tr>
		<td>
		    <c:out value="${HarvestDetail.harvestBr}"/></b>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestSBr}"/></b>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestAppl}"/></b>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestNode}"/></b>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestRemDir}"/>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestFileName}"/>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestFrequency}"/>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestStart1}"/>
		</td>
		<td>
		    <c:out value="${HarvestDetail.harvestFlagFile}"/>
		</td>
		<c:if test='${harvestSelector.accessFlag!="inq"}' >
		    <td>
			<stripes:link href="/econtroller/infopoint/actions/HarvestAdmin.action"
					event="ModifyHarvest">
			    Delete
			    <stripes:param name="recIndex" value="${idx.index}"/>
			    <stripes:param name="serverName" value="${HarvestDetail.serverName}"/>
			</stripes:link>
		    </td>
		    <td>
			<stripes:link href="/econtroller/infopoint/actions/HarvestAdmin.action"
					event="ModifyHarvest">
			    Modify
			    <stripes:param name="recIndex" value="${idx.index}"/>
			    <stripes:param name="serverName" value="${HarvestDetail.serverName}"/>
			</stripes:link>
		    </td>
		</c:if>
	    </tr>
	</c:forEach>
	</table>

	</div>

	</c:if>

	<center>
	    <stripes:errors/>
	    <stripes:submit name="View" value="View"/>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<stripes:submit name="NewHarvest" value="New Harvest"/>
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

