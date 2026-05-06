<%@ include file="taglibs.jsp" %>
<stripes:layout-render name="standardrep.jsp">
    <stripes:layout-component name="contents">
	<stripes:form action="/econtroller/infopoint/actions/ContentNavigation.action">
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
<div class="search">
--%>

    <h2>
	<stripes:label for="complexSearch"/> of 
    	<b><c:out value="${folderSelector.fileViewed}"/></b>
	" dated "
	<b><c:out value="${folderSelector.folderDate}"/></b>
    </h2>
<c:set var="option" value="1" scope="request"/>
<stripes:hidden name="folderSelector.actionFlag" value="" id="actionFlag"/>
<div id="caltab">
<%--
**************************************************************************************************
--%>
    <div id="tab10" style="display:none;">
	<table colspan="9" style="width:940px;">
	<th class="header" colspan="9"><input type="button" value="Search on single line" 
	    onclick="swoptions('oneLine');" ></th>
	</table>
    </div>
    <div id="oneLine">
	<table colspan="9" style="width:940px;">
	<tr>
	    <th colspan="9" class="header">Single Line Search Criteria</th>
	</tr>
	<tr>
	    <th class="header">Line</th>
	    <th class="header">Required Field</th>
	    <th class="header">Logic</th>
	    <th class="header">Optional Field</th>
	    <th class="header">Logic</th>
	    <th class="header" colspan=4>Number/Date Range</th>
	</tr>
	<tr>
	    <td rowspan=2>1</td>
	    <td>
		<stripes:text name="folderSelector.o1ReqField" value="${folderSelector.o1ReqField}"
					size='15' maxlength="80"/>
	    </td>
	    <td rowspan=2>
		AND<stripes:radio checked="AND" name="folderSelector.o1Radio" value="AND"/><br/>
		OR<stripes:radio name="folderSelector.o1Radio" value="OR"/>
	    </td>
	    <td>
		<stripes:text name="folderSelector.o1OptField"
	    		value="${folderSelector.o1OptField}" size="15" maxlength="80"/></td>
	    <td rowspan=2>
		AND
	    </td>
	    <td>date:<stripes:radio name="folderSelector.o1DateNumAmtRadio" value="DATE_YMD"/>ymd
		<stripes:radio name="folderSelector.o1DateNumAmtRadio" value="DATE_MDY"/>mdy
		<stripes:radio name="folderSelector.o1DateNumAmtRadio" value="DATE_DMY"/>dmy<br/>
		<stripes:radio name="folderSelector.o1DateNumAmtRadio" value="NUMBER"/>Number
		<stripes:radio checked="AMOUNT" name="folderSelector.o1DateNumAmtRadio" value="AMOUNT"/>Amount
	    </td>
	    <td rowspan="2">Between</td>
	    <td rowspan="2">
		<stripes:text name="folderSelector.o1DateNumAmtFrom"
		    value="${folderSelector.o1DateNumAmtFrom}" size="7" maxlength="15"/>
	    </td>
	    <td rowspan="2">
	    	<stripes:text name="folderSelector.o1DateNumAmtTo" 
		    value="${folderSelector.o1DateNumAmtTo}" size="7" maxlength="15"/>
	    </td>
	</tr>
	<tr>
	    <td> Column <br/>
		from <stripes:text name="folderSelector.o1ReqFieldColSt" 
		    value="${folderSelector.o1ReqFieldColSt}" size="3" maxlength="3"/>
		to <stripes:text name="folderSelector.o1ReqFieldColTo"
		    value="${folderSelector.o1ReqFieldColTo}" size="3" maxlength="3"/>
	    </td>
	    <td> Column <BR/>
		from <stripes:text name="folderSelector.o1OptFieldColSt" 
		    value="${folderSelector.o1OptFieldColSt}" size="3" maxlength="3"/>
		to <stripes:text name="folderSelector.o1OptFieldColTo" 
		    value="${folderSelector.o1OptFieldColTo}" size="3" maxlength="3"/>
	    </td>
	    <td> Column <BR/>
		from <stripes:text name="folderSelector.o1DateNumAmtColSt" 
		    value="${folderSelector.o1DateNumAmtColSt}" size="3" maxlength="3"/>
		to <stripes:text name="folderSelector.o1DateNumAmtColTo" 
		    value="${folderSelector.o1DateNumAmtColTo}" size="3" maxlength="3"/>
	    </td>
	</tr>
	</table>
    </div>
