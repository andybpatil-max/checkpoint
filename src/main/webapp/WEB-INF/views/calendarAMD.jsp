<%@ include file="taglibs.jsp" %>
<%--
	<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
	<script language="Javascript" type="text/javascript">
		var calendarWindow			= null;
		var calendarColors			= new Array();
		calendarColors['bgColor']		= '#BDC5D0';
		calendarColors['borderColor']		= '#333366';
		calendarColors['headerBgColor']		= '#143464';
		calendarColors['headerColor']		= '#FFFFFF';
		calendarColors['dateBgColor']		= '#8493A8';
		calendarColors['dateColor']		= '#004080';
		calendarColors['sasuColor']		= '#FF0000';
		calendarColors['dateHoverBgColor']	= '#FFFFFF';
		calendarColors['dateHoverColor']	= '#8493A8';
		var calendarMonths			= new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
		var calendarWeekdays			= new Array('S', 'M', 'T', 'W', 'T', 'F', 'S');
		//var calendarWeekdays			= new Array('S', 'M', 'T', 'W', 'T', 'F');
		var calendarUseToday			= true;
		//var calendarFormat			= 'd/m/y';
		var calendarFormat			= 'm/d/y';
		var calendarStartMonday			= true;
		//
		// getCalendar()
		//
		function getCalendar(in_dateField) 
		{
		//	alert("ENTERED CALENDAR --> "+in_dateField);
		//	alert("ENTERED CALENDAR --> "+idx);
		    if (calendarWindow && !calendarWindow.closed) {
		        alert('Calendar window already open.  Attempting focus...');
		        try {
		            calendarWindow.focus();
		        }
		        catch(e) {}
		        return false;
		    }
		    var cal_width = 415;
		    var cal_height = 310;
		    // IE needs less space to make this thing
		    if ((document.all) && (navigator.userAgent.indexOf("Konqueror") == -1)) {
		        cal_width = 410;
		    }
		    calendarTarget = in_dateField;
		//	alert("ENTERED CALENDAR --> "+calendarTarget);
		    calendarWindow = window.open('${ctx}/js/calendar.html', 'dateSelectorPopup','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=0,dependent=no,width='+cal_width+',height='+cal_height);
		    return false;
		}
		// }}}
		// {{{ killCalendar()
		function killCalendar() 
		{
		    if (calendarWindow && !calendarWindow.closed) {
		        calendarWindow.close();
		    }
		}
		// }}}
	</script>
--%>
<stripes:layout-render name="standard.jsp">
    <stripes:layout-component name="contents">
	<stripes:form name="holidayModifyForm" action="/econtroller/sysadmin/actions/Calendar.action">

	<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
	<script language="Javascript" type="text/javascript">
		var calendarWindow			= null;
		var calendarColors			= new Array();
		calendarColors['bgColor']		= '#BDC5D0';
		calendarColors['borderColor']		= '#333366';
		calendarColors['headerBgColor']		= '#143464';
		calendarColors['headerColor']		= '#FFFFFF';
		calendarColors['dateBgColor']		= '#8493A8';
		calendarColors['dateColor']		= '#004080';
		calendarColors['sasuColor']		= '#FF0000';
		calendarColors['dateHoverBgColor']	= '#FFFFFF';
		calendarColors['dateHoverColor']	= '#8493A8';
		var calendarMonths			= new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
		var calendarWeekdays			= new Array('S', 'M', 'T', 'W', 'T', 'F', 'S');
		//var calendarWeekdays			= new Array('S', 'M', 'T', 'W', 'T', 'F');
		var calendarUseToday			= true;
		//var calendarFormat			= 'd/m/y';
		var calendarFormat			= 'm/d/y';
		var calendarStartMonday			= true;
		//
		// getCalendar()
		//
		function getCalendar(in_dateField) 
		{
		//	alert("ENTERED CALENDAR --> "+in_dateField);
		//	alert("ENTERED CALENDAR --> "+idx);
		    if (calendarWindow && !calendarWindow.closed) {
		        alert('Calendar window already open.  Attempting focus...');
		        try {
		            calendarWindow.focus();
		        }
		        catch(e) {}
		        return false;
		    }
		    var cal_width = 415;
		    var cal_height = 310;
		    // IE needs less space to make this thing
		    if ((document.all) && (navigator.userAgent.indexOf("Konqueror") == -1)) {
		        cal_width = 410;
		    }
		    calendarTarget = in_dateField;
		//	alert("ENTERED CALENDAR --> "+calendarTarget);
		    calendarWindow = window.open('${ctx}/js/calendar.html', 'dateSelectorPopup','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=0,dependent=no,width='+cal_width+',height='+cal_height);
		    return false;
		}
		// }}}
		// {{{ killCalendar()
		function killCalendar() 
		{
		    if (calendarWindow && !calendarWindow.closed) {
		        calendarWindow.close();
		    }
		}
		// }}}
	</script>
<%--
	*****************************************************************************************************
	*****************************************************************************************************
	*****************************************************************************************************
--%>
	<h1><stripes:label for="calendar"/> <stripes:label for="maint"/></h1>

