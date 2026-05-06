<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/HarvestAdmin.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<c:if test='${folderSelector.accessFlag=="inq"}'>
	    <h1><stripes:label for="harvest"/> <stripes:label for="inquiry"/></h1>
	</c:if>
	<c:if test='${folderSelector.accessFlag!="inq"}'>
	    <h1><stripes:label for="harvest"/> <stripes:label for="maint"/></h1>
	</c:if>

	<div id="detail">
	<table colspan='2' width='70%'>
	<tr>
	    <c:if test='${harvestSelector.accessFlag=="inq"}'>
		<th class="header" colspan="2">
		    <font size=3 color=blue>
			<stripes:label for="harvest"/> <stripes:label for="inquiry"/>
		    </font>
		</th>
	    </c:if>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<c:if test='${harvestSelector.actionFlag=="Modify"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="harvestSource"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Delete"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="delete"/> <stripes:label for="harvestSource"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="New"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="harvestSource"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Update"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="modify"/> <stripes:label for="harvestSource"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Add"}'>
		    <th class="header" colspan="2">
			<font size=3 color=blue>
			    <stripes:label for="add"/> <stripes:label for="harvestSource"/>
			</font>
		    </th>
		</c:if>
	    </c:if>
	</tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="harvestId"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.serverName"
			value="${harvestDetail.serverName}"  size='20' readonly='true'/></b>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestBr"
			value="${harvestDetail.harvestBr}"  size='10' readonly='true'/></b>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="subBranch"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestSBr" value="${harvestDetail.harvestSBr}"/></b>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="application"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestAppl" value="${harvestDetail.harvestAppl}"/></b>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="node"/></font>
		</th>
		<td>
		    <select size="1" name="harvestDetail.harvestNode">
			<c:forEach var="node" items="${harvestSelector.hostList}">
			    <c:if test='${harvestDetail.harvestNode!=node}'>
			        <option> <c:out value="${node}"/> </option>
			    </c:if>
			    <c:if test='${harvestDetail.harvestNode==node}'>
				<option selected> <c:out value="${node}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="remoteDir"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestRemDir" value="${harvestDetail.harvestRemDir}"/>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="fileName"/> or *</font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestFileName" value="${harvestDetail.harvestFileName}"/>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="frequency"/></font>
		</th>
		<td>
		    <select size="1" name="harvestDetail.harvestFrequency">
			<c:if test='${harvestDetail.harvestFrequency=="every"}'>
			    <option selected>every</option>
			    <option>one</option>
			</c:if>
			<c:if test='${harvestDetail.harvestFrequency=="one"}'>
			    <option>every</option>
			    <option selected>one</option>
			</c:if>
			<c:if test='${harvestDetail.harvestFrequency==""}'>
			    <option>every</option>
			    <option>one</option>
			</c:if>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="startCycle"/></font>
		</th>
		<td>
		    <select size="1" name="harvestDetail.harvestStart1">
			<c:forEach var="ehours" begin="1" end="24">
			    <c:if test='${harvestDetail.harvestStart1==ehours}'>
				<option selected><c:out value="${ehours}"/></option>
			    </c:if>
			    <c:if test='${harvestDetail.harvestStart1!=ehours}'>
				<option><c:out value="${ehours}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th align='center' width='10%' height=15 colspan=1>
		    <font size=2><stripes:label for="flagFile"/></font>
		</th>
		<td>
		    <stripes:text name="harvestDetail.harvestFlagFile" 
			value="${harvestDetail.harvestFlagFile}"/>
		</td>
	    </tr>
	</table>

	</div>

	<center>
	    <stripes:errors/>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<c:if test='${harvestSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="UpdateHarvest" value="Update Harvest Source"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Update"}'>
		    <stripes:submit name="ConfirmAddModHarvest" value="Confirm Update Harvest Source"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="New"}'>
		    <stripes:submit name="AddHarvest" value="Add Harvest Source"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Add"}'>
		    <stripes:submit name="ConfirmAddModHarvest" value="Confirm Add Harvest Source"/>
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