<br/>
<%--
**************************************************************************************************
--%>

    <div id="tab20">
	<table colspan="9" style="width:940px;">
	<th class="header" colspan="9"><input type="button" value="Search on 2 lines" 
	    onclick="swoptions('twoLines');" ></th>
	</table>
    </div>
<%--
    <h3><input type="button" value="Search on 2 lines" onclick="swoptions('tab2')"; ></h3>
--%>
    <div id="twoLines" style="display:none;">
	<table style="width:940px;">
	    <tr><th class="header" colspan=9>2 Line Search Criteria</th></tr>
	    <tr>
		<th rowspan=1>Line</th>
		<th rowspan=1>Required Field</th>
		<th rowspan=1>Logic</th>
		<th rowspan=1>Optional Field</th>
		<th rowspan=1>Logic</th>
		<th rowspan=1 colspan=4># lines between Search Criteria lines 1&2</th>
	    </tr>
	    <tr>
		<td>1</td>
		<td><stripes:text name="folderSelector.o2ReqFieldL1"
			value="${folderSelector.o2ReqFieldL1}" size="15" maxlength="80"/>
		</td>
		<td>
		    <stripes:radio name="folderSelector.o2RadioL1" value="AND" checked="AND"/>AND <br>
		    <stripes:radio name="folderSelector.o2RadioL1" value="OR"/>OR
		</td>
		<td>
		    <stripes:text name="folderSelector.o2OptFieldL1"
			value="${folderSelector.o2OptFieldL1}" size="15" maxlength="80"/>
		</td>
		<td>AND</td>
		<td colspan=4 align=center>
		    <select name="folderSelector.o2LinesAfterL1" type=number>
			<c:forEach var="lAfter" begin="0" end="6" varStatus="lInd">
			    <c:if test="${folderSelector.o2LinesAfterL1==lAfter}">
				<option selected><c:out value="${lAfter}"/></option>
			    </c:if>
			    <c:if test="${folderSelector.o2LinesAfterL1!=lAfter}">
				<option><c:out value="${lAfter}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		</td>
	    </tr>
	    <tr>
		<td rowspan=2>2</td>
		<td rowspan=2>
		    <stripes:text name="folderSelector.o2ReqFieldL2"
			value="${folderSelector.o2ReqFieldL2}" size="15" maxlength="80"/>
		</td>
		<td rowspan=2>
		    <stripes:radio name="folderSelector.o2RadioL2" value="AND" checked="AND"/>AND <br>
		    <stripes:radio name="folderSelector.o2RadioL2" value="OR"/>OR
		</td>
		<td rowspan=2>
		    <stripes:text name="folderSelector.o2OptFieldL2"
			value="${folderSelector.o2OptFieldL2}" size="15" maxlength="80"/>
		</td>
		<td rowspan="2">AND</td>
		<th colspan=4 class="header">Number/Date Range</th>
	    </tr>
	    <tr>
		<td>date:<stripes:radio name="folderSelector.o2DateNumAmtRadio" value="DATE_YMD"/>ymd
		    	 <stripes:radio name="folderSelector.o2DateNumAmtRadio" value="DATE_MDY"/>mdy
		    	 <stripes:radio name="folderSelector.o2DateNumAmtRadio" value="DATE_DMY"/>dmy <br/>
			 <stripes:radio name="folderSelector.o2DateNumAmtRadio" value="NUMBER"/>Number
			 <stripes:radio name="folderSelector.o2DateNumAmtRadio" value="AMOUNT" checked="AMOUNT"/>Amount
		</td>
		<td>Between</td>
		<td>
		    <stripes:text name="folderSelector.o2DateNumAmtFrom"
			value="${folderSelector.o2DateNumAmtFrom}" size="15" maxlength="25"/>
		</td>
		<td>
		    <stripes:text name="folderSelector.o2DateNumAmtTo"
			value="${folderSelector.o2DateNumAmtTo}" size="15" maxlength="25"/>
		</td>
	    </tr>
	</table>
    </div>