<div id="detail">
	<table colspan='7' width='70%' align='center' border='1'>
	    <tr class="header">
	    	<c:if test='${calSelector.actionFlag=="Modify"}'>
		    <th class="header" align='center' height='19' colspan='10'>
			<font size=3>
			    <stripes:label for="modify"/> <stripes:label for="holidays"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${calSelector.actionFlag=="new_holi"}'>
		    <th class="header" align='center' height='19' colspan='10'>
			<font size=3>
			    <stripes:label for="add"/> <stripes:label for="holidays"/>
			</font>
	   	    </th>
		</c:if>
		<c:if test='${calSelector.actionFlag=="update_confirm"}'>
		    <th class="header" align='center' height='19' colspan='10'>
			<font size=3>
			    <stripes:label for="modify"/> <stripes:label for="holidays"/>
			</font>
		    </th>
		</c:if>
		<c:if test='${calSelector.actionFlag=="add_confirm"}'>
		    <th class="header" align='center' height='19' colspan='10'>
			<font size=3>
			    <stripes:label for="add"/> <stripes:label for="holidays"/>
			</font>
		    </th>
		</c:if>
	    </tr>

	    <tr class="header">
		<th class="header" align='center' width='3%' height=15 colspan=1>
		    <font size=2><stripes:label for="year"/></font>
		</th>
		<th class="header" align='center' width='1%' height=15 colspan=1>
		    <font size=2><stripes:label for="currency"/></font>
		</th>
		<th class="header" align='center' height=15 colspan=7>
		    <font size=2><stripes:label for="holidays"/></font>
		</th>
	    </tr>
	    <td align='center' width=4% height='19' rowspan=4 bgcolor='lightyellow'>
		<c:if test='${calSelector.actionFlag=="Modify"}'>
		    <stripes:text name='calDetail.hdYear' size='6'  readonly='true'/>
		</c:if>
		<c:if test='${calSelector.actionFlag=="new_holi"}'>
		    <stripes:text name='calDetail.hdYear' size='6' />
		</c:if>
		<c:if test='${calSelector.actionFlag=="update_confirm"}'>
		    <stripes:text name='calDetail.hdYear' size='6'  readonly='true'/>
		</c:if>
		<c:if test='${calSelector.actionFlag=="add_confirm"}'>
		    <stripes:text name='calDetail.hdYear' size='6'  readonly='true'/>
		</c:if>
	    </td>
	<td align='center' width=4% height='19' rowspan=4 bgcolor='lightyellow'>
	    <c:if test='${calSelector.actionFlag=="Modify"}'>
		<stripes:text name='calDetail.hdCurr' size='6'  readonly='true'/></td>
	    </c:if>
	    <c:if test='${calSelector.actionFlag=="new_holi"}'>
		<stripes:text name='calDetail.hdCurr' size='6' /></td>
	    </c:if>
	    <c:if test='${calSelector.actionFlag=="update_confirm"}'>
		<stripes:text name='calDetail.hdCurr' size='6'  readonly='true'/></td>
	    </c:if>
	    <c:if test='${calSelector.actionFlag=="add_confirm"}'>
		<stripes:text name='calDetail.hdCurr' size='6'  readonly='true'/></td>
	    </c:if>
	<c:if test='${calSelector.actionFlag=="Modify"}'>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_00" size="10" value="${calDetail.holidate_00}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[2]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_01" size="10" value="${calDetail.holidate_01}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[3]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_02" size="10" value="${calDetail.holidate_02}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[4]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_03" size="10" value="${calDetail.holidate_03}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[5]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_04" size="10" value="${calDetail.holidate_04}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[6]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_05" size="10" value="${calDetail.holidate_05}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[7]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_06" size="10" value="${calDetail.holidate_06}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[8]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_07" size="10" value="${calDetail.holidate_07}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[9]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_08" size="10" value="${calDetail.holidate_08}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[10]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_09" size="10" value="${calDetail.holidate_09}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[11]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_10" size="10" value="${calDetail.holidate_10}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[12]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_11" size="10" value="${calDetail.holidate_11}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[13]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_12" size="10" value="${calDetail.holidate_12}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[14]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_13" size="10" value="${calDetail.holidate_13}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[15]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_14" size="10" value="${calDetail.holidate_14}"/>
			<stripes:link href="javascript: void(0);" onclick="return getCalendar(document.holidayModifyForm.elements[16]);">
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_15" size="10" value="${calDetail.holidate_15}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[17]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_16" size="10" value="${calDetail.holidate_16}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[18]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_17" size="10" value="${calDetail.holidate_17}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[19]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_18" size="10" value="${calDetail.holidate_18}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[20]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_19" size="10" value="${calDetail.holidate_19}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[21]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
<%--
		<td></td>
