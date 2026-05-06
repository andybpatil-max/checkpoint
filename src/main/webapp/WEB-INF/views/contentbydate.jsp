<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
		<stripes:label for="profile" /> for <c:out value="${folderSelector.userId}"/></h1>
--%>
    <h1><stripes:label for="contentAccess"/> by <stripes:label for="date"/></h1>
    <stripes:errors />


<div id="calnav">
    <table colspan="3">
	<tr>
	    <td style="width:35%;">
		<center>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Prev6Mos">
			<img src="${ctx}/config/gifs/Actions-arrow-left-icon16.png" border="0"/>
		    </stripes:link>
		    <br/>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Prev6Mos">
			Previous 6 Months
		    </stripes:link>
		</center>
	    </td>
	    <td style="width:30%; align=center">
	    	<table>
		<tr>
		    <td align="center" width="10%" height="19">
			<select size="1" name="folderSelector.endMM">
			    <c:forEach items="${folderSelector.months}" var="mons" begin="1" varStatus="mIndex">
				<c:choose>
				    <c:when test="${folderSelector.months[folderSelector.endMM]==mons}">
					<option value="${mIndex.index}" selected>
					    <c:out value="${mons}"/>
					</option>
				    </c:when>
				    <c:otherwise>
					<option value="${mIndex.index}"><c:out value="${mons}"/></option>
				    </c:otherwise>
				</c:choose>
		    	    </c:forEach>
			</select>
		    </td>
		    <td align="center" width="10%" height="19">
		    	<select size="1" name="folderSelector.endYYYY">
			    <c:forEach items="${folderSelector.years}" var="iY" varStatus="yIndx">
				<c:choose>
				    <c:when test="${folderSelector.endYYYY==iY}">
					<option selected><c:out value="${iY}"/></option>
				    </c:when>
				    <c:otherwise>
					<option><c:out value="${iY}"/></option>
				    </c:otherwise>
				</c:choose>
			    </c:forEach>
			</select>
		    </td>
		    <td>
			<stripes:submit name="ViewCalendar" value="Go"/>
		    </td>
		</tr>
		</table>
	    </td>
	    <td style="width:35%;">
		<center>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Next6Mos">
			<img src="${ctx}/config/gifs/Actions-arrow-right-icon16.png" border="0"/>
		    </stripes:link>
		    <br/>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Next6Mos">
			Next 6 Months
		    </stripes:link>
		</center>
	    </td>
	</tr>
   </table>
</div>
<hr>