<br/>
<%--
**************************************************************************************************
--%>

    <div id="tab30">
	<table colspan="14" style="width:940px;">
	    <th class="header" colspan="14">
		<stripes:button name="folderSelector.actionFlag" value="Search Text only" 
			onclick="swoptions('textOnly');"/>
<%--		<input type="button" value="Search Text only" onclick="swoptions('tab31');" > --%>
	    </th>
	</table>
    </div>
<%--    <h3><input type="button" value="Search Text only" onclick="swoptions('tab3')"; ></h3> --%>
    <div id="textOnly" style="display:none;">
	<table style="width:940px;">
	    <tr>
		<th class="header" colspan="14">Text Only Search Criteria</th>
	    </tr>
	    <tr>
	        <th>Line</th>
	        <th>Required Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	        <th>Logic</th>
	        <th>Optional Field</th>
	    </tr>
	    <tr>
		<td rowspan="2">1</td>
		<td ><stripes:text name="folderSelector.o3ReqField"
		    value="${folderSelector.o3ReqField}" size="7" maxlength="80"/>
		</td>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField1"
		    value="${folderSelector.o3OptField1}" size="7" maxlength="80"/>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField2"
		    value="${folderSelector.o3OptField2}" size="7" maxlength="80"/>
		</td>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField3"
		    value="${folderSelector.o3OptField3}" size="7" maxlength="80"/>
		</td>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField4"
		    value="${folderSelector.o3OptField4}" size="7" maxlength="80"/>
		</td>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField5"
		    value="${folderSelector.o3OptField5}" size="7" maxlength="80"/>
		</td>
		<td rowspan="2">OR</td>
		<td rowspan="2"><stripes:text name="folderSelector.o3OptField6"
		    value="${folderSelector.o3OptField6}" size="7" maxlength="80"/>
		</td>
	    </tr>
	    <tr>
		<td>Column<br>
		    from <stripes:text name="folderSelector.o3ReqFieldColSt"
			value="${folderSelector.o3ReqFieldColSt}" size="3" maxlength="3"/>
		    to <stripes:text name="folderSelector.o3ReqFieldColTo" 
			value="${folderSelector.o3ReqFieldColTo}" size="3" maxlength="3"/>
		</td>
	    </tr>
	</table>
    </div>
