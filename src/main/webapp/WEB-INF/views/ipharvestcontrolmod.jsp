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
	</table>
	<div id="detail">
	<table colspan='2' width='60%' align='center' border='1' height='39'>
	    <tr>
		<th class="header" colspan="2">
		    <c:if test="${harvestSelector.actionFlag=='New'}">
			<font size=2>
			    <stripes:label for="add"/> <stripes:label for="harvestCtl"/>
			</font>
		    </c:if>
		    <c:if test="${harvestSelector.actionFlag=='Mod'}">
			<font size=2>
			    <stripes:label for="modify"/> <stripes:label for="harvestCtl"/>
			</font>
		    </c:if>
		    <c:if test="${harvestSelector.actionFlag=='Del'}">
			<font size=2>
			    <stripes:label for="delete"/> <stripes:label for="harvestCtl"/>
			</font>
		    </c:if>
		</th>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="harvestId"/></font>
		</th>
		<td>
		    <stripes:text name="harvestControlDetail.serverName"
			value="${harvestControlDetail.serverName}"/></b>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="branch"/></font>
		</th>
		<td>
		    <stripes:text name="harvestControlDetail.harvestBr"
			value="${harvestControlDetail.harvestBr}"/></b>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="startHour"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.startHour">
			<c:forEach begin="1" end="24" varStatus="iHour">
			    <c:if test='${harvestControlDetail.startHour==iHour.index}'>
				<option selected><c:out value="${iHour.index}"/></option>
			    </c:if>
			    <c:if test='${harvestControlDetail.startHour!=iHour.index}'>
				<option><c:out value="${iHour.index}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="endHour"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.endHour">
			<c:forEach var="ehours" begin="1" end="24">
			    <c:if test='${harvestControlDetail.endHour==ehours}'>
				<option selected><c:out value="${ehours}"/></option>
			    </c:if>
			    <c:if test='${harvestControlDetail.endHour!=ehours}'>
				<option><c:out value="${ehours}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="cycleMins"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.cycleMins">
		    	<c:if test="${harvestControlDetail.cycleMins!=''}" >
			    <option selected><c:out value="${harvestControlDetail.cycleMins}"/></option>
			</c:if>
		    	<c:if test="${harvestControlDetail.cycleMins==''}" >
			    <option selected>30</option>
			</c:if>
			<option>15</option>
			<option >30</option>
			<option >45</option>
			<option >60</option>
			<option >75</option>
			<option >90</option>
			<option >120</option>
			<option >180</option>
			<option >240</option>
			<option >300</option>
			<option >360</option>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="throttle"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.throttle">
		    	<c:if test="${harvestControlDetail.throttle!=0}" >
			    <option selected><c:out value="${harvestControlDetail.throttle}"/></option>
			</c:if>
		    	<c:if test="${harvestControlDetail.throttle==0}" >
			    <option selected>1000</option>
			</c:if>
			<option>2</option >
			<option>5</option >
			<option>10</option >
			<option>20</option >
			<option>50</option >
			<option>100</option >
			<option>200</option >
			<option>500</option >
			<option>1000</option >
		    </select>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="retentionYears"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.retentionYears">
			<c:forEach var="years" begin="1" end="25">
			    <c:if test='${harvestControlDetail.retentionYears==years}'>
				<option selected><c:out value="${years}"/></option>
			    </c:if>
			    <c:if test='${harvestControlDetail.retentionYears!=years}'>
			        <option> <c:out value="${years}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="checkNulls"/></font>
		</th>
		<td>
		    <stripes:text name="harvestControlDetail.checkNulls"
			value="${harvestControlDetail.checkNulls}"
		    		  size='40' maxlength='255'/>
		</td>
	    </tr>
	    <tr>
		<th>
		    <font size=2><stripes:label for="autoCreate"/></font>
		</th>
		<td>
		    <select size="1" name="harvestControlDetail.autoCreate">
			<c:if test='${harvestControlDetail.autoCreate==""}'>
			    <option Selected value="N">No</option>
			    <option value="Y">Yes</option>
			</c:if>
			<c:if test='${harvestControlDetail.autoCreate=="N"}'>
			    <option Selected value="N">No</option>
			    <option value="Y">Yes</option>
			</c:if>
		    	<c:if test='${harvestControlDetail.autoCreate=="Y"}'>
			    <option value="N">No</option>
			    <option Selected value="Y">Yes</option>
			</c:if>
		    </select>
		</td>
	    </tr>
	</table>

	</div>
	<center>
	    <stripes:errors/>
	    <c:if test='${harvestSelector.accessFlag!="inq"}'>
		<c:if test='${harvestSelector.actionFlag=="Modify"}'>
		    <stripes:submit name="UpdateControl" value="Update Harvest Control"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Update"}'>
		    <stripes:submit name="ConfirmAddModHarvestControl" value="Confirm Update Harvest Control"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="New"}'>
		    <stripes:submit name="AddControl" value="Add Harvest Control"/>
		</c:if>
		<c:if test='${harvestSelector.actionFlag=="Add"}'>
		    <stripes:submit name="ConfirmAddModHarvestControl" value="Confirm Add Harvest Control"/>
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