<div id="caltab">
    <c:set var="dYear" value="0" scope="request"/>
    <c:set var="dMon" value="0" scope="request"/>
    <c:set var="ctr" value="0" scope="request"/>
    <c:set var="dCtr" value="1" scope="request"/>
    <table colspan="3">
    <tr>
	<c:forEach items="${folderSelector.subDateRange}" var="dCal" varStatus="idx">
	    <c:if test="${dMon!=dCal.value[1]}">
		<c:if test="${idx.index>0}">
		    </tr>
		    </table>
		    </td>
		</c:if>
		<c:if test="${ctr==3}">
			</tr>
			<tr>
		</c:if>
		<c:if test="${ctr==6}">
			</tr>
		</c:if>
		<td>
		<table>
		    <tr>
			<th class="header" colspan="7">
			    <c:out value="${folderSelector.months[dCal.value[1]]}"/>
			    <c:out value=" "/>
			    <c:out value="${fn:substring(dCal.key,0,4)}"/>
			</th>
		    </tr>
		    <tr>
		        <c:forEach items="${folderSelector.weekDays}" var="wkdays" varStatus="wIdx">
			    <th class="header"><c:out value="${wkdays}"/></th>
			</c:forEach>
		    </tr>
		    <tr>
		    <c:set var="ctr" value="${ctr+1}" scope="request"/>
	    </c:if>
	    <%-- --%>
	    <c:if test="${dCal.value[0]=='01'}">
		<c:forEach begin="1" end="${dCal.value[3]}" varStatus="idx3">
		    <c:if test="${idx3.index!=dCal.value[2]}">
			<td>
			</td>
		    </c:if>
		    <c:if test="${idx3.index==dCal.value[3]}">
			<td class="num">
			    <%-- Contains directory spec -- i.e. directory exists --%>
			    <c:if test="${dCal.value[4]!=''}">
				<b><stripes:link class="cal"
					href="/econtroller/infopoint/actions/ContentNavigation.action"
					event="ViewDateFolder">
				    <c:out value="${dCal.value[2]}"/>
				    <stripes:param name="folderDate" value="${dCal.key}"/>
				    <stripes:param name="fileFolder" value="${dCal.value[4]}"/>
				</stripes:link></b>
			    </c:if>
			    <c:if test="${dCal.value[4]==''}">
				<c:out value="${dCal.value[2]}"/>
			    </c:if>
			</td>
		    </c:if>
		</c:forEach>
	    </c:if>
	    <c:if test="${dCal.value[0]!='01'}">
		<td class="num">
		    <c:if test="${dCal.value[4]!=''}">
			<b>
			<stripes:link class="cal"
				href="/econtroller/infopoint/actions/ContentNavigation.action"
			 	event="ViewDateFolder">
			    <c:out value="${dCal.value[2]}"/>
			    <stripes:param name="folderDate" value="${dCal.key}"/>
			    <stripes:param name="fileFolder" value="${dCal.value[4]}"/>
			</stripes:link>
			</b>
		    </c:if>
		    <c:if test="${dCal.value[4]==''}">
			    <c:out value="${dCal.value[2]}"/>
		    </c:if>
		</td>
	    </c:if>
	    <c:if test="${dCal.value[3]==7}">
		</tr>
		<tr>
	    </c:if>
	    <c:set var="dYear" value="${fn:substring(dCal.key,0,4)}"/>
	    <c:set var="dMon" value="${fn:substring(dCal.key,4,6)}"/>
	</c:forEach>
    </tr>
    </td>
    </table>
    </table>
    </br>
</div>
<hr>
<div id="calnav">
    <table colspan="3">
	<tr>
	    <td style="width:35%;">
		<center>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Prev6Mos">
			<img src="${ctx}/config/gifs/Actions-arrow-left-icon16.png" border="0"/>
		    </stripes:link>
		    <br/>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Prev6Mos">
			Previous 6 Months
		    </stripes:link>
		</center>
	    </td>
	    <td style="width:30%; align=center">
	    	<table>
		<tr>
		    <td align="center" width="10%" height="19">
			<select size="1" name="folderSelector.endMM">
			    <c:forEach items="${folderSelector.months}" var="mons" begin="1" varStatus="mIndex">
				<c:choose>
				    <c:when test="${folderSelector.months[folderSelector.endMM]==mons}">
					<option value="${mIndex.index}" selected>
					    <c:out value="${mons}"/>
					</option>
				    </c:when>
				    <c:otherwise>
					<option value="${mIndex.index}"><c:out value="${mons}"/></option>
				    </c:otherwise>
				</c:choose>
		    	    </c:forEach>
			</select>
		    </td>
		    <td align="center" width="10%" height="19">
		    	<select size="1" name="folderSelector.endYYYY">
			    <c:forEach items="${folderSelector.years}" var="iY" varStatus="yIndx">
				<c:choose>
				    <c:when test="${folderSelector.endYYYY==iY}">
					<option selected><c:out value="${iY}"/></option>
				    </c:when>
				    <c:otherwise>
					<option><c:out value="${iY}"/></option>
				    </c:otherwise>
				</c:choose>
			    </c:forEach>
			</select>
		    </td>
		    <td>
			<stripes:submit name="ViewCalendar" value="Go"/>
		    </td>
		</tr>
		</table>
	    </td>
	    <td style="width:35%;">
		<center>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Next6Mos">
			<img src="${ctx}/config/gifs/Actions-arrow-right-icon16.png" border="0"/>
		    </stripes:link>
		    <br/>
		    <stripes:link href="/econtroller/infopoint/actions/ContentNavigation.action" event="Next6Mos">
			Next 6 Months
		    </stripes:link>
		</center>
	    </td>
	</tr>
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