<br/>
<%--
**************************************************************************************************
--%>
	<table colspan="3" style="width:940px;">
	    <tr>
		<th class="header" colspan="3">Output Control</th>
	    </tr>
	    <tr><th class="header">Type</th>
		<th class="header">Format</th>
		<th class="header">Return Output</th>
	    </tr>
	    <tr>
		<td colspan="1">
		    <stripes:radio name="folderSelector.outRadio" value="ALL" checked="ALL"/>All occurrences<br>
		    <stripes:radio name="folderSelector.outRadio" value="STEP"/>One occurrence at a time<br>
		    <stripes:radio name="folderSelector.outRadio" value="POS"/>Position View to first Match
		</td>
		<td colspan="1">Annotation: 
		    <stripes:radio name="folderSelector.annotRadio" value="OFF" checked="OFF"/>OFF
                    <stripes:radio name="folderSelector.annotRadio" value="ON"/>ON
		    <stripes:radio name="folderSelector.annotRadio" value="VIEW-ALL"/>View-All
		</td>
    		<td colspan="1">
		    <stripes:radio name="folderSelector.pageLineRadio" value="PAGE" checked="PAGE"/>
			   Entire page where search criteria line(s) is/are found or <br>
		    <stripes:radio name="folderSelector.pageLineRadio" value="LINES"/>by lines --> # lines before: 
		    <select size="1" name="folderSelector.linesBefore">
			<c:forEach var="lBef" begin="0" end="9" varStatus="lbInd">
			    <c:if test="${folderSelector.linesBefore==lBef}">
				<option selected><c:out value="${lBef}"/></option>
			    </c:if>
			    <c:if test="${folderSelector.linesBefore!=lBef}">
				<option><c:out value="${lBef}"/></option>
			    </c:if>
			</c:forEach>
		    </select>
		    after: 
		    <select size="1" name="folderSelector.linesAfter">
			<c:forEach var="lAft" begin="0" end="9" varStatus="laInd">
			    <c:if test="${folderSelector.linesAfter==lAft}">
				<option selected><c:out value="${lAft}"/></option>
			    </c:if>
			    <c:if test="${folderSelector.linesAfter!=lAft}">
				<option><c:out value="${lAft}"/></option>
			    </c:if>
			</c:forEach>
<%--
		    	 <option selected >0</option>
			<option >1</option>
			<option >2</option>
			<option >3</option>
			<option >4</option>
			<option >5</option>
			<option >6</option>
			<option >7</option>
			<option >8</option>
			<option >9</option>
--%>
		    </select>
		    found line(s).
		</td>
	    </tr>
<%--
	</table>
<br/>
	<table>
**************************************************************************************************
--%>
	    <tr>
		<th class="header"  colspan="3">Action</th>
	    </tr>
	    <tr>
	    	<td class="thdr" colspan="3" style="text-align:center; background-color:lightyellow; background-image:none;">
		    Pattern Name:
		    <stripes:text name="folderSelector.searchPattern" size="32" maxlength="32"
		    		  value="${folderSelector.searchPattern}"/>
	    	Pattern Actions:
				<stripes:submit name="SavePattern" value="Save"/>
				<stripes:checkbox name="folderSelector.overWrite" value="ON" checked=""/>
						  		Overwrite existing version.
				<input type=reset value=Reset>
		    <br>
		    To customize pattern name it is recommended to add additional characters at the end of the default name
		</td>
	    </tr>
	    <c:if test="${folderSelector.patternList.size()!=0}">
	    <tr>
	        <td class="thdr" colspan="3"
		    style="text-align:center; background-color:lightyellow; background-image:none;">
				<stripes:submit name="LoadPattern" value="Load Selected"/>
		    selected from list 
		    <select size="1" name="folderSelector.savedPattern">
			<c:forEach var="pattern" items="${folderSelector.patternList}">
			    <c:if test='${folderSelector.savedPattern!=pattern}'>
			        <option> <c:out value="${pattern}"/> </option>
			    </c:if>
			    <c:if test='${folderSelector.savedPattern==pattern}'>
				<option selected> <c:out value="${pattern}"/> </option>
			    </c:if>
			</c:forEach>
		    </select>
				<stripes:submit name="DeletePattern" value="Delete"/>
				<stripes:checkbox name="folderSelector.delConfirm" value="ON" checked=""/>
				       		     		Confirm deletion
		</td>
	    </tr>
	    </c:if>
	    <tr>
		<th class="header" colspan="3" style="background-color:lightyellow; background-image:none;">
		    <stripes:submit name="DoSearch" value="Perform Search"/>
		</th>
	    </tr>
	</table>
</div>
<stripes:errors />
<%--
<c:out value="${folderSelector.actionFlag}"/>
**************************************************************************************************
--%>


<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	    <script>
		window.onload = swoptions("${folderSelector.actionFlag}");
	    </script>
	</stripes:form>
    </stripes:layout-component>
</stripes:layout-render>