--%>
	    </tr>
	</c:if>
	<c:if test='${calSelector.actionFlag=="new_holi"}'>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_00" size="10" value="${calDetail.holidate_00}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[2]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_01" size="10" value="${calDetail.holidate_01}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[3]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_02" size="10" value="${calDetail.holidate_02}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[4]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_03" size="10" value="${calDetail.holidate_03}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[5]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_04" size="10" value="${calDetail.holidate_04}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[6]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_05" size="10" value="${calDetail.holidate_05}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[7]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_06" size="10" value="${calDetail.holidate_06}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[8]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_07" size="10" value="${calDetail.holidate_07}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[9]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_08" size="10" value="${calDetail.holidate_08}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[10]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_09" size="10" value="${calDetail.holidate_09}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[11]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_10" size="10" value="${calDetail.holidate_10}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[12]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_11" size="10" value="${calDetail.holidate_11}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[13]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_12" size="10" value="${calDetail.holidate_12}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[14]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_13" size="10" value="${calDetail.holidate_13}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[15]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_14" size="10" value="${calDetail.holidate_14}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[16]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_15" size="10" value="${calDetail.holidate_15}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[17]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_16" size="10" value="${calDetail.holidate_16}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[18]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%> 
		    <stripes:text name="calDetail.holidate_17" size="10" value="${calDetail.holidate_17}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[19]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_18" size="10" value="${calDetail.holidate_18}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[20]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name="calDetail.holidate_19" size="10" value="${calDetail.holidate_19}"/>
			<stripes:link href="javascript: void(0);" onclick='return getCalendar(document.holidayModifyForm.elements[21]);'>
			<img src="${ctx}/calendar.png" border="0"></stripes:link></td>
		<td></td>
	    </tr>
	</c:if>
	<c:if test='${calSelector.actionFlag=="update_confirm"}'>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[0]' size='10' value="${calDetail.holidate_00}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[1]' size='10' value="${calDetail.holidate_02}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[2]' size='10' value="${calDetail.holidate_03}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[3]' size='10' value="${calDetail.holidate_04}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[4]' size='10' value="${calDetail.holidate_05}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[5]' size='10' value="${calDetail.holidate_06}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[6]' size='10' value="${calDetail.holidate_07}"
		    		  readonly='true'/></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[7]' size='10' value="${calDetail.holidate_08}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[8]' size='10' value="${calDetail.holidate_09}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[9]' size='10' value="${calDetail.holidate_10}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[10]' size='10' value="${calDetail.holidate_11}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[11]' size='10' value="${calDetail.holidate_12}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[12]' size='10' value="${calDetail.holidate_13}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[13]' size='10' value="${calDetail.holidate_14}"
		    		  readonly='true'/></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[14]' size='10' value="${calDetail.holidate_15}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[15]' size='10' value="${calDetail.holidate_16}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[16]' size='10' value="${calDetail.holidate_17}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[17]' size='10' value="${calDetail.holidate_18}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[18]' size='10' value="${calDetail.holidate_19}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[19]' size='10' value="${calDetail.holidate_19}"
		    		  readonly='true'/></td>
		<td></td>
	    </tr>
	</c:if>
	<c:if test='${calSelector.actionFlag=="add_confirm"}'>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[0]' size='10' value="${calDetail.holidate_00}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[1]' size='10' value="${calDetail.holidate_01}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[2]' size='10' value="${calDetail.holidate_02}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[3]' size='10' value="${calDetail.holidate_03}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[4]' size='10' value="${calDetail.holidate_04}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[5]' size='10' value="${calDetail.holidate_05}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[6]' size='10' value="${calDetail.holidate_06}"
		    		  readonly='true'/></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[7]' size='10' value="${calDetail.holidate_07}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[8]' size='10' value="${calDetail.holidate_08}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[9]' size='10' value="${calDetail.holidate_09}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[10]' size='10' value="${calDetail.holidate_10}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[11]' size='10' value="${calDetail.holidate_11}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[12]' size='10' value="${calDetail.holidate_12}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[13]' size='10' value="${calDetail.holidate_13}"
		    		  readonly='true'/></td>
	    </tr>
	    <tr>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[14]' size='10' value="${calDetail.holidate_14}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[15]' size='10' value="${calDetail.holidate_15}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[16]' size='10' value="${calDetail.holidate_16}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[17]' size='10' value="${calDetail.holidate_17}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[18]' size='10' value="${calDetail.holidate_18}"
		    		  readonly='true'/></td>
		<td align='left' height='15' width=3%>
		    <stripes:text name='calDetail.dateArray[19]' size='10' value="${calDetail.holidate_19}"
		    		  readonly='true'/></td>
		<td></td>
	    </tr>
	</c:if>
	</tr>
</table>
</div>

<%--
--%>

<%--	<c:out value='${calSelector.actionFlag}'/>			--%>
    <center>
     <stripes:errors />
     <c:if test='${calSelector.actionFlag=="Modify"}'>
	<stripes:submit  name="Update"  value="Update"/>
     </c:if>
     <c:if test='${calSelector.actionFlag=="update_confirm"}'>
	<stripes:submit  name="Confirm"  value="Confirm Update Holiday"/>
     </c:if>
     <c:if test='${calSelector.actionFlag=="add_confirm"}'>
	<stripes:submit  name="Confirm"  value="Confirm Add Holiday"/>
     </c:if>
     <c:if test='${calSelector.actionFlag=="new_holi"}'>
	<stripes:submit name="Add" value="Add"/>
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

